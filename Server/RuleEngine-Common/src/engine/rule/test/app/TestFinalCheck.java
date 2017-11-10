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
import engine.rule.model.creator.app.AppStoreInfoModelCreator;
import engine.util.ApplicationHelper;

public class TestFinalCheck {

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
	      String appId = "5d01aa1b-03ee-e311-b348-e0db5516d568​";
		  QueueMessager messager = new QueueMessager(appId, "SigningCheck", 0); 
		  Object result = RuleHandlerConfigManager.getRuleHandler(messager.getJobName(), 1).handle
		  (messager); 
		  //System.out.println(result);
		}	  
		catch(Exception e) 
		{ 
			Logger.get().error(e); 
	    }
	}
}
