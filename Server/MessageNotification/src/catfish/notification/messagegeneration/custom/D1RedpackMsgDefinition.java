package catfish.notification.messagegeneration.custom;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.database.DatabaseApi;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.message.WeChatText;
import catfish.notification.messagegeneration.MessageDataRequestApi;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nick on 16/7/20.
 * D1红包微信消息
 */
public class D1RedpackMsgDefinition implements MessageDefinition{

    private static final String sql_getD1OpenIdForApp =
            "select u.weixinuserid from dealeruserapplicationrelationobjects da " +
            "join userobjects u on u.id = da.userid " +
            "where da.appid = :appId";

    private static final String sql_getD1UserIdForApp =
            "select u.id from dealeruserapplicationrelationobjects da " +
            "join userobjects u on u.id = da.userid " +
            "where da.appid = :appId";
    
    @Override
    public List<Message> apply(Map<String, ?> receivedMessage) {
        List<Message> messages = new ArrayList<>();
        // 获取微信消息内容
        String content = (String) receivedMessage.get("Content");
        // 获取appId
        String appId = (String) receivedMessage.get("AppId");
        
        String d1OpenId = null;
        boolean salesAdminServiceSwitchOn = Boolean.FALSE;
        salesAdminServiceSwitchOn = DynamicConfig.readAsBool("salesAdminServiceSwitchOn");
        boolean isposApp = MessageDatabaseApi.isPOSApplication(appId);//app申请是否是pos用户
        if (salesAdminServiceSwitchOn && isposApp) {
        	//拆分：1.查出签单d1的userId；2，调用smsa的接口查询d人员的微信openId
        	d1OpenId = getD1WeChatOpenIdByAppId(appId);
        }else {
        	// 根据appId获取签单的d1
        	d1OpenId = getD1OpenIdForApp(appId);
		}
        if (d1OpenId != null) {
            WeChatText weChatText = new WeChatText(receivedMessage, Configuration.getWeChatDealerTokenManager(), d1OpenId, content);
            messages.add(weChatText);
        }
        return messages;
    }

    private String getD1OpenIdForApp(String appId) {
        return DatabaseApi.querySingleString(sql_getD1OpenIdForApp, CollectionUtils.mapOf("appId", appId));
    }
    
    public static String getD1WeChatOpenIdByAppId(String appId) {
		Logger.get().info("call getD1WeChatOpenIdByAppId with appId : " + appId);
		String WeiXinUserId = null;
		// 1.先根据appid查询签单表中d1的userId
		String d1UserId = DatabaseApi.querySingleString(sql_getD1UserIdForApp, CollectionUtils.mapOf("appId", appId));
		if (StringUtils.isNullOrWhiteSpaces(d1UserId)) {
			Logger.get().warn("no d1UserId from dealeruserapplicationrelationobjects , for appId : " + appId);
		} else {
			// 2.调用smsa接口，根据d人员的id查询d的微信openId
			Logger.get().info("-----begin smsa-service to get weixinUserId for d1 , d1UserId : " + d1UserId);
			Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().build();
			params.put("userObjectsId", d1UserId);
			
			Map<String, Object>[] data = MessageDataRequestApi.getSmsaUserExtendInfo(params);
			if (data == null || data.length==0 || !data[0].containsKey("weixinUserId")) {
				Logger.get().warn("call smsa-service -- no weixinUserId for d1 , d1UserId : " + d1UserId + " , appId : " + appId);
			} else {
				WeiXinUserId = (String) data[0].get("weixinUserId");
				Logger.get().info("call smsa-service -- weixinUserId for d1 , WeiXinUserId : " + WeiXinUserId +" d1UserId : " + d1UserId + " , appId : " + appId);
			}
		}
		return WeiXinUserId;
	}
}
