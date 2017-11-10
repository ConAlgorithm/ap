package jma.handlers;

import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.QueueMessageStatus;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import jma.JobHandler;
import jma.RetryRequiredException;

public class CheckPayDayLoanConditionHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();

    if (app == null
        || !isAppStatusOK(app.Status)) {
      responseMessager.setJobStatus(QueueMessageStatus.PDLReject.getValue());
      return;
    }

    updateAppStatusToLoanable(appId);
    responseMessager.setJobStatus(QueueMessageStatus.PDLPass.getValue());
  }

  private boolean isAppStatusOK(int status) {
    return status == ApplicationStatus.MoneyTransferSubmitted.getValue();
  }

  // update application status
  private void updateAppStatusToLoanable(String appId) {
    InstallmentApplicationDao.updateStatus(appId, ApplicationStatus.MerchantApproved);
  }
}