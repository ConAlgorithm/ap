package engine.rule.pdl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import catfish.base.DescriptionParser;
import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.pdl.ConsistencyCheckModelCreator;
import engine.rule.model.creator.pdl.FraudCheckModelCreator;
import engine.rule.model.creator.pdl.InvestigationModelCreator;
import engine.rule.model.creator.pdl.OutModelCreator;
import engine.rule.model.creator.pdl.PersonalInfoModelCreator;
import engine.rule.model.creator.pdl.ThirdpartyDataModelCreator;
import engine.rule.model.inout.pdl.DecisionInOutForm;
import engine.util.ApplicationHelper;

public class ReCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		String appId = message.getAppId();
		String jobName = message.getJobName();
		
		/***************just for testing********************/
		if(isForTesting)
		{
            /*if(message.getJobDataInt() == 0)
			{
				ApplicationHelper.ResetUploadedStatus(appId, 39, true);
				message.setJobStatus(JobStatus.RecheckingRequired);
				message.setJobDataInt(39);
			}*/
			//if(message.getJobDataInt() == 1)
			{
				int uploadStatus = 0;
				List<UploadFileStatus> list = new ArrayList<UploadFileStatus>(){
					{add(UploadFileStatus.ChestCardPhotoUploaded);}
					{add(UploadFileStatus.SocialSecurityCardPhotoUploaded);}
					{add(UploadFileStatus.WorkPermitUploaded);}
					{add(UploadFileStatus.TimeCardPhotoUploaded);}
					{add(UploadFileStatus.UniformPhotoUploaded);}
					{add(UploadFileStatus.HealthCertificatePhotoUploaded);}
				};
				Random random = new Random();
				for (int i = 0; i < list.size(); i++) {
					if(random.nextInt() % 2 == 0)
					{
					  uploadStatus |= list.get(i).getValue();
					}
				}
				
				ApplicationHelper.ResetUploadedStatus(appId, uploadStatus, true);
				message.setJobStatus(JobStatus.RecheckingRequired);
				message.setJobDataInt(uploadStatus);
			}
//			else
//			{
//				message.setJobStatus(JobStatus.Approved);
//			}
			
			return message;
		}
		/***************************************************/
		
		try {
			if (DatabaseApiUtil.isNormalOrTestUser(appId)) {
				RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.PayDayLoanApp, jobName);

				CompositeModelCreator reCheckCreator = new CompositeModelCreator();
				PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
				reCheckCreator.addCreator(new FraudCheckModelCreator(appId));
				reCheckCreator.addCreator(new ConsistencyCheckModelCreator(appId, personalInfoCreator));
				reCheckCreator.addCreator(new InvestigationModelCreator(appId));
				reCheckCreator.addCreator(new ThirdpartyDataModelCreator(appId));
				
				Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
						reCheckCreator, new OutModelCreator(appId, message.getJobDataInt()));

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
					message.setJobStatus(JobStatus.Approved);
					return message;
				}
				
				//RecheckingRequired --- jobDataInt
				int needReuploadFlag = getNeedReUploadFlagSum(result.getNeedReUploadFlagSet());		
				if (needReuploadFlag != UploadFileStatus.None.getValue()) {
					ApplicationHelper.ResetUploadedStatus(appId, needReuploadFlag, true);
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
