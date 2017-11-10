package catfish.msglauncher.model;

public class RestResult {
	private boolean success;
	private String message;
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public boolean isSuccess(){
		return success;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
