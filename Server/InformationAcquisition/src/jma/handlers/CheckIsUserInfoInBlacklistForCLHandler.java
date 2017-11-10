package jma.handlers;


import java.util.ArrayList;
import java.util.List;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.httpclient.HttpClientApi;
import jma.Configuration;
import jma.DatabaseEnumValues.BlacklistType;
import jma.RetryRequiredException;
import jma.models.BlackListInfo;
import net.sf.json.JSONObject;

/**
 * 
 * @author yeyb
 * @date 2017年7月25日
 */
public class CheckIsUserInfoInBlacklistForCLHandler extends CheckIsUserInfoInBlacklistHandler {

	@Override
	  public void execute(String appId) throws RetryRequiredException {
		
		JSONObject jsonObject = getUserInfoByAppId(appId);
		
		List<BlackListInfo> allParams = new ArrayList<>();
		
		//身份证号
		BlackListInfo id=new BlackListInfo();
		id.setContent(jsonObject.getString("idNumber"));
		id.setType(BlacklistType.ID_NUMBER);
		allParams.add(id);
		
		//用户手机号码
		BlackListInfo usermobile=new BlackListInfo();
		usermobile.setContent(jsonObject.getString("mobile"));
		usermobile.setType(BlacklistType.PHONE);
		allParams.add(usermobile);
		
		//QQ号码
		BlackListInfo qq=new BlackListInfo();
		qq.setContent(jsonObject.getString("qqNumber"));
		qq.setType(BlacklistType.QQ);
		allParams.add(qq);
		
		boolean checkWhetherOrNotBlack = checkWhetherOrNotBlack(allParams);
		
		AppDerivativeVariableManager.addVariables(
		        new AppDerivativeVariable(
		            appId, AppDerivativeVariableNames.IS_USER_INFO_IN_BLACKLIST, checkWhetherOrNotBlack));
		
	}
	
    private JSONObject getUserInfoByAppId(String appId) {

        String cowfishHost = Configuration.getCowfishUrl();        
        String appInfoUrl = cowfishHost + "/application/" + appId; 
        
        String appInfoResponse = HttpClientApi.get(appInfoUrl);
        JSONObject appJson = JSONObject.fromObject(appInfoResponse);
        
        
        String userId = appJson.getString("userId");
        
        String userInfoUrl = cowfishHost + "/user/" + userId + "/fullInfo"; 
        String userInfoResponse = HttpClientApi.get(userInfoUrl);
        JSONObject userJson = JSONObject.fromObject(userInfoResponse);
        
        Logger.get().info("get user info.userJson:" + userJson + ",url:" + userInfoUrl);
        
        return userJson;
    }

}
