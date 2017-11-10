package jma.handlers;

import java.util.HashMap;
import java.util.Map;

import catfish.cowfish.application.model.ApplicationModel;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

/**
 *百融支付消费评估查询接入--LTV
 * @author yeyb
 *
 */
public class CheckUserOnBrPayConsumForCLHandler extends CheckUserOnBrPayConsumHandler{
	@Override
	public Map<String, Object> getUserBaseInfoModel(String appId) {
        ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
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
        if(user!=null){
        	param.put("bankId",user.getBankCardAccount());
        }
		return param;
	}
}
