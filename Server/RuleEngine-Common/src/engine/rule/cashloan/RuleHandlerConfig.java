package engine.rule.cashloan;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import engine.rule.RuleHandler;
import engine.rule.config.cashloan.RuleCategory;

public class RuleHandlerConfig {

	@SuppressWarnings("rawtypes")
	private static Map<String, RuleHandler> HandlerMapping = new HashMap<String, RuleHandler>();

	static {
		HandlerMapping.put(RuleCategory.FINALCHECK.getValue(), new FinalCheckRuleHandler());
		HandlerMapping.put(RuleCategory.SECURITYCHECK.getValue(), new SecurityCheckRuleHandler());
		HandlerMapping.put(RuleCategory.PRECHECK.getValue(), new PreCheckRuleHandler());
		HandlerMapping.put(RuleCategory.CREDITCHECK.getValue(), new CreditCheckHandler());
		HandlerMapping.put(RuleCategory.CREDITPERCENT.getValue(), new CreditCheckPercentHandler());
	}

	@SuppressWarnings("rawtypes")
	public static RuleHandler getRuleHandler(String ruleName) {
		Logger.get().info(HandlerMapping);
		return HandlerMapping.get(ruleName);
	}
}
