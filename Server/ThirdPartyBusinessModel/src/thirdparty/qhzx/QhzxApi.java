package thirdparty.qhzx;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import thirdparty.config.QhzxConfiguration;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.httpclient.HttpClientApi;

import com.google.gson.Gson;

public class QhzxApi {

  public static DomainResult query(String appId, String idNumber, String idName, boolean isNew) {
    Gson gson = new Gson();
    String reqString = gson.toJson(prepareRequest(appId, prepareQueryRecord(idNumber, idName, appId)));
    
    Datagram response = convertResponse(post(
        isNew?QhzxConfiguration.getQueryUrlNew():QhzxConfiguration.getQueryUrl(),
        reqString));
    
    DomainResult result = new DomainResult();
    result.setRawResult(gson.toJson(response));
    if (isValid(response.getHeader().getRtCode())) {
      processDomainResult(response.getRawBusiData(), result);
    }
    return result;
  }

  public static boolean report(
      String appId,
      String idNumber,
      String idName,
      String gradeReport,
      String updatedDate,
      String money,
      String phoneNo,
      String cardNo,
      String qqNo) {

    Record record = new Record();
    record.setIdType("0");
    record.setIdNo(idNumber.toUpperCase());
    record.setName(idName);
    record.setGradeReport(gradeReport);
    record.setMoney(money);
    record.setCurrency("CNY");
    record.setPhoneNo(phoneNo);
    record.setCardNo(cardNo);
    record.setQqNo(qqNo);
    record.setUpdatedDate(updatedDate);
    record.setSeqNo("Seq01");

    Datagram result = convertResponse(post(
        QhzxConfiguration.getReportUrl(),
        new Gson().toJson(prepareRequest(appId, record))));

    Header header = result.getHeader();
    if (!isValid(header.getRtCode())) {
      Logger.get().warn(String.format(
          "Error from http request %s : %s", header.getRtCode(), header.getRtMsg()));
      return false;
    }
    record = result.getRawBusiData().getRecords().get(0);
    if (!isValid(record.getErCode())) {
      Logger.get().warn(String.format(
          "Error from blacklist report %s : %s", record.getErCode(), record.getErMsg()));
      return false;
    }
    return true;
  }

  private static Datagram prepareRequest(String appId, Record record) {
    Date now = new Date();
    String dataNo = new SimpleDateFormat("yyyyMMddHHmmss").format(now) + appId.substring(0, 8);

    Header header = new Header();
    header.setOrgCode(QhzxConfiguration.getOrgCode());
    header.setChnlId(QhzxConfiguration.getChnlId());
    header.setTransNo(dataNo);
    header.setTransDate(DateTimeUtils.format(now));
    header.setAuthCode(QhzxConfiguration.getAuthCode());
    header.setAuthDate(DateTimeUtils.format(now));

    BusiData busiData = new BusiData();
    busiData.setBatchNo(dataNo);
    busiData.setRecords(Arrays.asList(record));
    String encryptedBusiData = encrypt(
        new Gson().toJson(busiData),
        QhzxConfiguration.getEncryptionKey());

    SecurityInfo securityInfo = new SecurityInfo();
    securityInfo.setSignatureValue(signData(encryptedBusiData));
    securityInfo.setUserName(QhzxConfiguration.getUserName());
    securityInfo.setUserPassword(DigestUtils.shaHex(QhzxConfiguration.getUserPassword()));

    Datagram datagram = new Datagram();
    datagram.setHeader(header);
    datagram.setBusiData(encryptedBusiData);
    datagram.setSecurityInfo(securityInfo);

    return datagram;
  }

  private static Record prepareQueryRecord(String idNumber, String idName, String appId) {
    Record record = new Record();
    record.setIdType("0");
    record.setIdNo(idNumber.toUpperCase());
    record.setName(idName);
    record.setReasonCode("01");
    record.setSeqNo("Seq01");
    
    record.setEntityAuthCode(appId.substring(0, 8));
    record.setEntityAuthDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    return record;
  }

  private static String post(String url, String content) {
    return HttpClientApi.postString(
        url, content, CollectionUtils.mapOf("Content-Type", "application/json;charset=utf-8"));
  }

  private static Datagram convertResponse(String rawResponse) {
    Gson gson = new Gson();
    Datagram datagram = gson.fromJson(rawResponse, Datagram.class);
    if (!isValid(datagram.getHeader().getRtCode())) {
      Logger.get().warn("QHZX Api got response code " + datagram.getHeader().getRtCode());
      return datagram;
    }

    verifyData(datagram.getBusiData(), datagram.getSecurityInfo().getSignatureValue());
    datagram.setRawBusiData(gson.fromJson(
        decrypt(datagram.getBusiData(), QhzxConfiguration.getEncryptionKey()),
        BusiData.class));
    return datagram;
  }

