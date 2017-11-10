package fraudengine.rules;

import java.util.List;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.fraud.WXHeadPhotoCheckResult;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class A010 extends FraudRule {

	private static final String DEFAULT_NICK_NAME = "买单侠APP";
	
	private String DefaultWeiXinUserId;
	
	private String getDefaultWeixinUserId()
	{
		String sql = "select Id from WeiXinUserObjects where NickName = :NickName";
		return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("NickName", DEFAULT_NICK_NAME));
	}
	
  public A010(String id, String name, int score, String description) {
     super(id, name, score, description);
     try{
    	 DefaultWeiXinUserId = getDefaultWeixinUserId();
     }catch(Exception e)
     {
    	 Logger.get().error(String.format("Cannot Initialize the DefaultWeiXinUserId by default_nick_name= %s, the A010 will be ignored!",DEFAULT_NICK_NAME), e);
     }
     
  }

  @Override
  public CatfishEnum<Integer> run(String appID) {

    String sql = "select w.Id, w.HeadImageUrl from InstallmentApplicationObjects as i join "
        + "UserObjects as u on i.UserId=u.Id join WeiXinUserObjects as w on u.WeiXinUserId=w.Id where i.id=:AppId";
    List<String> weixinUser = DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appID), DatabaseExtractors.TWO_STRING_EXTRACTOR);
    String id = weixinUser.get(0);
    String headImageUrl = weixinUser.get(1);
    if(id.equals(DefaultWeiXinUserId))
    {
    	return WXHeadPhotoCheckResult.NotSure;
    }else if(StringUtils.isNullOrWhiteSpaces(headImageUrl)){
    	return WXHeadPhotoCheckResult.NoHeadPhoto;
    }else
    	return WXHeadPhotoCheckResult.HasHeadPhoto;
  }

}
