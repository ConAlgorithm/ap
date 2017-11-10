package jma.handlers;

import jma.JobHandler;
import jma.RetryRequiredException;

public class EmptyHandler extends JobHandler{
  @Override
  public void execute(String appId) throws RetryRequiredException { }
}
