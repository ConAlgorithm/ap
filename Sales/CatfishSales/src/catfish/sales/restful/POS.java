package catfish.sales.restful;

import java.util.List;

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

import catfish.base.database.Database;
import catfish.framework.IServiceProvider;
import catfish.framework.database.IDatabaseService;
import catfish.framework.restful.IRESTfulService;
import catfish.sales.models.PosModel;
import catfish.sales.objects.MerchantStoreObject;
import catfish.sales.services.POSService;

@Path("pos")
public class POS {
	private static final String JSON_UTF8=IRESTfulService.JSON_UTF8;;
	
	private POSService merchantStoreService;
	
	public POS(IServiceProvider sp){
		IDatabaseService dbService = sp.getService(IDatabaseService.class);
		Database db = dbService.getDatabase("mysql");
		merchantStoreService = new POSService(db);
	}
	/**
	 * 通过Id查询POS信息
	 */
	@GET
	@Path("/{id}")
    @Produces(JSON_UTF8)
	public MerchantStoreObject getPosById(@PathParam("id") String id,
			@QueryParam("query") String query){
		return merchantStoreService.get(id);
	}
	/**
	 * 获取过滤后的pos list
	 */
	@GET
	@Produces(JSON_UTF8)
	public List<PosModel> getFilterablePos(
			@QueryParam("name") String name,
			@QueryParam("status") String status,
			@QueryParam("s2") String s2,
			@QueryParam("salesdistrict") String salesdistrict,
			@QueryParam("tag") String tag,
			@QueryParam("cnareacode") String cnareacode,
			@QueryParam("id") String id){
		return merchantStoreService.getFilterablePos(name,status,s2,salesdistrict,tag,cnareacode,id);	
	}
	
	/**
	 * 获取pos的订单列表
	 */
	@GET
	@Path("/{id}/order")
	@Produces(JSON_UTF8)
	public List<PosModel> getPosOrders(
			@QueryParam("name") String name,
			@QueryParam("status") String status,
			@QueryParam("s2") String s2,
			@QueryParam("salesdistrict") String salesdistrict,
			@QueryParam("tag") String tag,
			@QueryParam("cnareacode") String cnareacode,
			@QueryParam("id") String id){
		return merchantStoreService.getFilterablePos(name,status,s2,salesdistrict,tag,cnareacode,id);	
	}
	
	/**
	 * 更新门店信息
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(JSON_UTF8)
	public boolean update(@PathParam("id") String id, MerchantStoreObject merchantStore){
		return merchantStoreService.update(id, merchantStore);
	}
	/**
	 * 更新门店状态
	 */
	@PUT
	@Path("/{id}/status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(JSON_UTF8)
	public boolean updateStatus(@PathParam("id") String id, MerchantStoreObject merchantStore){
		return merchantStoreService.update(id, merchantStore);
	}
	/**
	 * 更新门店tag
	 */
	@PUT
	@Path("/{id}/tag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(JSON_UTF8)
	public boolean updateTag(@PathParam("id") String id, MerchantStoreObject merchantStore){
		return merchantStoreService.update(id, merchantStore);
	}
	
	/**
	 * 删除门店
	 */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(JSON_UTF8)
	public boolean delete(@PathParam("id") String id, MerchantStoreObject merchantStore){
		return merchantStoreService.update(id, merchantStore);
	}
	/**
	 * 新增门店
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(JSON_UTF8)
	public boolean add(MerchantStoreObject merchantStore){
		return merchantStoreService.add(merchantStore);
	}
//	/**
//	 * 新增门店tag
//	 */
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(JSON_UTF8)
//	public boolean addTag(MerchantStoreObject merchantStore){
//		return merchantStoreService.add(merchantStore);
//	}

}
