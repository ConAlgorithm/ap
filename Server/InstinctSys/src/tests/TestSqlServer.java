package tests;

import java.lang.reflect.Field;
import catfish.base.StartupConfig;
import catfish.base.business.object.InstallmentApplicationObject;
import omni.database.DatabaseClient;
import omni.database.catfish.dao.CatfishDaoImp;

public class TestSqlServer 
{
	
	private CatfishDaoImp dao;

	public TestSqlServer()
	{
		StartupConfig.initialize();
		dao = DatabaseClient.catfishDao;
//		catfish = DataSourceFactory.sqlserver(StartupConfig.get("catfish.database.URL"), 
//				StartupConfig.getAsInt("catfish.database.min.connections"), 
//				StartupConfig.getAsInt("catfish.database.max.connections"));

	}
	
	public final InstallmentApplicationObject getApplicationById(String appId) 
	{
		
		InstallmentApplicationObject app = dao.getInstallmentApplicationObjectById(appId);
		
		return app;
	  }
	
//	public Application getApplicationById(String appId) {
//	    
//		String getAppByAppIdSql = "SELECT * FROM Application WHERE AppId = :appId";
//
//		StartupConfig.initialize();
//		DataSource dataSource = DataSourceFactory.sqlserver(StartupConfig.get("dw.database.URL"), 
//				StartupConfig.getAsInt("dw.database.min.connections"), 
//				StartupConfig.getAsInt("dw.database.max.connections"));		
//		Database database = new Database(dataSource);
//		Dao<Application> dao = Dao.create(Application.class, database);	 
//
//		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
//				  .add("appId",appId)
//				  .build();
//		Application app = dao.getSingle(getAppByAppIdSql, params);
////		luom.forEach(uom->uom.printSelf());
////		return dao.getMultiple(getAppByAppIdSql, params);
//		
////		Application app = dao.getApplicationById(appId);
//		
//		return app;
//	  }
	
	public static void main(String [] args) throws IllegalAccessException 
	{
		InstallmentApplicationObject app = new TestSqlServer().getApplicationById("BA2E3055-0004-E411-9BA0-AC853D9F54BA");
        Field[] field = app.getClass().getDeclaredFields();         

        for (int j = 0; j < field.length; j++)
        {     
            String name = field[j].getName();    
            
            System.out.println(name + " - " + field[j].get(app));
            
        }
	}
}
