package jma.handlers;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;

/**
 * qq长度
 * @author zhangll
 *
 */
public class CheckQQLengthHandler extends NonBlockingJobHandler {

	@Override
	public void execute(String appId) throws RetryRequiredException {
		String userQQ = DatabaseUtils.getUserQQ(appId);
		if(userQQ == null || userQQ.length()<=0){
			return;
		}
		int qaLength = userQQ.length();
		AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
		.isNotNullAdd(DerivativeVariableNames.QQ_LENGHT, qaLength)
		.build());
		
		Logger.get().info("success execute CheckQQLengthHandler.appId="+appId+"  qaLength="+qaLength );
	}
	
}
