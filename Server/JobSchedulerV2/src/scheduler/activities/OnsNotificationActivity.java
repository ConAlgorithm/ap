package scheduler.activities;

import catfish.base.ons.MessageNotificationUtil;

public class OnsNotificationActivity extends InteractiveActivity {
  private String extraInfo;

  public OnsNotificationActivity(String appId, String name, String extraInfo) {
    super(appId, name);
    this.extraInfo = extraInfo;
  }

  @Override
  protected void sendRequestJob() {
    MessageNotificationUtil.sendMessageAsynchronously(name, appId, extraInfo);
  }

  @Override
  public void trigger() {
    sendRequestJob();
    finishedHandler.execute("OnsNotificationSent");
  }
}
