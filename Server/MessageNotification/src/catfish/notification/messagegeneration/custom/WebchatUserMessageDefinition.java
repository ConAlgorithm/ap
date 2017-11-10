package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.message.WeChatText;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.sender.wechat.TokenManager;

/**
 * @author yinqs 2017年1月9日
 */
public class WebchatUserMessageDefinition implements MessageDefinition {

    @Override
    public List<Message> apply(Map<String, ?> receivedMessage) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(receivedMessage);
        Gson gson = new Gson();
        Logger.get().info("WebchatUserMessageDefinition params:" + gson.toJson(map));
        String contentWebchat = map.get("contentWebchat").toString();
        String userIds= map.get("userIds").toString();
        Logger.get().info("userIds: " + userIds);
        List<Message> messageList = new ArrayList<Message>();
        TokenManager tokenManager = Configuration.getWeChatBDTokenManager();
        List<String> openIdList = MessageDatabaseApi.getWeChatOpenIdByUserIds(userIds);
        Logger.get().info("openIdList: " + gson.toJson(openIdList));
        for (String weiXinUserId : openIdList) {;
            WeChatText message = new WeChatText(map, tokenManager, weiXinUserId, contentWebchat);
            messageList.add(message);
        }
        return messageList;
    }
}
