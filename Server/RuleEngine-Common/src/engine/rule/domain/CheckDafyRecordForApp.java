package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "达飞和好贷审核V3_达飞记录", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckDafyRecord,Catfish.Platform.ManualJobV3.Handlers")
public class CheckDafyRecordForApp{
}

