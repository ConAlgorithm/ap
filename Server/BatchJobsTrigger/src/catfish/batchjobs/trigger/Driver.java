package catfish.batchjobs.trigger;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
    public static void main(String[] args) {
        Server server = Server.Create();

        IPlugin[] plugins = new IPlugin[] {
                new Plugin()
                };

        Logger.get().info("Batch Job Trigger is running ...");
        server.run(plugins);
    }
}
