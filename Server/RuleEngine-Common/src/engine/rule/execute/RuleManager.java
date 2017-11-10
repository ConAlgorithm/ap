package engine.rule.execute;

import java.util.concurrent.ConcurrentHashMap;

import catfish.base.business.common.InstalmentChannel;
import engine.exception.CannotFindConfigItemException;

public class RuleManager {

	private static ConcurrentHashMap<String, RuleExecutor> ruleExecutorMap = new ConcurrentHashMap<String, RuleExecutor>();
	
	public static RuleExecutor GetRuleExecutor(InstalmentChannel channel, String ruleName)
			throws CannotFindConfigItemException {
	    
	    String key = channel.getValue() + "_" + ruleName;
	    
		if (!ruleExecutorMap.containsKey(key)) {
			RuleExecutor executor = new RuleExecutor(channel, ruleName);
			ruleExecutorMap.putIfAbsent(key, executor);
		}
		return ruleExecutorMap.get(key);
	}
}
