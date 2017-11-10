package jma.handlers;

import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.UploadFileStatus;

public class FlagApplicationCompletedHandler extends
        ChangeApplicationStatusHandler {

    public FlagApplicationCompletedHandler() {
        this.sendsResponse = true;
    }
    
    @Override
    protected ApplicationStatus changeStatusTo() {
        return ApplicationStatus.Completed;
    }

    @Override
    protected UploadFileStatus[] changeUploadStatusTo() {
        return null;
    }

}
