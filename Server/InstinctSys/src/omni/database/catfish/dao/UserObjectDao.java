package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.UserObject;
import omni.database.catfish.object.hybrid.AppUserObject;

public interface UserObjectDao 
{
	
	UserObject getUserObjectById(String userId);
	
	HashMap<String, AppUserObject> getMassiveAppUserById(List<String> appIds);

}
