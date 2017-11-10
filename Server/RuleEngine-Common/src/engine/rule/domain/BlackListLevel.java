package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

/**
 * Created by licanhui on 2016/12/6.
 */
@ModelDomainInstance(label = "百度黑名单等级", type = "string", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.BlackListLevel")
public class BlackListLevel {
}
