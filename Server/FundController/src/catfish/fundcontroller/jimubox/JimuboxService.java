package catfish.fundcontroller.jimubox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.HttpClientConfig;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;
import catfish.fundcontroller.Configuration;
import catfish.fundcontroller.util.Signature;

class JimuboxService {
    private static final String Ticket_Header = "Ticket";
    private static final String Location_Header = "Location";
    
    private static final String SPID= StartupConfig.get("catfish.jimubox.SPID");
    private static final String UserKey= StartupConfig.get("catfish.jimubox.UserKey");
    private static final String JimuboxUrl=StartupConfig.get("catfish.jimubox.url");
    
    HttpResponse post(ApplicationModel appModel, File idPhoto, File iou, File agreement, File transferVoucher) throws UnsupportedEncodingException{
        Logger.get().info("JimuboxService: post data");
        
        MultipartEntity mutiEntity = new MultipartEntity();
        
        Class<? extends ApplicationModel> c=appModel.getClass();
		Field[] fields=c.getDeclaredFields();
		for(Field field:fields){
			try{
				mutiEntity.addPart(field.getName(),getStringBody(field.get(appModel).toString()));
				Logger.get().info("["+field.getName()+"]=["+ field.get(appModel)+"]");
			}
			catch(Exception e){
				Logger.get().error(field.getName(),e);
				continue;
			}
		}
		mutiEntity.addPart("SPID", getStringBody(SPID));
        addFileBody(mutiEntity, "idPhoto", idPhoto);
        addFileBody(mutiEntity, "iou", iou);
        addFileBody(mutiEntity, "agreement", agreement);
        addFileBody(mutiEntity, "transferVoucher", transferVoucher);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(Ticket_Header, generateTicket(appModel));
		HttpResponse response = postDataToServer(JimuboxUrl, mutiEntity, headers,1);
		Logger.get().info("JimuboxService: post data response "+ response);
		return response;

    }
    
    private HttpResponse postDataToServer(String url, HttpEntity entity, Map<String, String> headers, int RetryTimes){
        HttpPost postRequest = new HttpPost(url);
        postRequest.setEntity(entity);
        for(String key:headers.keySet()){
            postRequest.setHeader(key, headers.get(key));
        }
        
        try {
        	
        	HttpResponse response = HttpClientConfig.get().execute(postRequest);
        	if(response.getStatusLine().getStatusCode() != HttpClientConfig.RESPONSE_CODE_OK){
        		onError(response);
        	}
        	//Release the connection
        	//postRequest.abort();
        	
            return response;
          } catch (IOException e) {
            //Release the connection
              postRequest.abort();
            throw new RuntimeException(e);
          }
    }
    
    private void onError(HttpResponse response){
        Logger.get().warn("Got unexpected http response: " + response.getStatusLine());
        
        if(response.containsHeader(Location_Header)){
            String location = response.getFirstHeader(Location_Header).getValue();
            Logger.get().info("Error URL Location: "+ location);
            String errResponse = HttpClientApi.get(location);
            Logger.get().info("Error URL Location response: "+ errResponse);
//            ApplicationModel result = new Gson().fromJson(errResponse, ApplicationModel.class);
//            if(result != null){
//                Logger.get().info(new Gson().toJson(result));
//            }
        }
        
        //throw new RuntimeException("Got unexpected http response: " + response.getStatusLine());
    }
    
    private StringBody getStringBody(String data) throws UnsupportedEncodingException{
        return new StringBody(data, Charset.forName(Configuration.UTF_8));
    }
    
    private void addFileBody(MultipartEntity mutiEntity, String key, File file){
        //file = new File("D:\\Book2.pdf");
        if(file == null){
            return;
        }
        
        FileBody fileBody = new FileBody(file);
        mutiEntity.addPart(key, fileBody);
    }
    
    private String generateTicket(ApplicationModel appModel){
        
        Class<? extends ApplicationModel> c=appModel.getClass();
		Field[] fields=c.getDeclaredFields();		
		Map<String,String> map=new TreeMap<String,String>( new Comparator<String>(){

			@Override
			public int compare(String obj1, String obj2) {
				// TODO Auto-generated method stub
				return obj1.toUpperCase().compareTo(obj2.toUpperCase());
			}
			
		});
		//Arrays.sort(fields, new SortByFieldName());
		for(Field field:fields){
			try{
				if(field.get(appModel)!=null&&field.get(appModel).toString()!="")
				map.put(field.getName(), field.get(appModel).toString());
			}
			catch(Exception e){
				Logger.get().error(field.getName(),e);
				continue;
			}
		}
		map.put("SPID", SPID);
		String org = "";
		Set<String> keySet = map.keySet(); 
		  Iterator<String> iter = keySet.iterator(); 
		  while(iter.hasNext()){ 
		   String key = iter.next(); 
		   System.out.println(key+":"+map.get(key)); 
		   org+=map.get(key);
		  }
		  org += UserKey;
		  System.out.println(org); 
        String ticket = Signature.MD5(org);
        System.out.println(ticket); 
        return ticket;
    }
    
}
