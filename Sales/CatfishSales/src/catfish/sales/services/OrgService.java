package catfish.sales.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.dao.Dao;
import catfish.base.database.Database;
import catfish.base.database.DatabaseExtractors;
import catfish.sales.models.BaseUserModel;

public class OrgService {
	private Database db;
	private String nodeTable;
	private String orgTable;
	private String relationTable;
	private String affiliateTable;
	public OrgService(Database db, String nodeTable, String orgTable, String relationTable){
		this.db = db;
		this.nodeTable = nodeTable;
		this.orgTable = orgTable;
		this.relationTable = relationTable;
	}
	
	public OrgService(Database db, String nodeTable, String orgTable, String relationTable,String affiliateTable){
		this.db = db;
		this.nodeTable = nodeTable;
		this.orgTable = orgTable;
		this.relationTable = relationTable;
		this.affiliateTable=affiliateTable;
	}
	
	public List<String> getPOSList(String id){
		if(id ==null){
			return null;
		}
		
		List<String> nodeIds = getDescendants(id);
		if(nodeIds == null || nodeIds.size() ==0){			
			return null;
		}
		
		String sql = String.format("SELECT POSId from %s where OrgNodeId in (:OrgNodeIds) and DeletedOn is null ", relationTable);
		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().add("OrgNodeIds", nodeIds).build();
		return db.queryMultipleResults(sql, params, DatabaseExtractors.STRING_EXTRACTOR);
	}
	
	private List<String> getDescendants(String id){
		List<String> ids = getNodeIds(id);
		if(ids == null || ids.size() == 0){
			Logger.get().warn(String.format("can not get node id, affiliateId=%s, nodeTable=%s", id, nodeTable));
			return null;
		}
		
		List<String> descendants = ids.subList(0, ids.size());
		while(ids.size()>0){
			List<String> children = getChildren(ids);
			children.removeAll(descendants);
			descendants.addAll(children);
			ids =children;
		}
		return descendants;
	}
	
	private List<String> getNodeIds(String affiliateId){
		String sql = String.format("SELECT OrgNodeId from %s where AffiliateId=:AffiliateId and DeletedOn is null ", nodeTable);
		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().add("AffiliateId", affiliateId).build();
		return db.queryMultipleResults(sql, params, DatabaseExtractors.STRING_EXTRACTOR);
	}
	
	private List<String> getChildren(List<String> parentIds){
		String sql = String.format("SELECT NodeId from %s where ParentId in (:ParentIds) and DeletedOn is null ", orgTable);
		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().add("ParentIds", parentIds).build();
		return db.queryMultipleResults(sql, params, DatabaseExtractors.STRING_EXTRACTOR);
	}
	
	public HashMap<String, BaseUserModel> getPosAffiliateInfo(String posIdList){
		String sql=String.format("select distinct pdor.POSId, m.Id,m.IdName,m.IdNumber,c.Content from %s m "
				+ "left join (select A.MerchantUserId,C.contactId,B.dateadded from "
				+ "( select distinct MerchantUserId from merchantusercontactobjects ) A "
				+ "left join(select MerchantUserId,max(dateadded) as dateadded from merchantusercontactobjects group by MerchantUserId) B "
				+ "on A.MerchantUserId=B.MerchantUserId left join(select MerchantUserId,contactId,dateadded from merchantusercontactobjects ) C "
				+ "on A.MerchantUserId =C.MerchantUserId and B.dateadded=C.dateadded) muc on muc.MerchantUserId=m.Id "
				+ "left join contactobjects c on c.Id=muc.ContactId "
				+ "left join %s dour on dour.AffiliateId=m.Id "
				+ "left join %s pdor on pdor.OrgNodeId=dour.OrgNodeId  "
				+ "where  pdor.DeletedOn is not null and (c.ContactType is null or (c.ContactType is not null and c.ContactType=2)) and pdor.posid in (:posId)",affiliateTable,nodeTable,relationTable);
		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().add("posId", posIdList).build();
//		List<BaseUserWithPosIdModel> list = Dao.create(BaseUserWithPosIdModel.class, db).getMultiple(sql, params);	
		HashMap<String,BaseUserModel> map=new HashMap<String,BaseUserModel>();
//		for(BaseUserWithPosIdModel result : list){
//			map.put(result.posId, result);
//		}
		List<ArrayList<String >> results=db.queryMultipleResults(sql, params, DatabaseExtractors.STRING_ARRAY_EXTRACKTER);
		BaseUserModel model=new BaseUserModel();
		for(ArrayList<String > result : results){
			model.id=result.get(1);
			model.idName=result.get(2);
			model.idNumber=result.get(3);
			model.mobile=result.get(4);
			map.put(result.get(0), model);
		}
		return map;	
	}
}

//class BaseUserWithPosIdModel extends BaseUserModel{
//	public String posId;
//}