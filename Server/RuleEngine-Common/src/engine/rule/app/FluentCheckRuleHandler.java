package engine.rule.app;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.app.AppStoreInfoModelCreator;
import engine.rule.model.creator.app.ApplicationModelCreator;
import engine.rule.model.creator.app.BehaviorModelCreator;
import engine.rule.model.creator.app.ConsistencyCheckModelCreator;
import engine.rule.model.creator.app.CreditReferenceModelCreator;
import engine.rule.model.creator.app.DynamicApplicationModelCreator;
import engine.rule.model.creator.app.FraudCheckModelCreator;
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.creator.app.PersonalInfoModelCreator;
import engine.rule.model.creator.app.SocialRelationModelCreator;
import engine.rule.model.creator.app.ThirdpartyDataModelCreator;
import engine.rule.model.creator.app.domain.InstallmentStatus;
import engine.rule.model.inout.app.DecisionInOutForm;

public class FluentCheckRuleHandler extends ApplicationRuleHandler<QueueMessager>{

	@Override
	public QueueMessager handle(QueueMessager message) {
		String appId = message.getAppId();
		

		
		boolean isContinuable = false;
		try {
			InstallmentApplicationObject app = InstallmentApplicationDao.getApplicationInfoById(appId);
			if(app.Status == ApplicationStatus.Rejected.getValue() || app.Status == ApplicationStatus.Canceled.getValue()) {
				Logger.get().warn("Application is rejected, will not run fluent check");
				return null;
			}
			
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, message.getJobName());
			
			PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
			ConsistencyCheckModelCreator consistencyCheckModelCreator = new ConsistencyCheckModelCreator(appId, personalInfoCreator);
			
			CompositeModelCreator autoLoanDecisionCreator = new CompositeModelCreator();
			autoLoanDecisionCreator.addCreator(personalInfoCreator);
			autoLoanDecisionCreator.addCreator(new SocialRelationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new BehaviorModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new FraudCheckModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new DynamicApplicationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new CreditReferenceModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new ApplicationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(consistencyCheckModelCreator);
			autoLoanDecisionCreator.addCreator(new AppStoreInfoModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new ThirdpartyDataModelCreator(appId));
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					autoLoanDecisionCreator, new OutModelCreator(appId, message.getJobName()));
			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			isContinuable = (result.getDecisionResult() == RuleEngineDecisionResult.Approved.getValue());
			
			message.setJobStatus(ResultStatusMap.get(result.getDecisionResult()));
			ProcessRuleResult(appId, message.getJobName(), isContinuable, 0, result);
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
	
		return message;
	}

}
