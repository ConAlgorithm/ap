package catfish.notification;

import catfish.base.StartupConfig;
import catfish.notification.common.ImageEnum;
import catfish.notification.sender.wechat.ImageManager;
import catfish.notification.sender.wechat.TokenManager;

public class Configuration {

  public static final String UTF_8 = "UTF-8";

  private static int maxRetries;
  private static String onsTopic;
  private static String onsProducerId;
  private static String onsConsumerId;
  private static String onsAccessKey;
  private static String onsSecretKey;
  private static String httpHost;
  private static int httpPort;
  private static String productPrefix;
  private static TokenManager weChatCustomerTokenManager;
  private static TokenManager weChatMerchantTokenManager;
  private static TokenManager weChatDealerTokenManager;
  private static TokenManager weChatBDTokenManager;
  private static ImageManager customerGuidedIouImageManager;
  private static ImageManager merchantGuidedIouImageManager;
  private static ImageManager customerGuidedBuckleImageManager;
  private static String guidedIouUrlFormat;
  private static String weiXinComsumerUrlPrefix;
  private static String mengWangIp;
  private static String mengWangPort;
  private static String mengWangUsername;
  private static String mengWangPassword;
  private static String huYiAccount;
  private static String huYiPassword;
  private static String yunPianApiKey;
  private static String jianZhouAccount;
  private static String jianZhouPassword;
  private static String qrCodeurlformat;
  private static String deductionTime;
  private static String repaymentUrlPrefix;
  private static String settlementUrlPrefix;
  private static String userUrlPrefix;
  private static String leanCloudUrl;
  private static String leanCloudIOSCert;
  private static String grasscarpRestfulUrl;
  private static String grasscarpRestfulPort;
  private static String saturnProductUrl;
  private static String saturnProductPort;

  private static String pdlBusinessRestfulhost;
  private static String pdlBusinessRestfulport;

  private static String commonserviceSmsUrl;
  private static boolean commonServiceSwitchOn;
  
  private static String riskPersistenceHost;
  private static String riskPersistencePort;
  
  // Hanter 2081
  private static String superAppSeviceUrl;
  private static String userbaseServiceUrl;
  

