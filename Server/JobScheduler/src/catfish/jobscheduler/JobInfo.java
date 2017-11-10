package catfish.jobscheduler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JobInfo {      // used as a structure rather than a class
  String name;
  JobType type;
  Set<JobInfo> previousJobs = new HashSet<>();      // direct and indirect previous jobs
  List<JobInfo> successiveJobs = new ArrayList<>(); // only direct successive jobs
}
