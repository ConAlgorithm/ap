package catfish.jobschedulerepeater;

import java.util.List;

public class RepeatersInfo {

  public static class OutputInfo {
    private int lowerBound;
    private int upperBound;
    private String outputQueue;

    public static OutputInfo of(int lowerBound, int upperBound, String outputQueue) {
      OutputInfo outputInfo = new OutputInfo();
      outputInfo.setLowerBound(lowerBound);
      outputInfo.setUpperBound(upperBound);
      outputInfo.setOutputQueue(outputQueue);
      return outputInfo;
    }

    public int getLowerBound() {
      return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
      this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
      return upperBound;
    }

    public void setUpperBound(int upperBound) {
      this.upperBound = upperBound;
    }

    public String getOutputQueue() {
      return outputQueue;
    }

    public void setOutputQueue(String outputQueue) {
      this.outputQueue = outputQueue;
    }
  }

  public static class RepeaterInfo {
    private String inputQueue;
    private int workers;
    private List<OutputInfo> outputsInfo;

    public static RepeaterInfo of(String inputQueue, int workers, List<OutputInfo> outputsInfo) {
      RepeaterInfo repeaterInfo = new RepeaterInfo();
      repeaterInfo.setInputQueue(inputQueue);
      repeaterInfo.setWorkers(workers);
      repeaterInfo.setOutputsInfo(outputsInfo);
      return repeaterInfo;
    }

    public String getInputQueue() {
      return inputQueue;
    }

    public void setInputQueue(String inputQueue) {
      this.inputQueue = inputQueue;
    }

    public int getWorkers() {
      return workers;
    }

    public void setWorkers(int workers) {
      this.workers = workers;
    }

    public List<OutputInfo> getOutputsInfo() {
      return outputsInfo;
    }

    public void setOutputsInfo(List<OutputInfo> outputsInfo) {
      this.outputsInfo = outputsInfo;
    }
  }

  private List<RepeaterInfo> repeaters;

  public static RepeatersInfo of(List<RepeaterInfo> repeaters) {
    RepeatersInfo repeatersInfo = new RepeatersInfo();
    repeatersInfo.setRepeaters(repeaters);
    return repeatersInfo;
  }

  public List<RepeaterInfo> getRepeaters() {
    return repeaters;
  }

  public void setRepeaters(List<RepeaterInfo> repeaters) {
    this.repeaters = repeaters;
  }
}
