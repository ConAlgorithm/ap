package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-逾期还款V3_身份确认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_OverdueRepaymentRemindIdentificationForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class OverdueRepaymentRemindIdentificationForPDLForPDL{
}

