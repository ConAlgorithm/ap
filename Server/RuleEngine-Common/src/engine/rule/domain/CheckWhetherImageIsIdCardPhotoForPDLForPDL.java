package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-身份证照审核V3_是否是身份证", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherImageIsIdCardPhotoForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherImageIsIdCardPhotoForPDLForPDL{
}

