package catfish.notification.messagegeneration.custom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.util.DateTimeUtils;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.messagegeneration.RepaymentApi;

public class PdlRepaymentReminderMessageDefinition implements MessageDefinition {

  private static final String REPAYMENT_BY_DATE_RUL = "/Repayment/RepaymentByDate";
  private static final String PENALTY_URL = "/Repayment/PenaltyPerDay";
  
  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);
    List<String> basicInfo = MessageDatabaseApi.getAppBasicInfo(appId);
    Map<String, String> params = CollectionUtils.mapOf(
        "AppId", appId,
        "Date", DateTimeUtils.formatYMD(new Date()));
    BigDecimal amount = RepaymentApi
        .invoke(REPAYMENT_BY_DATE_RUL, params).getCurrentAmount().setScale(2, RoundingMode.HALF_UP);
    BigDecimal fine = RepaymentApi
        .invoke(PENALTY_URL, params).getPenaltyPerDay().setScale(2, RoundingMode.HALF_UP);

    String placeHolder4, placeHolder5;
    
    if (basicInfo.get(0).equals("0")) {
      placeHolder4 = "今天" + Configuration.getDeductionTime() +"点";
      placeHolder5 = "今天" + Configuration.getDeductionTime() +"点前";
    } else {
      placeHolder4 = basicInfo.get(0).equals("1") ? "明天" : "2天后";
      placeHolder5 = "今天";
    }
    
    String content = String.format(
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
        Receiver.CUSTOMER, Channel.SHORTMESSAGE, Product.PDL), 
      basicInfo.get(2),basicInfo.get(1), amount, placeHolder4, placeHolder5, fine);
    

    return Arrays.<Message>asList(
      MessagesFactory.createMessage(receivedMessage, Channel.SHORTMESSAGE, Receiver.CUSTOMER, content));
  }

  
}
