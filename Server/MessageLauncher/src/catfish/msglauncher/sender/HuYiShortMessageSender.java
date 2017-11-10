package catfish.msglauncher.sender;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;

public class HuYiShortMessageSender implements MessageSender {

  private static final String URL = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
  private static final String EXPECTED_CODE = "2";

  @Override
  public void send(Message baseMessage) {
    if(!isAvailable())
      throw new RuntimeException("HuYi is closed.");
    
    
    ShortMessage message = (ShortMessage) baseMessage;
    String responseText = HttpClientApi.postForm(URL, CollectionUtils.mapOf(
        "account", Configuration.getHuYiAccount(),
        "password", Configuration.getHuYiPassword(),
        "mobile", message.getMobile(),
        "content", message.getContent()));

    try {
      String statusCode =
          DocumentHelper.parseText(responseText).getRootElement().elementTextTrim("code");
      if (!statusCode.equals(EXPECTED_CODE)) {
        throw new RuntimeException("Got HuYi error " + responseText);
      }
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
  }
  
  private boolean isAvailable(){
    return DynamicConfig.readAsBool("ihuyiSwitchOn");
  }
}
