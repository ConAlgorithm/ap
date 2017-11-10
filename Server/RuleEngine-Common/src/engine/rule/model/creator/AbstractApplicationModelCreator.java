package engine.rule.model.creator;

public abstract class AbstractApplicationModelCreator extends
		AbstractModelCreator {

	protected String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public AbstractApplicationModelCreator(String appId) {
		this.appId = appId;
	}

}
