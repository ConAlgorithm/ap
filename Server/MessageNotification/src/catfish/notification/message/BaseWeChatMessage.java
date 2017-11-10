package catfish.notification.message;

import java.util.Map;

import catfish.notification.Message;
import catfish.notification.sender.wechat.TokenManager;

public class BaseWeChatMessage<T> extends Message {
  private TokenManager tokenManager;
  private String openId;
  private T content;

  public BaseWeChatMessage(
      Map<String, ?> receivedMessage,
      TokenManager tokenManager,
      String openId,
      T content) {
    super(receivedMessage);

    this.tokenManager = tokenManager;
    this.openId = openId;
    this.content = content;
  }

  public TokenManager getTokenManager() {
    return tokenManager;
  }

  public String getOpenId() {
    return openId;
  }

  public T getContent() {
    return content;
  }

  @Override
  public String toString() {
    return String.format(
        "We chat message for %s, to %s, by token manager %s",
        super.toString(),
        openId,
        tokenManager.getName());
  }
}
