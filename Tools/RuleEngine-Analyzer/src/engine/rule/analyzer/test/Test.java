package engine.rule.analyzer.test;

import java.io.IOException;
import java.util.List;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import engine.rule.analyzer.resource.JarScanner;
import engine.rule.analyzer.resource.ModelGenerator;
import engine.rule.analyzer.util.PathUtil;

public class Test {

	public static void main(String[] args)
	{
		StartupConfig.initialize();
		Logger.initialize();
		
		System.out.println(PathUtil.ModelClassPath);
		
		String url = "D:/ap/Tools/RuleEngine-Analyzer/resource/RuleEngine.jar";
		JarScanner scanner = new JarScanner(url);
		List<Class<?>> classes = scanner.createClasses("engine.rule.model.in");
		for(Class cls : classes)
		{
			ModelGenerator.generateClassFile(cls, PathUtil.ModelJavaPath);
		}

	}
}
