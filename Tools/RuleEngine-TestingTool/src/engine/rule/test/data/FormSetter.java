package engine.rule.test.data;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import catfish.base.Logger;
import engine.exception.JavaBeanParseException;
import engine.rule.model.BaseForm;
import engine.rule.model.ModelFieldOfDB;
import engine.rule.model.annotation.AnnotationManager;
import engine.rule.test.util.AnnotationManagerTestTool;
import engine.util.JavaBeanUtil;

public class FormSetter {
	private Map<String, Method> fieldSetters;
	private BaseForm form;
	
	private Map<String, Method> getSetters(Class<? extends BaseForm> formClass)
	{
		Map<String, Method> map = new HashMap<>();
		Field[] fields = formClass.getDeclaredFields();
		for(Field item : fields)
		{
			try {
				PropertyDescriptor reader = new PropertyDescriptor(item.getName(), formClass);
				map.put(item.getName(), reader.getWriteMethod());
			} catch (IntrospectionException e) {
				Logger.get().error(e);
			}
		}
		return map;
	}
	
	private BaseForm getForm(Class<? extends BaseForm> formClass)
	{
		BaseForm form = null;
		try {
			form = formClass.newInstance();
		} catch (Exception e){
			Logger.get().error(e);
		}
		return form;
	}
	public FormSetter(Class<? extends BaseForm> formClass){
		this.fieldSetters = getSetters(formClass);
		this.form = getForm(formClass);
	}

	public boolean fill(Map<String, String> columnNameValueMappings) {
		boolean result = false;

		Map<String, ModelFieldOfDB> map = AnnotationManagerTestTool.getModelDBFieldsMap(form.getClass());
		for (Map.Entry<String,ModelFieldOfDB> e : map.entrySet()) {
			Field f = e.getValue().getModelField();
			if(columnNameValueMappings.containsKey(f.getName())) {
				try {
					JavaBeanUtil.fill(form, f, columnNameValueMappings.get(f.getName()));
				} catch (JavaBeanParseException e1) {
					Logger.get().error( String.format( "Parse column "+f.getName()+" error in columnNameValueMappings")+ e);
				}
				result = true;
			}
		}
		
		List<Field> withoutDbFieldlist = AnnotationManagerTestTool.getWithoutFieldsList(form.getClass());
		for (Field field : withoutDbFieldlist) {
			if(columnNameValueMappings.containsKey(field.getName())) {
				try {
					JavaBeanUtil.fill(form, field, columnNameValueMappings.get(field.getName()));
				} catch (JavaBeanParseException e1) {
					Logger.get().error( String.format( "Parse column "+field.getName()+" error in columnNameValueMappings")+ e1);
				}
			}
		}
		return result;
	}

	public boolean fillForm(Map<String, String> columnNameValueMappings){	
		boolean result = false;
		for(Entry<String, String> entry : columnNameValueMappings.entrySet()){
			result |= setForm(entry.getKey(), entry.getValue());
		}		
		return result;
	}
	
	private boolean setForm(String columnName, String value){
		Method method;
		try {			
			method = this.fieldSetters.get(columnName);
			if(method == null)
				return false;
			Object obj = convert(method.getParameterTypes()[0], value);
		    method.invoke(form, obj);
		} catch (Exception e) {
			Logger.get().error(e);
			return false;
		}	
		return true;
	}
	
	private static Object convert(Class<?> classType, String value){
		if(classType.equals(Integer.class) | classType.equals(int.class)){
			return new Integer(value);
		}
		else if(classType.equals(String.class)){
			return value;
		}
		else if(classType.equals(BigDecimal.class)){
			return new BigDecimal(value);
		}
		else if(classType.equals(Boolean.class) | classType.equals(boolean.class)){
			return new Boolean(value);
		}else if(classType.equals(Double.class)){
			return new Double(value);
		}else if(classType.equals(Date.class)){
		
			int count = value.split(":").length;
			DateTimeFormatter format = null;
			if(count == 1){
				if(!value.contains(" "))
				   format = DateTimeFormat.forPattern("yyyy-MM-dd");
				else
				   format = DateTimeFormat.forPattern("yyyy-MM-dd HH");
			}else if(count == 2){
				   format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"); 
			}else{
				format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"); 
			}
			return DateTime.parse(value).toDate();
		}		
	    throw new IllegalArgumentException("Invalid class type");
	}
	
	public BaseForm getForm(){
		return form;
	}
}
