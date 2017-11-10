package jma.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import jma.Configuration;
import jma.JobHandler;
import jma.JobInstance;
import jma.RetryRequiredException;
import jma.handlers.CheckIdCardOnPoliceServiceHandler;
import thirdparty.config.YLZHConfiguration;

public class IdCardPoliceCheckerTest {
	public static void main(String[] args) {
	        try {
	          //{"name":"艾铭坤", "idNo":"220281198811258619",  "appId":"96e529af-553c-e611-b04c-d89d67298ea4"}
	          //{"name":"王五", "idNo":"140421199003086816", "appId":"D53B7537-563C-E611-B04C-D89D67298EA4"}
	        	//16245DDA-8EB3-E511-83C4-AC853D9F54BA
	            StartupConfig.initialize();
	            Logger.initialize();
	            Configuration.readConfiguration();
	            YLZHConfiguration.readConfiguration();
	            HttpClientConfig.initialize();
	            PersistenceConfig.initialize();
	        	
	          JobInstance job = new JobInstance("CheckIdCardOnPoliceService","16245DDA-8EB3-E511-83C4-AC853D9F54BA");
	          
	          JobHandler handler = new CheckIdCardOnPoliceServiceHandler();
//	          try {
//	        	  //handler = (JobHandler) Class.forName(canonicalClassName).newInstance();
//			} catch (InstantiationException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IllegalAccessException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	          
	          try {

	            handler.execute(job.getAppId());
	            handler.writeJobResult(true);
	            
	          } catch (RetryRequiredException e) {
	            
	          } catch (RuntimeException e) {
	          }

	        } catch (RuntimeException e) {
	          Logger.get().error("Unexpected exception occurred in Job Thread :", e);
	        }
	      }

}
