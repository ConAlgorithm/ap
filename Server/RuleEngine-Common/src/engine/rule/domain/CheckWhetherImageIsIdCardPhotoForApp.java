package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "身份证照审核V3_是否是身份证件", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherImageIsIdCardPhoto,Catfish.Platform.ManualJobV3.Handlers")
public class CheckWhetherImageIsIdCardPhotoForApp{
}

