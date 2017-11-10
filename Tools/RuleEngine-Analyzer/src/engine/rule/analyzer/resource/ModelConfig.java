package engine.rule.analyzer.resource;

import catfish.base.StartupConfig;

public class ModelConfig {

	private static final String FILE = "model.properties";
	
	static{
		StartupConfig.initialize(FILE);
	}
	
	public static String get(String key)
	{
		return StartupConfig.get(key, "");
	}
}
