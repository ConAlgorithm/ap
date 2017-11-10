package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "头像照拍照是否合规", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.HeadPhotoPhotographyResult")
public class HeadPhotoPhotographyResult {

}
