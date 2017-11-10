package engine.rule.pdl;

import java.util.Set;

import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.dao.PaymentInfoDao;
import catfish.base.business.object.PaymentObject;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;

import com.google.common.collect.Sets;

import engine.util.ApplicationHelper;

public class ApprovalEvidenceRequirementCheckRuleHandler extends
		ApplicationRuleHandler<QueueMessager> {

	private static final Set<String> BUCKLE_BANK_NAME_SET = Sets.newHashSet(
	    "渤海银行",
	    "吉林农信联合社",
	    "吉林省农村信用社联合社",
	    "邮储银行河南分行",
	    "邮储银行",
	    "交通银行澳门分行",
	    "交通银行香港分行",
	    "交通银行",
	    "邮政储蓄银行",
	    "中国邮政储蓄银行信用卡中心",
	    "天津滨海农村商业银行",
	    "天津农村商业银行",
	    "北京银行",
	    "浦东发展银行",
	    "吉林农信联合社",
	    "吉林省农村信用社联合社",
	    "内蒙古自治区农村信用社联合社",
	    "江西农信联合社",
	    "江西省农村信用社联合社");
	
	@Override
	public QueueMessager handle(QueueMessager message) {
		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobDataInt(32);
			return message;
		}
		/***************************************************/
		
		String appId = message.getAppId();
		String jobName = message.getJobName();
		int resultStatus = 0;

		try{
			if (isBuckleRequired(appId)) {
				resultStatus |= UploadFileStatus.BuckleUploaded.getValue();
			}
	
			if (resultStatus > 0) {
			  ApplicationHelper.ResetUploadedStatus(appId, resultStatus, false);
			}
	
			ProcessRuleResult(appId, jobName, resultStatus == 0, resultStatus);
		}catch(Exception e){
			
		}
		
		return new QueueMessager(appId, jobName, resultStatus);
	}
	private boolean isBuckleRequired(String appId) {
		PaymentObject payment = new PaymentInfoDao(appId).getSingle();
		if (payment != null
				&& BUCKLE_BANK_NAME_SET.contains(payment.BankName)
				&& DatabaseApiUtil.isNormalOrTestUser(appId)) {
			return true;
		}
		return false;
	}
}
