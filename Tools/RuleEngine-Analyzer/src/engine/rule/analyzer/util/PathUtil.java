package engine.rule.analyzer.util;

import java.util.HashMap;
import java.util.Map;

import engine.rule.analyzer.resource.ModelConfig;


public class PathUtil {

	//生成的Model java路径
	public static String ModelJavaPath;
	//编译后的Model Class路径
	public static String ModelClassPath;
    //规则引擎资源Jar路径
	public static String ResourceJarPath;
	
	//RuleHandler 包名
	private static String EngineHandlerPath = ModelConfig.get("engine.rule.handler");
	//model in包名
	private static String EngineModelInPath = ModelConfig.get("engine.rule.model.in");
	//model inout包名
	private static String EngineModelInOutPath = ModelConfig.get("engine.rule.model.inout");
	//model out包名
	private static String EngineModelOutPath = ModelConfig.get("engine.rule.model.out");
	//产品与包名的对应关系
	private static final Map<String, String> ProductPackageMap = new HashMap<>();
	
	static{
		ModelClassPath = PathUtil.class.getClassLoader().getResource("").getPath();
		System.out.println(ModelClassPath);

		String homePath = ModelClassPath.split("bin")[0];
		ModelJavaPath = homePath + "models/";
		System.out.println(ModelJavaPath);

		ResourceJarPath = homePath + "resource/";
		System.out.println(ResourceJarPath);
		
		ProductPackageMap.put("POS", "app");
		ProductPackageMap.put("PDL", "pdl");
		ProductPackageMap.put("CASHLOAN", "cashloan");
	}
		
	public static String getModelPath(String type, String product)
	{
		String path = null;
		if(type.equals("in"))
			path = EngineModelInPath; 
		else if(type.equals("out"))
			path = EngineModelOutPath;
		else 
			path = EngineModelInOutPath;
		path += ProductPackageMap.get(product) + ".";
		return path;
	}
	public static String getHandlerPath(String product)
	{
		return EngineHandlerPath + ProductPackageMap.get(product);
	}
}
