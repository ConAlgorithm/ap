package fraudengine.rules;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.ContactPersonType;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.common.RelationshipType;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.database.DatabaseApi;

public class A007 extends FraudRule {

  public A007(String id, String name, int score, String description) {
    super(id, name, score, description);
  }

  @Override
  public CatfishEnum<Integer> run(String appID) {
    int relationship = getFirstContactRelationship(appID);
    if (relationship != RelationshipType.Father.getValue()
        && relationship != RelationshipType.Mother.getValue()
        && relationship != RelationshipType.Parent.getValue())
    {
      return FraudRuleResultStatus.Pass;
    }

    AppDerivativeVariables variables = AppDerivativeVariableManager.getVariables(
        appID,
        AppDerivativeVariableNames.FIRST_CONTACT_MOBILE_CITY,
        AppDerivativeVariableNames.USER_MOBILE_CITY,
        AppDerivativeVariableNames.ID_CARD_CITY,
        AppDerivativeVariableNames.COMPANY_PHONE_CITY,
        AppDerivativeVariableNames.ID_CARD_PROVINCE);

    String parentPhoneArea = variables.getAsString(
        AppDerivativeVariableNames.FIRST_CONTACT_MOBILE_CITY);
    String userMobileArea = variables.getAsString(
        AppDerivativeVariableNames.USER_MOBILE_CITY);
    String userIdCardArea = variables.getAsString(
        AppDerivativeVariableNames.ID_CARD_CITY);
    String companyPhoneArea = variables.getAsString(
        AppDerivativeVariableNames.COMPANY_PHONE_CITY);
    if (userIdCardArea.isEmpty()) {
      userIdCardArea = variables.getAsString(
          AppDerivativeVariableNames.ID_CARD_PROVINCE);    // municipalities
    }

    return parse(!userMobileArea.contains(parentPhoneArea)
    && !userIdCardArea.contains(parentPhoneArea)
    && !companyPhoneArea.contains(parentPhoneArea));
  }

  private int getFirstContactRelationship(String appId) {
    String sql =
        "SELECT TOP 1 Relationship " +
        "FROM ContactPersonObjects " +
        "WHERE AppId = :AppId " +
        "    AND ContactPersonType = :FirstContactPerson " +
        "ORDER BY LastModified DESC";

    Map<String, ?> params = CollectionUtils.mapOf(
        "AppId", appId,
        "FirstContactPerson", ContactPersonType.FirstContactPerson.getValue());

    return DatabaseApi.querySingleInteger(sql, params);
  }
}
