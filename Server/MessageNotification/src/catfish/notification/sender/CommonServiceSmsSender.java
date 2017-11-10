package catfish.notification.sender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Message;
import catfish.notification.Configuration;
import catfish.notification.message.ShortMessage;
import catfish.notification.message.SmsMessage;

public class CommonServiceSmsSender {
	
	private static String singleSend(SmsMessage sms) {

		String requestId = sms.getRequestId();
		String mobile = sms.getMobile();
		
		String url = Configuration.getCommonServiceSmsUrl() + "/single-sender";
		Logger.get().info("begin request for send message with requestId:" + requestId + " with mobile:" + sms);
		Map<String, ?> params = CollectionUtils.mapOf(
        "requestId", requestId,
        "mobile", mobile,
        "content", sms.getContent(),
        "sign", sms.getSign(),
        "source", sms.getSource());
		
		try {
			Map<String, Object> result = HttpClientApi.postJson(url, params);
			if (result == null || !result.containsKey("msgId")) {
				Logger.get().warn
				("end send message with requestId:" + requestId + " with mobile:" + mobile + " error result return" + result);
				
				return null;
			}
			String msgId = result.get("msgId").toString();
			Logger.get().info("end send message with requestId:" + requestId + " with mobile:" + mobile + " success.msgId=" + msgId);
			return msgId;
			
		} catch (Exception e) {
			Logger.get().warn("end send message with requestId:" + requestId + " with body:" + params + " error", e);
			return null;
		}
	}
	
	private static String singleVoiceSend(SmsMessage sms) {

		String requestId = sms.getRequestId();
		String mobile = sms.getMobile();
		
		String url = Configuration.getCommonServiceSmsUrl() + "/voice-sender/code";
		Logger.get().info("begin request for send message with requestId:" + requestId + " with mobile:" + sms);
		Map<String, ?> params = CollectionUtils.mapOf(
        "requestId", requestId,
        "mobile", mobile,
        "code", sms.getContent(),
        "source", sms.getSource());
		
		try {
			Map<String, Object> result = HttpClientApi.postJson(url, params);
			if (result == null || !result.containsKey("msgId")) {
				Logger.get().warn
				("end send message with requestId:" + requestId + " with mobile:" + mobile + " error result return" + result);
				
				return null;
			}
			String msgId = result.get("msgId").toString();
			Logger.get().info("end send message with requestId:" + requestId + " with mobile:" + mobile + " success.msgId=" + msgId);
			return msgId;
			
		} catch (Exception e) {
			Logger.get().warn("end send message with requestId:" + requestId + " with body:" + params + " error", e);
			return null;
		}
	}

	public static List<Message> sendSingleTextMessage(SmsMessage message) {
		
		if (Configuration.getCommonServiceSwitchOn()){
			
			singleSend(message);
			return new ArrayList<>();
		} else {
			
			ShortMessage shortMessage = new ShortMessage(message.getReceivedMessage(), message.getMobile(), message.getContent());
			shortMessage.setSign(message.getSign());
			return  Arrays.<Message> asList(shortMessage);
		}
	}
	
    public static List<Message> sendSingleVoiceMessage(SmsMessage message) {
		
		if (Configuration.getCommonServiceSwitchOn()){
			
			singleVoiceSend(message);
			return new ArrayList<>();
		} else {
			
			ShortMessage shortMessage = new ShortMessage(message.getReceivedMessage(), message.getMobile(), message.getContent());
			shortMessage.setSign(message.getSign());
			return  Arrays.<Message> asList(shortMessage);
		}
	}
}
