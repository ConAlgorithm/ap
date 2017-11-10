package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "随机数序号", type = "number", adapter = "engine.rule.domain.adapter.RandomNumberDomainAdapter")
public class RandomNumber {

}
