package catfish.bonuspoints.rankupdate;

import java.util.Date;

public class ScheduleConfig {

  private Date firstTime;
  private long period;

  public static final int ONE_DAY = 24 * 60 * 60 * 1000;  // milliseconds

  public ScheduleConfig(Date firstTime, long period) {
    this.firstTime = firstTime;
    this.period = period;
  }

  public Date getFirstTime() {
    return firstTime;
  }

  public void setFirstTime(Date firstTime) {
    this.firstTime = firstTime;
  }

  public long getPeriod() {
    return period;
  }

  public void setPeriod(long period) {
    this.period = period;
  }

}
