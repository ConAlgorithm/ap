package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "销售报警类型", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.MerchantUserActionType")
public class MerchantUserActionType {}
