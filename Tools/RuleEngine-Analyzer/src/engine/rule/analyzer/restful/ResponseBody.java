package engine.rule.analyzer.restful;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;


public class ResponseBody {
	
	public static Response jsonPBodyOrNot(String callback, Object body)
	{
		String result = "{}";
		if(body != null)
		{
			result = new Gson().toJson(body);
		}
		return jsonPBodyOrNot(callback, result);
	}
	
	public static Response jsonPBodyOrNot(String callback, String body)
	{
		if(callback == null)
		{
			return Response.ok(body).build();
		}else{
			return  Response.ok(callback + "(" + body + ")").header("Content-Type", "application/javascript").encoding("utf-8").build();
		}
	}
}
