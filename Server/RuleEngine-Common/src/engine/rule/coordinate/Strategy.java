/**
 * Created by Felton 2016年4月14日
 */
package engine.rule.coordinate;

/**
 * @author felton
 *
 */
public interface Strategy {

	//检查关键点
	boolean isCheckKeyPointPass(Workflow flow, Message msg);
	//检查互斥
	boolean isCheckMutexPass(Workflow flow, Message msg);
	//检查流程合理性
	boolean isCheckFlowPass(Workflow flow, Message msg);
}
