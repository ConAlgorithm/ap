package fraudengine.rules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class E002 extends FraudRule {

	public E002(String id, String name, int score, String description) {
		super(id, name, score, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CatfishEnum<Integer> run(String appID) {
		
		List<String> contactMobs = getContactMobils(appID);
	    Set<String> mobSets = new HashSet<>();
	    for (String mob : contactMobs) {
	      mobSets.add(mob);
	    }
	    
	    String sql = 
	    		"SELECT distinct mu.IdNumber FROM " +
	    		"ContactObjects c, " +
	    		"MerchantUserObjects mu, " +
	    		"MerchantUserContactObjects muc " +
	    		"WHERE " +
	    		"mu.Id = muc.MerchantUserId " +
	    		"and muc.ContactId = c.Id " +
	    		"and c.Content IN (:MobileContents)";
	    List<String> idNumbers = DatabaseApi.queryMultipleResults(
	            sql, CollectionUtils.mapOf("MobileContents", mobSets), DatabaseExtractors.STRING_EXTRACTOR); 		
	    
	    if (idNumbers == null || idNumbers.isEmpty()) {
	    	return FraudRuleResultStatus.Pass;
	    }
	    
	    if (idNumbers.size() == 1 && idNumbers.get(0).equals("")) {
	    	return FraudRuleResultStatus.Pass;
	    }
	    
		return parse(idNumbers.size() > 0);
	}
	
	private List<String> getContactMobils(String appId) {
	    String sql =
	        "SELECT co.Content " +
	        "FROM ContactPersonObjects AS cp, ContactObjects AS co " +
	        "WHERE cp.AppId = :AppId " +
	        "    AND cp.MobileContactId = co.Id";

	    return DatabaseApi.queryMultipleResults(
	        sql, CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.STRING_EXTRACTOR);
	  }
}
