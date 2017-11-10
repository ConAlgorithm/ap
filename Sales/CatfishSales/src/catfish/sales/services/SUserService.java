package catfish.sales.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.object.MerchantUserObject;
import catfish.base.dao.Dao;
import catfish.base.database.Database;
import catfish.base.database.DatabaseExtractors;
import catfish.sales.models.BaseUserModel;
import catfish.sales.models.SUserModel;

public class SUserService extends BaseUserService {

	private Database db;

	public SUserService(Database db) {
		super(MerchantUserObject.class, SUserModel.class, db);
		this.db = db;
	}
	
	public List<String> getPOSList(String affiliateId){
		OrgService tree = new OrgService(db, "BDOrgBDUserRelationObjects", "BDOrgRelationObjects",  "POSBDOrgRelationObjects");
		List<String> allNodes = tree.getPOSList(affiliateId);
		return allNodes;
	}

	public HashMap<String, BaseUserModel> getAffiliateInfo(String s1IdList){
		String sql="select distinct m.Id,m.IdName,m.IdNumber,c.Content from merchantuserobjects m "
				+ "left join (select A.MerchantUserId,C.contactId,B.dateadded from "
				+ "( select distinct MerchantUserId from merchantusercontactobjects ) A "
				+ "left join(select MerchantUserId,max(dateadded) as dateadded from merchantusercontactobjects group by MerchantUserId) B "
				+ "on A.MerchantUserId=B.MerchantUserId left join(select MerchantUserId,contactId,dateadded from merchantusercontactobjects ) C "
				+ "on A.MerchantUserId =C.MerchantUserId and B.dateadded=C.dateadded) muc on muc.MerchantUserId=m.Id "
				+ "left join contactobjects c on c.Id=muc.ContactId "
				+ "where  m.id in (:s1Id) and (c.ContactType is null or (c.ContactType is not null and c.ContactType=2))";
		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().add("s1Id", s1IdList).build();
		HashMap<String,BaseUserModel> map=new HashMap<String,BaseUserModel>();
		List<ArrayList<String >> results=db.queryMultipleResults(sql, params, DatabaseExtractors.STRING_ARRAY_EXTRACKTER);
		BaseUserModel model=new BaseUserModel();
		for(ArrayList<String > result : results){
			model.id=result.get(0);
			model.idName=result.get(1);
			model.idNumber=result.get(2);
			model.mobile=result.get(3);
			map.put(result.get(0), model);
		}
		return map;	
	}
}



