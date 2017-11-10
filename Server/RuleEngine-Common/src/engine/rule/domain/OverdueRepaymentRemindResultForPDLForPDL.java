package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-逾期还款V3_还款提醒结果", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_OverdueRepaymentRemindResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class OverdueRepaymentRemindResultForPDLForPDL{
}

