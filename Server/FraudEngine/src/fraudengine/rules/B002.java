package fraudengine.rules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.collections.MapBuilder;
import catfish.base.database.DatabaseApi;

public class B002 extends FraudRule{

	public B002(String id, String name, int score, String description) {
		super(id, name, score, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CatfishEnum<Integer> run(String appID) {

		//先判断自己满足与否，如果不满足则未中规则
		String sql = "SELECT count(*) FROM InstallmentApplicationObjects "+
                     " where DATEDIFF(SS, PreApprovedOn, AllFileSubmitedDateTime) < 90 and Id = :AppId";
		int delta = DatabaseApi.querySingleInteger(sql, CollectionUtils.mapOf("AppId", appID));
		if(delta == 0)
			return FraudRuleResultStatus.Pass;

		sql = "SELECT count(distinct IdNumber) FROM " +
				"InstallmentApplicationObjects inst "+
				", MerchantStoreObjects merc "+
				", EndUserExtensionObjects endu "+
				", JobInfoObjects jobi "+
				", ContactObjects cont "+
				"where "+
				"inst.MerchantStoreId = merc.Id "+
				"and inst.Id = jobi.ApplicationId "+
				"and jobi.CompanyTelId = cont.Id "+
				"and inst.UserId = endu.Id "+
				"and DATEDIFF(DD, inst.InstallmentStartedOn, GETDATE()) <= 4 "+
				"and DATEDIFF(SS, inst.PreApprovedOn, inst.AllFileSubmitedDateTime) < 90 "+
				"and inst.MerchantStoreId = :MerchantStoreId " +
				"and cont.Content = :CompanyTelNumber "+
				"and inst.Principal = :Principal";
		List<?> appInfo = this.getApplicationInfo(appID);
		Map<String, Object> params = new MapBuilder<String, Object>()
			        .add("MerchantStoreId", appInfo.get(0))
			        .add("CompanyTelNumber", appInfo.get(1))
			        .add("Principal", appInfo.get(2))
			        .build();
		int result = DatabaseApi.querySingleInteger(sql, params);
		return parse(result >= 2);
	}

	private List<?> getApplicationInfo(String appId) {
	    String sql =
	    		"select inst.MerchantStoreId, cont.Content CompanyTelNumber, inst.Principal from " +
	    		"InstallmentApplicationObjects inst, MerchantStoreObjects merc, JobInfoObjects jobi, ContactObjects cont " +
	    		"where " +
	    		"inst.MerchantStoreId = merc.Id "+
	    		"and inst.Id = jobi.ApplicationId "+
	    		"and jobi.CompanyTelId = cont.Id "+
	    		"and inst.Id = :AppId";
	    RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
	      @Override
	      public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
	        return Arrays.asList(
	            result.getString("MerchantStoreId"),
	            result.getString("CompanyTelNumber"),
	            result.getBigDecimal("Principal"));
	      }
	    };
	    return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
	}
}
