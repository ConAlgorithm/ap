package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;
@ModelDomainInstance(label = "手机三要素返回", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "engine.rule.domain.MobileThree")
public class MobileThreeResult {

}
