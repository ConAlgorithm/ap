package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.RuleEngineScoreResultObject;
import omni.database.catfish.object.hybrid.AppRuleEngineScoreResultObject;

public interface RuleEngineDao 
{
	
	String getRuleEngineResultId(String appId);

	Float getPersonalInfoScore(String ruleEngineResultId);

	Float getInvestigationScore(String ruleEngineResultId);

	Float getCreditReferenceScore(String ruleEngineResultId);

	Float getFraudCheckScore(String ruleEngineResultId);

	Float getApplicationScore(String ruleEngineResultId);

	Float getBehaviorScore(String ruleEngineResultId);

	String getStoreLevel(String ruleEngineResultId);

	String getRuleEngineFinalCheckResultId(String appId);
	
	RuleEngineScoreResultObject getRuleEngineScoreResultObjectByResultId(String ruleEngineResultId);

	HashMap<String, AppRuleEngineScoreResultObject> getMassiveAppRuleEngineScoreResultById(List<String> appIds);

}
