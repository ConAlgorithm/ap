package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-胸牌审核_是否是正面胸牌", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_IsBadgeForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class IsBadgeForPDLForPDL{
}

