package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import jma.AppDerivativeVariablesBuilder;
import jma.JobHandler;
import jma.RetryRequiredException;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckMerchantAreaHandler extends JobHandler {

  private static final RowMapper<List<String>> EXTRACTOR = new RowMapper<List<String>>() {
    @Override
    public List<String> mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
      return Arrays.asList(
          resultSet.getString("ParentCode"),
          resultSet.getString("Code"),
          resultSet.getString("Name"));
    }
  };

  @Override
  public void execute(String appId) throws RetryRequiredException {
    List<String> districtInfo = queryDistrictInfo(appId);
    List<String> cityInfo = queryAreaInfo(districtInfo.get(0));
    List<String> provinceInfo = queryAreaInfo(cityInfo.get(0));

    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.MERCHANT_PROVINCE_CODE, provinceInfo.get(1))
        .add(AppDerivativeVariableNames.MERCHANT_PROVINCE, provinceInfo.get(2))
        .add(AppDerivativeVariableNames.MERCHANT_CITY_CODE, cityInfo.get(1))
        .add(AppDerivativeVariableNames.MERCHANT_CITY, cityInfo.get(2))
        .add(AppDerivativeVariableNames.MERCHANT_DISTRICT_CODE, districtInfo.get(1))
        .add(AppDerivativeVariableNames.MERCHANT_DISTRICT, districtInfo.get(2))
        .build());
  }

  private static List<String> queryDistrictInfo(String appId) {
    String sql =
        "SELECT CNAreaObjects.ParentCode, CNAreaObjects.Code, CNAreaObjects.Name " +
        "FROM InstallmentApplicationObjects, MerchantStoreObjects, CNAreaObjects " +
        "WHERE InstallmentApplicationObjects.MerchantStoreId = MerchantStoreObjects.Id " +
        "    AND MerchantStoreObjects.CNAreaId = CNAreaObjects.Id " +
        "    AND InstallmentApplicationObjects.Id = :AppId";

    return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), EXTRACTOR);
  }

  public static List<String> queryAreaInfo(String code) {
    return DatabaseApi.querySingleResult(
        "SELECT ParentCode, Code, Name FROM CNAreaObjects WHERE Code = :Code",
        CollectionUtils.mapOf("Code", code), EXTRACTOR);
  }
}
