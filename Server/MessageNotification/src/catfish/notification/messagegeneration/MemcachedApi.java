package catfish.notification.messagegeneration;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

public class MemcachedApi {
	
    private static MemcachedClient cache = null;    
    private static AuthDescriptor ad = null;
    public static MemcachedClient getMemcachedClient() throws IOException{

       try {
           if(ad == null){
               ad = new AuthDescriptor(new String[]{"PLAIN"}, new PlainCallbackHandler(MemcachedUtils.getMemcachedUsername(), MemcachedUtils.getMemcachedPassword()));
           }
           if(cache == null){
               cache = new MemcachedClient(
                       new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY)
                       .setAuthDescriptor(ad)
                       .build(),
                       AddrUtil.getAddresses(MemcachedUtils.getMemcachedHost() + ":" + MemcachedUtils.getMemcachedPort()));        
           }
       } catch (Exception e) {
           e.printStackTrace();
       } 
       return cache;    
    }
	
}
