package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-健康证审核_是否有姓名", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherHealthCertificateHasClientNameForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherHealthCertificateHasClientNameForPDLForPDL{
}

