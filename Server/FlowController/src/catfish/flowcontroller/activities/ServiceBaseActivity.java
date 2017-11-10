package catfish.flowcontroller.activities;

import java.util.Map;

import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.UploadFileStatus;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.httprequest.HttpRequest;
import catfish.framework.httprequest.HttpRespond;
import catfish.framework.httprequest.IHttpRequestService;

import com.google.gson.Gson;

//不建议在FollowController中同步请求Service，容易卡住
@Deprecated
public abstract class ServiceBaseActivity extends Activity{

	protected static final String ApplicationServiceHost = "";
	protected static final String ApplicationServicePort = "";
	
	private static final String Response = "Response";
	//收到一个消息，可以重新请求
	public String retryMsg;
	//收到一个消息，跳过请求，Activity标记为完成
	public String skipMsg;
	
	//要修改的申请状态
	public ApplicationStatus status = ApplicationStatus.None;
	//要修改的申请文件上传状态二进制和
	public int uploadStatus = UploadFileStatus.None.getValue();
	
	private HttpRequest request;
	private HttpRespond response;
	
	
	public void reset(){
		super.reset();
		request = null;
		response = null;
	}
	
	public void saveState(Map<String, Object> dataMap){
		super.saveState(dataMap);
		if(response != null)
		{
			String responseStr = new Gson().toJson(response);
			StateHelper.save(dataMap, Response, responseStr);
		}
	}
	
	public void loadState(Map<String, Object> dataMap){
		super.loadState(dataMap);
		String responseStr = StateHelper.loadString(dataMap, Response);
		if(responseStr != null)
		{
			response = new Gson().fromJson(responseStr, HttpRespond.class);
		}
	}
	
	@Override
	protected void init(Application application, IServiceProvider sp) {
		IHttpRequestService service = sp.getService(IHttpRequestService.class);
		request = createRequest(application, sp);
		
		if(request != null)
		{
			response = service.sendHttpRequest(request);
		}		   
	}
	
	@Override
	protected void process(Application application, IServiceProvider sp) {
			
		if(MessagerHelper.findQueueMessager(this, application, retryMsg) != null)
		{
			init(application, sp);
		}
		else if(MessagerHelper.findQueueMessager(this, application, skipMsg) != null)
		{
			this.done = true;
			return;
		}
		
		if(handleResponse(response, application, sp))
		{
			this.done = true;
			return;
		}
	}
	
	protected abstract HttpRequest createRequest(Application application, IServiceProvider sp);
	
	protected abstract boolean handleResponse(HttpRespond response, Application application, IServiceProvider sp);

	public HttpRequest getRequest() {
		return request;
	}

	public HttpRespond getResponse() {
		return response;
	}
}

