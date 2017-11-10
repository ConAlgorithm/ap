package jma.handlers.preprocess.cashloan;

import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import catfish.cowfish.application.model.ApplicationModel;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckUserCreditOnBRPreModel;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

public class CheckUserCreditOnBRPreprocessor implements IPreprocessor<CheckUserCreditOnBRPreModel>{

	@Override
	public CheckUserCreditOnBRPreModel getPreModel(String appId) {
		ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
	    User user = UserResourceFactory.getUser(clApp.userId);
	    Account userAccount = UserResourceFactory.getUserAccount(clApp.userId);
	    return new CheckUserCreditOnBRPreModel(
	        user.getIdName(), user.getIdNumber(), userAccount.getMobile());
	}
	
}
