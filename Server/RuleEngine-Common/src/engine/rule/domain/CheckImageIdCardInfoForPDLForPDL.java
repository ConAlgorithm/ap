package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-身份证照审核V3_身份证信息是否可以辨认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImageIdCardInfoForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckImageIdCardInfoForPDLForPDL{
}

