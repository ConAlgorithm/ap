package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "结束申请V3_操作", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_FinishApplicationOperation,Catfish.Platform.ManualJobV3.Handlers")
public class FinishApplicationOperationForApp{
}

