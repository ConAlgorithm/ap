package fraudengine.rules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class E001 extends FraudRule {

	public E001(String id, String name, int score, String description) {
		super(id, name, score, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CatfishEnum<Integer> run(String appID) {
		
		List<?> userInfo = this.getUserInfo(appID);
		Set<String> idNumberSet = new HashSet<>();
		idNumberSet.add((String) userInfo.get(1));
		
		String sql =
				"SELECT mu.IdNumber FROM " +
				"ContactObjects c, " +
				"MerchantUserObjects mu, " +
				"MerchantUserContactObjects muc " +
				"WHERE " +
				"mu.Id = muc.MerchantUserId " +
				"and muc.ContactId = c.Id " +
				"and c.ContactType = 1 " +
				"and c.Content = :QQContent";
		
		List<String> idNumbers = DatabaseApi.queryMultipleResults(
		        sql, CollectionUtils.mapOf("QQContent", userInfo.get(0)), DatabaseExtractors.STRING_EXTRACTOR);
		
		if (idNumbers == null || idNumbers.isEmpty()) {
			return FraudRuleResultStatus.Pass;
		}
		
		for (String idNumber : idNumbers) {
			if (!idNumber.equals("")) {
				idNumberSet.add(idNumber);
			}
		}

		return parse(idNumberSet.size() > 1);
	}
	
	private List<?> getUserInfo(String appId) {
		String sql = 
				"SELECT c.Content, eu.IdNumber FROM " +
				"ContactObjects c, " +
				"UserObjects u, " +
				"EndUserExtensionObjects eu, " +
				"PersonalInfoObjects p, " +
				"InstallmentApplicationObjects app " +
				"WHERE " +
				"app.UserId = u.Id " +
				"and u.Id = eu.Id " +
				"and app.Id = p.AppId " +
				"and p.QQContactId = c.Id " + 
				"and c.ContactType = 1 " +
				"and app.Id = :AppId";
		RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
		  @Override
		  public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
		    return Arrays.asList(
			    result.getString("Content"),
			    result.getString("IdNumber"));
		    }
		};
		return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
	}

}
