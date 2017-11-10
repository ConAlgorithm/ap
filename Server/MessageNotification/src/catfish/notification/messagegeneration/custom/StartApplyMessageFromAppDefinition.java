package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.message.WeChatText;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.object.AppMessageObject;
import catfish.notification.sender.wechat.TokenManager;

public class StartApplyMessageFromAppDefinition implements MessageDefinition{

@Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    Map<String, Object> map=new HashMap<String, Object>();
    map.putAll(receivedMessage);
    Gson gson=new Gson();
    Logger.get().info("StartApplyMessageFromAppDefinition params:"+  gson.toJson(map));
      
    String appId = (String) map.get(NotificationKeys.APP_ID);
    
    String contentWebchat = String.format(ResourceConfig.getResourceByName(this.getClass().getSimpleName(), 
      Receiver.D1, Channel.WECHAT), MessageDatabaseApi.getCustomerMobileBy(appId));
    Logger.get().info("contentWebchat="+contentWebchat); 
    String contentApp = String.format(ResourceConfig.getResourceByName(this.getClass().getSimpleName(), 
        Receiver.D1, Channel.APP), MessageDatabaseApi.getMerchantStoreNameBy(appId),MessageDatabaseApi.getCustomerMobileBy(appId));
    Logger.get().info("contentApp="+contentApp);
    
    List<Message> messageList=new ArrayList<Message>();
    
/*    Message messageWeixin= MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.D1, contentWebchat);
    messageList.add(messageWeixin);*/
    TokenManager tokenManager = Configuration.getWeChatDealerTokenManager();
    List<String> openIdList = MessageDatabaseApi.getAllD1WeChatOpenIdBy(appId);
    Logger.get().info("openIdList: "+ gson.toJson(openIdList));
    for (String weiXinUserId : openIdList) {
        WeChatText message=new WeChatText(map, tokenManager,weiXinUserId,contentWebchat);
        messageList.add(message);
    }
    List<AppMessageObject> d1idList = new LinkedList<AppMessageObject>();
    // 获取smsa开关
    boolean salesAdminServiceSwitchOn = Boolean.FALSE;
    salesAdminServiceSwitchOn = DynamicConfig.readAsBool("salesAdminServiceSwitchOn");
    boolean isposApp = MessageDatabaseApi.isPOSApplication(appId);//app申请是否是pos用户
    if (salesAdminServiceSwitchOn && isposApp) {
    	//smsa开关打开,调用新逻辑,取消查询DOrgNodeObjects节点相关数据表方式
    	d1idList = MessageDatabaseApi.getAllD1IdByAppId_New(appId);
	}else {
		d1idList = MessageDatabaseApi.getAllD1IDByAppId(appId);	
	}
    Logger.get().info("d1idList: "+ gson.toJson(d1idList));
    for (AppMessageObject appMessageObject : d1idList) {
        Map<String, Object> mapApp=new HashMap<String, Object>();
        mapApp.putAll(receivedMessage);
        mapApp.put("deviceType", appMessageObject.getDeviceType());
        mapApp.put("deviceToken", appMessageObject.getDeviceToken());
        LeanCloudMessage messageapp=new LeanCloudMessage(mapApp, appMessageObject.getId(), contentApp,
            StartupConfig.get("appserver.leancloud.sms.appID"),StartupConfig.get("appserver.leancloud.sms.appKey"));
        messageapp.setBizType("101");
        messageList.add(messageapp);
    }
    return messageList;
  }
}
