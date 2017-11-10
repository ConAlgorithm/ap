package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "App交易监控_拒绝原因", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_TransactionMonitorRejectReason,Catfish.Platform.ManualJobV3.Handlers")
public class TransactionMonitorRejectReasonForApp{
}

