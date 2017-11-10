package engine.rule.config.app;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.InstalmentChannel;
import engine.rule.config.RuleConfigManager;

public enum RuleCategory implements CatfishEnum<String> {

	@Description(text = "预审批")
	PRECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("precheck")),

	@Description(text = "强签检查")
	SIGNINGCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("signingcheck")),
	
	@Description(text = "黑名单检查")
	BLACKCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("blackcheck")),
	
	@Description(text = "再次审批")
	RECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("recheck")),

	@Description(text = "流通检查")
	FLUENTCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("fluentcheck")),
	
	@Description(text = "分群")
	SEGMENTATION(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("segmentation")),

	@Description(text = "放款必要文件检查")
	APPROVALEVIDENCEREQUIREMENTCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("approvalEvidenceRequirementCheck")),
	
	@Description(text = "终审")
	FINALCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("finalcheck")),
	
	@Description(text = "欺诈检查")
	FRAUDCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("fraudcheck")),
	
	@Description(text = "交易监控审核")
    MONITORCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("monitorcheck")),
    	
	@Description(text = "放款审核")
	LOANCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("loancheck")),
	
	@Description(text = "首付比检查")
	DOWNPAYMENTCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.App).getNameByIdentity("downpaymentcheck"));	
	
	private final String value;

	@Override
	public String getValue() {
		return this.value;
	}

	RuleCategory(String value) {
		this.value = value;
	}
}
