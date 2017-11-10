package omni.model;

import omni.database.catfish.object.hybrid.AppContactPersonObject;
import omni.database.mongo.DerivativeVariables;
import omni.model.type.CheckContactHereJudgeResult;
import omni.model.type.CheckContactIdentificationResult;
import omni.model.type.CheckContactPhoneAnswerResult;
import omni.model.type.CheckPhoneCallResult;
import omni.model.type.PDLCheckContactRiskPromptResult;
import omni.model.type.CheckContactRiskPromptResult;
import omni.model.type.CheckContactSoundJudgeResult;
import omni.model.type.CheckContactResultViaPhone;
import omni.model.type.RelationshipType;

/**
 * 
 * Please note that x_CheckContactRiskPromptResult and x_CheckContactRiskPromptResultForPDL have different type definitions as defined in 
 * <tt>Catfish</tt> project.<p>
 * Please see {@link CheckContactRiskPromptResult} and {@link PDLCheckContactRiskPromptResult} for reference.
 * 
 * @author guoqing
 * @version 1.1.0
 *
 */
public class SubReference 
{
	private String contactPersonId;		//	use this to locate the contact
	private Integer contactPersonType;	//	use to order this contact

	private String contactName;			//	1-many	Top 1 [catfish].[dbo].[ContactPersonObjects]->Name	(Surname)
	private Integer relationship;		//	Top 1 [catfish].[dbo].[ContactPersonObjects]->Relationship	(First_Name)
	private final String contactInBook = null;	// TODO: Null	(Middle_Name)
	private String contactMobile;	//	Top 1 [catfish].[dbo].[ContactPersonObjects]->MobileContactId->
									//	[catfish].[dbo].[ContactObjects]->content&type=2	(Mobile_Phone_Number)
	private Integer x_CheckContactIdentificationResult;	//	Mangodb (User_Field1)
	private Integer x_CheckContactPhoneAnswererResult;	//	Mangodb	(User_Field2)
	private Integer x_CheckContactPhoneCallResult;		//	Mangodb	(User_Field3)
	private Integer x_CheckContactRiskPromptResult;		//	Mangodb	(User_Field4)
	private Integer x_CheckContactRiskPromptResultForPDL;	//	Mangodb	(User_Field4) 
															//	!!Note: The type of this one is different from x_CheckContactRiskPromptResult!!
	private Integer x_CheckContactSoundJudgeResult;		//	Mangodb	(User_Field6)
	private Integer x_CheckContactZodiacResult;			//	Mangodb	(User_Field7)
	private Integer x_CheckContactHereJudgeResult;		//	Mangodb	(User_Field8)
	private String x_ContactMobileCity;	// (Home_Address1)
	
	/** 联系人手机归属地省份 */
	private String x_ContactMobileProvince;

