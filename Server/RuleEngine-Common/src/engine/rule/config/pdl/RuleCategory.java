package engine.rule.config.pdl;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.InstalmentChannel;
import engine.rule.config.RuleConfigManager;

public enum RuleCategory implements CatfishEnum<String> {

	@Description(text = "预审批")
	PRECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.PayDayLoanApp).getNameByIdentity("precheck")),

	@Description(text = "图片必要性检查")
	PHOTOCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.PayDayLoanApp).getNameByIdentity("photocheck")),
	
	@Description(text = "再次审批")
	RECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.PayDayLoanApp).getNameByIdentity("recheck")),

	@Description(text = "分群")
	SEGMENTATION(RuleConfigManager.getConfigByChannel(InstalmentChannel.PayDayLoanApp).getNameByIdentity("segmentation")),
	
	@Description(text = "终审")
	FINALCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.PayDayLoanApp).getNameByIdentity("finalcheck")),
	
	@Description(text = "交易监控审核")
    MONITORCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.PayDayLoanApp).getNameByIdentity("monitorcheck")),
	
	@Description(text = "借款交易欺诈审核")
    ANTIFRAUDCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.PayDayLoanApp).getNameByIdentity("antifraudcheck"));
		
	private final String value;

	@Override
	public String getValue() {
		return this.value;
	}

	RuleCategory(String value) {
		this.value = value;
	}
}
