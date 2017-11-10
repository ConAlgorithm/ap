package engine.rule.test.tester;

import java.util.List;
import java.util.Map;

import catfish.base.Logger;

import com.google.gson.Gson;

import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.BaseForm;
import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.test.creator.CompositeModelCreator;
import engine.rule.test.data.Message;
import engine.rule.test.data.TestCaseData;
import engine.rule.test.data.TestCaseReader;
import engine.rule.test.data.TestCaseResult;
import engine.rule.test.validator.ResultValidator;

public abstract class Tester extends Thread{

	protected static final String REUPLOADCOUNT = "reuploadCount";
	//测试数据
	private TestCaseData testcaseData;
	//测试结果
	private TestCaseResult testcaseResult = new TestCaseResult();
	
	private ResultValidator validator;
		
	public Tester(TestCaseData testcaseData, ResultValidator validator){
		this.testcaseData = testcaseData;
		this.validator = validator;
	}
	
	
	@Override
	public void run() {
		doTest();
	}

	private boolean doTest() {
		try {
			TestCaseReader reader = new TestCaseReader(testcaseData.filePath);
			List<Map<String, String>> columnValueMappings = reader.getLines();
			testcaseResult.getExecute().total(columnValueMappings.size());
			
			int lineCount = 1;
			for (Map<String, String> line : columnValueMappings) {
				
				RuleExecutor executor = RuleManager.GetRuleExecutor(testcaseData.channel, testcaseData.decisionPoint);
				CompositeModelCreator inCreator = getInModelCreator(line);
				AbstractModelCreator outCreator = getOutModelCreator(line);
			
				Map<String, Object> out = executor.ExecuteRuleAndGetResponse(inCreator, outCreator);

				Logger.get().info(out);
				BaseForm result = null;
				
				if(TestCaseData.HandlerDecisionOutMap.containsKey(testcaseData.decisionPoint))
				{
					result = (BaseForm) out.get(TestCaseData.HandlerDecisionOutMap.get(testcaseData.decisionPoint));
				}else{
					result = (BaseForm) out.get(TestCaseData.DecionOutName);
				}
				Logger.get().info("result is " + result);  			
				validateResult(lineCount, inCreator, line, result);
				
				testcaseResult.getExecute().addExecuted();		
				lineCount ++;
			}
		} catch (Exception e) {
			Logger.get().error("Execute TestCase of " + testcaseData.filePath + " failed!", e);
			return false;
		} 
		
		return testcaseResult.getExecute().getTotal() == testcaseResult.getExecute().getSucceed();
	}
	
	public void validateResult(int lineCount, AbstractModelCreator inCreator, Map<String, String> line, BaseForm result)
	{	synchronized(testcaseResult){
			if(!validator.validate(result, line)){
				if(testcaseResult.getExecute().getFail() < 20)
				{
					Message msg = new Message();
					msg.setLine(lineCount);
					
					String original = new Gson().toJson(line);
					msg.setExpected(original);			
				
					String realResult = new Gson().toJson(result);
					msg.setReal(realResult);
					try {
						String debug = new Gson().toJson(inCreator.createModelForm());
						msg.setDebug(debug);
					} catch (Exception e) {
						Logger.get().warn("Cannot create debug info of " + original, e);
					}
					testcaseResult.getExecute().addFail();
					testcaseResult.getValidate().addMsg(msg);
				}
			}else{
				testcaseResult.getExecute().addSucceed();
			}
	   }
	}
	protected abstract CompositeModelCreator getInModelCreator(Map<String, String> line);
	protected abstract AbstractModelCreator getOutModelCreator(Map<String, String> line);
	
	public TestCaseData getTestcaseData() {
		return testcaseData;
	}

	public TestCaseResult getTestcaseResult() {
		return testcaseResult;
	}
}
