package jma.models;


/**
 * Created by ruilx
 * Date: 2017/4/13
 * Time: 17:12
 */
public class ShuWeiRiskResponseModel {

	private String code;//状态码0：成功

	private String message;
	private String resultNo;//结果流水号
	private ShuWeiData data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResultNo() {
		return resultNo;
	}

	public void setResultNo(String resultNo) {
		this.resultNo = resultNo;
	}

	public ShuWeiData getData() {
		return data;
	}

	public void setData(ShuWeiData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ShuWeiRiskResponseModel{");
		sb.append("code='").append(code).append('\'');
		sb.append(", message='").append(message).append('\'');
		sb.append(", resultNo='").append(resultNo).append('\'');
		sb.append(", data=").append(data);
		sb.append('}');
		return sb.toString();
	}
}
