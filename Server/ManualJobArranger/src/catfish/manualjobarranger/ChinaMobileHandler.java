package catfish.manualjobarranger;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 涓烘敮鎸佺Щ鍔ㄩ」鐩殑涓存椂鏂规
 * Created by feit on 2017/3/31.
 */
public class ChinaMobileHandler {
    private static Set<String> JobNames;
	private static String CMCCServiceUrl;
	
    private static int MaxRetry = Configuration.getAppServiceMaxRetries();

    static{
        
		CMCCServiceUrl = Configuration.getAppServiceUrl();
		if(!CMCCServiceUrl.endsWith("/")) CMCCServiceUrl += "/";
		CMCCServiceUrl += "cmcc/";
		
        String[] jobs = new String[]{"CheckD1Feedback", "CheckDGroupPhoto",
                "CheckUserForApp", "CheckFirstContactForApp",
                "CheckSecondContactForApp", "CheckCompanyForApp",
                "CheckPersonalInfoV3", "CheckIOUV3", "CheckMerchantForApp", "CheckTransactionMonitorJobs", "CheckBuckle", "TransactionMonitorForApp"};
        JobNames = new HashSet<>(Arrays.asList(jobs));
    }

    public static boolean doNotNeedJob(MessageEntity entity){
    	String appId = entity.getAppId();
    	String jobName = entity.getJobName();
        if(isCMCC(appId)){
            Logger.get().info(String.format("This is a ChinaMobile appId: %s ,jobName: %s, Message: %s", appId, jobName, entity.getMessageBody()));
            if(JobNames.contains(jobName)){
            	try{
            		GeneralQueueApi.deleteMsg(entity.getQueueName(), entity.getMessageHandle());
                	GeneralQueueApi.sendMsg(Configuration.getFlowQueue(), entity.getMessageBody());
                	Logger.get().info(String.format("This job will not generate for china mobile, Send message: %s to queue: %s", entity.getMessageBody(), Configuration.getFlowQueue()));
                }catch(Exception e){
                	Logger.get().error(String.format("Can not send msg: %s to queue: %s ", entity.getMessageBody(), Configuration.getFlowQueue()), e);
                }
                return true;
            }           
        }
        return false;
    }
    //是否移动申请
    private static boolean isCMCC(final String appId){
        return HttpClientApi.CallService(MaxRetry, new ServiceHandler<Map<String, Object>, Boolean>() {
            @Override
            public String createUrl() {
                return String.format("%sapplication/isOrNotCMCC?appId=%s", CMCCServiceUrl, appId);
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
