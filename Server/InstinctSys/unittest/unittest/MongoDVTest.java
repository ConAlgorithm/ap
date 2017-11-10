package unittest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import omni.database.DatabaseClient;
import omni.database.mongo.DerivativeVariables;
import omni.database.mongo.OmniProdMongoClient;

public class MongoDVTest 
{

	private static OmniProdMongoClient mongo;
	private static DerivativeVariables dv;
	
	@BeforeClass
	public static void testBeforeClass() 
	{
		StartupConfig.initialize();
		Logger.initialize();
		
		mongo = DatabaseClient.mongo;
	}
	 
	@Before 
	public void testBefore() 
	{
		 
	} 
	 
	@Test
	public void testOthers() 
	{
		dv = mongo.load("4447F415-F78C-E511-98E3-AC853DA49BEC");
		assertEquals(1, dv.X_NumberOfSpecifiedAppFromSameStoreAndCompany.intValue());
		assertEquals(false, dv.X_ParentPhoneDoesAnyOtherReferenceExist);
		assertEquals(false, dv.X_HasSamePhoneNumber);
		//assertEquals("2015-11-17T06:47:38.000Z", dv.X_ApplicationStartedOn.toString());
		
		assertEquals("NM007", dv.X_StrategyCode);
		assertEquals("F1", dv.X_SegmentationCode);
		assertEquals("4000.00", dv.X_ApplicationMaxCredit);
		assertEquals(1, dv.X_AccompanyNum.intValue());
		assertEquals(2, dv.X_AllContactCount.intValue());
		assertEquals(0, dv.X_QQOtherUserReference.intValue());

		dv = mongo.load("37176B87-D259-E411-98E3-AC853DA49BEC");
		assertEquals("30", dv.X_CheckCourtExecuted.toString());
	}
	
	@Test
	public void testLoanMoney() 
	{
		dv = mongo.load("C7F73C6C-008D-E511-98E3-AC853DA49BEC");
		assertEquals("20", dv.X_LoanMoneyResult.toString());
		assertEquals("30", dv.X_LoanMoneyFailureReason.toString());
	}
	
	@Test
	public void testCheckPersonalInfo() 
	{
		dv = mongo.load("37176B87-D259-E411-98E3-AC853DA49BEC");
		assertEquals("20", dv.X_CheckPersonalInfoResidenceVSCompanyAddressResult.toString());
		assertEquals(10, dv.X_CheckPersonalInfoProfessionIdentificationResult.intValue());
		assertEquals(10, dv.X_CheckPersonalInfoCompanyTypeResult.intValue());
	}
	
