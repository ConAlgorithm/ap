package jma.handlers;

import jma.NonBlockingJobV2Handler;
import jma.RetryRequiredException;
import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.util.AppDerivativeVariableManager;

public class CheckUserGenderHandler extends NonBlockingJobV2Handler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    responseMessager.setJobStatus("");
    responseMessager.setJobStatus(
        AppDerivativeVariableManager.getAsString(appId, AppDerivativeVariableConsts.IdCardGender));
  }
}
