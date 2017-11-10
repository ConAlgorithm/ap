package engine.rule.analyzer.model.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import catfish.base.Logger;

public class ClassEntity extends Entity{

	private Class cls;
	private String packageName;
	private String className;
	
	public ClassEntity(Class cls, String ruleName)
	{
		super(ruleName);
		this.cls = cls;
		this.packageName = initPackageName();
		this.className = initClassName();
	}
	
	private String initPackageName()
	{
		String name = cls.getName();
		System.out.println(name);
		return name.substring(0, name.lastIndexOf('.'));
	}
	
	private String initClassName()
	{
		return cls.getSimpleName();
	}
	
	@Override
	public String template() {
		StringBuilder builder = new StringBuilder();
		builder.append("package\t").append(packageName).append(";\n");
		builder.append("public class\t").append(className).append("{\n");
		
		for(Entity entity: innerEntities)
		{
			builder.append(entity.template());
		}
		
		builder.append("}");
		
		return builder.toString();
	}

	@Override
	public JSONObject toJson()
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("classify", "class");
			if(cls != null)
			{
				obj.put("name", cls.getName());
			}
			if(ruleName != null)
			{
				obj.put("rule", this.ruleName);
			}
			JSONArray array = new JSONArray();
			for(Entity entity : innerEntities)
			{
				array.put(entity.toJson());
			}
			obj.put("members", array);
		} catch (JSONException e) {
			Logger.get().error(cls.getName() + " toJson error!", e);
		}
		return obj;
	}
	public Class getCls() {
		return cls;
	}
}
