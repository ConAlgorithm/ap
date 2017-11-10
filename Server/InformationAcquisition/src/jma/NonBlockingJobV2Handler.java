package jma;

public abstract class NonBlockingJobV2Handler extends JobV2Handler {
  public NonBlockingJobV2Handler() {
    sendsResponse = true;
  }
}
