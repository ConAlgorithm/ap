package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.ContactPersonObject;
import omni.database.catfish.object.hybrid.AppContactPersonObject;

public interface ContactPersonObjectDao 
{
	
	ContactPersonObject getContactPersonObjectById(String contactPersonId);

	String getContactPersonTypeById(String contactPersonId);

//	int getNumOfContactByAppId(String appId);

	List<String> getListOfContactPersonIdByAppId(String appId);

	HashMap<String, List<AppContactPersonObject>> getMassiveAppContactPersonById(List<String> appIds);

}
