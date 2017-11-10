package fraudengine.rules;
import java.util.List;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class B004 extends FraudRule{

  public B004(String id, String name, int score, String description) {
    super(id, name, score, description);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public CatfishEnum<Integer> run(String appID){
    
    List<String> allContactPhoneList = getAllContactPhoneList(appID);
    List<String> twoDayApplication = getTwoDayAppSameMerchantStoreByAppId(appID);
    if(!twoDayApplication.isEmpty()){
      List<String> twoDayPhoneList = getTwoDayPhoneListByAppIDList(twoDayApplication);
      
      for(String phone : allContactPhoneList){
        for(String sameStorePhone : twoDayPhoneList){
          if(phone.subSequence(0, 7).equals(sameStorePhone.subSequence(0, 7)) 
              && !phone.subSequence(7, 11).equals(sameStorePhone.subSequence(7,11))){
            return FraudRuleResultStatus.Fail;
          }
        }
      }
    }
    return FraudRuleResultStatus.Pass;
  }
  
  /**
   * 根据appID查找该订单所有联系人包括本人的手机号
   * @param appID
   * @return
   */
  private List<String> getAllContactPhoneList(String appID){
    String sql = 
        "select co.Content " +
        "from ContactObjects co " + 
        "where co.Id in " +
        "(" +
          "select cp.MobileContactId " +  
          "from ContactPersonObjects cp " + 
          "where cp.AppId = :appID " +
          "and not exists " +
          "( " +
            "select * from ContactPersonObjects cp1 " +
            "where cp1.AppId = :appID and cp1.Name = cp.Name " +
            " and cp1.DateAdded > cp.DateAdded " +
          ") " +
          "union " +
          "select u.MobileContactId " +  
          "from InstallmentApplicationObjects app " +  
          "inner join UserObjects u on u.Id = app.UserId where app.Id = :appID " +
        ")";
    
    return DatabaseApi.queryMultipleResults(
        sql, CollectionUtils.mapOf("appID", appID), DatabaseExtractors.STRING_EXTRACTOR);
    
  }
  
  /**
   * 根据appID找到所属的商户，查找该商户2天内所有的AppID
   * @param appID
   * @return
   */
  private List<String> getTwoDayAppSameMerchantStoreByAppId(String appID){
    String sqlForAppInfo = 
        "select app.MerchantStoreId, app.DateAdded from InstallmentApplicationObjects app " +
        "where app.Id = :appID ";
    List<List<String>> appInfo = DatabaseApi.queryMultipleResults(
        sqlForAppInfo, CollectionUtils.mapOf("appID", appID), DatabaseExtractors.TWO_STRING_EXTRACTOR);

    String sqlForAppId = 
        "select app.Id from InstallmentApplicationObjects app " + 
        "where app.MerchantStoreId = :MSID " +
        "and DATEDIFF(HOUR, app.DateAdded, :DATE) < 48 " +
        "and app.Id != :appID and app.DateAdded < :DATE ";
    return DatabaseApi.queryMultipleResults(
        sqlForAppId, CollectionUtils.mapOf(
            "MSID",appInfo.get(0).get(0),"DATE",appInfo.get(0).get(1), "appID", appID), 
            DatabaseExtractors.STRING_EXTRACTOR);
  }
  
  /**
   * 根据订单列表查找每一个订单的联系人包括本人的手机号
   * @param appList
   * @return 
   */
  private List<String> getTwoDayPhoneListByAppIDList(List<String> appList){
    String sql = 
        "select distinct(co.Content) " +
        "from ContactObjects co " + 
        "where co.Id in " +
        "(" +
          "select cp.MobileContactId " +  
          "from ContactPersonObjects cp " + 
          "where cp.AppId in (:appList) " +
          "and not exists " +
          "( " +
            "select * from ContactPersonObjects cp1 " +
            "where cp1.AppId in (:appList) and cp1.Name = cp.Name " +
            " and cp1.DateAdded > cp.DateAdded " +
          ") " +
          "union " +
          "select u.MobileContactId " +  
          "from InstallmentApplicationObjects app " +  
          "inner join UserObjects u on u.Id = app.UserId where app.Id in (:appList) " +
        ")";
    return DatabaseApi.queryMultipleResults(
        sql, CollectionUtils.mapOf("appList", appList), DatabaseExtractors.STRING_EXTRACTOR);
  }
  
}