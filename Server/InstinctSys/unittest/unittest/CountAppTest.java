package unittest;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import omni.database.DatabaseClient;
import omni.database.catfish.dao.CatfishDaoImp;

public class CountAppTest 
{

	private static CatfishDaoImp dao;
	
	@BeforeClass
	public static void testBeforeClass() 
	{
		StartupConfig.initialize();
		Logger.initialize();
		
		dao = DatabaseClient.catfishDao;
	}
	 
	@Before 
	public void testBefore() 
	{
		 
	} 

	@Test
	public void testCountPOSAppIds() 
	{
		List<String> pos = new LinkedList<>(); 
		pos = dao.getPOSAppIds(2014, 3);
		assertEquals(0, pos.size());
		
		pos.clear();
		pos = dao.getPOSAppIds(2014, 4);
		assertEquals("104", new Integer(pos.size()).toString());

//		pos.clear();
//		pos = dao.getPOSAppIds(2014,5);
//		assertEquals(113,pos.size());
//		
//		pos.clear();
//		pos = dao.getPOSAppIds(2014,6);
//		assertEquals(422,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2014,7);
//		assertEquals(1104,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2014,8);
//		assertEquals(1704,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2014,9);
//		assertEquals(3441,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2014,10);
//		assertEquals(5101,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2014,11);
//		assertEquals(6332,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2014,12);
//		assertEquals(4288,pos.size());
//		
//		pos.clear();
//		pos = dao.getPOSAppIds(2015, 1);
//		assertEquals(1998,pos.size());
//		 
//		pos.clear();
//		pos = dao.getPOSAppIds(2015, 2);
//		assertEquals(854,pos.size());
//		 
//		pos.clear();
//		pos = dao.getPOSAppIds(2015, 3);
//		assertEquals(1790,pos.size());
//		
//		pos.clear();
//		pos = dao.getPOSAppIds(2015, 4);
//		assertEquals(9277,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2015, 5);
//		assertEquals(26461,pos.size());
//		
//		pos.clear();
//		pos = dao.getPOSAppIds(2015,6);
//		assertEquals(36166,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2015,7);
//		assertEquals(37957,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2015,8);
//		assertEquals(44007,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2015,9);
//		assertEquals(57752,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2015,10);
//		assertEquals(85463,pos.size());
//
//		pos.clear();
//		pos = dao.getPOSAppIds(2015,11);
//		assertEquals(95769,pos.size());
	}
	
	@Test
	public void testCountAppIds() 
	{
		List<String> appIds = new LinkedList<>(); 
		appIds = dao.getAppIds(2014, 3);
		assertEquals(0, appIds.size());

//		appIds.clear();
//		appIds = dao.getAppIds(2014,4);
//		assertEquals(104,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2014,5);
//		assertEquals(113,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2014,6);
//		assertEquals(422,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2014,7);
//		assertEquals(1104,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2014,8);
//		assertEquals(1704,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2014,9);
//		assertEquals(3441,appIds.size());
//		
		appIds.clear();
		appIds = dao.getAppIds(2014, 10);
		assertEquals("5099", new Integer(appIds.size()).toString());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2014,11);
//		assertEquals(6332,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2014,12);
//		assertEquals(4288,appIds.size());
//
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 1);
//		assertEquals(1998,appIds.size());
//		 
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 2);
//		assertEquals(854,appIds.size());
//		 
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 3);
//		assertEquals(1790,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 4);
//		assertEquals(9277,appIds.size());
//
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 5);
//		assertEquals(26520,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 6);
//		assertEquals(36183,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 7);
//		assertEquals(38069,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 8);
//		assertEquals(44026,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 9);
//		assertEquals(58766,appIds.size());
//		
//		appIds.clear();
//		appIds = dao.getAppIds(2015, 10);
//		assertEquals(89874,appIds.size());
		
		appIds.clear();
		appIds = dao.getAppIds(2015, 11);
		assertEquals("99172", new Integer(appIds.size()).toString());
		
		appIds.clear();
		appIds = dao.getAppIds(2016, 4);
		assertEquals("122840", new Integer(appIds.size()).toString());
		
		appIds.clear();
		appIds = dao.getAppIds(2016, 5);
		assertEquals("17849", new Integer(appIds.size()).toString());
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
