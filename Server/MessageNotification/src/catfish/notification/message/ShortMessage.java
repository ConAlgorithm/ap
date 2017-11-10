package catfish.notification.message;

import java.util.Map;

import catfish.notification.Message;

public class ShortMessage extends Message {
	private String mobile;
	private String content;
	
	private int provider;
	private String sign;

	// to adapte ali short message
	private String templateId;
	private String templateCode;
	private String contentJson;

	public ShortMessage(Map<String, ?> receivedMessage, String mobile,
			String content) {
		super(receivedMessage);

		this.mobile = mobile;
		this.content = content;
	}
	
	public ShortMessage(Map<String, ?> receivedMessage, String mobile,
			String content, int provider) {
		this(receivedMessage, mobile, content);

		this.provider = provider;
	}

	public String getMobile() {
		return mobile;
	}

	public String getContent() {
		return content;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getContentJson() {
		return contentJson;
	}

	public void setContentJson(String contentJson) {
		this.contentJson = contentJson;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return String.format("Short message for %s, to %s", super.toString(),
				mobile);
	}
}
