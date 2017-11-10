package unittest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import omni.database.DataContainer;
import omni.model.Applicant;
import omni.model.Application;
import omni.model.CBA;
import omni.model.CBB;
import omni.model.Reference;
import omni.model.UCA;
import omni.model.User;
import omni.model.User2;
import omni.model.type.ApplicationStatus;
import omni.model.type.CheckCompanyApplicantCheckResult;
import omni.model.type.CheckContactHereJudgeResult;
import omni.model.type.CheckContactIdentificationResult;
import omni.model.type.CheckContactPhoneAnswerResult;
import omni.model.type.CheckContactRiskPromptResult;
import omni.model.type.CheckContactSoundJudgeResult;
import omni.model.type.CheckContactResultViaPhone;
import omni.model.type.CheckIOUIsSmile;
import omni.model.type.CheckIOUMobileColor;
import omni.model.type.CheckIOURingFinger;
import omni.model.type.CheckImageHeadPhotoFaceExpression;
import omni.model.type.CheckMerchantCustomerOnSpot;
import omni.model.type.CheckMerchantPhoneCallResult;
import omni.model.type.CheckPhoneCallResult;
import omni.model.type.CheckUserIdentification;
import omni.model.type.CheckUserPhoneAnswererResult;
import omni.model.type.CheckUserSoundJudgeResult;
import omni.model.type.CourtExecutionResult;
import omni.model.type.EducationalBackground;
import omni.model.type.LivingCondition;
import omni.model.type.LoanMoneyFailureReasonType;
import omni.model.type.LoanMoneyResult;
import omni.model.type.MarriageStatus;
import omni.model.type.Nationality;
import omni.model.type.NoYesResult;
import omni.model.type.NthJob;
import omni.model.type.PDLCheckContactRiskPromptResult;
import omni.model.type.PDLCheckPhoneCallResult;
import omni.model.type.PDLCheckWhetherImageIsWorkPermit;
import omni.model.type.RejectedType;
import omni.model.type.RelationshipType;
import omni.model.type.RepeatedConsistencyCheckResult;
import omni.model.type.ShortcutRejectReasonType;
import omni.model.type.TransactionMonitorResult;
import omni.model.type.YLZH_BankCardMobileMatch;
import omni.model.type.YesNoResult;
import util.InstinctizeUtil;

public class OmniModelTest 
{

	private static Application oapp;
	private static Applicant oappt;
	private static CBA oia;
	private static Reference ore;
	private static CBB ova;
	private static User ousr;
	private static User2 ousr2;
	private static UCA ouca;
	private static List<String> appIds = new LinkedList<>();
	
	@BeforeClass
	public static void testBeforeClass() 
	{
		StartupConfig.initialize();
		Logger.initialize();
		
	}
	 
	@Before 
	public void testBefore() 
	{
		 
	} 
	 
