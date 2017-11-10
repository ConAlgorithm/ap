package thirdparty.jxl.response.reportdata;

import catfish.base.httpclient.object.BaseObject;

import com.google.gson.annotations.SerializedName;

public class Person extends BaseObject{

	/*
		真实姓名	real_name	string	申请人姓名
		身份证号码	id_card_num	string	申请人身份证号码
		性别	gender	string	申请人性别
		星座	sign	string	申请人星座
		年龄	age	int	申请人年龄
		出生省份	province	string	申请人出生省份
		出生城市	city	string	申请人出生城市
		出生县	region	string	申请人出生县
    */
	@SerializedName("real_name")
	private String realName;
	
	@SerializedName("id_card_num")
	private String idCardNumber;
	
	@SerializedName("gender")
	private String gender;
	
	@SerializedName("sign")
	private String starSign;
	
	@SerializedName("age")
	private int age;
	
	@SerializedName("province")
	private String province;
	
	@SerializedName("city")
	private String city;
	
	@SerializedName("region")
	private String region;

	//旧的字段，现在以province为准
	@SerializedName("state")
	private String state;
	
	
	@SerializedName("result")
	private boolean result;
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public String getStarSign() {
		return starSign;
	}

	public void setStarSign(String starSign) {
		this.starSign = starSign;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}	
}
