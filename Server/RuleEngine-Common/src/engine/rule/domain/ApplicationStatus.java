package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "业务申请状态(衍生变量)", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.ApplicationStatus")
public class ApplicationStatus {

}
