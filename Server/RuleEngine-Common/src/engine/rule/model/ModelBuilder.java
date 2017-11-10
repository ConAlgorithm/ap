package engine.rule.model;

import java.util.List;

import engine.databaseapi.DerivativeVariableApi;
import engine.exception.DBFieldAdapterException;
import engine.rule.model.adapter.DBFieldAdapter;
import engine.rule.model.annotation.AnnotationManager;
import engine.util.JavaBeanUtil;

public class ModelBuilder<T extends BaseForm> {

	private T form;

	public ModelBuilder(T form) {
		this.form = form;
	}

	public ModelBuilder<T> buidDerivativeVariables(String appId) {
		DerivativeVariableApi.buildForm(form, appId);
		return this;
	}

	// TODO! not implemented yet!
	public ModelBuilder<T> buildNormalFields(String appId,
			Object... extraParams) {
		return null;
	}

	public ModelBuilder<T> buildModelFieldsByAdapter(String adapterName,
			Object... params) throws DBFieldAdapterException {
		try {
			List<ModelFieldOfDB> list = AnnotationManager
					.getModelDBFieldsList(form.getClass());
			for (ModelFieldOfDB item : list) {
				if (item.getDbField().bindAdapter().equals(adapterName)) {
					Class<?> adapterClass = Class.forName(adapterName);
					DBFieldAdapter adapter = (DBFieldAdapter) adapterClass
							.newInstance();
					JavaBeanUtil.fill(form, item.getModelField(),
							adapter.execute(params));
					break;
				}
			}
			return this;
		} catch (Exception e) {
			throw new DBFieldAdapterException(adapterName, e);
		}
	}

	public T getForm() {
		return this.form;
	}
}
