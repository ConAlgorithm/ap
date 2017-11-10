package jma.models;

/**
 * @author caizhp
 * @Comments 芝麻信用数据源返回的Model
 * @since 2016-10-12
 * @version OmniPrime.All rights reserved.
 * 
 */
public class ZMXYResponseModel  {

	/**
	 * 授权URL地址
	 */
	private String authPageURL;

	/**
	 * 用户是否对该应用授权
	 */
	private boolean authorized = false;	
	
	/** 
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	private String bizNo;

    /**
     * 接口成功标识
     */
    private boolean success;

	public String getAuthPageURL() {
		return authPageURL;
	}

	public void setAuthPageURL(String authPageURL) {
		this.authPageURL = authPageURL;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
