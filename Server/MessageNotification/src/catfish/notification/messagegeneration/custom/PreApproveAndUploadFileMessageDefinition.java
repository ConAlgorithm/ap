package catfish.notification.messagegeneration.custom;

import java.util.List;
import java.util.Map;

import catfish.base.business.common.WordsTemplate;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.UploadFileApi;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Receiver;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.SimpleMessageDefinition;

public class PreApproveAndUploadFileMessageDefinition implements MessageDefinition{

	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
		String appId = (String)receivedMessage.get(NotificationKeys.APP_ID);
		int uploadStatus = InstallmentApplicationDao.getAppUploadStatusById(appId);
		StringBuilder builder = new StringBuilder(WordsTemplate.WordsForPreApproved);
		List<String> words = UploadFileApi.getWeixinSendWordsByUploadFileStatus(uploadStatus, appId);
		for(String item : words)
		{
			builder.append(item);
		}
		return new SimpleMessageDefinition(Receiver.CUSTOMER, null, builder.toString()).apply(receivedMessage);
	}
}
