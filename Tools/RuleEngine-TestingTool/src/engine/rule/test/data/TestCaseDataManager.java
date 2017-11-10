package engine.rule.test.data;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import engine.rule.test.util.PathUtil;

public class TestCaseDataManager {

	private String path;
	private File root;
	
	public static TestCaseDataManager Instance = new TestCaseDataManager(PathUtil.TestingDataPath);
	
	private TestCaseDataManager(String path)
	{
		this.path = path;
		root = new File(path);
	}
	
	public List<String> getAllTestCaseNames(String product)
	{
		return getTestCaseNames(product, null);
	}
	
	public List<String> getTestCaseNames(String product, String handler)
	{
		if(product == null)
			return null;
		String prefix = product + "_";
		if(handler != null)
			prefix += handler;
		
		List<String> nameList = new LinkedList<>();
		File[] files = root.listFiles();
		for(File file : files){
			if(file.isDirectory()) continue;
			String fileName = file.getName();
			if(fileName.startsWith(prefix))
				nameList.add(fileName);
		}
		return nameList;
	}
	
	public List<String> getFilterTestCaseNames(List<String> src, ITestCaseFilter filter)
	{
		List<String> result = new LinkedList<>();
		for(String name : src){
			if(filter.filter(name)){
				result.add(name);
			}
		}
		return result;
	}
	
	public static long getTestCaseTime(String fileName)
	{
		String[] temp = fileName.split("_");
		return Long.parseLong(temp[temp.length-1].split("\\.")[0]);
	}
}
