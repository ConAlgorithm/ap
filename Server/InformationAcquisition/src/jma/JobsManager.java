package jma;

import java.util.Date;

import catfish.base.ThreadUtils;

public class JobsManager {

  private static void createWorkers() {
    for (int i = 0; i < Configuration.getJobWorkerThreads(); i++) {
      JMSharedData.threads[i] =
          new Thread(new JobThread(i), "Worker thread " + i);
      JMSharedData.threads[i].start();
    }
  }

  private static void checkWorkers() {
    while (true) {
      for (int i = 0; i < Configuration.getJobWorkerThreads(); i++) {
        JMSharedData.locks[i].lock();
        if (JMSharedData.jobs[i] != null
            && new Date().after(JMSharedData.jobs[i].getExpirationTime())) {
          JMSharedData.threads[i].interrupt();
        }
        JMSharedData.locks[i].unlock();
      }

      ThreadUtils.sleepInSeconds(1);
    }
  }

  public static void run() {
    createWorkers();
    checkWorkers();
  }
}
