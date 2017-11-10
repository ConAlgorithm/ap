package jma.handlers.preprocess.cashloan;

import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import catfish.cowfish.application.model.ApplicationModel;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckUserCreditOnTdPreModel;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

public class CheckUserCreditOnTdPreprocessor implements IPreprocessor<CheckUserCreditOnTdPreModel> {

  @Override
  public CheckUserCreditOnTdPreModel getPreModel(String appId) {
    ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
    User user = UserResourceFactory.getUser(clApp.userId);
    Account userAccount = UserResourceFactory.getUserAccount(clApp.userId);
    return new CheckUserCreditOnTdPreModel(
        user.getIdName(), user.getIdNumber(), userAccount.getMobile());
  }

}
