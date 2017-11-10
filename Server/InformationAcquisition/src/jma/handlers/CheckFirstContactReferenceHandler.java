package jma.handlers;

import java.util.Map;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseEnumValues;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneReferenceUtils;
import jma.dataservice.PhoneUtils;
import catfish.base.CollectionUtils;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckFirstContactReferenceHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    String currentUserIdNumber = DatabaseUtils.getIdCardNumberBy(appId);
    String phoneNumber = PhoneUtils.getFirstContactMobile(appId);
    int otherUserRef = PhoneReferenceUtils.getOtherUserMobileReference(currentUserIdNumber, phoneNumber);
    int otherCompanyRef = PhoneReferenceUtils.getOtherCompanyTelReference(
        currentUserIdNumber, phoneNumber);
    int otherFirstContactRef = PhoneReferenceUtils.getOtherFirstContactMobileReference(
        currentUserIdNumber, phoneNumber);
    int otherSecondContactRef = PhoneReferenceUtils.getOtherSecondContactMobileReference(
        currentUserIdNumber, phoneNumber);
    int otherThirdContactRef = PhoneReferenceUtils.getOtherThirdContactMobileReference(
        currentUserIdNumber, phoneNumber);
    int otherAdditionalContactRef = PhoneReferenceUtils.getOtherAdditionalContactMobileReference(
        currentUserIdNumber, phoneNumber);
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.FIRST_CONTACT_OTHER_USER_REFERENCE, otherUserRef)
        .add(AppDerivativeVariableNames.FIRST_CONTACT_OTHER_COMPANY_TEL_REFERENCE, otherCompanyRef)
        .add(AppDerivativeVariableNames.FIRST_CONTACT_OTHER_FIRST_CONTACT_REFERENCE, otherFirstContactRef)
        .add(AppDerivativeVariableNames.FIRST_CONTACT_OTHER_SECOND_CONTACT_REFERENCE, otherSecondContactRef)
        .add(AppDerivativeVariableNames.FIRST_CONTACT_OTHER_THIRD_CONTACT_REFERENCE, otherThirdContactRef)
        .add(AppDerivativeVariableNames.FIRST_CONTACT_OTHER_ADDITIONAL_CONTACT_REFERENCE, otherAdditionalContactRef)
        .build());

    int relationship = getFirstContactRelationship(appId);
    if (relationship == DatabaseEnumValues.ContactRelationShip.FATHER
        || relationship == DatabaseEnumValues.ContactRelationShip.MOTHER
        || relationship == DatabaseEnumValues.ContactRelationShip.PARENT) {
      Boolean variableValue = otherUserRef + otherCompanyRef + otherFirstContactRef + otherSecondContactRef
          + otherThirdContactRef + otherAdditionalContactRef > 0;
      AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
          appId,
          AppDerivativeVariableNames.PARENT_PHONE_DOES_ANY_OTHER_REFERENCE_EXIST,
          variableValue));
      
      //int score = variableValue ? 100 : 0;
      FraudRuleResultDao.saveFraudDetailResult(appId, "A004", "父母电话校验", variableValue ? FraudRuleResultStatus.Fail.getValue() : FraudRuleResultStatus.Pass.getValue());
    }
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
        "FirstContactPerson", DatabaseEnumValues.ContactPersonType.FIRST_CONTACT_PERSON);

    return DatabaseApi.querySingleInteger(sql, params);
  }
}
