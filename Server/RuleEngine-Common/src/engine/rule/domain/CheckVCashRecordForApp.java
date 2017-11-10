package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "维信审核V3_维信记录", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckVCashRecord,Catfish.Platform.ManualJobV3.Handlers")
public class CheckVCashRecordForApp{
}

