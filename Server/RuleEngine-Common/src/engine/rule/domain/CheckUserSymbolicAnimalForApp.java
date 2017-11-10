package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_生肖确认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserSymbolicAnimal,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserSymbolicAnimalForApp{
}

