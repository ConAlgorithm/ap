package engine.rule.analyzer.resource;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.huateng.toprules.core.annotation.ModelInstance;

import catfish.base.Logger;

public class JarLoader{

	private static Map<String, ClassLoader> loaderMap = new ConcurrentHashMap<>();
	
	public static  ClassLoader getLoader(String jarName)
	{
		try{
			if(!loaderMap.containsKey(jarName))
			{
				URL url = new URL("file:" + jarName);
				URLClassLoader loader = new URLClassLoader(new URL[]{url}, ModelInstance.class.getClassLoader());
				loaderMap.put(jarName, loader);
			}
			
			return loaderMap.get(jarName);
		}catch(Exception e)
		{
			Logger.get().error("Cannot create ClassLoader of " + jarName, e);
			return null;
		}		
	}
}

