package thirdparty.jxl.request.submitverifydata;

import com.google.gson.annotations.SerializedName;

public class ContactListItem {

	@SerializedName("contact_tel")
	private String contactTel;
	
	@SerializedName("contact_name")
	private String contactName;
	
	@SerializedName("contact_type")
	private int contactType;
}
