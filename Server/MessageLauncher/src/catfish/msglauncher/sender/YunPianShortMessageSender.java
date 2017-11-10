package catfish.msglauncher.sender;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;

import com.google.gson.Gson;

public class YunPianShortMessageSender implements MessageSender {

  private static final String URL = "http://yunpian.com/v1/sms/send.json";
  private static final String MESSAGE_PREFIX = "【买单侠】";
  private static final int EXPECTED_CODE = 0;

  @Override
  public void send(Message baseMessage) {
    if(!isAvailable())
      throw new RuntimeException("Yunpian is closed.");
    
    ShortMessage message = (ShortMessage) baseMessage;
    String responseText = HttpClientApi.postForm(URL, CollectionUtils.mapOf(
        "apikey", Configuration.getYunPianApiKey(),
        "mobile", message.getMobile(),
        "text", String.format("%s%s", MESSAGE_PREFIX, message.getContent())));

    int code = ((Double) new Gson().fromJson(responseText, Map.class).get("code")).intValue();
    if (code != EXPECTED_CODE) {
      throw new RuntimeException("Got YunPian error " + responseText);
    }
  }
  
  private boolean isAvailable(){
    return DynamicConfig.readAsBool("yunpianSwitchOn");
  }
}
