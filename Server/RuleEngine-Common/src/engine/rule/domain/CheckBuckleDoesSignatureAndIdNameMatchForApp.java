package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "代扣协议审核V3_客户代扣协议照片中客户填写信息与申请人信息是否一致", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckBuckleDoesSignatureAndIdNameMatch,Catfish.Platform.ManualJobV3.Handlers")
public class CheckBuckleDoesSignatureAndIdNameMatchForApp{
}

