package catfish.manualjobarranger;

import java.util.List;
import java.util.Map;

public class ArrangerConfig {
  private List<Integer> channelWeights;
  private Map<String, Integer> onlineJobWeights;
  private int averageDuration;
  private int averagePatience;
  private List<String> offlineJobOrders;
  private List<String> examinerPrefixes;

  public List<Integer> getChannelWeights() {
    return channelWeights;
  }

  public void setChannelWeights(List<Integer> channelWeights) {
    this.channelWeights = channelWeights;
  }

  public List<String> getExaminerPrefixes() {
    return examinerPrefixes;
  }

  public void setExaminerPrefixes(List<String> examinerPrefixes) {
    this.examinerPrefixes = examinerPrefixes;
  }

  public Map<String, Integer> getOnlineJobWeights() {
    return onlineJobWeights;
  }

  public void setOnlineJobWeights(Map<String, Integer> onlineJobWeights) {
    this.onlineJobWeights = onlineJobWeights;
  }

  public List<String> getOfflineJobOrders() {
    return offlineJobOrders;
  }

  public void setOfflineJobOrders(List<String> offlineJobOrders) {
    this.offlineJobOrders = offlineJobOrders;
  }

  public int getAverageDuration() {
    return averageDuration;
  }

  public void setAverageDuration(int averageDuration) {
    this.averageDuration = averageDuration;
  }

  public int getAveragePatience() {
    return averagePatience;
  }

  public void setAveragePatience(int averagePatience) {
    this.averagePatience = averagePatience;
  }
}
