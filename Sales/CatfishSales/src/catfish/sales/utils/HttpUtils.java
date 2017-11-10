package catfish.sales.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;


public class HttpUtils {
	
	public <T> HttpResponse post(T requeset,String url) throws UnsupportedEncodingException{
        List<NameValuePair> data = new ArrayList<NameValuePair>(); 
        getParamList(requeset, data);		
		return sendRequest(data, url);
    }

	public HttpResponse sendRequest(List<NameValuePair> formparams,String url){
		// 创建httppost  
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列  
		UrlEncodedFormEntity uefEntity ;

		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			Logger.get().info("Requeset content: " + EntityUtils.toString(uefEntity, "UTF-8"));	
			httppost.setEntity(uefEntity);
			Logger.get().info("executing request " + httppost.getURI());
			HttpClient httpclient=HttpClientConfig.get();
			return httpclient.execute(httppost);
		} catch (Exception e) {
			Logger.get().error("executing request " + httppost.getURI(),e);
		} 	
		return null;
	}
	
	private final static <T> List<NameValuePair> getParamList(T model,List<NameValuePair> list){
    	if(model==null){
    		return list;
    	}
    	Class<? extends Object> cc=model.getClass();
		Field[] Fields=cc.getFields();		
		for(Field field:Fields){
			try{
				if(field.get(model)!=null&&!field.get(model).toString().equals("")){
					list.add(new BasicNameValuePair(field.getName(),field.get(model).toString()));
					Logger.get().info("["+field.getName()+"]=["+ field.get(model)+"]");
				}
			}
			catch(Exception e){
				Logger.get().error(field.getName(),e);
				continue;
			}
		}
		return list;
    }
	
	public static HttpResponse postStringRequest(String request,String url){
		// 创建httppost  
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列  
		StringEntity strEntity ;

		try {
			strEntity = new StringEntity(request, "UTF-8");
			Logger.get().info("Requeset content: " + EntityUtils.toString(strEntity, "UTF-8"));	
			httppost.setEntity(strEntity);
			Logger.get().info("executing request " + httppost.getURI());
			HttpClient httpclient=HttpClientConfig.get();
			return httpclient.execute(httppost);
		} catch (Exception e) {
			Logger.get().error("executing request " + httppost.getURI(),e);
		} 	
		return null;
	}
}
