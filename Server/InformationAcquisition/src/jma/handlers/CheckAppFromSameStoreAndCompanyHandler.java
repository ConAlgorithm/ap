package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.collections.MapBuilder;
import catfish.base.database.DatabaseApi;

//曾经申请过相同购买手机型号，金额，门店，公司电话的身份证号数量
public class CheckAppFromSameStoreAndCompanyHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String sql =
        "SELECT COUNT (DISTINCT ee.IdNumber) " +
        "FROM InstallmentApplicationObjects AS ia, EndUserExtensionObjects AS ee, " +
        "    MerchantStoreObjects AS ms, JobInfoObjects AS ji, ContactObjects AS co " +
        "WHERE ee.Id = ia.UserId " +
        "    AND ms.Id = ia.MerchantStoreId " +
        "    AND ji.ApplicationId = ia.Id " +
        "    AND co.Id = ji.CompanyTelId " +
        "    AND ia.ProductName = :ProductName " +
        "    AND ia.Principal = :Principal " +
        "    AND ms.Id = :MerchantStoreId " +
        "    AND co.Content = :CompanyPhone " +
        "    AND DATEDIFF(DD, ia.InstallmentStartedOn, GETDATE()) <= 2";

    List<?> currentAppInfo = getApplicationInfo(appId);
    Map<String, Object> params = new MapBuilder<String, Object>()
        .add("ProductName", currentAppInfo.get(0))
        .add("Principal", currentAppInfo.get(1))
        .add("MerchantStoreId", currentAppInfo.get(2))
        .add("CompanyPhone", PhoneUtils.getCompanyTel(appId))
        .build();

    int variableValue = DatabaseApi.querySingleInteger(sql, params);
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
        appId,
        AppDerivativeVariableNames.NUMBER_OF_SPECIFIED_APP_FROM_SAME_STORE_AND_COMPANY,
        variableValue));
    
    //int score = variableValue >= 3 ? 60 : 0;
    FraudRuleResultDao.saveFraudDetailResult(appId, "B001", "同单位进件", variableValue >= 3 ? FraudRuleResultStatus.Fail.getValue() : FraudRuleResultStatus.Pass.getValue());
  }

  private List<?> getApplicationInfo(String appId) {
    String sql =
        "SELECT ia.ProductName, ia.Principal, ia.MerchantStoreId " +
        "FROM InstallmentApplicationObjects AS ia " +
        "WHERE ia.Id = :AppId";

    RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
      @Override
      public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
        return Arrays.asList(
            result.getString("ProductName"),
            result.getBigDecimal("Principal"),
            result.getString("MerchantStoreId"));
      }
    };

    return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
  }
}
