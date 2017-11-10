package engine.rule.app;

import java.util.HashMap;
import java.util.Map;

import engine.rule.RuleHandler;
import engine.rule.config.app.RuleCategory;

public class RuleHandlerConfig {

	@SuppressWarnings("rawtypes")
	private static Map<String, RuleHandler> HandlerMapping = new HashMap<String, RuleHandler>();

	static {
		HandlerMapping.put(RuleCategory.APPROVALEVIDENCEREQUIREMENTCHECK.getValue(), new ApprovalEvidenceRequirementCheckRuleHandler());
		HandlerMapping.put(RuleCategory.FINALCHECK.getValue(), new FinalCheckRuleHandler());
		HandlerMapping.put(RuleCategory.LOANCHECK.getValue(), new LoanCheckRuleHandler());
		HandlerMapping.put(RuleCategory.SIGNINGCHECK.getValue(), new SigningCheckRuleHandler());
		HandlerMapping.put(RuleCategory.FLUENTCHECK.getValue(), new FluentCheckRuleHandler());
		HandlerMapping.put(RuleCategory.SEGMENTATION.getValue(), new SegmentationRuleHandler());
		HandlerMapping.put(RuleCategory.PRECHECK.getValue(), new PreCheckRuleHandler());
		HandlerMapping.put(RuleCategory.RECHECK.getValue(), new ReCheckRuleHandler());
		HandlerMapping.put(RuleCategory.MONITORCHECK.getValue(), new MonitorCheckRuleHandler());
		HandlerMapping.put(RuleCategory.BLACKCHECK.getValue(), new BlackCheckRuleHandler());
		HandlerMapping.put(RuleCategory.FRAUDCHECK.getValue(), new FraudCheckRuleHandler());
		HandlerMapping.put(RuleCategory.DOWNPAYMENTCHECK.getValue(), new DownpaymentCheckRuleHandler());
	}

	@SuppressWarnings("rawtypes")
	public static RuleHandler getRuleHandler(String ruleName) {
		return HandlerMapping.get(ruleName);
	}
}
