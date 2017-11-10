package jma.handlers;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.cowfish.application.model.ApplicationModel;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

/**
 * LTV接入富数黑名单接口
 * @author yeyb
 * @date 2017年8月24日
 */
public class CheckUserOnFuShuForCLHandler extends CheckUserOnFuShuHandler {
	
	@Override
	public Map<String, Object> getUserBaseInfoModel(String appId) {
        ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
        if(clApp==null){
        	Logger.get().warn(String.format("***get null ApplicationModel from cowfishHost,appId=%s",appId));
        	return null;
        }
        User user = UserResourceFactory.getUser(clApp.userId);
        Account userAccount = UserResourceFactory.getUserAccount(clApp.userId);
        Map<String, Object> param = new HashMap<String, Object>();
        if(user!=null){
        	param.put("name", user.getIdName());
        	param.put("idNo", user.getIdNumber());
        }
        if(userAccount!=null){
        	param.put("mobile", userAccount.getMobile());
        }
		return param;
	}

}
