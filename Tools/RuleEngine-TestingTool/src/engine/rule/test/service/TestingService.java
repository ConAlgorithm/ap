package engine.rule.test.service;

import static engine.rule.test.service.object.BasicResponse.Fail;
import static engine.rule.test.service.object.BasicResponse.Success;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;

import catfish.base.business.ruleengine.BaseInfo;
import catfish.base.business.ruleengine.HandlerInfo;
import catfish.base.business.ruleengine.HandlersInfo;
import catfish.base.business.ruleengine.RuleEngineManager;
import catfish.base.exception.CatfishException;
import catfish.base.httpclient.HttpClientApi;

import com.google.gson.Gson;

import engine.rule.test.Configuration;
import engine.rule.test.TestingToolException;
import engine.rule.test.data.ExecuteResult;
import engine.rule.test.data.ITestCaseFilter;
import engine.rule.test.data.TestCaseData;
import engine.rule.test.data.TestCaseDataManager;
import engine.rule.test.data.TestCaseResult;
import engine.rule.test.data.ValidateResult;
import engine.rule.test.service.object.BasicResponse;
import engine.rule.test.service.object.TestCaseFile;
import engine.rule.test.tester.TesterManager;

public class TestingService {

	private RuleEngineManager handersManager = new RuleEngineManager();
	private static final int OneDayMilis = 24*60*60*1000;
	
    //编译model
	public BasicResponse compile(String modelPackage)
	{
		String listUrl = Configuration.getAnalyzerUrl() + "package/" + modelPackage + "/list";
		Map<String, Object> result = HttpClientApi.getGson(listUrl);
		return Success;
	}
	
	//上传并执行测试用例
	public BasicResponse submitTestingDataAndExecute(final byte[] body)
	{
		String request = new String(body);
		TestCaseData  data = new Gson().fromJson(request, TestCaseData.class);
		TesterManager.Instance.runCase(data);
		return Success;
	}
	
	//上传测试用例
	public BasicResponse uploadTestCase(String product, String decisionPoint, byte[] body)
	{
		String request = new String(body);
		String file = request.split("\r\n\r\n")[1];	
		TestCaseData  data = new TestCaseData(product, decisionPoint, file);
        data.storeData();		
		return Success;
	}
	
	//执行测试
	public BasicResponse executeTestCase(final byte[] body)
	{
		String request = new String(body);
		System.out.println(request);
		TestCaseData  data = new Gson().fromJson(request, TestCaseData.class);
        TesterManager.Instance.runCase(data);
        return Success;
	}
	
	//获取测试结果
	public ValidateResult getTestingResult(String product)
	{
		return TesterManager.Instance.getResult(product).getValidate();
	}
	
	//获取执行结果
	public ExecuteResult getExecuteResult(String product)
	{
		TestCaseResult result = TesterManager.Instance.getResult(product);
		return result == null ? null : result.getExecute();
	}
	
	//获取验证结果
	public ValidateResult getValidateResult(String product)
	{
		TestCaseResult result = TesterManager.Instance.getResult(product);
		return result == null ? null : result.getValidate();
	}
	
	//停止测试
	public BasicResponse stopTesting(String product)
	{
		TesterManager.Instance.stopCase(product);
		return Success;
	}
	
	//清除日志
	public BasicResponse clearLog(String product)
	{
		TesterManager.Instance.getResult(product).getValidate().removeMsgs();
		return Success;
	}
			
	//更新handler中的model
    public BasicResponse updateModelsOfHandler(String env, String type, String product, byte[] data)
    {
    	String request = new String(data);
    	System.out.println(request);
    	HandlerInfo handler = new Gson().fromJson(request, HandlerInfo.class);
    	BaseInfo query = new BaseInfo(env, product, type);
		try{
			HandlersInfo handlers = handersManager.load(query, HandlersInfo.class);
    	    if(handlers != null)
    	    {
    	    	handlers.handlers.put(handler.name, handler);
    	    }else
    	    {
    	    	handlers = new HandlersInfo(env, product, type);
    	    	handlers.addHandler(handler);
    	    }
    	    handersManager.save(query, handlers);
    	    return Success;
		}catch(Exception e){
			return Fail;
		}    
    }
	
    //按时间和handler查询案例列表
	public List<TestCaseFile> getTestCases(String product, String handler, String date)
	{
		System.out.println(date);
		final long time = Long.parseLong(date);
		List<String> rawList = TestCaseDataManager.Instance.getTestCaseNames(product, handler);
		if(rawList == null)
		{
			throw new CatfishException(TestingToolException.NO_TESTCASES_ERROR);
		}
		List<String> result = TestCaseDataManager.Instance.getFilterTestCaseNames(rawList, new ITestCaseFilter(){

			@Override
			public boolean filter(String name) {
				long tsTime = TestCaseDataManager.getTestCaseTime(name);
				if(tsTime >= time && tsTime <= time + OneDayMilis)
				{
					return true;
				}
				return false;
			}			
		});
		return getTestCaseFileList(result);
	}
	
	private List<TestCaseFile> getTestCaseFileList(List<String> fileNames)
	{
		Long date = null;
		List<TestCaseFile> tcList = new LinkedList<>();
		for(String item : fileNames)
		{
			date = TestCaseDataManager.getTestCaseTime(item);
			TestCaseFile tc = new TestCaseFile("data\\" + item, new Date(date));
			tcList.add(tc);
		}
		return tcList;
	}
}
