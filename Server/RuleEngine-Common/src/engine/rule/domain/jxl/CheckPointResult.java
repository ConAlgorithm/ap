package engine.rule.domain.jxl;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "业务检查点结果", type = "string", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.jxl.CheckPointResult")
public class CheckPointResult {

}
