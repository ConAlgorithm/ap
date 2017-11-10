package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "商户电话审核V3_致电销售人员代行放款信息核对", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckMerchantReplacedBySales,Catfish.Platform.ManualJobV3.Handlers")
public class CheckMerchantReplacedBySalesForApp{
}

