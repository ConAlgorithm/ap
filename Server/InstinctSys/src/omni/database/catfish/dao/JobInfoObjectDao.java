package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.JobInfoObject;
import omni.database.catfish.object.hybrid.AppJobInfoObject;

public interface JobInfoObjectDao 
{
	
	JobInfoObject getJobInfoObjectById(String appId);
	
	HashMap<String, AppJobInfoObject> getMassiveAppJobInfoById(List<String> appIds);

}
