package engine.rule.analyzer.resource;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import catfish.base.Logger;
import catfish.base.exception.CatfishException;
import engine.rule.analyzer.AnalyzerException;

public class JarScanner {

	private static final String POSTFIX = ".class";
	
	private String file;
	private JarFile jarFile;
	
	public JarScanner(String file)
	{
		try{
			this.file = file;
			jarFile = new JarFile(file);
		}catch(Exception e)
		{
			Logger.get().error(e);
			throw new CatfishException(AnalyzerException.JAR_SCAN_ERROR);
		}			
	}
	
	public List<String> scanClasses(String pkg)
	{
		pkg = getClearUpPkg(pkg);
		List<String> classNames = new LinkedList<>();
		
		try {		
			Enumeration<JarEntry> en = jarFile.entries();
			while(en.hasMoreElements())
			{
				String entryName = en.nextElement().getName();
				String className = null;
				if(pkg == null)
				{
					if(entryName.endsWith(POSTFIX))
						className = entryName.replace(POSTFIX, "").replace('/', '.');
				}
				else{
					String pkgPath = pkg.replace('.', '/');
					if(entryName.contains("/") && entryName.substring(0, entryName.lastIndexOf("/")).equals(pkgPath) && entryName.endsWith(POSTFIX))
					{
						className = entryName.replace(POSTFIX, "").replace('/', '.');
					}
				}
				if(className != null)
				{
					classNames.add(className);
				}
			}	 		
		} catch (Exception e) {
			Logger.get().error("Cannot scan class files in " + pkg, e);
		}
		return classNames;
	}

	public List<Class<?>> createAllClasses()
	{
		return createClasses(null);
	}
	
	public Class<?> createClass(String className)
	{
		Class<?> cls = null;
	    try{
	    	ClassLoader loader = JarLoader.getLoader(file);
	    	 cls = loader.loadClass(className);
	    }catch(Exception e)
	    {
	    	Logger.get().error("Can not loadClass: " + className, e);
	    }
	    return cls;
	}
	
	public List<Class<?>> createClasses(String pkg)
	{
		pkg = getClearUpPkg(pkg);
		List<Class<?>> classes = new LinkedList<>();
		try {
			ClassLoader loader = JarLoader.getLoader(file);
			List<String> classNames = this.scanClasses(pkg);
			for(String className : classNames)
			{
				Class<?> cls = createClass(className);
				if(cls != null)
					classes.add(cls);				
			}
		} catch (Exception e) {
			Logger.get().error("Get JarLoader: " + file + " failed!", e);
		}	
		return classes;
	}
	
	public void close()
	{
		try {
			jarFile.close();
		} catch (IOException e) {
			Logger.get().error("Can not close jarFile: " + file, e);
		}
	}
	
	public String getFile() {
		return file;
	}
	
	private String getClearUpPkg(String pkg)
	{
		if(pkg.endsWith("."))
		   return pkg.substring(0, pkg.length()-1);
		return pkg;
	}
}
