/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import catfish.cowfish.application.model.ApplicationModel;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import jma.dataservice.PhoneUtils;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

/**
 * @ClassName: CheckUserOnBrApplyVerificationForCLHandler 
 * @Description: 百融特殊名单和多次申请核查查询(CL)
 * @author liyh
 * @date: 2017年7月11日 上午10:10:40
 *
 */
public class CheckUserOnBrApplyVerificationForCLHandler extends CheckUserOnBrApplyVerificationHandler {
	
	/**
	 * <p>
	 * 〈获取请求的用户相关信息〉
	 * </p>
	 * 
	 * @param appId
	 * @return
	 */
	protected Map<String, Object> getUserBaseInfoModel(String appId) {
		ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
        User user = UserResourceFactory.getUser(clApp.userId);
        Account userAccount = UserResourceFactory.getUserAccount(clApp.userId);
		String firstPhone = PhoneUtils.getFirstContactMobile(appId);
		String secondPhone = PhoneUtils.getSecondContactMobile(appId);
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer linkmanCell = new StringBuffer();
		if (user != null) {
			param.put("name", user.getIdName());
			param.put("idNo", user.getIdNumber());
		}
		if (userAccount != null) {
			param.put("mobile", userAccount.getMobile());
		}
		if(StringUtils.isNotBlank(firstPhone)){
			linkmanCell.append(firstPhone);
			if(StringUtils.isNotBlank(secondPhone)){
				linkmanCell.append(",");
				linkmanCell.append(secondPhone);
			}
		}else{
			linkmanCell.append(secondPhone);
		}
		param.put("linkmanCell", linkmanCell.toString());
		param.put("productCode", "SpecialList_c,ApplyLoanStr");
		return param;
	}

}
