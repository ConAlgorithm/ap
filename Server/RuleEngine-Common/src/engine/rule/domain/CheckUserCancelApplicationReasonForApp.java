package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_客户执意取消申请，询问原因", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserCancelApplicationReason,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserCancelApplicationReasonForApp{
}

