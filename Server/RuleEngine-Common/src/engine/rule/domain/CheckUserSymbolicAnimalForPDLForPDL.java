package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-客户电话审核_生肖确认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserSymbolicAnimalForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckUserSymbolicAnimalForPDLForPDL{
}

