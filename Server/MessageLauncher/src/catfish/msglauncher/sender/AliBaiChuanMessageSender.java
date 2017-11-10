package catfish.msglauncher.sender;

import java.util.Map;

import com.google.gson.Gson;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.OpenSmsSendmsgRequest;
import com.taobao.api.request.OpenSmsSendmsgRequest.SendMessageRequest;
import com.taobao.api.response.OpenSmsSendmsgResponse;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.queue.QueueApi;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;
import catfish.msglauncher.util.MongoDBApi;

public class AliBaiChuanMessageSender implements MessageSender {
	public static TaobaoClient client;
	
	static{
		client = new DefaultTaobaoClient(
				Configuration.getAlibaichuanUrl(),
				Configuration.getAlibaichuanAppKey(),
				Configuration.getAlibaichuanSecret());
	}
	
	@Override
	public void send(Message baseMessage) {
		if (!isAvailable()){
			throw new RuntimeException("Ali Baichuan is closed.");
		}

		ShortMessage message = (ShortMessage) baseMessage;
		if (null == message.getTemplateCode() || null == message.getTemplateId()){
			throw new RuntimeException("Not Ali Short Message");
		}
		
		OpenSmsSendmsgRequest req = new OpenSmsSendmsgRequest();
		SendMessageRequest obj117445 = new SendMessageRequest();
		obj117445.setTemplateId(Long.valueOf(message.getTemplateId()));
		obj117445.setSignatureId(Long.valueOf(Configuration.getAlibaichuanSignId()));
		obj117445.setContextString(message.getContentJson());
		obj117445.setMobile(message.getMobile());
		req.setSendMessageRequest(obj117445);
		OpenSmsSendmsgResponse rsp;
		try {
			rsp = client.execute(req);
			Logger.get().info(rsp.getBody());
			MongoDBApi.logShortMessage(message, rsp.getBody());
			if (rsp.getResult().getSuccessful() == false) {
				throw new RuntimeException("Got Ali Baichuan error " + rsp.getBody());
			}

		} catch (ApiException e) {
			throw new RuntimeException(e);
		}

	}
	
//	public void retryViaMqs(AliShortMessage message){
//		Map<String, String> notification = CollectionUtils.mapOf(
//		    	MessageNotificationUtil.NotificationKeys.NOTIFICATION_NAME, "AliShortMessage",
//		    	MessageNotificationUtil.NotificationKeys.MOBILE, message.getMobile(),
//		    	MessageNotificationUtil.NotificationKeys.TEMPLATE_ID, message.getTemplateId(),
//		    	MessageNotificationUtil.NotificationKeys.CONTENT_JSON, message.getContent(),
//		    	MessageNotificationUtil.NotificationKeys.RETRYTIMES, String.valueOf(message.getRetryTimes()+1));
//		String messageBody = new Gson().toJson(notification);
//		QueueApi.writeMessage("Notification", messageBody, 1, 0);
//	}
	
//	public void redirectMessageViaMqs(AliShortMessage message){
//		Map<String, String> notification = CollectionUtils.mapOf(
//		    	MessageNotificationUtil.NotificationKeys.NOTIFICATION_NAME, message.getOldNotificationName(),
//		    	MessageNotificationUtil.NotificationKeys.APP_ID, message.getAppId(),
//		    	MessageNotificationUtil.NotificationKeys.EXTRAINFO, message.getExtraInfo());
//		String messageBody = new Gson().toJson(notification);
//		QueueApi.writeMessage("Notification", messageBody, 1, 0);
//	}
	
	private boolean isAvailable(){
		return DynamicConfig.readAsBool("alibaichuanSwitchOn");
	}

}
