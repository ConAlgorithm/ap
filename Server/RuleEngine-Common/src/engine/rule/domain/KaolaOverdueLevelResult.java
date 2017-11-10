package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;
@ModelDomainInstance(label = "考拉逾期级别", type = "string", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "engine.rule.domain.KaolaOverdueLevel")
public class KaolaOverdueLevelResult {

}
