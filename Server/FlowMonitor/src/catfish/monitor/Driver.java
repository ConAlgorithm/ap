package catfish.monitor;

import java.io.FileNotFoundException;
import java.util.List;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.framework.IPlugin;
import catfish.servers.Server;

public class Driver {

    public static void main(String[] args) {
        Server server = Server.Create();

        HttpClientConfig.initialize();
        
        String flowControllerHost = StartupConfig.get("flowcontroller.rest.host");
        int port = StartupConfig.getAsInt("flowcontroller.rest.port");
        String url = String.format("http://%s:%s/overview/app", flowControllerHost, port);
        int intervalMinutes = StartupConfig.getAsInt("flowmonitor.refresh.intervalMinutes",1);
        //int maxMinutes = StartupConfig.getAsInt("flowmonitor.activity.maxMinutes");
        //List<String> ignoredActivities = StartupConfig.getAsList("flowmonitor.activity.ignored");
        
        try {
            Config config= Config.load("app");
            IPlugin[] plugins = new IPlugin[] {
                    new MonitorPlugin(url, intervalMinutes, config)
            };
            
            Logger.get().info("Catfish monitor Server is running ...");
            server.run(plugins);
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
}
