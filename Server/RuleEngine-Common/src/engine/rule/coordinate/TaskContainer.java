/**
 * Created by Felton 2016年4月13日
 */
package engine.rule.coordinate;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;

/**
 * @author felton
 *
 */
public class TaskContainer {

	public Map<String, Task> taskMap = new HashMap<>();
	
	public void addTask(Class<? extends Task> taskCls, Map<String, Object> data){
		try {
			Task task = taskCls.newInstance();
			task.load(data);
			taskMap.put(task.name, task);
		} catch(Exception e){
			Logger.get().error(String.format("Can not create task: %s for %s", taskCls.getName(), new Gson().toJson(data)), e);
		}
	}
}
