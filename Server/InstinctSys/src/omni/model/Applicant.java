package omni.model;

import catfish.base.business.object.JobInfoObject;
import catfish.base.business.object.PersonInfoObject;
import catfish.base.business.object.UserObject;
import omni.database.DataContainer;
import omni.database.catfish.object.EndUserExtensionObject;
import omni.database.catfish.object.hybrid.AppContactObject;
import omni.database.catfish.object.hybrid.AppOtherInfoObject;
import omni.database.mongo.DerivativeVariables;
import omni.model.type.EducationalBackground;
import omni.model.type.LivingCondition;
import omni.model.type.MarriageStatus;
import omni.model.type.Nationality;
import omni.model.type.NthJob;

/**
 * @author guoqing
 * @version 1.1.0
 */
public class Applicant 
{
	
	private String idNumber;		//	UserId->EndUserExtensionObjects	(Id_Number1)
	private String qqNumber;		//	appId->PersonalInfoObjects->QQcontactId->ContactObjects	(Id_Number2)
	private String weiXinUserId;	//	[catfish].[dbo].[UserObjects]->WeiXinUserId	(Id_Number)
	private String idName;			//	UserId->EndUserExtensionObjects	[dw].[dbo].[Application]	(Surname)
	private String nickName;		//	WeiXinUserId-> [catfish].[dbo].[WeiXinUserObjects]	(First_Name)
	private String x_IdCardGender;	//	mangodb (Sex)
	private String x_IdCardProvince; //	mangodb (Home_Address1)
	private String x_IdCardCity;	//	mangodb (Home_Address2)
	private String x_IdCardDistrict; //	mangodb (Home_Address3)
	private String livingAddress;	//	appId->[personalinforobjects]	(Home_Address4)
	private String mobile;			//	UserId->[catfish].[dbo].[UserObjects]->MobileContactId->
									//	[catfish].[dbo].[ContactObjects]->content	(Mobile_Phone_Number)
	private String companyName;		//	applicationId->[jobinfoobjects]	(Company_Name)
	private String companyAddress;	//	applicationId->[jobinfoobjects]	(Company_Address1)
	private String companyPhone;	//	ApplicationId(AppId)->[catfish].[dbo].[JobInfoObjects]->CompanyTelId-> 
									//	[catfish].[dbo].[ContactObjects]->content	(Company_Phone_Number)
	private String x_UserMobileServiceProvider;		//	Mongodb	(User_Field1)
	private Integer livingCondition;	//	appId->[personalinforobjects]	(User_Field2)
	private Integer education;	//	userId->[enduserextensionobjects]	(User_Field3)
	private Integer nthJob;			//	applicationId->[jobinfoobjects]	(User_Field4)
	private Integer marriageStatus;	//	appId->[personalinforobjects]	(User_Field5)
	private String nativePerson;	//	(city==x_IdCardCity)?"true":"false"	(User_Field6)
	private String departmentName;	//	applicationId->[jobinfoobjects]	(User_Field8)
	private String x_UserMobileCity; //	Mangodb	(User_Field9)
	private Double monthlyIncome;	//	applicationId->[jobinfoobjects]	(User_Field10)
	private String x_InputIdCardInfoNationality;		//	mangodb:x_InputIdCardInfoNationality 
	private String bankAccount;		//	applicationid->[catfish].[dbo].[PaymentApplicationObjects] 
									//	-> paymentid->[catfish].[dbo].[PaymentObjects]->BankAccount	(User_Field14)	
	private final String bankCity = null;	//	Null for now	(User_Field15)
	
	/**
	 * 年龄
	 */
	private Integer x_IdCardAge;
	/**
     * 公司电话归属地省份
     */
    private String x_CompanyPhoneProvince;
    /**
     * 公司电话归属地
     */
    private String x_CompanyPhoneCity;
    /**
     * 手机归属地省份
     */
    private String x_UserMobileProvince;
	
	public Applicant()
	{
		
	}
	
	public Applicant(String appId)
	{
		this(appId, "");
	}
	
	/**
	 * This constructor generates information for preCheck phase.
	 * 
	 * @param appId application ID. 
	 * @param instinctCall supports only "preCheck".
	 * @since 1.1.0
	 * 
	 */
	public Applicant(String appId, String instinctCall)
	{
		if ("precheck".equalsIgnoreCase(instinctCall))
		{
			EndUserExtensionObject endUser = DataContainer.endUsrObj.get(appId);
			AppContactObject conObj = DataContainer.conObj.get(appId);
			this.idNumber = endUser.idNumber;
			this.idName = endUser.idName;
			if (conObj != null)
			{
				this.qqNumber = conObj.qqNumber;
				this.mobile = conObj.mobile;
				this.companyPhone = conObj.companyPhone;
			}
		}
		else
		{
			this.initialize(appId);
		}
	}	
	