	public SubReference(String appId, AppContactPersonObject cpObj, DerivativeVariables mongodv)
	{
		
		if (cpObj != null)
		{
			this.contactPersonType = cpObj.ContactPersonType;	
			this.contactName = cpObj.Name;
			this.relationship = cpObj.Relationship;
			this.contactMobile = cpObj.mobile;
		}
		
		if (mongodv != null)
		{
			switch (contactPersonType)
			{
			case 2:	// 直系亲属联系人
				this.x_CheckContactIdentificationResult = mongodv.X_CheckFirstContactIdentificationResult == null
						? mongodv.X_CheckFirstContactIdentificationResultForPDL : mongodv.X_CheckFirstContactIdentificationResult;
				this.x_CheckContactPhoneAnswererResult = mongodv.X_CheckFirstContactPhoneAnswererResult == null
						? mongodv.X_CheckFirstContactPhoneAnswererResultForPDL : mongodv.X_CheckFirstContactPhoneAnswererResult;
				this.x_CheckContactPhoneCallResult = mongodv.X_CheckFirstContactPhoneCallResult == null
						? mongodv.X_CheckFirstContactPhoneCallResultForPDL : mongodv.X_CheckFirstContactPhoneCallResult;
				this.x_CheckContactRiskPromptResult = mongodv.X_CheckFirstContactRiskPromptResult;
				this.x_CheckContactRiskPromptResultForPDL = mongodv.X_CheckFirstContactRiskPromptResultForPDL;
				this.x_CheckContactSoundJudgeResult = mongodv.X_CheckFirstContactSoundJudgeResult == null
						? mongodv.X_CheckFirstContactSoundForPDL : mongodv.X_CheckFirstContactSoundJudgeResult;
				this.x_CheckContactZodiacResult = mongodv.X_CheckFirstContactZodiacResult == null
						? mongodv.X_CheckFirstContactZodiacResultForPDL : mongodv.X_CheckFirstContactZodiacResult;
				this.x_CheckContactHereJudgeResult = mongodv.X_CheckFirstContactHereJudgeResult;
				this.x_ContactMobileCity = mongodv.X_FirstContactMobileCity;
				// v20160809 add start
                this.x_ContactMobileProvince = mongodv.X_FirstContactMobileProvince;
				// v20160809 add end
				break;
			case 1:	// 其他联系人
				this.x_CheckContactIdentificationResult = mongodv.X_CheckSecondContactIdentificationResult == null
						? mongodv.X_CheckSecondContactIdentificationResultForPDL : mongodv.X_CheckSecondContactIdentificationResult;
				this.x_CheckContactPhoneAnswererResult = mongodv.X_CheckSecondContactPhoneAnswererResult == null
						? mongodv.X_CheckSecondContactPhoneAnswererResultForPDL : mongodv.X_CheckSecondContactPhoneAnswererResult;
				this.x_CheckContactPhoneCallResult = mongodv.X_CheckSecondContactPhoneCallResult == null
						? mongodv.X_CheckSecondContactPhoneCallResultForPDL : mongodv.X_CheckSecondContactPhoneCallResult;
				this.x_CheckContactRiskPromptResult = mongodv.X_CheckSecondContactRiskPromptResult;
				this.x_CheckContactRiskPromptResultForPDL = mongodv.X_CheckSecondContactRiskPromptResultForPDL;
				this.x_CheckContactSoundJudgeResult = mongodv.X_CheckSecondContactSoundJudgeResult == null
						? mongodv.X_CheckSecondContactSoundForPDL : mongodv.X_CheckSecondContactSoundJudgeResult;
				this.x_CheckContactZodiacResult = mongodv.X_CheckSecondContactZodiacResult == null
						? mongodv.X_CheckSecondContactZodiacResultForPDL : mongodv.X_CheckSecondContactZodiacResult;
				this.x_CheckContactHereJudgeResult = mongodv.X_CheckSecondContactHereJudgeResult;
				this.x_ContactMobileCity = mongodv.X_SecondContactMobileCity;
				// v20160809 add start
                this.x_ContactMobileProvince = mongodv.X_SecondContactMobileProvince;
                // v20160809 add end
				break;
			case 3:	// 第三联系人
				this.x_CheckContactIdentificationResult = mongodv.X_CheckThirdContactIdentificationResult;
				this.x_CheckContactPhoneAnswererResult = mongodv.X_CheckThirdContactPhoneAnswererResult;
				this.x_CheckContactPhoneCallResult = mongodv.X_CheckThirdContactPhoneCallResult;
				this.x_CheckContactRiskPromptResult = mongodv.X_CheckThirdContactRiskPromptResult;
				this.x_CheckContactSoundJudgeResult = mongodv.X_CheckThirdContactSoundJudgeResult;
				this.x_CheckContactZodiacResult = mongodv.X_CheckThirdContactZodiacResult;
				this.x_CheckContactHereJudgeResult = mongodv.X_CheckThirdContactHereJudgeResult;
				this.x_ContactMobileCity = mongodv.X_ThirdContactMobileCity;
				// v20160809 add start
                this.x_ContactMobileProvince = mongodv.X_ThirdContactMobileProvince;
                // v20160809 add end
				break;
			default: // 默认
				this.x_CheckContactIdentificationResult = mongodv.X_CheckAdditionalContactIdentificationResult;
				this.x_CheckContactPhoneAnswererResult = mongodv.X_CheckAdditionalContactPhoneAnswererResult;
				this.x_CheckContactPhoneCallResult = mongodv.X_CheckAdditionalContactPhoneCallResult;
				this.x_CheckContactRiskPromptResult = mongodv.X_CheckAdditionalContactRiskPromptResult;
				this.x_CheckContactSoundJudgeResult = mongodv.X_CheckAdditionalContactSoundJudgeResult;
				this.x_CheckContactZodiacResult = mongodv.X_CheckAdditionalContactZodiacResult;
				this.x_CheckContactHereJudgeResult = mongodv.X_CheckAdditionalContactHereJudgeResult;
				this.x_ContactMobileCity = mongodv.X_AdditionalContactMobileCity;
				// v20160809 add start
                this.x_ContactMobileProvince = "";
                // v20160809 add end
				break;
			}

		}
	}
	
