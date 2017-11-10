package thirdparty.jxl.response.reportdata;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CellPhoneBehaviorItem {

	@SerializedName("phone_num")
	private String phoneNumber;
	
	@SerializedName("behavior")
	private List<BehaviorItem> behaviorList;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<BehaviorItem> getBehaviorList() {
		return behaviorList;
	}

	public void setBehaviorList(List<BehaviorItem> behaviorList) {
		this.behaviorList = behaviorList;
	}
}
