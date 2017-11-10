package scheduler.activities;

import scheduler.Activity;
import scheduler.FinishedHandler;

public abstract class BaseActivity implements Activity {

  protected FinishedHandler finishedHandler;

  protected FinishedHandler lazyFinishedHandler = new FinishedHandler() {
    @Override
    public void execute(String result) {
      finishedHandler.execute(result);
    }
  };

  @Override
  public void setFinishedHandler(FinishedHandler handler) {
    this.finishedHandler = handler;
  }
}
