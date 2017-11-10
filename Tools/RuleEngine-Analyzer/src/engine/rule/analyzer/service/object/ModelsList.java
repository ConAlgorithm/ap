package engine.rule.analyzer.service.object;

import java.util.ArrayList;
import java.util.List;

public class ModelsList extends BasicResponse{

	private List<String> modelNames = new ArrayList<>();
	
	public ModelsList(List<Class<?>> clsList)
	{
		for(Class item : clsList)
		{
			modelNames.add(item.getSimpleName());
		}		
	}
}
