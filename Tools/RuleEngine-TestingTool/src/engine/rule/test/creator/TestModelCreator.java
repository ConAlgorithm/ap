package engine.rule.test.creator;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.BaseForm;
import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.test.data.FormSetter;

public abstract class TestModelCreator extends AbstractModelCreator {

	private String key;
	protected Map<String, String> columnNameValueMappings;
	private FormSetter setter;
    public boolean fillResult;
    
	public TestModelCreator(Class<? extends BaseForm> formType, String key, Map<String, String> columnNameValueMappings) {
		this.key = key;
		this.columnNameValueMappings = columnNameValueMappings;
		this.setter = new FormSetter(formType);
	}
	
	@Override
	public Map<String, Object> createModelForm() {
	
		fillResult = setter.fill(columnNameValueMappings);	
		fillResult |= setSpecificFormValue(setter.getForm());
		this.form = setter.getForm();
		Map<String, Object> in = CollectionUtils.mapOf(this.key, (Object)form);
		return in;
	}
	
	protected abstract boolean setSpecificFormValue(BaseForm form);
	
	@Override
	public String createBusinessNo() {
		return null;
	}
	
	public boolean getFillResult()
	{
		return fillResult;
	}
}
