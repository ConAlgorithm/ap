package catfish.manualjobarranger;

import catfish.base.Logger;
import catfish.framework.IListener;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;
import catfish.framework.http.IHTTPService;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class HttpHandler {
  private static final String QUERY_JOB = "queryjob";
  private static final String QUERY_JOB_COUNT = "queryjobcount";
  private static final String HEALTH = "health";

  public static void run(Server server) {
    ServerConfig config = new ServerConfig();
    
    server.run(config, new IPlugin[] { new IPlugin() {
      @Override
      public void init(IServiceProvider provider) {
        provider.getService(IHTTPService.class).register(QUERY_JOB, new IListener<HttpData>() {
          @Override
          public void onMessage(String message, HttpData data) {
            data.setResponseData(queryJob(data.getRequestData()));
          }
        });
        provider.getService(IHTTPService.class).register(
            QUERY_JOB_COUNT,
            new IListener<HttpData>() {
              @Override
              public void onMessage(String message, HttpData data) {
                data.setResponseData(queryJobCount(data.getRequestData()));
              }
        });
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

  private static String queryJob(String requestLiteral) {
    Gson gson = new Gson();
    RequestData request = gson.fromJson(requestLiteral, RequestData.class);
    Logger.get().info("start select message for userid : " + request.getUserId());
    MessageEntity response =
        QueryController.selectMessage(request.getProducts(), request.getUserId());
    if (response == null) {
      response = new MessageEntity();
      response.setSuccess(false);
    } else {
      response.setSuccess(true);
    }
    Logger.get().info("finish select message for userid : " + request.getUserId() + " result : " + gson.toJson(response));
    return gson.toJson(response);
  }

  private static String queryJobCount(String requestLiteral) {
    Gson gson = new Gson();
    RequestData request = gson.fromJson(requestLiteral, RequestData.class);
    return gson.toJson(QueryController.queryMessageCount(
        request.getProducts(), request.getUserId()));
  }
}
