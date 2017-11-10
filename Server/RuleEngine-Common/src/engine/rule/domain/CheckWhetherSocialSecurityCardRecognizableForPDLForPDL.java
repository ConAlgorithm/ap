package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-社保卡审核_社保卡是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherSocialSecurityCardRecognizableForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherSocialSecurityCardRecognizableForPDLForPDL{
}

