package scheduler.activities;

public class DummyActivity extends BaseActivity {
  @Override
  public void trigger() {
    finishedHandler.execute("DummyActivityResult");
  }
}
