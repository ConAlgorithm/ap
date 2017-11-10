package catfish.flowcontroller.rest;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.framework.IListener;
import catfish.framework.http.HttpData;

public class HatlthService implements IListener<HttpData> {

	@Override
	public void onMessage(String message, HttpData data) {
		// TODO Auto-generated method stub
		Map<String, Object> result =new HashMap<String, Object>();
		result.put("status", "UP");
		data.setResponseData(new Gson().toJson(result));
	}

}
