package engine.util;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariables;
import engine.databaseapi.DerivativeVariableApi;
import engine.exception.JavaBeanParseException;
import engine.rule.model.ModelFieldOfDB;
import org.joda.time.DateTime;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class JavaBeanUtil {

	public static void fill(Object bean, ModelFieldOfDB field,
			AppDerivativeVariables fromVariables) throws JavaBeanParseException {
		Class<?> acceptedType = field.getModelField().getType();
		String variableName = field.getDbField().variableType();

		Object value;
	
		switch (field.getDbField().fieldName()) {
		case DerivativeVariableApi.DecimalValue:
			if (acceptedType.equals(double.class)
					|| acceptedType.equals(Double.class)) {
				
				value = fromVariables.getAsDecimal(variableName).doubleValue();
			} else { // decimal
				value = fromVariables.getAsDecimal(variableName);
			}
			break;
		case DerivativeVariableApi.StringValue:
			// mismatch fields, e.g. X_InputIdCardInfoNationality
			if (acceptedType.equals(int.class)
					|| acceptedType.equals(Integer.class)) {
				value = Integer.parseInt(fromVariables
						.getAsString(variableName));
			} else {
				value = fromVariables.get(variableName);
			}
			break;
		default:
			value = fromVariables.get(variableName);
			break;
		}

		try {
			new PropertyDescriptor(field.getModelField().getName(),
					bean.getClass()).getWriteMethod().invoke(bean, value);
		} catch (IntrospectionException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
		    try {
		        Logger.get().warn(
                    String.format("first time methodField: %s invoke writeMethod failed,value: %s is not problem",
                        field.getModelField(), value.toString()));
		        fill(bean,field.getModelField(),value.toString());
		    } catch (Exception e2){
		        Logger.get().warn(
		        String.format("second time methodField: %s invoke writeMethod failed,value: %s is a problem",
                    field.getModelField(), value.toString()));
		        throw new JavaBeanParseException(e2);
		    }
		}
	}

	public static void fill(Object bean, Field field, String value)
			throws JavaBeanParseException {
		if (value == null || "null".equalsIgnoreCase(value))
			return;

		try {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
					bean.getClass());
			Method wM = pd.getWriteMethod();

			Object[] oo = new Object[1];
			Class<?> type = field.getType();
			if (type.equals(String.class)) {
				oo[0] = value;
			} else if (type.equals(int.class) || type.equals(Integer.class)) {
				if (value.length() > 0)
					oo[0] = Integer.valueOf(value);
			} else if (type.equals(float.class) || type.equals(Float.class)) {
				if (value.length() > 0)
					oo[0] = Float.valueOf(value);
			} else if (type.equals(double.class) || type.equals(Double.class)) {
				if (value.length() > 0)
					oo[0] = Double.valueOf(value);
			} else if (type.equals(BigDecimal.class)) {
				if (value.length() > 0)
					oo[0] = new BigDecimal(Double.valueOf(value));
			} else if (type.equals(Date.class)) {
				Date date = null;
				if (value.length() > 0) {
					try{
		        		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
			        	  date = sdf.parse(value);   
		        	  }catch(Exception e)
		        	  {
		        		  date = DateTime.parse(value).toDate();
		        	  }
				   oo[0] = date;
				}
			} else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
				if (value.length() > 0)
					oo[0] = Boolean.valueOf(value);
			} else if (type.equals(long.class) || type.equals(Long.class)) {
				if (value.length() > 0)
					oo[0] = Long.valueOf(value);
			} else if (Set.class.isAssignableFrom(type)) {
				Method method = findCollectionAdder(bean, field.getName());
				if (method != null) {
					wM = method;
					oo[0] = value;
				}
			}
			wM.invoke(bean, oo);
		} catch (Exception e) {
			throw new JavaBeanParseException(e);
		}
	}

	private static Method findCollectionAdder(Object bean, String fieldName) {
		int index = 0;
		while (fieldName.charAt(index) == '_') {
			index++;
		}
		String replace = fieldName.substring(index, index + 1);
		String target = fieldName.replaceFirst(replace, replace.toUpperCase());
		String methodName = String.format("add%s", target);
		Method method = null;
		try {
			method = bean.getClass()
					.getDeclaredMethod(methodName, Object.class);
		} catch (NoSuchMethodException e) {
			Logger.get().error(
					String.format("Add %s into colletion by %s error",
							fieldName, methodName), e);
		} catch (SecurityException e) {
			Logger.get().error(
					String.format("Add %s into colletion by %s error",
							fieldName, methodName), e);
		}
		return method;
	}

	public static Object get(Object bean, Field field)
			throws JavaBeanParseException {
		PropertyDescriptor pd;
		try {
			if (field.getType() == Map.class)
				return "";
			pd = new PropertyDescriptor(field.getName(), bean.getClass());
			Method wM = pd.getReadMethod();
			return wM.invoke(bean, null);
		} catch (Exception e) {
			throw new JavaBeanParseException(e);
		}
	}

	public static void tryFillAdditionalContactVariable(Object bean, int index,
			String variable, int value) throws JavaBeanParseException {
		try {
			Method method = bean.getClass().getDeclaredMethod(
					"setAdditionalContactVariable",
					new Class[] { Integer.class, String.class, Integer.class });
			method.invoke(bean, index, variable, value);
		} catch (Exception e) {
			throw new JavaBeanParseException(e);
		}
	}
}
