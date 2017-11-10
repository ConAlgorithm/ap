package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "电话是否正常接听", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckMerchantPhoneCallResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckMerchantPhoneCallResult {

}
