package catfish.jobschedulerepeater;

import java.util.Arrays;

import catfish.jobschedulerepeater.RepeatersInfo.OutputInfo;
import catfish.jobschedulerepeater.RepeatersInfo.RepeaterInfo;

import com.google.gson.Gson;

public class RepeatersInfoGenerator {
  public static void main(String[] args) {
    System.out.println(new Gson().toJson(
        RepeatersInfo.of(Arrays.asList(
            RepeaterInfo.of(
                "JobStatusQueue",
                1,
                Arrays.asList(
                    OutputInfo.of(1, 1000, "JobStatusQueueA"))),
            RepeaterInfo.of(
                "StatusQueueV2",
                2,
                Arrays.asList(
                    OutputInfo.of(1, 500, "StatusQueueV2A"),
                    OutputInfo.of(501, 1000, "StatusQueueV2B")))))));
  }
}
