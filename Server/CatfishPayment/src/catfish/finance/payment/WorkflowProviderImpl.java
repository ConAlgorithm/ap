package catfish.finance.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.finance.payment.api.PaymentController;
import catfish.flowcontroller.WorkFlow;
import catfish.flowcontroller.WorkflowProvider;
import catfish.framework.IServiceProvider;

public class WorkflowProviderImpl implements WorkflowProvider{
    private Map<String, WorkFlow> workflowMap = new HashMap<String, WorkFlow>();
    private WorkflowStorage workflowStorage = new WorkflowStorage();
    private PaymentController controller;
    private IServiceProvider serviceProvider;
    
    public WorkflowProviderImpl(){
        
    }
    
    public void init(PaymentController controller, IServiceProvider serviceProvider){
        this.controller = controller;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public WorkFlow get(String flowName) {
        List<HashMap<String, Object>> activityConfigs = workflowStorage.load(flowName);
        return WorkflowBuilder.build(flowName, activityConfigs);
    }

    @Override
    public WorkFlow getByAppId(String appId) {
        if(workflowMap.containsKey(appId)){
            return workflowMap.get(appId);
        } else {
            //PaymentChannelProvider channelProvider = new PaymentChannelProvider(controller, serviceProvider);
            PaymentChannelProvider channelProvider = serviceProvider.getService(PaymentChannelProvider.class);
            List<HashMap<String, Object>> activityConfigs = WorkflowBuilder.build(channelProvider.getPaymentChannels());
            workflowStorage.save(appId, activityConfigs);
            
            WorkFlow workflow = WorkflowBuilder.build(appId, activityConfigs);
            workflowMap.put(appId, workflow);
            return workflow;
        }
    }

}
