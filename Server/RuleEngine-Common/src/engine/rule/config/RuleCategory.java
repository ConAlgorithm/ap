package engine.rule.config;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.InstalmentChannel;

public enum RuleCategory implements CatfishEnum<String> {

	@Description(text = "动态问卷")
	DYNAMICQUESTIONNAIRE(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("dynamicquestionnaire")),

	@Description(text = "门店评级")
	MERCHANTRATING(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("merchantrating")),

	@Description(text = "图片分拣检测")
	PHOTO_CHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("photocheck")),

	@Description(text = "预审批")
	PRECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("precheck")),

	@Description(text = "身份证与姓名一致性检查")
	CHECK_NAME_ID_MATCH(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("checknameidmatch")),

	@Description(text = "第一次审批")
	FIRSTCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("firstcheck")),

	@Description(text = "再次审批")
	RECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("recheck")),

	@Description(text = "自动放款决策")
	LOANDECISION(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("checkwholeprocess")),

	@Description(text = "电子借条审批")
	IOUCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("ioucheck")),
	
	@Description(text = "电子借条代扣协议必要性审批")
	POSTAPPROVALEVIDENCEREQUIREMENTCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("postapprovalevidencerequirementcheck")),
	
	@Description(text = "电子借条代扣协议审批")
	POSTAPPROVALEVIDENCECHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("postapprovalevidencecheck")),

	@Description(text = "最终放款决策")
	LOANCHECK(RuleConfigManager.getConfigByChannel(InstalmentChannel.WeChat).getNameByIdentity("loancheck"));

	private final String value;

	@Override
	public String getValue() {
		return this.value;
	}

	RuleCategory(String value) {
		this.value = value;
	}
}
