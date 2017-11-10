package network.relationship.api;

import network.relationship.businessdomain.GroupInfo;

public interface IStorage {
	void save(String appId, GroupInfo groupInfo);
}
