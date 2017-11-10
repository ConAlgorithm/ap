package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "商户电话审核V3_客户是否在现场", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckMerchantCustomerOnSpot,Catfish.Platform.ManualJobV3.Handlers")
public class CheckMerchantCustomerOnSpotForApp{
}

