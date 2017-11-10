package omni.model;

import omni.database.DataContainer;
import omni.database.mongo.DerivativeVariables;

public class User 
{
	private Integer x_AllContactCount;		//	Mangodb	(Id_Number1)
	private final String contactorCitiesNumber = null;	//	TODO: Need computing	(Id_Number2)
	//	mongodb
	private String x_JXL_ReportData_IsAlwaysPowerOff;		//	(Id_Number3)
	private Integer x_JXL_ReportData_CallIn_Count;		//	(Surname)
	private Integer x_JXL_ReportData_CallIn_Length;		//	(First_Name)
	private Integer x_JXL_ReportData_CallOut_Count;		//	(Middle_Name)
	private Integer x_JXL_ReportData_CallOut_Length;	//	(Home_Address1)
	private Integer x_JXL_ReportData_Contact_Count;		//	(Home_Address2)
	private Integer x_JXL_ReportData_ContactNumber_Count;	//	(Home_Address3)
	private Integer x_JXL_ReportData_regContactPhoneInJXLNum;	//	(Home_Address4)
	private Integer x_JXL_ReportData_ContactRegion_Count;		//	(Home_Address5)
	private Integer x_JXL_ReportData_DataExistMonth_Count;		//	(Home_Address6)
	private Boolean x_JXL_ReportData_CallFinancePhone;		//	(Company_Name)
	private Boolean x_JXL_ReportData_CallJieXinPhone;		//	(Company_Address1)
	private Boolean x_JXL_ReportData_CallLoanPhone;			//	(Company_Address2)
	private String x_JXL_ReportData_IsNewNumber;			//	(Company_Address3)
	private String x_JXL_ReportData_IsProviderInfoMatch;	//	(Company_Address4)
	private String x_JXL_ReportData_IsRealAuthenticated;	//	(Company_Address5)

	/**第一联系人是否存在于用户通讯录*/
	private Integer X_IsContact1Inlist;
    /**第二联系人是否存在于用户通讯录*/
	private Integer X_IsContact2Inlist;
    /**第一联系人手机号码在用户通讯录中的称呼，不存在的话为-1*/
	private String  X_Contact1nameInList;
    /**第二联系人手机号码在用户通讯录中的称呼，不存在的话为-1*/
	private String X_Contact2nameInList;
    /**第1联系人在通讯录里的称呼中是否包含姓名中的某一个字---模糊匹配*/
	private Boolean X_IsContact1True;
    /**第2联系人在通讯录里的称呼中是否包含姓名中的某一个字---模糊匹配*/
	private Boolean X_IsContact2True;
    /**通讯录中的所有称呼里面包含敏感词库中的敏感词的个数*/
	private int X_ContactNumberOfBlack;
    /**通讯录厚度----计算通讯录电话非重复个数，包括固话和短号*/
	private int X_ContactNumberOfMobile;
    /**通讯录中的所有称呼里面包含“买单侠”的称呼的个数*/
	private int X_ContactNumberOfMDX; 
	
	public User(String appId)
	{
		this(appId, "");
	}

	public User(String appId, String instinctCall) 
	{
		if ("precheck".equalsIgnoreCase(instinctCall)){
		    DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
		    if (mongodv != null){
		        this.X_IsContact1Inlist = (mongodv.X_IsContact1Inlist==null?null:(mongodv.X_IsContact1Inlist==true?1:0));
		        this.X_IsContact2Inlist = (mongodv.X_IsContact2Inlist==null?null:(mongodv.X_IsContact2Inlist==true?1:0));
		        this.X_Contact1nameInList = mongodv.X_Contact1nameInList;
		        this.X_Contact2nameInList =mongodv.X_Contact2nameInList;
		        this.X_IsContact1True = mongodv.X_IsContact1True;
		        this.X_IsContact2True = mongodv.X_IsContact2True;
		        this.X_ContactNumberOfBlack = mongodv.X_ContactNumberOfBlack;
		        this.X_ContactNumberOfMobile = mongodv.X_ContactNumberOfMobile;
		        this.X_ContactNumberOfMDX = mongodv.X_ContactNumberOfMDX;
		    }	    
		}else{
		    this.initialize(appId);  
		}
	}

