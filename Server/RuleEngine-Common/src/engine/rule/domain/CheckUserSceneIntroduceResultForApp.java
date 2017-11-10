package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_客户是否接受", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserSceneIntroduceResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserSceneIntroduceResultForApp{
}

