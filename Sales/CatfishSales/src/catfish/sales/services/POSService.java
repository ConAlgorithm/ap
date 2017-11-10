package catfish.sales.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.dao.Dao;
import catfish.base.database.Database;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;
import catfish.framework.IServiceProvider;
import catfish.sales.models.BaseUserModel;
import catfish.sales.models.PosModel;
import catfish.sales.objects.BaseUserObject;
import catfish.sales.objects.MerchantStoreObject;
import catfish.sales.utils.DataTranslator;

public class POSService extends DataService<MerchantStoreObject>{
	private Database db;
	private Dao<MerchantStoreObject> dao;
	public POSService(Database db){
		super(MerchantStoreObject.class, db);
		this.db=db;
		this.dao = Dao.create(MerchantStoreObject.class, db);
	}

	public List<PosModel> getFilterablePos(String name, String status, String s2,
			String salesdistrict, String tag, String cnareacode, String id) {
		String sql = String.format("select * from %s m ", this.dao.getTableName());
		String joinsql="";
		String wheresql="";
		String concatsql=" where ";
		if(name!=null){
			name=name.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.Name in ('"+name+"')");		
			concatsql=" and ";
		}
		if(status!=null){
			status=status.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.Status in ('"+status+"')");
			concatsql=" and ";
		}
		if(s2!=null){
			s2=s2.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.OwnerUserId in ('"+s2+"')");		
			concatsql=" and ";
		}
		if(salesdistrict!=null){
			salesdistrict=salesdistrict.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" der.DistrictId in ('"+salesdistrict+"')");
			joinsql=joinsql.concat("left join DistrictEntityRelationObjects der on der.EntityId=m.Id ");
			concatsql=" and ";
		}
		if(tag!=null){
			tag=tag.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" ptr.TagId in ('"+tag+"')");
			joinsql=joinsql.concat("left join POSTagRelationObjects ptr on ptr.POSId=m.Id  ");
			concatsql=" and ";
		}
		if(cnareacode!=null){
			cnareacode=cnareacode.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.CNAreaId in ('"+cnareacode+"')");
			concatsql=" and ";
		}
		List<MerchantStoreObject> list = this.dao.getMultiple(sql+joinsql+wheresql, null);
		List<PosModel> results=new ArrayList<PosModel>();
		results= DataTranslator.convertList(list,PosModel.class, results, null);
		return results;
	}
	
//	public HashMap<String, BaseUserModel> getPosAffiliateInfo(String posIdList){
//		String sql="select distinct pdor.POSId as posId, m.Id as id,m.IdName as idName,m.IdNumber as idNumber,c.Content as mobile "
//				+ "from dealeruserobjects m "
//				+ "left join merchantusercontactobjects muc on muc.MerchantUserId=m.Id "
//				+ "left join contactobjects c on c.Id=muc.ContactId "
//				+ "left join dorgduserrelationobjects dour on dour.AffiliateId=m.Id "
//				+ "left join posdorgrelationobjects pdor on pdor.OrgNodeId=dour.OrgNodeId  "
//				+ "where  pdor.DeletedOn is not null and c.Content is not null and pdor.posid in (:posId);";
//		HashMap<String,BaseUserModel> map=new HashMap<String,BaseUserModel>();
//		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().add("posId", posIdList).build();
//		List<ArrayList<String >> results=db.queryMultipleResults(sql, params, DatabaseExtractors.STRING_ARRAY_EXTRACKTER);
//		BaseUserModel model=new BaseUserModel();
//		for(ArrayList<String > result : results){
//			model.id=result.get(1);
//			model.idName=result.get(2);
//			model.idNumber=result.get(3);
//			model.mobile=result.get(4);
//			map.put(result.get(0), model);
//		}
//		return map;
//	}

}


