package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "身份证信息录入-额外证件类型", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.InputIdCardInfoCertTypeResult")
public class InputIdCardInfoCertTypeResult {

}
