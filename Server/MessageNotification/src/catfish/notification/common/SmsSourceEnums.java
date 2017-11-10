package catfish.notification.common;

public enum SmsSourceEnums {
	
	CODE("code"),
	
	/*****
	 * product line tag
	 */
	
	DIRECT("direct-"),
	
	CMPOS("cmpos-"),
	
	PSL_TAG("psl-");

	private String value;
	
	SmsSourceEnums(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
