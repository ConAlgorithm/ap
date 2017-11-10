package catfish.msglauncher.sender;

import catfish.msglauncher.Message;

public interface MessageSender {
  void send(Message baseMessage);
}
