package engine.rule.analyzer.service.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

class ModelTag
{
	public String modelName;
	
	public String tagName;
	
	public ModelTag(String modelName, String tagName)
	{
		this.modelName = modelName;
		this.tagName = tagName;
	}
}

public class ModelTagsList extends BasicResponse{

	private List<ModelTag> modelTags = new ArrayList<>();
	
	public ModelTagsList(Map<String, String> tagsMap)
	{
		for(Entry<String, String> item : tagsMap.entrySet())
		{
			ModelTag tag = new ModelTag(item.getKey(), item.getValue());
			modelTags.add(tag);
		}
	}

	public List<ModelTag> getModelTags() {
		return modelTags;
	}

	public void setModelTags(List<ModelTag> modelTags) {
		this.modelTags = modelTags;
	}
}