	private void initialize(String appId) 
	{
		DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
		
		if (mongodv != null)
		{		
			this.x_AllContactCount = mongodv.X_AllContactCount;
			this.x_JXL_ReportData_IsAlwaysPowerOff = mongodv.X_JXL_ReportData_IsAlwaysPowerOff;
			this.x_JXL_ReportData_CallIn_Count = mongodv.X_JXL_ReportData_CallIn_Count;
			this.x_JXL_ReportData_CallIn_Length = mongodv.X_JXL_ReportData_CallIn_Length;
			this.x_JXL_ReportData_CallOut_Count = mongodv.X_JXL_ReportData_CallOut_Count;
			this.x_JXL_ReportData_CallOut_Length = mongodv.X_JXL_ReportData_CallOut_Length;
			this.x_JXL_ReportData_Contact_Count = mongodv.X_JXL_ReportData_Contact_Count;
			this.x_JXL_ReportData_ContactNumber_Count = mongodv.X_JXL_ReportData_ContactNumber_Count;
			this.x_JXL_ReportData_regContactPhoneInJXLNum = mongodv.X_JXL_ReportData_regContactPhoneInJXLNum;
			this.x_JXL_ReportData_ContactRegion_Count = mongodv.X_JXL_ReportData_ContactRegion_Count;
			this.x_JXL_ReportData_DataExistMonth_Count = mongodv.X_JXL_ReportData_DataExistMonth_Count;
			this.x_JXL_ReportData_CallFinancePhone = mongodv.X_JXL_ReportData_CallFinancePhone;
			this.x_JXL_ReportData_CallJieXinPhone = mongodv.X_JXL_ReportData_CallJieXinPhone;
			this.x_JXL_ReportData_CallLoanPhone = mongodv.X_JXL_ReportData_CallLoanPhone;
			this.x_JXL_ReportData_IsNewNumber = mongodv.X_JXL_ReportData_IsNewNumber;
			this.x_JXL_ReportData_IsProviderInfoMatch = mongodv.X_JXL_ReportData_IsProviderInfoMatch;
			this.x_JXL_ReportData_IsRealAuthenticated = mongodv.X_JXL_ReportData_IsRealAuthenticated;
			this.X_IsContact1Inlist = (mongodv.X_IsContact1Inlist==null?null:(mongodv.X_IsContact1Inlist==true?1:0));
            this.X_IsContact2Inlist = (mongodv.X_IsContact2Inlist==null?null:(mongodv.X_IsContact2Inlist==true?1:0));
            this.X_Contact1nameInList = mongodv.X_Contact1nameInList;
            this.X_Contact2nameInList =mongodv.X_Contact2nameInList;
            this.X_IsContact1True = mongodv.X_IsContact1True;
            this.X_IsContact2True = mongodv.X_IsContact2True;
            this.X_ContactNumberOfBlack = mongodv.X_ContactNumberOfBlack;
            this.X_ContactNumberOfMobile = mongodv.X_ContactNumberOfMobile;
            this.X_ContactNumberOfMDX = mongodv.X_ContactNumberOfMDX;
		}		
	}

	public final Integer getX_AllContactCount()
	{
		return this.x_AllContactCount;
	}
	
	public final String getContactorCitiesNumber()
	{
		return this.contactorCitiesNumber;
	}
	
	public final String getX_JXL_ReportData_IsAlwaysPowerOff()
	{
		return this.x_JXL_ReportData_IsAlwaysPowerOff;
	}
	
	public final Integer getX_JXL_ReportData_CallIn_Count()
	{
		return this.x_JXL_ReportData_CallIn_Count;
	}

	public final Integer getX_JXL_ReportData_CallIn_Length()
	{
		return this.x_JXL_ReportData_CallIn_Length;
	}
	
	public final Integer getX_JXL_ReportData_CallOut_Count()
	{
		return this.x_JXL_ReportData_CallOut_Count;
	}
	
	public final Integer getX_JXL_ReportData_CallOut_Length()
	{
		return this.x_JXL_ReportData_CallOut_Length;
	}
	
	public final Integer getX_JXL_ReportData_Contact_Count()
	{
		return this.x_JXL_ReportData_Contact_Count;
	}
	
	public final Integer getX_JXL_ReportData_ContactNumber_Count()
	{
		return this.x_JXL_ReportData_ContactNumber_Count;
	}
	
	public final Integer getX_JXL_ReportData_regContactPhoneInJXLNum()
	{
		return this.x_JXL_ReportData_regContactPhoneInJXLNum;
	}

	public final Integer getX_JXL_ReportData_ContactRegion_Count()
	{
		return this.x_JXL_ReportData_ContactRegion_Count;
	}
	
	public final Integer getX_JXL_ReportData_DataExistMonth_Count()
	{
		return this.x_JXL_ReportData_DataExistMonth_Count;
	}
	
	public final Boolean getX_JXL_ReportData_CallFinancePhone()
	{
		return this.x_JXL_ReportData_CallFinancePhone;
	}
	
	public final Boolean getX_JXL_ReportData_CallJieXinPhone()
	{
		return this.x_JXL_ReportData_CallJieXinPhone;
	}
	
	public final Boolean getX_JXL_ReportData_CallLoanPhone()
	{
		return this.x_JXL_ReportData_CallLoanPhone;
	}
	
	public final String getX_JXL_ReportData_IsNewNumber()
	{
		return this.x_JXL_ReportData_IsNewNumber;
	}
	
	public final String getX_JXL_ReportData_IsProviderInfoMatch()
	{
		return this.x_JXL_ReportData_IsProviderInfoMatch;
	}
	
	public final String getX_JXL_ReportData_IsRealAuthenticated()
	{
		return this.x_JXL_ReportData_IsRealAuthenticated;
	}

    public final Integer getX_IsContact1Inlist() {
        return X_IsContact1Inlist;
    }

    public final Integer getX_IsContact2Inlist() {
        return X_IsContact2Inlist;
    }

    public final String getX_Contact1nameInList() {
        return X_Contact1nameInList;
    }

    public final String getX_Contact2nameInList() {
        return X_Contact2nameInList;
    }

    public final Boolean getX_IsContact1True() {
        return X_IsContact1True;
    }

    public final Boolean getX_IsContact2True() {
        return X_IsContact2True;
    }

    public final int getX_ContactNumberOfBlack() {
        return X_ContactNumberOfBlack;
    }

    public final int getX_ContactNumberOfMobile() {
        return X_ContactNumberOfMobile;
    }

    public final int getX_ContactNumberOfMDX() {
        return X_ContactNumberOfMDX;
    }
	
	
}
