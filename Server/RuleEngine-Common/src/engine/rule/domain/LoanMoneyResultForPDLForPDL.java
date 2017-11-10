package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-放款V3_转账情况", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_LoanMoneyResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class LoanMoneyResultForPDLForPDL{
}

