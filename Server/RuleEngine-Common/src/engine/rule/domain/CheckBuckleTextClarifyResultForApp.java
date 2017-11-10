package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "代扣协议审核V3_客户代扣协议照片是否清晰可以辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckBuckleTextClarifyResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckBuckleTextClarifyResultForApp{
}

