package catfish.bonuspoints.rank;

import java.util.Map;

import com.google.gson.Gson;

import catfish.framework.IListener;
import catfish.framework.http.HttpData;

public class RankListener implements IListener<HttpData>{
	
	private RankService rankService;
	
	public RankListener(RankService rankService) {
		this.rankService = rankService;
	}
	
	@Override
	public void onMessage(String message, HttpData data) {
	    @SuppressWarnings("unchecked") Map<String, Object> rankRequest =
	            (Map<String, Object>) new Gson().fromJson(data.getRequestData(), Map.class);
	    
	    String userId = (String) rankRequest.get("UserId");
	    if (userId == null) {
	    	RankResponse error = RankResponse.getErrorResponse("UserId error!");
	    	data.setResponseData(new Gson().toJson(error));
	    	return;
	    }
	    
	    Boolean cityWeek = (Boolean) rankRequest.get("CityWeek");
	    if (cityWeek != null && cityWeek) {
	    	Integer myRank = rankService.getMyRankOfCityThisWeek(userId);
	    	if (myRank != null)
	    		data.setResponseData(myRank.toString());
	    	else
	    		data.setResponseData("-");
	    	return;
	    }
	    
	    String timeRange = (String) rankRequest.get("TimeRange");
	    if (timeRange == null || (!timeRange.equalsIgnoreCase("Week") && !timeRange.equalsIgnoreCase("History"))) {
	    	RankResponse error = RankResponse.getErrorResponse("TimeRange error!");
	    	data.setResponseData(new Gson().toJson(error));
	    	return;
	    }
	    
	    String territoryRange = (String) rankRequest.get("TerritoryRange");
	    if (territoryRange == null || 
	    	(!territoryRange.equalsIgnoreCase("Area") && !territoryRange.equalsIgnoreCase("City") && !territoryRange.equalsIgnoreCase("Province"))) {
	    	RankResponse error = RankResponse.getErrorResponse("TerritoryRange error!");
	    	data.setResponseData(new Gson().toJson(error));
	    	return;
	    }
	    

	    
	    RankResponse responseObject;
	    responseObject = rankService.getRankResult(userId, timeRange, territoryRange);
	    
	    data.setResponseData(new Gson().toJson(responseObject));
	}
}
