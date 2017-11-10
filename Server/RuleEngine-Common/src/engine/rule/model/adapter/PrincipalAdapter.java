package engine.rule.model.adapter;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.database.DatabaseApi;

import java.util.UUID;

public class PrincipalAdapter implements DBFieldAdapter {

	@Override
	public String execute(Object... args) {
		
		String appId = (String) args[0];
		
		//appId非空判断
		if(appId == null || "".equals(appId)){
		    return null;
		}
		//appId合法性校验
	    try {
	          UUID.fromString(appId);        
	    } catch (Exception e) {
	        Logger.get().error("The appId is not a uuid !", e);
	        return null;
	    }

		String sql = "SELECT Principal FROM InstallmentApplicationObjects WHERE Id = :AppId";

		return DatabaseApi.querySingleString(sql,
				CollectionUtils.mapOf("AppId", appId));
	}

}
