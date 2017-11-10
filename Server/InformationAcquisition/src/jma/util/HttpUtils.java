package jma.util;

import grasscarp.application.model.PDLApplication;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import jma.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;

public class HttpUtils {

    private static class UtilDateSerializer implements JsonSerializer<Date>,JsonDeserializer<Date> {   
        @Override
        public JsonElement serialize(Date date, Type type,
          JsonSerializationContext context) {
         return new JsonPrimitive(date.getTime());
        }
        
        @Override
        public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context)
          throws JsonParseException {
         return DateUtils.toLocal(new Date(element.getAsJsonPrimitive().getAsLong()));
        }   
       }
    
    private final static Gson  GSON = new GsonBuilder()
    .registerTypeAdapter(Date.class, new UtilDateSerializer())
    .setDateFormat(DateFormat.LONG).setPrettyPrinting().create();
    
    private final static int HTTP_MAX_RETRY = Configuration.getApplicationServiceMaxRetries();
    
    public final static String SYMBOL_EQUAL = "=";
    public final static String SYMBOL_AND = "&";
    public final static String SYMBOL_PATH_SEPARATOR = "/";
    public final static String SYMBOL_QUESTION_MARK = "?";
    
    public static String financeServiceUrl;
    public static String gpsServiceUrl;
    public static String appServiceUrl;
	public static String userServiceUrl;
	static
	{
		String url = Configuration.getApplicationServiceUrl();
		if(url.endsWith("/"))
		{
			appServiceUrl = url + "application/";
			userServiceUrl = url + "account/";
		}else{
			appServiceUrl = url + "/application/";
			userServiceUrl = url + "/account/";
		}
		
		gpsServiceUrl = Configuration.getGpsServiceUrl();
		financeServiceUrl = Configuration.getFinanceServiceUrl();
	}
	
    public static String invokeGet(final String urlString) {
        Logger.get().info("Start invoking get request :" + urlString);
        
        String result = null;
        try {
            
            result = HttpClientApi.CallService(
                    HTTP_MAX_RETRY, 
                    new ServiceHandler<String, String>() {

                        @Override
                        public String createUrl() {
                            return urlString;
                        }
        
                        @Override
                        public String OnRequest(String url) {
                            return HttpClientApi.get(urlString);
                        }
        
                        @Override
                        public String OnSuccess(String result) {
                            return result;
                        }
        
                        @Override
                        public String OnError(String result) {
                            return null;
                    }
            });
            
            return result;
        } catch (Exception e) {
            Logger.get().warn("http request error from visiting url : " + urlString);
            return null;
        }
    }
    
    public static <T>  T invokeGet(final String urlString, Type type) { 
        String responseString = invokeGet(urlString);
        
        try {
            return GSON.fromJson(responseString, type);
        } catch (Exception e) {
            Logger.get().warn(String.format(
                    "error parse json string to %s : %s", 
                    type.getClass().getSimpleName(),
                    responseString), e
                    );
        }
        
        return null;
    }
    
    public static String buildGetParamString(Map<String, String> params) {
        
        StringBuilder sb = new StringBuilder();
        
        for (String key : params.keySet()) {
            sb.append(key)
            .append(SYMBOL_EQUAL)
            .append(encodeString(params.get(key)))
            .append(SYMBOL_AND);
        }
        
        String result = sb.toString();
        if(result.endsWith(SYMBOL_AND)) {
            int length = result.length() - SYMBOL_AND.length();
            result = result.substring(0, Math.max(length, 1));
        }
        
        return result;
    }
    
    private static String encodeString(String str) {
        try {
            String result = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
            return result;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            Logger.get().warn(
                    "url encode error : " + e);
            return null;
        }
    }
}
