package engine.rule.app;

import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import catfish.base.Logger;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.common.app.UploadFileFlag;
import catfish.base.queue.QueueMessager;

import com.google.common.collect.Sets;

import engine.rule.model.BankModel;
import engine.util.ApplicationHelper;
import engine.util.FundService;

public class ApprovalEvidenceRequirementCheckRuleHandler extends
		ApplicationRuleHandler<QueueMessager> {

	
	@Override
	public QueueMessager handle(QueueMessager message) {
				
		String appId = message.getAppId();
		String jobName = message.getJobName();
		int resultStatus = 0;
		

		/***************just for testing********************/
		if(isForTesting)
		{
			int testingStatus = 48;
			message.setJobDataInt(resultStatus);
			ApplicationHelper.ResetUploadedStatus(appId, testingStatus, false);
			return message;
		}
		
		if(ApplicationHelper.isCMCC(appId)){
			Logger.get().info(String.format("This is a ChinaMobile appId: %s , will not check iou and buckle.", appId));
			message.setJobDataInt(UploadFileFlag.None.getValue());
			return new QueueMessager(appId, jobName, 0);
		}
		/***************************************************/
		
		try{
            Logger.get().info("ApprovalEvidenceRequirementCheckRuleHandler Buckle handle begin--" + resultStatus + " appId : " + appId);			
			
			if (isIOURequired(appId)) {
				resultStatus |= UploadFileStatus.IOUUploaded.getValue();
			}
			
			if (resultStatus > 0) {
				ApplicationHelper.ResetUploadedStatus(appId, resultStatus, false);
				ApplicationHelper.ResetUploadedStatus(appId,UploadFileStatus.BuckleUploaded.getValue());
			}		
			
			ProcessRuleResult(appId, jobName, resultStatus == 0, resultStatus);
			
	        Logger.get().info("ApprovalEvidenceRequirementCheckRuleHandler Buckle handle end--" + resultStatus + " appId : " + appId);	
	        
		}catch(Exception e){
            Logger.get().error("There is an Exception in ApprovalEvidenceRequirementCheckRuleHandler-- appId : " + appId + " exception : " + e);			
		}
		
		return new QueueMessager(appId, jobName, resultStatus);
	}

	private boolean isIOURequired(String appId) {
		return true;
	}
	
}
