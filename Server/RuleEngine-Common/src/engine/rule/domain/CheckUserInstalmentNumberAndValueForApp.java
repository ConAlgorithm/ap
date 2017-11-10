package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_开放式问题询问产品熟悉程度", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserInstalmentNumberAndValue,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserInstalmentNumberAndValueForApp{
}

