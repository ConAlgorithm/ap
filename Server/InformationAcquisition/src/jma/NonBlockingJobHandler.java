package jma;

public abstract class NonBlockingJobHandler extends JobHandler {
  public NonBlockingJobHandler() {
    sendsResponse = true;
  }
}
