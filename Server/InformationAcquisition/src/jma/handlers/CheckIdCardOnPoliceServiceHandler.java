package jma.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.OssManager;
import jma.RetryRequiredException;
import jma.thirdpartyservices.IdCardPoliceChecker;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.StringUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseConfig;
import catfish.base.database.DatabaseExtractors;

public class CheckIdCardOnPoliceServiceHandler extends NonBlockingJobHandler {

  protected static IdCardPoliceChecker checker;

  static {
    try {
      checker = IdCardPoliceChecker.createCheckerChain();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void execute(String appId) throws RetryRequiredException {

	  if(DynamicConfig.read("IdCardOnPoliceServiceSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue()))
	  {
		  return;
	  }

    List<String> nameAndNumber = DatabaseUtils.getIdCardNameAndNumber(appId);
    String name = nameAndNumber.get(0);
    String number = nameAndNumber.get(1).toUpperCase();
    
    List<String> cachedInfo = tryGetingCachedResultAndPath(name, number);
    String validationResult = cachedInfo.get(0);
    String photoPath = getFilePathBy(cachedInfo.get(1));
    if (StringUtils.isNullOrWhiteSpaces(validationResult)
        || StringUtils.isNullOrWhiteSpaces(photoPath)) {
      List<String> info = checkOnPoliceService(name, number,appId);
      validationResult = info.get(0);
      photoPath = info.get(1);
    }

    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.ID_CARD_IDENTIFICATION_RESULT, validationResult)
        .add(AppDerivativeVariableNames.ID_CARD_IDENTIFICATION_PHOTO_PATH, photoPath)
        .build());
  }

  private static List<String> tryGetingCachedResultAndPath(String name, String number) {
    String sql =
        "SELECT Result, AttachmentId " +
        "FROM IdResultObjects " +
        "WHERE Name = :Name " +
        "    AND IdNumber = :Number " +
        "ORDER BY LastModified";
    Map<String, ?> params = CollectionUtils.mapOf("Name", name, "Number", number);
    List<String> defaultResult = Arrays.asList("", "");
    return DatabaseApi.querySingleResultOrDefault(
        sql, params, DatabaseExtractors.TWO_STRING_EXTRACTOR, defaultResult);
  }

  private static String getFilePathBy(String attachmentId) {
    if (StringUtils.isNullOrWhiteSpaces(attachmentId)) {
      return null;
    }

    return DatabaseApi.querySingleString(
        "SELECT FilePath FROM AttachmentObjects WHERE Id = :Id",
        CollectionUtils.mapOf("Id", attachmentId));
  }

  private static List<String> checkOnPoliceService(String name, String number, String appId)
    throws RetryRequiredException {

    IdCardPoliceChecker.Result result = checker.check(name, number, appId);
    String photoPath = String.format("IdentifierPhoto/%s/%s.png", name, number);
    String attachmentId = UUID.randomUUID().toString();
    if(result.getPhoto().length>100){//假设图片很小，应该是没取到，不需要存储
    	OssManager.put(photoPath, result.getPhoto());
    }
    else{
    	photoPath="";
    }
    cacheAttachment(attachmentId, photoPath);
    cacheResult(name, number, result.getMsg(), attachmentId);

    return Arrays.asList(result.derivationVariableValue(), photoPath);
  }
  
  private static List<String> checkOnPoliceService(String name, String number)
		  throws RetryRequiredException {
	  return checkOnPoliceService(name,number,"00000000-0000-0000-0000-000000000000");
  }
  private static void cacheAttachment(String attachmentId, String filePath) {
    String sql =
        "INSERT INTO AttachmentObjects ( " +
        "    Id, FileName, FilePath, MimeType, DateAdded, LastModified) " +
        "VALUES (:Id, :FileName, :FilePath, :MimeType, :DateAdded, :LastModified)";

    String timeString = DateTimeUtils.getCurrentTimeString();
    Map<String, String> params = CollectionUtils.<String, String>newMapBuilder()
        .add("Id", attachmentId)
        .add("FileName", filePath)
        .add("FilePath", filePath)
        .add("MimeType", "image/png")
        .add("DateAdded", timeString)
        .add("LastModified", timeString)
        .build();

    new NamedParameterJdbcTemplate(DatabaseConfig.getDataSource()).update(sql, params);
  }

  private static void cacheResult(String name, String number, String result, String attachmentId) {
    String sql =
        "INSERT INTO IdResultObjects ( " +
        "    Name, IdNumber, Result, AttachmentId, DateAdded, LastModified) " +
        "VALUES (:Name, :IdNumber, :Result, :AttachmentId, :DateAdded, :LastModified)";

    String timeString = DateTimeUtils.getCurrentTimeString();
    Map<String, String> params = CollectionUtils.<String, String>newMapBuilder()
        .add("Name", name)
        .add("IdNumber", number)
        .add("Result", result)
        .add("AttachmentId", attachmentId)
        .add("DateAdded", timeString)
        .add("LastModified", timeString)
        .build();

    new NamedParameterJdbcTemplate(DatabaseConfig.getDataSource()).update(sql, params);
  }
}
