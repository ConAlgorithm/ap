package catfish.sales.restful;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import catfish.base.database.Database;
import catfish.framework.IServiceProvider;
import catfish.framework.database.IDatabaseService;
import catfish.framework.restful.IRESTfulService;
import catfish.sales.models.BaseUserModel;
import catfish.sales.models.DUserModel;
import catfish.sales.models.InstallmentApplicationModel;
import catfish.sales.services.ApplicationService;
import catfish.sales.services.BDUserService;
import catfish.sales.services.DUserService;
import catfish.sales.services.OrgService;
import catfish.sales.services.SUserService;

@Path("dealer")
public class DUser {
	private static final String JSON_UTF8= IRESTfulService.JSON_UTF8;
	
	private DUserService dealerService;
	
    private Database mysql;
    private Database sqlserver;
	public DUser(IServiceProvider sp){
		IDatabaseService dbService = sp.getService(IDatabaseService.class);
		mysql = dbService.getDatabase("mysql");
		dealerService = new DUserService(mysql);
		sqlserver = dbService.getDatabase("sqlserver");		
	}
	
	@GET
	@Path("/{id}/pos")
	@Produces(JSON_UTF8)
	public List<String> getPOSList(@PathParam("id") String id){
		return dealerService.getPOSList(id);
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
		return dealerService.getFilterableUser(idname,status,salesdistrict,idnumber,userid,mobile,posid,id,role);
	}
	
	@GET
	@Path("/{id}/application")
    @Produces(JSON_UTF8)
	public List<InstallmentApplicationModel> getApplicationByDate(@PathParam("id") String id){
		List<String> POSIds=dealerService.getPOSList(id);		
		ApplicationService appService=new ApplicationService(sqlserver);
		return appService.getApplicationByPosIdList(POSIds,mysql);
	}
	
	@GET
	@Path("/{id}/collectionApplication")
    @Produces(JSON_UTF8)
	public List<InstallmentApplicationModel> getCollectionApplicationByDate(@PathParam("id") String id){
		List<String> POSIds=dealerService.getPOSList(id);	
		ApplicationService appService=new ApplicationService(sqlserver);
		return appService.getCollectionAppInfoByPOSList(POSIds,new Date());
	}
	
}
