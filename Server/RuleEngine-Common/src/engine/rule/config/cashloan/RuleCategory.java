package engine.rule.config.cashloan;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.InstalmentChannel;
import engine.rule.config.RuleConfigManager;

public enum RuleCategory implements CatfishEnum<String> {

	@Description(text = "预审批")
	PRECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.CashLoan).getNameByIdentity("precheck")),

	@Description(text = "安全检查")
	SECURITYCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.CashLoan).getNameByIdentity("securitycheck")),

	@Description(text = "终审")
	FINALCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.CashLoan).getNameByIdentity("finalcheck")),
	
	@Description(text = "增信")
	CREDITCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.CashLoan).getNameByIdentity("creditcheck")),
	
	@Description(text = "增信提额")
	CREDITPERCENT(RuleConfigManager.getConfigByChannel(InstalmentChannel.CashLoan).getNameByIdentity("creditpercent"));

	private final String value;

	@Override
	public String getValue() {
		return this.value;
	}

	RuleCategory(String value) {
		this.value = value;
	}
}
