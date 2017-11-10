package catfish.plugins.pdfgenerator;

/**
 * function: bool model of return result<p>
 * date： 2017-02-27
 * @author jiaoh
 *
 */
public class BoolModel {
	private boolean success;

	public BoolModel() {
		super();
	}

	public BoolModel(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
