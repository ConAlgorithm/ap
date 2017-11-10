package jma.handlers;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneReferenceUtils;
import jma.dataservice.PhoneUtils;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckCompanyTelReferenceHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    String currentUserIdNumber = DatabaseUtils.getIdCardNumberBy(appId);
    String phoneNumber = PhoneUtils.getCompanyTel(appId);
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
    int variableValue = otherUserRef + otherFirstContactRef + otherSecondContactRef + otherThirdContactRef
        + otherAdditionalContactRef;
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.COMPANY_TEL_OTHER_USER_REFERENCE, otherUserRef)
        .add(AppDerivativeVariableNames.COMPANY_TEL_OTHER_COMPANY_TEL_REFERENCE, otherCompanyRef)
        .add(AppDerivativeVariableNames.COMPANY_TEL_OTHER_FIRST_CONTACT_REFERENCE, otherFirstContactRef)
        .add(AppDerivativeVariableNames.COMPANY_TEL_OTHER_SECOND_CONTACT_REFERENCE, otherSecondContactRef)
        .add(AppDerivativeVariableNames.COMPANY_TEL_OTHER_THIRD_CONTACT_REFERENCE, otherThirdContactRef)
        .add(AppDerivativeVariableNames.COMPANY_TEL_OTHER_ADDITIONAL_CONTACT_REFERENCE, otherAdditionalContactRef)
        .add(AppDerivativeVariableNames.COMPANY_TEL_DOES_OTHER_REFERENCE_EXCEPT_COMPANY_EXIST,
            variableValue > 0)
        .build());
    
    //int score = variableValue > 0 ? 60 : 0;
    FraudRuleResultDao.saveFraudDetailResult(appId, "A001","单位电话校验", variableValue > 0 ? FraudRuleResultStatus.Fail.getValue() : FraudRuleResultStatus.Pass.getValue());
  }
}
