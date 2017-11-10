/**
 * Created by Felton 2016年4月12日
 */
package engine.rule.coordinate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author felton
 *
 */
public class TaskManager {

	private Map<String, Class<? extends Task>> taskDefineMap = new HashMap<>();
	private TaskContainer container = new TaskContainer();
	
	public TaskManager(){
		taskDefineMap.put("decision", DecisionTask.class);
		taskDefineMap.put("leaf", LeafTask.class);
		taskDefineMap.put("split", SplitTask.class);
		taskDefineMap.put("mutex", MutexTask.class);
	}
	
	private void initTask(Reader flowReader)
	{
		Map<String, Object> flow = new Gson().fromJson(flowReader, new TypeToken<Map<String, Object>>() { }.getType());
		List<Map<String, Object>> tasks = (List<Map<String, Object>>) flow.get("tasks");
		for(Map<String, Object> item : tasks)
		{
			String taskType = item.get(Task.TYPE).toString();
			Class taskCls = taskDefineMap.get(taskType);
			if(taskCls == null)
			{
				Logger.get().error("Do not exist task of type: " + taskType);
			}
			container.addTask(taskCls, item);
		}
	}

	public TaskContainer getContainer() {
		return container;
	}

	public void loadTasksByFile(String flowName)
	{
		String file = "config/" + flowName + ".flow"; 
		try {
			initTask(new FileReader(file));
		} catch (FileNotFoundException e) {
			Logger.get().error("Cannot read flow file: " + file + ", Server will shutdown!");
			System.exit(0);
		}
	}
}
