package jma.handlers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jma.AppDerivativeVariablesBuilder;
import jma.JobV2Handler;
import jma.RetryRequiredException;
import jma.dataservice.AppDataService;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.business.common.SuccessiveEventType;
import catfish.base.business.dao.SuccessiveAppEventsStatisticsDao;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

class Pair<T>{
	private String id;
	private T value;

	public Pair(String idString, T t){
		this.id = idString;
		this.value = t;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
}

public class CalculateMerchantViewStatisticsHandler extends JobV2Handler {

  @Override
  public void execute(String appId) throws RetryRequiredException {

	  //金额
	  String merchantStoreId = AppDataService.getMerchantStoreIdBy(appId);
	  List<Pair<BigDecimal>> appPrincipals = AppDataService.getApprovedAppDataForToday(merchantStoreId, new RowMapper<Pair<BigDecimal>>() {
	    @Override
	    public Pair<BigDecimal> mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
	      return new Pair<BigDecimal>(resultSet.getString("Id"), resultSet.getBigDecimal("Principal"));
	    }
	  });
	  double principalLable = SuccessiveAppEventsStatisticsDao.getValue(SuccessiveEventType.Principal,
			  count(AppDataService.getInstallmentAppObject(appId).Principal, appPrincipals));

	  //产品
	  List<Pair<String>> appProductNames = AppDataService.getApprovedAppDataForToday(merchantStoreId, new RowMapper<Pair<String>>() {
	    @Override
	    public Pair<String> mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
	      return new Pair<String>(resultSet.getString("Id"), resultSet.getString("ProductName"));
	    }
	  });
	  double productNameLabel = SuccessiveAppEventsStatisticsDao.getValue(SuccessiveEventType.Product,
			  count(AppDataService.getInstallmentAppObject(appId).ProductName, appProductNames));

	  //贷款期数
	  List<Pair<BigDecimal>> appRepaymentMonths = AppDataService.getApprovedAppDataForToday(merchantStoreId, new RowMapper<Pair<BigDecimal>>() {
	    @Override
	    public Pair<BigDecimal> mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
	      return new Pair<BigDecimal>(resultSet.getString("Id"), resultSet.getBigDecimal("Repayments"));
	    }
	  });
	  double repaymentMonthsLabel = SuccessiveAppEventsStatisticsDao.getValue(SuccessiveEventType.RepaymentMonths,
			  count(new BigDecimal(AppDataService.getInstallmentAppObject(appId).Repayments), appRepaymentMonths));

	  //save to the derivative variable table
	  AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
	  .add(AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_PRINCIPALS, new BigDecimal(principalLable))
	  .add(AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_PRODUCTS, new BigDecimal(productNameLabel))
	  .add(AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_REPAYMONTHS, new BigDecimal(repaymentMonthsLabel))
	  .build());
  }

	private static <T> int count(T currentAppValue, List<Pair<T>> appPrincipals) {
		int count = 1;
		for (int i = appPrincipals.size() - 1; i >= 0; --i) {
			if (!appPrincipals.get(i).getValue().equals(currentAppValue))
				break;
			++count;
		}
		return count;
	}
}
