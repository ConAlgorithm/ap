package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "App交易监控_判断结果", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_TransactionMonitorResult,Catfish.Platform.ManualJobV3.Handlers")
public class TransactionMonitorResultForApp{
}

