package jma.handlers;

import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.dao.InstallmentApplicationDao;
import jma.JobHandler;
import jma.RetryRequiredException;

public class LoanApprovePayDayLoanHandler extends JobHandler{

	@Override
	public void execute(String appId) throws RetryRequiredException {
		InstallmentApplicationDao.updateStatus(appId, ApplicationStatus.MerchantApproved);
	}
}
