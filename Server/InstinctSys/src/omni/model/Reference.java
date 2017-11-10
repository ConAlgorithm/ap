package omni.model;

import java.util.ArrayList;
import java.util.List;

import omni.database.DataContainer;
import omni.database.catfish.object.hybrid.AppContactPersonObject;
import omni.database.mongo.DerivativeVariables;

public class Reference 
{
	
	private ArrayList<SubReference> subReferences = new ArrayList<>();
	
	/**
	 * This constructor generates information for preCheck phase.
	 *  
	 * @see AppContactPersonObject
	 * @param id appId
	 * @param instCall instinctCall
	 * 
	 */
	public Reference(String id, String instCall)
	{
		List<AppContactPersonObject> cpObj = DataContainer.conPerObj.get(id);
		if ("precheck".equalsIgnoreCase(instCall))
		{
			cpObj.forEach(conPerObj-> subReferences.add(new SubReference(conPerObj)));
		}
		else
		{
			this.initialize(id);
		}
	}
	
	public Reference(String appId)
	{
		this(appId, "");
	}
	
	private void initialize(String appId)
	{
		List<AppContactPersonObject> cpObj = DataContainer.conPerObj.get(appId);
		DerivativeVariables mongodv = DataContainer.mongodv.get(appId);	
		cpObj.forEach(conPerObj-> subReferences.add(new SubReference(appId, conPerObj, mongodv)));
	}
	
	public final ArrayList<SubReference> getReferences()
	{
		return this.subReferences;
	}
	
}