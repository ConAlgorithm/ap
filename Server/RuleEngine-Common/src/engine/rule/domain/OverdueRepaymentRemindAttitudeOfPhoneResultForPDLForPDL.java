package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-逾期还款V3_主观判断联系人对本通电话反感程度", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_OverdueRepaymentRemindAttitudeOfPhoneResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class OverdueRepaymentRemindAttitudeOfPhoneResultForPDLForPDL{
}

