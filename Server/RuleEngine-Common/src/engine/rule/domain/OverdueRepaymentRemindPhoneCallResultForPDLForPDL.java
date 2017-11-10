package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-逾期还款V3_电话是否正常接听", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_OverdueRepaymentRemindPhoneCallResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class OverdueRepaymentRemindPhoneCallResultForPDLForPDL{
}

