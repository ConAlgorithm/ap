package engine.rule.domain.adapter;

import java.util.LinkedList;
import java.util.List;
import com.huateng.toprules.core.adapter.DynamicDomainAdapter;
import com.huateng.toprules.core.model.bean.DynamicDomain;
import com.huateng.toprules.core.model.bean.DynamicDomainAttribute;

import engine.rule.config.ModelConfig;

public class RandomNumberDomainAdapter implements DynamicDomainAdapter {

	@Override
	public DynamicDomain execute(String domainName, String adapterClass,
			Object[] params) {

		List<DynamicDomainAttribute> attributes = new LinkedList<DynamicDomainAttribute>();
		for (int i = 0; i < ModelConfig.randomNumCount; i++) {
			DynamicDomainAttribute attribute = new DynamicDomainAttribute(
					"randomNumber_" + i, "" + i, "" + i);
			attributes.add(attribute);
		}

		DynamicDomain domain = new DynamicDomain(domainName, attributes,
				TYPE.STRING.toString(), adapterClass);
		return domain;
	}

}
