package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-胸牌审核_胸牌是否在客户胸前佩戴", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherBadgeOnBreastForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherBadgeOnBreastForPDLForPDL{
}

