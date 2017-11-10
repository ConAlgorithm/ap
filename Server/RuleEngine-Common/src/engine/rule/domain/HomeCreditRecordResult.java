package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "捷信记录", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.HomeCreditRecordResult")
public class HomeCreditRecordResult {

}