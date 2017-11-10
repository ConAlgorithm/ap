package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "放款V3_转账失败原因", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_LoanMoneyFailureReason,Catfish.Platform.ManualJobV3.Handlers")
public class LoanMoneyFailureReasonForApp{
}

