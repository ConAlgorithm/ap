package tests;

//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

import com.mongodb.BasicDBObject;

import catfish.base.StartupConfig;
import omni.database.DatabaseClient;
import omni.database.mongo.DerivativeVariables;
import omni.database.mongo.OmniProdMongoClient;

public final class TestMongo 
{
	private TestMongo()
	{
		
	}
	
	public static void main(String[] args)
	{
		StartupConfig.initialize();
		OmniProdMongoClient mongo = DatabaseClient.mongo;
//		List<String> appIds = new ArrayList<String>();
//		appIds.add("82D9A61C-DEC0-4CB1-B9C7-000563A77A88");
//		appIds.add("3E046E20-AD5F-43B1-BA95-181787366318");
//		appIds.add("2635CECA-71EE-E411-98E3-AC853DA49BEC");
//		appIds.add("8FDCD9E0-2F46-E511-98E3-AC853DA49BEC");
//		appIds.add("8FDCD9E0-2F46-E511-98E3-AC853DA49BEC");
//		
		
		DerivativeVariables dv = mongo.queryOne(new BasicDBObject("X_CheckFirstContactIdentificationResultForPDL", 
		new BasicDBObject("$exists", true)));
		System.out.println(dv._id);
		
//		Field[] fields = DerivativeVariables.class.getDeclaredFields();
//		DerivativeVariables dv;
//		Map<String, String> dvMap = new HashMap<String, String>();
//		
//		for(int i=0; i<fields.length;i++)
//		{
//			String field = fields[i].getName();
//			dv = mongo.queryOne(new BasicDBObject(field, new BasicDBObject("$exists", true)));
//			if(dv!=null) 
//			{
//				dvMap.put(field, dv._id);
//				System.out.println(field+":"+dv._id);
//			}
//			else
//			{
//				dvMap.put(field, "N/A");
//				System.out.println(field+" does not exist in Mongo DB!");
//			}
//		}

		//		System.out.println(dv.X_MerchantCooperationStartTime.getDate());
	}
}
