package engine.rule.test.creator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import engine.rule.model.creator.AbstractModelCreator;


public class CompositeModelCreator extends AbstractModelCreator {

	private Set<AbstractModelCreator> creatorList = new HashSet<AbstractModelCreator>();

	@Override
	public Map<String, Object> createModelForm() {
		Map<String, Object> params = CollectionUtils
				.<String, Object> newMapBuilder().build();
		for (AbstractModelCreator creator : creatorList) {
			Map<String, Object> param = creator.createModelForm();
			System.out.println(new Gson().toJson(param));
			if (param != null)
				params.putAll(param);
		}
		return params;
	}

	@Override
	public String createBusinessNo() {
		return null;
	}

	public void addCreator(AbstractModelCreator creator) {
		if (creatorList.contains(creator))
			throw new RuntimeException(String.format(
					"the model creator %s already exists!", creator.getClass()
							.getName()));
		creatorList.add(creator);
	}

	public void removeCreator(AbstractModelCreator creator) {
		creatorList.remove(creator);
	}
}
