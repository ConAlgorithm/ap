package network.relationship.api;

import com.google.gson.Gson;

import catfish.base.Logger;
import network.relationship.businessdomain.GroupInfo;

public class TextStorage implements IStorage{

	@Override
	public void save(String appId, GroupInfo groupInfo) {
		Logger.get().info(appId + ":" + new Gson().toJson(groupInfo));		
	}

}
