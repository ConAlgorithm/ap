package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "微信头像检查结果", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.fraud.WXHeadPhotoCheckResult")
public class WXHeadPhotoCheckResult {

}
