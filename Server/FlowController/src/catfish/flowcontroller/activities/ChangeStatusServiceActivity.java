package catfish.flowcontroller.activities;

import java.util.Map;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.util.EnumUtils;
import catfish.flowcontroller.activities.ActivityFactoryBase;
import catfish.flowcontroller.activities.ServiceBaseActivity;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.httprequest.HttpRequest;
import catfish.framework.httprequest.HttpRespond;

//不建议在FollowController中同步请求Service，容易卡住
@Deprecated
public class ChangeStatusServiceActivity extends ServiceBaseActivity{

	@Override
	protected HttpRequest createRequest(Application application,
			IServiceProvider sp) {
		String[] path = new String[]{"application", application.appId};
		
		Map<String, Integer> body = CollectionUtils.<String, Integer>newMapBuilder().build();
		if(status != ApplicationStatus.None)
		{
			body.put("status", status.getValue());
		}
		if(uploadStatus != UploadFileStatus.None.getValue())
		{
			body.put("uploadStatus", uploadStatus);
		}
		String bodyStr = new Gson().toJson(body);
		HttpRequest request = new HttpRequest("PUT", ApplicationServiceHost, ApplicationServicePort, path, bodyStr);
		
		return request;
	}

	@Override
	protected boolean handleResponse(HttpRespond response,
			Application application, IServiceProvider sp) {
		Logger.get().info(response.getResponse());
		return true;
	}
}


@Deprecated
class ChangeStatusServiceActivityFactory extends ActivityFactoryBase<ChangeStatusServiceActivity>{
	@Override
    protected void setProperties(ChangeStatusServiceActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object retryMsgObj = activityConfig.get("retryMsg");
        if(retryMsgObj != null)
        {
        	activity.retryMsg = retryMsgObj.toString();
        }
        else{
        	activity.retryMsg = "reTry" + activity.name;
        }
        
        Object skipMsgObj = activityConfig.get("skipMsg");
        if(skipMsgObj != null)
        {
        	activity.skipMsg = skipMsgObj.toString();
        }else{
        	activity.skipMsg = "skip" + activity.name;
        }
        
        Object statusObj = activityConfig.get("status");
        if(statusObj != null)
        {
        	Object value = EnumUtils.getValue(ApplicationStatus.class, statusObj.toString());     	
        	activity.status = EnumUtils.parse(ApplicationStatus.class, value);
        }
        
        Object uploadStatusObj = activityConfig.get("uploadStatus");
        if(uploadStatusObj != null)
        {
        	String[] array = this.loadAray(uploadStatusObj);
        	for(String item : array)
        	{
        		activity.uploadStatus += Integer.parseInt(EnumUtils.getValue(UploadFileStatus.class, item).toString());
        	}
        }
    }
}

