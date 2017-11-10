package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-现场照片审核V3_是否有销售", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIsSalesmanExistedForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckIsSalesmanExistedForPDLForPDL{
}

