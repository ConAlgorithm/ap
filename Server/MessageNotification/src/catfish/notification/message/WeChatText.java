package catfish.notification.message;

import java.util.Map;

import catfish.notification.sender.wechat.TokenManager;

public class WeChatText extends BaseWeChatMessage<String> {
  public WeChatText(
      Map<String, ?> receivedMessage,
      TokenManager tokenManager,
      String openId,
      String content) {
    super(receivedMessage, tokenManager, openId, content);
  }
}
