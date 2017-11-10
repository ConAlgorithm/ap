package instinct.model;

import util.InstinctizeUtil;

public class Applicant 
{
    /** A */
	public final String category = "A";	//	Text	1	Must be “A”.
	/** Text   60  申请人证件号码 */
	public String id_Number1;	//	Text	60	申请人证件号码
	/** Text   60  客户QQ */
	public String id_Number2;	//	Text	60	客户QQ
	/** Text   60  微信用户ID */
	public String id_Number3;	//	Text	60	微信用户ID
	/** Text   300  客户姓名 */
	public String surname;		//	Text	300	客户姓名
	/** Text   300  微信用户名 */
	public String first_Name;	//	Text	300	微信用户名
	//public String Middle_Name;	//	Text	200	
	/** Text   2   性别 */
	public String sex;				//	Text	2	性别
//	public String Date_Of_Birth;	//	Date	8	
	/** Text   140 户籍地省份(IdCardProvince) */
	public String home_Address1;	//	Text	140	户籍地省份(IdCardProvince)
	/** Text   140 户籍地城市(IdCardCity) */
	public String home_Address2;	//	Text	140	户籍地城市(IdCardCity)
	/** Text   140 户籍地区县(IdCardArea) */
	public String home_Address3;	//	Text	140	户籍地区县(IdCardArea)
	/** Text   140 家庭地址详细信息(Living Address) */
	public String home_Address4;	//	Text	140	家庭地址详细信息（Living Address）
	
//	public String Home_Address5;	//	Text	140	
//	public String Home_Address6;	//	Text	140	
//	public String Home_Postcode;	//	Text	20	
//	public String Home_Phone_Number;	//	Text	64	
	/** Text   64  移动电话 */
	public String mobile_Phone_Number;	//	Text	64	移动电话
	/** Text   300 工作单位名称 */
	public String company_Name;		//	Text	300	工作单位名称
	/** Text   140 单位地址详细信息(CompanyAddress) */
	public String company_Address1;	//	Text	140	单位地址详细信息（CompanyAddress）
//	public String Company_Address2;	//	Text	140	
//	public String Company_Address3;	//	Text	140	
//	public String Company_Address4;	//	Text	140	
//	public String Company_Postcode;	//	Text	20	
	/** Text   64  单位电话(Company Phone Number) */
	public String company_Phone_Number;	//	Text	64	单位电话（Company Phone Number）
	/** Text   60  手机供应商(UserMobileServiceProvider) */
	public String user_Field1;		//	Text	60	手机供应商（UserMobileServiceProvider）
	/** Text   60  居住性质(Living Condition) */
	public String user_Field2;		//	Text	60	居住性质（Living Condition）
	/** Text   60  教育程度(Education) */
	public String user_Field3;		//	Text	60	教育程度（Education）
	/** Text   60  第几份工作(NthJob) */
	public String user_Field4;		//	Text	60	第几份工作（NthJob）
	/** Text   60  婚姻状况(MarriageStatus) */
	public String user_Field5;		//	Text	60	婚姻状况（MarriageStatus）
	/** Text   100  是否本地人(NativePerson) */
	public String user_Field6;		//	Text	100	是否本地人（NativePerson）
//	public String User_Field7;		//	Text	100	
	/** Text   100  所在部门名称(DepartmentName) */
	public String user_Field8;		//	Text	100	所在部门名称（DepartmentName）
	/** Text   100  手机归属地(localPhone) */
	public String user_Field9;		//	Text	100	手机归属地（localPhone）
	/** Numeric    8   保留字段    如果没有收入信息，则不启用 */
	public String user_Field10;	//	Numeric	8	保留字段	如果没有收入信息，则不启用
//	public double User_Field11;	//	Numeric	8	
//	public String User_Field12;	//	Date	8	
	/** Text   100 民族(Nationality) */
	public String user_Field13;	//	Text	100	民族（Nationality）
	/** Text   100 银行卡号(BankAccount) */
	public String user_Field14;	//	Text	100	银行卡号（BankAccount）
	/** Text   100 银行卡归属地 */
	public String user_Field15;	//	Text	100	银行卡归属地
//	public String user_Field16;	//	Text	100	微信头像（HeadImage）
	/** Text   100 年龄(X_IdCardAge) */
	public String user_Field17;	//	Text	100	
	/** Text   100 公司电话归属地省份(X_CompanyPhoneProvince) */
	public String user_Field18;	//	Text	100
	/** Text   100 公司电话归属地(X_CompanyPhoneCity) */
	public String user_Field19;	//	Text	100
	/** Text   100 手机归属地省份(X_UserMobileProvince) */
	public String user_Field20;	//	Text	100	
	
	public Applicant(omni.model.Applicant posapp)
	{
		id_Number1 = InstinctizeUtil.string(posapp.getIdNumber());	
		id_Number2 = InstinctizeUtil.string(posapp.getQQNumber());
		id_Number3 = InstinctizeUtil.string(posapp.getWeiXinUserId());
		surname = InstinctizeUtil.string(posapp.getIdName());
		first_Name = InstinctizeUtil.string(posapp.getNickName());
		sex = InstinctizeUtil.string(posapp.getX_IdCardGender());
		home_Address1 = InstinctizeUtil.string(posapp.getX_IdCardProvince());
		home_Address2 = InstinctizeUtil.string(posapp.getX_IdCardCity());
		home_Address3 = InstinctizeUtil.string(posapp.getX_IdCardDistrict());
		home_Address4 = InstinctizeUtil.string(posapp.getLivingAddress());
		mobile_Phone_Number = InstinctizeUtil.string(posapp.getMobile());
		company_Name = InstinctizeUtil.string(posapp.getCompanyName());
		company_Address1 = InstinctizeUtil.string(posapp.getCompanyAddress());
		company_Phone_Number = InstinctizeUtil.string(posapp.getCompanyPhone());
		user_Field1 = InstinctizeUtil.string(posapp.getX_UserMobileServiceProvider());
		user_Field2 = InstinctizeUtil.string(posapp.getLivingCondition());
		user_Field3 = InstinctizeUtil.string(posapp.getEducationStatus());
		user_Field4 = InstinctizeUtil.string(posapp.getNthJob());
		user_Field5 = InstinctizeUtil.string(posapp.getMarriageStatus());
		user_Field6 = InstinctizeUtil.string(posapp.getNativePerson());
		user_Field8 = InstinctizeUtil.string(posapp.getDepartmentName());
		user_Field9 = InstinctizeUtil.string(posapp.getX_UserMobileCity());
		user_Field10 = InstinctizeUtil.string(posapp.getMonthlyIncome());
		user_Field13 = InstinctizeUtil.string(posapp.getNationality());
		user_Field14 = InstinctizeUtil.string(posapp.getBankAccount());
		user_Field15 = InstinctizeUtil.string(posapp.getBankCity());
//		user_Field16 = InstinctizeUtil.string(posapp.getHeadImageUrl());
		
		// v20160802 add start
        user_Field17 = InstinctizeUtil.string(posapp.getX_IdCardAge());
        user_Field18 = InstinctizeUtil.string(posapp.getX_CompanyPhoneProvince());
        user_Field19  = InstinctizeUtil.string(posapp.getX_CompanyPhoneCity());
        user_Field20 = InstinctizeUtil.string(posapp.getX_UserMobileProvince());
        // v20160802 add end
	}
	
}