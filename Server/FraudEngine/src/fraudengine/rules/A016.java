package fraudengine.rules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.execution.ExecutionType;
import catfish.base.collections.MapBuilder;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class A016 extends FraudRule {

	public A016(String id, String name, int score, String description) {
		super(id, name, score, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CatfishEnum<Integer> run(String appID) {
		
		List<?> userInfo = this.getUserInfo(appID);
		
		List<String> userContactMobs = this.getUserAllMobileContentByIdNumber((String) userInfo.get(0));
		if (userContactMobs == null || userContactMobs.isEmpty()) {
			return FraudRuleResultStatus.Pass;
		}

		if (userContactMobs.size() > 1) {
			return FraudRuleResultStatus.Fail;
		}
		
		Set<String> parentNameSet = new HashSet<>();
		parentNameSet.add((String) userInfo.get(1));
		Set<String> parentMobSet = new HashSet<>();
		parentMobSet.add((String) userInfo.get(3));
		
		List<List<?>> parentsInfo = this.getParentsInfo((String) userInfo.get(0), (String) userInfo.get(2));
		
		if (parentsInfo == null || parentsInfo.isEmpty() || parentsInfo.get(0) == null || parentsInfo.get(0).isEmpty()) {
			return FraudRuleResultStatus.Pass;
		}
		
		for (List<?> parent : parentsInfo) {
			if (!parent.get(0).toString().equals("")) {
				parentNameSet.add((String) parent.get(0));
			}
			
			if (!parent.get(1).toString().equals("")) {
				parentMobSet.add((String) parent.get(1));
			}
		}
		
		Integer type = InstallmentApplicationDao.getApplicationTypeById(appID);
		
		if(type == ExecutionType.App.getValue())
		{
			return parse(parentNameSet.size() != parentMobSet.size());
		}
		
		if (parentNameSet.size() > 1) {
			return FraudRuleResultStatus.Fail;
		} else if (parentMobSet.size() > 1) {
			return FraudRuleResultStatus.Fail;
		}
		
		return FraudRuleResultStatus.Pass;
	}
	
	private List<?> getUserInfo(String appId) {
		String sql = 
				"SELECT eu.IdNumber, cp.Name, cp.Relationship, co.Content FROM " +
				"EndUserExtensionObjects eu, " +
				"InstallmentApplicationObjects app, " +
				"ContactPersonObjects cp, " +
				"ContactObjects co " +
				"WHERE eu.Id = app.UserId " +
				"AND cp.AppId = app.Id " +
				"AND (cp.Relationship = 6 or cp.Relationship = 7 or cp.Relationship = 0) " +
				"AND cp.MobileContactId = co.Id " +
				"AND app.Id = :AppId";
		RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
		  @Override
		  public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
		    return Arrays.asList(
			    result.getString("IdNumber"),
			    result.getString("Name"),
			    result.getString("Relationship"),
			    result.getString("Content"));
		    }
		};
		return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
	}
	
	private List<String> getUserAllMobileContentByIdNumber(String idNumber) {
		String sql =
				"SELECT distinct co.Content FROM " +
				"ContactObjects co, " +
				"UserObjects u, " +
				"EndUserExtensionObjects eu, " +
				"InstallmentApplicationObjects app " +
				"where eu.Id = app.UserId " +
				"and app.Status <= 29 " +
				"and u.Id = eu.Id " +
				"and u.MobileContactId = co.Id " +
				"and eu.IdNumber = :IdNumber";
		return DatabaseApi.queryMultipleResults(
		        sql, CollectionUtils.mapOf("IdNumber", idNumber), DatabaseExtractors.STRING_EXTRACTOR);
	}

	private List<List<?>> getParentsInfo(String idNumber, String relationship) {
		String sql = 
				"SELECT cp.Name, co.Content FROM " +
				"EndUserExtensionObjects eu, " +
				"InstallmentApplicationObjects app, " +
				"ContactPersonObjects cp, " +
				"ContactObjects co " +
				"where cp.AppId = app.Id " +
				"and cp.Relationship = :Relationship " +
				"and app.Status <= 29 " +
				"and cp.MobileContactId = co.Id " +
				"and eu.Id = app.UserId " +
				"and eu.IdNumber = :IdNumber";
		RowMapper<List<?>> extractor = new RowMapper<List<?>>() {
		  @Override
		  public List<?> mapRow(ResultSet result, int rowIndex) throws SQLException {
		    return Arrays.asList(
		    	result.getString("Name"),
		        result.getString("Content"));
		  }
		};
		
		Map<String, Object> params = new MapBuilder<String, Object>()
		        .add("IdNumber", idNumber)
		        .add("Relationship", relationship)
		        .build();
		return DatabaseApi.queryMultipleResults(
		        sql, params, extractor);
	}
}
