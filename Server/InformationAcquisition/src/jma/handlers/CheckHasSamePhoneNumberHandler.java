package jma.handlers;

import java.util.Set;

import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckHasSamePhoneNumberHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    Set<String> phoneNumbers = CollectionUtils.<String>newSetBuilder()
        .add(PhoneUtils.getUserMobile(appId))
        .add(PhoneUtils.getCompanyTel(appId))
        .add(PhoneUtils.getFirstContactMobile(appId))
        .add(PhoneUtils.getSecondContactMobile(appId))
        .build();

    boolean hasSamePhoneNumber = phoneNumbers.size() < 4;

    AppDerivativeVariableManager.addVariables(
        new AppDerivativeVariable(
            appId, AppDerivativeVariableNames.HAS_SAME_PHONE_NUMBER, hasSamePhoneNumber));
  }
}