	/**
	 * This constructor generates information for preCheck phase.
	 *  
	 * @param cpObj AppContactPersonObject containing reference data.
	 * @see AppContactPersonObject
	 */
	public SubReference(AppContactPersonObject cpObj)
	{
		this.contactMobile = cpObj == null ? null : cpObj.mobile;
	}
	
	public final String getContactPersonId()
	{
		return this.contactPersonId;
	}

	public final String getContactName()
	{
		return this.contactName;
	}
	
	public final Integer getContactPersonType()
	{
		return this.contactPersonType == null ? null : this.contactPersonType;
	}
	
	public final RelationshipType getRelationship()
	{
		return this.relationship == null ? null : RelationshipType.forValue(this.relationship);
	}
	
	public final String getContactInBook()
	{
		return this.contactInBook;
	}
	
	public final String getContactMobile()
	{
		return this.contactMobile;
	}

	public final CheckContactIdentificationResult getX_CheckContactIdentificationResult()
	{
		return this.x_CheckContactIdentificationResult == null ? null
				: CheckContactIdentificationResult.forValue(this.x_CheckContactIdentificationResult);
	}
	
	public final CheckContactPhoneAnswerResult getX_CheckContactPhoneAnswererResult()
	{
		return this.x_CheckContactPhoneAnswererResult == null ? null
				: CheckContactPhoneAnswerResult.forValue(this.x_CheckContactPhoneAnswererResult);
	}
	
	public final CheckPhoneCallResult getX_CheckContactPhoneCallResult()
	{
		return this.x_CheckContactPhoneCallResult == null ? null
				: CheckPhoneCallResult.forValue(this.x_CheckContactPhoneCallResult);
	}
	
	public final CheckContactRiskPromptResult getX_CheckContactRiskPromptResult()
	{
		return this.x_CheckContactRiskPromptResult == null ? null
				: CheckContactRiskPromptResult.forValue(this.x_CheckContactRiskPromptResult);
	}
	
	public final PDLCheckContactRiskPromptResult getX_CheckContactRiskPromptResultForPDL()
	{
		return this.x_CheckContactRiskPromptResultForPDL == null ? null
				: PDLCheckContactRiskPromptResult.forValue(this.x_CheckContactRiskPromptResultForPDL);
	}
	
	public final CheckContactSoundJudgeResult getX_CheckContactSoundJudgeResult()
	{
		return this.x_CheckContactSoundJudgeResult == null ? null
				: CheckContactSoundJudgeResult.forValue(this.x_CheckContactSoundJudgeResult);
	}
	
	public final CheckContactResultViaPhone getX_CheckContactZodiacResult()
	{
		return this.x_CheckContactZodiacResult == null ? null
				: CheckContactResultViaPhone.forValue(this.x_CheckContactZodiacResult);
	}
	
	public final CheckContactHereJudgeResult getX_CheckContactHereJudgeResult()
	{
		return this.x_CheckContactHereJudgeResult == null ? null 
				: CheckContactHereJudgeResult.forValue(this.x_CheckContactHereJudgeResult);
	}

	public final String getX_ContactMobileCity() 
	{
		return this.x_ContactMobileCity;
	}

    public String getX_ContactMobileProvince() {
        return x_ContactMobileProvince;
    }
    
}
