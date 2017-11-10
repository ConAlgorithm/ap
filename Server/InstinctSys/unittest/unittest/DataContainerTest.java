package unittest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import omni.database.DataContainer;
import omni.database.DatabaseClient;
import omni.database.mongo.DerivativeVariables;
import omni.database.mongo.OmniProdMongoClient;

public class DataContainerTest 
{

	private static OmniProdMongoClient mongo;
	
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
	public void testAppObjectList() 
	{
		List<String> appIds = new ArrayList<String>();
		appIds.add("8F7C2281-BE60-E411-98E3-AC853DA49BEC");
		appIds.add("52963B83-A480-E511-98E3-AC853DA49BEC");
		
		DataContainer.setInstallmentApplicationObject(appIds);
		assertEquals("52963B83-A480-E511-98E3-AC853DA49BEC", DataContainer.appObj.get("52963B83-A480-E511-98E3-AC853DA49BEC").Id);
		assertEquals("0.78", new Double(DataContainer.appObj.get("52963B83-A480-E511-98E3-AC853DA49BEC").APR).toString());
	}
	
	@Test
	public void testPurposeObjectList() 
	{
		List<String> appIds = new ArrayList<String>();
		appIds.add("8F7C2281-BE60-E411-98E3-AC853DA49BEC");
		appIds.add("52963B83-A480-E511-98E3-AC853DA49BEC");
		
		DataContainer.setAppPurposeObject(appIds);

		assertEquals("52963B83-A480-E511-98E3-AC853DA49BEC", DataContainer.purposeObj.get("52963B83-A480-E511-98E3-AC853DA49BEC").appId);
		assertEquals("原手机太慢了 换新的啦！", DataContainer.purposeObj.get("52963B83-A480-E511-98E3-AC853DA49BEC").message);
		assertEquals(true, DataContainer.purposeObj.get("52963B83-A480-E511-98E3-AC853DA49BEC").isActive);
		assertEquals("羞涩", DataContainer.purposeObj.get("52963B83-A480-E511-98E3-AC853DA49BEC").title);
		assertNull(DataContainer.purposeObj.get("8F7C2281-BE60-E411-98E3-AC853DA49BEC"));
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
		DataContainer.clear();
	}
	 
	@AfterClass
	public static void testAfterClass() 
	{

	}
}
