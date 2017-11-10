package catfish.notification.message;

import java.util.Map;

import catfish.notification.sender.wechat.ImageManager;
import catfish.notification.sender.wechat.TokenManager;

public class WeChatImage extends BaseWeChatMessage<ImageManager> {
  public WeChatImage(
      Map<String, ?> receivedMessage,
      TokenManager tokenManager,
      String openId,
      ImageManager imageManager) {
    super(receivedMessage, tokenManager, openId, imageManager);
  }

  public ImageManager getImageManager() {
    return getContent();
  }
}
