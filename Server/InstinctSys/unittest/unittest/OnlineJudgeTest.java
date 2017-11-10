package unittest;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import core.InstinctOnlineJudge;
import instinct.service.exception.MalFormedApplicationException;
import instinct.service.model.InstinctResult;
import omni.database.DataContainer;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class OnlineJudgeTest 
{

	private InstinctResult instinctRes = new InstinctResult(); 
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
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
	public void testPreCheck() throws Exception 
	{
		instinctRes = InstinctOnlineJudge.judge("52963B83-A480-E511-98E3-AC853DA49BEC", "precheck");
		assertEquals("", instinctRes.errorCode);
		assertEquals("52963B83-A480-E511-98E3-AC853DA49BEC", instinctRes.appId);
	}
	
	@Test
	public void testPreCheckLowerCase() throws Exception 
	{
		instinctRes = InstinctOnlineJudge.judge("52963B83-A480-E511-98E3-AC853DA49BEC".toLowerCase(), "precheck");
		assertEquals("", instinctRes.errorCode);
		assertEquals("52963B83-A480-E511-98E3-AC853DA49BEC", instinctRes.appId);
	}
	
	@Test
	public void testMalFormedApplicationException() throws Exception 
	{
	    thrown.expect(MalFormedApplicationException.class);
	    thrown.expectMessage("Some columns are not correctly settled!");
		instinctRes = InstinctOnlineJudge.judge("52963B83-A480-E511-98E3-BC853DA49BEC", "precheck");
	}
	
	@Test
	public void testMalFormedApplicationException2() throws Exception 
	{
	    thrown.expect(MalFormedApplicationException.class);
	    thrown.expectMessage("What the hell is this exception !!?");
		instinctRes = InstinctOnlineJudge.judge("BC853DA49BEC", "precheck");
	}

	@Test
	public void testFinalCheck() throws Exception 
	{
		instinctRes = InstinctOnlineJudge.judge("52963B83-A480-E511-98E3-AC853DA49BEC", "finalcheck");
		assertEquals("", instinctRes.errorCode);
		assertEquals("52963B83-A480-E511-98E3-AC853DA49BEC", instinctRes.appId);
	}

	@Test
	public void testUpdate() throws Exception 
	{
		instinctRes = InstinctOnlineJudge.update("52963B83-A480-E511-98E3-AC853DA49BEC");
		assertEquals("", instinctRes.errorCode);
		assertEquals("52963B83-A480-E511-98E3-AC853DA49BEC", instinctRes.appId);
	}

	@Test
	public void testIllegalArgumentException() throws Exception 
	{
	    thrown.expect(IllegalArgumentException.class);
	    thrown.expectMessage("Invalid action: update! Please use InstinctOnlineJudge.update(String appId) method instead!");
		instinctRes = InstinctOnlineJudge.judge("52963B83-A480-E511-98E3-AC853DA49BEC", "update");
	}

	@After 
	public void testAfter() 
	{ 

	}
	 
	@AfterClass
	public static void testAfterClass() 
	{
		DataContainer.clear();
	}
}
