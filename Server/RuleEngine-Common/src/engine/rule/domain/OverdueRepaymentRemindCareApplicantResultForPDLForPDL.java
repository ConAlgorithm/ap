package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-逾期还款V3_主观判断申请人对使用CF产品重视程度", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_OverdueRepaymentRemindCareApplicantResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class OverdueRepaymentRemindCareApplicantResultForPDLForPDL{
}

