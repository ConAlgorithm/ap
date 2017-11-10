package thirdparty.config;

import catfish.base.StartupConfig;

public class TdConfiguration {
  private static String PREFIX = "catfish.td";

  private static String queryUrl;
  private static String detailUrl;
  private static String partnerCode;
  private static String secretKey;
  private static String eventId;
  private static String partnerKey;

  public static void initialize() {
    queryUrl = get("queryUrl");
    detailUrl = get("detailUrl");
    partnerCode = get("partnerCode");
    secretKey = get("secretKey");
    eventId = get("eventId");
    partnerKey = get("partnerKey");
  }

  public static String getQueryUrl() {
    return queryUrl;
  }

  public static String getDetailUrl() {
    return detailUrl;
  }

  public static String getPartnerCode() {
    return partnerCode;
  }

  public static String getSecretKey() {
    return secretKey;
  }

  public static String getEventId() {
    return eventId;
  }
  public static String getPartnerKey() {
    return partnerKey;
  }

  private static String get(String key) {
    return StartupConfig.get(String.format("%s.%s", PREFIX, key));
  }
}
