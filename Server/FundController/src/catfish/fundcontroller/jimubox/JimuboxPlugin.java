package catfish.fundcontroller.jimubox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.queue.QueueMessager;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.fundcontroller.Configuration;
import catfish.fundcontroller.StorageService;
import catfish.fundcontroller.util.MessagerHelper;

public class JimuboxPlugin  implements IPlugin,  IQueueListener{
    private static final String JIMUBOX_FINISHED = "JMBox";
    private static final String JIMUBOX_RETRY = "JMBox_Retry";
    
    private DataService dataService = new DataService();
    private StorageService storageService = new StorageService();
    private JimuboxService jimuboxService= new JimuboxService();
    private IQueue jsQueue;
    private List<String> TriggerTimers;
    private static final String Location_Header = "Location";
    
    @Override
    public void init(IServiceProvider sp) {
    	TriggerTimers=new ArrayList<String>();
		String[] timers = Configuration.getIntervalTimes().split(",");
		for (int i = 0; i < timers.length; i++) {
			TriggerTimers.add(timers[i].toString().trim());
		}
        IQueueService queueService = sp.getService(IQueueService.class);
        this.jsQueue = queueService.getQueue(Configuration.getQueueName(),"JimuboxPlugin");
        jsQueue.register(JIMUBOX_FINISHED, this);
        jsQueue.register(JIMUBOX_RETRY, this);
    }
    
    @Override
    public void onMessage(String message, String data) {
        QueueMessager messager = QueueMessager.fromString(data);
        if(messager==null){
            Logger.get().warn("JimuboxPlugin: messager is null");
            return;
        }
        String appId = messager.getAppId();
        if(appId ==null){
            Logger.get().warn("JimuboxPlugin: appId is null");
            return;
        }
        
        int retrytime=0;
        if(messager.getJobDataInt()!=null){
        	retrytime=messager.getJobDataInt();
        }
        
        try
        {
        	
        	if(retrytime<Configuration.getRequestTime()){
        		int intervaltime=Integer.parseInt(TriggerTimers.get(retrytime));
        		String fundTag=dataService.getFundTagByAppId(appId);
        		if(fundTag==null||!fundTag.equals(Configuration.getJMBoxFundTag())){
        			Logger.get().info("申请不是积木盒子，appId="+appId);
        			return;
        		}
	            ApplicationObject appObj = dataService.getApplication(appId);
	            if(appObj==null){
	            	Logger.get().info("无此申请，appId："+appId);
	            	return;
	            }
	            ApplicationModel appModel = dataService.convert(appObj);
	            File idPhoto = saveToFile(appObj.idPhotoPath, "IdPhoto", ".png");
	            File iou = saveToFile(appObj.iouPath, "IOUPhoto", ".png");
	            File agreement = saveToFile(appObj.agreementPath, "agreement", ".pdf");
	            File transferVoucher = saveToFile(appObj.transferVoucher, "agreement", ".png");
	            if(idPhoto==null&&iou==null&&agreement==null&&transferVoucher==null){
	            	MessagerHelper.sendMessages(jsQueue, appId, JIMUBOX_RETRY, ++retrytime, intervaltime, 1 );
	            	Logger.get().error("未成功下载文件,appId:"+appId);
	            	return;
	            }
	            HttpResponse response=jimuboxService.post(appModel, idPhoto, iou, agreement, transferVoucher);
	            String result=EntityUtils.toString(response.getEntity());
	            if(response.getStatusLine().getStatusCode() != HttpClientConfig.RESPONSE_CODE_OK){
	            	String location = response.getFirstHeader(Location_Header).getValue();
		            Logger.get().info("Error URL Location: "+ location);
		            result = HttpClientApi.get(location);
	        	}
	            dataService.saveJMBoxResponse(result, appId, retrytime);
	            if(response.getStatusLine().getStatusCode() != HttpClientConfig.RESPONSE_CODE_OK){
	            	MessagerHelper.sendMessages(jsQueue, appId, JIMUBOX_RETRY, ++retrytime, intervaltime, 1 );
	            }
	    		Logger.get().info(result);
        	}
        	else{
        		Logger.get().info(retrytime+"次重试后，积木盒子无正确响应，appId="+appId);
        	}
        		
        }catch(Exception ex){
            Logger.get().error("JimuboxPlugin on message exception", ex);
        }
    }
    
    @SuppressWarnings("resource")
    private File saveToFile(String key, String prefix, String suffix){
        File file = null;
        if(key == null || key.length() ==0){
            return file;
        }
        
        InputStream input = null;
        OutputStream os=null;
        
        try{
            input = storageService.download(key);
        }catch(Exception ex){
            Logger.get().error("download file exception", ex);
            return file;
        }
        
        try{
            file = File.createTempFile(prefix, suffix);
            os=new FileOutputStream(file);
            byte buffer[]=new byte[4*1024];
            int len = 0;
            while((len = input.read(buffer)) != -1)
            {
                os.write(buffer,0,len);
            }
            os.flush();
        }catch(Exception e){
            Logger.get().error(String.format("save file %s exception",key), e);
        }finally{
            try{
                os.close();
            }catch(Exception e){
                Logger.get().error("close output stream exception", e);
            }
        }
        
        return file;
    }
}
