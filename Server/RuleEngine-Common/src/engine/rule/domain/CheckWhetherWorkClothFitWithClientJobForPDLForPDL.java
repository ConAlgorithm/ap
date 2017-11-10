package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工作服审核_工作服与职业性质是否相符", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherWorkClothFitWithClientJobForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherWorkClothFitWithClientJobForPDLForPDL{
}

