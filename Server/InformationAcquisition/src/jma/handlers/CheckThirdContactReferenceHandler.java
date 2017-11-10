package jma.handlers;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneReferenceUtils;
import jma.dataservice.PhoneUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckThirdContactReferenceHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String currentUserIdNumber = DatabaseUtils.getIdCardNumberBy(appId);
    String phoneNumber = PhoneUtils.getThirdContactMobile(appId);

    if(phoneNumber == null) return;
    int otherUserMobileRef = PhoneReferenceUtils.getOtherUserMobileReference(currentUserIdNumber, phoneNumber);
    int otherCompanyTelRef = PhoneReferenceUtils.getOtherCompanyTelReference(currentUserIdNumber, phoneNumber);
    int otherFirstContactMobileRef = PhoneReferenceUtils.getOtherFirstContactMobileReference(currentUserIdNumber, phoneNumber);
    int otherSecondContactMobileRef = PhoneReferenceUtils.getOtherSecondContactMobileReference(currentUserIdNumber, phoneNumber);
    int otherThirdContactMobileRef = PhoneReferenceUtils.getOtherThirdContactMobileReference(currentUserIdNumber, phoneNumber);
    int otherAdditionalContactMobileRef = PhoneReferenceUtils.getOtherAdditionalContactMobileReference(currentUserIdNumber, phoneNumber);
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.THIRD_CONTACT_OTHER_USER_REFERNCE,
            otherUserMobileRef)
        .add(AppDerivativeVariableNames.THIRD_CONTACT_OTHER_COMPANY_TEL_REFERENCE,
            otherCompanyTelRef)
        .add(AppDerivativeVariableNames.THIRD_CONTACT_OTHER_FIRST_CONTACT_REFERENCE,
            otherFirstContactMobileRef)
        .add(AppDerivativeVariableNames.THIRD_CONTACT_OTHER_SECOND_CONTACT_REFERENCE,
            otherSecondContactMobileRef)
        .add(AppDerivativeVariableNames.THIRD_CONTACT_OTHER_THIRD_CONTACT_REFERENCE,
            otherThirdContactMobileRef)
        .add(AppDerivativeVariableNames.THIRD_CONTACT_OTHER_ADDITIONAL_CONTACT_REFERENCE,
            otherAdditionalContactMobileRef)
        .build());
  }
}
