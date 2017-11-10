package catfish.plugins.finance;

import java.util.HashMap;
import java.util.Map;

import catfish.base.StartupConfig;
import catfish.framework.httprequest.HttpRequest;
import catfish.services.httprequest.HttpRequestService;

import com.google.gson.Gson;

public class ActivityRepaymentSchedulePluginTest {

	public static void main(String[] args) {
		
		StartupConfig.initialize();
		
		Map<String, String> body = new HashMap<String, String>();
		String appId = "87813016-DEE8-E411-87D5-80DB2F14945F";
		String adminUserId = "ouyang";
		body.put("AppId", appId);
		body.put("AdminUserId", adminUserId);
		String host = StartupConfig.get("catfish.bonuspoints.rest.host");
		String port = StartupConfig.get("catfish.bonuspoints.rest.port");
		HttpRequest request = new HttpRequest("POST", host, port, new String[]{"RepaymentSchedule"},new Gson().toJson(body));
		
		new HttpRequestService().sendHttpRequest(request);
	}

}
