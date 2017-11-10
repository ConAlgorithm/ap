/**
 * Created by Felton 2016年4月14日
 */
package engine.rule.coordinate;

import java.util.HashSet;
import java.util.Set;

import catfish.base.business.common.JobStatus;

/**
 * @author felton
 *
 */
public class DefaultStrategy implements Strategy{

	private ExecutionPlan plan = ExecutionPlan.getInstance();
	
	@Override
	public boolean isCheckKeyPointPass(Workflow flow, Message msg) {
		for(Message sent : flow.getSent())
		{
			if(sent.status != null && sent.status.equals(JobStatus.Rejected))
				return false;
		}
		return true;
	}

	@Override
	public boolean isCheckMutexPass(Workflow flow, Message msg) {
		Set<String> hit = null;
		String currentTask = msg.name;
		String hitTask = null;
		for(Set<String> mutex : plan.getMutexList())
		{
			if(mutex.contains(currentTask))
			{
				hit = mutex;
				break;
			}
		}
		if(hit != null)
		{
			for(Message sent : flow.getSent())
			{
				if(hit.contains(sent.name) && !sent.name.equals(currentTask))
				{
					hitTask = sent.name;
					break;
				}			
			}
		}
		return hitTask == null;
	}

	@Override
	public boolean isCheckFlowPass(Workflow flow, Message msg) {
		String currentTask = msg.name;
		Set<String> nextTasks = plan.getAllNextMap().get(currentTask);
		if(nextTasks == null)
		{
			return true;
		}
		for(Message sent : flow.getSent())
		{
			if(nextTasks.contains(sent.name))
			{
				return false;
			}
		}
		return true;
	}
}
