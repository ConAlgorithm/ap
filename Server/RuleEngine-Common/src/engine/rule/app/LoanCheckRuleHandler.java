package engine.rule.app;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catfish.base.Logger;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RejectedType;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.queue.QueueMessager;
import engine.main.Configuration;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.app.AppStoreInfoModelCreator;
import engine.rule.model.creator.app.ApplicationModelCreator;
import engine.rule.model.creator.app.FraudCheckModelCreator;
import engine.rule.model.creator.app.InvestigationModelCreator;
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.creator.app.PersonalInfoModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.util.ApplicationHelper;

public class LoanCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		String appId = message.getAppId();
		String jobName = message.getJobName();
		/***************just for testing********************/
		if(isForTesting)
		{
			if(message.getJobDataInt() == 0)
			{
				ApplicationHelper.ResetUploadedStatus(appId, 48, true);
				message.setJobStatus(JobStatus.RecheckingRequired);
				message.setJobDataInt(48);
			}
			else if(message.getJobDataInt() == 1)
			{
				ApplicationHelper.ResetUploadedStatus(appId, 16, true);
				message.setJobStatus(JobStatus.RecheckingRequired);
				message.setJobDataInt(16);
			}
			else
			{
				message.setJobStatus(JobStatus.Approved);
			}		
			return message;
		}		
		/***************************************************/
		
		//如果申请已经被拒绝，则保证不会启动放款流程！
		int currentStatus = InstallmentApplicationDao.getAppStatusById(appId);
		if(currentStatus == ApplicationStatus.Canceled.getValue() || currentStatus == ApplicationStatus.Rejected.getValue()){
			Logger.get().info(String.format("Application of AppId: %s is canceled or rejected, payment won't start.", appId));
			return new QueueMessager(appId, jobName, JobStatus.Rejected);
		}
		
		try {			
			if (!DatabaseApiUtil.isNormalOrTestUser(appId)) {
				Logger.get().info(String.format("The user of AppId: %s is not a normal or test user.", appId));
				Set<String> rejectReason = new HashSet<String>();
				rejectReason.add("Invalid user type");
				ApplicationHelper.RecordRejectReason(appId, rejectReason, RejectedType.RuleEngineCheckFailed);
				message.setJobStatus(JobStatus.Rejected);
				return message;
			}
			
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, jobName);

			CompositeModelCreator loanCheckCreator = new CompositeModelCreator();
			loanCheckCreator.addCreator(new InvestigationModelCreator(appId));
			loanCheckCreator.addCreator(new FraudCheckModelCreator(appId));
            loanCheckCreator.addCreator(new ApplicationModelCreator(appId));
            loanCheckCreator.addCreator(new PersonalInfoModelCreator(appId));
            loanCheckCreator.addCreator(new AppStoreInfoModelCreator(appId));
            
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(loanCheckCreator, 
			    new OutModelCreator(appId, message.getJobDataInt(), message.getJobName()));

			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			Integer ruleEngineDecisionResult = result.getDecisionResult();

			ProcessRuleResult(appId, jobName, ruleEngineDecisionResult == RuleEngineDecisionResult.Approved.getValue(), 0, result);

			if (ruleEngineDecisionResult == RuleEngineDecisionResult.Rejected.getValue()) {
				this.recordReasonIfRejected(appId, result);
				return new QueueMessager(appId, jobName, JobStatus.Rejected);
			}
			else if(ruleEngineDecisionResult == RuleEngineDecisionResult.Approved.getValue()){
				MessageNotificationUtil.sendMessageAsynchronously("LoanVoucherApproved", appId, "");
				return new QueueMessager(appId, jobName, JobStatus.Approved);
			}else if(ruleEngineDecisionResult == RuleEngineDecisionResult.Canceled.getValue()){
				return new QueueMessager(appId, jobName, JobStatus.Canceled);
			}

			//RecheckingRequired --- jobDataInt
			int needReuploadFlag = getNeedReUploadFlagSum(result.getNeedReUploadFlagSet());
			//记录重传原因次数
			String needReuploadReason = result.getNeedReUploadReasonSet().toString();
			int restCount = Configuration.PIC_REUPLOAD_MAX_COUNT-result.getReuploadCount();
			recordReasonAndCount(appId,needReuploadReason,restCount);	
			if (needReuploadFlag != UploadFileStatus.None.getValue()) {
				ApplicationHelper.ResetUploadedStatus(appId, needReuploadFlag, true);
			}
			message.setJobStatus(JobStatus.RecheckingRequired);
			message.setJobDataInt(needReuploadFlag);
			return message;
			
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
	
		return null;
	}

}
