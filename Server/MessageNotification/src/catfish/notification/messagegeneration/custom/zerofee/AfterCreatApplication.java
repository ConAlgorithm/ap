package catfish.notification.messagegeneration.custom.zerofee;

import grasscarp.application.model.POSApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;






import catfish.base.DateTimeUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.InstalmentChannel;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.MessageDataRequestApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;

public class AfterCreatApplication implements MessageDefinition {

	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
		String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);

		Map<String, String> appSalesInfo = MessageDataRequestApi.getAppSalesInfo(appId);
		
		POSApplication app = null;
		try {
		  
		  app = (POSApplication)MessageDataRequestApi.getApp(appId, InstalmentChannel.App.getValue());
		  
		} catch (Exception e) {
		  
		  Logger.get().warn("send message to D3 with get AppInfo error with appId: " + appId,e);
		  return new ArrayList<Message>();
		}
		
		String d1Name = StringUtils.parseNullToEmpty(appSalesInfo.get(MessageDataRequestApi.D1UNAME));
		String msName = StringUtils.parseNullToEmpty(appSalesInfo.get(MessageDataRequestApi.MERCHANTSTORENAME));
		
		String content = String.format(
				ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.D3, Channel.WECHAT),
				d1Name, msName, app.getProductName(), app.getPrincipal(), DateTimeUtils.formatYMD(new Date()));

		return Arrays.<Message> asList(MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.D3,
				content));
	}
}
