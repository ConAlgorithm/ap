package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-交易监控_拒绝原因", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_TransactionMonitorRejectReasonForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class TransactionMonitorRejectReasonForPDLForPDL{
}

