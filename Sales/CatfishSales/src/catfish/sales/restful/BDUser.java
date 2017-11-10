package catfish.sales.restful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.MerchantStatus;
import catfish.base.business.common.MerchantUserRole;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.database.Database;
import catfish.framework.IServiceProvider;
import catfish.framework.database.IDatabaseService;
import catfish.framework.restful.IRESTfulService;
import catfish.sales.Configuration;
import catfish.sales.models.BaseUserModel;
import catfish.sales.models.DUserModel;
import catfish.sales.models.InstallmentApplicationModel;
import catfish.sales.models.SUserModel;
import catfish.sales.services.ApplicationService;
import catfish.sales.services.BDUserService;
import catfish.sales.services.DUserService;
import catfish.sales.services.OrgService;
import catfish.sales.services.SUserService;
import catfish.sales.utils.HttpUtils;

@Path("bd")
public class BDUser {
    private static final String JSON_UTF8=IRESTfulService.JSON_UTF8;
    private static final String appStatusRestUrl=Configuration.getAppStatusRestUrl();
    private BDUserService businessDeveloperService;

    private Database mysql;
    private Database sqlserver;
	public BDUser(IServiceProvider sp){
		IDatabaseService dbService = sp.getService(IDatabaseService.class);
		mysql = dbService.getDatabase("mysql");
		businessDeveloperService = new BDUserService(mysql);
		sqlserver = dbService.getDatabase("sqlserver");		
	}
	
	@GET
	@Path("/{id}/pos")
    @Produces(JSON_UTF8)
	public List<String> getPOSList(@PathParam("id") String id){
		return businessDeveloperService.getPOSList(id);
	}
	
	@GET
	@Produces(JSON_UTF8)
	public List<DUserModel> getFilterableDearUser(
			@QueryParam("idname") String idname,
			@QueryParam("status") String status,
			@QueryParam("salesdistrict") String salesdistrict,
			@QueryParam("idnumber") String idnumber,
			@QueryParam("userid") String userid,
			@QueryParam("mobile") String mobile,
			@QueryParam("posid") String posid,
			@QueryParam("id") String id,
			@QueryParam("role") String role){
		return businessDeveloperService.getFilterableUser(idname,status,salesdistrict,idnumber,userid,mobile,posid,id,role);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") String id){
		businessDeveloperService.delete(id);;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean update(@PathParam("id") String id, DUserModel model){
		return businessDeveloperService.update(id , model);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(JSON_UTF8)
	public boolean add(DUserModel model){
		return businessDeveloperService.add(model);
	}
	
	@GET
	@Path("/{id}/application")
    @Produces(JSON_UTF8)
	public List<InstallmentApplicationModel> getApplicationByDate(@PathParam("id") String id){
		List<String> POSIds=businessDeveloperService.getPOSList(id);	
		ApplicationService appService=new ApplicationService(sqlserver);
		return appService.getApplicationByPosIdList(POSIds,mysql);
	}
	
	@GET
	@Path("/{id}/collectionapplication")
    @Produces(JSON_UTF8)
	public List<InstallmentApplicationModel> getCollectionApplicationByDate(@PathParam("id") String id){
		List<String> POSIds=businessDeveloperService.getPOSList(id);		
		ApplicationService appService=new ApplicationService(sqlserver);
		return appService.getCollectionAppInfoByPOSList(POSIds,new Date());
	}
}
