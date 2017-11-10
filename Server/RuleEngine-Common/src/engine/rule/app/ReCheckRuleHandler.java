package engine.rule.app;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.common.app.UploadFileFlag;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import engine.main.Configuration;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.app.ApplicationModelCreator;
import engine.rule.model.creator.app.ConsistencyCheckModelCreator;
import engine.rule.model.creator.app.FraudCheckModelCreator;
import engine.rule.model.creator.app.InvestigationModelCreator;
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.creator.app.PersonalInfoModelCreator;
import engine.rule.model.creator.app.SHZXDataModelCreator;
import engine.rule.model.creator.app.ThirdpartyDataModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.util.ApplicationHelper;

public class ReCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		String appId = message.getAppId();
		String jobName = message.getJobName();
		
		/***************just for testing********************/
		if(isForTesting)
		{
			if(message.getJobDataInt() == 0)
			{
				ApplicationHelper.ResetUploadedStatus(appId, 7, true);
				message.setJobStatus(JobStatus.RecheckingRequired);
				message.setJobDataInt(7);
			}
			else if(message.getJobDataInt() == 1)
			{
				ApplicationHelper.ResetUploadedStatus(appId, 1, true);
				message.setJobStatus(JobStatus.RecheckingRequired);
				message.setJobDataInt(1);
			}
			else
			{
				message.setJobStatus(JobStatus.Approved);
			}
			
			return message;
		}
		/***************************************************/
			
		try {
			if (DatabaseApiUtil.isNormalOrTestUser(appId)) {
				RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, jobName);

				CompositeModelCreator reCheckCreator = new CompositeModelCreator();
				reCheckCreator.addCreator(new ApplicationModelCreator(appId));
				PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
				reCheckCreator.addCreator(personalInfoCreator);
				reCheckCreator.addCreator(new ConsistencyCheckModelCreator(appId, personalInfoCreator));
				reCheckCreator.addCreator(new InvestigationModelCreator(appId));
				reCheckCreator.addCreator(new ThirdpartyDataModelCreator(appId));
				reCheckCreator.addCreator(new FraudCheckModelCreator(appId));
				reCheckCreator.addCreator(new SHZXDataModelCreator(appId));  
				
				Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
						reCheckCreator, new OutModelCreator(appId, message.getJobDataInt(), message.getJobName()));

				DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
				Integer decisionResult = result.getDecisionResult();

				//TODO: keep the RuleEngineResultObjects.IsPassed as a boolean type to support the weixin rules for now.
				//We should write a migration to change the boolean type to an int type.
				ProcessRuleResult(appId, jobName, decisionResult == RuleEngineDecisionResult.Approved.getValue(), 0, result);

				if (decisionResult == RuleEngineDecisionResult.Rejected.getValue()) {
					this.recordReasonIfRejected(appId, result);
					message.setJobStatus(JobStatus.Rejected);
					return message;
				}
				else if(decisionResult == RuleEngineDecisionResult.Approved.getValue()){
					//先清除掉重传标志位
					ApplicationHelper.ClearReuploadFlag(appId);
					
					message.setJobStatus(JobStatus.Approved);
					return message;
				}
				
				//RecheckingRequired --- jobDataInt
				int needReuploadFlag = getNeedReUploadFlagSum(result.getNeedReUploadFlagSet());
				//记录重传原因次数
				String needReuploadReason = result.getNeedReUploadReasonSet().toString();
				int restCount = Configuration.PIC_REUPLOAD_MAX_COUNT-result.getReuploadCount();
				recordReasonAndCount(appId,needReuploadReason,restCount);				
				//过渡过程，D1GroupPhotoCheck重置调用另外一个Service
				if((needReuploadFlag & UploadFileFlag.D1GroupPhoto.getValue()) > 0)
				{
					ApplicationHelper.ResetD1GroupPhotoUploadedStatus(appId);
				}
				
				if (needReuploadFlag != UploadFileStatus.None.getValue()) {
					ApplicationHelper.ResetUploadedStatus(appId, needReuploadFlag & ~UploadFileFlag.D1GroupPhoto.getValue(), true);
				}
				
				message.setJobStatus(JobStatus.RecheckingRequired);
				message.setJobDataInt(needReuploadFlag);
				return message;			
			}
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
		
		return null;
	}

	public int getNeedReUploadFlagSum(Set<Integer> flagSet) {
		int sum = 0;
		Iterator<Integer> iter = flagSet.iterator();
		while (iter.hasNext()) {
			sum += iter.next();
		}
		return sum;
	}
}
