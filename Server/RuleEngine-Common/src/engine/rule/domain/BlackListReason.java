package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

/**
 * Created by licanhui on 2016/12/6.
 */
@ModelDomainInstance(label = "百度加黑原因", type = "string", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.BlackListReason")
public class BlackListReason {
}
