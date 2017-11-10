package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_主观判断申请人对使用CF产品重视程度", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserCarenessLevel,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserCarenessLevelForApp{
}

