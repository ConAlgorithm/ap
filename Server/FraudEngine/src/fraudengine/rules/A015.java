package fraudengine.rules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.util.CommonUtil;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class A015 extends FraudRule {

	public A015(String id, String name, int score, String description) {
		super(id, name, score, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CatfishEnum<Integer> run(String appID) {
		
		List<?> userInfo = this.getUserInfo(appID);

		String sql = 
				"SELECT cp.Name FROM " +
				"ContactPersonObjects cp, " + 
				"ContactObjects co " +
				"WHERE " +
				"cp.MobileContactId = co.Id " +
				"and co.Content = :MobileContent";
		
		List<String> idNames = DatabaseApi.queryMultipleResults(
	        sql, CollectionUtils.mapOf("MobileContent", userInfo.get(0)), DatabaseExtractors.STRING_EXTRACTOR);
		
		if (idNames == null || idNames.isEmpty()) {
			return FraudRuleResultStatus.Pass;
		}
		
		String userIdName = (String) userInfo.get(1);
		
		for (String idName : idNames) {
			if (!CommonUtil.isSimilarUserName(userIdName, idName)) {
				return FraudRuleResultStatus.Fail;
			}
		}
		
		return FraudRuleResultStatus.Pass;
	}
	
	private List<?> getUserInfo(String appId) {
		String sql = 
				"SELECT co.Content, eu.IdName FROM " +
				"ContactObjects co, " +
				"UserObjects u, " +
				"EndUserExtensionObjects eu, " +
				"InstallmentApplicationObjects app " +
				"where " +
				"app.UserId = u.Id " +
				"and u.Id = eu.Id " +
				"and u.MobileContactId = co.Id " +
				"and app.Id = :AppId";
		RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
		  @Override
		  public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
		    return Arrays.asList(
			    result.getString("Content"),
			    result.getString("IdName"));
		    }
		};
		return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
	}

}
