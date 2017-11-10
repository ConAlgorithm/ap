package thirdparty.jxl.response.reportdata;

import com.google.gson.annotations.SerializedName;

public class CheckPointItem {

	/*  
		检查点类别	category	string	检查点类别
		检查项目	check_point	string	检查项目
		检查结果	result	string	检查结果
		证据	evidence	string	证据
	*/
	//for application_check, eg: 号码绑定
	//for behavior_check, eg: 呼叫验证
	@SerializedName("category")
	private String category;
	
	//for application_check, eg: 是否手机登记号码的信息
	//for behavior_check, eg: 朋友圈是否在本地(上海)
	@SerializedName("check_point")
	private String checkPoint;

	//for application_check, eg: 是
	//for behavior_check, eg: 是
	@SerializedName("result")
	private String result;
	
	//for application_check, eg: 收集到号码xxxxxx的信息
	//for behavior_check, eg: 本地通话的比例超过50%
	@SerializedName("evidence")
	private String evidence;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}
	
}
