package catfish.msglauncher.sender;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;

import com.jianzhou.sdk.BusinessService;

public class JianZhouMessageSender implements MessageSender {

  private static final String URL = "http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService";
  private static final int EXPECTED_CODE = 0;

  @Override
  public void send(Message baseMessage) {
    if(!isAvailable())
      throw new RuntimeException("Jianzhou is closed.");
    
    
    ShortMessage message = (ShortMessage) baseMessage;
    BusinessService bs = new BusinessService();
    bs.setWebService(URL);

    String sign = message.getSign() == null?" 【买单侠】" : message.getSign();
    Logger.get().info("JianZhou message content: " + message.getContent() + sign);

    int responseCode = bs.sendMessage(
        Configuration.getJianZhouAccount(), Configuration.getJianZhouPassword(),
        message.getMobile(), message.getContent() + sign);

    if (responseCode > EXPECTED_CODE) {
      Logger.get().info("Send JianZhou message with code " + responseCode);
    } else {
      throw new RuntimeException(String.format(
          "Got MengWang error %d ", responseCode));
    }
  }
  private boolean isAvailable(){
    return DynamicConfig.readAsBool("jianzhouSwitchOn");
  }
}
