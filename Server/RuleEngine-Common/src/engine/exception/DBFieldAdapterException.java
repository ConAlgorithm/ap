package engine.exception;

public class DBFieldAdapterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6638066258924544715L;
	private static final String message = "Error occurs when  trying to build the ModelField by Adapter %s : %s";

	public DBFieldAdapterException(String adapterName, Exception e) {
		super(String.format(message, adapterName, e.getMessage()));
	}
}
