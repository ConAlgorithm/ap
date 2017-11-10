package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-客户电话审核_本人风险提示", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserRiskRevealForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckUserRiskRevealForPDLForPDL{
}

