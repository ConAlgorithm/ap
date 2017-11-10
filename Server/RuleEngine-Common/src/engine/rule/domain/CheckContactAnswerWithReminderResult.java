package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "联系人回答问题风险提示项", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.CheckContactAnswerWithReminderResult")
public class CheckContactAnswerWithReminderResult {

}
