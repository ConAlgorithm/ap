package engine.rule.analyzer.service.object;

import java.util.ArrayList;
import java.util.List;

import engine.rule.analyzer.model.entity.ValueEntity;

public class ModelDomain {

	private String name;
	
	private List<Object> domain = new ArrayList<>();
	
	public ModelDomain(String domainName, List<ValueEntity> list)
	{
		name = domainName;
		for(ValueEntity item : list)
		{
			domain.add(item);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getDomain() {
		return domain;
	}

	public void setDomain(List<Object> domain) {
		this.domain = domain;
	}
}
