package catfish.plugins.pdfgenerator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSErrorCode;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.MimeType;

public class OssApi {
	
	private static String AliyunKeyId = StartupConfig.get("catfish.aliyun.oss.Key");
	private static String AliyunKeySecret = StartupConfig.get("catfish.aliyun.oss.Secret");
	private static String AliyunOssEndpoint = StartupConfig.get("catfish.aliyun.oss.Endpoint");
	private static String Bucket = StartupConfig.get("catfish.aliyun.oss.UserDataBucket");
	private static String AttachmentBucket = StartupConfig.get("catfish.aliyun.oss.AttachmentBucket");

    public  static InputStream get(String key) {
    	Logger.get().info("Get file from OSS : " + key);
    	OSSClient client = new OSSClient(AliyunOssEndpoint, AliyunKeyId, AliyunKeySecret);
    	OSSObject obj = client.getObject(new GetObjectRequest(Bucket, key));
    	return obj.getObjectContent();
    }
    
    
    public static PutObjectResult savePDF(String key, InputStream ins) {
    	return save(key, ins, "application/pdf", 300);
    }
    
    public static void delete(String key) {
    	try {
    		OSSClient client = new OSSClient(AliyunOssEndpoint, AliyunKeyId, AliyunKeySecret);
    		client.deleteObject(Bucket, key);
    	} catch(Exception e) {
    		Logger.get().error(e);
    	}
    }
    
    public static String getUrl(String key) {
		ClientConfiguration config = new ClientConfiguration();
		config.setConnectionTimeout(500);
		OSSClient client = new OSSClient(AliyunOssEndpoint, AliyunKeyId, AliyunKeySecret, config);
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MINUTE, 5);
    	URL url = client.generatePresignedUrl(Bucket, key, cal.getTime());
    	return url.toString();
    }
    
    private static PutObjectResult save(String key, InputStream ins, String contentType, int timeout) {
    	try {
    		ClientConfiguration config = new ClientConfiguration();
    		config.setConnectionTimeout(timeout);
    		
    		OSSClient client = new OSSClient(AliyunOssEndpoint, AliyunKeyId, AliyunKeySecret, config);
    		ObjectMetadata metadata = new ObjectMetadata();
    		
    		metadata.setContentType(contentType);
    		metadata.setContentLength(ins.available());
    		PutObjectResult result =  client.putObject(Bucket, key, ins, metadata);
    		
    		Logger.get().info(String.format("object saved to oss :\n type=%s,\n key=%s", contentType, key));
    		return result;
    		
    	} catch(Exception e) {
    		Logger.get().error(e);
    	}
    	
    	return null;
    }
}
