package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "放款V3_转账情况", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_LoanMoneyResult,Catfish.Platform.ManualJobV3.Handlers")
public class LoanMoneyResultForApp{
}

