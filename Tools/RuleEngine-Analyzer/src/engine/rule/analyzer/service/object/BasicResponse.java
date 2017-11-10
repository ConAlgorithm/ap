package engine.rule.analyzer.service.object;

import com.google.gson.Gson;

public class BasicResponse {

	public static final BasicResponse Success = new BasicResponse(true);
	public static final BasicResponse Fail = new BasicResponse(false);
	
	public BasicResponse(boolean result)
	{
		this.success = result;
	}
	
	public BasicResponse()
	{
		this(true);
	}
	
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	@Override
	public String toString()
	{
		return new Gson().toJson(this);
	}
}
