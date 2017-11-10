package engine.rule.test.service.object;

public class BasicResponse {

	public static final BasicResponse Success = new BasicResponse(true);
	public static final BasicResponse Fail = new BasicResponse(false);
	
	public BasicResponse(boolean result)
	{
		this.success = result;
	}
	
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
