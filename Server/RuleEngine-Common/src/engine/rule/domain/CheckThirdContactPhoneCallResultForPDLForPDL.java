package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-第三联系人电话审核V3_电话是否正常接听", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckThirdContactPhoneCallResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckThirdContactPhoneCallResultForPDLForPDL{
}

