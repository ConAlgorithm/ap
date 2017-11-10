package risk.services.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoCounter;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.HttpClientConfig;
import catfish.framework.restful.IRESTfulService;
import risk.services.Warnings;
import risk.services.restfulobjects.RestfulResponse;

public class Driver {
	public static void main(String[] args) {

		StartupConfig.initialize();
		Logger.initialize();
		HttpClientConfig.initialize();

	//	testGetMerchantUserActions();
//		testPostMerchantUserActions();
		testQueryHasWarnedByAppIdList();
	}

	private static void testPostMerchantUserActions() {
		String url = "http://127.0.0.1:9200/Risk/Warnings/MerchantUserActions";
		Map<String, Object> body = CollectionUtils.<String, Object>mapOf(
		    "appId", "1F115574-DCDC-E411-B71F-005056C00008",
		    "merchantUserId", "46ED5280-AE06-E411-9BA0-AC853D9F54BA",
		    "actionType", 200,
		    "content", "其他信息");
		Map<String, String> headers = CollectionUtils.mapOf("Content-Type", IRESTfulService.JSON_UTF8, "Accept", IRESTfulService.JSON_UTF8);
		Map<String, Object> str = HttpClientApi.postJson(url, body, headers);
		Logger.get().info(str);
	}

	private static void testGetMerchantUserActions() {
		String url = "http://127.0.0.1:9200/Risk/Warnings/MerchantUserActions/appId=1F115574-DCDC-E411-B71F-005056C00008";
		String str = HttpClientApi.get(url);
		Logger.get().info(str);
	}
	private static void testQueryHasWarnedByAppIdList(){
	    List<String>appIdList = new ArrayList<>();
	    appIdList.add("588d11f6-f23b-e511-87d5-80db2f14945f");
	    appIdList.add("c8e42533-d233-4911-86e8-7903e1ebda1b");
	    appIdList.add("43c25390-747b-4315-be21-9a3361b652b2");
	    appIdList.add("02cebbac-0b3c-e511-87d5-80db2f14945f");
	    appIdList.add("da6b62ab-4846-e511-87d5-80db2f14945f");
	    appIdList.add("41b104c8-2170-4d7e-acb2-79eae7980e9b");
	    appIdList.add("ea6abfa2-44f9-46aa-8ae9-f0f209c9165d");
	    Warnings w = new Warnings();
	    RestfulResponse restfulResponse = w.queryHasWarnedByAppIdList(appIdList);
	}
}
