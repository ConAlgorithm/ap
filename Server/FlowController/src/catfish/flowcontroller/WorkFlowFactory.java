package catfish.flowcontroller;

import java.util.List;

import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.ActivityRepository;

public abstract class WorkFlowFactory {
    public static WorkFlow createWorkflow(String name, List<Activity> activities){
        ActivityRepository repository = new ActivityRepository();
        for(Activity activity: activities){
            repository.register(activity.name, activity);
        }
        ExecutionPlan plan = new ExecutionPlan(repository);
        WorkFlow workFlow = new WorkFlow(name);
        workFlow.init(plan, repository);
        return workFlow;
    }
}
