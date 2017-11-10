package catfish.msglauncher.sender;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;

import com.montnets.mwgate.client.MengWangClient;

public class MengWangMessageSender implements MessageSender {

  private static final int EXPECTED_CODE = 0;

  @Override
  public void send(Message baseMessage) {
    if(!isAvailable())
      throw new RuntimeException("Mengwang is closed.");
    
    ShortMessage message = (ShortMessage) baseMessage;

    StringBuffer resultLiteral = new StringBuffer();
    int resultCode = MengWangClient.send(
        resultLiteral,
        Configuration.getMengWangIp(),
        Configuration.getMengWangPort(),
        Configuration.getMengWangUsername(),
        Configuration.getMengWangPassword(),
        message.getMobile(),
        message.getContent(),
        HttpClientConfig.get());

    if (resultCode == EXPECTED_CODE) {
      Logger.get().info("Send MengWang message with code " + resultLiteral.toString());
    } else {
      throw new RuntimeException(String.format(
          "Got MengWang error %d : %s", resultCode, resultLiteral.toString()));
    }
  }
  
  private boolean isAvailable(){
    return DynamicConfig.readAsBool("mengwangSwitchOn");
  }
}
