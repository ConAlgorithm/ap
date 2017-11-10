package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "商户电话审核V3_购机条件确定，等待放款", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckMerchantPurchaseConfirmed,Catfish.Platform.ManualJobV3.Handlers")
public class CheckMerchantPurchaseConfirmedForApp{
}

