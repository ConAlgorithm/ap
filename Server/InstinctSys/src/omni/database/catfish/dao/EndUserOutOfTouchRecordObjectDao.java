package omni.database.catfish.dao;

import omni.database.catfish.object.EndUserOutOfTouchRecordObject;

public interface EndUserOutOfTouchRecordObjectDao 
{
	
	EndUserOutOfTouchRecordObject getEndUserOutOfTouchRecordObjectByUserId(String userId);

}
