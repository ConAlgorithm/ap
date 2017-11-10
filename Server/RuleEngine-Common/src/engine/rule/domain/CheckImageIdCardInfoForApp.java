package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "身份证照审核V3_身份证信息是否可以辨认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImageIdCardInfo,Catfish.Platform.ManualJobV3.Handlers")
public class CheckImageIdCardInfoForApp{
}

