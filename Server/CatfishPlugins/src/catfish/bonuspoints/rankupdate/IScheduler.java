package catfish.bonuspoints.rankupdate;

import java.util.TimerTask;

public interface IScheduler {

  IScheduler addTask(TimerTask task, ScheduleConfig config);

  void start();

}
