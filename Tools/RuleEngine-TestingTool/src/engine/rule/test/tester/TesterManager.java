package engine.rule.test.tester;

import java.util.concurrent.ConcurrentHashMap;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.exception.CatfishException;
import engine.rule.test.TestingToolException;
import engine.rule.test.data.TestCaseData;
import engine.rule.test.data.TestCaseResult;
import engine.rule.test.validator.ResultValidator;

public final class TesterManager {

	private ConcurrentHashMap<String, Tester> testerMap = new ConcurrentHashMap<>();
	
	public static TesterManager Instance = new TesterManager();
	
	private TesterManager(){}
	
	private String getKey(String product)
	{
		return product;
	}
	
	private ResultValidator getValidator(TestCaseData data)
	{		
		String clsName = String.format("engine.rule.test.validator.%s.%sResultValidator", data.pkgName, data.decisionPoint);
		ResultValidator validator = null;
		try {
			Class cls = Class.forName(clsName);
			validator = (ResultValidator)cls.newInstance();
		} catch (Exception e) {
			Logger.get().error("Can not create ResultValidator: " + clsName, e);
		}
		return validator;
	}
	
	private Tester getTester(TestCaseData data)
	{
		if(data.channel == InstalmentChannel.App)
		{
			return new POSTester(data, getValidator(data));
		}else if(data.channel == InstalmentChannel.PayDayLoanApp){
			return new PDLTester(data, getValidator(data));
		}else if(data.channel == InstalmentChannel.CashLoan){
			return new LTVTester(data, getValidator(data));
		}
		return null;
	}
	public synchronized void runCase(TestCaseData data)
	{
		String key = getKey(data.product);
		if(testerMap.containsKey(key) && testerMap.get(key).isAlive())
		{
			throw new CatfishException(TestingToolException.TESTER_ALREADY_RUNNING);
		}
		data.init();
		Tester tester = getTester(data);
		testerMap.put(key, tester);
		tester.start();
	}
	
	
	public synchronized boolean stopCase(String product)
	{
		String key = getKey(product);
		if(testerMap.containsKey(key))
		{
			Thread tester = testerMap.get(key);
			if(tester.isAlive())
			{
				try{
					tester.stop();
				}catch(Exception e){
				}
/*				if(tester.isAlive())
				{
					throw new CatfishException(TestingToolException.SHUTDOWN_TESTER_ERROR);
				}*/
				testerMap.remove(key);
			}
		}
		return true;
	}
	
	public TestCaseResult getResult(String product)
	{
		Tester tester = testerMap.get(getKey(product));
		if(tester == null)
            return null;
		return tester.getTestcaseResult();
	}
}
