package jma.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jma.JobHandlerSwitch;
import jma.NonBlockingJobV2Handler;
import jma.thirdpartyservices.junyu.AlternativeUrlsContainer;
import jma.thirdpartyservices.junyu.JYChecker;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckJunyu3PhotoHandler extends NonBlockingJobV2Handler {

	private static final int NameIdCardNotMatch = -1;
	private static final int CheckNameIDCardOnPolice = 1;
	private static final int CheckIDCardPhoto = 2;
	private static final int CheckHeadPhoto = 4;

	private static Set<Integer> NameIdCardNotMatchCode = new HashSet<>();

	private AlternativeUrlsContainer urlsContainer = new AlternativeUrlsContainer();

	static{
		//NameIdCardNotMatchCode.add(-2202);//库中无此号		
		NameIdCardNotMatchCode.add(-2203);//身份证姓名不一致	
		}

  private void doAllJobs()
  {
		responseMessager.setJobDataInt(CheckNameIDCardOnPolice | CheckIDCardPhoto | CheckHeadPhoto);
  }
  @Override
  public void execute(String appId) {

	  responseMessager.setJobStatus(null);
	  //为保证安全，默认为调用失�需要做全部检查工�	  
	  doAllJobs();

	  if(DynamicConfig.read("JYCheckSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue()))
	  {
		  return;
	  }
	  try{
			List<Integer> result = JYChecker.callJunYu(appId, urlsContainer, 0);
			if(result == null) return;
			switch(result.get(0))
			{
			//此时返回3张照片对比相似度
		    case 0:
	    		//不需要再检查公安部
		    	responseMessager.setJobDataInt(CheckIDCardPhoto | CheckHeadPhoto);
		    	List<AppDerivativeVariable> variablesList = new ArrayList<>();
		    	variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JY_IDPHOTO_SIMILARITY, result.get(1)));
		    	variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JY_HEADPHOTO_SIMILARITY, result.get(2)));
		    	AppDerivativeVariableManager.addVariables(variablesList);
			    break;
		    //此时身份证与公安部对比不通过
			case 1:
				if(NameIdCardNotMatchCode.contains(result.get(1)))
				{
					AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableNames.ID_CARD_IDENTIFICATION_RESULT, "不一致"));
					responseMessager.setJobDataInt(NameIdCardNotMatch);
				}else {
					if(result.get(1) == -2202)
					{
						AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableNames.ID_CARD_IDENTIFICATION_RESULT, "库中无此号"));
					}
					doAllJobs();
				}
				break;
			//此时身份证与公安部对比通过，身份证与现场照不通过
			case 2:
				responseMessager.setJobDataInt(CheckIDCardPhoto | CheckHeadPhoto);
				break;
			//此时表示什么都没验证，直接返回�			
			case 3:
				doAllJobs();
				break;
			//其他调用失败情况
			default:
				doAllJobs();
			}

	  }catch(Exception e)
	  {
		  Logger.get().warn(String.format("CheckJunyu3Photo warning, appId: ",appId),e);
	  }
    }
	@Override
	public void writeJobResult(boolean isSuccess) {
		if(((CheckNameIDCardOnPolice & responseMessager.getJobDataInt()) == 0) && (responseMessager.getJobDataInt() != NameIdCardNotMatch))
		{
			AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(requestMessager.getAppId(), AppDerivativeVariableNames.ID_CARD_IDENTIFICATION_RESULT, "一致"));
		}
		super.writeJobResult(isSuccess);
	}

}
