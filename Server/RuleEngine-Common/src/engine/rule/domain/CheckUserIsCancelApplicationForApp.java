package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_客户是否取消申请", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserIsCancelApplication,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserIsCancelApplicationForApp{
}

