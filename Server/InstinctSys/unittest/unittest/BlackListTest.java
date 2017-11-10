package unittest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
import omni.database.DatabaseClient;
import omni.database.catfish.dao.CatfishDaoImp;
import omni.database.catfish.object.BlackListObject;

public class BlackListTest 
{
	private static CatfishDaoImp dao;
	private static Applicant appt;
	private static Application app;

	
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
	public void testBlackList() 
	{
		ArrayList<BlackListObject> blackListObj = dao.getBlackListObject(1);
		blackListObj.forEach(black-> {
			appt = new Applicant(new omni.model.ApplicantBlackList(black));
			app = new Application();
			InstinctizeModel.singleBlackList(app, appt);
		});
		assertEquals("PSL", app.application_Type);
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
