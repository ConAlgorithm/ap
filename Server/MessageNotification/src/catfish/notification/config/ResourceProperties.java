package catfish.notification.config;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import catfish.base.Logger;

public class ResourceProperties {

  private static final String UTF_8 = "UTF-8";
  private static Properties properties;

  private static void init() {
    if (properties == null) {
      try {
        InputStreamReader inputStream = new InputStreamReader(new FileInputStream("resources.properties"), UTF_8);
        synchronized(ResourceProperties.class){
          if (properties == null) {
            properties = new Properties();
          }
        }
        properties.load(inputStream);
      } catch (Exception e) {
        Logger.get().warn("Load resources error:　" , e);  
      }
    }
  }
  
  public static String getProperty(String name) {
    init();
    return properties.getProperty(name);
  }
 
}
