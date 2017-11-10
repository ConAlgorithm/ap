package catfish.notification.sender;

import catfish.notification.Message;
import catfish.notification.message.WeChatText;
import catfish.notification.sender.wechat.AccessTokenException;
import catfish.notification.sender.wechat.TokenManager;
import catfish.notification.sender.wechat.WeChatApi;

public class WeChatTextSender implements MessageSender {

  @Override
  public void send(Message baseMessage) {
    WeChatText message = (WeChatText) baseMessage;
    TokenManager tokenManager = message.getTokenManager();

    try {
      WeChatApi.sendMessage(
          tokenManager.getIdentifier(), message.getOpenId(), message.getContent());
    } catch (AccessTokenException e) {
      tokenManager.update();
      throw e;
    }
  }
}
