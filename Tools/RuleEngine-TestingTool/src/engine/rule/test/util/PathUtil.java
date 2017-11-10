package engine.rule.test.util;


public class PathUtil {

	//编译后的Model Class路径
	public static String ModelClassPath;
	
	//保存的测试数据路径
	public static String TestingDataPath;
	
	static{
		ModelClassPath = PathUtil.class.getClassLoader().getResource("").getPath();
		System.out.println(ModelClassPath);
		
		String homePath = ModelClassPath.split("bin")[0];
		TestingDataPath = homePath + "web/data/";
		System.out.println(TestingDataPath);
	}
	
    private static final String PackageBase = "engine.rule.model";
	
	public static String getModelPackage(String subPath)
	{
		return PackageBase + subPath;
	}

}
