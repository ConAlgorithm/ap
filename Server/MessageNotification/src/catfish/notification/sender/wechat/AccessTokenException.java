package catfish.notification.sender.wechat;

public class AccessTokenException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public AccessTokenException(String description) {
    super(description);
  }
}
