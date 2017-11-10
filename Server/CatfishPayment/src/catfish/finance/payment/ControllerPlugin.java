package catfish.finance.payment;

import catfish.flowcontroller.FlowControllerPlugin;
import catfish.flowcontroller.storage.Storage;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;

public class ControllerPlugin extends FlowControllerPlugin{
    private WorkflowProviderImpl workflowProviderImpl;
    private PaymentControllerImpl paymentController;

    public ControllerPlugin(int executorCount, String name, String dataType,
            String[] inputQueues, WorkflowProviderImpl workflowProvider,
            Storage storage) {
        super(executorCount, name, dataType, inputQueues, workflowProvider, storage);
        this.workflowProviderImpl = workflowProvider;
        this.paymentController = new PaymentControllerImpl(this);
    }

    @Override
    public void init(IServiceProvider sp) {
        super.init(sp);
        
        IServiceRegister sr = (IServiceRegister)sp;
        sr.register(PaymentChannelProvider.class, new PaymentChannelProvider(paymentController, sp));
        
        this.workflowProviderImpl.init(paymentController, sp);
    }
}
