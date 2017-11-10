package catfish.jobschedulerepeater;

import catfish.base.StartupConfig;

import com.google.gson.Gson;

public class Configuration {
  private static RepeatersInfo repeatersInfo;

  public static void readConfiguration() {
    repeatersInfo = new Gson().fromJson(
        StartupConfig.get("catfish.jobschedulerepeater.repeatersinfo"),
        RepeatersInfo.class);
  }

  public static RepeatersInfo getRepeatersInfo() {
    return repeatersInfo;
  }
}
