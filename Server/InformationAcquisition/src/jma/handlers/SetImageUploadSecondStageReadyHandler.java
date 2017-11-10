package jma.handlers;

import jma.JobV2Handler;
import jma.RetryRequiredException;
import catfish.base.business.dao.InstallmentApplicationTagDao;

public class SetImageUploadSecondStageReadyHandler extends JobV2Handler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    InstallmentApplicationTagDao.setSecondStageStatus(appId, true);
  }
}
