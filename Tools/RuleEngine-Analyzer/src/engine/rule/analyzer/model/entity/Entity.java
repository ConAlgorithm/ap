package engine.rule.analyzer.model.entity;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

public abstract class Entity{
	
	protected Set<Entity> innerEntities = new HashSet<>();
	
	protected Entity parent;
	
	protected String ruleName;
	
	public Entity(String ruleName)
	{
		this.ruleName = ruleName;
	}
	public abstract String template();
	
	public JSONObject toJson()
	{
		return new JSONObject();
	}
	
	public void addEntity(Entity innerEntity)
	{
		this.innerEntities.add(innerEntity);
		innerEntity.parent = this;
	}
	
	public Set<Entity> getInnerEntities() {
		return innerEntities;
	}
	public Entity getParent() {
		return parent;
	}
	public String getRuleName() {
		return ruleName;
	}
	
}
