package instinct.model;

import util.InstinctizeUtil;

public class User 
{
	public final String category = "U";	//	Text	1	Must be “U”.
	public String id_Number1;		//	Text	60	联系人数(allContactCount)
	public String id_Number2;		//	Text	60	联系人不同城市数（contactorCitiesNumber）
	public String id_Number3;		//	Text	60	是否长时间关机(默认值无)(alwaysPowerOff)
	public String surname;			//	Text	300	呼入通话总数量(默认值-1)(callInCount)
	public String first_Name;		//	Text	300	呼入通话总时长(默认值-1)(callInLength)
	public String middle_Name;		//	Text	200	呼出通话总数量(默认值-1)(callOutCount)
//	public String Date_Of_Birth;	//	Date	8	
	public String home_Address1;	//	Text	140	呼出通话总时长(默认值-1)(callOutLength)
	public String home_Address2;	//	Text	140	联系人数量(默认值-1)(contactCount)
	public String home_Address3;	//	Text	140	联系号码数量(默认值-1)(contactNumberCount)
	public String home_Address4;	//	Text	140	用户所填联系人电话在聚信力中出现个数(默认值0)(contactPhoneNumberInJxlCount)
	public String home_Address5;	//	Text	140	联系地区数量(默认值-1)(contactRegionCount)
	public String home_Address6;	//	Text	140	有数据月数(默认值-1)(dataExistMonthCount)
//	public String Home_Phone_Number;	//	Text	64	
//	public String Mobile_Phone_Number;	//	Text	64	
	public String company_Name;		//	Text	300	是否拨打含‘金融’的电话(默认值否)(hasCallFinancePhone)
	public String company_Address1;	//	Text	140	是否拨打含‘捷信’的电话(默认值否)(hasCallJiexinPhone)
	public String company_Address2;	//	Text	140	是否拨打含‘贷款’的电话(默认值否)(hasCallLoanPhone)
	public String company_Address3;	//	Text	140	绑定号码是否为新号(默认值无)(newNumber)
	public String company_Address4;	//	Text	140	运营商登记人身份证号码和姓名是否匹配(默认值无)(providerInfoMatch)
	public String company_Address5;	//	Text	140	是否实名认证(默认值无)(realAuthencated)
	public String Company_Address6;	//	Text	140	第一联系人是否存在于用户通讯录
	public String Company_Phone_Number;	//	Text	64	第二联系人是否存在于用户通讯录
	public String User_Field1;		//	Text	60	第一联系人手机号码在用户通讯录中的称呼，不存在的话为-1
	public String User_Field2;		//	Text	60	第二联系人手机号码在用户通讯录中的称呼，不存在未-1 
	public String User_Field6;		//	Text	100	第1联系人在通讯录里的称呼中是否包含姓名中的某一个字
	public String User_Field7;		//	Text	100	第2联系人在通讯录里的称呼中是否包含姓名中的某一个字
	public String User_Field8;		//	Text	100	黑词个数-----敏感词库
	public String User_Field9;     //  Text    100 通讯录厚度----计算通讯录电话非重复个数，包括固话和短号
	public String User_Field10;     //  Text    100 含买单侠字眼个数，有“买单侠”
	
	public User(omni.model.User posusr)
	{
		this.id_Number1 = InstinctizeUtil.string(posusr.getX_AllContactCount());
		
		this.id_Number2 = InstinctizeUtil.string(posusr.getContactorCitiesNumber());
		
		this.id_Number3 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_IsAlwaysPowerOff());
		
		this.surname = InstinctizeUtil.string(posusr.getX_JXL_ReportData_CallIn_Count());
		
		this.first_Name = InstinctizeUtil.string(posusr.getX_JXL_ReportData_CallIn_Length());
		
		this.middle_Name = InstinctizeUtil.string(posusr.getX_JXL_ReportData_CallOut_Count());
		
		this.home_Address1 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_CallOut_Length());
		
		this.home_Address2 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_Contact_Count());
		
		this.home_Address3 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_ContactNumber_Count());
		
		this.home_Address4 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_regContactPhoneInJXLNum());
		
		this.home_Address5 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_ContactRegion_Count());
		
		this.home_Address6 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_DataExistMonth_Count());
		
		this.company_Name = InstinctizeUtil.string(posusr.getX_JXL_ReportData_CallFinancePhone());
		
		this.company_Address1 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_CallJieXinPhone());
		
		this.company_Address2 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_CallLoanPhone());
		
		this.company_Address3 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_IsNewNumber());
		
		this.company_Address4 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_IsProviderInfoMatch());
		
		this.company_Address5 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_IsRealAuthenticated());
		
		this.Company_Address6 = InstinctizeUtil.string(posusr.getX_IsContact1Inlist());
		this.Company_Phone_Number = InstinctizeUtil.string(posusr.getX_IsContact2Inlist());
		this.User_Field1 = InstinctizeUtil.string(posusr.getX_Contact1nameInList());
		this.User_Field2 = InstinctizeUtil.string(posusr.getX_Contact2nameInList());
		this.User_Field6 = InstinctizeUtil.string(posusr.getX_IsContact1True());
		this.User_Field7 = InstinctizeUtil.string(posusr.getX_IsContact2True());
		this.User_Field8 = InstinctizeUtil.string(posusr.getX_ContactNumberOfBlack());
		this.User_Field9 = InstinctizeUtil.string(posusr.getX_ContactNumberOfMobile());
		this.User_Field10 = InstinctizeUtil.string(posusr.getX_ContactNumberOfMDX());
	}

	public User() 
	{
		// TODO Auto-generated constructor stub
	}
}
