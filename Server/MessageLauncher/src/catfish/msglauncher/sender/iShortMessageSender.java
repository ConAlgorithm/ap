package catfish.msglauncher.sender;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;

public class iShortMessageSender implements MessageSender {

  private static final String URL = "http://hq.fenqi.im:3868/GetResponse/";
  private static final String EXPECTED_CODE = "Success";

  @Override
  public void send(Message baseMessage) {
    if(!isAvailable())
      throw new RuntimeException("Fenqi SMS is closed.");
    
    
    ShortMessage message = (ShortMessage) baseMessage;
    String responseText = HttpClientApi.postForm(URL, CollectionUtils.mapOf(
        "phone", message.getMobile(),
        "content", message.getContent()));

    String[] statusCode = responseText.split(":");
    if (statusCode[1].equals(EXPECTED_CODE)) {
      Logger.get().info("Send Short message success by iSms! ");
    } else {
      throw new RuntimeException(String.format("iSms Send error!"));
    }

  }
  
  private boolean isAvailable(){
    return DynamicConfig.readAsBool("iShortMessageSwitchOn");
  }
}
