package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "捷信审核V3_捷信记录", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckHomeCreditRecord,Catfish.Platform.ManualJobV3.Handlers")
public class CheckHomeCreditRecordForApp{
}

