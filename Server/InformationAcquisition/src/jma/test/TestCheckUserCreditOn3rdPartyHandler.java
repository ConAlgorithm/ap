package jma.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;

import jma.Configuration;
import thirdparty.config.QhzxConfiguration;
import thirdparty.qhzx.DomainResult;
import thirdparty.qhzx.QhzxApi;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.queue.QueueApi;

public class TestCheckUserCreditOn3rdPartyHandler {

	public static void main(String[] args) {
		StartupConfig.initialize();
		Logger.initialize();
		Configuration.readConfiguration();
		HttpClientConfig.initialize();
		QhzxConfiguration.initialize();

//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		while (true) {
//	    String s = null;
//      try {
//        s = reader.readLine();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//	    if (StringUtils.isNullOrWhiteSpaces(s)) {
//	      break;
//	    }
//	    try {
//        String[] line = s.split(",");
//        if (Arrays.asList(args).contains("--query")) {
//          query(line);
//        }
//        if (Arrays.asList(args).contains("--report")) {
//          report(line);
//        }
//      } catch (RuntimeException e) {
//	      e.printStackTrace();
//      }
//    }
		
		sendViaMqs();
  }

  private static void query(String[] line) {
    DomainResult result = QhzxApi.query(UUID.randomUUID().toString(), line[0], line[1],true);
    System.out.println("RAW " + result.getRawResult());
    System.out.println(line[0] + "," + line[1] + "," + result.toString());
	}

  private static void report(String[] line) {
    System.out.println(line[0] + "," + line[1] + "," + QhzxApi.report(UUID.randomUUID().toString(),
        line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]));
  }
  
  static void sendViaMqs() {
	  Map<String, String> iaMessage = new HashMap<String, String>();
//	  iaMessage.put("jobType", "CheckUserCreditOn3rdPartyNew");
	  iaMessage.put("appId", "825BC5AB-AE5A-E511-87D5-80DB2F14945F");
	  iaMessage.put("jobName", "CheckUserCreditOn3rdParty");
	  
	  String messageBody = new Gson().toJson(iaMessage);
	  //QueueApi.ensureQueueExist("JobRequestQueue", 30L);
	  QueueApi.writeMessage("JobRequestQueue", messageBody, 1, 0);
  }
  
}
