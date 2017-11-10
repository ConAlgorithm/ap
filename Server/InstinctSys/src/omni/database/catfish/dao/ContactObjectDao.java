package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.ContactObject;
import omni.database.catfish.object.hybrid.AppContactObject;

public interface ContactObjectDao 
{
	
	ContactObject getContactObjectById(String contactId);

	HashMap<String, AppContactObject> getMassiveAppContactById(List<String> appIds);

}
