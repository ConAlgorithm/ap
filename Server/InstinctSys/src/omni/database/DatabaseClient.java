package omni.database;

import omni.database.adapter.settlement.SettlementService;
import omni.database.adapter.settlement.SettlementServiceImpl;
import omni.database.catfish.dao.CatfishDaoImp;
import omni.database.mongo.OmniProdMongoClient;

/**
 * This class initializes Mongo and Catfish client.
 * 
 * @author guoqing
 *
 */
public final class DatabaseClient 
{
	private DatabaseClient()
	{
		
	}
	
	public static OmniProdMongoClient mongo = new OmniProdMongoClient();
	
	public static CatfishDaoImp catfishDao = new CatfishDaoImp();
	
	public static SettlementService settlementService = new SettlementServiceImpl();

}
