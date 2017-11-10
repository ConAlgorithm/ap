package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-胸牌审核_是否有单位名称", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherBadgeHasCompanyNameForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherBadgeHasCompanyNameForPDLForPDL{
}

