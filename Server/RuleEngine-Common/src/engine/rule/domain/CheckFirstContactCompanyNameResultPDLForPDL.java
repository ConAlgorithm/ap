package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-第一联系人电话审核V3_关系核身单位名称($(Relation))", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckFirstContactCompanyNameResultPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckFirstContactCompanyNameResultPDLForPDL{
}

