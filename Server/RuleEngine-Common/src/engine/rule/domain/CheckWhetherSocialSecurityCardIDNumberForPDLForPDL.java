package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-社保卡审核_是否有身份证号码", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherSocialSecurityCardIDNumberForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherSocialSecurityCardIDNumberForPDLForPDL{
}

