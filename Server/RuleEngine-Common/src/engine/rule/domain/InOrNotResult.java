package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "是/不是", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "engine.enums.InOrNotResult")
public class InOrNotResult {

}
