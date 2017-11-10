package jma;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.framework.IListener;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;
import catfish.framework.http.IHTTPService;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class HttpHandler {
	private static final String HEALTH = "health";

	  public static void run(Server server) {
	    ServerConfig config = new ServerConfig();
	    
	    server.run(config, new IPlugin[] { new IPlugin() {
	      @Override
	      public void init(IServiceProvider provider) {
	        provider.getService(IHTTPService.class).register(HEALTH, new IListener<HttpData>() {
				@Override
				public void onMessage(String message, HttpData data) {
					// TODO Auto-generated method stub
					Map<String, Object> result =new HashMap<String, Object>();
					result.put("status", "UP");
					data.setResponseData(new Gson().toJson(result));
				}
			});
	      }
	    }}, new ServiceFactory());
	  }
}
