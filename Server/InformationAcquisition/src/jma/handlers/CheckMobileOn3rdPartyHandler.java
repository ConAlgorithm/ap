package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jma.AppDerivativeVariablesBuilder;
import jma.JobHandler;
import jma.RetryRequiredException;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.database.DatabaseApi;

public abstract class CheckMobileOn3rdPartyHandler extends JobHandler {

  public static final int USER_MOBILE = 10;
  public static final List<String> MUNICIPALITIES = Arrays.asList("上海", "北京", "天津", "重庆");

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String phoneNumber = getPhoneNumber(appId);
    if (phoneNumber == null) {
      return;
    }

    List<String> result = phoneNumber.startsWith("1")
        ? getMobileArea(phoneNumber)
        : getTelephoneArea(phoneNumber);

    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(getProvinceVariable(), result.get(0))
        .add(getCityVariable(), result.get(1))
        .add(getSupplierVariable(), result.get(2))
        .build());

    if (getMobileType() == USER_MOBILE) {
      String userMobileCity = result.get(1);
      AppDerivativeVariables provinceCity = AppDerivativeVariableManager.getVariables(
          appId,
          AppDerivativeVariableNames.MERCHANT_PROVINCE,
          AppDerivativeVariableNames.MERCHANT_CITY);
      String merchantProvince = provinceCity.getAsString(
          AppDerivativeVariableNames.MERCHANT_PROVINCE);
      String merchantCity = provinceCity.getAsString(
          AppDerivativeVariableNames.MERCHANT_CITY);

      boolean isMobileLocal = merchantCity.contains(userMobileCity);
      if (MUNICIPALITIES.contains(userMobileCity)) {
        isMobileLocal = merchantProvince.contains(userMobileCity);
      }

      AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
          appId,
          AppDerivativeVariableNames.IS_USER_MOBILE_LOCAL,
          isMobileLocal));
    }
  }

  protected int getMobileType() {
    return 0;
  }

  protected abstract String getPhoneNumber(String appId);

  protected abstract String getProvinceVariable();

  protected abstract String getCityVariable();

  protected abstract String getSupplierVariable();

  private List<String> getMobileArea(String number) {
    String sql =
        "SELECT Province, City, ServiceProvider " +
        "FROM MobilePhoneAreaObjects " +
        "WHERE Prefix = :Prefix";

    RowMapper<List<String>> extractor = new RowMapper<List<String>>() {
      @Override
      public List<String> mapRow(ResultSet resultSet, int index) throws SQLException {
        return Arrays.asList(
            resultSet.getString("Province"),
            resultSet.getString("City"),
            resultSet.getString("ServiceProvider"));
      }
    };

    return DatabaseApi.querySingleResultOrDefault(
        sql,
        CollectionUtils.mapOf("Prefix", number.substring(0, 7)),
        extractor,
        Arrays.asList("", "", ""));
  }

  private List<String> getTelephoneArea(String number) {
    String sql =
        "SELECT TOP 1 Province, City " +
        "FROM TelephoneAreaObjects " +
        "WHERE Code = :FirstThreeDigits " +
        "    OR Code = :FirstFourDigits " +
        "    OR Code = :FirstFiveDigits";

    Map<String, String> params = CollectionUtils.mapOf(
        "FirstThreeDigits", number.substring(0, 3),
        "FirstFourDigits", number.substring(0, 4),
        "FirstFiveDigits", number.substring(0, 5));

    RowMapper<List<String>> extractor = new RowMapper<List<String>>() {
      @Override
      public List<String> mapRow(ResultSet resultSet, int index) throws SQLException {
        return Arrays.asList(
            resultSet.getString("Province"), resultSet.getString("City"), "");
      }
    };

    List<String> areaInfo = DatabaseApi.querySingleResultOrDefault(
        sql, params, extractor, Arrays.asList("", "", ""));
    if (MUNICIPALITIES.contains(areaInfo.get(0))) {
      areaInfo.set(1, areaInfo.get(0));
    }

    return areaInfo;
  }
}
