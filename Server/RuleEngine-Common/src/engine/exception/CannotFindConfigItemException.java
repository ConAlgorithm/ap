package engine.exception;

public class CannotFindConfigItemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4292642556045995552L;

	private static final String message = "Cannot find the ConfigItem of Rule %s , so this rule cannot be executed!";

	public CannotFindConfigItemException(String itemName, String appId) {
		super(String.format(message, itemName));
	}
}