  public static void initialize() {
    leanCloudIOSCert = StartupConfig.get("appserver.leancloud.ios.cert");
    leanCloudUrl = StartupConfig.get("appserver.leancloud.pushURL");
    maxRetries = StartupConfig.getAsInt("catfish.notification.maxretries");
    productPrefix = StartupConfig.get("catfish.queue.product");
    if (productPrefix == null) {
      productPrefix = "App";
    }

    String onsPrefix = StartupConfig.get("catfish.ons.prefix");
    onsTopic = String.format("%sNotification", onsPrefix);
    onsProducerId = String.format("PID-%sNotification", onsPrefix);
    onsConsumerId = String.format("CID-%sNotification", onsPrefix);
    onsAccessKey = StartupConfig.get("catfish.ons.accesskey");
    onsSecretKey = StartupConfig.get("catfish.ons.secretkey");

    httpHost = StartupConfig.get("catfish.http.host");
    httpPort = StartupConfig.getAsInt("catfish.http.port");

    weChatCustomerTokenManager = new TokenManager("CustomerTokenManager",
        StartupConfig.get("catfish.wechat.customer.id"), StartupConfig.get("catfish.wechat.customer.secret"));
    weChatMerchantTokenManager = new TokenManager("MerchantTokenManager",
        StartupConfig.get("catfish.wechat.merchant.id"), StartupConfig.get("catfish.wechat.merchant.secret"));
    weChatDealerTokenManager = new TokenManager("StandaloneDTokenManager",
        StartupConfig.get("catfish.wechat.dealer.id"), StartupConfig.get("catfish.wechat.dealer.secret"));
    weChatBDTokenManager = new TokenManager("BDTokenManager", StartupConfig.get("catfish.wechat.BD.id"),
        StartupConfig.get("catfish.wechat.BD.secret"));

    customerGuidedIouImageManager = new ImageManager("CustomerGuidedIOUImageManager",
        StartupConfig.get("catfish.guidedioupath"), weChatCustomerTokenManager);
    merchantGuidedIouImageManager = new ImageManager("MerchantGuidedIOUImageManager",
        StartupConfig.get("catfish.guidedmerchantioupath"), weChatMerchantTokenManager);
    customerGuidedBuckleImageManager = new ImageManager("CustomerGuidedBuckleImageManager",
        StartupConfig.get("catfish.guidebucklepath"), weChatCustomerTokenManager);
    guidedIouUrlFormat = StartupConfig.get("catfish.guidediouurlformat");
    qrCodeurlformat = StartupConfig.get("catfish.qrCodeurlformat");
    weiXinComsumerUrlPrefix = StartupConfig.get("catfish.weiwincomsumerurlprefix");
    deductionTime = StartupConfig.get("catfish.deductiontime");
    repaymentUrlPrefix = StartupConfig.get("catfish.repaymenturlprefix");
    settlementUrlPrefix = StartupConfig.get("catfish.settlementurlprefix");
    userUrlPrefix = StartupConfig.get("catfish.userurlprefix");

    mengWangIp = StartupConfig.get("catfish.mengwang.ip");
    mengWangPort = StartupConfig.get("catfish.mengwang.port");
    mengWangUsername = StartupConfig.get("catfish.mengwang.username");
    mengWangPassword = StartupConfig.get("catfish.mengwang.password");
    huYiAccount = StartupConfig.get("catfish.huyi.account");
    huYiPassword = StartupConfig.get("catfish.huyi.password");
    yunPianApiKey = StartupConfig.get("catfish.yunpian.apikey");
    grasscarpRestfulUrl = StartupConfig.get("grasscarp.restful.host");
    grasscarpRestfulPort = StartupConfig.get("grasscarp.restful.port");
    saturnProductUrl = StartupConfig.get("saturn.product.host");
    saturnProductPort = StartupConfig.get("saturn.product.port");

    setJianZhouAccount(StartupConfig.get("catfish.jianzhou.account"));
    setJianZhouPassword(StartupConfig.get("catfish.jianzhou.password"));

    pdlBusinessRestfulhost = StartupConfig.get("catfish.pdlBusiness.restful.host");
    pdlBusinessRestfulport = StartupConfig.get("catfish.pdlBusiness.restful.port");

    commonserviceSmsUrl = StartupConfig.get("commonservice.sms.url");
    commonServiceSwitchOn = StartupConfig.getAsBoolean("commonservice.sms.switchOn");
    
    riskPersistenceHost = StartupConfig.get("catfish.riskPersistence.host");
    riskPersistencePort = StartupConfig.get("catfish.riskPersistence.port");
    
    superAppSeviceUrl = StartupConfig.get("omniprimeinc.superapp.wechatbase");
    userbaseServiceUrl = StartupConfig.get("com.omniprimeinc.ukelele.user-base");
    
  }

  public static int getMaxRetries() {
    return maxRetries;
  }

  public static String getProductPrefix() {
    return productPrefix;
  }

  public static String getOnsTopic() {
    return onsTopic;
  }

  public static String getOnsProducerId() {
    return onsProducerId;
  }

  public static String getOnsConsumerId() {
    return onsConsumerId;
  }

  public static String getOnsAccessKey() {
    return onsAccessKey;
  }

  public static String getOnsSecretKey() {
    return onsSecretKey;
  }

  public static String getHttpHost() {
    return httpHost;
  }

  public static int getHttpPort() {
    return httpPort;
  }

  public static TokenManager getWeChatCustomerTokeManager() {
    return weChatCustomerTokenManager;
  }

  public static TokenManager getWeChatMerchantTokenManager() {
    return weChatMerchantTokenManager;
  }

  public static TokenManager getWeChatDealerTokenManager() {
    return weChatDealerTokenManager;
  }

  public static TokenManager getWeChatBDTokenManager() {
    return weChatBDTokenManager;
  }

