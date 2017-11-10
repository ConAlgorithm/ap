package scheduler.activities;

import scheduler.Activity;
import scheduler.FinishedHandler;

public class NonBlockingActivity extends BaseActivity {

	Activity innerActivity;

	public NonBlockingActivity(Activity innerActivity) {
		this.innerActivity = innerActivity;

		innerActivity.setFinishedHandler(new FinishedHandler() {
			@Override
			public void execute(String result) {
				// empty
			}
		});
	}

	@Override
	public void trigger() {
		innerActivity.trigger();
		finishedHandler.execute("NonBlockingActivityResult");
	}
}
