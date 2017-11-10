package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "客户姓名一致性检查", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.CheckBinaryConsistencyResult")
public class CheckBinaryConsistencyResult {

}
