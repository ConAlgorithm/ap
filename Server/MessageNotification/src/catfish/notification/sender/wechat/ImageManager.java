package catfish.notification.sender.wechat;

import catfish.base.Logger;

public class ImageManager extends ReusableResourceManager {

  private static final int UPLOAD_RETRIES = 2;
  private static final int EXPIRATION_IN_MINUTES = 60 * 24 * 3 - 5;

  private String path;
  private TokenManager tokenManager;

  public ImageManager(
      String name,
      String path,
      TokenManager tokenManager) {
    super(name);

    this.path = path;
    this.tokenManager = tokenManager;
  }

  @Override
  public synchronized void update() {
    for (int i = 0; i < UPLOAD_RETRIES; i++) {
      try {
        identifier = WeChatApi.uploadJpgImage(tokenManager.getIdentifier(), path);
        updateExpirationDate();
        return;
      } catch (AccessTokenException e) {
        Logger.get().info("Access token expired", e);
        tokenManager.update();    // and continue retrying
      }
    }
    throw new RuntimeException("Upload image error.");
  }

  @Override
  protected int getExpirationInMinutes() {
    return EXPIRATION_IN_MINUTES;
  }
}
