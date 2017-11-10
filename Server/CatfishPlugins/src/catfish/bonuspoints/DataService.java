package catfish.bonuspoints;

import java.lang.reflect.Array;
import java.util.*;

import catfish.base.CollectionUtils;
import catfish.base.business.object.BonusPointsObject;
import catfish.base.collections.MapBuilder;
import catfish.base.database.DatabaseApi;

public class DataService {
	public boolean savePoint(List<BonusPointsObject> pointList){
		String sql =
		    "insert into BonusPointsObjects ( "
	        + "Point, UserId, RuleId, SourceId, SourceType, SelfRefId, DateAdded, LastModified) "
	        + "values "
	        + "(:Point, :UserId, :RuleId, :SourceId, :SourceType, :SelfRefId, getdate(), getdate())";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> params = null;
	    for(BonusPointsObject pointsObject : pointList){
	    	params = new MapBuilder<String, Object>()
	            .add("SourceId", pointsObject.SourceId)
	            .add("SourceType", pointsObject.SourceType)
	            .add("Point", pointsObject.Point)
	            .add("UserId", pointsObject.UserId)
	            .add("RuleId", pointsObject.RuleId)
	            .add("SelfRefId", pointsObject.SelfRefId)
	            .build();
	    	list.add(params);
	    }
	    if(list.size() > 0 && params != null){
	        @SuppressWarnings("unchecked")
            Map<String, Object>[] paramsArray = (Map<String, Object>[])Array.newInstance(params.getClass(), list.size());
		    DatabaseApi.batchUpdateTable(sql, list.toArray(paramsArray));
	    }
		return true;
	}

	public String getS1Id(String appId){
		String sql =
	        "SELECT InstalmentApplicationSnapObjects.S1Id " +
	        "FROM InstalmentApplicationSnapObjects " +
	        "WHERE InstalmentApplicationSnapObjects.InstalmentAppId = :AppId";

	    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
	}
}