	@Test
	public void testApplication() 
	{
		appIds.add("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
		appIds.add("6F07E89B-1394-4D49-90EC-00391F08CD2F");
		DataContainer.initialize(appIds);
		
		oapp = new omni.model.Application("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
        assertEquals("1BF2DC8C-23BF-4767-9E98-000266CF4F8E", oapp.getAppId());
        assertEquals("PSL", oapp.getAppType());
        assertEquals("02/08/2015", InstinctizeUtil.date(oapp.getInstallmentStartedOn()));
        assertEquals("03/08/2015", InstinctizeUtil.date(oapp.getExpireDate()));
        assertEquals("3000.0", oapp.getPrincipal().toString());
        assertEquals("原手机好好的 就要换新的！", oapp.getPurpose());
        assertEquals("MobileCredit", oapp.getProductType());
        assertEquals("苹果 iPhone 6 Plus", oapp.getProductName());
//        assertEquals(7, oapp.getUserInputTime().intValue());
        assertEquals(4, oapp.getX_TransactionMonitorJobCount().intValue());
        assertEquals(ApplicationStatus.Canceled, oapp.getStatus());
        assertEquals("F005", oapp.getRejectedReason());
        assertEquals(RejectedType.RuleEngineCheckFailed, oapp.getRejectedType());
        
        oapp = new omni.model.Application("6F07E89B-1394-4D49-90EC-00391F08CD2F", "precheck");
        assertEquals("PDL", oapp.getAppType());
        assertEquals("PaydayLoan", oapp.getProductType());
        assertEquals(true, oapp.getInstinctCall().equalsIgnoreCase("precheck"));
	}
	
	@Test
	public void testApplicant() 
	{
		appIds.add("0B3382AF-0DDC-E411-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		
		oappt = new omni.model.Applicant("0B3382AF-0DDC-E411-98E3-AC853DA49BEC");
        assertEquals("1426XXXXXXXXXX4640", oappt.getIdNumber());
        assertEquals("943XXX244", oappt.getQQNumber());
        assertEquals("oiUFft5R_smViHpMjj7hftbK_XhM", oappt.getWeiXinUserId());
        assertEquals("孟莹莹", oappt.getIdName());
        assertEquals("余生分开走  …", oappt.getNickName());
        assertEquals("F", oappt.getX_IdCardGender());
        assertEquals("山西省", oappt.getX_IdCardProvince());
        assertEquals("临汾地区", oappt.getX_IdCardCity());
        assertEquals("临汾市", oappt.getX_IdCardDistrict());
        assertEquals("昆山市玉山镇微山湖路夏驾园贵园12栋502", oappt.getLivingAddress());
        
        assertEquals("151XXXXX112", oappt.getMobile());
        assertEquals("辅讯光电", oappt.getCompanyName());
        assertEquals("昆山市玉山镇同丰东路555号", oappt.getCompanyAddress());
        assertEquals("051XXXXXX666", oappt.getCompanyPhone());
        assertEquals("移动", oappt.getX_UserMobileServiceProvider());
        assertEquals(LivingCondition.RentWithOthers, oappt.getLivingCondition());
        assertEquals(EducationalBackground.TechnicalSecondarySchool, oappt.getEducationStatus());
        assertEquals(NthJob.First, oappt.getNthJob());
        assertEquals(MarriageStatus.Married, oappt.getMarriageStatus());
        assertEquals("No", oappt.getNativePerson());
        assertEquals("制造", oappt.getDepartmentName());
        assertEquals("临汾", oappt.getX_UserMobileCity());
        assertEquals("4000.0", new Double(oappt.getMonthlyIncome()).toString());
        assertEquals(Nationality.HanZu, oappt.getNationality());
        assertEquals("6227XXXXXXXXXXX1117", oappt.getBankAccount());
	}	
	@Test
	public void testCBA() 
	{
		appIds.add("ACAED161-3DA9-E411-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		
		oia = new omni.model.CBA("ACAED161-3DA9-E411-98E3-AC853DA49BEC"); //bd2name, d1name
        assertEquals("张梦", oia.getS1Name());
        assertEquals("黄天兵", oia.getD1Name());
        assertEquals("汪道喆", oia.getBD2Name());
        assertEquals("江苏省", oia.getX_MerchantProvince());
        assertEquals("苏州市", oia.getX_MerchantCity());
        assertEquals("张家港市", oia.getX_MerchantDistrict());
        assertEquals("151XXXXX356", oia.getS1Mobile());
        assertEquals("0.78", oia.getAPR().toString());
        assertEquals("15", oia.x_MerchantApplicationHistoryApproved.toString());
        assertEquals(1, oia.x_MerchantApplicationHistoryDelayed, 0);
        assertEquals("0.06666666666666667", oia.getPOSOverduerate().toString());
        assertEquals("15", oia.x_MerchantCompanyApplicationHistoryApproved.toString());
        assertEquals(1, oia.x_MerchantCompanyApplicationHistoryDelayed, 0);
        assertEquals("0.06666666666666667", oia.getS3Overduerate().toString());
        assertEquals(11, oia.x_MerchantUserApplicationHistoryApproved, 0);
        assertEquals(1, oia.x_MerchantUserApplicationHistoryDelayed, 0);
        assertEquals("0.09090909090909091", oia.getS1Overduerate().toString());
        assertEquals("86F221B7-5150-E411-98E3-AC853DA49BEC", oia.getS1Id());
        assertEquals("25020AB8-7E31-E411-98E3-AC853DA49BEC", oia.getD1Id());
        assertEquals("161DA634-97A6-E411-98E3-AC853DA49BEC", oia.getBD2Id());
        assertEquals("张家港市金港镇后塍正大通信店", oia.getPOSName());
        
        appIds.clear();
        appIds.add("D98DA0A8-F9E0-E311-B348-E0DB5516D568");
		DataContainer.initialize(appIds);
		oia = new omni.model.CBA("D98DA0A8-F9E0-E311-B348-E0DB5516D568"); 
        assertEquals("000XXXXX000", oia.getS1Mobile());
        
	}
	
	@Test
	public void testCBAPDL() 
	{
		appIds.add("939FBD78-FD94-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		
		oia = new omni.model.CBA("939FBD78-FD94-E511-98E3-AC853DA49BEC"); 
        assertEquals("焦情", oia.getS1Name());
        assertNull(oia.getD1Name());
        assertNull(oia.getBD2Name());
        assertEquals("西藏", oia.getX_MerchantProvince());
        assertEquals("拉萨市", oia.getX_MerchantCity());
        assertEquals("市辖区", oia.getX_MerchantDistrict());
        assertEquals("158XXXXX050", oia.getS1Mobile());
        assertEquals(0, oia.getAPR(), 0);
        assertEquals("3245", oia.x_MerchantApplicationHistoryApproved.toString()); //total 5683 rejected 2319
        assertEquals("137", oia.x_MerchantApplicationHistoryDelayed.toString());
        assertEquals("0.04221879815100154", oia.getPOSOverduerate().toString());
        assertEquals("3245", oia.x_MerchantCompanyApplicationHistoryApproved.toString());
        assertEquals("137", oia.x_MerchantCompanyApplicationHistoryDelayed.toString());
        assertEquals("0.04221879815100154", oia.getS3Overduerate().toString());
        assertEquals("3213", oia.x_MerchantUserApplicationHistoryApproved.toString());
        assertEquals("135", oia.x_MerchantUserApplicationHistoryDelayed.toString());
        assertEquals("0.04201680672268908", oia.getS1Overduerate().toString());
        assertEquals("62A30145-4E5F-E511-98E3-AC853DA49BEC", oia.getS1Id());
        assertNull(oia.getD1Id());
        assertNull(oia.getBD2Id());
        assertNull(oia.getPOSName());
	}
	
	@Test
	public void testReference() 
	{
		appIds.add("52963B83-A480-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ore = new omni.model.Reference("52963B83-A480-E511-98E3-AC853DA49BEC");

        if ("帅小明".equalsIgnoreCase(ore.getReferences().get(1).getContactName()))
        {
			assertEquals("帅小明", ore.getReferences().get(1).getContactName());
	        assertEquals(RelationshipType.Father, ore.getReferences().get(1).getRelationship());
	        assertEquals("13979658608", ore.getReferences().get(1).getContactMobile());
	        assertEquals("吉安", ore.getReferences().get(1).getX_ContactMobileCity());
	        assertEquals(CheckContactIdentificationResult.IsTheRightRelation, ore.getReferences().get(1).getX_CheckContactIdentificationResult());
	        assertEquals(CheckContactPhoneAnswerResult.RightPerson, ore.getReferences().get(1).getX_CheckContactPhoneAnswererResult());
	        assertEquals(CheckPhoneCallResult.Normal, ore.getReferences().get(1).getX_CheckContactPhoneCallResult());
	        assertEquals(CheckContactRiskPromptResult.NoRiskPrompt, ore.getReferences().get(1).getX_CheckContactRiskPromptResult());
	        assertEquals(CheckContactSoundJudgeResult.NoSoundException, ore.getReferences().get(1).getX_CheckContactSoundJudgeResult());
	        assertEquals(CheckContactResultViaPhone.DonotKnow, ore.getReferences().get(1).getX_CheckContactZodiacResult());
	        assertEquals(CheckContactHereJudgeResult.CannotJudge, ore.getReferences().get(1).getX_CheckContactHereJudgeResult());
        
		    assertEquals("钟军平", ore.getReferences().get(0).getContactName());
		    assertEquals(RelationshipType.Friend, ore.getReferences().get(0).getRelationship());
		    assertEquals("18606735391", ore.getReferences().get(0).getContactMobile());
		    assertEquals("嘉兴", ore.getReferences().get(0).getX_ContactMobileCity());
		    assertNull(ore.getReferences().get(0).getX_CheckContactIdentificationResult());
		    assertNull(ore.getReferences().get(0).getX_CheckContactPhoneAnswererResult());
		    assertEquals(CheckPhoneCallResult.PoweredOffOrNotReachableOrNotAnswered, ore.getReferences().get(0).getX_CheckContactPhoneCallResult());
		    assertNull(ore.getReferences().get(0).getX_CheckContactRiskPromptResult());
		    assertNull(ore.getReferences().get(0).getX_CheckContactSoundJudgeResult());
		    assertNull(ore.getReferences().get(0).getX_CheckContactZodiacResult());
		    assertNull(ore.getReferences().get(0).getX_CheckContactHereJudgeResult());
        }
        else
        {
            assertEquals("帅小明", ore.getReferences().get(0).getContactName());
            assertEquals(RelationshipType.Father, ore.getReferences().get(0).getRelationship());
            assertEquals("139XXXXX608", ore.getReferences().get(0).getContactMobile());
            assertEquals("吉安", ore.getReferences().get(0).getX_ContactMobileCity());
            assertEquals(CheckContactIdentificationResult.IsTheRightRelation, ore.getReferences().get(0).getX_CheckContactIdentificationResult());
            assertEquals(CheckContactPhoneAnswerResult.RightPerson, ore.getReferences().get(0).getX_CheckContactPhoneAnswererResult());
            assertEquals(CheckPhoneCallResult.Normal, ore.getReferences().get(0).getX_CheckContactPhoneCallResult());
            assertEquals(CheckContactRiskPromptResult.NoRiskPrompt, ore.getReferences().get(0).getX_CheckContactRiskPromptResult());
            assertEquals(CheckContactSoundJudgeResult.NoSoundException, ore.getReferences().get(0).getX_CheckContactSoundJudgeResult());
            assertEquals(CheckContactResultViaPhone.DonotKnow, ore.getReferences().get(0).getX_CheckContactZodiacResult());
            assertEquals(CheckContactHereJudgeResult.CannotJudge, ore.getReferences().get(0).getX_CheckContactHereJudgeResult());
            
            assertEquals("钟军平", ore.getReferences().get(1).getContactName());
            assertEquals(RelationshipType.Friend, ore.getReferences().get(1).getRelationship());
            assertEquals("186XXXXX391", ore.getReferences().get(1).getContactMobile());
            assertEquals("嘉兴", ore.getReferences().get(1).getX_ContactMobileCity());
            assertNull(ore.getReferences().get(1).getX_CheckContactIdentificationResult());
            assertNull(ore.getReferences().get(1).getX_CheckContactPhoneAnswererResult());
            assertEquals(CheckPhoneCallResult.PoweredOffOrNotReachableOrNotAnswered, ore.getReferences().get(1).getX_CheckContactPhoneCallResult());
            assertNull(ore.getReferences().get(1).getX_CheckContactRiskPromptResult());
            assertNull(ore.getReferences().get(1).getX_CheckContactSoundJudgeResult());
            assertNull(ore.getReferences().get(1).getX_CheckContactZodiacResult());
            assertNull(ore.getReferences().get(1).getX_CheckContactHereJudgeResult());	
        }
	}

	@Test
	public void testReferencePDL() 
	{
		appIds.add("939FBD78-FD94-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		oapp = new omni.model.Application("939FBD78-FD94-E511-98E3-AC853DA49BEC");
        assertEquals("PDL", oapp.getAppType());
		ore = new omni.model.Reference("939FBD78-FD94-E511-98E3-AC853DA49BEC");
        
        assertEquals("刘娃", ore.getReferences().get(0).getContactName());
        assertEquals(RelationshipType.Friend, ore.getReferences().get(0).getRelationship());
        assertEquals("138XXXXX296", ore.getReferences().get(0).getContactMobile());
        assertEquals("武汉", ore.getReferences().get(0).getX_ContactMobileCity());
        assertEquals(CheckContactIdentificationResult.IsTheRightRelation, ore.getReferences().get(0).getX_CheckContactIdentificationResult());
        assertEquals(CheckContactPhoneAnswerResult.RightPerson, ore.getReferences().get(0).getX_CheckContactPhoneAnswererResult());
        assertEquals(CheckPhoneCallResult.Normal, ore.getReferences().get(0).getX_CheckContactPhoneCallResult());
        assertEquals(PDLCheckContactRiskPromptResult.NoRiskPrompt, ore.getReferences().get(0).getX_CheckContactRiskPromptResultForPDL());
        assertEquals(CheckContactSoundJudgeResult.NoSoundException, ore.getReferences().get(0).getX_CheckContactSoundJudgeResult());
        assertNull(ore.getReferences().get(0).getX_CheckContactZodiacResult());
        assertNull(ore.getReferences().get(0).getX_CheckContactHereJudgeResult());
        
        assertEquals("李定爱", ore.getReferences().get(1).getContactName());
        assertEquals(RelationshipType.Mother, ore.getReferences().get(1).getRelationship());
        assertEquals("159XXXXX942", ore.getReferences().get(1).getContactMobile());
        assertEquals("武汉", ore.getReferences().get(1).getX_ContactMobileCity());
        assertEquals(CheckContactIdentificationResult.IsTheRightRelation, ore.getReferences().get(1).getX_CheckContactIdentificationResult());
        assertEquals(CheckContactPhoneAnswerResult.RightPerson, ore.getReferences().get(1).getX_CheckContactPhoneAnswererResult());
        assertEquals(CheckPhoneCallResult.Normal, ore.getReferences().get(1).getX_CheckContactPhoneCallResult());
        assertEquals(PDLCheckContactRiskPromptResult.NoRiskPrompt, ore.getReferences().get(1).getX_CheckContactRiskPromptResultForPDL());
        assertEquals(CheckContactSoundJudgeResult.NoSoundException, ore.getReferences().get(1).getX_CheckContactSoundJudgeResult());
        assertEquals(CheckContactResultViaPhone.CorrectAnswer, ore.getReferences().get(1).getX_CheckContactZodiacResult());
        assertNull(ore.getReferences().get(1).getX_CheckContactHereJudgeResult());
        
        appIds.clear();
		appIds.add("3B1A0784-3E79-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		oapp = new omni.model.Application("3B1A0784-3E79-E511-98E3-AC853DA49BEC");
        assertEquals("PDL", oapp.getAppType());
		ore = new omni.model.Reference("3B1A0784-3E79-E511-98E3-AC853DA49BEC");
        assertEquals(PDLCheckContactRiskPromptResult.OtherRiskPrompt, ore.getReferences().get(0).getX_CheckContactRiskPromptResultForPDL());
//        assertNull(ore.getReferences().get(0).getX_CheckContactRiskPromptResultForPDL());
	}
	
	@Test
	public void testCBB() 
	{
		appIds.add("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
		DataContainer.initialize(appIds);
		ova = new omni.model.CBB("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
        assertEquals(false, ova.getX_IsUserInfoInBlacklist());
        assertEquals(true, ova.getIsCanceled());
        assertEquals(false, ova.getIsRecommended());
        assertEquals(true, ova.getIsReported());
        assertEquals(false, ova.getIsOutOfTouch());
        assertEquals(0, ova.getPersonalInfoScore(), 0);
        assertEquals(0, ova.getInvestigationScore(), 0);
        assertEquals(0, ova.getCreditReferenceScore(), 0);
        assertEquals(0, ova.getFraudCheckScore(), 0);
        assertEquals(0, ova.getApplicationScore(), 0);
        assertEquals(0, ova.getBehaviorScore(), 0);
        assertEquals(new Integer(0), ova.getX_IdCardNumberDelayedCount());
        assertEquals(new Integer(0), ova.getX_IdCardNumberRepayingCount());
        assertEquals(false, ova.getX_IsIdInSupremeCourtBlacklist());
        assertEquals(new Integer(0), ova.getX_GroupInfoAppTotalCount());
        assertEquals(new Integer(1), ova.getX_GroupInfoUserTotalCount());
        assertEquals("http://wx.qlogo.cn/mmopen/4cq89RpFeho1T4SbopUxWp6tiajv9wOibbPS691Ilq66bZ1WZHJoicico3Hf5QPXT8OXUicMwYa0f3HOVVzDjrXpM5mRWaRUOsWhm/0", ova.getHeadImageUrl());
	}
	
	@Test
	public void testCBBPDL() 
	{
		appIds.add("14187D17-0B75-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ova = new omni.model.CBB("14187D17-0B75-E511-98E3-AC853DA49BEC");
        assertEquals(LoanMoneyFailureReasonType.CausedByBank, ova.getX_LoanMoneyFailureReason());
        assertEquals(LoanMoneyResult.Failed, ova.getX_LoanMoneyResult());
	}	
	
	@Test
	public void testUser() 
	{
		appIds.add("82D9A61C-DEC0-4CB1-B9C7-000563A77A88");
		DataContainer.initialize(appIds);
		ousr = new omni.model.User("82D9A61C-DEC0-4CB1-B9C7-000563A77A88");
        assertEquals(2, ousr.getX_AllContactCount().intValue());
        assertEquals("否", ousr.getX_JXL_ReportData_IsAlwaysPowerOff());
        assertEquals("415", ousr.getX_JXL_ReportData_CallIn_Count().toString());
        assertEquals("35849", ousr.getX_JXL_ReportData_CallIn_Length().toString());
        assertEquals("491", ousr.getX_JXL_ReportData_CallOut_Count().toString());
        assertEquals("80146", ousr.getX_JXL_ReportData_CallOut_Length().toString());

        assertEquals("153", ousr.getX_JXL_ReportData_Contact_Count().toString());
        assertEquals("153", ousr.getX_JXL_ReportData_ContactNumber_Count().toString());
        assertEquals(2, ousr.getX_JXL_ReportData_regContactPhoneInJXLNum(), 0);
        assertEquals("16", ousr.getX_JXL_ReportData_ContactRegion_Count().toString());
        assertEquals(6, ousr.getX_JXL_ReportData_DataExistMonth_Count(), 0);

        assertEquals(false, ousr.getX_JXL_ReportData_CallFinancePhone());
        assertEquals(false, ousr.getX_JXL_ReportData_CallJieXinPhone());
        assertEquals(false, ousr.getX_JXL_ReportData_CallLoanPhone());
        assertEquals("否", ousr.getX_JXL_ReportData_IsNewNumber());
        assertEquals("是", ousr.getX_JXL_ReportData_IsProviderInfoMatch());
        assertEquals("是", ousr.getX_JXL_ReportData_IsRealAuthenticated());
	}
	
	@Test
	public void testUser2() 
	{
		appIds.add("52963B83-A480-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("52963B83-A480-E511-98E3-AC853DA49BEC");
        assertEquals(CheckCompanyApplicantCheckResult.ApplicantExisted, ousr2.getX_CheckCompanyApplicantCheckResult());
        assertNull(ousr2.getX_CheckCompanyPhoneRelationshipResult());
        assertNull(ousr2.getX_CheckCourtExecuted());
        assertEquals(CheckImageHeadPhotoFaceExpression.NoSmile, ousr2.getX_CheckImageHeadPhotoFaceExpression());
        assertEquals(CheckIOUIsSmile.NoSmile, ousr2.getX_CheckIOUIsSmile());
        assertEquals(CheckIOUMobileColor.BlackOrGray, ousr2.getX_CheckIOUMobileColor());
        assertEquals(CheckIOURingFinger.WithoutRing, ousr2.getX_CheckIOURingFinger());
        assertEquals(CheckMerchantCustomerOnSpot.Yes, ousr2.getX_CheckMerchantCustomerOnSpot());
        assertEquals(CheckMerchantPhoneCallResult.Normal, ousr2.getX_CheckMerchantPhoneCallResult());
        assertNull(ousr2.getX_CheckUserIDCardAddress());
        assertEquals(CheckUserIdentification.Consistent, ousr2.getX_CheckUserIdentification());
        assertEquals(NoYesResult.No, ousr2.getX_CheckUserIsCancelApplication());
        assertNull(ousr2.getX_CheckUserJXLInforCrawlStatus());
        assertEquals(CheckUserPhoneAnswererResult.User, ousr2.getX_CheckUserPhoneAnswererResult());
        assertEquals(CheckPhoneCallResult.Normal, ousr2.getX_CheckUserPhoneCallResult());
        assertEquals(CheckUserSoundJudgeResult.BackgroundSoundNormal, ousr2.getX_CheckUserSound());
        assertNull(ousr2.getX_CheckUserSymbolicAnimal());
        assertNull(ousr2.getX_CompletenessCheckResidenceAndCompanyAddressComparisonResult());
        assertNull(ousr2.getX_TransactionMonitorRejectReason());
        assertNull(ousr2.getX_TransactionMonitorResult());
        assertNull(ousr2.getx_JXL_GetReportStatusResult());
        assertNull(ousr2.getX_JXL_ReportData_Success());
        assertEquals(YLZH_BankCardMobileMatch.DataError, ousr2.getX_YLZH_BankCardMobileMatch());
        assertNull(ousr2.getX_YLZH_IsMaxConsumptionAmountNative());
        assertNull(ousr2.getX_YLZH_IsMaxConsumptionCountNative());
        assertNull(ousr2.getX_YLZH_ConsumptionTotalAmount());
        assertNull(ousr2.getX_YLZH_ConsumptionTotalCount());
        assertNull(ousr2.getX_YLZH_ConsumptionTotalRegionCount());

	}
	
	@Test
	public void testUser2PDL() 
	{
		appIds.add("2617CB20-1A8D-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("2617CB20-1A8D-E511-98E3-AC853DA49BEC");

        assertEquals(CheckImageHeadPhotoFaceExpression.NoSmile, ousr2.getX_CheckImageHeadPhotoFaceExpression());
        
        appIds.clear();
		appIds.add("6D8DA65C-4560-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("6D8DA65C-4560-E511-98E3-AC853DA49BEC");
        assertEquals(CourtExecutionResult.NoRecords, ousr2.getX_CheckCourtExecuted());
        
        appIds.clear();
		appIds.add("038AC9E1-618C-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("038AC9E1-618C-E511-98E3-AC853DA49BEC");
        assertEquals(RepeatedConsistencyCheckResult.Consistent, ousr2.getX_CheckUserIDCardAddress());
        assertEquals(CheckUserIdentification.Consistent, ousr2.getX_CheckUserIdentification());
        assertEquals(CheckUserPhoneAnswererResult.User, ousr2.getX_CheckUserPhoneAnswererResult());
        assertEquals(PDLCheckPhoneCallResult.Normal, ousr2.getX_CheckUserPhoneCallResultForPDL());
        assertEquals(CheckUserSoundJudgeResult.BackgroundSoundNormal, ousr2.getX_CheckUserSound());
        
        appIds.clear();
		appIds.add("8319D088-FA6E-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("8319D088-FA6E-E511-98E3-AC853DA49BEC");
        assertEquals(RepeatedConsistencyCheckResult.Consistent, ousr2.getX_CheckUserSymbolicAnimal());
        
        appIds.clear();
		appIds.add("8DC8A17C-83D4-4744-AE6D-30C5A1CDEFCE");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("8DC8A17C-83D4-4744-AE6D-30C5A1CDEFCE");
        assertEquals(ShortcutRejectReasonType.BadCredit, ousr2.getX_TransactionMonitorRejectReason());
        assertEquals(TransactionMonitorResult.Rejected, ousr2.getX_TransactionMonitorResult());
	}
	
	@Test
	public void testUCA() 
	{
		appIds.add("8319D088-FA6E-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		
		ouca = new omni.model.UCA("8319D088-FA6E-E511-98E3-AC853DA49BEC");
        assertEquals(RepeatedConsistencyCheckResult.Consistent, ouca.getX_CheckUserCompanyPaydayForPDL());
        assertEquals(PDLCheckWhetherImageIsWorkPermit.No, ouca.getX_CheckWhetherImageIsWorkPermitForPDL());
        assertEquals(YesNoResult.Yes, ouca.getX_CheckWhetherImageIsGroupPhotoForPDL());
        assertEquals(CheckContactResultViaPhone.CorrectAnswer, ouca.getX_CheckSecondContactCompanyNameResultPDL());
        assertEquals(CheckContactIdentificationResult.IsTheRightRelation, ouca.getX_CheckSecondContactIdentificationResultForPDL());
        
        appIds.clear();
        appIds.add("609C9697-628F-E511-98E3-AC853DA49BEC");
        DataContainer.initialize(appIds);
		ouca = new omni.model.UCA("609C9697-628F-E511-98E3-AC853DA49BEC");
		assertEquals("22", ouca.getX_HistoricalLoanAverageDays());
		assertEquals("0", ouca.getX_HistoricalGPSAwayFromApplicationPostionAverageDistance());
		assertEquals("0", ouca.getX_HistoricalGPSAwayFromApplicationPostionMaxDistance());
		assertEquals(0, ouca.getX_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount(), 0);
		assertEquals("1000", ouca.getX_HistoricalLoanAverageLimitAmount());
		assertEquals("19", ouca.getX_HistoricalLoanDayAwayFromPayDayAverageDays());
		
		appIds.clear();
		appIds.add("8319D088-FA6E-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ouca = new omni.model.UCA("8319D088-FA6E-E511-98E3-AC853DA49BEC");
		assertEquals("浙江省嘉善县西塘镇金明村费家港29号", ouca.getX_InputIdCardInfoAddressForPDL());
	}
	
	@After 
	public void testAfter() 
	{ 
		appIds.clear();
		DataContainer.clear();
	}
	 
	@AfterClass
	public static void testAfterClass() 
	{
		
	}
}
