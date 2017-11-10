package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "佰仟审核V3_佰仟记录", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckBilFinanceRecord,Catfish.Platform.ManualJobV3.Handlers")
public class CheckBilFinanceRecordForApp{
}

