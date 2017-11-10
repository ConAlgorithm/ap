package jma.dataservice;

import catfish.base.CollectionUtils;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;

public class UserDataService {
	public static String getIdNumber(String userId) {
		return getEndUserExtensionObject(userId).IdNumber;
	}
	
	private static EndUserExtensionObject getEndUserExtensionObject(String userId){
		Dao<EndUserExtensionObject> dao = Dao.create(EndUserExtensionObject.class, DatabaseApi.database);
		return dao.getSingle("SELECT * FROM EndUserExtensionObjects WHERE Id = :Id", CollectionUtils.mapOf("Id", userId));
	}
}
