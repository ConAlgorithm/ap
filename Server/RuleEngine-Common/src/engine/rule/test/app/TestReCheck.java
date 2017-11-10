package engine.rule.test.app;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.database.DatabaseConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.ons.OnsConfig;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;
import engine.main.Configuration;
import engine.rule.RuleHandlerConfigManager;

public class TestReCheck {

	public static void main(String[] args) {
		StartupConfig.initialize();
		Logger.initialize();
		QueueConfig.initialize();
		DatabaseConfig.initialize();
		Configuration.initialize();
		OnsConfig.initialize();
		HttpClientConfig.initialize();
		Configuration.initialize();
				
		try{ 
	      String appId = "e4109845-bbec-e511-909c-d89d672b352b";
		  QueueMessager messager = new QueueMessager(appId, "ReCheck", 0); 
		  //String appId = messager.getAppId();
          InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
		  Object result = RuleHandlerConfigManager.getRuleHandler(messager.getJobName(), app.InstalmentChannel).handle
		  (messager); 
		  System.out.println(result);
		}	  
		catch(Exception e) 
		{ 
			Logger.get().error(e); 
	    }
	}
}
