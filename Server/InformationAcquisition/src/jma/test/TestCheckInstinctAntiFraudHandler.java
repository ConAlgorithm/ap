package jma.test;

import instinct.service.model.InstinctResult;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import core.InstinctOnlineJudge;
import jma.Configuration;
import jma.RetryRequiredException;
import jma.handlers.AbstractCheckInstinctAntiFraudHandler;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.queue.QueueApi;

public class TestCheckInstinctAntiFraudHandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartupConfig.initialize();
		Logger.initialize();
		Configuration.readConfiguration();
		HttpClientConfig.initialize();
		
		sendViaMqs();
		
	}
	
	
	static void sendViaMqs() {
		Map<String, String> iaMessage = new HashMap<String, String>();
		iaMessage.put("appId", "6CF93FBD-A582-E511-ACD8-AC853D9F5508");
		iaMessage.put("jobName", "CheckInstinctAntiFraudFinal");
//		iaMessage.put("jobStatus", "InstinctCallBack");
//		iaMessage.put("handle", "K");
		  
		String messageBody = new Gson().toJson(iaMessage);
		//QueueApi.ensureQueueExist("JobRequestQueue", 30L);
		QueueApi.writeMessage("JobRequestQueue", messageBody, 1, 0);
	}

}
