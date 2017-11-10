package jma.handlers.preprocess.pdl;

import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import jma.dataservice.PhoneUtils;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckUserCreditOnBRPreModel;

public class CheckUserCreditOnBRPreprocessor implements IPreprocessor<CheckUserCreditOnBRPreModel>{

	@Override
	public CheckUserCreditOnBRPreModel getPreModel(String appId) {
		EndUserExtensionObject user = new EndUserExtentionDao(appId).getSingle();
	    String mobile = PhoneUtils.getUserMobile(appId);
	    return new CheckUserCreditOnBRPreModel(user.IdName, user.IdNumber, mobile);
	}
	
}
