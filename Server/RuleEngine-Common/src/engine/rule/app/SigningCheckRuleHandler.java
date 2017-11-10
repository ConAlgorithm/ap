package engine.rule.app;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.app.UploadFileFlag;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.app.AppStoreInfoModelCreator;
import engine.rule.model.creator.app.ApplicationModelCreator;
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.creator.app.PersonalInfoModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.util.ApplicationHelper;

public class SigningCheckRuleHandler extends
		ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		String appId = message.getAppId();
		
		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobDataInt(UploadFileFlag.D1GroupPhoto.getValue());
			return message;
		}
		/***************************************************/
		if(ApplicationHelper.isCMCC(appId)){
			Logger.get().info(String.format("This is a ChinaMobile appId: %s , will not sign.", appId));
			message.setJobDataInt(UploadFileFlag.None.getValue());
			return message;
		}

		if (!DatabaseApiUtil.isNormalOrTestUser(appId)) {
			Logger.get().info(String.format("The user of AppId: %s is not a normal or test user.", appId));
			message.setJobDataInt(UploadFileFlag.None.getValue());
			return message;
		}
			
		try{
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, message.getJobName());
			
			CompositeModelCreator photoCheckCreator = new CompositeModelCreator();
			photoCheckCreator.addCreator(new ApplicationModelCreator(appId));
			photoCheckCreator.addCreator(new PersonalInfoModelCreator(appId));
			photoCheckCreator.addCreator(new AppStoreInfoModelCreator(appId));
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(photoCheckCreator, new OutModelCreator(appId, message.getJobName()));
			DecisionInOutForm result = (DecisionInOutForm)out.get("inout_Decision");
			
		    boolean needCompulsorySigning = result.isCompulsorySigning();
		    recordSigningResult(appId, needCompulsorySigning);
		    
		    UploadFileFlag flag = UploadFileFlag.None;
		    if(needCompulsorySigning)
		    {
		    	flag = UploadFileFlag.D1GroupPhoto;
		    }
			ProcessRuleResult(appId, message.getJobName(), needCompulsorySigning, flag.getValue(), result);
			
			message.setJobDataInt(flag.getValue());
			return message;
		}catch(Exception e){
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
		return null;
	}
	//记录强签结果
	public void recordSigningResult(String appId, boolean result)
	{
		AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableConsts.IsCompulsorySigning, result));
	}
}
