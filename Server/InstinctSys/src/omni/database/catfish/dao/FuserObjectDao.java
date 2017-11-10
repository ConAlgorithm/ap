package omni.database.catfish.dao;

import java.util.List;
import java.util.Map;
import omni.database.catfish.object.hybrid.AppFuserObject;

public interface FuserObjectDao 
{
	
	Map<String, AppFuserObject> getMassiveAppFuserById(List<String> appIds);
}
