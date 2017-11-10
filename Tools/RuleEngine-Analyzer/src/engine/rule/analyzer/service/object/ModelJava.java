package engine.rule.analyzer.service.object;

import engine.rule.analyzer.model.entity.Entity;

public class ModelJava extends BasicResponse{

	private String model;
	
	public ModelJava(Entity entity)
	{
		this.model = entity.template();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
