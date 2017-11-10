package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-社保卡审核_是否是正面社保卡", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_IsSocialSecurityCardForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class IsSocialSecurityCardForPDLForPDL{
}

