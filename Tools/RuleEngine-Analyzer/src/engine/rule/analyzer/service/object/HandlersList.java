package engine.rule.analyzer.service.object;

import java.util.ArrayList;
import java.util.List;

public class HandlersList extends BasicResponse{

	private List<String> handlers = new ArrayList<>();
	
	public HandlersList(List<Class<?>> handlers)
	{
		String handlerPostFix = "RuleHandler";
		String name = null;
		for(Class<?> item : handlers)
		{
			name = item.getSimpleName();
			if(name.endsWith(handlerPostFix))
			{
				this.handlers.add(name.replace(handlerPostFix, ""));
			}
		}
	}
}
