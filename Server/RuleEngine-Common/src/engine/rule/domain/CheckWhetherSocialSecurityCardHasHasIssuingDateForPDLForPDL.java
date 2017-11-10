package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-社保卡审核_是否有发卡日期", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherSocialSecurityCardHasHasIssuingDateForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherSocialSecurityCardHasHasIssuingDateForPDLForPDL{
}

