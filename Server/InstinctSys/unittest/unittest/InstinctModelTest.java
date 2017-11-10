package unittest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import core.InstinctizeModel;
import instinct.model.Applicant;
import instinct.model.Application;
import instinct.model.CBA;
import instinct.model.CBB;
import instinct.model.Reference;
import instinct.model.UCA;
import instinct.model.User;
import instinct.model.User2;
import omni.database.DataContainer;
import util.UuidUtil;

public class InstinctModelTest 
{

	private static omni.model.Application oapp;
	private static Application iapp;
	
	private static omni.model.Applicant oappt;
	private static Applicant iappt;
	
	private static omni.model.CBA oia;
	private static CBA iia;
	
	private static omni.model.Reference ore;
	private static Reference ire;
	
	private static omni.model.CBB ova;
	private static CBB iva;
	
	private static omni.model.User ousr;
	private static User iusr;
	
	private static omni.model.User2 ousr2;
	private static User2 iusr2;
	
	private static omni.model.UCA ouca;
	private static UCA iuca;
	
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
		List<String> ids = new LinkedList<>();
		ids.add("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
		ids.add("6F07E89B-1394-4D49-90EC-00391F08CD2F");
		DataContainer.initialize(ids);

		oapp = new omni.model.Application("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
		iapp = new Application(oapp);
		System.out.println(InstinctizeModel.application(iapp));
		
//		assertEquals("G/LcjCO/R2eemAACZs9Pjg",iapp.appId);
		assertEquals("G(LcjCO(R2eemAACZs9Pjg", iapp.appId);
		assertEquals(UuidUtil.uncompress("G_LcjCO_R2eemAACZs9Pjg").toUpperCase(), iapp.user_Field10);
        assertEquals("PSL", iapp.application_Type);
        assertEquals("02/08/2015", iapp.application_Date);
        assertEquals("03/08/2015", iapp.expiry_Date);
        assertEquals("3000", iapp.amount_Limit);
        assertEquals("原手机好好的 就要换新的！", iapp.user_Field1);
        assertEquals("MobileCredit", iapp.user_Field2);
        assertEquals("苹果 iPhone 6 Plus", iapp.user_Field3);
        assertEquals("4", iapp.user_Field6);
        assertEquals("Canceled", iapp.user_Field8);
        assertEquals("RuleEngineCheckFailed", iapp.user_Field11);
        assertEquals("F005", iapp.user_Field12);
        
        oapp = new omni.model.Application("6F07E89B-1394-4D49-90EC-00391F08CD2F", "precheck");
		iapp = new Application(oapp);
		assertEquals("PDL", iapp.application_Type);
        assertEquals("PaydayLoan", iapp.user_Field2);
	}

	@Test
	public void testApplicant() 
	{
		appIds.add("0B3382AF-0DDC-E411-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		
		oappt = new omni.model.Applicant("0B3382AF-0DDC-E411-98E3-AC853DA49BEC");
		iappt = new Applicant(oappt);

        assertEquals("1426XXXXXXXXXX4640", iappt.id_Number1);
        assertEquals("943XXX244", iappt.id_Number2);
        assertEquals("oiUFft5R_smViHpMjj7hftbK_XhM", iappt.id_Number3);
        assertEquals("孟莹莹", iappt.surname);
        assertEquals("余生分开走  …", iappt.first_Name);
        assertEquals("F", iappt.sex);
        assertEquals("山西省", iappt.home_Address1);
        assertEquals("临汾地区", iappt.home_Address2);
        assertEquals("临汾市", iappt.home_Address3);
        assertEquals("昆山市玉山镇微山湖路夏驾园贵园12栋502", iappt.home_Address4);
        
        assertEquals("151XXXXX112", iappt.mobile_Phone_Number);
        assertEquals("辅讯光电", iappt.company_Name);
        assertEquals("昆山市玉山镇同丰东路555号", iappt.company_Address1);
        assertEquals("051XXXXXX666", iappt.company_Phone_Number);
        assertEquals("移动", iappt.user_Field1);
        assertEquals(true, "RentWithOthers".equalsIgnoreCase(iappt.user_Field2));
        assertEquals(true, "TechnicalSecondarySchool".equalsIgnoreCase(iappt.user_Field3));
        assertEquals(true, "First".equalsIgnoreCase(iappt.user_Field4));
        assertEquals(true, "Married".equalsIgnoreCase(iappt.user_Field5));
        assertEquals("No", iappt.user_Field6);
        assertEquals("制造", iappt.user_Field8);
        assertEquals("临汾", iappt.user_Field9);
        assertEquals("4000", new BigDecimal(iappt.user_Field10).toPlainString());
        assertEquals(true, "HanZu".equalsIgnoreCase(iappt.user_Field13));
        assertEquals("6227XXXXXXXXXXX1117", iappt.user_Field14);
        System.out.println(InstinctizeModel.applicant(iappt));
	}
	
	@Test
	public void testCBA() 
	{
		appIds.add("ACAED161-3DA9-E411-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		
		oia = new omni.model.CBA("ACAED161-3DA9-E411-98E3-AC853DA49BEC"); //bd2name, d1name
        iia = new CBA(oia);

        assertEquals("张梦", iia.surname);
        assertEquals("黄天兵", iia.first_Name);
        assertEquals("汪道喆", iia.middle_Name);
        assertEquals("江苏省", iia.home_Address1);
        assertEquals("苏州市", iia.home_Address2);
        assertEquals("张家港市", iia.home_Address3);
        assertEquals("151XXXXX356", iia.mobile_Phone_Number);
        assertEquals("0.7799999713897705078125", new BigDecimal(iia.user_Field6).toPlainString());
        assertEquals("0.06666666666666666574148081281236954964697360992431640625", new BigDecimal(iia.user_Field9).toPlainString());
        assertEquals("0.06666666666666666574148081281236954964697360992431640625", new BigDecimal(iia.user_Field10).toPlainString());
        assertEquals("0.0909090909090909116141432377844466827809810638427734375", new BigDecimal(iia.user_Field11).toPlainString());
        assertEquals("86F221B7-5150-E411-98E3-AC853DA49BEC", iia.user_Field12);
        assertEquals("25020AB8-7E31-E411-98E3-AC853DA49BEC", iia.user_Field13);
        assertEquals("161DA634-97A6-E411-98E3-AC853DA49BEC", iia.user_Field14);
        assertEquals("张家港市金港镇后塍正大通信店", iia.user_Field29);
        
        System.out.println(InstinctizeModel.cba(iia));
	}
	
	@Test
	public void testReference() 
	{
		appIds.add("52963B83-A480-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ore = new omni.model.Reference("52963B83-A480-E511-98E3-AC853DA49BEC");
        ire = new Reference(ore);
        ire.subReferences.forEach(subRef -> {
        	System.out.println(InstinctizeModel.reference(subRef));	
        });

        if ("帅小明".equalsIgnoreCase(ire.subReferences.get(1).surname))
        {
	        assertEquals("帅小明", ire.subReferences.get(1).surname);
	        assertEquals(true, "Father".equalsIgnoreCase(ire.subReferences.get(1).first_Name));
	        assertEquals("13979658608", ire.subReferences.get(1).mobile_Phone_Number);
	        assertEquals(true, "IsTheRightRelation".equalsIgnoreCase(ire.subReferences.get(1).user_Field1));
	        assertEquals(true, "RightPerson".equalsIgnoreCase(ire.subReferences.get(1).user_Field2));
	        assertEquals(true, "Normal".equalsIgnoreCase(ire.subReferences.get(1).user_Field6));
	        assertEquals(true, "NoRiskPrompt".equalsIgnoreCase(ire.subReferences.get(1).user_Field7));
	        assertEquals(true, "NoSoundException".equalsIgnoreCase(ire.subReferences.get(1).user_Field8));
	        assertEquals(true, "DonotKnow".equalsIgnoreCase(ire.subReferences.get(1).user_Field9));
	        assertEquals(true, "CannotJudge".equalsIgnoreCase(ire.subReferences.get(1).user_Field10));
	        
	        assertEquals("钟军平", ire.subReferences.get(0).surname);
	        assertEquals(true, "Friend".equalsIgnoreCase(ire.subReferences.get(0).first_Name));
	        assertEquals("18606735391", ire.subReferences.get(0).mobile_Phone_Number);
	        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field1));
	        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field2));
	        assertEquals(true, "PoweredOffOrNotReachableOrNotAnswered".equalsIgnoreCase(ire.subReferences.get(0).user_Field6));
	        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field7));
	        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field8));
	        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field9));
	        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field10));
        }
        else
        {
          assertEquals("帅小明", ire.subReferences.get(0).surname);
          assertEquals(true, "Father".equalsIgnoreCase(ire.subReferences.get(0).first_Name));
          assertEquals("139XXXXX608", ire.subReferences.get(0).mobile_Phone_Number);
          assertEquals(true, "IsTheRightRelation".equalsIgnoreCase(ire.subReferences.get(0).user_Field1));
          assertEquals(true, "RightPerson".equalsIgnoreCase(ire.subReferences.get(0).user_Field2));
          assertEquals(true, "Normal".equalsIgnoreCase(ire.subReferences.get(0).user_Field6));
          assertEquals(true, "NoRiskPrompt".equalsIgnoreCase(ire.subReferences.get(0).user_Field7));
          assertEquals(true, "NoSoundException".equalsIgnoreCase(ire.subReferences.get(0).user_Field8));
          assertEquals(true, "DonotKnow".equalsIgnoreCase(ire.subReferences.get(0).user_Field9));
          assertEquals(true, "CannotJudge".equalsIgnoreCase(ire.subReferences.get(0).user_Field10));
          
          assertEquals("钟军平", ire.subReferences.get(1).surname);
          assertEquals(true, "Friend".equalsIgnoreCase(ire.subReferences.get(1).first_Name));
          assertEquals("186XXXXX391", ire.subReferences.get(1).mobile_Phone_Number);
          assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(1).user_Field1));
          assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(1).user_Field2));
          assertEquals(true, "PoweredOffOrNotReachableOrNotAnswered".equalsIgnoreCase(ire.subReferences.get(1).user_Field6));
          assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(1).user_Field7));
          assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(1).user_Field8));
          assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(1).user_Field9));
          assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(1).user_Field10));
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
	    ire = new Reference(ore);
	    ire.subReferences.forEach(subRef -> {
        	System.out.println(InstinctizeModel.reference(subRef));	
        });
        assertEquals("刘娃", ire.subReferences.get(0).surname);
        assertEquals(true, "Friend".equalsIgnoreCase(ire.subReferences.get(0).first_Name));
        assertEquals("138XXXXX296", ire.subReferences.get(0).mobile_Phone_Number);
        assertEquals("武汉", ire.subReferences.get(0).home_Address1);
        assertEquals(true, "IsTheRightRelation".equalsIgnoreCase(ire.subReferences.get(0).user_Field1));
        assertEquals(true, "RightPerson".equalsIgnoreCase(ire.subReferences.get(0).user_Field2));
        assertEquals(true, "Normal".equalsIgnoreCase(ire.subReferences.get(0).user_Field6));
        assertEquals(true, "NoRiskPrompt".equalsIgnoreCase(ire.subReferences.get(0).user_Field7));
        assertEquals(true, "NoSoundException".equalsIgnoreCase(ire.subReferences.get(0).user_Field8));
        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field9));
        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(0).user_Field10));
        
        assertEquals("李定爱", ire.subReferences.get(1).surname);
        assertEquals(true, "Mother".equalsIgnoreCase(ire.subReferences.get(1).first_Name));
        assertEquals("159XXXXX942", ire.subReferences.get(1).mobile_Phone_Number);
        assertEquals("武汉", ire.subReferences.get(0).home_Address1);
        assertEquals(true, "IsTheRightRelation".equalsIgnoreCase(ire.subReferences.get(1).user_Field1));
        assertEquals(true, "RightPerson".equalsIgnoreCase(ire.subReferences.get(1).user_Field2));
        assertEquals(true, "Normal".equalsIgnoreCase(ire.subReferences.get(1).user_Field6));
        assertEquals(true, "NoRiskPrompt".equalsIgnoreCase(ire.subReferences.get(1).user_Field7));
        assertEquals(true, "NoSoundException".equalsIgnoreCase(ire.subReferences.get(1).user_Field8));
        assertEquals(true, "CorrectAnswer".equalsIgnoreCase(ire.subReferences.get(1).user_Field9));
        assertEquals(true, "".equalsIgnoreCase(ire.subReferences.get(1).user_Field10));
        
        appIds.clear();
		appIds.add("3B1A0784-3E79-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		oapp = new omni.model.Application("3B1A0784-3E79-E511-98E3-AC853DA49BEC");
        assertEquals("PDL", oapp.getAppType());
	    ore = new omni.model.Reference("3B1A0784-3E79-E511-98E3-AC853DA49BEC");
	    ire = new Reference(ore);
        assertEquals("OtherRiskPrompt", ire.subReferences.get(0).user_Field7);
//        assertEquals("", ire.subReferences.get(0).user_Field7);
	}
	
	@Test
	public void testCBB() 
	{
		appIds.add("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
		DataContainer.initialize(appIds);
		ova = new omni.model.CBB("1BF2DC8C-23BF-4767-9E98-000266CF4F8E");
        iva = new CBB(ova);
        assertEquals("false", iva.id_Number1);
        assertEquals("true", iva.id_Number2);
        assertEquals("false", iva.id_Number3);
        assertEquals("true", iva.surname);
        assertEquals("false", iva.first_Name);
        assertEquals("0", iva.user_Field2);
        assertEquals("0", iva.user_Field4);
        assertEquals("0", iva.user_Field5);
        assertEquals("0", iva.user_Field6);
        assertEquals("0", iva.user_Field7);
        assertEquals("0", iva.user_Field8);
        assertEquals("0", iva.user_Field9);
        assertEquals("0", iva.user_Field2);
        assertEquals("false", iva.user_Field12);
        assertEquals("0", iva.user_Field17);
        assertEquals("1", iva.user_Field18);
        assertEquals("http://wx.qlogo.cn/mmopen/4cq89RpFeho1T4SbopUxWp6tiajv9wOibbPS691Ilq66bZ1WZHJoicico3Hf5QPXT8OXUicMwYa0f3HOVVzDjrXpM5mRWaRUOsWhm/0", iva.user_Field29);
        
        System.out.println(InstinctizeModel.cbb(iva));
	}
	
	
	@Test
	public void testUser() 
	{
		appIds.add("82D9A61C-DEC0-4CB1-B9C7-000563A77A88");
		DataContainer.initialize(appIds);
		ousr = new omni.model.User("82D9A61C-DEC0-4CB1-B9C7-000563A77A88");
        iusr = new User(ousr);
        assertEquals("2", iusr.id_Number1);
        assertEquals("否", iusr.id_Number3);
        assertEquals("415", iusr.surname);
        assertEquals("35849", iusr.first_Name);
        assertEquals("491", iusr.middle_Name);
        assertEquals("80146", iusr.home_Address1);

        assertEquals("153", iusr.home_Address2);
        assertEquals("153", iusr.home_Address3);
        assertEquals("2", iusr.home_Address4);
        assertEquals("16", iusr.home_Address5);
        assertEquals("6", iusr.home_Address6);

        assertEquals("false", iusr.company_Name);
        assertEquals("false", iusr.company_Address1);
        assertEquals("false", iusr.company_Address2);
        assertEquals("否", iusr.company_Address3);
        assertEquals("是", iusr.company_Address4);
        assertEquals("是", iusr.company_Address5);
        InstinctizeModel.user(iusr);
	}
	
	@Test
	public void testUser2() 
	{
		appIds.add("52963B83-A480-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("52963B83-A480-E511-98E3-AC853DA49BEC");
        iusr2 = new User2(ousr2);
        assertEquals("ApplicantExisted", iusr2.user_Field1);
        assertEquals("", iusr2.user_Field2);
        assertEquals("", iusr2.user_Field3);
        assertEquals("NoSmile", iusr2.user_Field4);
        assertEquals("NoSmile", iusr2.user_Field5);
        assertEquals("BlackOrGray", iusr2.user_Field6);
        assertEquals("WithoutRing", iusr2.user_Field7);
        assertEquals("Yes", iusr2.user_Field8);
        assertEquals("Normal", iusr2.user_Field9);
        assertEquals("", iusr2.user_Field10);
        assertEquals("Consistent", iusr2.user_Field11);
        assertEquals("No", iusr2.user_Field12);
        assertEquals("", iusr2.user_Field13);
        assertEquals("User", iusr2.user_Field14);
        assertEquals("Normal", iusr2.user_Field15);
        assertEquals("BackgroundSoundNormal", iusr2.user_Field16);
        assertEquals("", iusr2.user_Field17);
        assertEquals("", iusr2.user_Field18);
        assertEquals("", iusr2.user_Field19);
        assertEquals("", iusr2.user_Field20);
        assertEquals("", iusr2.user_Field21);
        assertEquals("", iusr2.user_Field22);
        assertEquals("DataError", iusr2.user_Field23);
        assertEquals("", iusr2.user_Field24);
        assertEquals("", iusr2.user_Field25);
        assertEquals("", iusr2.user_Field26);
        assertEquals("", iusr2.user_Field27);
        assertEquals("", iusr2.user_Field28);
        
        System.out.println(InstinctizeModel.user2(iusr2));
	}
	
	@Test
	public void testUser2PDL() 
	{
		appIds.add("038AC9E1-618C-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ousr2 = new omni.model.User2("038AC9E1-618C-E511-98E3-AC853DA49BEC");
        iusr2 = new User2(ousr2);
        assertEquals("Normal", iusr2.user_Field15);
	}
	
	@Test
	public void testUCA() 
	{
		appIds.add("8319D088-FA6E-E511-98E3-AC853DA49BEC");
		DataContainer.initialize(appIds);
		ouca = new omni.model.UCA("8319D088-FA6E-E511-98E3-AC853DA49BEC");
        iuca = new UCA(ouca);
        assertEquals("Consistent", iuca.id_Number1);
        assertEquals("No", iuca.id_Number2);
        assertEquals("Yes", iuca.surname);
        assertEquals("CorrectAnswer", iuca.user_Field2);
        assertEquals("IsTheRightRelation", iuca.user_Field4);
        System.out.println(InstinctizeModel.uca(iuca));
	}
	
	@Test
	public void testAllInOne() 
	{
//		Instinctize.allInOne("C21348CD-2317-E411-9BA0-AC853D9F54BA");	// isoutoftouch
		System.out.println(InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC"));	//	x_LoanMoneyFailureReason
		
		InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC", "Update");	
		InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC", "Finalcheck");	
		InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC", "precheck");	
		InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC", "bad");	
	}
	
	@Test
	public void testPreFinalDiff() 
	{
		String allInOnePre = InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC", "precheck");
		String allInOneDefault = InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC");
		String allInOneFinal = InstinctizeModel.allInOne("0E0A7938-82F5-E411-98E3-AC853DA49BEC");
		assertEquals(allInOneDefault, allInOneFinal);
		assertNotEquals(allInOnePre, allInOneFinal);
	}
	
	@Test
	public void testError() 
	{
		List<String> ids = new LinkedList<>();
		ids.add("D183E292-D7A5-E411-98E3-AC853DA49BEC");
		ids.add("47543392-A59F-E411-98E3-AC853DA49BEC");
		ids.add("B8906AC8-4FA9-E411-98E3-AC853DA49BEC");
		DataContainer.initialize(ids);
		
		oia = new omni.model.CBA("D183E292-D7A5-E411-98E3-AC853DA49BEC"); 
        assertEquals("152XXXXX503", oia.getS1Mobile());
        assertNull(oia.getS1QQ());
        
		InstinctizeModel.allInOne("47543392-A59F-E411-98E3-AC853DA49BEC");	//s1Userid
		InstinctizeModel.allInOne("B8906AC8-4FA9-E411-98E3-AC853DA49BEC");	//d1name bd2name
	}
	
	@Test
	public void testDebug() 
	{
//		System.out.println(InstinctizeModel.allInOne("939FBD78-FD94-E511-98E3-AC853DA49BEC"));

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
