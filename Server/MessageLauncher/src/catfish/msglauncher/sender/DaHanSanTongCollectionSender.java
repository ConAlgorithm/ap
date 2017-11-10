package catfish.msglauncher.sender;

import java.util.HashMap;
import java.util.Map;

import catfish.base.DynamicConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;

public class DaHanSanTongCollectionSender implements MessageSender {

	private static final String URL = "http://wt.3tong.net/json/sms/Submit";
	private static final String EXPECTED_CODE = "0";

	@Override
	public void send(Message baseMessage) {
		if (!isAvailable()) {
			throw new RuntimeException("DaHanSanTong is closed.");
		}

		ShortMessage message = (ShortMessage) baseMessage;

		Map<String, Object> requestContent = new HashMap<>();
		requestContent.put("account", Configuration.getDaHanSanTongShortMsgAccount());
		requestContent.put("password", Configuration.getDaHanSanTongShortMsgPassword());
		requestContent.put("phones", message.getMobile());
		requestContent.put("content", message.getContent());
		requestContent.put("sign", Configuration.getDaHanSanTongShortMsgSign());
		requestContent.put("msgmode", "0");

		Map<String, Object> response = HttpClientApi.postJson(URL, requestContent);

		String statusCode = (String) response.get("result");
		if (!EXPECTED_CODE.equals(statusCode)) {
			throw new RuntimeException(new StringBuffer("Got DaHanSanTong error ").append(statusCode).append(",").append((String)response.get("desc")).toString());
		}
	}

	private boolean isAvailable() {
		return DynamicConfig.readAsBool("daHanSanTongShortMsgSwitchOn");
	}
	
}
