package catfish.notification.message;

import java.util.Map;
import java.util.UUID;

import catfish.notification.Message;

public class SmsMessage extends Message {
	
	private String requestId;
	
	private String mobile;
	
	private String content;
	
	private String sign;
	
	private String source;

	public SmsMessage(Map<String, ?> receivedMessage) {
		super(receivedMessage);
		// TODO Auto-generated constructor stub
	}
	
	public SmsMessage(Map<String, ?> receivedMessage, String mobile, String content, String sign, String source) {
		super(receivedMessage);
		this.requestId = UUID.randomUUID().toString();
		this.mobile = mobile;
		this.content = content;
		this.sign = sign;
		this.source = source;
	}

	public static SmsMessage fromShortMessage(ShortMessage message) {
	  SmsMessage sms = new SmsMessage(message.getReceivedMessage());
	  sms.setReceivedMessage(message.getReceivedMessage());
	  sms.setMobile(message.getMobile());
	  sms.setContent(message.getContent());
	  sms.setSign(message.getSign());
	  sms.setRequestId(UUID.randomUUID().toString());
	  return sms;
	}
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
