package catfish.flowcontroller;

import java.util.*;

import catfish.flowcontroller.activities.Activity;
import static catfish.flowcontroller.util.CommonUtils.APP;
import static catfish.flowcontroller.util.CommonUtils.PDL;
import static catfish.flowcontroller.util.CommonUtils.PDLM;
import static catfish.flowcontroller.util.CommonUtils.CL;
import static catfish.flowcontroller.util.CommonUtils.BATCHJOBS;

public class DefaultWorkflowProvider implements WorkflowProvider{
    private WorkFlow defaultWorkflow;
    private Map<String, WorkFlow> workflowMap = new HashMap<String, WorkFlow>();

    public static DefaultWorkflowProvider instance = new DefaultWorkflowProvider();

    private DefaultWorkflowProvider(){
        init();
    }

    private void init(){
        String[] workflowNames = new String[]{APP, PDL, PDLM, CL, BATCHJOBS};
        for(String workflowName: workflowNames){
            List<Activity> activities = ActivityBuilder.load(workflowName);
            WorkFlow workflow = WorkFlowFactory.createWorkflow(workflowName, activities);
            workflowMap.put(workflowName, workflow);
        }

        defaultWorkflow = workflowMap.get(APP);
    }

    @Override
    public WorkFlow get(String flowName){
        if(workflowMap.containsKey(flowName)){
            return workflowMap.get(flowName);
        }
        return defaultWorkflow;
    }

    @Override
    public WorkFlow getByAppId(String appId){
        return defaultWorkflow;
    }
}
