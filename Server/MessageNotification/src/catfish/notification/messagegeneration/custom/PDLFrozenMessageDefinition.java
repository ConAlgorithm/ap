package catfish.notification.messagegeneration.custom;

import grasscarp.account.model.FrozenDetail;
import grasscarp.application.model.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.messagegeneration.MessageDataRequestApi;
import catfish.notification.messagegeneration.MessageDefinition;

public class PDLFrozenMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);
    
    Logger.get().info("begin send frozen message for appId: " + appId);
    
    Application app = MessageDataRequestApi.getApp(appId, InstalmentChannel.PayDayLoanApp.getValue());
    
    if (app == null) {
      Logger.get().warn("send frozen message to leancloud with null app error. appId: " + appId);
      return new ArrayList<Message>();
    }
    
    FrozenDetail frozen = MessageDataRequestApi.getAvailableFrozenDetail(app.getUserId());
    
    if(frozen == null || frozen.getFrozenDay() <= 0) {
      Logger.get().warn("user account not frozen with userId: " + app.getUserId());
      return new ArrayList<Message>();
    }
    
    return Arrays.<Message>asList(
        new LeanCloudMessage(receivedMessage, app.getUserId(), frozen.getAppMessage()));
    }
  }
