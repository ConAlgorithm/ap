package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "教育程度", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.EducationalBackground")
public class EducationalBackground {

}
