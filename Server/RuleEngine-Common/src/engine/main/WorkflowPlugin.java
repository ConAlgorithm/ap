/**
 * Created by Felton 2016年4月12日
 */
package engine.main;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import engine.rule.coordinate.ExecutionPlan;

/**
 * @author felton
 *
 */
public class WorkflowPlugin implements IPlugin{

	@Override
	public void init(IServiceProvider sp) {
		ExecutionPlan plan = ExecutionPlan.getInstance();
		plan.initFlowByFile("pos");
	}
}
