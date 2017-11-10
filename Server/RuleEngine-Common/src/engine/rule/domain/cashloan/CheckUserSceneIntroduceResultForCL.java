package engine.rule.domain.cashloan;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(
    label = "CL_客户电话审核_客户是否接受",
    type = "number",
    adapter = "engine.rule.domain.adapter.EnumDomainAdapter",
    params = "X_CheckUserSceneIntroduceResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserSceneIntroduceResultForCL{
}

