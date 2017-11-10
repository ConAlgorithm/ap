package jma.handlers;

import jma.JobHandler;
import jma.RetryRequiredException;

public class EmptyInstinctPreHandler extends JobHandler{
  @Override
  public void execute(String appId) throws RetryRequiredException { }
}
