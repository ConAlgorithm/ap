package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "代扣协议审核V3_检查上传照片是否为代扣协议", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckBuckleIsBuckleAgreement,Catfish.Platform.ManualJobV3.Handlers")
public class CheckBuckleIsBuckleAgreementForApp{
}

