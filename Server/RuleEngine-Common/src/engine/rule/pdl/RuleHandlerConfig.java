package engine.rule.pdl;

import java.util.HashMap;
import java.util.Map;

import engine.rule.RuleHandler;
import engine.rule.config.pdl.RuleCategory;

public class RuleHandlerConfig {

	@SuppressWarnings("rawtypes")
	private static Map<String, RuleHandler> HandlerMapping = new HashMap<String, RuleHandler>();

	static {
		HandlerMapping.put(RuleCategory.FINALCHECK.getValue(), new FinalCheckRuleHandler());
		HandlerMapping.put(RuleCategory.PHOTOCHECK.getValue(), new PhotoCheckRuleHandler());
		HandlerMapping.put(RuleCategory.SEGMENTATION.getValue(), new SegmentationRuleHandler());
		HandlerMapping.put(RuleCategory.PRECHECK.getValue(), new PreCheckRuleHandler());
		HandlerMapping.put(RuleCategory.RECHECK.getValue(), new ReCheckRuleHandler());
		HandlerMapping.put(RuleCategory.MONITORCHECK.getValue(), new MonitorCheckRuleHandler());
		HandlerMapping.put(RuleCategory.ANTIFRAUDCHECK.getValue(), new AntiFraudCheckRuleHandler());
	}

	@SuppressWarnings("rawtypes")
	public static RuleHandler getRuleHandler(String ruleName) {
		return HandlerMapping.get(ruleName);
	}
}
