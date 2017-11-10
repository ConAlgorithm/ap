package thirdparty.jxl.response.reportstatus;

import com.google.gson.annotations.SerializedName;

public class ParserInfo {

	@SerializedName("members")
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
