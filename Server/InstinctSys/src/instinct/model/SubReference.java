package instinct.model;

import util.InstinctizeUtil;

public class SubReference 
{
    /** Text    1   Must be “R” */
	public final String category = "R";	//	Text	1	Must be “R”.
//	public String Id_Number1;		//	Text	60	
//	public String Id_Number2;		//	Text	60	
//	public String Id_Number3;		//	Text	60	
	/** Text   300 联系人姓名(contactName) */
	public String surname;			//	Text	300	联系人姓名（contactName）
	/** Text   300 联系人类型(contactPersonType) */
	public String first_Name;		//	Text	300	联系人类型（contactPersonType）
	/** Text   200 联系人是否在手机通讯录中 */
	public String middle_Name;		//	Text	200	联系人是否在手机通讯录中
	/** Text   140 联系人手机归属地 X_FirstContactMobileCity/X_SecondContactMobileCity */
	public String home_Address1;	//	Text	140	联系人手机归属地 X_FirstContactMobileCity	
	/** Text   140 联系人手机归属地省份 X_FirstContactMobileProvince/X_SecondContactMobileProvince */
	public String home_Address2;	//	Text	140	
//	public String Home_Address3;	//	Text	140	
//	public String Home_Address4;	//	Text	140	
//	public String Home_Address5;	//	Text	140	
//	public String Home_Address6;	//	Text	140	
//	public String Home_Postcode;	//	Text	20	
//	public String Home_Phone_Number;		//	Text	64	
	/** Text   64  联系人电话号码(contact) */
	public String mobile_Phone_Number;		//	Text	64	联系人电话号码（contact）
//	public String Company_Phone_Number;	//	Text	64	
	/** Text   60  第一联系人申请人关系审核(checkFirstContactIdentificationResult) */
	public String user_Field1;		//	Text	60	第一联系人申请人关系审核(checkFirstContactIdentificationResult)
	/** Text   60  第一联系人通话人接听情况(checkFirstContactPhoneAnswererResult) */
	public String user_Field2;		//	Text	60	第一联系人通话人接听情况(checkFirstContactPhoneAnswererResult)
	/** Text   100 第一联系人电话是否正常接听(checkFirstContactPhoneCallResult) */
	public String user_Field6;		//	Text	100	第一联系人电话是否正常接听(checkFirstContactPhoneCallResult)
	/** Text   100 第一联系人提示申请人是否有风险(checkFirstContactRiskPromptResult) */
	public String user_Field7;		//	Text	100	第一联系人提示申请人是否有风险(checkFirstContactRiskPromptResult)
//	public String User_Field5;		//	Date	8	
	/** Text   100 第一联系人声音与常识推断(checkFirstContactSoundJudgeResult) */
	public String user_Field8;		//	Text	100	第一联系人声音与常识推断(checkFirstContactSoundJudgeResult)
	/** Text   100 第一联系人申请人生肖审核(x_CheckFirstContactZodiacResult) */
	public String user_Field9;		//	Text	100	第一联系人申请人生肖审核（x_CheckFirstContactZodiacResult）
	/** Text   100 第二联系人电话审核联系人是否在分期现场(checkSecondContactHereJudgeResult)   */
	public String user_Field10;		//	Text	100  第二联系人电话审核联系人是否在分期现场(checkSecondContactHereJudgeResult)	
//	public String User_Field9;		//	Text	100	
//	public String User_Field10;	//	Text	100	
	
	
	public SubReference(omni.model.SubReference posapp)
	{
		surname = InstinctizeUtil.string(posapp.getContactName());
		first_Name = InstinctizeUtil.string(posapp.getRelationship());
		middle_Name = InstinctizeUtil.string(posapp.getContactInBook());
		mobile_Phone_Number = InstinctizeUtil.string(posapp.getContactMobile());
		user_Field1 = InstinctizeUtil.string(posapp.getX_CheckContactIdentificationResult());
		user_Field2 = InstinctizeUtil.string(posapp.getX_CheckContactPhoneAnswererResult());
		user_Field6 = InstinctizeUtil.string(posapp.getX_CheckContactPhoneCallResult());
		String userField7tmp1 = InstinctizeUtil.string(posapp.getX_CheckContactRiskPromptResult());
		String userField7tmp2 = InstinctizeUtil.string(posapp.getX_CheckContactRiskPromptResultForPDL());
		user_Field7 = "".equalsIgnoreCase(userField7tmp1) ? userField7tmp2 : userField7tmp1; 
		user_Field8 = InstinctizeUtil.string(posapp.getX_CheckContactSoundJudgeResult());
		user_Field9 = InstinctizeUtil.string(posapp.getX_CheckContactZodiacResult());	
		user_Field10 = InstinctizeUtil.string(posapp.getX_CheckContactHereJudgeResult());
		home_Address1 = InstinctizeUtil.string(posapp.getX_ContactMobileCity());
		// v20160809 add start
	    home_Address2 = InstinctizeUtil.string(posapp.getX_ContactMobileProvince());
		// v20160809 add end
	}
}
