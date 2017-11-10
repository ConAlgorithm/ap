package engine.rule.analyzer.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import engine.rule.analyzer.service.object.BasicResponse;
import engine.rule.analyzer.service.object.ModelTagsList;


@Path("model")
public interface ModelResource {
	
	@GET
	public String welcome();
	
	//生成model java文件
	@GET
	@Path("package/{package}/{type}")
	public BasicResponse generateModelFiles(@PathParam("package") String product, @PathParam("type") String type);
	
	//获取model信息
	@GET
	@Path("class/{product}/{type}/{modelName}")
	public Response getModelInfo(@PathParam("product") String product, @PathParam("type") String type, @PathParam("modelName") String modelName, @QueryParam("Callback") String callback);
	
	//获取生成的model java 文件
	@GET
	@Path("java/{product}/{type}/{modelClass}")
	public Response getModelJavaFile(@PathParam("product") String product, @PathParam("type") String type, @PathParam("modelClass") String modelClass, @QueryParam("Callback") String callback);
	
	//获取model包下的所有model类名列表
	@GET
	@Path("package/{product}/{type}/list")
	public Response getModelsList(@PathParam("product") String product, @PathParam("type") String type,  @QueryParam("Callback") String callback);
	
	//生成model的数据字典
    @GET
    @Path("dictionary/{product}/{type}")
    public Response getDictionary(@PathParam("product") String product, @PathParam("type") String type);
    
    //获取model与标签的对应关系
    @GET
    @Path("tag/{product}/{type}")
    public ModelTagsList getModelTags(@PathParam("product") String product, @PathParam("type") String type);
    
    //获取所有Handler名称
    @GET
    @Path("handler/{product}")
    public Response getRuleHandlers(@PathParam("product") String product, @QueryParam("Callback") String callback);
    
    //获取一个决策点的所有Model类名
    //此处type表示类型，例如handlers
    @GET
    @Path("handler/model/{enviroment}/{product}/{type}/{handler}")
    public Response getModelsOfHandler(@PathParam("enviroment") String env, @PathParam("product") String product, @PathParam("type") String type, @PathParam("handler") String handlerName, @QueryParam("Callback") String callback);
    
    //获取所有Model的中文描述
    @GET
    @Path("description/{product}/{type}")
    public Response getDesriptionOfModels(@PathParam("product") String product, @PathParam("type") String type, @QueryParam("Callback") String callback);
    
    @GET
    @Path("domain")
    public Response getDomainOfModelMembers(
    		@PathParam("domainName") String domainName,
    		@QueryParam("Callback") String callback,
    		@QueryParam("bindDomain") String bindDomain,
    		@QueryParam("paramDomains") String paramDomains,
    		@QueryParam("returnDomain") String returnDomain);
}
