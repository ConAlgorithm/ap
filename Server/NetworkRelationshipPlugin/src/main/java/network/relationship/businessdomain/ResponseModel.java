/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package network.relationship.businessdomain;

/**
 * 〈返回模型〉
 *
 * @author dengw
 * @version ResponseModel.java, V1.0 2017年5月22日 下午2:42:15
 */
public class ResponseModel<T> {
	public static int SUCCESS = 0;
	public static int FAILED = 1;

	private int code;
	private T data;
	private String msg = "success";

	public ResponseModel<T> buildSuccessResponse(T data) {
		this.data = data;
		this.code = SUCCESS;
		return this;
	}

	public ResponseModel<T> buildFailedResponse(T data) {
		this.data = data;
		this.code = FAILED;
		this.msg = "failed";
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
