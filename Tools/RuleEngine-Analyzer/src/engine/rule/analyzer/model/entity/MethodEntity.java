package engine.rule.analyzer.model.entity;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import catfish.base.Logger;

public class MethodEntity extends Entity{

	private static final String PARAMPREFIX = "arg";
	private Method md;
	private String methodName;
	private Class[] paramTypes;
	private Class returnType;
	private String storeFieldName;
	private String[] paramDomains;
	private String returnDomain;
	
	public MethodEntity(Method md, String ruleName, String[] paramDomains, String returnDomain)
	{
		super(ruleName);
		this.paramDomains = paramDomains;
		this.returnDomain = returnDomain;
		this.md = md;
		this.methodName = initMethodName();
		this.paramTypes = md.getParameterTypes();
		this.returnType = md.getReturnType();	
		this.storeFieldName = initStoreFieldName();
	}
	
	private String initMethodName()
	{		
		return md.getName();
	}
	
	private String initStoreFieldName()
	{
		StringBuilder builder = new StringBuilder();
		for(int count = 0; count < paramTypes.length; count ++)
		{
			builder.append("_").append(paramTypes[count].getSimpleName());
		}
		return "store_" + this.methodName + builder.toString();
	}
	private boolean isVoidParam()
	{
		return paramTypes.length == 0;
	}
	private boolean isVoidReturn()
	{
		return returnType.equals(void.class);
	}
	private String storeFieldTemplate()
	{
		Entity parent = this.parent;
		while(!(parent instanceof ClassEntity))
		{
			parent = parent.parent;
		}
		ClassEntity entity = (ClassEntity)parent;
		FieldEntity storeField = null;
		if(isVoidParam())
		{
			storeField = new FieldEntity(initStoreFieldName(), returnType, entity.getCls());
			
		}else{		
			storeField = new FieldEntity(initStoreFieldName(), Map.class, returnType, entity.getCls());
			StringBuilder builder = new StringBuilder();
			builder.append("new\t").append(HashMap.class.getName()).append("<>()");
			storeField.defaultValue(builder.toString());
		}
		
		this.addEntity(storeField);
		
		return storeField.template();
		
	}
	private String paramsTemplate()
	{
		StringBuilder builder = new StringBuilder();
		if(!isVoidParam())
		{
			for(int count = 0; count < paramTypes.length; count ++)
			{
				builder.append(paramTypes[count].getName()).append("\t").append(PARAMPREFIX + count).append(",");
			}
			builder.replace(builder.length()-1, builder.length(), "");
		}
		
		return builder.toString();
	}
	
	private String returnTemplate()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(returnType.getName());
		return builder.toString();
	}
	
	private String getStoreFieldKey()
	{
		StringBuilder builder = new StringBuilder();
		if(!isVoidParam())
		{
			for(int count = 0; count < paramTypes.length; count ++)
			{
				builder.append("\"\"+" +(PARAMPREFIX + count)+"+");
			}
			builder.replace(builder.length()-1, builder.length(), "");
		}else{
			builder.append(storeFieldName);
		}
		return builder.toString();
	}
	
	@Override
	public String template() {
		StringBuilder builder = new StringBuilder();
		if(!isVoidReturn())
		{
			builder.append(storeFieldTemplate())
			.append("\tpublic\t").append(returnTemplate()).append("\t")
			.append(methodName)
			.append("(")
			.append(paramsTemplate())
			.append("){\n")
			.append("\t\t return\t");
			
			if(isVoidParam())
			{
				builder.append(storeFieldName);
			}else{
				builder.append(storeFieldName).append(".get("+ getStoreFieldKey()+")");
			}
			builder.append(";\n").append("\t};\n");
		}
			
		return builder.toString();
	}

	@Override
	public JSONObject toJson()
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("classify", "method");
			if(methodName != null)
			{
				obj.put("name", methodName);
			}
			if(ruleName != null)
			{
				obj.put("rule", this.ruleName);
			}
			JSONArray paramTypeArray = new JSONArray();
			for(Class param : paramTypes)
			{
				paramTypeArray.put(param.getName());
			}
			obj.put("paramTypes", paramTypeArray);
			
			if(paramDomains != null)
			{
				JSONArray paramDomainsArray = new JSONArray(Arrays.asList(this.paramDomains));
				obj.put("paramDomains", paramDomainsArray);
			}
			
			if(returnDomain != null)
			{
				obj.put("returnDomain", this.returnDomain);
			}
			
			if(returnType != null)
			{
				obj.put("returnType", returnType.getName());
			}
		} catch (JSONException e) {
			Logger.get().error(methodName + " toJson error!", e);
		}
		return obj;
	}

    public Method getMd() {
        return md;
    }

    public void setMd(Method md) {
        this.md = md;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }

    public String getStoreFieldName() {
        return storeFieldName;
    }

    public void setStoreFieldName(String storeFieldName) {
        this.storeFieldName = storeFieldName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
