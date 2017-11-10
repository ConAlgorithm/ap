package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;
@ModelDomainInstance(label = "在网时长返回", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "engine.rule.domain.MobileNetWork")
public class MobileNetWorkResult {

}