  public static ImageManager getImageManager(String name) {
    if (name == ImageEnum.CUSTOMER_IOU.toString()) {
      return customerGuidedIouImageManager;
    } else if (name == ImageEnum.CUSTOMER_BUCKLE.toString()) {
      return customerGuidedBuckleImageManager;
    } else if (name == ImageEnum.S1_IOU.toString()) {
      return merchantGuidedIouImageManager;
    } else {
      return null;
    }
  }

  // public static ImageManager getCustomerGuidedIouImageManager() {
  // return customerGuidedIouImageManager;
  // }
  //
  // public static ImageManager getMerchantGuidedIouImageManager() {
  // return merchantGuidedIouImageManager;
  // }
  //
  // public static ImageManager getCustomerGuidedBuckleImageManager(){
  // return customerGuidedBuckleImageManager;
  // }

  public static String getGuidedIouUrlFormat() {
    return guidedIouUrlFormat;
  }

  public static String getQrCodeUrlFormat() {
    return qrCodeurlformat;
  }

  public static String getWeiXinComsumerUrlPrefix() {
    return weiXinComsumerUrlPrefix;
  }

  public static String getDeductionTime() {
    return deductionTime;
  }

  public static String getRepaymentUrlPrefix() {
    return repaymentUrlPrefix;
  }

  public static String getSettlementUrlPrefix() {
    return settlementUrlPrefix;
  }

  public static String getUserUrlPrefix() {
    return userUrlPrefix;
  }

  public static String getMengWangIp() {
    return mengWangIp;
  }

  public static String getMengWangPort() {
    return mengWangPort;
  }

  public static String getMengWangUsername() {
    return mengWangUsername;
  }

  public static String getMengWangPassword() {
    return mengWangPassword;
  }

  public static String getHuYiAccount() {
    return huYiAccount;
  }

  public static String getHuYiPassword() {
    return huYiPassword;
  }

  public static String getYunPianApiKey() {
    return yunPianApiKey;
  }

  public static String getJianZhouAccount() {
    return jianZhouAccount;
  }

  public static void setJianZhouAccount(String jianZhouAccount) {
    Configuration.jianZhouAccount = jianZhouAccount;
  }

  public static String getJianZhouPassword() {
    return jianZhouPassword;
  }

  public static void setJianZhouPassword(String jianZhouPassword) {
    Configuration.jianZhouPassword = jianZhouPassword;
  }

  public static String getLeanCloudUrl() {
    return leanCloudUrl;
  }

  public static void setLeanCloudUrl(String leanCloudUrl) {
    Configuration.leanCloudUrl = leanCloudUrl;
  }

  public static String getLeanCloudIOSCert() {
    return leanCloudIOSCert;
  }

  public static void setLeanCloudIOSCert(String leanCloudIOSCert) {
    Configuration.leanCloudIOSCert = leanCloudIOSCert;
  }

  public static String getGrasscarpUrlPrefix() {
    return String.format("http://%s:%s", grasscarpRestfulUrl, grasscarpRestfulPort);
  }

  public static String getSaturnProductUrlPrefix() {
    return String.format("http://%s:%s", saturnProductUrl, saturnProductPort);
  }

  public static String getPDLBusinessUrlPrefix() {
    return String.format("http://%s:%s", pdlBusinessRestfulhost, pdlBusinessRestfulport);
  }

  public static String getCommonServiceSmsUrl() {
    return String.format("http://%s", commonserviceSmsUrl);
  }

  public static boolean getCommonServiceSwitchOn() {

    return commonServiceSwitchOn;
  }
  
  public static String getRiskPersistenceHost() {
	  return riskPersistenceHost;
  }
	
  public static String getRiskPersistencePort() {
	  return riskPersistencePort;
  }
  
  public static String getRiskPersistenceUrl() {
	  return String.format("http://%s:%s", riskPersistenceHost, riskPersistencePort);
  }
  
  public static String getSuperAppServiceUrl() {
	  return superAppSeviceUrl;
  }
  
  public static String getUserbaseServiceUrl() {
	  return userbaseServiceUrl;
  }
}
