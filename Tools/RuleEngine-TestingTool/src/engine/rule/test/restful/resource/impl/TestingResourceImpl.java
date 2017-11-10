package engine.rule.test.restful.resource.impl;

import java.util.List;

import engine.rule.test.Configuration;
import engine.rule.test.data.ExecuteResult;
import engine.rule.test.data.ValidateResult;
import engine.rule.test.restful.resource.TestingResource;
import engine.rule.test.service.TestingService;
import engine.rule.test.service.UserAccountService;
import engine.rule.test.service.object.BasicResponse;
import engine.rule.test.service.object.TestCaseFile;

public class TestingResourceImpl implements TestingResource{

	private TestingService testingService = new TestingService();
	private UserAccountService userService = new UserAccountService();
	
	public String welcome()
	{
		return "welcome to RuleEngine-TestingTool!";
	}
	
	public BasicResponse compile(String modelPackage)
	{
		return testingService.compile(modelPackage);
	}
	
	//上传并执行测试用例
	public BasicResponse submitTestingDataAndExecute(final byte[] body)
	{
		return testingService.submitTestingDataAndExecute(body);
	}
	
	//上传测试用例
	public BasicResponse uploadTestCase(String product, String handler, byte[] body)
	{
		return testingService.uploadTestCase(product, handler, body);
	}
	
	//执行测试
	public BasicResponse executeTestCase(final byte[] body)
	{
		return testingService.executeTestCase(body);
	}
	
	//获取测试结果
	public ValidateResult getTestingResult(String product)
	{
		return testingService.getTestingResult(product);
	}
	
	//获取执行结果
	public ExecuteResult getExecuteResult(String product)
	{
        return testingService.getExecuteResult(product);	
	}
	
	//获取验证结果
	public ValidateResult getValidateResult(String product)
	{
		return testingService.getValidateResult(product);
	}
	
	//停止测试
	public BasicResponse stopTesting(String product)
	{
		return testingService.stopTesting(product);
	}
	
	//清除日志
	public BasicResponse clearLog(String product)
	{
		return testingService.clearLog(product);
	}
	
	//登录验证
	public BasicResponse login(final byte[] body)
	{
		return userService.login(body);
	}
	
	//更新handler中的model
    public BasicResponse updateModelsOfHandler(String env, String type, String product, byte[] data)
    {
        return testingService.updateModelsOfHandler(env, type, product, data);
    }
    
	//按时间和handler查询案例列表
	public List<TestCaseFile> getTestCases(String product, String handler, String date)
	{
		return testingService.getTestCases(product, handler, date);
	}

	@Override
	public String getAnalyzerUrl() {
		return Configuration.getAnalyzerUrl();
	}
}
