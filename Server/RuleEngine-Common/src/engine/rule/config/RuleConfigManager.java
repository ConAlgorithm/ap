package engine.rule.config;

import java.util.concurrent.ConcurrentHashMap;

import catfish.base.business.common.InstalmentChannel;

public class RuleConfigManager {

    private static ConcurrentHashMap<InstalmentChannel, RuleConfig> configMap;

    static {
        configMap = new ConcurrentHashMap<InstalmentChannel, RuleConfig>();
        configMap.putIfAbsent(InstalmentChannel.WeChat, new RuleConfig(InstalmentChannel.WeChat));
        configMap.putIfAbsent(InstalmentChannel.PayDayLoanApp, new RuleConfig(InstalmentChannel.PayDayLoanApp));
        configMap.putIfAbsent(InstalmentChannel.App, new RuleConfig(InstalmentChannel.App));
        configMap.putIfAbsent(InstalmentChannel.CashLoan, new RuleConfig(InstalmentChannel.CashLoan));
    }

    public static RuleConfig getConfigByChannel(InstalmentChannel channel) {
        return configMap.get(channel);
    }
}
