/**
 * Created by Felton 2016年4月12日
 */
package engine.rule.coordinate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author felton
 *
 */
public abstract class Task {

	protected static final String NAME = "name";
	protected static final String TYPE = "type";
	protected static final String NEXT = "next";
	
	public String name;
	public String type;
	public Set<String> next;
	
	public void load(Map<String, Object> task)
	{
		this.name = task.get(NAME).toString();
		this.type = task.get(TYPE).toString();
		Object nexts = task.get(NEXT);
		Set<String> nextSet = new HashSet<>();
		if(nexts != null)
		{
			nextSet.addAll((List<String>)nexts);
		}	
		this.next = nextSet;
		initByCustom(task);
	}
	
	public abstract void initByCustom(Map<String, Object> task);
	
}
