package catfish.flowcontroller.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import catfish.base.Logger;

public class FlowControllerDynamicConfig {

	public static Map<String,String> readSplitDyna(Iterator<String> iterator) {
	  	  	Map<String,String> map=new HashMap<String,String>();
	  	    Properties p = new Properties();
	  	    try {
	  	      InputStream stream = new FileInputStream("dynamic.properties");
	  	      p.load(stream);
	  	      stream.close();
	  	    } catch (IOException e) {
	  	      Logger.get().warn("Dynamic properties file exception, null string is used instead.", e);
	  	      return null;
	  	    }
	  	    String key="";
	  	    String val="";
	  	    while(iterator.hasNext()){
	  	    	key=iterator.next();
	  	    	val=p.getProperty(key);
	  	    	map.put(key, val);
	  	    }
	  	    return map;
	    }
	
}