	private void initialize(String appId)
	{
		JobInfoObject jobInfo = DataContainer.jobObj.get(appId);
		PersonInfoObject perInfo = DataContainer.personObj.get(appId);
		EndUserExtensionObject endUser = DataContainer.endUsrObj.get(appId);
		UserObject usrObj = DataContainer.usrObj.get(appId); 
		AppContactObject conObj = DataContainer.conObj.get(appId);
		AppOtherInfoObject otherInfoObj = DataContainer.otherInfoObj.get(appId);
		DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
		
		this.idNumber = endUser.idNumber;
		this.idName = endUser.idName;
		this.education = endUser.education;
		this.weiXinUserId = usrObj.WeiXinUserId;

		if (conObj != null)
		{
			this.qqNumber = conObj.qqNumber;
			this.mobile = conObj.mobile;
			this.companyPhone = conObj.companyPhone;
		}			
		
		if (otherInfoObj != null)
		{
			this.nickName = otherInfoObj.nickName;
			this.bankAccount = otherInfoObj.bankAccount;
		}
		
		if (perInfo != null)
		{
			this.livingAddress = perInfo.LivingAddress;
			this.livingCondition = perInfo.LivingCondition; 	
			this.marriageStatus = perInfo.MarriageStatus;
		}
		
		if (jobInfo != null)
		{
			this.companyName = jobInfo.CompanyName;
			this.companyAddress = jobInfo.CompanyAddress;
			this.nthJob = jobInfo.NthJob;					
			this.departmentName = jobInfo.DepartmentName;
			this.monthlyIncome = jobInfo.MonthlyIncome.doubleValue();
		}		
		
		if (mongodv != null)
		{
			this.x_IdCardGender = mongodv.X_IdCardGender;
			this.x_IdCardProvince = mongodv.X_IdCardProvince;
			this.x_IdCardCity = mongodv.X_IdCardCity;	
			this.x_IdCardDistrict = mongodv.X_IdCardDistrict;

			this.x_UserMobileServiceProvider = mongodv.X_UserMobileServiceProvider;

			this.x_UserMobileCity = mongodv.X_UserMobileCity;
			this.nativePerson = x_UserMobileCity == x_IdCardCity ? "Yes" : "No";

			this.x_InputIdCardInfoNationality = mongodv.X_InputIdCardInfoNationality == null ? "11" : mongodv.X_InputIdCardInfoNationality;
			
			// v20160802 add start
	        this.x_IdCardAge = mongodv.X_IdCardAge;
	        this.x_CompanyPhoneProvince = mongodv.X_CompanyPhoneProvince;
	        this.x_CompanyPhoneCity  = mongodv.X_CompanyPhoneCity;
	        this.x_UserMobileProvince = mongodv.X_UserMobileProvince;
	        // v20160802 add end
		}
	}	
	
	public String getIdNumber()
	{
		return this.idNumber;
	}
	
	public String getQQNumber()
	{
		return this.qqNumber;
	}
	
	public String getWeiXinUserId()
	{
		return this.weiXinUserId;
	}
	
	public String getIdName()
	{
		return this.idName;
	}

	public final String getNickName()
	{
		return this.nickName;
	}
	
	public final String getX_IdCardGender()
	{
		return this.x_IdCardGender;
	}
	
	public final String getX_IdCardProvince()
	{
		return this.x_IdCardProvince;
	}
	
	public final String getX_IdCardCity()
	{
		return this.x_IdCardCity;
	}
	
	public final String getX_IdCardDistrict()
	{
		return this.x_IdCardDistrict;
	}
	
	public final String getLivingAddress()
	{
		return this.livingAddress;
	}
	
	public String getMobile()
	{
		return this.mobile;
	}
	
	public final String getCompanyName()
	{
		return this.companyName;
	}
	
	public final String getCompanyAddress()
	{
		return this.companyAddress;
	}
	
	public final String getCompanyPhone()
	{
		return this.companyPhone;
	}
	
	public final String getX_UserMobileServiceProvider()
	{
		return this.x_UserMobileServiceProvider;
	}
	
	public final LivingCondition getLivingCondition()
	{
		return this.livingCondition == null ? null : LivingCondition.forValue(this.livingCondition);
	}
	
	public final Double getMonthlyIncome()
	{
		return this.monthlyIncome;
	}
	
	public final NthJob getNthJob()
	{
		return this.nthJob == null ? null : NthJob.forValue(this.nthJob);
	}
	
	public final MarriageStatus getMarriageStatus()
	{
		return this.marriageStatus == null ? null : MarriageStatus.forValue(this.marriageStatus);
	}
	
	public final String getNativePerson()
	{
		return this.nativePerson;
	}
	
	public final String getDepartmentName()
	{
		return this.departmentName;
	}
	
	public final String getX_UserMobileCity()
	{
		return this.x_UserMobileCity;
	}
	
	public final EducationalBackground getEducationStatus()
	{
		return this.education == null ? null : EducationalBackground.forValue(this.education);
	}
	
	public final Nationality getNationality()
	{
		return Nationality.forValue(this.x_InputIdCardInfoNationality);
	}
	
	public final String getBankAccount()
	{
		return this.bankAccount;
	}
	
	public final String getBankCity()
	{
		return this.bankCity;
	}

    public Integer getX_IdCardAge() {
        return x_IdCardAge;
    }

    public String getX_CompanyPhoneProvince() {
        return x_CompanyPhoneProvince;
    }

    public String getX_CompanyPhoneCity() {
        return x_CompanyPhoneCity;
    }

    public String getX_UserMobileProvince() {
        return x_UserMobileProvince;
    }
	
}
