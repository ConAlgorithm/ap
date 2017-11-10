package jma.handlers;

import jma.JobHandler;
import jma.RetryRequiredException;

public class EmptyForSegmentF4Handler extends JobHandler{
    @Override
    public void execute(String appId) throws RetryRequiredException { }
}
