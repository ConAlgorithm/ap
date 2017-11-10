package unittest;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import util.UuidUtil;

public class UuidUtilTest 
{

	@BeforeClass
	public static void testBeforeClass() 
	{
		Logger.initialize();
		StartupConfig.initialize();
	}
	 
	@Before 
	public void testBefore() 
	{
		 
	} 
	
	@Test
	public void testUuid() 
	{
    	assertEquals("b11zKO1P5RGY46yFPaSb7A", UuidUtil.compress("6F5D7328-ED4F-E511-98E3-AC853DA49BEC"));
    	assertEquals("bPk(vaWC5RGs2KyFPZ9VCA", UuidUtil.compress("6CF93FBD-A582-E511-ACD8-AC853D9F5508".toLowerCase()));
//    	assertEquals("bPk/vaWC5RGs2KyFPZ9VCA", UuidUtil.compress("6CF93FBD-A582-E511-ACD8-AC853D9F5508".toLowerCase()));
    	assertEquals("6CF93FBD-A582-E511-ACD8-AC853D9F5508", UuidUtil.uncompress("bPk(vaWC5RGs2KyFPZ9VCA").toUpperCase());
//    	assertEquals("6CF93FBD-A582-E511-ACD8-AC853D9F5508", UuidUtil.uncompress("bPk/vaWC5RGs2KyFPZ9VCA").toUpperCase());
    	assertEquals("Q9Is-hCbRY-GlaKGr2b3Vg", UuidUtil.compress("43D22CFA-109B-458F-8695-A286AF66F756"));
    	assertEquals("43D22CFA-109B-458F-8695-A286AF66F756", UuidUtil.uncompress("Q9Is-hCbRY-GlaKGr2b3Vg").toUpperCase());
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
