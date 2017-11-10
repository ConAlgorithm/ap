package instinct.service.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.queue.QueueApi;
import catfish.base.queue.QueueMessager;


public final class TestQueueApi 
{
	private TestQueueApi()
	{
		
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		StartupConfig.initialize();
		Logger.initialize();
		Map<String, String> iaMessage = new HashMap<String, String>();
		iaMessage.put("appId", "6CF93FBD-A582-E511-ACD8-AC853D9F5508");
		iaMessage.put("jobName", "CheckInstinctAntiFraudFinal");
	    iaMessage.put("jobStatus", "InstinctCallBack");
	    iaMessage.put("handle", "Kss");
	    String messageBody = new Gson().toJson(iaMessage);
//	    QueueApi.ensureQueueExist("JobRequestQueue", 30L);
	    QueueApi.writeMessage("JobRequestQueue", messageBody, 1, 0);
	    
	    while (true)
	    {
	    	QueueMessager qm = QueueApi.consumeMessager("JobRequestQueue");
	    	System.out.println(qm.toString());
	    	Thread.sleep(10 * 10 * 10);
	    }
//	    System.exit(0);
	}
}
