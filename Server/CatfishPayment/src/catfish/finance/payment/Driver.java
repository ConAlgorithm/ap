package catfish.finance.payment;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.flowcontroller.FlowControllerPlugin;
import catfish.framework.IPlugin;
import catfish.servers.Server;

public class Driver {
    public static void main(String[] args) {
        Server server = Server.Create();
        
        int executorCount = StartupConfig.getAsInt("catfish.flowcontroller.executor.count");
        ControllerPlugin flowControllerPlugin = new ControllerPlugin(executorCount,"autopaymentController", "appautopayment",
                new String[] {"AutoPaymentController"},
                new WorkflowProviderImpl(), new FlowStatusStorage());
        IPlugin[] plugins = new IPlugin[] {
                flowControllerPlugin,
                new PaymentPlugin(flowControllerPlugin)
                };

        Logger.get().info("Flow controller Server is running ...");
        server.run(plugins);
        
    }
}
