package jma.handlers;

import jma.NonBlockingJobV2Handler;
import jma.RetryRequiredException;
import catfish.base.StringUtils;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;

public class CheckCourtExecutedExistHandler extends NonBlockingJobV2Handler {

  public CheckCourtExecutedExistHandler() {
    responseMessager.setJobStatus("ManualCheckRequired");
  }

  @Override
  public void execute(String appId) throws RetryRequiredException {
    boolean succeed = !StringUtils.isNullOrWhiteSpaces(RawDataStorageManager
        .getRawData(appId, RawDataVariableNames.QHZX_UNITED_RAW_DATA));
    if (succeed) {
      responseMessager.setJobStatus("NoManualCheckRequired");
    }
  }
}
