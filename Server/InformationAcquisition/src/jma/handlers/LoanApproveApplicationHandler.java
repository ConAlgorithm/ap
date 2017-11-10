package jma.handlers;

import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.UploadFileStatus;

public class LoanApproveApplicationHandler extends ChangeApplicationStatusHandler{

	@Override
	protected ApplicationStatus changeStatusTo() {
		return ApplicationStatus.MerchantApproved;
	}

	@Override
	protected UploadFileStatus[] changeUploadStatusTo() {
		// TODO Auto-generated method stub
		return null;
	}

}