  private static DomainResult processDomainResult(BusiData response, DomainResult domain) {
    for (Record record : response.getRecords()) {
      if (!isValid(record.getErCode())) {
        if (!record.getErCode().equals("E000996")) {    // No data found
          Logger.get().warn("QHZX Api got single query response code " + record.getErCode());
        }
        continue;
      }

      domain.setHasBlacklist(true);

      if (!StringUtils.isNullOrWhiteSpaces(record.getDataBuildTime())) {
        Date date = DateTimeUtils.parseYMD(record.getDataBuildTime());
        if (domain.getLatestBuildTime() == null
            || domain.getLatestBuildTime().before(date)) {
          domain.setLatestBuildTime(date);
        }
      }
      
      if(!StringUtils.isNullOrWhiteSpaces(record.getGradeQuery())){
    	  int grade = tryParse(record.getGradeQuery(), 0);
          if (0 < grade && grade < 99) {
            if (grade == 1) {
              domain.setHasBeenExecuted(true);
            }
            if (grade == 7) {
              domain.setHasBeenBrokenPromise(true);
            }
            if (grade == 9) {
              domain.setHasBeenBroeknPromiseAndExecuted(true);
            }
            if (grade == 3 || grade == 5 || grade == 6) {
              domain.setMaxOverdueGrade(Math.max(domain.getMaxOverdueGrade(), grade));
            }
          }
      }
      
      if(!StringUtils.isNullOrWhiteSpaces(record.getMoneyBound())){
    	  int bound = tryParse(record.getMoneyBound(), 0);
          if (0 < bound && bound< 99) {
            domain.setMaxMoneyBound(Math.max(domain.getMaxMoneyBound(), bound));
          }
      }
      
      
      if(!StringUtils.isNullOrWhiteSpaces(record.getSourceId())){
    	  switch(record.getSourceId()){
	    	  case "A":{
	    		  domain.setHasCreditOverdueRisk(true);
	    		  break;
	    	  }
	    	  case "B":{
	    		  domain.setHasAdministrationNegativeRisk(true);
	    		  break;
	    	  }
	    	  case "C":{
	    		  domain.setHasFraudRisk(true);
	    		  break;
	    	  }
    	  }
      }
      
      if(!StringUtils.isNullOrWhiteSpaces(record.getRskScore())){
    	  int riskScore = tryParse(record.getRskScore(), 10);
    	  if (10 <= riskScore && riskScore <= 45) {
              domain.setRiskScore(riskScore);
          }
      }
      
      
      if(!StringUtils.isNullOrWhiteSpaces(record.getRskMark())){
    	  switch(record.getRskMark()){
	    	  case "B1":{
	    		  domain.setHasBeenBroeknPromiseAndExecuted(true);
	    		  break;
	    	  }
	    	  case "B2":{
	    		  domain.setHasBeenExecuted(true);
	    		  break;
	    	  }
	    	  case "B3":{
	    		  domain.setHasSeriousTrafficViolationRisk(true);
	    		  break;
	    	  }
	    	  case "C1":{
	    		  domain.setHasMobileFraudRisk(true);
	    		  break;
	    	  }
	    	  case "C2":{
	    		  domain.setHasBankCardFraudRisk(true);;
	    		  break;
	    	  }
	    	  case "C3":{
	    		  domain.setHasIdCardFraudRisk(true);
	    		  break;
	    	  }
	    	  case "C4":{
	    		  domain.setHasIPAddressFraudRisk(true);
	    		  break;
	    	  }
	    	  
    	  }
      }
      
      if(!StringUtils.isNullOrWhiteSpaces(record.getDataStatus())){
    	  domain.setDataStatus(record.getDataStatus());
      }
      
      
    }
    return domain;
  }

  private static boolean isValid(String code) {
    return code.equals("E000000");
  }

  private static int tryParse(String value, int defaultValue) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  private static String encrypt(String text, String key) {
    try {
      Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, getKey(key));
      return Base64.encodeBase64String(cipher.doFinal(text.getBytes()));
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  private static String decrypt(String text, String key) {
    try {
      Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, getKey(key));
      return new String(cipher.doFinal(Base64.decodeBase64(text)));
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  private static SecretKey getKey(String key) {
    try {
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
      DESedeKeySpec keySpec = new DESedeKeySpec(key.getBytes());
      return keyFactory.generateSecret(keySpec);
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  private static String signData(String data) {
    try {
      Signature signature = Signature.getInstance("SHA1WithRSA");
      signature.initSign(getPrivateKey());
      signature.update(data.getBytes());
      return Base64.encodeBase64String(signature.sign());
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  private static void verifyData(String data, String signedValue) {
    try {
      Signature signature = Signature.getInstance("SHA1WithRSA");
      signature.initVerify(getPublicKey());
      signature.update(data.getBytes());
      if (!signature.verify(Base64.decodeBase64(signedValue))) {
        throw new RuntimeException("Response verification error!");
      }
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  private static PublicKey getPublicKey() {
    try {
      CertificateFactory factory = CertificateFactory.getInstance("X.509");
      FileInputStream fileStream = new FileInputStream(QhzxConfiguration.getSignCerPath());
      PublicKey result = factory.generateCertificate(fileStream).getPublicKey();
      fileStream.close();
      return result;
    } catch (GeneralSecurityException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static PrivateKey getPrivateKey() {
    try {
      KeyStore keyStore = KeyStore.getInstance("JKS");
      FileInputStream fileStream = new FileInputStream(QhzxConfiguration.getSignJksPath());
      keyStore.load(fileStream, QhzxConfiguration.getSignPassword().toCharArray());
      fileStream.close();
      return (PrivateKey) keyStore.getKey(
          QhzxConfiguration.getSignAlias(), QhzxConfiguration.getSignPassword().toCharArray());
    } catch (GeneralSecurityException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
