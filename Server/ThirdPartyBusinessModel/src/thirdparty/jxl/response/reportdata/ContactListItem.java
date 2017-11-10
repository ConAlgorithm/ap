package thirdparty.jxl.response.reportdata;

import com.google.gson.annotations.SerializedName;

public class ContactListItem {

	/*
		 号码	phone_num	string	号码
		号码归属地	phone_num_loc	string	号码归属地
		（可能的）姓名	contact_name	string	（可能的）姓名
		通话次数	call_cnt	int	通话次数
		通话时长	call_len	int	通话时长
		呼出次数	call_out_cnt	int	呼出次数
		呼入次数	call_in_cnt	int	呼入次数
		关系推测	p_relation	string	关系推测（未实现）
		最近一周联系次数	contact_1w	int	最近一周联系次数
		最近一月联系次数	contact_1m	int	最近一月联系次数
		最近三月联系次数	contact_3m	int	最近三月联系次数
		凌晨联系次数	contact_early_morning	int	凌晨联系次数
		上午联系次数	contact_morning	int	上午联系次数
		中午联系次数	contact_noon	int	中午联系次数
		晚上联系次数	contact_afternoon	int	晚上联系次数
		深夜联系次数	contact_night	int	深夜联系次数
		是否全天联系	contact_all_day	bool	是否全天联系
		周中联系次数	contact_weekday	int	周中联系次数
		周末联系次数	contact_weekend	int	周末联系次数
		节假日联系次数	contact_holiday	int	节假日联系次数 
		
		//新增
		呼入时间 call_in_len int
		呼出时间 call_out_len int
		需求类型 needs_type
	*/
	@SerializedName("phone_num")
	private String phoneNumber;
	
	@SerializedName("phone_num_loc")
	private String phoneNumberLocation;
	
	@SerializedName("contact_name")
	private String possibleContactName;
	
	@SerializedName("call_cnt")
	private int callCount;
	
	@SerializedName("call_len")
	private int callLength;
	
	@SerializedName("call_out_cnt")
	private int calloutCount;
	
	@SerializedName("call_in_cnt")
	private int callinCount;
	
	@SerializedName("p_relation")
	private String possibleRelation;
	
	@SerializedName("contact_1w")
	private int contactCountIn1Week;
	
	@SerializedName("contact_1m")
	private int contactCountIn1Month;
	
	@SerializedName("contact_3m")
	private int contactCountIn3Month;
	
	@SerializedName("contact_early_morning")
	private int contactCountInEarlymorning;
	
	@SerializedName("contact_morning")
	private int contactCountInMorning;
	
	@SerializedName("contact_noon")
	private int contactCountInNoon;
	
	@SerializedName("contact_afternoon")
	private int contactCountInAfternoon;
	
	@SerializedName("contact_night")
	private int contactCountInNight;
	
	@SerializedName("contact_all_day")
	private boolean contactCountInAllDay;
	
	@SerializedName("contact_weekday")
	private int contactCountInWeekday;
	
	@SerializedName("contact_weekend")
	private int contactCountInWeekend;
	
	@SerializedName("contact_holiday")
	private int contactCountInHoliday;

	@SerializedName("call_in_len")
	private int callinLength;
	
	@SerializedName("call_out_len")
	private int calloutLength;
	
	@SerializedName("needs_type")
	private String needsType;
		
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumberLocation() {
		return phoneNumberLocation;
	}

	public void setPhoneNumberLocation(String phoneNumberLocation) {
		this.phoneNumberLocation = phoneNumberLocation;
	}

	public String getPossibleContactName() {
		return possibleContactName;
	}

	public void setPossibleContactName(String possibleContactName) {
		this.possibleContactName = possibleContactName;
	}

	public int getCallCount() {
		return callCount;
	}

	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}

	public int getCallLength() {
		return callLength;
	}

	public void setCallLength(int callLength) {
		this.callLength = callLength;
	}

	public int getCalloutCount() {
		return calloutCount;
	}

	public void setCalloutCount(int calloutCount) {
		this.calloutCount = calloutCount;
	}

	public int getCallinCount() {
		return callinCount;
	}

	public void setCallinCount(int callinCount) {
		this.callinCount = callinCount;
	}

	public String getPossibleRelation() {
		return possibleRelation;
	}

	public void setPossibleRelation(String possibleRelation) {
		this.possibleRelation = possibleRelation;
	}

	public int getContactCountIn1Week() {
		return contactCountIn1Week;
	}

	public void setContactCountIn1Week(int contactCountIn1Week) {
		this.contactCountIn1Week = contactCountIn1Week;
	}

	public int getContactCountIn1Month() {
		return contactCountIn1Month;
	}

	public void setContactCountIn1Month(int contactCountIn1Month) {
		this.contactCountIn1Month = contactCountIn1Month;
	}

	public int getContactCountIn3Month() {
		return contactCountIn3Month;
	}

	public void setContactCountIn3Month(int contactCountIn3Month) {
		this.contactCountIn3Month = contactCountIn3Month;
	}

	public int getContactCountInEarlymorning() {
		return contactCountInEarlymorning;
	}

	public void setContactCountInEarlymorning(int contactCountInEarlymorning) {
		this.contactCountInEarlymorning = contactCountInEarlymorning;
	}

	public int getContactCountInMorning() {
		return contactCountInMorning;
	}

	public void setContactCountInMorning(int contactCountInMorning) {
		this.contactCountInMorning = contactCountInMorning;
	}

	public int getContactCountInNoon() {
		return contactCountInNoon;
	}

	public void setContactCountInNoon(int contactCountInNoon) {
		this.contactCountInNoon = contactCountInNoon;
	}

	public int getContactCountInAfternoon() {
		return contactCountInAfternoon;
	}

	public void setContactCountInAfternoon(int contactCountInAfternoon) {
		this.contactCountInAfternoon = contactCountInAfternoon;
	}

	public int getContactCountInNight() {
		return contactCountInNight;
	}

	public void setContactCountInNight(int contactCountInNight) {
		this.contactCountInNight = contactCountInNight;
	}

	public boolean getContactCountInAllDay() {
		return contactCountInAllDay;
	}

	public void setContactCountInAllDay(boolean contactCountInAllDay) {
		this.contactCountInAllDay = contactCountInAllDay;
	}

	public int getContactCountInWeekday() {
		return contactCountInWeekday;
	}

	public void setContactCountInWeekday(int contactCountInWeekday) {
		this.contactCountInWeekday = contactCountInWeekday;
	}

	public int getContactCountInWeekend() {
		return contactCountInWeekend;
	}

	public void setContactCountInWeekend(int contactCountInWeekend) {
		this.contactCountInWeekend = contactCountInWeekend;
	}

	public int getContactCountInHoliday() {
		return contactCountInHoliday;
	}

	public void setContactCountInHoliday(int contactCountInHoliday) {
		this.contactCountInHoliday = contactCountInHoliday;
	}

	public int getCallinLength() {
		return callinLength;
	}

	public void setCallinLength(int callinLength) {
		this.callinLength = callinLength;
	}

	public int getCalloutLength() {
		return calloutLength;
	}

	public void setCalloutLength(int calloutLength) {
		this.calloutLength = calloutLength;
	}

	public String getNeedsType() {
		return needsType;
	}

	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}
}
