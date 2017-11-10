package jma.thirdpartyservices.reg007;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import thirdparty.config.REG007Configuration;
import thirdparty.reg007.ResponseData;
import thirdparty.reg007.ResponseData.RegisterWebInfo;
import catfish.base.Logger;
import catfish.base.httpclient.Configuration;

import com.google.gson.Gson;

public class REG007InfoCrawling {

	private String key;
	
	private HttpClient client = new DefaultHttpClient();
	
	private Map<String, String> ajaxPara = new HashMap<String, String>();
	
	private List<RegisterWebInfo> result = new ArrayList<RegisterWebInfo>();
		
	@SuppressWarnings("serial")
	private Map<String, String> headers = new HashMap<String, String> () {
		{
			put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
			put("Referer", "http://www.reg007.com/");
			put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		}
	};
	
	public REG007InfoCrawling(String key) {
		this.key = key;
	}
	
	public List<RegisterWebInfo> run() {
		try{
			getCookieByFirstVisitor();
			getAjaxParameterBySearchRequest();
			return getRegisterWebsiteByAjaxRequest();
		} catch (Exception ex) {
			Logger.get().warn(String.format("Get REG007 Info error! info : %s", ex.toString()), ex);
			return null;
		}
	}
	
	public void getCookieByFirstVisitor() {
		HttpGet get = new HttpGet(REG007Configuration.getReg007VisitorUrl());
		try {
			HttpResponse response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("REG007 Page not fond!");
			}
		} catch(Exception ex) {
			Logger.get().warn(String.format("Can't open Reg007, please check it! key : %s info : %s", this.key, ex.toString()), ex);
		} finally {
			get.abort();
		} 
	}
	
	public void getAjaxParameterBySearchRequest() {		
		HttpGet get = new HttpGet(REG007Configuration.getReq007SearchUrl() + this.key);
		
		for(String key : headers.keySet()) {
			get.setHeader(key, headers.get(key));
		}
		
		try {				
			HttpResponse response =  client.execute(get);
			String responseHtml = EntityUtils.toString(response.getEntity());				
			Pattern p = Pattern.compile("var i=(.*);var t=(.*);var q=\"(.*)\";var h=\"(.*)\";");
			Matcher m = p.matcher(responseHtml);
	
			while(m.find()) {
				ajaxPara.put("i", m.group(1));
				ajaxPara.put("t", m.group(2));
				ajaxPara.put("q", m.group(3));
				ajaxPara.put("h", m.group(4));
			}
			
		} catch(Exception ex) {
			Logger.get().warn(String.format("Get REG007 search page error! key: %s info: %s", this.key, ex.toString()), ex);
		} finally {
			get.abort();
		}
	}
	
	public List<RegisterWebInfo> getRegisterWebsiteByAjaxRequest() {	
		HttpPost post = new HttpPost(REG007Configuration.getReg007AjaxRequestUrl());
		headers.put("Referer", REG007Configuration.getReq007SearchUrl() + this.key);
		headers.put("X-Requested-With", "XMLHttpRequest");
		
		for(String key : headers.keySet()) {
			post.setHeader(key, headers.get(key));
		}
	    
	    int retryCount = REG007Configuration.getMaxRetryCount();
	    
		while(true) {
			try {
				List<BasicNameValuePair> nameValueFields = new ArrayList<>();
			    for (Entry<String, String> field : ajaxPara.entrySet()) {
			      nameValueFields.add(new BasicNameValuePair(field.getKey(), field.getValue()));
			    }
				post.setEntity(new UrlEncodedFormEntity(nameValueFields, Configuration.UTF_8));
				retryCount--;
				if (retryCount < 0 ){
					return this.result;
				}
				
				HttpResponse response = client.execute(post);
				String responseHtml = EntityUtils.toString(response.getEntity());
				
				ResponseData tempData = new Gson().fromJson(responseHtml, ResponseData.class);
				this.result.addAll(tempData.getData());
				if (!tempData.getInfo().equals("100%")) {
					Integer oldT = Integer.parseInt(ajaxPara.get("t")) + 1; 
					ajaxPara.put("t", oldT.toString());
				} else {
					return this.result;
				}		
			} catch(Exception ex) {
				Logger.get().warn(String.format("Get ajax response error! key: %s,remaining retryCount :%d,request url :%s", this.key,retryCount,REG007Configuration.getReg007AjaxRequestUrl()),ex);
			} 
		}
	}	
}