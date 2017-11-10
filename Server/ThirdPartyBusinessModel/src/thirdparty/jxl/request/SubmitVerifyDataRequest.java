package thirdparty.jxl.request;

import java.util.List;
import java.util.Map;

import thirdparty.jxl.request.submitverifydata.ContactListItem;
import catfish.base.httpclient.annotation.Necessary;

import com.google.gson.annotations.SerializedName;

public class SubmitVerifyDataRequest extends JXLBaseRequest{

	//姓名
	@catfish.base.httpclient.annotation.Necessary()
	@SerializedName("name")
	private String name;
	
	//身份证号
	@Necessary()
	@SerializedName("idcard")
	private String idCardNumber;
	
	//电话号
	@Necessary()
	@SerializedName("phone")
	private String phone;
	
	//住宅电话
	@SerializedName("home_tel")
	private String homeTel;
	
	//住宅地址ַ
	@Necessary()
	@SerializedName("home_addr")
	private String homeAddress;
	
	//公司电话
	@SerializedName("work_tel")
	private String workTel;
	
	//配偶电话
	@SerializedName("couple_phone_num")
	private String couplePhoneNumber;
	
	//联系人数组
	@Necessary()
	@SerializedName("contact_list")
	private List<ContactListItem> contactList;
	
	@Override
	protected Map<String, Object> customizeGetParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> customizePostParam() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getWorkTel() {
		return workTel;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public String getCouplePhoneNumber() {
		return couplePhoneNumber;
	}

	public void setCouplePhoneNumber(String couplePhoneNumber) {
		this.couplePhoneNumber = couplePhoneNumber;
	}

	public List<ContactListItem> getContactList() {
		return contactList;
	}

	public void setContactList(List<ContactListItem> contactList) {
		this.contactList = contactList;
	}
}
