/**
 * Created by Felton 2016年4月12日
 */
package engine.rule.coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import catfish.base.Logger;

/**
 * @author felton
 *
 */
public class ExecutionPlan {

	//记录task的直接前导tasks
	private Map<String, Set<String>> directPreviousMap = new HashMap<>();
	//记录task的直接后继tasks
	private Map<String, Set<String>> directNextMap = new HashMap<>();
	//流程图
	private Map<String, Set<String>> workFlows = new HashMap<>();
	//记录互斥节点
	private List<Set<String>> mutexList = new ArrayList<>();
	//记录task的所有后继tasks
	private Map<String, Set<String>> allPreviousMap = new HashMap<>();
	//记录task的所有前导tasks
	private Map<String, Set<String>> allNextMap = new HashMap<>();
	
	private TaskManager manager = new TaskManager();
	
	private static ExecutionPlan instance = new ExecutionPlan();
	
	public static ExecutionPlan getInstance()
	{
		return instance;
	}
	public void initFlowByFile(String flowName)
	{
		manager.loadTasksByFile(flowName);
		createFlows(manager.getContainer().taskMap);
	}
	
	private void createFlows(Map<String, Task> tasks)
	{
		int flowNumber = 1;
		Set<String> taskNames = tasks.keySet();
		for(String name : taskNames)
		{
			Task task = tasks.get(name);
			addPreviousTask(task);
			addNext(task);
			addMutex(task);
		}
		//TODO
		//生成流程图
		initAllNextTasks();
		initAllPreviousTasks();
	}
	private void addPreviousTask(Task task)
	{
		//首节点的前导为空set，便于一致性处理
		if(!directPreviousMap.containsKey(task.name))
		{
			directPreviousMap.put(task.name, new HashSet<String>());
		}
		for(String next : task.next)
		{
			if(!directPreviousMap.containsKey(next))
			{
				directPreviousMap.put(next, new HashSet<String>());
			}
			directPreviousMap.get(next).add(task.name);
		}
	}
	private void addNext(Task task)
	{
		//尾节点的后继为空set，便于一致性处理
		if(!directNextMap.containsKey(task.name))
		{
			directNextMap.put(task.name, task.next);
		}
	}
	private void addMutex(Task task)
	{
		if(task instanceof SplitTask)
		{
			mutexList.add(task.next);
		}
	}
	private void initAllNextTasks()
	{
		Set<String> names = this.directNextMap.keySet();
		for(String name : names)
		{
			allNextMap.put(name, getLinkedTasks(name, directNextMap));
		}
	}

	private void initAllPreviousTasks()
	{
		Set<String> names = this.directPreviousMap.keySet();
		for(String name : names)
		{
			allPreviousMap.put(name, getLinkedTasks(name, directPreviousMap));
		}
	}
	 
	private Set<String> getLinkedTasks(String taskName, Map<String, Set<String>> container)
	{
		Set<String> linkedTasks = container.get(taskName);
		Set<String> tempNexts = new HashSet<>();
		for(String next : linkedTasks)
		{
			tempNexts.addAll(getLinkedTasks(next, container));
		}
		linkedTasks.addAll(tempNexts);
		return linkedTasks;
	}
	public Map<String, Set<String>> getDirectPreviousMap() {
		return directPreviousMap;
	}
	public Map<String, Set<String>> getDirectNextMap() {
		return directNextMap;
	}
	public Map<String, Set<String>> getWorkFlows() {
		return workFlows;
	}
	public List<Set<String>> getMutexList() {
		return mutexList;
	}
	public Map<String, Set<String>> getAllPreviousMap() {
		return allPreviousMap;
	}
	public Map<String, Set<String>> getAllNextMap() {
		return allNextMap;
	}
	public TaskManager getManager() {
		return manager;
	}
}
