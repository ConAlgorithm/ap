package fraudengine.rules;

import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.ContactPersonType;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.common.RelationshipType;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class A005 extends FraudRule {

	public A005(String id, String name, int score, String description) {
		super(id, name, score, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CatfishEnum<Integer> run(String appID) {

		List<Integer> colleaguePersonTypes = getColleaguePersonTypeInfo(appID);

		if (colleaguePersonTypes.size() <= 1)
			return FraudRuleResultStatus.Pass;

		AppDerivativeVariables variables = AppDerivativeVariableManager.getVariables(
		    appID,
		    AppDerivativeVariableNames.SECOND_CONTACT_MOBILE_CITY,
		    AppDerivativeVariableNames.THIRD_CONTACT_MOBILE_CITY,
		    AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_CITY_11,
		    AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_CITY_12,
		    AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_CITY_13);
		Set<String> citySet = new HashSet<>();
		for (Integer type : colleaguePersonTypes) {

			if (ContactPersonType.SecondContactPerson.getValue() == type) {
				citySet.add(variables.getAsString(AppDerivativeVariableNames.SECOND_CONTACT_MOBILE_CITY));
			}
			else if (ContactPersonType.ThirdContactPerson.getValue() == type) {
				citySet.add(variables.getAsString(AppDerivativeVariableNames.THIRD_CONTACT_MOBILE_CITY));
			}
			else if (ContactPersonType.AdditionalContactPerson_1.getValue() == type) {
				citySet.add(variables.getAsString(AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_CITY_11));
			}
			else if (ContactPersonType.AdditionalContactPerson_2.getValue() == type) {
				citySet.add(variables.getAsString(AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_CITY_12));
			}
			else if (ContactPersonType.AdditionalContactPerson_3.getValue() == type) {
				citySet.add(variables.getAsString(AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_CITY_13));
			}
		}
		return parse(citySet.size() == colleaguePersonTypes.size());
	}

	private List<Integer> getColleaguePersonTypeInfo(String appId) {
		String sql =
				"SELECT cp.ContactPersonType FROM " +
				 "ContactPersonObjects cp " +
				 "where " +
				 "cp.Relationship = :Colleague " +
				 "and cp.AppId = :AppId";
		Map<String, ?> params = CollectionUtils.mapOf(
				"AppId", appId,
				"Colleague", RelationshipType.Colleague.getValue());

		return DatabaseApi.queryMultipleResults(
			sql, params, DatabaseExtractors.INTEGER_EXTRACTOR);
	}
}
