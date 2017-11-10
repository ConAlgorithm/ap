package jma.handlers;

import jma.AppDerivativeVariablesBuilder;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.models.ContactInfo;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

/**
 * LTV联系人集奥二要素验证
 * @author zhangll
 *
 */
public class JOFirstContentForCLHandler extends JOInfoCrawlHandler{

	@Override
	public void execute(String appId) throws RetryRequiredException {
		
		try {
			ContactInfo firstContactInfo = PhoneUtils.getFirstContactInfo(appId);
		
			AppDerivativeVariablesBuilder adBuilder = new AppDerivativeVariablesBuilder(appId);
			if (firstContactInfo == null) {
				Logger.get().info("JOFirstContentForCLHandler.firstContactinfo is null");
				return ;
			}
			saveContactIFT(AppDerivativeVariableNames.JO_IsFirstContactRealAuthenticated2, AppDerivativeVariableNames.JO_IsFirstContactRealAuthenticated2Desc, getJoData(firstContactInfo), adBuilder);
			AppDerivativeVariableManager.addVariables(adBuilder.build());
		} catch (Exception e) {
            Logger.get().error("JOFirstContentForCL data error for" + appId, e);
            throw new RetryRequiredException();
        }
	}
	
}
