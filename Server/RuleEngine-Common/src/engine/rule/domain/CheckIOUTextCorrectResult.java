package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "客户电子借条照片中借条文信息与申请人申请借款信息是否一致", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUTextCorrectResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUTextCorrectResult {

}
