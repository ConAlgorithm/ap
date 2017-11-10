package catfish.sales.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.dao.Dao;
import catfish.base.database.Database;
import catfish.sales.Configuration;
import catfish.sales.models.BaseUserModel;
import catfish.sales.models.InstallmentApplicationModel;
import catfish.sales.utils.HttpUtils;

public class ApplicationService extends DataService<InstallmentApplicationModel> {
	
	private Database db;
	private Dao<InstallmentApplicationModel> dao;
	public ApplicationService(Database db){
		super(InstallmentApplicationModel.class, db);
		this.db=db;
		this.dao = Dao.create(InstallmentApplicationModel.class, db);
	}

	public List<InstallmentApplicationModel> getAppInfoByPOSList(
			String posIds, Date date) {
		
		String sql="	select i.Id,i.ProductName,i.InstallmentStartedOn as InstallmentStartedOn,i.DateAdded,i.LastModified,i.Principal,i.MerchantStoreId as posId "
				+ "	,i.Status,i.UploadStatus as uploadStatus,i.InstalmentChannel,i.FirstPaybackDate as firstPaybackDate	 "
				+ "	,e.Id as userId,e.IdName as userIdName,co.Content as userMobile,wxu.NickName as userWXName,wxu.HeadImageUrl as userWXPhoto	 "
				+ "	,(select ao.content from AffiliateOperationObjects ao where i.Id=ao.AppId and ao.Tag=10) as hasReported,"
				+ "(select rmdo.content from AffiliateOperationObjects rmdo where i.Id=rmdo.AppId and rmdo.Tag=20) as hasRecommended	 "
				+ "	from installmentapplicationObjects i	 "
				+ "	 join userobjects u on u.id=i.UserId	 "
				+ "	 left join ContactObjects co on u.MobileContactId=co.Id	 "
				+ "	 join EndUserExtensionObjects e on e.Id=i.UserId	 "
				+ "	 join WeiXinUserObjects wxu on wxu.Id=u.WeiXinUserId	 "				
				+ "	 where  i.DateAdded>:date 	and "
				+ "	  i.MerchantStoreId in (:posId) 	 "
				+ "	 order by i.Id,i.DateAdded desc,i.MerchantStoreId	 ";		
		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
				  .add("posId",posIds)
				  .add("date",date)
				  .build();
		return dao.getMultiple(sql, params);		
	}

	public List<InstallmentApplicationModel> getCollectionAppInfoByPOSList(
			List<String> posIds,Date date) {
		String sql= "select i.Id,i.ProductName,i.Principal,i.DateAdded,i.LastModified,ms.Name,e.Id as userId,e.IdName  as userIdName,u.UserName as userName,i.FirstPaybackDate,"
				+ "(select COUNT(1) from CollectionNoteObjects c where c.AppId=i.Id ) as collectionTime,"
				+ "(select COUNT(1) from CollectionNoteObjects c where c.AppId=i.Id and c.NoteType=2) as collectionWithdrawTime "
				+ "  from InstallmentApplicationObjects i	 "
				+ "	 join EndUserExtensionObjects e on i.UserId=e.Id	 "
				+ "	 join UserObjects u on u.Id=i.UserId	 "
				+ "	 join MerchantStoreObjects ms on ms.Id=i.MerchantStoreId	 "
				+ "	 where i.Status=200 	 "
				+ "	 and i.FirstPaybackDate>:startdate and i.FirstPaybackDate<:enddate	 "
				+ "	 and i.MerchantStoreId in (:posId)	 "
				+ "	 order by i.DateAdded desc	 ";
		String posIdStr="";
		Iterator iter = posIds.iterator();
		while(iter.hasNext()){
			posIdStr+=iter.next()+"','";
		}
		posIdStr=posIdStr.substring(0, posIdStr.length()-3);
		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
				  .add("posId",posIdStr)
				  .add("startdate",date)
				  .add("enddate",date)
				  .build();
		return dao.getMultiple(sql, params);
	}	
	
	public List<InstallmentApplicationModel> getApplicationByPosIdList(List<String> POSIds,Database db){

		String posIdStr="";
		Iterator iter = POSIds.iterator();
		while(iter.hasNext()){
			posIdStr+=iter.next()+"','";
		}
		posIdStr=posIdStr.substring(0, posIdStr.length()-3);
		
		//get app basic info
		List<InstallmentApplicationModel> apps=getAppInfoByPOSList(posIdStr,new Date());
		if(apps.size()==0){
			return apps;
		}
		//get app d1 info
		BaseUserModel affiliate;
		Map<String, BaseUserModel> d1List=new OrgService(db, "DOrgDUserRelationObjects", "DOrgRelationObjects",  "POSDOrgRelationObjects","DealerUserObjects").getPosAffiliateInfo(posIdStr);
		if(d1List!=null){
			for(InstallmentApplicationModel app :apps){
				affiliate=d1List.get(app.posId);
				app.d1Id=affiliate.id;
				app.d1IdName=affiliate.idName;
				app.d1Moblie=affiliate.mobile;
			}
		}
		//get app s1 info
		String s1IdStr="";
		for(InstallmentApplicationModel app:apps){
			if(app.s1Id!=null){
				s1IdStr+=app.s1Id+"','";
			}		
		}
		if(s1IdStr!=""){
			s1IdStr=s1IdStr.substring(0, s1IdStr.length()-3);
			HashMap<String, BaseUserModel> s1List=new SUserService(db).getAffiliateInfo(s1IdStr);	
			if(s1List!=null){
				for(InstallmentApplicationModel app :apps){
					affiliate=s1List.get(app.s1Id);
					app.s1IdName=affiliate.idName;
					app.s1Mobile=affiliate.mobile;
				}
			}
		}
		//get app status info
		return getAppDetailInfoByPOSList(apps);
	}
	
	public List<InstallmentApplicationModel> getAppDetailInfoByPOSList(
			List<InstallmentApplicationModel> apps) {
		//get description and progress,hasd1dchecked		
		List<InstallmentApplicationModel> appIds=new ArrayList<InstallmentApplicationModel>();
		InstallmentApplicationModel app=new InstallmentApplicationModel();
		for(int i=0;i <apps.size();i++){ 
			app.id=apps.get(i).id;
			appIds.add(app);
		} 
		Gson gson=new Gson();
		HttpResponse response=HttpUtils.postStringRequest(gson.toJson(appIds),Configuration.getAppStatusRestUrl());
		try {
			String result = EntityUtils.toString(response.getEntity());
			Map<String,Map<String,String >> map=new HashMap<String,Map<String,String>>();
			map=gson.fromJson(result, map.getClass());
			Map<String, String> appstatus = null;
			for(int i=0;i <apps.size();i++){ 
				app=apps.get(i);
				appstatus=map.get(app.id);
				app.hasD1Checked=appstatus.get("hasD1Checked");
				app.description=appstatus.get("description");
				app.progress=appstatus.get("progress");
			} 
			return apps;
		} catch (Exception e) {
			Logger.get().error("currentApplication error appid:"+appIds,e);
		}
		return apps;
	}

	public List<InstallmentApplicationModel> getCollectionAppDetailsByAppId(String appId){
		
		return null;
		
	}
	
}
