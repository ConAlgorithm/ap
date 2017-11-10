package engine.rule.domain;


import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "微信头像", type = "string", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "engine.enums.WechatHead")
public class WechatHead {
	
}
