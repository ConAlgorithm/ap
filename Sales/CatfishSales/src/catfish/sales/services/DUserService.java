package catfish.sales.services;

import java.util.ArrayList;
import java.util.List;

import catfish.base.dao.Dao;
import catfish.base.database.Database;
import catfish.sales.models.DUserModel;
import catfish.sales.models.PosModel;
import catfish.sales.objects.DealerUserObject;
import catfish.sales.objects.MerchantStoreObject;
import catfish.sales.utils.DataTranslator;

public class DUserService extends BaseUserService {
	
	public DUserService(Database db) {
		super( DealerUserObject.class, DUserModel.class, db);
		this.db = db;
	}

	private Database db;

	
	

}