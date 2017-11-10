package catfish.sales.services;

import java.util.List;

import catfish.base.database.Database;
import catfish.sales.models.BDUserModel;
import catfish.sales.objects.BusinessDevelopUserObject;

public class BDUserService extends BaseUserService {
	public BDUserService(Database db) {
		super( BusinessDevelopUserObject.class, BDUserModel.class, db);
		this.db = db;
	}

	private Database db;
	
	public List<String> getPOSList(String dealerId){
		OrgService tree = new OrgService(db, "BDOrgBDUserRelationObjects", "BDOrgRelationObjects",  "POSBDOrgRelationObjects");
		List<String> allNodes = tree.getPOSList(dealerId);
		return allNodes;
	}
}
