package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "商户电话审核V3_商户风险提示", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckMerchantRiskHint,Catfish.Platform.ManualJobV3.Handlers")
public class CheckMerchantRiskHintForApp{
}

