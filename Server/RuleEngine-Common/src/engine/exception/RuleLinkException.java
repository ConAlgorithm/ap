package engine.exception;

public class RuleLinkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2573504090279243051L;

	private String ruleName;

	public String getRuleName() {
		return ruleName;
	}

	private static final String linkSelf = "the Rule named %s cannot set nextRule to itself!";

	private static final String endLessLoop = "find the Rule named %s can make an endless loop in the rules link!";

	private RuleLinkException(String message) {
		super(message);
	}

	public static RuleLinkException LinkSelf(String ruleName) {
		RuleLinkException exception = new RuleLinkException(String.format(
				linkSelf, ruleName));
		exception.ruleName = ruleName;
		return exception;
	}

	public static RuleLinkException EndLessLoop(String ruleName) {
		RuleLinkException exception = new RuleLinkException(String.format(
				endLessLoop, ruleName));
		exception.ruleName = ruleName;
		return exception;
	}
}
