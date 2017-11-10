package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "头像照是否在现场", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.HeadPhotoPhotographySiteResult")
public class HeadPhotoPhotographySiteResult {

}
