package engine.rule.test.model;

import java.lang.reflect.Method;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import engine.rule.model.creator.AbstractModelCreator;

public class ModelCreator extends  AbstractModelCreator{

	private String ruleFlag;
	private String modelName;
	
	public ModelCreator(String ruleFlag, String modelName)
	{
	    this.ruleFlag = ruleFlag;
	    this.modelName = modelName;
	}
	
	@Override
	public Map<String, Object> createModelForm() {
		Map<String, Object> outParams = CollectionUtils
				.<String, Object> newMapBuilder().build();
		try {
			Class<?> cls = ModelCreator.class.getClassLoader().loadClass(modelName);
			Object form = cls.newInstance();
			
			Method m = form.getClass().getMethod("setInput", new Class[]{Integer.class}); 
	        m.invoke(form, new Object[]{1}); 
	        
			outParams.put(ruleFlag, form);  
			
		} catch (Exception e) {
			Logger.get().error("Create Model: " + modelName + " failed!", e);
		}	
		return outParams;
	}

	@Override
	public String createBusinessNo() {
		return null;
	}
}
