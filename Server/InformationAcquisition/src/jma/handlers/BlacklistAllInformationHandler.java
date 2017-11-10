package jma.handlers;

import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.StringUtils;
import catfish.base.business.common.ContactPersonType;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;
import jma.DatabaseEnumValues.BlacklistType;
import jma.DatabaseUtils;
import jma.RetryRequiredException;

public class BlacklistAllInformationHandler extends BlacklistPersonalInformationHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    super.execute(appId);
    
    blacklistContact(appId, ContactPersonType.FirstContactPerson.getValue(),reason, channel.name());
    blacklistContact(appId, ContactPersonType.SecondContactPerson.getValue(), reason, channel.name());
    blacklistCompany(appId, reason, channel.name());
  }

  private static void blacklistContact(String appId, int contactType,String reason, String channel) {
    List<String> info = getContactInfo(appId, contactType);
    if (info == null) {
      return;
    }
    blacklistInfo(info.get(0), info.get(1), BlacklistType.PHONE, reason, channel);
  }

  private static void blacklistCompany(String appId, String reason, String channel) {
    List<String> info = getCompanyInfo(appId);
    if (info == null
        || StringUtils.isNullOrWhiteSpaces(info.get(1))
        || !info.get(1).startsWith("1")) {
      return;
    }

    List<String> nameAndIdNumber = DatabaseUtils.getIdCardNameAndNumber(appId);
    String userName = nameAndIdNumber == null ? null : nameAndIdNumber.get(0);

    blacklistInfo(String.format("%s_%s", userName, info.get(0)), info.get(1), BlacklistType.PHONE, reason, channel);
  }

  private static List<String> getContactInfo(String appId, int contactType) {
    String sql =
        "SELECT ContactPersonObjects.Name, ContactObjects.Content " +
        "FROM ContactObjects, ContactPersonObjects " +
        "WHERE ContactObjects.Id = ContactPersonObjects.MobileContactId " +
        "  AND ContactPersonObjects.AppId = :AppId " +
        "  AND ContactPersonObjects.ContactPersonType = :Type " +
        "ORDER BY ContactObjects.LastModified DESC";
    Map<String, ?> params = CollectionUtils.mapOf(
        "AppId", appId,
        "Type", contactType);
    return DatabaseApi.querySingleResultOrDefault(
        sql, params, DatabaseExtractors.TWO_STRING_EXTRACTOR, null);
  }

  private static List<String> getCompanyInfo(String appId) {
    String sql =
        "SELECT JobInfoObjects.CompanyName, ContactObjects.Content " +
        "FROM ContactObjects, JobInfoObjects " +
        "WHERE ContactObjects.Id = JobInfoObjects.CompanyTelId " +
        "  AND JobInfoObjects.ApplicationId = :AppId " +
        "ORDER BY ContactObjects.LastModified DESC";
    return DatabaseApi.querySingleResultOrDefault(
        sql, CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.TWO_STRING_EXTRACTOR, null);
  }
}
