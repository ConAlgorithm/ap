package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.InstalmentApplicationSnapObject;

public interface InstallmentApplicationObjectDao 
{
	
	InstallmentApplicationObject getInstallmentApplicationObjectById(String appId);

	HashMap<String, InstallmentApplicationObject> getMassiveInstallmentApplicationById(List<String> appIds);

	InstalmentApplicationSnapObject getInstalmentApplicationSnapObjectByAppId(String appId);

	HashMap<String, InstalmentApplicationSnapObject> getMassiveInstalmentApplicationSnapById(List<String> appIds);

	List<String> getPOSAppIds(int numOfApps);

	List<String> getPOSAppIds(int yearOfData, int monthOfData);

	List<String> getAppIds(int numOfApps);

	List<String> getAppIds(int yearOfData, int monthOfData);
	
	InstallmentApplicationObject getLastInstallmentApplicationObjectsById(String appId);

}
