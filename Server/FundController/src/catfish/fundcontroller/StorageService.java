package catfish.fundcontroller;

import java.io.InputStream;

import catfish.base.StartupConfig;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

public class StorageService {
    private String ossEndpoint;
    private String ossAccessId;
    private String ossAccessKey;
    private String bucket;
    
    public StorageService(){
        this.ossEndpoint = StartupConfig.get("catfish.aliyun.oss.Endpoint");
        this.ossAccessId = StartupConfig.get("catfish.aliyun.oss.AccessId");
        this.ossAccessKey = StartupConfig.get("catfish.aliyun.oss.AccessKey");
        this.bucket = StartupConfig.get("catfish.aliyun.oss.Bucket");
    }
    
    public boolean save(String key, InputStream input, long length, String contentType){
        OSSClient client = new OSSClient(ossEndpoint, ossAccessId, ossAccessKey);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(length);
        objectMeta.setContentType(contentType);

        client.putObject(this.bucket, key, input, objectMeta);
        return true;
    }
    
    public InputStream download(String key) {
      OSSClient client = new OSSClient(ossEndpoint, ossAccessId, ossAccessKey);
      OSSObject obj = client.getObject(this.bucket, key);
      return obj.getObjectContent();
    }
}
