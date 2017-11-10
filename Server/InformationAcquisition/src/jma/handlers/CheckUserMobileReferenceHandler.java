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

public class CheckUserMobileReferenceHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    String currentUserIdNumber = DatabaseUtils.getIdCardNumberBy(appId);
    String phoneNumber = PhoneUtils.getUserMobile(appId);
    int otherUserRef = PhoneReferenceUtils.getOtherUserMobileReference(currentUserIdNumber, phoneNumber);
    int otherParentRef = PhoneReferenceUtils.getOtherParentPhoneReference(currentUserIdNumber, phoneNumber);
    int otherCompanyTelRef = PhoneReferenceUtils.getOtherCompanyTelReference(currentUserIdNumber, phoneNumber);
    int otherFirstContactMobileRef = PhoneReferenceUtils.getOtherFirstContactMobileReference(currentUserIdNumber, phoneNumber);
    int otherSecondContactMobileRef = PhoneReferenceUtils.getOtherSecondContactMobileReference(currentUserIdNumber, phoneNumber);
    int otherThirdContactMobileRef = PhoneReferenceUtils.getOtherThirdContactMobileReference(currentUserIdNumber, phoneNumber);
    int otherAdditionalContactMobileRef = PhoneReferenceUtils.getOtherAdditionalContactMobileReference(currentUserIdNumber, phoneNumber);
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.USER_MOBILE_OTHER_USER_REFERENCE, otherUserRef)
        .add(AppDerivativeVariableNames.USER_MOBILE_OTHER_COMPANY_TEL_REFERENCE,
            otherCompanyTelRef)
        .add(AppDerivativeVariableNames.USER_MOBILE_OTHER_FIRST_CONTACT_REFERENCE,
            otherFirstContactMobileRef)
        .add(AppDerivativeVariableNames.USER_MOBILE_OTHER_SECOND_CONTACT_REFERENCE,
            otherSecondContactMobileRef)
        .add(AppDerivativeVariableNames.USER_MOBILE_OTHER_THIRD_CONTACT_REFERENCE,
            otherThirdContactMobileRef)
        .add(AppDerivativeVariableNames.USER_MOBILE_OTHER_ADDITIONAL_CONTACT_REFERENCE,
            otherAdditionalContactMobileRef)
        .add(AppDerivativeVariableNames.USER_MOBILE_DOES_OTHER_USER_OR_PARENT_REFERENCE_EXIST,
            otherUserRef > 0 || otherParentRef > 0)
        .build());
    //int score = otherUserRef > 0 || otherParentRef > 0 ? 100 : 0;
    FraudRuleResultDao.saveFraudDetailResult(appId, "A002","个人电话校验", (otherUserRef > 0 || otherParentRef > 0) ? FraudRuleResultStatus.Fail.getValue() : FraudRuleResultStatus.Pass.getValue());
  }
}
