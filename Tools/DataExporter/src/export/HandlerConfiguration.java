package export;

import catfish.base.StartupConfig;
import catfish.framework.storage.IStorageService;
import catfish.services.storage.StorageService;

public class HandlerConfiguration {
	
	private static final String JUXINLI_SWITCH = "catfish.dataExporter.juxinliExport";
	
	private static final String USER_ATTACHMENT_SWITCH = "catfish.dataExporter.userAttachment"; 
	
	private static final String OSS_END_POINT = "catfish.aliyun.oss.Endpoint";
	private static final String OSS_ACCESS_ID = "catfish.aliyun.oss.AccessId";
  private static final String OSS_ACCESS_KEY = "catfish.aliyun.oss.AccessKey";
  private static final String OSS_BUCKET = "catfish.aliyun.oss.Bucket";
	
  private static IStorageService storageService = null;
  
  
  public static IStorageService getStorageService() {
       
    if(storageService == null) {
      storageService = new StorageService(
        StartupConfig.get(OSS_END_POINT),
        StartupConfig.get(OSS_ACCESS_ID), 
        StartupConfig.get(OSS_ACCESS_KEY),
        StartupConfig.get(OSS_BUCKET));
    }
    return storageService;
  }
	
	public static Boolean getJuxinliSwitch() {
		
		return StartupConfig.getAsBoolean(JUXINLI_SWITCH);
	}
	
  public static Boolean getUserAttachmentSwitch() {
    
    return StartupConfig.getAsBoolean(USER_ATTACHMENT_SWITCH);
  }

}
