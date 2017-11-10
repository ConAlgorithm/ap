package engine.rule;

import catfish.base.business.common.InstalmentChannel;

public class RuleHandlerConfigManager {

	@SuppressWarnings("rawtypes")
	public static RuleHandler getRuleHandler(String ruleName, Integer channel) {
		if(channel.equals(InstalmentChannel.App.getValue()))
			return engine.rule.app.RuleHandlerConfig.getRuleHandler(ruleName);
		else if(channel.equals(InstalmentChannel.PayDayLoanApp.getValue()))
			return engine.rule.pdl.RuleHandlerConfig.getRuleHandler(ruleName);
		else if (channel.equals(InstalmentChannel.CashLoan.getValue()))
		    return engine.rule.cashloan.RuleHandlerConfig.getRuleHandler(ruleName);
		else
		    return null;
	}
}
