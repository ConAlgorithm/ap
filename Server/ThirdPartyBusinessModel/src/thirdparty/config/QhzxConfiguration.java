package thirdparty.config;

import catfish.base.StartupConfig;

public class QhzxConfiguration {
  private static final String PREFIX = "catfish.qhzx";

  private static String queryUrl;
  private static String reportUrl;
  private static String orgCode;
  private static String chnlId;
  private static String authCode;
  private static String userName;
  private static String userPassword;
  private static String encryptionKey;
  private static String signCerPath;
  private static String signJksPath;
  private static String signAlias;
  private static String signPassword;
  private static String queryUrlNew;

  public static void initialize() {
    queryUrl = get("queryUrl");
    reportUrl = get("reportUrl");
    orgCode = get("orgCode");
    chnlId = get("chnlId");
    authCode = get("authCode");
    userName = get("userName");
    userPassword = get("userPassword");
    encryptionKey = get("encryption.key");
    signCerPath = get("signature.cerPath");
    signJksPath = get("signature.jksPath");
    signAlias = get("signature.alias");
    signPassword = get("signature.password");
    queryUrlNew = get("queryUrlNew");
  }

  public static String getQueryUrl() {
    return queryUrl;
  }

  public static String getReportUrl() {
    return reportUrl;
  }

  public static String getOrgCode() {
    return orgCode;
  }

  public static String getChnlId() {
    return chnlId;
  }

  public static String getAuthCode() {
    return authCode;
  }

  public static String getUserName() {
    return userName;
  }

  public static String getUserPassword() {
    return userPassword;
  }

  public static String getEncryptionKey() {
    return encryptionKey;
  }

  public static String getSignCerPath() {
    return signCerPath;
  }

  public static String getSignJksPath() {
    return signJksPath;
  }

  public static String getSignAlias() {
    return signAlias;
  }

  public static String getSignPassword() {
    return signPassword;
  }

  public static String getQueryUrlNew() {
	return queryUrlNew;
  }

private static String get(String key) {
    return StartupConfig.get(String.format("%s.%s", PREFIX, key));
  }
}
