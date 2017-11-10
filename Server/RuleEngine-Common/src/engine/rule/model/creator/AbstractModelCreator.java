package engine.rule.model.creator;

import java.util.Map;

import engine.rule.model.BaseForm;

public abstract class AbstractModelCreator {

	protected BaseForm form;

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public abstract Map<String, Object> createModelForm();

	public abstract String createBusinessNo();

	// public abstract void addCreator(AbstractModelCreator creator);
	//
	// public abstract void removeCreator(AbstractModelCreator creator);
}
