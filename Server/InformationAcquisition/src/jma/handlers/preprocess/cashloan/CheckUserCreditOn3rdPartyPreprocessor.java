package jma.handlers.preprocess.cashloan;

import grasscarp.user.model.User;

import catfish.cowfish.application.model.ApplicationModel;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckUserCreditOn3rdPartyPreModel;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

public class CheckUserCreditOn3rdPartyPreprocessor implements IPreprocessor<CheckUserCreditOn3rdPartyPreModel> {

  @Override
  public CheckUserCreditOn3rdPartyPreModel getPreModel(String appId) {
    ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
    User user = UserResourceFactory.getUser(clApp.userId);
    return new CheckUserCreditOn3rdPartyPreModel(user.getIdName(), user.getIdNumber());
  }

}
