package engine.rule.domain.adapter;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import catfish.base.DescriptionParser;
import catfish.base.business.common.CatfishEnum;

import com.huateng.toprules.core.adapter.DynamicDomainAdapter;
import com.huateng.toprules.core.model.bean.DynamicDomain;
import com.huateng.toprules.core.model.bean.DynamicDomainAttribute;

public class FixedDomainFieldsAdapter implements DynamicDomainAdapter {

	@Override
	public DynamicDomain execute(String domainName, String adapterClass,
			Object[] params) {
		String fieldsClassName = (String) params[0];
		List<DynamicDomainAttribute> attributes = new LinkedList<DynamicDomainAttribute>();
		try {
			Class<?> fieldsClassType = Class.forName(fieldsClassName);
			Field[] fields = fieldsClassType.getFields();
			for (Field item : fields) {
				if (DescriptionParser.isHidden(item))
					continue;
				String desc = DescriptionParser.getDescription(item);
				@SuppressWarnings("rawtypes")
				String value = ((CatfishEnum) item.get(null)).getValue()
						.toString();
				DynamicDomainAttribute attribute = new DynamicDomainAttribute(
						item.getName(), (desc == null ? value : desc), value);
				attributes.add(attribute);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DynamicDomain domain = new DynamicDomain(domainName, attributes,
				TYPE.STRING.toString(), adapterClass);
		return domain;
	}

}
