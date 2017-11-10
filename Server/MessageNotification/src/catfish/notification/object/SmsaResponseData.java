package catfish.notification.object;

import java.util.Map;

public class SmsaResponseData<T> {
	private int code;
	private String message;
	private Map<String, T>[] value;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, T>[] getValue() {
		return value;
	}

	public void setValue(Map<String, T>[] value) {
		this.value = value;
	}

	
}
