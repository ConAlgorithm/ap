package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-健康证审核_是否有体检时间-发证时间", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherHealthCertificateExaminationTimeForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherHealthCertificateExaminationTimeForPDLForPDL{
}

