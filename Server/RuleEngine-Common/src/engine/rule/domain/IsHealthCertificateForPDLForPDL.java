package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-健康证审核_是否是正面健康证", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_IsHealthCertificateForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class IsHealthCertificateForPDLForPDL{
}

