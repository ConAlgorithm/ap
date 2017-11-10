package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-还款提醒_电话是否正常接听", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_RepaymentRemindPhoneCallResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class RepaymentRemindPhoneCallResultForPDLForPDL{
}

