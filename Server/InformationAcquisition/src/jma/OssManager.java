package jma;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;

public class OssManager {

  public static void put(String key, byte[] bytes) {
    ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
    ObjectMetadata meta = new ObjectMetadata();
    meta.setContentLength(bytes.length);

    getClient().putObject(
        Configuration.getAliyunOssBucket(),
        key,
        stream,
        meta);

    IOUtils.closeQuietly(stream);
  }

  public static void put(String key, String localFilePath) {
    File file = new File(localFilePath);
    try {
      InputStream content = new FileInputStream(file);
      ObjectMetadata meta = new ObjectMetadata();
      meta.setContentLength(file.length());

      getClient().putObject(
          Configuration.getAliyunOssBucket(),
          key,
          content,
          meta);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("put file to OSS error!", e);
    }
  }

  public static InputStream get(String key) {
    try {
      return getClient().getObject(Configuration.getAliyunOssBucket(), key).getObjectContent();
    } catch(Exception e) {
      return null;
    }
  }
  private static OSSClient getClient() {
    return new OSSClient(
        Configuration.getAliyunOssEndPoint(),
        Configuration.getAliyunOssAccessId(),
        Configuration.getAliyunOssAccessKey());
  }
}
