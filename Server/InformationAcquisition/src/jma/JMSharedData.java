package jma;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** Shared data for job management. */
public class JMSharedData {
  static Thread[] threads;
  static Lock[] locks;
  static JobInstance[] jobs;

  public static void initialize() {
    int nthreads = Configuration.getJobWorkerThreads();

    threads = new Thread[nthreads];
    locks = new Lock[nthreads];
    jobs = new JobInstance[nthreads];

    for (int i = 0; i < nthreads; i++) {
      locks[i] = new ReentrantLock();
    }
  }
}
