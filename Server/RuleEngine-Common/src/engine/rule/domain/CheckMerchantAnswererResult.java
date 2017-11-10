package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "电话接通后，通话人情况", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckMerchantAnswererResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckMerchantAnswererResult {

}
