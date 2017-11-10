package catfish.flowcontroller.rest;

import catfish.base.Logger;
import catfish.flowcontroller.storage.FlowUIStorage;
import catfish.flowcontroller.ui.Layout;
import catfish.framework.IListener;
import catfish.framework.http.HttpData;

import com.google.gson.Gson;

public class FlowUIService implements IListener<HttpData>{

	private static final String POST="post";
    private static final String GET="get";
    private static final String emptyJson = "{}";
    private static final String successJson = "{\"success\":\"成功!\"}";
    private static final String errorJson = "{\"success\":\"失败!\"}";
    
	@Override
	public void onMessage(String message, HttpData data) {
		String method = data.getMethod().toLowerCase();
        if(POST.equals(method)){
            String requestData = data.getRequestData();
            data.setResponseData(saveLayout(requestData));
        } else if(GET.equals(method)){
            String[] paths = data.getPaths();
            if(paths != null && paths.length>1){
                String workflow= paths[1];
                Layout layout = FlowUIStorage.Instance().loadLayout(workflow);
                if(layout == null)
                    data.setResponseData(emptyJson);
                else
                	data.setResponseData(new Gson().toJson(layout.coords));
            } else {
                data.setResponseData(emptyJson);
            }
        } else {
            Logger.get().warn("[FlowUIService] Not supported method in status" + method);
            data.setResponseData(emptyJson);
        }
	}

	private String saveLayout(String data)
	{
		try{
			Layout layout = new Gson().fromJson(data, Layout.class);
			FlowUIStorage.Instance().saveLayout(layout);
			return successJson;
		}catch(Exception e)
		{
			Logger.get().error("[FlowUIService] Save Flow UI error", e);
			return errorJson;
		}
	}
}
