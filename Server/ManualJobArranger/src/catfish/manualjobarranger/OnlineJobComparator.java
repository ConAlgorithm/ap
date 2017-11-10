package catfish.manualjobarranger;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OnlineJobComparator implements Comparator<MessageEntity> {
  private static final int DEFAULT_WEIGHT = 0;
  private Date now = new Date();
  List<Integer> channelWeights;
  private long m;
  private long n;

  public OnlineJobComparator(int appCount, int examinerCount) {
    channelWeights = Configuration.getArrangerConfig().getChannelWeights();
    m = appCount * Configuration.getArrangerConfig().getAverageDuration();
    n = examinerCount * Configuration.getArrangerConfig().getAveragePatience();
  }

  @Override
  public int compare(MessageEntity x, MessageEntity y) {
    return - Long.compare(calcPriority(x), calcPriority(y));
  }

  private long calcPriority(MessageEntity entity) {
    return m * m * diffSeconds(entity.getAppSubmittedDate(), now)
        + n * n * diffSeconds(entity.getJobGeneratedDate(), now)
        + m * n * (getJobWeight(entity.getQueueName()) + getChannelWeight(entity.getChannel()));
  }

  private int getChannelWeight(int channel) {
    return 0 <= channel && channel < channelWeights.size() ? channelWeights.get(channel) : 0;
  }

  private int getJobWeight(String name) {
    Integer result = Configuration.getArrangerConfig().getOnlineJobWeights().get(name);
    return result == null ? DEFAULT_WEIGHT : result;
  }

  private long diffSeconds(Date start, Date end) {
    return (end.getTime() - start.getTime() + 500L) / 1000L;
  }
}
