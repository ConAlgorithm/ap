package catfish.notification;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.ons.OnsConfig;
import catfish.base.queue.QueueApi;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.sender.MessagesSender;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.google.gson.Gson;

public class TestClient {

  public static void main(String[] args) throws UnsupportedEncodingException {
    StartupConfig.initialize();
    Logger.initialize();

    Configuration.initialize();
    HttpClientConfig.initialize();
    sendFromSender();
//    sendViaHttp();
//    OnsConfig.initialize();
//    sendViaOns();
//    sendViaMqs();
  }

  static void sendFromSender() {
	  
	  MessageConfig.initialize();
	  
	  String notificationLiteral = "{\"NotificationName\":\"FreeContentMessage\",\"mobile\":\"13166223969\","
	      + " \"content\":\"招商银行上海中远两湾城支行。请您核对好户名，开户行及账号后通过网银、柜台或支付宝进行还款，转账成功后务必保留\"}";
	  
	  System.out.println(MessagesSender.handle(notificationLiteral, 0));
  }
  
  static void sendViaOns() throws UnsupportedEncodingException {
    Properties properties = new Properties();
    properties.put(PropertyKeyConst.ProducerId, Configuration.getOnsProducerId());
    properties.put(PropertyKeyConst.AccessKey, Configuration.getOnsAccessKey());
    properties.put(PropertyKeyConst.SecretKey, Configuration.getOnsSecretKey());

    Producer producer = ONSFactory.createProducer(properties);
    producer.start();

    Map<String, String> notification = CollectionUtils.mapOf(
    	MessageNotificationUtil.NotificationKeys.NOTIFICATION_NAME, "AliShortMessage",
    	MessageNotificationUtil.NotificationKeys.APP_ID, "5e0fb228-4304-e511-87d5-80db2f14945f",
    	MessageNotificationUtil.NotificationKeys.MOBILE, "13764648731",
    	MessageNotificationUtil.NotificationKeys.TEMPLATE_CODE, "WITHHOLDING",
    	MessageNotificationUtil.NotificationKeys.CONTENT_JSON, "{\"code\":\"xiaonan\"}");

    Message ms = new Message(
            Configuration.getOnsTopic(),
            notification.get(MessageNotificationUtil.NotificationKeys.NOTIFICATION_NAME),
            notification.get(MessageNotificationUtil.NotificationKeys.APP_ID),
            new Gson().toJson(notification).getBytes(Configuration.UTF_8));
    ms.setStartDeliverTime(0);
    System.out.println(producer.send(ms));

    producer.shutdown();
  }
  
  static void sendViaMqs() {
	  Map<String, String> notification = new HashMap<String, String>();
	  notification.put(MessageNotificationUtil.NotificationKeys.NOTIFICATION_NAME, "AliShortMessage");
	  notification.put(MessageNotificationUtil.NotificationKeys.MOBILE, "13764648731");
	  notification.put(MessageNotificationUtil.NotificationKeys.TEMPLATE_CODE, "PDL_SHORTMESSAGE_CUSTOMER_PdlRepaymentReminderMessageDefinition");
	  notification.put(MessageNotificationUtil.NotificationKeys.CONTENT_JSON,"{\"name\":\"洛天依\",\"month\":\"11\",\"sum\":\"6888\",\"time\":\"2015-12);01\",\"content\":\"xxx\",\"var\":\"5\"}");
	  notification.put(MessageNotificationUtil.NotificationKeys.OLD_NOTIFICATION_NAME,"DsfWithholdingOverdueSuccess");
	  notification.put(MessageNotificationUtil.NotificationKeys.APP_ID, "5e0fb228-4304-e511-87d5-80db2f14945f");
	  notification.put(MessageNotificationUtil.NotificationKeys.EXTRAINFO,"xxxx");
//		    	MessageNotificationUtil.NotificationKeys.NOTIFICATION_NAME, "AliShortMessage",
//		    	MessageNotificationUtil.NotificationKeys.MOBILE, "13764648731",
//		    	MessageNotificationUtil.NotificationKeys.TEMPLATE_CODE, "TEST",
//		    	MessageNotificationUtil.NotificationKeys.CONTENT_JSON, "{\"code\":\"xiaonan\"}");
//	  			MessageNotificationUtil.NotificationKeys.CONTENT_JSON,
//	  			"{\"name\":\"洛天依\",\"month\":\"11\",\"sum\":\"6888\",\"time\":\"2015-12-01\",\"content\":\"xxx\",\"var\":\"5\"}",
//	  			MessageNotificationUtil.NotificationKeys.OLD_NOTIFICATION_NAME,"DsfWithholdingOverdueSuccess",
//	  			MessageNotificationUtil.NotificationKeys.APP_ID, "5e0fb228-4304-e511-87d5-80db2f14945f",
//	  			MessageNotificationUtil.NotificationKeys.EXTRAINFO,"xxxx");
	  String messageBody = new Gson().toJson(notification);
	  // QueueApi.ensureQueueExist("Notification", 30L);
	  QueueApi.writeMessage("Notification", messageBody, 1, 0);
  }

  static void sendViaHttp() {
    System.out.println(HttpClientApi.postJson(
        String.format(
            "http://%s:%d/notification.action",
            Configuration.getHttpHost(),
            Configuration.getHttpPort()),
        CollectionUtils.mapOf(
            NotificationKeys.NOTIFICATION_NAME, "SendValidationCodeFromApp",
            "ExtraInfo", "13764648731",
//            NotificationKeys.APP_ID, "06b00e19-c11a-4735-a780-c5596404ea3a"),
            "UserId","662F6C1A-BEEC-E411-87D5-80DB2F14945F"))
            
    		);
  }
  
}
