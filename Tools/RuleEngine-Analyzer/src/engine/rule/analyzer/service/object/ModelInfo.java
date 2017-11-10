package engine.rule.analyzer.service.object;

import engine.rule.analyzer.model.entity.Entity;

public class ModelInfo extends BasicResponse{

    private String model;
	
	public ModelInfo(Entity entity)
	{
		this.model = entity.toJson().toString();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
