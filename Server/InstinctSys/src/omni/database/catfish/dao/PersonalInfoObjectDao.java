package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.PersonInfoObject;
import omni.database.catfish.object.hybrid.AppPersonalInfoObject;

public interface PersonalInfoObjectDao 
{
	
	PersonInfoObject getPersonalInfoObjectById(String appId);

	HashMap<String, AppPersonalInfoObject> getMassiveAppPersonalInfoById(List<String> appIds);

}
