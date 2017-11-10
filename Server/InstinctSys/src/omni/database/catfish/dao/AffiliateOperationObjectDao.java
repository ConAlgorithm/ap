package omni.database.catfish.dao;

public interface AffiliateOperationObjectDao 
{
	
//	AffiliateOperationType getAffiliateOperationTypebyAppId(String appId);

	Boolean getIsRecommendedbyAppId(String appId);
	Boolean getIsReportedbyAppId(String appId);
}
