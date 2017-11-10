package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import omni.database.catfish.object.InstalmentPurposeObject;
import omni.database.catfish.object.hybrid.AppPurposeObject;

public interface InstallmentPurposeObjectDao 
{
	
	InstalmentPurposeObject getInstalmentPurposeObjectById(String id);
	
	HashMap<String, AppPurposeObject> getMassiveAppPurposeById(List<String> appIds);
	
}
