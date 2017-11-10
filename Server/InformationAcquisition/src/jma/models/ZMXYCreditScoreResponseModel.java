package jma.models;

/**
 * 芝麻信用评分模型
 * @author caizhp
 *
 */
public class ZMXYCreditScoreResponseModel extends ZMXYResponseModel{
    
	/**
	 * 用户的芝麻信用分
	 */
	private String zmScore ;

	public String getZmScore() {
		return zmScore;
	}

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore;
	}
	
}

