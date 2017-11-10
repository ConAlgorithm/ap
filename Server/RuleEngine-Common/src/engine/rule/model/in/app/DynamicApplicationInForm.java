package engine.rule.model.in.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.rule.model.BaseForm;

@ModelInstance(description = "动态业务申请材料")
public class DynamicApplicationInForm extends BaseForm {

	private Map<String, Object> values = new HashMap<>();

	@ModelMethod(name = "(this)中存在变量(#1,<变量名>)")
	public boolean hasVariable(String variable) {
		Object val = values.get(variable);
		return val != null;
	}

	@ModelMethod(name = "(this)的整数变量(#1,<变量名>)的值")
	public Integer getIntegerValue(String variable) {
		return (Integer) getValue(variable, Integer.class);
	}

	@ModelMethod(name = "(this)的布尔变量(#1,<变量名>)的值")
	public Boolean getBooleanValue(String variable) {
		return (Boolean) getValue(variable, Boolean.class);
	}

	@ModelMethod(name = "(this)的字符串变量(#1,<变量名>)的值")
	public String getStringValue(String variable) {
		return (String) getValue(variable, String.class);
	}

	@ModelMethod(name = "(this)的日期时间变量(#1,<变量名>)的值")
	public Date getDatetimeValue(String variable) {
		return (Date) getValue(variable, Date.class);
	}

	@ModelMethod(name = "(this)的十进制小数变量(#1,<变量名>)的值")
	public BigDecimal getDecimalValue(String variable) {
		return (BigDecimal) getValue(variable, BigDecimal.class);
	}

	public void setValue(String variable, Object value) {
		values.put(variable, value);
	}

	// For HuaTeng compatible
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object getValue(String variable, Class expectedClass) {
		Object val = values.get(variable);
		if (val != null && expectedClass.isAssignableFrom(val.getClass())) {
			return val;
		}
		return null;
	}
}
