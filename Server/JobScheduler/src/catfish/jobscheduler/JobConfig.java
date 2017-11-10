package catfish.jobscheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobConfig {

  private static Map<String, JobInfo> jobMapping = new HashMap<>();
  private static List<JobInfo> jobsInTopologicalOrder = new ArrayList<>();

  public static JobInfo getJobInfo(String jobName) {
    return jobMapping.get(jobName);
  }

  public static List<JobInfo> getAllJobs() {
    return jobsInTopologicalOrder;
  }

  public static void readConfigFromFile() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        ClassLoader.getSystemResourceAsStream(Configuration.JOB_CONFIG_FILE)));

    while (true) {
      List<String> description = readLineAsList(reader);
      if (description.isEmpty()) {
        break;
      }

      JobInfo job = new JobInfo();
      job.name = description.get(0);
      job.type = JobType.valueOf(description.get(1).toUpperCase());

      for (int i = 2; i < description.size(); i++) {
        JobInfo previousJob = jobMapping.get(description.get(i));
        job.previousJobs.add(previousJob);
        job.previousJobs.addAll(previousJob.previousJobs);
        previousJob.successiveJobs.add(job);
      }

      jobMapping.put(job.name, job);
      jobsInTopologicalOrder.add(job);
    }
  }

  private static List<String> readLineAsList(BufferedReader reader) {
    String s = readLine(reader);
    return s == null ? Arrays.<String>asList() : Arrays.asList(s.split(" "));
  }

  private static String readLine(BufferedReader reader) {
    try {
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
