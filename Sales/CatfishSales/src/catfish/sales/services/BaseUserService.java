package catfish.sales.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import catfish.base.Logger;
import catfish.base.dao.Dao;
import catfish.base.dao.SqlUtils;
import catfish.base.database.Database;
import catfish.sales.objects.BaseUserObject;
import catfish.sales.objects.MerchantStoreObject;
import catfish.sales.utils.DataTranslator;

public abstract class BaseUserService<Tobject extends BaseUserObject,Tmodel> extends DataService<Tobject> {
	
	protected BaseUserService(Class<Tobject> to,Class<Tmodel> tm, Database db) {
		super(to, db);
		this.db = db;
		this.dao = Dao.create(to, db);
		this.tm=tm;
		this.to=to;
		this.tablename=this.dao.getTableName();
	}

	private String tablename;
	private Database db;
	private Dao<Tobject> dao;
	private Class<Tmodel> tm;
	private Class<Tobject> to;
	
	public List<String> getPOSList(String affiliateId){
		OrgService tree = new OrgService(db, "DOrgDUserRelationObjects", "DOrgRelationObjects",  "POSDOrgRelationObjects");
		List<String> allNodes = tree.getPOSList(affiliateId);
		return allNodes;
	}
	
	public List<Tmodel> getFilterableUser(String idname, String status,
			String salesdistrict, String idnumber, String userid,
			String mobile, String posid, String id, String role) {
		String sql = String.format("select distinct m.* from %s m ", this.dao.getTableName());
		String joinsql="";
		String wheresql="";
		String concatsql=" where ";
		if(idname!=null){
			idname=idname.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.idname in ('"+idname+"')");
			concatsql=" and ";
		}
		if(status!=null){
			status=status.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.Status in ('"+status+"')");
			concatsql=" and ";
		}		
		if(salesdistrict!=null){
			salesdistrict=salesdistrict.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" der.DistrictId in ('"+salesdistrict+"')");
			joinsql=joinsql.concat("left join DistrictEntityRelationObjects der on der.EntityId=m.Id ");
			concatsql=" and ";
		}
		if(idnumber!=null){
			idnumber=idnumber.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.idnumber in ('"+idnumber+"')");
			concatsql=" and ";
		}
		if(userid!=null){
			userid=userid.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.userid in ('"+userid+"')");
			concatsql=" and ";
		}
		if(mobile!=null){
			mobile=mobile.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" c.ContactType=2 and m.mobile in ('"+mobile+"') ");
			joinsql=joinsql.concat("left join merchantusercontactobjects muc on m.id=muc.merchantuserid left join contactobjects c on c.id=muc.contactid  ");
			concatsql=" and ";
		}
		if(id!=null){
			id=id.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.id in ('"+id+"')");
			concatsql=" and ";
		}
		if(role!=null){
			role=role.replace(",", "','");
			wheresql=wheresql.concat(concatsql).concat(" m.role in ('"+role+"')");
			concatsql=" and ";
		}
//		if(posid!=null){
//			posid=posid.replace(",", "','");
//			wheresql=wheresql.concat(concatsql).concat(" pdor.DeletedOn is not null and pdor.posid in ('"+posid+"')");
//			joinsql=joinsql.concat("left join dorgduserrelationobjects dour on dour.AffiliateId=m.Id left join posdorgrelationobjects pdor on pdor.OrgNodeId=dour.OrgNodeId ");
//			concatsql=" and ";
//		}

		List<Tobject> list = this.dao.getMultiple(sql+joinsql+wheresql, null);
		List<Tmodel> results=new ArrayList<Tmodel>();
		results= DataTranslator.convertList(list,tm, results, null);
		return results;
	}

	
	
//	public String add(Tmodel model) {
//		String insert="";
//		try {
//			Tobject object = DataTranslator.convert(model, to, null);
//			object.Id=UUID.randomUUID().toString();
//			Date date=new Date();
//			object.LastModified=date;
//			object.DateAdded=date;
//			insert = SqlUtils.buildInsert(tablename, SqlUtils.getFields(object),"mysql");
//        	String setsql = String.format("set names utf8mb4 ");
//        	db.updateTable(setsql, null);
//            boolean success = dao.insert(insert, object);
//            if(!success){
//                Logger.get().error("insert sql failed:"+ insert+","+tablename);
//                return null;
//            }
//            return object.Id;
//        } catch (InstantiationException e) {
//        	Logger.get().error("InstantiationException:",e); 
//		} catch (IllegalAccessException e) {
//			Logger.get().error("IllegalAccessException:",e); 
//        }catch(Exception ex){
//            Logger.get().error("insert sql error:"+ insert+","+tablename,ex); 
//        }
//		return null;
//	}

	public Tmodel getUserById(){
		return null;
	}

	public void delete(String id){
		
	}
	
//	public void update(String id, Tmodel model){
//		
//	}
}
