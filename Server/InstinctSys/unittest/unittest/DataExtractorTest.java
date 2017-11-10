package unittest;

//import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import core.DataExtractor;

public class DataExtractorTest 
{
	
	private static DataExtractor de; 
	
	@BeforeClass
	public static void testBeforeClass() 
	{
		StartupConfig.initialize();
		Logger.initialize();
		
		de = new DataExtractor();
	}
	 
	@Before 
	public void testBefore() 
	{
		 
	} 
	 
	@Test
	public void testExtractAppId() 
	{
		de.extractAppIdToFile(2014, 4);
//		assertTrue(de.clearHistoryFiles(2014, 4));
	}
	
	@Test
	public void testExtractBlackList() 
	{
		de.extractBlackListToFile(2);
//		assertTrue(de.clearBlacklistFiles());
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
