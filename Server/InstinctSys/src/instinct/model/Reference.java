package instinct.model;

import java.util.ArrayList;

public class Reference 
{

	public ArrayList<SubReference> subReferences = new ArrayList<>();
	
	public Reference(omni.model.Reference posref)
	{
		posref.getReferences().forEach(subRef->subReferences.add(new SubReference(subRef)));
	}
}
