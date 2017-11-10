package jma.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jma.JobHandler;
import jma.RetryRequiredException;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.collections.MapBuilder;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseConfig;

import com.aliyun.common.utils.IOUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class CheckAddressAbstractHandler extends JobHandler {

  public static final int RESIDENCE = 10;
  public static final int WORK = 20;
  public static final int STORE = 30;
  public static final List<String> CITYS = Arrays.asList("上海", "苏州", "昆山", "太仓");
  public static final List<String> DISTRICTS_SHANGHAI = Arrays.asList(
      "普陀", "静安", "杨浦", "黄浦", "南汇", "嘉定", "徐汇", "奉贤", "闸北",
      "卢湾", "长宁", "闵行", "青浦", "金山", "宝山", "虹口", "浦东", "崇明");

  public static final String BAIDU_KEY = "1EHGBGPnY5CWnWkkpxo0qaFq";
  public static final String ADDR_TO_COOR_GEOCODING =
      "http://api.map.baidu.com/geocoder/v2/?" +
      "output=json&" +
      "ak=" + BAIDU_KEY + "&" +
      "address=%s&" +
      "city=%s";
  public static final String ADDR_TO_PRO_AND_CITY =
      "http://api.map.baidu.com/geocoder/v2/?" +
      "output=json&" +
      "ak=" + BAIDU_KEY + "&" +
      "pois=1&" +
      "location=%s,%s";

  protected abstract String getAddress(String appId);

  protected abstract String getCity(String appId);

  protected abstract int getAddressType();

  protected abstract String getRefId(String appId);

  protected void saveAreaInfo(String appId, String areaCode) { }

  @Override
  public void execute(String appId) throws RetryRequiredException {
    // 1. save latitude, longitude, ...
    String sql =
        "INSERT INTO AddressInfoObjects( " +
        "    AppId, RefId, Type, RawAddress, " +
        "    StatusBaidu, LngBaidu, LatBaidu, PreciseBaidu, ConfidenceBaidu, TypeBaidu, " +
        "    DateAdded, LastModified) " +
        "VAlUES(:AppId, :RefId, :Type, :RawAddress, " +
        "    :StatusBaidu, :LngBaidu, :LatBaidu, :PreciseBaidu, :ConfidenceBaidu, :TypeBaidu, " +
        "    :DateAdded, :LastModified)";
    String timeString = DateTimeUtils.getCurrentTimeString();
    String address = getAddress(appId);

    Map<String, Object> addressInfoMap = getAddressInfoFromBaidu(
        address, getCity(appId));
    Map<String, Object> params = new MapBuilder<String, Object>()
        .add("AppId", appId)
        .add("RefId", getRefId(appId))
        .add("Type", getAddressType())
        .add("RawAddress", address)
        .add("StatusBaidu", addressInfoMap.get("StatusBaidu"))
        .add("LngBaidu", addressInfoMap.get("LngBaidu"))
        .add("LatBaidu", addressInfoMap.get("LatBaidu"))
        .add("PreciseBaidu", addressInfoMap.get("PreciseBaidu"))
        .add("ConfidenceBaidu", addressInfoMap.get("ConfidenceBaidu"))
        .add("TypeBaidu", addressInfoMap.get("TypeBaidu"))
        .add("DateAdded", timeString)
        .add("LastModified", timeString)
        .build();
    new NamedParameterJdbcTemplate(DatabaseConfig.getDataSource()).update(sql, params);

    // 2. save province, city, district info
    if (addressInfoMap.get("LatBaidu") != null && getAddressType() != STORE) {
      String[] areaInfoArray = getProvinceAndCityAddressInfoFromBaidu(
          addressInfoMap.get("LatBaidu").toString(), addressInfoMap.get("LngBaidu").toString());
      if (areaInfoArray != null && areaInfoArray.length == 3) {
        saveAreaInfo(appId, getAreaCodeId(areaInfoArray[0], areaInfoArray[1], areaInfoArray[2]));
      }
    }
  }

  public String getStoreCity(String appId) {
    return AppDerivativeVariableManager.getAsString(
        appId, AppDerivativeVariableNames.MERCHANT_DISTRICT);
  }

  public String getApplicantCity(String appId) {
    String address = getAddress(appId);
    String cityString = address.length() > 3
        ? address.substring(0, 3)
        : address;    // only search first three words

    for (String city : CITYS) {
      if (cityString.contains(city)) {
        return city;
      }
    }

    for (String district : DISTRICTS_SHANGHAI) {
      if (cityString.contains(district)) {
        return district;
      }
    }

    String storeCity = AppDerivativeVariableManager.getAsString(
        appId, AppDerivativeVariableNames.MERCHANT_CITY);
    if (storeCity.contains("市辖区")) {
      storeCity = AppDerivativeVariableManager.getAsString(
          appId, AppDerivativeVariableNames.MERCHANT_PROVINCE);
    }
    return storeCity;
  }

  private Map<String, Object> getAddressInfoFromBaidu(String address, String city) {
    String jsonString = getJson(getAddr2CoorUrl(address, city));
    JsonObject jObjLevel1 = new JsonParser().parse(jsonString).getAsJsonObject();
    int status = jObjLevel1.get("status").getAsInt();
    MapBuilder<String, Object> baiduMapBuilder = new MapBuilder<String, Object>()
        .add("StatusBaidu", status);
    if ((status == 0) && (jObjLevel1.get("result") != null)) {
      JsonObject jObjLevel2 = jObjLevel1.get("result").getAsJsonObject();
      JsonObject jObjLevel3 = jObjLevel2.get("location").getAsJsonObject();
      baiduMapBuilder.add("LngBaidu", jObjLevel3.get("lng").getAsDouble())
                     .add("LatBaidu", jObjLevel3.get("lat").getAsDouble())
                     .add("PreciseBaidu", jObjLevel2.get("precise").getAsInt())
                     .add("ConfidenceBaidu", jObjLevel2.get("confidence").getAsInt())
                     .add("TypeBaidu", jObjLevel2.get("level").getAsString());
    }

    return baiduMapBuilder.build();
  }

  private URL getAddr2CoorUrl(String address, String city) {
    try {
      return new URL(String.format(ADDR_TO_COOR_GEOCODING,
          URLEncoder.encode(address, "UTF-8"),
          URLEncoder.encode(city, "UTF-8")));
    } catch (MalformedURLException | UnsupportedEncodingException e) {
      throw new RuntimeException();
    }
  }

  private URL getLacation2CoorUrl(String lat, String lng) {
    try {
      return new URL(String.format(ADDR_TO_PRO_AND_CITY,
          URLEncoder.encode(lat, "UTF-8"),
          URLEncoder.encode(lng, "UTF-8")));
    } catch (MalformedURLException | UnsupportedEncodingException e) {
      throw new RuntimeException();
    }
  }

  private String getJson(URL url) {
    try {
      InputStream is = url.openConnection().getInputStream();
      String jsonString = IOUtils.readStreamAsString(is, "UTF-8");
      is.close();
      return jsonString;
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  private String[] getProvinceAndCityAddressInfoFromBaidu(String lat, String lng) {
    String jsonString = getJson(getLacation2CoorUrl(lat, lng));
    JsonObject jObjLevel1 = new JsonParser().parse(jsonString).getAsJsonObject();
    int status = jObjLevel1.get("status").getAsInt();
    if ((status == 0) && (jObjLevel1.get("result") != null)) {
      JsonObject jObjLevel2 = jObjLevel1.get("result").getAsJsonObject();
      JsonObject jObjLevel3 = jObjLevel2.get("addressComponent").getAsJsonObject();
      return new String[] {
          jObjLevel3.get("province").getAsString(),
          jObjLevel3.get("city").getAsString(),
          jObjLevel3.get("district").getAsString()};
    }
    return null;
  }

  private String getAreaCodeId(String province, String city, String district) {
    if(!province.equals(city)) {
      String sql1 = "SELECT Id FROM CNAreaObjects " +
          "JOIN " +
          "(SELECT CNAreaObjects.Code FROM CNAreaObjects " +
          "JOIN " +
          "(SELECT Code FROM CNAreaObjects WHERE Name = :Province) " +
          "Province " +
          "ON Province.Code = CNAreaObjects.ParentCode " +
          "WHERE Name = :City) " +
          "City " +
          "ON City.Code = CNAreaObjects.ParentCode " +
          "WHERE CNAreaObjects.Name = :District";
      Map<String, String> params = CollectionUtils.mapOf(
          "Province", province,
          "City", city,
          "District", district);
      return DatabaseApi.querySingleString(sql1, params);
    }
    String sql2 = "SELECT Id FROM CNAreaObjects " +
                  "JOIN " +
                  "(SELECT Code FROM CNAreaObjects WHERE Name = :Province) " +
                  "Province " +
                  "ON SUBSTRING(CNAreaObjects.ParentCode, 1, 2) = SUBSTRING(Province.Code, 1, 2) " +
                  "WHERE CNAreaObjects.Name = :District";
    Map<String, String> params = CollectionUtils.mapOf(
        "Province", province,
        "District", district);
    return DatabaseApi.querySingleString(sql2, params);
  }
}
