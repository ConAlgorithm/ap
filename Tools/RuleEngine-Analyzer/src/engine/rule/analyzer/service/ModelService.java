package engine.rule.analyzer.service;

import static engine.rule.analyzer.service.object.BasicResponse.Success;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import catfish.base.StringUtils;
import catfish.base.business.ruleengine.BaseInfo;
import catfish.base.business.ruleengine.HandlerInfo;
import catfish.base.business.ruleengine.HandlersInfo;
import catfish.base.business.ruleengine.RuleEngineManager;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

import engine.rule.analyzer.model.entity.Entity;
import engine.rule.analyzer.model.entity.ValueEntity;
import engine.rule.analyzer.resource.DomainGenerator;
import engine.rule.analyzer.resource.JarScanner;
import engine.rule.analyzer.resource.ModelConfig;
import engine.rule.analyzer.resource.ModelGenerator;
import engine.rule.analyzer.service.object.BasicResponse;
import engine.rule.analyzer.service.object.HandlersList;
import engine.rule.analyzer.service.object.ModelDomain;
import engine.rule.analyzer.service.object.ModelInfo;
import engine.rule.analyzer.service.object.ModelJava;
import engine.rule.analyzer.service.object.ModelTagsList;
import engine.rule.analyzer.service.object.ModelsDescription;
import engine.rule.analyzer.service.object.ModelsList;
import engine.rule.analyzer.util.ModelUtils;
import engine.rule.analyzer.util.PathUtil;

public class ModelService {

	private static final String RuleEngineJar = PathUtil.ResourceJarPath + "RuleEngine.jar";
	private RuleEngineManager handersManager = new RuleEngineManager();
	
	//生成model java文件
	public BasicResponse generateModelFiles(String product, String type)
	{
		String path = PathUtil.getModelPath(type, product);
		System.out.println(path);
		JarScanner scanner = new JarScanner(RuleEngineJar);
		List<Class<?>> weixinClasses = scanner.createClasses(path);
		ModelGenerator.clearFolder(PathUtil.ModelJavaPath);
		for(Class cls : weixinClasses)
		{
			ModelGenerator.generateClassFile(cls, PathUtil.ModelJavaPath);
		}
		return Success;
	}
	
	//获取model信息
	public ModelInfo getModelInfo(String product, String type, String modelName)
	{
		String path = PathUtil.getModelPath(type, product) + modelName;
		System.out.println(path);
		Entity entity = null;
		JarScanner scanner = new JarScanner(RuleEngineJar);
		Class<?> cls = scanner.createClass(path);
		entity = ModelGenerator.generateClassEntity(cls);
		return new ModelInfo(entity);	
	}
	
	//获取生成的model java 文件
	public ModelJava getModelJavaFile(String product, String type, String modelClass)
	{
		String path = PathUtil.getModelPath(type, product) + modelClass;
		System.out.println(path);
		Entity entity = null;
		JarScanner scanner = new JarScanner(RuleEngineJar);
		Class<?> cls = scanner.createClass(path);
		entity = ModelGenerator.generateClassEntity(cls);
		return new ModelJava(entity);
	}
	
	//获取model包下的所有model类名列表
	public ModelsList getModelsList(String product, String type)
	{
		String path = PathUtil.getModelPath(type, product);
		System.out.println(path);
		List<Class<?>> clsList = null;
		JarScanner scanner = new JarScanner(RuleEngineJar);
		clsList = scanner.createClasses(path);
		return new ModelsList(clsList);		
	}
	
	//生成model的数据字典
    public Response getDictionary(String product, String type)
    {
    	String path = PathUtil.getModelPath(type, product);
        JarScanner scanner = new JarScanner(RuleEngineJar);
        List<Class<?>> clsList = scanner.createClasses(path);               
        String csvStr = ModelUtils.getCsvString(clsList);          
        return Response.status( 200 )
                .header("Content-Type", "text/csv; charset=utf-8")
                .entity( csvStr )
                .build();    
    }
    
    //获取model与标签的对应关系
    public ModelTagsList getModelTags(String product,String type)
    {
    	String path = PathUtil.getModelPath(type, product);
    	System.out.println(path);
		Map<String, String> tagsMap = new HashMap<>();
		JarScanner scanner = new JarScanner(RuleEngineJar);
		List<Class<?>> clsList = scanner.createClasses(path);
		for(Class item : clsList)
		{
			tagsMap.put(item.getName(), ModelConfig.get(item.getName()));
		}
		return new ModelTagsList(tagsMap);
    }
    
    //获取所有Handler名称
    public HandlersList getRuleHandlers(String product)
    {
    	String path = PathUtil.getHandlerPath(product);
    	System.out.println(path);
    	List<Class<?>> clsList = null;
		JarScanner scanner = new JarScanner(RuleEngineJar);
		clsList = scanner.createClasses(path);	
		return new HandlersList(clsList);
    }
    
    //获取一个决策点的所有Model类名
    //此处type表示test还是prod
    public HandlerInfo getModelsOfHandler(String env,String type, String product, String handlerName)
    {
    	HandlersInfo handlers = handersManager.load(new BaseInfo(env, product, type), HandlersInfo.class);
    	HandlerInfo handler = null;
    	if(handlers != null)
    	{
    	   handler = handlers.handlers.get(handlerName);
    	}
    	return handler;
    }
    
    //获取所有Model的中文描述
    public ModelsDescription getDesriptionOfModels(String product, String type)
    {
    	String path = PathUtil.getModelPath(type, product);
    	System.out.println(path);
		Map<String, String> descMap = new HashMap<>();
		JarScanner scanner = new JarScanner(RuleEngineJar);
		List<Class<?>> clsList = scanner.createClasses(path);
		for(Class item : clsList)
		{
			descMap.put(item.getSimpleName(), ModelGenerator.getModelDescription(item));
		}
		return new ModelsDescription(descMap);
    }

    public List<ModelDomain> getDomainOfModelMembers(String domainName,String bindDomain,String paramDomains,String returnDomain){
		JarScanner scanner = new JarScanner(RuleEngineJar);
		List<ModelDomain> domains = new ArrayList<>();
    	String[] list = {bindDomain, paramDomains, returnDomain};
    	for(String item : list){
    		if(!StringUtils.isNullOrWhiteSpaces(item)){
    			Class<?> domainCls = scanner.createClass(item);
    			if(domainCls != null)
    			{
    				ModelDomainInstance domainIst = DomainGenerator.getDomainInstance(domainCls);
    				Class<?> adapterCls = scanner.createClass(domainIst.adapter());
    				List<ValueEntity> values = DomainGenerator.getDomainItemsList(domainIst, adapterCls);
                    ModelDomain domain = new ModelDomain(item, values);
                    domains.add(domain);
    			}       			      		
        	}
    	}
    	return domains;
    }
}
