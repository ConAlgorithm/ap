package jma.handlers;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseEnumValues;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.AppDataService;
import jma.dataservice.PhoneReferenceUtils;
import jma.dataservice.PhoneUtils;
import jma.dataservice.UserDataService;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckAdditionalContactReferenceHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
	String userId = AppDataService.getUserId(appId);
    String currentUserIdNumber = UserDataService.getIdNumber(userId);

    for(int i = DatabaseEnumValues.ContactPersonType.ADDITIONAL_CONTACT_PERSON_BASE;
        i <= DatabaseEnumValues.ContactPersonType.ADDITIONAL_CONTACT_PERSON_UP;
        ++i) {
      String phoneNumber = PhoneUtils.getAdditionalContactMobile(appId, i);

      if(phoneNumber == null) break;

      AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_OTHER_USER_REFERNCE + "_" + i,
              PhoneReferenceUtils.getOtherUserMobileReference(currentUserIdNumber, phoneNumber))
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_OTHER_COMPANY_TEL_REFERENCE + "_" + i,
              PhoneReferenceUtils.getOtherCompanyTelReference(currentUserIdNumber, phoneNumber))
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_OTHER_FIRST_CONTACT_REFERENCE + "_" + i,
              PhoneReferenceUtils.getOtherFirstContactMobileReference(currentUserIdNumber, phoneNumber))
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_OTHER_SECOND_CONTACT_REFERENCE + "_" + i,
              PhoneReferenceUtils.getOtherSecondContactMobileReference(currentUserIdNumber, phoneNumber))
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_OTHER_THIRD_CONTACT_REFERENCE + "_" + i,
              PhoneReferenceUtils.getOtherThirdContactMobileReference(currentUserIdNumber, phoneNumber))
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_OTHER_ADDITIONAL_CONTACT_REFERENCE + "_" + i,
              PhoneReferenceUtils.getOtherAdditionalContactMobileReference(currentUserIdNumber, phoneNumber))
          .build());
    }
  }
}
