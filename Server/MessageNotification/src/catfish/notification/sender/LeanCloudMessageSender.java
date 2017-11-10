package catfish.notification.sender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.collections.MapBuilder;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.AppMessage;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.message.LeanCloudMessage;

public class LeanCloudMessageSender implements MessageSender {
  @Override
  public void send(Message baseMessage) {
      Gson gson=new Gson();
    LeanCloudMessage message = (LeanCloudMessage) baseMessage;
    MapBuilder<String, Object> msgBuilder = new MapBuilder<String, Object>()
      .add("channels", message.getChannels())
      .add("data", new MapBuilder<String, String>().add("appId", message.getAppId())
        .add("badge", message.getBadge())
        .add("type", message.getType())
        .add("appAction", message.getAppAction())
        .add("action", message.getAction())
        .add("status", message.getStatus())
        .add("bizType", message.getBizType())
        .add("alert", message.getMessage()).build());
    if (message.getProd() != null && message.getProd().equals("dev")) {
      msgBuilder.add("prod", message.getProd());
    }
    
    //调用risk服务保存数据
    saveDataToRisk(message.getAppId(), message.getMessage());
    
    String leancloudAppID=null;
    String leancloudAppKey=null;
    if (message.getLeancloudAppId()!=null
            &&!message.getLeancloudAppId().trim().isEmpty()
            &&message.getLeancloudAppKey()!=null
            &&!message.getLeancloudAppKey().trim().isEmpty()) {
        Map<String, ?> receivedMessage=message.getReceivedMessage();
        String deviceType=(String) receivedMessage.get("deviceType");
            if ("ios".equals(deviceType)) {
                msgBuilder.add("where", new MapBuilder<String, Object>()
                                .add("deviceType",deviceType)
                                .add("deviceToken",receivedMessage.get("deviceToken")).build());
        }else if ("android".equals(deviceType)) {
            msgBuilder.add("where", new MapBuilder<String, Object>()
                       .add("deviceType",deviceType)
                       .add("installationId",receivedMessage.get("deviceToken")).build());
        } 
        leancloudAppID=message.getLeancloudAppId();
        leancloudAppKey=message.getLeancloudAppKey();
        msgBuilder.build().remove("channels");
    }else{
    	if("Y".equals(message.getToSupperApp())){
    		leancloudAppID=StartupConfig.get("appserver.supperapp.leancloud.appID");
            leancloudAppKey=StartupConfig.get("appserver.supperapp.leancloud.appKey");
    	}else{
    		leancloudAppID=StartupConfig.get("appserver.leancloud.appID");
            leancloudAppKey=StartupConfig.get("appserver.leancloud.appKey");
    	}
        
    }
    
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json");
    headers.put("X-AVOSCloud-Application-Id", leancloudAppID);
    headers.put("X-AVOSCloud-Application-Key", leancloudAppKey);
    
    Logger.get().info(String.format("Ready to post json: url:%s, body:%s,headers:%s", Configuration.getLeanCloudUrl(), gson.toJson(msgBuilder.build()), headers));
    Map<String, Object>  result=HttpClientApi.postJson(Configuration.getLeanCloudUrl(), msgBuilder.build(), headers);
    if (result!=null) {
        Logger.get().info("LeanCloud result: "+ gson.toJson(result));  
    }
  }
  
  public static void saveDataToRisk(String appId, String message){
	  Gson gson=new Gson();
	  List<Object> messageList = new ArrayList<Object>();
	  AppMessage appMessage = new AppMessage(message);
	  messageList.add(appMessage);
	  MapBuilder<String, Object> msgBuilder = new MapBuilder<String, Object>()
		      .add("appId", appId.toUpperCase()).add("messageList", messageList);
	  
	  Map<String, String> headers = new HashMap<String, String>();
	  headers.put("Content-Type", "application/json");
	     
	  Logger.get().info(String.format("Ready to post json: url:%s, body:%s,headers:%s", Configuration.getRiskPersistenceUrl() +"/appmessage/addAppMessage", gson.toJson(msgBuilder.build()), headers));
	  Map<String, Object> result = null;
	  try {
		  result = HttpClientApi.postJson(Configuration.getRiskPersistenceUrl()+"/appmessage/addAppMessage", msgBuilder.build(), headers);
	  } catch (Exception e) {
		  Logger.get().warn("call RiskPersistence error,exception:" + e);
	  }
	  if (result != null) {
		  Logger.get().info("RiskPersistence  result: "+ gson.toJson(result)); 
	  }
		  
  }
}
