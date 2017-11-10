package catfish.bonuspoints.rankupdate;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.util.Timer;

import catfish.base.business.dao.BonusPointsRankDao;

public class RankUpdateScheduler implements IScheduler {

  private Map<TimerTask, ScheduleConfig> tasks;
  private Timer timer;

  public RankUpdateScheduler() {
    tasks = new HashMap<TimerTask, ScheduleConfig>();
    timer = new Timer();
  }

  @Override
  public IScheduler addTask(TimerTask task, ScheduleConfig config) {
    this.tasks.put(task, config);
    return this;
  }

  @Override
  public void start() {
    // run bonus points rank update once
    new RankUpdateTask().run();

    for (Entry<TimerTask, ScheduleConfig> task : tasks.entrySet()) {
      timer.scheduleAtFixedRate(
          task.getKey(), task.getValue().getFirstTime(), task.getValue().getPeriod());
    }
  }

  public Date getLastUpdateDate() {
    Date lastUpdate = BonusPointsRankDao.getLastUpdateDate();
    if (lastUpdate == null) {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 1970);
      lastUpdate = cal.getTime();
    }
    return lastUpdate;
  }

}
