package engine.rule.test.model;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import engine.rule.test.util.PathUtil;

public class ModelCompiler {
	
	private static JavaCompiler compiler = null;
	private static StandardJavaFileManager manager = null;
	
	static{		
		init();
	}
	
	private static void init()
	{
		compiler = ToolProvider.getSystemJavaCompiler();
		manager = compiler.getStandardFileManager(null, null, null);
	}
	
	public static boolean compileFile(String javaPath, String javaName, String targetPath)
	{	
		try {
			Iterable it = manager.getJavaFileObjects(new File(javaPath, javaName));	
			CompilationTask task = compiler.getTask(null, manager, null, Arrays.asList("-d", targetPath), null, it);			
			task.call();	
			manager.close();
			return true;
		} catch (IOException e) {
			Logger.get().error("compile java " + javaName +" failed!", e);
			return false;
		}
	}
	public static boolean compileString(String className, String codeStr, String targetPath)
	{
		try {
			JavaFileObject fileObj = new JavaStringObject(className, codeStr);
			CompilationTask task = compiler.getTask(null, manager, null, Arrays.asList("-d", targetPath), null, Arrays.asList(fileObj));			
			task.call();	
			manager.close();
			return true;
		} catch (IOException e) {
			Logger.get().error("compile java " + className +" failed!", e);
			return false;
		}
	}
}
