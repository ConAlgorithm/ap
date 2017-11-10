package fraudengine.rules;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.util.CommonUtil;
import catfish.base.database.DatabaseApi;


public class A014 extends FraudRule{
  public A014(String id, String name, int score, String description) {
    super(id, name, score, description);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public CatfishEnum<Integer> run(String appID){
    List<List<String>> allContactInfoList = getAllContactInfoByAppID(appID);
    
    ///遍历二维数组
    for(List<String> contactInfoList : allContactInfoList){
      ///找到需要进行判断的联系人信息
      if(!appID.toUpperCase().equals(contactInfoList.get(3))){   
        List<String> contactInfoWithSamePhone = 
            getContactInfoWithSamePhone(allContactInfoList, contactInfoList, appID);
        if(contactInfoList.get(2) != null){
          if(!CommonUtil.isSimilarUserName(contactInfoList.get(2), contactInfoWithSamePhone.get(2))){
            return FraudRuleResultStatus.Fail;
          }
        }
        else
        {
          String appName = getAppNameByContactId(contactInfoList.get(0));
          ///如果申请人的申请信息过少，无法查询IDName，故使用null，默认为通过 
          if(catfish.base.StringUtils.isNullOrWhiteSpaces(appName)){
            continue;
          }
          else if(!CommonUtil.isSimilarUserName(
              appName, contactInfoWithSamePhone.get(2))){
            return FraudRuleResultStatus.Fail;
          }
        }
      }
    }
    return FraudRuleResultStatus.Pass;
  }
  
  /**
   * 在所有联系信息列表中查找该申请，而且手机号相同的列表信息,正常不会返回null
   * @param compareList
   * @param list
   * @param appID
   * @return
   */
  private List<String> getContactInfoWithSamePhone(
      List<List<String>> originalList, List<String> targetList, String appID){
    for(List<String> original : originalList){
      if(appID.toUpperCase().equals(original.get(3))){   
        if(original.get(1).equals(targetList.get(1))){
          return original;
        }
      }
    }
    return null;
  }

  
  /**
   * 根据appId找到所有联系人手机号，查找使用该手机号的联系人信息
   * @param appId
   * @return CID，Phone，Name，AppID
   */
  private List<List<String>> getAllContactInfoByAppID(String appId){
    String sql = 
        "select co.Id as CID, co.Content as Phone, " +
        "cp.Name as Name, cp.AppId as AppID " +
        "from ContactObjects co " +
        "left join ContactPersonObjects cp on cp.MobileContactId = co.Id " +
        "where co.Content in " + 
        "(" +
            "select co.Content " +
            "from ContactPersonObjects cp " + 
            "inner join ContactObjects co on co.Id = cp.MobileContactId " + 
            "where cp.AppId = :AppID " +
            "and not exists " +
            "(" +
                "select * from ContactPersonObjects cp1 " +
                "where cp1.AppId = :AppID " +
                "and cp1.Name = cp.Name " +
                "and cp1.DateAdded > cp.DateAdded " +
            ")" +
        ")";
    
    RowMapper<List<String>> extractor = new RowMapper<List<String>>() {
      @Override
      public List<String> mapRow(ResultSet result, int rowIndex) throws SQLException {
        return Arrays.asList(
            result.getString("CID"),
            result.getString("Phone"),
            result.getString("Name"),
            result.getString("AppID"));
      }
    };
    
    return DatabaseApi.queryMultipleResults(
        sql, CollectionUtils.mapOf("AppID", appId), extractor);
  }
  
  /**
   * 根据ContactID找到找到申请人的姓名，如果无法查询到IDName，则使用null表示
   * @param ContactID
   * @return
   */
  private String getAppNameByContactId(String ContactID){
    String sql = 
        "select eu.IdName " +
        "from UserObjects u " +
        "inner join EndUserExtensionObjects eu on u.Id = eu.Id " +
        "where u.MobileContactId = :contactID ";
      
    return DatabaseApi.querySingleStringOrDefault(
        sql, CollectionUtils.mapOf("contactID", ContactID), null);
  }
}