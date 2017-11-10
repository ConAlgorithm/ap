package catfish.notification.sender;

import catfish.notification.Message;
import catfish.notification.message.WeChatImage;
import catfish.notification.sender.wechat.AccessTokenException;
import catfish.notification.sender.wechat.ImageManager;
import catfish.notification.sender.wechat.InvalidMediaIdException;
import catfish.notification.sender.wechat.TokenManager;
import catfish.notification.sender.wechat.WeChatApi;

public class WeChatImageSender implements MessageSender {

  @Override
  public void send(Message baseMessage) {
    WeChatImage message = (WeChatImage) baseMessage;
    TokenManager tokenManager = message.getTokenManager();
    ImageManager imageManager = message.getImageManager();

    try {
      WeChatApi.sendImage(
          tokenManager.getIdentifier(), message.getOpenId(), imageManager.getIdentifier());
    } catch (AccessTokenException e) {
      tokenManager.update();
      throw e;
    } catch (InvalidMediaIdException e) {
      imageManager.update();
      throw e;
    }
  }
}
