package catfish.msglauncher;

import catfish.base.StartupConfig;

public class Configuration {

  public static final String UTF_8 = "UTF-8";

  private static int maxRetries;
  private static String onsTopic;
  private static String onsProducerId;
  private static String onsConsumerId;
  private static String onsAccessKey;
  private static String onsSecretKey;
  private static String productPrefix;
  private static String mengWangIp;
  private static String mengWangPort;
  private static String mengWangUsername;
  private static String mengWangPassword;
  private static String huYiAccount;
  private static String huYiPassword;
  private static String yunPianApiKey;
  private static String jianZhouAccount;
  private static String jianZhouPassword;
  private static String alibaichuanUrl;
  private static String alibaichuanAppKey;
  private static String alibaichuanSecret;
  private static String alibaichuanSignId;
  private static String daHanSanTongAccount;
  private static String daHanSanTongPassword;
  private static String daHanSanTongShortMsgAccount;
  private static String daHanSanTongShortMsgPassword;
  private static String daHanSanTongShortMsgSign;
  //大汉三通注册登录发送
  private static String daHanSanTongShortMessageAccount;
  private static String daHanSanTongShortMessagePassword;
  private static String daHanSanTongShortMessageSign;
  
  
  public static void initialize() {
    maxRetries = StartupConfig.getAsInt("catfish.notification.maxretries");
    productPrefix = StartupConfig.get("catfish.queue.product");
    if(productPrefix == null){
        productPrefix = "App";
    }

    String onsPrefix = StartupConfig.get("catfish.ons.prefix");
    onsTopic = String.format("%sNotification", onsPrefix);
    onsProducerId = String.format("PID-%sNotification", onsPrefix);
    onsConsumerId = String.format("CID-%sNotification", onsPrefix);
    onsAccessKey = StartupConfig.get("catfish.ons.accesskey");
    onsSecretKey = StartupConfig.get("catfish.ons.secretkey");

    mengWangIp = StartupConfig.get("catfish.mengwang.ip");
    mengWangPort = StartupConfig.get("catfish.mengwang.port");
    mengWangUsername = StartupConfig.get("catfish.mengwang.username");
    mengWangPassword = StartupConfig.get("catfish.mengwang.password");
    huYiAccount = StartupConfig.get("catfish.huyi.account");
    huYiPassword = StartupConfig.get("catfish.huyi.password");
    yunPianApiKey = StartupConfig.get("catfish.yunpian.apikey");

    setJianZhouAccount(StartupConfig.get("catfish.jianzhou.account"));
    setJianZhouPassword(StartupConfig.get("catfish.jianzhou.password"));
    
    alibaichuanUrl = StartupConfig.get("catfish.alibaichuan.url");
    alibaichuanAppKey = StartupConfig.get("catfish.alibaichuan.appkey");
    alibaichuanSecret = StartupConfig.get("catfish.alibaichuan.secret");
    alibaichuanSignId = StartupConfig.get("catfish.alibaichuan.signid");
    
    daHanSanTongAccount = StartupConfig.get("catfish.dahansantong.account");
    daHanSanTongPassword = StartupConfig.get("catfish.dahansantong.password");
    daHanSanTongShortMsgAccount = StartupConfig.get("catfish.dahansantong.shortMsg.account");
    daHanSanTongShortMsgPassword = StartupConfig.get("catfish.dahansantong.shortMsg.password");
    daHanSanTongShortMsgSign = StartupConfig.get("catfish.dahansantong.shortMsg.sign");
    daHanSanTongShortMessageAccount = StartupConfig.get("catfish.dahansantong.shortMessage.account");
    daHanSanTongShortMessagePassword = StartupConfig.get("catfish.dahansantong.shortMessage.password");
    daHanSanTongShortMessageSign = StartupConfig.get("catfish.dahansantong.shortMessage.sign");
  }
  
  public static String getDaHanSanTongAccount() {
	return daHanSanTongAccount;
  }

  public static String getDaHanSanTongPassword() {
	return daHanSanTongPassword;
  }
  
  public static String getDaHanSanTongShortMsgAccount() {
	return daHanSanTongShortMsgAccount;
  }

  public static String getDaHanSanTongShortMsgPassword() {
	return daHanSanTongShortMsgPassword;
  }
  
  public static String getDaHanSanTongShortMsgSign() {
	return daHanSanTongShortMsgSign;
  }
  public static String getDaHanSanTongShortMessageAccount() {
	  return daHanSanTongShortMessageAccount;
  }
  
  public static String getDaHanSanTongShortMessagePassword() {
	  return daHanSanTongShortMessagePassword;
  }
  
  public static String getDaHanSanTongShortMessageSign() {
	  return daHanSanTongShortMessageSign;
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
  
  public static String getAlibaichuanUrl(){
	  return alibaichuanUrl;
  }
  
  public static String getAlibaichuanAppKey(){
	  return alibaichuanAppKey;
  }
  
  public static String getAlibaichuanSecret(){
	  return alibaichuanSecret;
  }
  
  public static String getAlibaichuanSignId(){
	  return alibaichuanSignId;
  }

}
