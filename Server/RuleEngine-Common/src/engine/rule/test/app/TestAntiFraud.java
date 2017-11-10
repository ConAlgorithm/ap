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

public class TestAntiFraud {

	public static void main(String[] args)
	{
		StartupConfig.initialize();
		Logger.initialize();
		QueueConfig.initialize();
		DatabaseConfig.initialize();
		Configuration.initialize();
		OnsConfig.initialize();
		HttpClientConfig.initialize();
		Configuration.initialize();
				
		try{ 
	      String appId = "11fe4d74-af87-e511-98e3-ac853da49bec";
		  QueueMessager messager = new QueueMessager(appId, "AntiFraudCheck", 0); 
          InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
		  Object result = RuleHandlerConfigManager.getRuleHandler(messager.getJobName(), app.InstalmentChannel).handle
		  (messager); 
		  System.out.println(result);
		}	  
		catch(Exception e) 
		{ 
			Logger.get().error(e); 
	    }
		
		/* try { 
			  String appId = "0eb11d2a-88ff-e411-83c4-ac853d9f54ba"; 
			  String result = HttpClientApi.post( String.format( "http://%s:%d/",
			  "121.41.60.158", 9001), new StringEntity(new
			  Gson().toJson(CollectionUtils.mapOf("appId",appId, "jobName",
			  "PreCheck")), Configuration.UTF_8));
			  System.out.println(result); 
		  } catch (Exception e) 
		  { // TODO
		      e.printStackTrace(); 
		 }*/
	
	}
}
