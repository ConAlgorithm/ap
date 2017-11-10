package engine.rule.cashloan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.RejectedType;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.dao.RuleEngineDao;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.object.RuleEngineScoreResultObject;
import catfish.base.business.util.CommonUtil;
import engine.rule.RuleHandler;
import engine.rule.model.BaseForm;
import engine.rule.model.inout.cashloan.DecisionInOutForm;
import engine.util.ApplicationHelper;

public abstract class ApplicationRuleHandler<T> extends RuleHandler<T> {

	protected boolean isForTesting = StartupConfig.getAsBoolean("catfish.ruleengine.isForTestingPOS");

	//如果拒绝，则记录拒绝原因
	protected void recordReasonIfRejected(String appId, DecisionInOutForm form)
	{
		if(form.getDecisionResult() == RuleEngineDecisionResult.Rejected.getValue())
		{
			ApplicationHelper.RecordRejectReason(appId, form.getDecisionRejectReasonSet(), RejectedType.RuleEngineCheckFailed);
		}
	}
	// 保存规则执行结果
	protected void processRuleResult(String appId, String jobName,
			boolean isPassed, int score, BaseForm... outForms) {
		try {
			if (outForms == null || outForms.length == 0) {
				RuleEngineDao.saveRuleEngineResult(appId, jobName, isPassed,
						score, null);
				return;
			}
			RuleEngineScoreResultObject scoreResultObj = new RuleEngineScoreResultObject();
			for (BaseForm outForm : outForms) {
				buildScoreResultObj(scoreResultObj, outForm);
			}
			DecisionInOutForm decisionForm = (DecisionInOutForm) outForms[0];

			String execSeqStr = CommonUtil.composeConnectString(
					decisionForm.getRandomNumResultList(), ",");
			RuleEngineDao.saveRuleEngineResult(appId, jobName, isPassed, score,
					execSeqStr);
			String ruleResultId = new RuleEngineDao(appId, jobName).getSingle().Id;

			// 存储决策详情
			Set<String> message = decisionForm.getDecisionRejectReasonSet();
			if (message == null)
				return;
			Iterator<String> iter = message.iterator();
			while (iter.hasNext()) {
				RuleEngineDao.saveRuleEngineResultDetail(ruleResultId,
						iter.next(), score);
			}

			// 存储决策评分结果
			scoreResultObj.RuleEngineResultId = ruleResultId;
			RuleEngineDao.saveRuleEngineScoreResult(scoreResultObj);

		} catch (Exception e) {
			Logger.get()
					.error(String.format(
							"Save RuleEngine execute result of AppId: %s error",
							appId), e);
		}
	}

	public void buildScoreResultObj(RuleEngineScoreResultObject obj,
			BaseForm outForm) {
		if (outForm == null)
			return;
		else if (outForm instanceof DecisionInOutForm) {
			DecisionInOutForm decisionForm = (DecisionInOutForm) outForm;
			obj.ApplicationScore = decisionForm.getApplicationScore();
			obj.BehaviorScore = decisionForm.getBehaviorScore();
			obj.ConsistencyCheckScore = decisionForm.getConsistencyCheckScore();
			obj.CreditReferenceScore = decisionForm.getCreditReferenceScore();
			obj.FraudCheckScore = decisionForm.getFraudCheckScore();
			obj.InvestigationScore = decisionForm.getInvestigationScore();
			obj.PersonalInfoScore = decisionForm.getPersonalInfoScore();
			obj.SocialRelationScore = decisionForm.getSocialRelationScore();
		}
	}

	public static String getRuleResultDetailString(Set<String> message) {
		StringBuilder builder = new StringBuilder();
		Iterator<String> iter = message.iterator();
		while (iter.hasNext()) {
			builder.append(iter.next());
			builder.append("|");
		}
		return builder.toString();
	}

	public int getNeedReUploadFlagSum(Set<Integer> flagSet) {
		int sum = 0;
		Iterator<Integer> iter = flagSet.iterator();
		while (iter.hasNext()) {
			sum += iter.next();
		}
		return sum;
	}

	protected List<String> getBaseInfo(String appId) {
		EndUserExtensionObject endUser = new EndUserExtentionDao(appId)
				.getSingle();
		List<String> infoList = new ArrayList<String>();
		infoList.add(appId);
		infoList.add(endUser.IdName);
		infoList.add(endUser.IdNumber);
		return infoList;
	}

}
