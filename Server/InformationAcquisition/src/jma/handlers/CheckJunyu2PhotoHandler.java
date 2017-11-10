package jma.handlers;

import java.util.List;

import jma.Configuration;
import jma.JobHandlerSwitch;
import jma.JobStatus;
import jma.NonBlockingJobV2Handler;
import jma.thirdpartyservices.junyu.AlternativeUrlsContainer;
import jma.thirdpartyservices.junyu.JYChecker;
import catfish.base.DynamicConfig;
import catfish.base.Logger;

public class CheckJunyu2PhotoHandler extends NonBlockingJobV2Handler{

	private AlternativeUrlsContainer urlsContainer = new AlternativeUrlsContainer();

	 @Override
	 public void execute(String appId) {
		  //为保证安全，默认为需要人工对比	  
		 responseMessager.setJobStatus(JobStatus.MANUALCHECKREQUIRED);

		  if(DynamicConfig.read("JYCheckSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue()))
		  {
			  return;
		  }

		  try{
				List<Integer> result = JYChecker.callJunYu(appId, urlsContainer, 1);
				if(result == null) return;
				switch(result.get(0))
				{
				//此时返回3张照片对比相似度
			    case 10:
			    	//相似度合�			    	
			    	if(result.get(2) >= Configuration.getJunyuHeadPhotoSimilarityPass())
			    	{
			    		responseMessager.setJobStatus(JobStatus.SUCCEESS);
			    	}else{
			    		responseMessager.setJobStatus(JobStatus.MANUALCHECKREQUIRED);
			    	}
				    break;
			    //此时身份证与公安部对比不通过
				case 11:
					responseMessager.setJobStatus(JobStatus.MANUALCHECKREQUIRED);
					break;
				default:
				    responseMessager.setJobStatus(JobStatus.MANUALCHECKREQUIRED);
				}
		  }catch(Exception e)
		  {
			  Logger.get().warn(String.format("CheckJunyu2Photo warning, appId: %s",appId),e);
		  }
	 }
}
