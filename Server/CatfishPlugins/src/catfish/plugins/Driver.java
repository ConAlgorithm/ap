package catfish.plugins;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;
import catfish.bonuspoints.BonusPointsPlugin;
import catfish.framework.IPlugin;
import catfish.plugins.rest.RestPlugin;
import catfish.plugins.finance.ActivityFinancePlugin;
//import catfish.plugins.finance.ActivityFinancePlugin_v1;
import catfish.plugins.luckdraw.ActivityLuckDrawPlugin;
import catfish.plugins.pdfgenerator.PDFGeneratorHttpPlugin;
import catfish.plugins.pdfgenerator.PDFGeneratorPlugin;
import catfish.plugins.redpack.ActivityWeixinRedPackPlugin;
import catfish.plugins.sales.SalesPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
	public static void main(String[] args) {

	    HttpClientConfig.initialize();

	    Server server = Server.Create();
		ServerConfig serverConfig = new ServerConfig();

		IPlugin[] plugins = new IPlugin[] {
			    new ActivityWeixinRedPackPlugin(),
			    new PDFGeneratorPlugin(),
			    new ActivityLuckDrawPlugin(),
			    new PDFGeneratorHttpPlugin(),
			    new RestPlugin(),
			    new BonusPointsPlugin(),
			    new ActivityFinancePlugin(),
//			    new ActivityFinancePlugin_v1(),
			    new SalesPlugin()
		};

		Logger.get().info("Catfish Plugin Server is running ...");;
		server.run(serverConfig, plugins, new ServiceFactory());
		
		
//		IServiceProvider serverprovider=server.getServiceProvider();
//		IQueueService ms = serverprovider.getService(IQueueService.class);
//		QueueMessager qm = new QueueMessager("B6854100-C477-E511-A974-AC853D9F545E","GeneratePDF");
		//ms.postMessage("AppCompleted", qm.toString());

//		QueueApi.writeMessager("GeneratePDFQueue", qm);
	}
}
