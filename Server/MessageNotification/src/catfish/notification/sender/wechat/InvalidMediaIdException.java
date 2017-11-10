package catfish.notification.sender.wechat;

public class InvalidMediaIdException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InvalidMediaIdException(String description) {
    super(description);
  }
}
