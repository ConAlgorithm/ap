package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_身份确认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserIdentification,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserIdentificationForApp{
}

