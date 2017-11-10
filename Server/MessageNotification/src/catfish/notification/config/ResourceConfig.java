package catfish.notification.config;

import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;

public class ResourceConfig {
  
  public static String getResourceByName(String name, Receiver receiver) {
    return ResourceProperties.getProperty("ALL_ALL_" + receiver + "_" + name);
  }
  
  public static String getResourceByName(String name, Receiver receiver, Channel channel) {
    return ResourceProperties.getProperty("ALL_" + channel + "_" + receiver + "_" + name);
  }
  
  public static String getResourceByName(String name, Receiver receiver, Product product) {
    return ResourceProperties.getProperty(product + "_ALL_" + receiver + "_" + name);
  }
  
  public static String getResourceByName(String name, Receiver receiver, Channel channel, Product product) {
    return ResourceProperties.getProperty(product + "_" + channel + "_" + receiver + "_" + name);
  }
  
}
