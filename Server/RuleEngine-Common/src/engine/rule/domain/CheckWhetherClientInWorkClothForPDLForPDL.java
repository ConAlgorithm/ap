package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工作服审核_是否为客户本人穿着工作服", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherClientInWorkClothForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherClientInWorkClothForPDLForPDL{
}

