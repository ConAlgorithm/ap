package engine.rule.analyzer.restful.resource.impl;

import javax.ws.rs.core.Response;

import engine.rule.analyzer.restful.ResponseBody;
import engine.rule.analyzer.restful.resource.ModelResource;
import engine.rule.analyzer.service.ModelService;
import engine.rule.analyzer.service.object.BasicResponse;
import engine.rule.analyzer.service.object.ModelTagsList;

public class ModelResourceImpl implements ModelResource{

	private ModelService modelService = new ModelService();
	public String welcome()
	{
		return "welcome to RuleEngine-Analyzer!";
	}
	
	//生成model java文件
	public BasicResponse generateModelFiles(String product,String type)
	{
		return modelService.generateModelFiles(product, type);
	}
	
	//获取model信息
	public Response getModelInfo(String product, String type, String modelName, String callback)
	{
		return ResponseBody.jsonPBodyOrNot(callback, modelService.getModelInfo(product, type, modelName));
	}
	
	//获取生成的model java 文件
	public Response getModelJavaFile(String product,String type, String modelClass,String callback)
	{
		return ResponseBody.jsonPBodyOrNot(callback, modelService.getModelJavaFile(product, type, modelClass));
	}
	
	//获取model包下的所有model类名列表
	public Response getModelsList(String product, String type, String callback)
	{
        return ResponseBody.jsonPBodyOrNot(callback, modelService.getModelsList(product, type));
	}
	
	//生成model的数据字典
    public Response getDictionary(String product, String type)
    {
    	return modelService.getDictionary(product, type);
    }
    
    //获取model与标签的对应关系
    public ModelTagsList getModelTags(String product,String type)
    {
    	return modelService.getModelTags(product, type);
    }
    
    //获取所有Handler名称
    public Response getRuleHandlers(String product, String callback)
    {
    	return ResponseBody.jsonPBodyOrNot(callback, modelService.getRuleHandlers(product));
    }
    
    //获取一个决策点的所有Model类名
    //此处type表示test还是prod
    @Override
    public Response getModelsOfHandler(String env, String product, String type, String handlerName, String callback)
    {
        return ResponseBody.jsonPBodyOrNot(callback, modelService.getModelsOfHandler(env, type, product, handlerName));
    } 
    
    //获取所有Model的中文描述
    public Response getDesriptionOfModels(String product, String type, String callback)
    {
    	return ResponseBody.jsonPBodyOrNot(callback, modelService.getDesriptionOfModels(product, type));
    }

    public Response getDomainOfModelMembers(String domainName,String callback,String bindDomain,String paramDomains,String returnDomain)
    {
    	return ResponseBody.jsonPBodyOrNot(callback, modelService.getDomainOfModelMembers(domainName, bindDomain, paramDomains, returnDomain));
    }
}
