package catfish.notification.messagegeneration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.message.ShortMessage;
import catfish.notification.message.WeChatImage;
import catfish.notification.message.WeChatText;
import catfish.notification.sender.wechat.TokenManager;

public class MessagesFactory {

	private static Message createAppMessage(Map<String, ?> receivedMessage, Receiver receiver, String content) {
		String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
		String userId = "";
		switch (receiver) {
		case CUSTOMER:
			userId = MessageDatabaseApi.getUserIdByAppId(appId);
			break;
		default:
			break;
		}
		return new LeanCloudMessage(receivedMessage, userId, content);
	}

	private static Message createWeChatTextMessage(Map<String, ?> receivedMessage, Receiver receiver, String content) {
		String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);

		List<Object> openIdAndTokenManager = getOpenIdAndTokenManager(appId, receiver);
		if (openIdAndTokenManager == null)
			return null;
		return new WeChatText(receivedMessage, (TokenManager) openIdAndTokenManager.get(1),
				(String) openIdAndTokenManager.get(0), content);
	}

	private static List<Object> getOpenIdAndTokenManager(String appId, Receiver receiver) {
		String openId = "";
		TokenManager tokenManager = null;
		boolean salesAdminServiceSwitchOn = Boolean.FALSE;
		salesAdminServiceSwitchOn = DynamicConfig.readAsBool("salesAdminServiceSwitchOn");// 获取smsa开关
		boolean isposApp = MessageDatabaseApi.isPOSApplication(appId);//app申请是否是pos用户
		
		switch (receiver) {
		case CUSTOMER:
			openId = MessageDatabaseApi.getCustomerWeChatOpenIdBy(appId);
			tokenManager = Configuration.getWeChatCustomerTokeManager();
			break;
		case S1:
			openId = MessageDatabaseApi.getMerchantWeChatOpenIdBy(appId);
			tokenManager = Configuration.getWeChatMerchantTokenManager();
			break;
		case D1:
			if (salesAdminServiceSwitchOn && isposApp) {
				openId = MessageDataRequestApi.getD1WeChatOpenIdByAppId(appId);
			} else {
				openId = MessageDatabaseApi.getD1WeChatOpenIdBy(appId);
			}
			tokenManager = Configuration.getWeChatDealerTokenManager();
			break;
		case D3:
			Map<String, String> appSalesInfo = MessageDataRequestApi.getAppSalesInfo(appId);
			String d3WeiXinId = appSalesInfo.get(MessageDataRequestApi.D3WEIXINID);
			if (appSalesInfo == null || StringUtils.isNullOrWhiteSpaces(d3WeiXinId)) {
				Logger.get().warn("Cannot send D3 message because the D3 weixinid is null");
				break;
			}
			openId = d3WeiXinId;
			tokenManager = Configuration.getWeChatDealerTokenManager();
			break;
		case BD2:
			if (salesAdminServiceSwitchOn && isposApp){
				openId = MessageDataRequestApi.getBD2WeChatOpenIdByAppId(appId);
			} else {
				openId = MessageDatabaseApi.getBD2WeChatOpenIdBy(appId);
			}

			tokenManager = Configuration.getWeChatBDTokenManager();
			break;
		default:
			return null;
		}
		return Arrays.<Object>asList(openId, tokenManager);
	}

	private static Message createWeChatImageMessage(Map<String, ?> receivedMessage, Receiver receiver, String content) {
		String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
		List<Object> openIdAndTokenManager = getOpenIdAndTokenManager(appId, receiver);
		if (openIdAndTokenManager == null)
			return null;
		return new WeChatImage(receivedMessage, (TokenManager) openIdAndTokenManager.get(1),
				(String) openIdAndTokenManager.get(0), Configuration.getImageManager(content));
	}

	private static Message createShortMessage(Map<String, ?> receivedMessage, Receiver receiver, String content) {
		String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
		String mobile = "";
		boolean salesAdminServiceSwitchOn = Boolean.FALSE;
		salesAdminServiceSwitchOn = DynamicConfig.readAsBool("salesAdminServiceSwitchOn");// 获取smsa开关
		boolean isposApp = MessageDatabaseApi.isPOSApplication(appId);//是否是pos用户申请
		
		switch (receiver) {
		case CUSTOMER:
			mobile = MessageDatabaseApi.getCustomerMobileBy(appId);
			break;
		case S1:
			mobile = MessageDatabaseApi.getMerchantMobileBy(appId);
			break;
		case D1:
			if (salesAdminServiceSwitchOn && isposApp) {
				mobile = MessageDataRequestApi.getD1MobileByAppId(appId);
			} else {
				mobile = MessageDatabaseApi.getD1MobileBy(appId);
			}
			break;
		default:
			break;
		}
		return new ShortMessage(receivedMessage, mobile, content);
	}

	public static Message createMessage(Map<String, ?> receivedMessage, Channel channel, Receiver receiver,
			String content) {
		switch (channel) {
		case APP:
			return createAppMessage(receivedMessage, receiver, content);
		case WECHAT:
			return createWeChatTextMessage(receivedMessage, receiver, content);
		case WECHATIMAGE:
			return createWeChatImageMessage(receivedMessage, receiver, content);
		case SHORTMESSAGE:
			return createShortMessage(receivedMessage, receiver, content);
		default:
			return null;
		}
	}
}
