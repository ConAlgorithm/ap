package fraudengine.rules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.database.DatabaseApi;


public class E004 extends FraudRule {

	public E004(String id, String name, int score, String description) {
		super(id, name, score, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CatfishEnum<Integer> run(String appID) {
		
		List<?> userInfo = this.getUserInfo(appID);
		if (userInfo == null || userInfo.isEmpty()) {
			return FraudRuleResultStatus.Pass;
		}
		
		String userQQcontent = (String) userInfo.get(0);
		String userBankAccount = (String) userInfo.get(1);
		
		String sql1 =
				"SELECT p.BankAccount FROM " +
				"PaymentObjects p, " +
				"MerchantUserObjects mu, " +
				"MerchantUserPaymentObjects mup " +
				"WHERE " +
				"mu.Id = mup.MerchantUserId " +
				"and mup.PaymentId = p.Id " +
				"and mu.IdNumber = :IdNumber";

		String sellerBankAccount = DatabaseApi.querySingleString(sql1, CollectionUtils.mapOf("IdNumber", userInfo.get(2)));

		String sql2 = 
				"SELECT c.Content FROM " +
				"ContactObjects c, " +
				"MerchantUserObjects mu, " +
				"MerchantUserContactObjects muc " +
				"WHERE " +
				"mu.Id = muc.MerchantUserId " +
				"and muc.ContactId = c.Id " +
				"and c.ContactType = 1 " +
				"and mu.IdNumber = :IdNumber";
		
		String sellerQQContent = DatabaseApi.querySingleString(sql2, CollectionUtils.mapOf("IdNumber", userInfo.get(2)));
		
		if (sellerQQContent.equals("") || sellerBankAccount.equals("")) {
			return FraudRuleResultStatus.Pass;
		}

		return parse(!userQQcontent.equals(sellerQQContent)
			    || !userBankAccount.equals(sellerBankAccount));
	}
	
	private List<?> getUserInfo(String appId) {
		String sql = 
				"SELECT c.Content, p.BankAccount, eu.IdNumber FROM " +
				"ContactObjects c, EndUserExtensionObjects eu, PersonalInfoObjects pinfo, PaymentObjects p, PaymentApplicationObjects pa, InstallmentApplicationObjects app, MerchantUserObjects mu " +
				"WHERE " +
				"app.UserId = eu.Id " +
				"and app.Id = pinfo.AppId " +
				"and pinfo.QQContactId = c.Id " + 
				"and c.ContactType = 1 " +
				"and p.Id = pa.PaymentId " +
				"and mu.IdNumber = eu.IdNumber " +
				"and app.Id = pa.ApplicationId " +
				"and app.Id = :AppId";
		RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
		  @Override
		  public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
		    return Arrays.asList(
			    result.getString("Content"),
			    result.getString("BankAccount"),
			    result.getString("IdNumber"));
		    }
		};
		return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
	}

}
