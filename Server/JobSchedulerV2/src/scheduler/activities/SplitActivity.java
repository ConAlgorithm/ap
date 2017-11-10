package scheduler.activities;

import java.util.Random;
import java.util.UUID;

import scheduler.Activity;

/**
 * Split the given activities by ratio, e.g. assign 40% task to one activity,
 * the left assign to another activity
 *
 */
public class SplitActivity extends BaseActivity {

	private Random randomGenerator = new Random();

	private Activity winActivity;

	public SplitActivity(
	    String appId,
	    Activity firstActivity,
	    Activity secondActivity,
	    int percentageOfFirstActivity) {
	  UUID uuid = UUID.fromString(appId);
	  randomGenerator.setSeed(uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits());
		int value = randomGenerator.nextInt(100);
		if (value < percentageOfFirstActivity) {
			winActivity = firstActivity;
		} else {
			winActivity = secondActivity;
		}
		winActivity.setFinishedHandler(lazyFinishedHandler);
	}

	@Override
	public void trigger() {
		winActivity.trigger();
	}

}
