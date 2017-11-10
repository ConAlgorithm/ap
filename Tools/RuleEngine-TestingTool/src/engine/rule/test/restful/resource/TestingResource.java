package engine.rule.test.restful.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import engine.rule.test.data.ExecuteResult;
import engine.rule.test.data.ValidateResult;
import engine.rule.test.service.object.BasicResponse;
import engine.rule.test.service.object.TestCaseFile;

@Path("testing")
public interface TestingResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String welcome();
		
	//获取规则分析器的服务地址
	@GET
	@Path("analyzer")
	public String getAnalyzerUrl();
	
	//编译model
	@GET
	@Path("compile/{modelPackage}")
	public BasicResponse compile(@PathParam("modelPackage") String modelPackage);
		
	//上传并执行测试用例
	@POST
	@Path("data")
	public BasicResponse submitTestingDataAndExecute(final byte[] body);
	
	//上传测试用例
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA )
	@Path("testcase/upload/{product}/{handler}")
	public BasicResponse uploadTestCase(@PathParam("product") String product, @PathParam("handler") String handler, byte[] body);
	
	//执行测试
	@POST
	@Path("testcase/execute")
	public BasicResponse executeTestCase(final byte[] body);
	
	//获取测试结果
	@GET
	@Path("result/{product}")
	public ValidateResult getTestingResult(@PathParam("product") String product);
	
	//获取执行结果
	@GET
	@Path("executeResult/{product}")
	public ExecuteResult getExecuteResult(@PathParam("product") String product);
	
	//获取验证结果
	@GET
	@Path("validateResult/{product}")
	public ValidateResult getValidateResult(@PathParam("product") String product);
	
	//停止测试
	@GET
	@Path("stop/{product}")
	public BasicResponse stopTesting(@PathParam("product") String product);
	
	//清除日志
	@GET
	@Path("clearLog/{product}")
	public BasicResponse clearLog(@PathParam("product") String product);
	
	//登录验证
	@POST
	@Path("login")
	public BasicResponse login(final byte[] body);
	
	//更新handler中的model
	@POST
    @Path("handler/model/{enviroment}/{product}/{type}")
    public BasicResponse updateModelsOfHandler(@PathParam("enviroment") String env, @PathParam("type") String type, @PathParam("product") String product, byte[] data);
	//按时间和handler查询案例列表
	@GET
	@Path("testcase/{product}/{handler}")
	public List<TestCaseFile> getTestCases(@PathParam("product") String product, @PathParam("handler") String handler, @QueryParam("date") String date);
}
