package catfish.notification.message;

import java.util.Map;

import catfish.base.StringUtils;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.common.AppMessageType;

public class LeanCloudMessage extends Message {
  private String prod;
  private String channels;
  private String appId;
  private String badge;
  private String type;
  private String appAction;
  private String action;
  private String status;
  private String message;
  private String bizType;
  private String leancloudAppId;
  private String leancloudAppKey;
  
  private String toSupperApp;
  
  public LeanCloudMessage(Map<String, ?> receivedMessage, String channels, String message) {
    super(receivedMessage);
    this.appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
    this.prod = Configuration.getLeanCloudIOSCert();
    this.badge = "Increment";
    this.action = "MDXIM_STATUS";
    this.channels = channels.replaceAll("-", "");
    this.type = String.valueOf(AppMessageType.APPLICATION);
    this.status = null;
    this.appAction = null;
    this.message = message;  
    
    if (receivedMessage.containsKey("toSupperApp")) {
    	this.toSupperApp = (String)receivedMessage.get("toSupperApp");
    }
    
    if (receivedMessage.containsKey("bizType")) {
		String bizType = (String) receivedMessage
				.get("bizType");
		if (!StringUtils.isNullOrWhiteSpaces(bizType)) {
			 this.bizType=(String) receivedMessage.get("bizType");
		}
	}
   
	// 支持业务方自己定义推送app消息需要的的appid和appkey
	if (receivedMessage.containsKey("leancloudAppKey")) {
		String leancloudAppKey = (String) receivedMessage
				.get("leancloudAppKey");
		if (!StringUtils.isNullOrWhiteSpaces(leancloudAppKey)) {
			this.leancloudAppKey = leancloudAppKey;
		}
	}
	if (receivedMessage.containsKey("leancloudAppId")) {
		String leancloudAppId = (String) receivedMessage
				.get("leancloudAppId");
		if (!StringUtils.isNullOrWhiteSpaces(leancloudAppId)) {
			this.leancloudAppId = leancloudAppId;
		}

	}
		
   
  }
 
  public LeanCloudMessage(Map<String, ?> receivedMessage, String channels, String message, String type) {
	super(receivedMessage);
	this.appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
	this.prod = Configuration.getLeanCloudIOSCert();
	this.badge = "Increment";
	this.action = "MDXIM_STATUS";
	this.channels = channels.replaceAll("-", "");
	this.type = type;
	this.status = null;
	this.appAction = null;
	this.message = message;
  }
  
  public LeanCloudMessage(Map<String, ?> receivedMessage, String channels, String message,String leancloudAppId, String leancloudAppKey) {
      super(receivedMessage);
      this.appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
      this.prod = Configuration.getLeanCloudIOSCert();
      this.badge = "Increment";
      this.action = "MDXIM_STATUS";
      this.channels = channels.replaceAll("-", "");
      this.type = String.valueOf(AppMessageType.APPLICATION);
      this.status = null;
      this.appAction = null;
      this.message = message;
      this.leancloudAppId = leancloudAppId;
      this.leancloudAppKey = leancloudAppKey;
  }
  public String getProd() {
    return prod;
  }


  public String getChannels() {
    return channels;
  }


  public String getAppId() {
    return appId;
  }


  public String getBadge() {
    return badge;
  }


  public String getType() {
    return type;
  }


  public String getAppAction() {
    return appAction;
  }


  public String getAction() {
    return action;
  }


  public String getStatus() {
    return status;
  }


  public String getMessage() {
    return message;
  }

  public String getBizType() {
      return bizType;
    }

  public void setBizType(String bizType) {
      this.bizType=bizType;
  }

public String getLeancloudAppId() {
    return leancloudAppId;
}

public void setLeancloudAppId(String leancloudAppId) {
    this.leancloudAppId= leancloudAppId;
}

public String getLeancloudAppKey() {
    return leancloudAppKey;
}

public void setLeancloudAppKey(String leancloudAppKey) {
    this.leancloudAppKey= leancloudAppKey;
}


public String getToSupperApp() {
	return toSupperApp;
}

public void setToSupperApp(String toSupperApp) {
	this.toSupperApp = toSupperApp;
}

@Override
  public String toString() {
    return String.format("Push leanCloud message for %s, to %s", super.toString(), appId);
  }
}
