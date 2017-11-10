package omni.database.catfish.dao;

import catfish.base.business.object.WeiXinUserObject;

public interface WeiXinUserObjectDao 
{
	
	WeiXinUserObject getWeiXinUserObjectById(String weiXinUserId);
	
}
