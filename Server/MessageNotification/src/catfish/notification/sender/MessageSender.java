package catfish.notification.sender;

import catfish.notification.Message;

public interface MessageSender {
  void send(Message baseMessage);
}
