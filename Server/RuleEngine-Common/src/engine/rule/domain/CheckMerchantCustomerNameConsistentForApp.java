package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "商户电话审核V3_门店确定客户姓名", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckMerchantCustomerNameConsistent,Catfish.Platform.ManualJobV3.Handlers")
public class CheckMerchantCustomerNameConsistentForApp{
}

