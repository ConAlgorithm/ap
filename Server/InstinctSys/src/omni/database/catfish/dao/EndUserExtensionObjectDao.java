package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import omni.database.catfish.object.EndUserExtensionObject;
import omni.database.catfish.object.hybrid.AppEndUserExtensionObject;

public interface EndUserExtensionObjectDao 
{
	
	EndUserExtensionObject getEndUserExtensionObjectByUserId(String userId);

	HashMap<String, AppEndUserExtensionObject> getMassiveAppEndUserExtensionById(List<String> appIds);

}
