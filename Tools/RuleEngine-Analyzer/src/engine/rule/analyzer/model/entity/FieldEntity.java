package engine.rule.analyzer.model.entity;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import catfish.base.Logger;
import engine.rule.analyzer.util.ModelUtils;

public class FieldEntity extends Entity{

	private Field fld;
	private Class beanCls;
	private String fieldName;
	private Class fieldType;
	private Method readMethod;
	private Method writeMethod;
	private String defaultValue;
	private Class genericType;
	private String derivativeVariableName;
	private String bindDomain;
	
	public FieldEntity(String fieldName, Class fieldType, Class beanCls)
	{
		super(null);
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.beanCls = beanCls;
	}
	public FieldEntity(String fieldName, Class fieldType, Class genericType, Class beanCls)
	{
		this(fieldName, fieldType, beanCls);
		this.genericType = genericType;
	}
	
	public FieldEntity(Field fld, Class beanCls)
	{
		this(null, null, fld, beanCls);
	}
	public FieldEntity(String ruleName, String bindDomain, Field fld, Class beanCls)
	{
		super(ruleName);
		this.bindDomain = bindDomain;
		this.fld = fld;
		this.beanCls = beanCls;
		this.fieldName = initFieldName();
		this.fieldType = initFieldType();
		this.readMethod = initReadMethod();
		this.writeMethod = initWriteMethod();
	}
	private String initFieldName()
	{
		return fld.getName();
	}
	
	private Class initFieldType()
	{
		return fld.getType();
	}
	private Method initReadMethod()
	{
		Method md = null;
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, beanCls);
			md = pd.getReadMethod();
		} catch (IntrospectionException e) {
			Logger.get().warn("Cannot getReadMethod of " + fieldName + " in " + beanCls.getName(), e);
		}
		return md;
	}
	private Method initWriteMethod()
	{
		Method md = null;
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, beanCls);
			md = pd.getWriteMethod();
		} catch (IntrospectionException e) {
			Logger.get().warn("Cannot getWriteMethod of " + fieldName + " in " + beanCls.getName(), e);
		}
		return md;
	}
	private String initGenericTypeTemplate()
	{
		String type = null;
		if(Collection.class.isAssignableFrom(fieldType))
		{
			type = "<" + ModelUtils.getWrapper(genericType).getName()+ ">";
		}else if(Map.class.isAssignableFrom(fieldType))
		{
			type = "<String," + ModelUtils.getWrapper(genericType).getName()+ ">";
		}
		return type;
	}
	private String fieldTemplate()
	{
		StringBuilder builder = new StringBuilder();
		String fullFieldName = fieldType.getName();
		if(genericType != null)
		{
			fullFieldName += initGenericTypeTemplate();
		}
		builder.append("\tprivate\t").append(fullFieldName).append("\t").append(fieldName);
		if(defaultValue != null)
		{
			builder.append("=").append(defaultValue);
		}
		builder.append(";\n");
		return builder.toString();
	}
	private String writeMethodTemplate()
	{
		String mdName = null;
		if(writeMethod == null)
		{
			
		}
		StringBuilder builder = new StringBuilder();
		builder.append("\tpublic\tvoid\t").append(getWriteMethodName())
		.append(String.format("(%s %s)", fieldType.getName(), fieldName)).append("{\n");
		builder.append("\t\t this.").append(fieldName).append("=").append(fieldName).append(";\n");
		builder.append("\t}\n");
		return builder.toString();
	}
	
	private String readMethdTemplate()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\tpublic\t").append(fieldType.getName()).append("\t").append(getReadMethodName()).append("()").append("{\n");
		builder.append("\t\t return\t").append(fieldName).append(";\n");
		builder.append("\t}\n");
		return builder.toString();
	}
	
	private String getPostfixName()
	{
		return fieldName.replace(fieldName.substring(0,1), fieldName.substring(0,1).toUpperCase());
	}
	private String getWriteMethodName()
	{
		if(writeMethod == null)
		{
			return "set" + getPostfixName();
		}
		return writeMethod.getName();
	}
	private String getReadMethodName()
	{
		if(readMethod == null)
		{		
			if(fieldType.equals(Boolean.class) || fieldType.equals(boolean.class))
			{
				return "is" + getPostfixName();
			}
			else
			{
				return "get" + getPostfixName();
			}
		}
		return readMethod.getName();
	}
	
	public void defaultValue(String valueStr)
	{
		this.defaultValue = valueStr;
	}
	
	@Override
	public String template() {
		StringBuilder builder = new StringBuilder();
	    builder.append(fieldTemplate()).append(writeMethodTemplate()).append(readMethdTemplate());
	    return builder.toString();
	}
	
	@Override
	public JSONObject toJson()
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("classify", "field");
			if(fieldName != null)
			{
				obj.put("name", fieldName);
			}
			if(ruleName != null)
			{
				obj.put("rule", this.ruleName);
			}
			if(bindDomain != null)
			{
				obj.put("bindDomain", this.bindDomain);
			}
			if(fieldType != null)
			{
				obj.put("type", fieldType.getName());
			}
			if(readMethod != null)
			{
				obj.put("readMethod", readMethod.getName());
			}
			if(writeMethod != null)
			{
				obj.put("writeMethod", writeMethod.getName());
			}
			if(defaultValue != null)
			{
				obj.put("defaultValue", defaultValue);
			}
			if(genericType != null)
			{
				obj.put("genericType", genericType.getName());
			}
			
		} catch (JSONException e) {
			Logger.get().error(fieldName + " toJson error!", e);
		}
		return obj;
	}
    public String getFieldName() {
        return fieldName;
    }
    public String getDerivativeVariableName() {
        return derivativeVariableName;
    }
    public void setDerivativeVariableName(String derivativeVariableName) {
        this.derivativeVariableName = derivativeVariableName;
    }
}
