package jma.util;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.AttachmentType;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;
import grasscarp.application.model.POSApplication;
import grasscarp.common.model.AttachmentModel;
import grasscarp.product.model.CellPhone;

public class GrasscarpClient {
  private final static String contentType     = "url";
  
  public static POSApplication getPosApplicationById(String appId) {
    
    String requestUrl = getBaseUrl() + "application/" + appId;
    Logger.get().info("begin request for " + requestUrl);
    
    try {
      
      POSApplication result = HttpClientApi.getGson(requestUrl, POSApplication.class);
      return result;
    } catch (RuntimeException e) {
      
      e.printStackTrace();
      Logger.get().warn("requst url:" + requestUrl + " with error: " + e.getMessage());
      return null;
    }
  }
  
  public static CellPhone getCellPhone(String productId, String productName, String merchantStoreId) {
    
    String requestUrl = String.format(getBaseUrl() + "product/%s/cellPhone?cellPhone=%s&merchantStoreId=%s", 
        productId, productName, merchantStoreId);
    Logger.get().info("begin request for " + requestUrl);
    try {
      
      CellPhone result = HttpClientApi.getGson(requestUrl, CellPhone.class);
      return result;
    } catch (RuntimeException e) {
      
      e.printStackTrace();
      Logger.get().warn("requst url:" + requestUrl + " with error: " + e.getMessage());
      return null;
    }
  }
  
  private static String getBaseUrl() {
    
    return StartupConfig.get("catfish.rest.URL");
  }
  
  public static String getUserHeadPhotoKey(String appId) {
      return getAttachmentKey(appId, AttachmentType.UserHeadPhoto.getValue(), contentType);
  }

  public static String getIdPhotoKey(String appId) {
      return getAttachmentKey(appId, AttachmentType.UserIdPhoto.getValue(), contentType);
  }
  
  private static String getAttachmentKey(String appId, Integer type, String contentType) {
      String url = String.format("%s%s/%s/user-attachment?type=%d&content=%s", getBaseUrl(), "application", appId, type, contentType);
      Logger.get().info("begin request for " + url);
      try {
          AttachmentModel attachment = HttpClientApi.getGson(url, AttachmentModel.class);
          if (attachment == null) {
              Logger.get().info(String.format("appId=%s, type=%d, contextType=%s.attachment is null", appId, type, contentType));
              return null;
          }
          return attachment.getFilePath();
      } catch (RuntimeException e) {
          e.printStackTrace();
          Logger.get().warn("requst url:" + url + " with error: " + e.getMessage());
          return null;
        }
  }
  
    //判断是否为移动项目的申请
	public static boolean isCMCC(final String appId){
		return HttpClientApi.CallService(3, new ServiceHandler<Map<String, Object>, Boolean>() {
			@Override
			public String createUrl() {
				return String.format("%scmcc/application/isOrNotCMCC?appId=%s", getBaseUrl(), appId);
			}
	
			@Override
			public Map<String, Object> OnRequest(String url) {
				return HttpClientApi.getGson(url);
			}
	
			@Override
			public Boolean OnSuccess(Map<String, Object> result) {
				return Boolean.valueOf(result.get("data").toString());
			}
	
			@Override
			public Boolean OnError(Map<String, Object> result) {
				return true;
			}
		});
	}
}
