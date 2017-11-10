package jma.handlers;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import jma.Configuration;
import jma.JobHandler;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.queue.QueueApi;

public abstract class ChangeApplicationStatusHandler extends JobHandler{

  public ChangeApplicationStatusHandler() {
    sendsResponse = false;
  }

	@Override
	public void execute(String appId){
		String url = Configuration.getApplicationServiceUrl();
		if(url.endsWith("/"))
		{
			url += "application/" + appId;
		}else{
			url += "/application/" + appId;
		}

		int count = 0;
		while(count < Configuration.getApplicationServiceMaxRetries())
		{
			try{
				ApplicationStatus status = changeStatusTo();
				UploadFileStatus[] uploadStatus = changeUploadStatusTo();

				Map<String, Integer> body = CollectionUtils.<String, Integer>newMapBuilder().build();
				if(status != null && status != ApplicationStatus.None)
				{
					body.put("status", status.getValue());
				}
				int sum = getUploadStatusSum(uploadStatus);
				if(sum != 0)
				{
					body.put("uploadStatus", sum);
				}
				if(checkStatus(appId, status)){
				    String response = HttpClientApi.putJson(url, body);
				    Logger.get().info("Request: " + new Gson().toJson(body));
				    if(response != null)
				    {
				        Logger.get().info("Response: " + response);
				    }				    
				}
                sendsResponse = true;
                
                // 申请如果为最终状态如 -1 35 40，则需要通知Instinct录入申请信息
                if(status == ApplicationStatus.Rejected || 
                        status == ApplicationStatus.MerchantApproved || 
                        status == ApplicationStatus.Canceled) {
                    updateInstinct();
                }
                
                return;
			}catch(Exception e)
			{
				Logger.get().warn(String.format("Change Application Status of %s failed, retry count: %d ,request url :%s",appId,count,url), e);
				count ++;
			}
		}
		if(!sendsResponse)
		{
			Logger.get().error("Change Application Status of " + appId + " error!");
		}
	}
	
	private void updateInstinct() {
        //request instinct system
        Map<String, String> iaMessage = new HashMap<String, String>();
        iaMessage.put("appId", this.requestMessager.getAppId());
        iaMessage.put("jobName", "CheckInstinctAntiFraudUpdate");
        iaMessage.put("jobStatus", "ToBeUpdate");
        String messageBody = new Gson().toJson(iaMessage);
        Logger.get().info("Send CheckInstinctAntiFraudUpdate Msg to InstinctQueue, msg: " + messageBody);        
        QueueApi.writeMessage("InstinctQueue", messageBody, 1, 0);
	}

	private int getUploadStatusSum(UploadFileStatus[] uploadStatus)
	{
		int sum = 0;
		if(uploadStatus != null)
		{

			for(UploadFileStatus item : uploadStatus)
			{
				sum += item.getValue();
			}
		}
		return sum;
	}

	protected abstract ApplicationStatus  changeStatusTo();

	protected abstract UploadFileStatus[] changeUploadStatusTo();
	
    protected boolean checkStatus(String appId, ApplicationStatus status) {
        String sql = "SELECT Status FROM InstallmentApplicationObjects WHERE Id=:appId";
        try {
            Integer oldStatus = DatabaseApi.querySingleInteger(sql, CollectionUtils.mapOf("appId", appId));
            if (oldStatus.equals(ApplicationStatus.Canceled.getValue()) || oldStatus.equals(ApplicationStatus.Rejected.getValue()) || oldStatus.compareTo(ApplicationStatus.Closed.getValue()) >= 0) {
                Logger.get().info("ApplicationStatus can't changed !  Status should not be from  " + oldStatus + " to " + status.getValue());
                return false;
            }
            if (status.getValue().equals(ApplicationStatus.Canceled.getValue())) {
                return true;
            } else if (oldStatus.compareTo(status.getValue()) >= 0) {
                Logger.get().info("ApplicationStatus can't changed !  Status should not be from  " + oldStatus + " to " + status.getValue());
                return false;
            }
        } catch (Exception e) {
            Logger.get().error("checkStatus has error appId is " + appId, e);
            return false;
        }
        return true;
    }
}