	@Test
	public void testInputIdCard() 
	{
		dv = mongo.load("3C46E93C-7BA8-E411-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_InputIdCardInfoIsOtherCertProvided.intValue());
		assertEquals("江苏省宿迁市宿豫区皂河镇八井村二组68号", dv.X_InputIdCardInfoAddress);
		assertEquals("11", dv.X_InputIdCardInfoNationality);
	}
	
	@Test
	public void testTransactionMonitor() 
	{
		dv = mongo.load("28BA6B9C-D014-E511-98E3-AC853DA49BEC");
		assertEquals(5, dv.X_TransactionMonitorJobCount.intValue());
		assertEquals("30", dv.X_TransactionMonitorResult.toString());
		assertEquals("20", dv.X_TransactionMonitorRejectReason.toString());
	}
	
	@Test
	public void testUbt() 
	{
		dv = mongo.load("36E22A14-C260-E411-98E3-AC853DA49BEC");
		assertEquals("2700.955", dv.X_UbtTotallySpentSeconds_Summation);
		assertEquals("iPhone", dv.X_UbtPhoneType_Latest);
//		"X_UbtFieldDurationFirstContactMobile_Summation" : "0.0",
//		"X_UbtFieldChangedTimesName_Summation" : 2,
//		"X_UbtFieldDurationAdditionalContacts_4_Mobile_Summation" : "0.0",
//		"X_UbtFieldChangedTimesFirstContactTelAreaId_Summation" : 0,
//		"X_UbtFieldDurationAdditionalContacts_1_Mobile_Summation" : "0.0",
//		"X_UbtFieldDurationBankName_Summation" : "0.0",
//		"X_UbtPageScrollCountApplicationAdditionContactInfoPage_Summation" : 11,
//		"X_UbtPageDurationApplicationPersonalInfoPage_Summation" : "135.109",
//		"X_UbtFieldDurationMonthlyIncome_Summation" : "1.251",
//		"X_UbtFieldChangedTimesAdditionalContacts_4_Name_Summation" : 0,
//		"X_UbtFieldDurationAdditionalContacts_0_Name_Summation" : "3.002",
//		"X_UbtFieldDurationAdditionalContacts_3_Name_Summation" : "0.0",
//		"X_UbtFieldChangedTimesBankAccountTextBox_Summation" : 2,
//		"X_UbtFieldDurationAdditionalContacts_2_Name_Summation" : "0.0",
//		"X_UbtFieldChangedTimesLivingAddress_Summation" : 0,
//		"X_UbtFieldDurationThirdContactName_Summation" : "1.695",
//		"X_UbtFieldDurationMobileValidationCode_Summation" : "0.828",
//		"X_UbtPhoneOsVersion_Latest" : "6_1_2",
//		"X_UbtPageDurationDynamicPageContactInfoPage_Summation" : "208.851",
//		"X_UbtFieldChangedTimesAdditionalContacts_3_Mobile_Summation" : 0,
//		"X_UbtFieldDurationFirstContactTelAreaId_Summation" : "0.0",
//		"X_UbtFieldChangedTimesFirstContactName_Summation" : 0,
//		"X_UbtFieldChangedTimesJobLength_Summation" : 1,
//		"X_UbtPageDurationApplicationLinkExpiredPage_Summation" : "0.0",
//		"X_UbtFieldChangedTimesFirstContactPhoneCheckbox_Summation" : 0,
//		"X_UbtFieldChangedTimesBankAccountTextBoxConfirm_Summation" : 0,
//		"X_UbtFieldChangedTimesRegisterAgree_Summation" : 0,
//		"X_UbtPageScrollCountApplicationBankAccountPage_Summation" : 0,
//		"X_UbtPageScrollCountAccountRegisterPage_Summation" : 28,
//		"X_UbtFieldDurationFirstContactName_Summation" : "3.004",
//		"X_UbtFieldChangedTimesSecondContactRelationship_Summation" : 0,
//		"X_UbtAcceptLanguage_Latest" : "zh-cn",
//		"X_UbtFieldChangedTimesIsStudent_Summation" : 0,
//		"X_UbtFieldDurationAdditionalContacts_5_Name_Summation" : "0.0",
//		"X_UbtFieldDurationCaptcha_Summation" : "49.251",
//		"X_UbtPageScrollCountDynamicPageContactInfoPage_Summation" : 0,
//		"X_UbtPageScrollCountApplicationLinkExpiredPage_Summation" : 0,
//		"X_UbtInputType_Latest" : "拼音",
//		"X_UbtFieldDurationName_Summation" : "19.995",
//		"X_UbtFieldChangedTimesAdditionalContacts_3_Name_Summation" : 0,
//		"X_UbtFieldChangedTimesMonthlyIncome_Summation" : 0,
//		"X_UbtPageDurationAccountRegisterPage_Summation" : "494.343",
//		"X_UbtFieldChangedTimesThirdContactMobile_Summation" : 0,
//		"X_UbtFieldChangedTimesThirdContactRelationship_Summation" : 0,
//		"X_UbtPhoneOs_Latest" : "iPhone OS",
//		"X_UbtFieldChangedTimesCompanyPhone_Summation" : 0,
//		"X_UbtPageDurationAccountLogInPage_Summation" : "0.34",
//		"X_UbtPageDurationApplicationAdditionContactInfoPage_Summation" : "12.062",
//		"X_UbtFieldChangedTimesFirstContactPhone_Summation" : 0,
//		"X_UbtFieldDurationQQ_Summation" : "1.812",
//		"X_UbtPageScrollCountApplicationPaymentInfoPage_Summation" : 8,
//		"X_UbtFieldChangedTimesIDTextBox_Summation" : 1,
//		"X_UbtPageDurationApplicationEmergencyContactInfoPage_Summation" : "29.122",
//		"X_UbtPageDurationApplicationAgreementPage_Summation" : "14.452",
//		"X_UbtFieldChangedTimesAdditionalContacts_2_Relationship_Summation" : 0,
//		"X_UbtFieldDurationCompanyPhone_Summation" : "0.0",
//		"X_UbtFieldChangedTimesAdditionalContacts_0_Mobile_Summation" : 1,
//		"X_UbtFieldChangedTimesAdditionalContacts_1_Name_Summation" : 0,
//		"X_UbtFieldChangedTimesMobileValidationCode_Summation" : 0,
//		"X_UbtFieldChangedTimesThirdContactName_Summation" : 0,
//		"X_UbtPageScrollCountApplicationEmergencyContactInfoPage_Summation" : 21,
//		"X_UbtPageScrollCountApplicationRegisterCompletePage_Summation" : 0,
//		"X_UbtFieldChangedTimesAdditionalContacts_2_Name_Summation" : 0,
//		"X_UbtFieldChangedTimesAdditionalContacts_2_Mobile_Summation" : 0,
//		"X_UbtFieldChangedTimesLivingCondition_Summation" : 0,
//		"X_UbtFieldChangedTimesFirstContactTelBody_Summation" : 0,
//		"X_UbtFieldDurationThirdContactMobile_Summation" : "5.053",
//		"X_UbtPageDurationApplicationPaymentInfoPage_Summation" : "82.875",
//		"X_UbtPageScrollCountApplicationPlanPage_Summation" : 20,
//		"X_UbtPageScrollCountAccountLogInPage_Summation" : 2,
//		"X_UbtFieldChangedTimesAdditionalContacts_5_Relationship_Summation" : 0,
//		"X_UbtFieldChangedTimesCaptcha_Summation" : 5,
//		"X_UbtFieldChangedTimesFirstContactCheckBox_Summation" : 0,
//		"X_UbtFieldChangedTimesAdditionalContacts_4_Mobile_Summation" : 0,
//		"X_UbtFieldDurationAdditionalContacts_4_Name_Summation" : "0.0",
//		"X_UbtFieldChangedTimesProductName_Summation" : 0,
//		"X_UbtFieldDurationMobile_Summation" : "7.598",
//		"X_UbtFieldDurationAdditionalContacts_3_Mobile_Summation" : "0.0",
//		"X_UbtFieldDurationAdditionalContacts_2_Mobile_Summation" : "0.0",
//		"X_UbtFieldDurationFirstContactTelBody_Summation" : "0.0",
//		"X_UbtFieldChangedTimesAdditionalContacts_3_Relationship_Summation" : 0,
//		"X_UbtFieldChangedTimesAdditionalContacts_4_Relationship_Summation" : 0,
//		"X_UbtFieldChangedTimesMobile_Summation" : 2,
//		"X_UbtPageDurationApplicationPlanPage_Summation" : "72.381",
//		"X_UbtFieldDurationAdditionalContacts_0_Mobile_Summation" : "6.908",
//		"X_UbtFieldChangedTimesSecondContactName_Summation" : 0,
//		"X_UbtFieldChangedTimesEducation_Summation" : 1,
//		"X_UbtFieldDurationBankAccountTextBoxConfirm_Summation" : "6.835",
//		"X_UbtFieldChangedTimesBankName_Summation" : 0,
//		"X_UbtFieldChangedTimesFirstContactMobile_Summation" : 0,
//		"X_UbtFieldChangedTimesAdditionalContacts_1_Mobile_Summation" : 0,
//		"X_UbtFieldChangedTimesJobType_Summation" : 0,
//		"X_UbtFieldDurationSecondContactMobile_Summation" : "9.487",
//		"X_UbtPageDurationApplicationValidateIdentityPage_Summation" : "96.171",
//		"X_UbtFieldDurationPrincipal_Summation" : "5.222",
//		"X_UbtPageDurationApplicationBankAccountPage_Summation" : "0.0",
//		"X_UbtFieldDurationCompanyAddress_Summation" : "19.416",
//		"X_UbtFieldChangedTimesAdditionalContacts_0_Relationship_Summation" : 0,
//		"X_UbtPageScrollCountApplicationValidateIdentityPage_Summation" : 18,
//		"X_UbtFieldDurationAdditionalContacts_1_Name_Summation" : "0.0",
//		"X_UbtFieldChangedTimesAdditionalContacts_5_Mobile_Summation" : 0,
//		"X_UbtFieldChangedTimesAdditionalContacts_0_Name_Summation" : 0,
//		"X_UbtFieldChangedTimesCompanyAddress_Summation" : 1,
//		"X_UbtFieldDurationSecondContactName_Summation" : "3.201",
//		"X_UbtWeChatVersion_Latest" : "6.0",
//		"X_UbtFieldChangedTimesMarriageStatus_Summation" : 1,
//		"X_UbtFieldDurationBankAccountTextBox_Summation" : "14.423",
//		"X_UbtNetType_Latest" : "NONWIFI",
//		"X_UbtFieldChangedTimesAdditionalContacts_1_Relationship_Summation" : 0,
//		"X_UbtPageScrollCountApplicationPersonalInfoPage_Summation" : 26,
//		"X_UbtPageScrollCountApplicationJobInfoPage_Summation" : 43,
//		"X_UbtFieldChangedTimesQQ_Summation" : 0,
//		"X_UbtPageDurationApplicationRegisterCompletePage_Summation" : "0.0",
//		"X_UbtFieldChangedTimesCompanyName_Summation" : 0,
//		"X_UbtPageDurationApplicationJobInfoPage_Summation" : "403.153",
//		"X_UbtFieldChangedTimesFirstContactRelationship_Summation" : 0,
//		"X_UbtFieldChangedTimesPrincipal_Summation" : 6,
//		"X_UbtFieldDurationLivingAddress_Summation" : "16.918",
//		"X_UbtFieldChangedTimesNthJob_Summation" : 0,
//		"X_UbtFieldChangedTimesIsAgree_Summation" : 0,
//		"X_UbtPageScrollCountApplicationAgreementPage_Summation" : 2,
//		"X_UbtFieldDurationAdditionalContacts_2_Relationship_Summation" : "0.0",
//		"X_UbtFieldChangedTimesSecondContactMobile_Summation" : 2,
//		"X_UbtFieldDurationIDTextBox_Summation" : "8.224",
//		"X_UbtFieldDurationCompanyName_Summation" : "17.427",
//		"X_UbtFieldChangedTimesPayDay_Summation" : 0,
//		"X_UbtFieldChangedTimesAdditionalContacts_5_Name_Summation" : 0,
//		"X_UbtFieldDurationAdditionalContacts_5_Mobile_Summation" : "0.0",
//		"X_UbtFieldDurationFirstContactPhone_Summation" : "6.77",
	}
	
	@Test
	public void testCheckXRecord() 
	{
		dv = mongo.load("4DB0D66F-E610-E511-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckHomeCreditRecord.intValue());
		assertEquals(10, dv.X_CheckDafyRecord.intValue());
		assertEquals(10, dv.X_CheckHaodaiRecord.intValue());
		assertEquals(10, dv.X_CheckVCashRecord.intValue());
		assertEquals(10, dv.X_CheckCreditEaseRecord.intValue());
		assertEquals(10, dv.X_CheckBilFinanceRecord.intValue());
	}
	
	@Test
	public void testD1Check() 
	{
		dv = mongo.load("08125AA4-B610-E511-98E3-AC853DA49BEC");
		assertEquals("40", dv.X_D1CheckLocaleCustomerBankName.toString());
		assertEquals(10, dv.X_D1CheckCustomerIsAdornGlass.intValue());
		assertEquals("白色", dv.X_D1CheckCustomerCoatColor);
		
		dv = mongo.load("67CEB4F6-550E-E511-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_D1CheckCustomerSex.intValue());	    
	}
	
	@Test
	public void testCheckCompany() 
	{
		dv = mongo.load("A31B05B2-9AAD-E411-98E3-AC853DA49BEC");
		assertEquals(11, dv.X_CheckCompanyPhoneCallResult.intValue());
		assertEquals(10, dv.X_CheckCompanyIdentificationResult.intValue());
		assertEquals(10, dv.X_CheckCompanyPhoneRelationshipResult.intValue());
		assertEquals(10, dv.X_CheckCompanyApplicantCheckResult.intValue());
		assertEquals("30", dv.X_CheckCompanyAddressConsistency.toString());
		assertEquals(1, dv.X_CheckCompanyAnswerPersonRiskPromptResult.intValue());
		assertEquals(10, dv.X_CheckCompanyAnswerPersonAnswerResult.intValue());
		assertEquals(10, dv.X_CheckCompanySomeElseHelpResult.intValue());
	}
	
	@Test
	public void testIs() 
	{
		dv = mongo.load("34ED7F80-EB88-E411-98E3-AC853DA49BEC");
		assertEquals(false, dv.X_IsSpouseRelationshipInconsistent);
		assertEquals(false, dv.X_IsContactRelationshipInconsistent);
		assertEquals(false, dv.X_IsFirstSevenDigitsOfMobilePhonesSame);
		assertEquals(true, dv.X_IsUserMobileLocal);
		assertEquals(false, dv.X_IsIdInSupremeCourtBlacklist);
		assertEquals(false, dv.X_IsUserInfoInBlacklist);

		dv = mongo.load("022EA729-DD76-E411-98E3-AC853DA49BEC");
		assertEquals(true, dv.X_IsSurnameOfFatherAndSonSame);
		assertEquals(false, dv.X_IsIdCardNumberInBlacklist);

		dv = mongo.load("3BFBE01C-6EDC-E411-98E3-AC853DA49BEC");
		assertEquals(1, dv.X_IsUserSelf.intValue());
		assertEquals(1, dv.X_IsUserMobile.intValue());
	}

	@Test
	public void testCheckWhether() 
	{
		dv = mongo.load("2617CB20-1A8D-E511-98E3-AC853DA49BEC"); // PDL
		assertEquals(10, dv.X_CheckWhetherImageIsGroupPhotoForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherGroupPhotoIsRecognizableForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherCustomerPhotoIsTheSamePersonForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherSalesmanPhotoIsTheSamePersonForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherImageIsBankCardForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherBankCardPhotoIsRecognizableForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherBankCardInfoIsRecognizableForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherImageIsWorkPermitForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherImageIsConsistentWithTemplateWorkPermitForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherWorkPermitOfficialSealConsistentForPDL.intValue());
		assertEquals(10, dv.X_CheckWhetherWorkPermitConsistentForPDL.intValue());
		
		dv = mongo.load("4DB0D66F-E610-E511-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckWhetherImageIsIdCardPhoto.intValue());
		assertEquals(10, dv.X_CheckWhetherImageIsBankCard.intValue());
		assertEquals(10, dv.X_CheckWhetherBankCardPhotoIsRecognizable.intValue());
		assertEquals(10, dv.X_CheckWhetherBankCardInfoIsRecognizable.intValue());
	}
	
	@Test
	public void testWelcomeCall() 
	{
		dv = mongo.load("4878B724-2F57-419B-93E8-F79EBBF40873");
		assertEquals(10, dv.X_WelcomeCallPhoneStatus.intValue());
		assertEquals(10, dv.X_WelcomeCallPhoneAnswererResult.intValue());
		assertEquals(10, dv.X_WelcomeCallIdentityConfirmResult.intValue());
		assertEquals(10, dv.X_WelcomeCallMDXServiceConfirmResult.intValue());
		assertEquals(10, dv.X_WelcomeCallSceneFeedback.intValue());
		assertEquals(10, dv.X_WelcomeCallIdentityInfoResult.intValue());
		assertEquals(1, dv.X_WelcomeCallGoodsRiskReminder.intValue());
		assertEquals(10, dv.X_WelcomeCallCustomerAttitudeResult.intValue());
	}
	
	@Test
	public void testCheckUser() 
	{
		dv = mongo.load("8319D088-FA6E-E511-98E3-AC853DA49BEC"); //	PDL
		assertEquals(0, dv.X_CheckUserAdditionalContactCount.intValue());
		assertEquals(10, dv.X_CheckUserPhoneCallResultForPDL.intValue());
		assertEquals(10, dv.X_CheckUserPhoneAnswererResultForPDL.intValue());
		assertEquals(10, dv.X_CheckUserSceneIntroduceResultForPDL.intValue());
		assertEquals("70", dv.X_CheckUserIdentificationForPDL.toString());
		assertEquals(10, dv.X_CheckUserCompanyPaydayForPDL.intValue());
		assertEquals(10, dv.X_CheckUserSymbolicAnimalForPDL.intValue());
		assertEquals(10, dv.X_CheckUserConsequenceIntroduceResultForPDL.intValue());
		assertEquals(1, dv.X_CheckUserRiskRevealForPDL.intValue());
		assertEquals(1, dv.X_CheckUserSoundForPDL.intValue());
		assertEquals("30", dv.X_CheckUserCarenessLevelForPDL.toString());
		assertEquals("30", dv.X_CheckUserDislikeLevelForPDL.toString());
		
		dv = mongo.load("038AC9E1-618C-E511-98E3-AC853DA49BEC"); //	PDL
		assertEquals(10, dv.X_CheckUserIDCardAddressForPDL.intValue());
		dv = mongo.load("9C9D1EA8-1977-E511-98E3-AC853DA49BEC"); //	PDL
		assertEquals(10, dv.X_CheckUserCompanyNameForPDL.intValue());

		dv = mongo.load("C95DF50A-6266-E411-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckUserPhoneCallResult.intValue());
		assertEquals(10, dv.X_CheckUserPhoneAnswererResult.intValue());
		assertEquals(10, dv.X_CheckUserSceneIntroduceResult.intValue());
		assertEquals("40", dv.X_CheckUserIdentification.toString());
		assertEquals(10, dv.X_CheckUserCompanyFamilarity.intValue());
		assertEquals(10, dv.X_CheckUserResidencyFamilarity.intValue());
		assertEquals("30", dv.X_CheckUserDownPaymentCategory.toString());
		assertEquals(10, dv.X_CheckUserInstalmentNumberAndValue.intValue());
		assertEquals("20", dv.X_CheckUserCommodityNameAndPrice.toString());
		assertEquals(10, dv.X_CheckUserWhatIsMDX.intValue());
		assertEquals(10, dv.X_CheckUserRepayment.intValue());
		assertEquals(10, dv.X_CheckUserRepaymentDateAndValue.intValue());
		assertEquals(10, dv.X_CheckUserRepaymentHow.intValue());
		assertEquals(10, dv.X_CheckUserRepaymentOnSchedule.intValue());
		assertEquals(1, dv.X_CheckUserRiskReveal.intValue());
		assertEquals(1, dv.X_CheckUserSound.intValue());
		assertEquals(10, dv.X_CheckUserAnswerWithReminder.intValue());
		
		dv = mongo.load("4DB0D66F-E610-E511-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckUserIsCancelApplication.intValue());
		assertEquals(10, dv.X_CheckUserCompanyName.intValue());
		assertEquals(10, dv.X_CheckUserConsequenceIntroduceResult.intValue());
		assertEquals("30", dv.X_CheckUserCarenessLevel.toString());
		assertEquals("30", dv.X_CheckUserDislikeLevel.toString());
		assertEquals("1388", dv.X_CheckUserDownPayment);
		assertEquals("3388", dv.X_CheckUserProductPrice);
		
		dv = mongo.load("862450AB-CDFB-E411-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckUserIDCardAddress.intValue());
		assertEquals("20", dv.X_CheckUserJXLInforCrawlStatus.toString());
		assertEquals(0, dv.X_CheckUserCancelApplicationReason.intValue());

		dv = mongo.load("A4F2BD59-C1FB-E411-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckUserSymbolicAnimal.intValue());
	}
	
	@Test
	public void testImage() 
	{
		dv = mongo.load("4DB0D66F-E610-E511-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckImageComparision.intValue());

		dv = mongo.load("3C46E93C-7BA8-E411-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckImageIdCardValidation.intValue());
		
		assertEquals(10, dv.X_CheckImageIdCardAccordance.intValue());
		assertEquals(10, dv.X_CheckImageIdCardPhotography.intValue());
		assertEquals(10, dv.X_CheckImageIdCardInfo.intValue());
		assertEquals(10, dv.X_CheckImageIdCardPersonalPic.intValue());
		assertEquals(10, dv.X_CheckImageIdCardInfoComparision.intValue());
		assertEquals("30", dv.X_CheckImagePhotoFromPoliceExist.toString());
		
		assertEquals("23", dv.X_CheckImageIdCardPhotoComparision.toString());
		assertEquals(10, dv.X_CheckImageHeadPhotoValidation.intValue());
		assertEquals(10, dv.X_CheckImageHeadPhotoAccordance.intValue());
		assertEquals(10, dv.X_CheckImageHeadPhotoPhotographySite.intValue());
		assertEquals(10, dv.X_CheckImageHeadPhotoPhotography.intValue());
		assertEquals(10, dv.X_CheckImageHeadPhotoPersonalPic.intValue());
		assertEquals(10, dv.X_CheckImageHeadPhotoSite.intValue());
		assertEquals(10, dv.X_CheckImageHeadPhotoLongCloseShot.intValue());
		assertEquals(10, dv.X_CheckImageHeadPhotoHeadDirection.intValue());
		assertEquals("20", dv.X_CheckImageHeadPhotoFaceExpression.toString());
	}
	
	@Test
	public void testCheckBuckle() 
	{
		dv = mongo.load("8DC8A17C-83D4-4744-AE6D-30C5A1CDEFCE"); //PDL
		assertEquals(10, dv.X_CheckBuckleIsBuckleAgreementForPDL.intValue());
		assertEquals(10, dv.X_CheckBuckleTextClarifyResultForPDL.intValue());
		assertEquals(10, dv.X_CheckBuckleSignatureClarifyResultForPDL.intValue());
		assertEquals(10, dv.X_CheckBuckleDoesSignatureAndIdNameMatchForPDL.intValue());

		dv = mongo.load("66352FC2-ADCC-E411-98E3-AC853DA49BEC"); 
		assertEquals(10, dv.X_CheckBuckleIsBuckleAgreement.intValue());
		assertEquals(10, dv.X_CheckBuckleTextClarifyResult.intValue());
		assertEquals(10, dv.X_CheckBuckleSignatureClarifyResult.intValue());
		assertEquals(10, dv.X_CheckBuckleDoesSignatureAndIdNameMatch.intValue());
	}
	
	
	@Test
	public void testCheckIOU() 
	{
		dv = mongo.load("4DB0D66F-E610-E511-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckIOUIsIOU.intValue());
		assertEquals(10, dv.X_CheckIOUIsHeadPhotoClarity.intValue());
		
		assertEquals(10, dv.X_CheckIOUIsTextClarify.intValue());
		assertEquals(10, dv.X_CheckIOUImageComparision.intValue());
		assertEquals("20", dv.X_CheckIOUMobileColor.toString());
		assertEquals("30", dv.X_CheckIOURingFinger.toString());
		assertEquals("20", dv.X_CheckIOUIsSmile.toString());
	}
	
	
	@Test
	public void testCheckMerchant() 
	{
		dv = mongo.load("4DB0D66F-E610-E511-98E3-AC853DA49BEC");
		assertEquals(10, dv.X_CheckMerchantPhoneCallResult.intValue());
		assertEquals(10, dv.X_CheckMerchantAnswererResult.intValue());
		
		assertEquals(10, dv.X_CheckMerchantStoreNameConsistent.intValue());
		assertEquals(10, dv.X_CheckMerchantCustomerNameConsistent.intValue());
		assertEquals(10, dv.X_CheckMerchantCustomerOnSpot.intValue());
		assertEquals(10, dv.X_CheckMerchantPurchaseConfirmed.intValue());
		assertEquals(1, dv.X_CheckMerchantRiskHint.intValue());
		
		assertEquals("1388", dv.X_CheckMerchantDownPayment);
		assertEquals("3388", dv.X_CheckMerchantPrice);
	}
	
	
	@Test
	public void testThirdContact()
	{
		dv = mongo.load("43A43CBE-3579-E411-98E3-AC853DA49BEC"); 
		
		assertEquals("上海", dv.X_ThirdContactMobileProvince);
		assertEquals("上海", dv.X_ThirdContactMobileCity);
		assertEquals("联通", dv.X_ThirdContactMobileServiceProvider);
		
		assertEquals(0, dv.X_ThirdContactOtherUserReference.intValue());
		assertEquals(0, dv.X_ThirdContactOtherCompanyRefernce.intValue());
		assertEquals(0, dv.X_ThirdContactOtherFirstContactReference.intValue());
		assertEquals(0, dv.X_ThirdContactOtherSecondContactReference.intValue());
		assertEquals(0, dv.X_ThirdContactOtherThirdContactReference.intValue());
		assertEquals(0, dv.X_ThirdContactOtherAdditionalContactRefernce.intValue());
		
		assertEquals(10, dv.X_CheckThirdContactPhoneCallResult.intValue());
		assertEquals(10, dv.X_CheckThirdContactPhoneAnswererResult.intValue());
		assertEquals(10, dv.X_CheckThirdContactIdentificationResult.intValue());
		assertEquals(1, dv.X_CheckThirdContactSoundJudgeResult.intValue());
		assertEquals("30", dv.X_CheckThirdContactHereJudgeResult.toString());
		assertEquals(1, dv.X_CheckThirdContactRiskPromptResult.intValue());
		assertEquals(10, dv.X_CheckThirdContactSomeoneElseHelpResult.intValue());
	}
	
	
	@Test
	public void testSecondContact()
	{
		dv = mongo.load("8319D088-FA6E-E511-98E3-AC853DA49BEC"); //	PDL
		assertEquals("浙江", dv.X_SecondContactMobileProvince);
		assertEquals("宁波", dv.X_SecondContactMobileCity);
		assertEquals("联通", dv.X_SecondContactMobileServiceProvider);

		assertEquals(1, dv.X_SecondContactOtherUserReference.intValue());
		assertEquals(0, dv.X_SecondContactOtherCompanyTelReference.intValue());
		assertEquals(0, dv.X_SecondContactOtherFirstContactReference.intValue());
		assertEquals(0, dv.X_SecondContactOtherSecondContactReference.intValue());
		assertEquals(0, dv.X_SecondContactOtherThirdContactReference.intValue());
		assertEquals(0, dv.X_SecondContactOtherAdditionalContactRefernce.intValue());

		assertEquals("330200", dv.X_SecondContactMobileCityCode);
		assertEquals(10, dv.X_CheckSecondContactPhoneCallResultForPDL.intValue());
		assertEquals(10, dv.X_CheckSecondContactPhoneAnswererResultForPDL.intValue());
		assertEquals(10, dv.X_CheckSecondContactSceneIntroduceResultForPDL.intValue());
		assertEquals(10, dv.X_CheckSecondContactIdentificationResultForPDL.intValue());
		assertEquals(10, dv.X_CheckSecondContactCompanyNameResultPDL.intValue());
		assertEquals(1, dv.X_CheckSecondContactRiskPromptResultForPDL.intValue());
		assertEquals(1, dv.X_CheckSecondContactSoundForPDL.intValue());
		assertEquals("30", dv.X_CheckSecondContactCareApplicantResultForPDL.toString());
		assertEquals("30", dv.X_CheckSecondContactAttitudeOfPhoneResultForPDL.toString());

		dv = mongo.load("9C9D1EA8-1977-E511-98E3-AC853DA49BEC"); //	PDL
		assertEquals(10, dv.X_CheckSecondContactMarriageStatusResultForPDL.intValue());

		dv = mongo.load("228DF228-BE03-E511-98E3-AC853DA49BEC"); 
		assertEquals(10, dv.X_CheckSecondContactPhoneCallResult.intValue());
		assertEquals(10, dv.X_CheckSecondContactPhoneAnswererResult.intValue());
		assertEquals(10, dv.X_CheckSecondContactSceneIntroduceResult.intValue());
		assertEquals(10, dv.X_CheckSecondContactIdentificationResult.intValue());
		assertEquals(1, dv.X_CheckSecondContactSoundJudgeResult.intValue());
		assertEquals("20", dv.X_CheckSecondContactHereJudgeResult.toString());
		assertEquals(10, dv.X_CheckSecondContactCareApplicantResult.intValue());
		assertEquals("20", dv.X_CheckSecondContactAttitudeOfPhoneResult.toString());
		assertEquals(1, dv.X_CheckSecondContactRiskPromptResult.intValue());
		assertEquals(10, dv.X_CheckSecondContactZodiacResult.intValue());
//		assertEquals("40", dv.X_CheckSecondContactPermanentResidenceAddressResult.toString());
	}
	

	@Test
	public void testFirstContact()
	{
		dv = mongo.load("2CDBED42-DB76-E411-98E3-AC853DA49BEC");
		assertEquals("江苏", dv.X_FirstContactMobileProvince);
		assertEquals("泰州", dv.X_FirstContactMobileCity);
		assertEquals("联通", dv.X_FirstContactMobileServiceProvider);

		assertEquals(0, dv.X_FirstContactOtherUserReference.intValue());
		assertEquals(0, dv.X_FirstContactOtherCompanyTelReference.intValue());
		assertEquals(0, dv.X_FirstContactOtherFirstContactReference.intValue());
		assertEquals(0, dv.X_FirstContactOtherSecondContactReference.intValue());
		assertEquals(0, dv.X_FirstContactOtherThirdContactReference.intValue());
		assertEquals(0, dv.X_FirstContactOtherAdditionalContactRefernce.intValue());
		assertEquals("321200", dv.X_FirstContactMobileCityCode);

		dv = mongo.load("A31B05B2-9AAD-E411-98E3-AC853DA49BEC");

		assertEquals(10, dv.X_CheckFirstContactPhoneCallResult.intValue());
		assertEquals(10, dv.X_CheckFirstContactPhoneAnswererResult.intValue());
		assertEquals(10, dv.X_CheckFirstContactSceneIntroduceResult.intValue());
		assertEquals(10, dv.X_CheckFirstContactIdentificationResult.intValue());
		assertEquals(1, dv.X_CheckFirstContactSoundJudgeResult.intValue());
		assertEquals("30", dv.X_CheckFirstContactHereJudgeResult.toString());
		
		assertEquals("30", dv.X_CheckFirstContactCareApplicantResult.toString());
		assertEquals("30", dv.X_CheckFirstContactAttitudeOfPhoneResult.toString());
		assertEquals(1, dv.X_CheckFirstContactRiskPromptResult.intValue());
		assertEquals(10, dv.X_CheckFirstContactZodiacResult.intValue());
		assertEquals("40", dv.X_CheckFirstContactPermanentResidenceAddressResult.toString());
	}
	
	
	@Test
	public void testUserMobile()
	{
		dv = mongo.load("D1EE9652-5A96-E411-98E3-AC853DA49BEC");
		assertEquals("江苏", dv.X_UserMobileProvince);
		assertEquals("苏州", dv.X_UserMobileCity);
		assertEquals("移动", dv.X_UserMobileServiceProvider);
		assertEquals(true, dv.X_IsUserMobileLocal);
		
		assertEquals(0, dv.X_UserMobileOtherUserReference.intValue());
		assertEquals(0, dv.X_UserMobileOtherCompanyTelReference.intValue());
		assertEquals(0, dv.X_UserMobileOtherFirstContactReference.intValue());		
		assertEquals(0, dv.X_UserMobileOtherSecondContactReference.intValue());		
		
		assertEquals(0, dv.X_UserMobileOtherThirdContactReference.intValue());
		assertEquals(0, dv.X_UserMobileOtherAdditionalContactRefernce.intValue());
		assertEquals(false, dv.X_UserMobileDoesOtherUserOrParentReferenceExist);	
		
//		dv = mongo.load("D1EE9652-5A96-E411-98E3-AC853DA49BEC");
//		assertEquals(null,dv.X_UserMobileCityCode);
	}
	
	
	@Test
	public void testCompany() 
	{
		dv = mongo.load("52D323C1-A982-E411-98E3-AC853DA49BEC");
		assertEquals(0, dv.X_CompanyNameOtherUserReference.intValue());
		assertEquals("上海", dv.X_CompanyPhoneProvince);
		assertEquals("上海", dv.X_CompanyPhoneCity);
		assertEquals("电信", dv.X_CompanyPhoneServiceProvider);
		assertEquals(0, dv.X_CompanyAddrOtherUserReference.intValue());
		assertEquals(0, dv.X_CompanyTelOtherUserReference.intValue());
		assertEquals(0, dv.X_CompanyTelOtherCompanyTelReference.intValue());
		assertEquals(0, dv.X_CompanyTelOtherFirstContactReference.intValue());
		assertEquals(0, dv.X_CompanyTelOtherSecondContactReference.intValue());
		assertEquals(0, dv.X_CompanyTelOtherThirdContactReference.intValue());
		assertEquals(0, dv.X_CompanyTelOtherAdditionalContactRefernce.intValue());
		assertEquals(false, dv.X_CompanyTelDoesOtherReferenceExceptCompanyExist);

	}
	

	@Test
	public void testIdCard() 
	{
		dv = mongo.load("2E27B666-036E-E411-98E3-AC853DA49BEC");
		assertEquals(true, dv.X_IdCardIsChecksumValid);
		assertEquals("M", dv.X_IdCardGender);
		assertEquals("17", dv.X_IdCardAge.toString());
		assertEquals("江苏省", dv.X_IdCardProvince);
		assertEquals("苏州市", dv.X_IdCardCity);
		assertEquals("太仓县", dv.X_IdCardDistrict);
		assertEquals(0, dv.X_IdCardNumberRejectedTimes.intValue());
		assertEquals(0, dv.X_IdCardNumberApprovedTimes.intValue());
		assertEquals(0, dv.X_IdCardNumberRepayingCount.intValue());
		assertEquals(0, dv.X_IdCardNumberDelayedCount.intValue());
		assertEquals(false, dv.X_IsIdCardNumberInBlacklist);
		
		dv = mongo.load("2EA01059-D042-E411-98E3-AC853DA49BEC");
		assertEquals("一致", dv.X_IdCardIdentificationResult);
		assertEquals("IdentifierPhoto/陈健/320882198201091615.png", dv.X_IdCardIdentificationPhotoPath);

		dv = mongo.load("21EF2CBC-0404-E411-9BA0-AC853D9F54BA");
		assertEquals("2014-07-05T05:17:33.000Z", dv.X_IdCardNumberLastRejectedDate.toString());

	}
	

	@Test
	public void testMerchant() 
	{
		dv = mongo.load("4DB0D66F-E610-E511-98E3-AC853DA49BEC");
		assertEquals("410000", dv.X_MerchantProvinceCode);
		assertEquals("河南省", dv.X_MerchantProvince);
		assertEquals("411400", dv.X_MerchantCityCode);
		assertEquals("商丘市", dv.X_MerchantCity);
		assertEquals("411481", dv.X_MerchantDistrictCode);
		assertEquals("永城市", dv.X_MerchantDistrict);
		assertEquals(10, dv.X_MerchantApplicationHistoryTotal.intValue());
		assertEquals(7, dv.X_MerchantApplicationHistoryApproved.intValue());
		assertEquals(3, dv.X_MerchantApplicationHistoryRejected.intValue());
		assertEquals(0, dv.X_MerchantApplicationHistoryDelayed.intValue());
		assertEquals(1, dv.X_MerchantApplicationTodayTotal.intValue());
		assertEquals(1, dv.X_MerchantApplicationTodayApproved.intValue());
		assertEquals(0, dv.X_MerchantApplicationTodayRejected.intValue());
		//assertEquals("2015-05-25T08:35:36.000Z", dv.X_MerchantCooperationStartTime.toString());
		
		assertEquals(7, dv.X_MerchantCompanyApplicationHistoryApproved.intValue());
		assertEquals(3, dv.X_MerchantCompanyApplicationHistoryRejected.intValue());
		assertEquals(10, dv.X_MerchantCompanyApplicationHistoryTotal.intValue());
		assertEquals(0, dv.X_MerchantCompanyApplicationHistoryDelayed.intValue());
		assertEquals(1, dv.X_MerchantCompanyApplicationTodayApproved.intValue());
		assertEquals(0, dv.X_MerchantCompanyApplicationTodayRejected.intValue());
		assertEquals(1, dv.X_MerchantCompanyApplicationTodayTotal.intValue());
		
		assertEquals(10, dv.X_MerchantUserApplicationHistoryTotal.intValue());
		assertEquals(7, dv.X_MerchantUserApplicationHistoryApproved.intValue());
		assertEquals(3, dv.X_MerchantUserApplicationHistoryRejected.intValue());
		assertEquals(0, dv.X_MerchantUserApplicationHistoryDelayed.intValue());
		assertEquals(1, dv.X_MerchantUserApplicationTodayTotal.intValue());
		assertEquals(1, dv.X_MerchantUserApplicationTodayApproved.intValue());
		assertEquals(0, dv.X_MerchantUserApplicationTodayRejected.intValue());

		assertEquals("0.61846999304808780717479521626955829560756683349609375", dv.X_MerchantViewStatisticsPrincipals);
		assertEquals("0", dv.X_MerchantViewStatisticsProducts);
		assertEquals("0.054662894488304490658858725282698287628591060638427734375", dv.X_MerchantViewStatisticsRepayMonths);
	}
	
	
	@Test
	public void testGroupInfo() 
	{
		dv = mongo.load("EFACFFF0-C6E8-E411-98E3-AC853DA49BEC");
		assertEquals(4, dv.X_GroupInfoAppTotalCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppApprovedCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppRejectedCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppCancelledCount.intValue());
		assertEquals(1, dv.X_GroupInfoUserTotalCount.intValue());
		assertEquals("0", dv.X_GroupInfoUserApprovedRate);
		assertEquals("0", dv.X_GroupInfoRejectedRate);

		dv = mongo.load("4447F415-F78C-E511-98E3-AC853DA49BEC");
		assertEquals(0, dv.X_GroupInfoAppTotalCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppApprovedCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppRejectedCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppCancelledCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppFPD1Count.intValue());
		assertEquals(0, dv.X_GroupInfoAppFPD1TotalCount.intValue());
		assertEquals(0, dv.X_GroupInfoAppFPD30Count.intValue());
		assertEquals(0, dv.X_GroupInfoAppFPD30TotalCount.intValue());
		assertEquals(0, dv.X_GroupInfoUserTotalCount.intValue());
		assertEquals(0, dv.X_GroupInfoUserApprovedCount.intValue());
		assertEquals(0, dv.X_GroupInfoUserRejectedCount.intValue());
		assertEquals(0, dv.X_GroupInfoUserCancelledCount.intValue());
		assertEquals(0, dv.X_GroupInfoUserFPD1Count.intValue());
		assertEquals(0, dv.X_GroupInfoUserFPD1TotalCount.intValue());
		assertEquals(0, dv.X_GroupInfoUserFPD30Count.intValue());
		assertEquals(0, dv.X_GroupInfoUserFPD30TotalCount.intValue());
	}
	
	
	@Test
	public void testJXL_Report() 
	{
		dv = mongo.load("5E0F8758-F50B-E511-98E3-AC853DA49BEC");
		assertEquals("否", dv.X_JXL_ReportData_IsAlwaysPowerOff);
		assertEquals(true, dv.X_JXL_ReportData_Success);
		assertEquals("是", dv.X_JXL_ReportData_IsRealAuthenticated);
		assertEquals("是", dv.X_JXL_ReportData_IsProviderInfoMatch);
		assertEquals("是", dv.X_JXL_ReportData_IsNewNumber);
		
		assertEquals(2, dv.X_JXL_ReportData_DataExistMonth_Count.intValue());
		assertEquals(4, dv.X_JXL_ReportData_ContactRegion_Count.intValue());
		assertEquals("28", dv.X_JXL_ReportData_ContactNumber_Count.toString());

		assertEquals("6674", dv.X_JXL_ReportData_CallOut_Length.toString());
		assertEquals("83", dv.X_JXL_ReportData_CallOut_Count.toString());
		assertEquals("2293", dv.X_JXL_ReportData_CallIn_Length.toString());
		assertEquals("38", dv.X_JXL_ReportData_CallIn_Count.toString());
		assertEquals("28", dv.X_JXL_ReportData_Contact_Count.toString());

		assertEquals(false, dv.X_JXL_ReportData_CallLoanPhone);
		assertEquals(false, dv.X_JXL_ReportData_CallFinancePhone);
		assertEquals(false, dv.X_JXL_ReportData_CallJieXinPhone);
	
		dv = mongo.load("49108DF5-088D-E511-98E3-AC853DA49BEC");
		assertEquals(0, dv.X_JXL_ReportData_regContactPhoneInJXLNum.intValue());
		assertEquals("30", dv.X_JXL_GetReportStatusResult.toString());

	}
	

	@Test
	public void testYLZH() 
	{
		dv = mongo.load("4447F415-F78C-E511-98E3-AC853DA49BEC");
		assertEquals("1", dv.X_YLZH_BankCardMobileMatch);
		assertEquals(2, dv.X_YLZH_ConsumptionTotalRegionCount.intValue());
		assertEquals("3500", dv.X_YLZH_ConsumptionTotalAmount);
		assertEquals(3, dv.X_YLZH_ConsumptionTotalCount.intValue());
		assertEquals(0, dv.X_YLZH_IsMaxConsumptionAmountNative.intValue());
		assertEquals(1, dv.X_YLZH_IsMaxConsumptionCountNative.intValue());
	}
	

	@Test
	public void testTD_Rule() 
	{
		dv = mongo.load("8C3B0B86-3A0F-E511-98E3-AC853DA49BEC");
		assertEquals(true, dv.X_TD_Rule_33638);
		
		dv = mongo.load("C71B8300-3E12-E511-98E3-AC853DA49BEC");
		assertEquals(true, dv.X_TD_Rule_33640);
		
		dv = mongo.load("006AEC4D-A309-E511-98E3-AC853DA49BEC");
		assertEquals(true, dv.X_TD_Rule_33642);
		assertEquals(true, dv.X_TD_SecondContact_Rule33674Hit);
		assertEquals(1, dv.X_TD_SecondContact_Rule33674Hit_LoanAmount.intValue());
		assertEquals(2, dv.X_TD_SecondContact_Rule33674Hit_PlatformAmount.intValue());
		
		dv = mongo.load("38541D88-9512-E511-98E3-AC853DA49BEC");
		assertEquals(true, dv.X_TD_Rule_33674);
		assertEquals(true, dv.X_TD_Rule_33676);
		assertEquals(true, dv.X_TD_Rule_33652);
		
		dv = mongo.load("B4D897D3-4B16-E511-98E3-AC853DA49BEC");
		assertEquals(true, dv.X_TD_Rule_33654);
	}
	

	@Test
	public void testDerivativeVariablesList() 
	{
		List<String> appIds = new ArrayList<String>();
		appIds.add("8F7C2281-BE60-E411-98E3-AC853DA49BEC");
		appIds.add("52963B83-A480-E511-98E3-AC853DA49BEC");
		
		HashMap<String, DerivativeVariables> ldv = mongo.load(appIds);
		assertEquals("52963B83-A480-E511-98E3-AC853DA49BEC", ldv.get("52963B83-A480-E511-98E3-AC853DA49BEC")._id);
		assertEquals("8F7C2281-BE60-E411-98E3-AC853DA49BEC", ldv.get("8F7C2281-BE60-E411-98E3-AC853DA49BEC")._id);
		//assertEquals("2015-09-25T06:58:51.000Z", ldv.get("52963B83-A480-E511-98E3-AC853DA49BEC").X_MerchantCooperationStartTime.getDate());
		assertEquals("浙江省", ldv.get("52963B83-A480-E511-98E3-AC853DA49BEC").X_MerchantProvince);
	}
	

	@After 
	public void testAfter() 
	{ 

	}
	 
	@AfterClass
	public static void testAfterClass() 
	{

	}
}
