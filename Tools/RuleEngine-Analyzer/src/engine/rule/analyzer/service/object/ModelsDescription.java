package engine.rule.analyzer.service.object;

import java.util.Map;

public class ModelsDescription extends BasicResponse{

	private Map<String, String> modelDescs;
	
	public ModelsDescription(Map<String, String> modelDescs)
	{
		this.modelDescs = modelDescs;
	}

	public Map<String, String> getModelDescs() {
		return modelDescs;
	}

	public void setModelDescs(Map<String, String> modelDescs) {
		this.modelDescs = modelDescs;
	}
}
