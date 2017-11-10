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

public class E003 extends FraudRule {

	public E003(String id, String name, int score, String description) {
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
				"PaymentObjects p, " +
				"MerchantUserObjects mu, " +
				"MerchantUserPaymentObjects mup " +
				"WHERE " +
				"mu.Id = mup.MerchantUserId " +
				"and mup.PaymentId = p.Id " +
				"and p.BankAccount = :BankAccount";
		
		List<String> idNumbers = DatabaseApi.queryMultipleResults(
	        sql, CollectionUtils.mapOf("BankAccount", userInfo.get(0)), DatabaseExtractors.STRING_EXTRACTOR);
		
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
				"SELECT p.BankAccount, eu.IdNumber FROM " +
				"PaymentObjects p, PaymentApplicationObjects pa, InstallmentApplicationObjects app, EndUserExtensionObjects eu " +
				"where " +
				"p.Id = pa.PaymentId " +
				"and eu.Id = app.UserId " +
				"and app.Id = pa.ApplicationId " + 
				"and pa.ApplicationId = :AppId";
		RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
		  @Override
		  public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
		    return Arrays.asList(
		    	result.getString("BankAccount"),
		        result.getString("IdNumber"));
		  }
		};
		return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
	}

}
