package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "身份信息录入_是否提供了其他证件", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_InputIdCardInfoIsOtherCertProvided,Catfish.Platform.ManualJobV3.Handlers")
public class InputIdCardInfoIsOtherCertProvidedForApp{
}

