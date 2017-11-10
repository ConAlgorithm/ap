package catfish.notification.messagegeneration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.AbstractDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.UploadFileApi;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.collections.MapBuilder;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;
import catfish.notification.object.AppMessageObject;

public class MessageDatabaseApi {

  public static class ContactType {
    public static final int MOBILE = 2;
  }
  
  public static List<String> getReuploadFileSendWordsByUploadFileStatus(int needReupload, String appId) {
    return UploadFileApi.getReuploadFileSendWordsByUploadFileStatus(needReupload, appId);
  }

  public static void updateDailyShortMsgRecord(String message, String recordId) {
    String sql = "update DailyShortMessageRecordObjects "
      + "set Content = :message, Result = 'true', LastModified = :modifiedDate, "
      + "SendDate = :sendDate where Id = :RecordId ";
    DatabaseApi.updateTable(sql,
      CollectionUtils.mapOf("message", message, "RecordId", recordId,
          "modifiedDate", new java.util.Date(), "sendDate", new java.util.Date()));
  }
  public static Map<String, String> getApplicationInfo(Map<String, ?> receivedMessage) {
    String sql = "select eu.IdName as Name, c.Content as phone, app.Principal, app.UserId,"
        + "app.InstallmentStartedOn as StartDate, p.BankName, cna.Name as Area, "
        + "app.Status, dsm.Type, app.MonthlyPaybackDay as PayDay "
        + "from DailyShortMessageRecordObjects dsm "
        + "inner join InstallmentApplicationObjects app on app.Id = dsm.AppId "
        + "inner join UserObjects u on u.Id = app.UserId "
        + "inner join EndUserExtensionObjects eu on eu.Id = app.UserId "
        + "inner join ContactObjects c on c.Id = u.MobileContactId "
        + "inner join PaymentApplicationObjects pa on pa.ApplicationId = app.Id "
        + "inner join PaymentObjects p on p.Id = pa.PaymentId "
        + "inner join MerchantStoreObjects ms on ms.Id = app.MerchantStoreId "
        + "inner join CNAreaObjects cn on cn.Id = ms.CNAreaId "
        + "inner join CNAreaObjects cna on cna.Code = cn.ParentCode "
        + "where dsm.Id = :RecordId ";
    RowMapper<Map<String, String>> extractor = new RowMapper<Map<String, String>>() {
      @SuppressWarnings("serial")
      @Override
      public Map<String, String> mapRow(final ResultSet arg0, int arg1)
          throws SQLException {
        return new HashMap<String, String>(){
          {
            put("UserId", arg0.getString("UserId"));
            put("Name", arg0.getString("Name"));
            put("Phone", arg0.getString("Phone"));
            put("Principal", arg0.getString("Principal"));
            put("StartDate", arg0.getString("StartDate"));
            put("BankName", arg0.getString("BankName"));
            put("Area", arg0.getString("Area"));
            put("Status", arg0.getString("Status"));
            put("Type", arg0.getString("Type"));
            put("PayDay", arg0.getString("PayDay"));
          }
        };
      }
    };
    Map<String, String> returnMap = DatabaseApi.querySingleResult(sql, receivedMessage, extractor);
    if(returnMap == null){
      returnMap = new HashMap<String, String>();
    }
    return returnMap;
  }
  
  public static InstallmentApplicationObject getInstallmentApplicationById(String appId){
    return InstallmentApplicationDao.getInstallmentApplicationById(appId);
  }
  public static String toSpaceSeparatedString(List<String> userWordsList) {
    return UploadFileApi.toSpaceSeparatedString(userWordsList);
  }
  
  public static List<String> getHeadOrIdOrBankCardWords(int uploadStatus) {
    return UploadFileApi.getHeadOrIdOrBankCardWords(uploadStatus);
  }
  
  public static boolean isReupload(int uploadStatus) {
    return UploadFileApi.isReupload(uploadStatus);
  }
  
  public static boolean isHeadOrIdOrBankCardNeeded(int uploadStatus) {
    return UploadFileApi.isHeadOrIdOrBankCardNeeded(uploadStatus);
  }
  
  public static boolean isIOUNeeded(int uploadStatus) {
    return UploadFileApi.isIOUNeeded(uploadStatus);
  }
  
  public static boolean isBuckleNeeded(int uploadStatus) {
    return UploadFileApi.isBuckleNeeded(uploadStatus);
  }
  
  public static List<String> getAppBasicInfo(String appId) {
    String sql =
        "SELECT DATEDIFF(D, GETDATE(), I.FirstPaybackDate), " +
        "    DATEPART(M, I.InstallmentStartedOn), " +
        "    E.IdName " +
        "FROM InstallmentApplicationObjects I, EndUserExtensionObjects E " +
        "WHERE I.UserId = E.Id " +
        "    AND I.Id = :AppId";
    RowMapper<List<String>> extractor = new RowMapper<List<String>>() {
      @Override
      public List<String> mapRow(ResultSet resultSet, int index) throws SQLException {
        return Arrays.asList(
            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
      }
    };
    return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
  }
  
  public static List<String> getMobileAndValidationCodeByMobile(String mobile) {
    String sql = "SELECT TOP 1 Mobile, MobileValidationCode " +
        "FROM [ValidationCodeObjects] WHERE Mobile = :mobile AND [UserId] IS NULL " +
        "ORDER BY DateAdded DESC";
    return DatabaseApi.querySingleResultOrDefault(
      sql, CollectionUtils.mapOf("mobile", mobile), DatabaseExtractors.TWO_STRING_EXTRACTOR, null);
  }
  
  public static List<String> getMobileAndValidationCodeByUserId(String userId){
    String sql =
      "SELECT TOP 1 Mobile, MobileValidationCode " +
      "FROM ValidationCodeObjects " +
      "WHERE UserId = :UserId " +
      "ORDER BY LastModified DESC";
    return DatabaseApi.querySingleResult(
      sql, CollectionUtils.mapOf("UserId", userId), DatabaseExtractors.TWO_STRING_EXTRACTOR);
  }
  
  public static boolean isApplicationFromAgentApp(String appId)
  {
	  int instalmentChannel = InstallmentApplicationDao.getApplicationTypeById(appId);
	  return (instalmentChannel & InstalmentChannel.App.getValue()) > 0;
  }
  
  public static boolean isPOSApplication(String appId)
  {
    int instalmentChannel = InstallmentApplicationDao.getApplicationTypeById(appId);
    return (instalmentChannel == InstalmentChannel.App.getValue()) || 
    		(instalmentChannel == InstalmentChannel.WeChat.getValue());
  }
  
  public static boolean isPDLApplication(String appId)
  {
    int instalmentChannel = InstallmentApplicationDao.getApplicationTypeById(appId);
    return instalmentChannel == InstalmentChannel.PayDayLoanApp.getValue();
  }
  
  public static String getUserIdByAppId(String appId)
  {
    return InstallmentApplicationDao.getUserIdByAppId(appId);
  }

  public static String getCustomerWeChatOpenIdBy(String appId) {
    String sql =
        "SELECT UserObjects.WeiXinUserId " +
        "FROM InstallmentApplicationObjects, UserObjects " +
        "WHERE InstallmentApplicationObjects.UserId = UserObjects.Id " +
        "    AND InstallmentApplicationObjects.Id = :AppId";

    String UnionId = getCustomerUnionIdBy(appId);
    if(UnionId == null){
        Logger.get().warn("Unionid is null for appId: " + appId);
        return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
    } else {
        return getCustomerWeChatOpenIdByUnionId(UnionId);
    }

}

  public static String getCustomerUnionIdBy(String appId) {
      String sql =
          "SELECT WeiXinUserObjects.UnionId " +
          "FROM InstallmentApplicationObjects, UserObjects, WeiXinUserObjects " +
          "WHERE InstallmentApplicationObjects.UserId = UserObjects.Id " +
          "    AND WeiXinUserObjects.Id = UserObjects.WeiXinUserId " +
          "    AND InstallmentApplicationObjects.Id = :AppId";

      return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  public static String getCustomerWeChatOpenIdByUnionId(String unionId) {
      String sql =
          "SELECT WeiXinUserObjects.Id " +
          "FROM WeiXinUserObjects " +
          "WHERE Source = 'MP' " +
          "    AND UnionId = :UnionId ";

      return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("UnionId", unionId));
  }

  public static String getMerchantWeChatOpenIdBy(String appId) {
    String sql =
        "SELECT U.WeiXinUserId " +
        "FROM InstallmentApplicationObjects I, MerchantUserObjects M, UserObjects U " +
        "WHERE I.MerchantUserId = M.Id " +
        "    AND M.UserId = U.Id " +
        "    AND I.Id = :AppId";

    return DatabaseApi.querySingleStringOrDefault(sql, CollectionUtils.mapOf("AppId", appId), null);
  }

  public static String getD1WeChatOpenIdBy(String appId) {
	    String sql =
	        "SELECT U.WeiXinUserId " +
	        "FROM InstalmentApplicationSnapObjects IAS, DealerUserObjects DU, UserObjects U " +
	        "WHERE IAS.D1Id = DU.Id " +
	        "    AND DU.UserId = U.Id "+
	        "    AND IAS.InstalmentAppId = :AppId";

	    return DatabaseApi.querySingleStringOrDefault(sql, CollectionUtils.mapOf("AppId", appId), null);
	 }
  
  public static String getBD2WeChatOpenIdBy(String appId) {
    String sql =
        "SELECT U.WeiXinUserId " +
        "FROM InstalmentApplicationSnapObjects IAS, BusinessDevelopUserObjects BDU, UserObjects U " +
        "WHERE IAS.BD2Id = BDU.Id " +
        "    AND BDU.UserId = U.Id "+
        "    AND IAS.InstalmentAppId = :AppId";

    return DatabaseApi.querySingleStringOrDefault(sql, CollectionUtils.mapOf("AppId", appId), null);
 }

  public static String getCustomerMobileBy(String appId) {
    String sql =
        "SELECT ContactObjects.Content " +
        "FROM ContactObjects, UserObjects, InstallmentApplicationObjects " +
        "WHERE ContactObjects.Id = UserObjects.MobileContactId " +
        "  AND UserObjects.Id = InstallmentApplicationObjects.UserId " +
        "  AND ContactObjects.ContactType = :ContactType " +
        "  AND InstallmentApplicationObjects.Id = :AppId " +
        "ORDER BY ContactObjects.LastModified DESC";

    return DatabaseApi.querySingleString(
        sql, CollectionUtils.mapOf("ContactType", ContactType.MOBILE, "AppId", appId));
  }

  public static String getMerchantMobileBy(String appId) {
    String sql =
        "SELECT C.Content " +
        "FROM InstallmentApplicationObjects I, MerchantUserContactObjects MC, ContactObjects C " +
        "WHERE I.MerchantUserId = MC.MerchantUserId " +
        "    AND MC.ContactId = C.Id " +
        "    AND C.ContactType = :ContactType " +
        "    AND I.Id = :AppId " +
        "ORDER BY C.LastModified DESC";

    return DatabaseApi.querySingleStringOrDefault(
        sql, CollectionUtils.mapOf("ContactType", ContactType.MOBILE, "AppId", appId), null);
  }


  public static String getD1MobileBy(String appId) {
    String sql =
        "SELECT C.Content " +
        "FROM InstalmentApplicationSnapObjects I, MerchantUserContactObjects MC, ContactObjects C " +
        "WHERE I.D1Id = MC.MerchantUserId " +
        "    AND MC.ContactId = C.Id " +
        "    AND C.ContactType = :ContactType " +
        "    AND I.InstalmentAppId = :AppId " +
        "ORDER BY C.LastModified DESC";

    return DatabaseApi.querySingleStringOrDefault(
        sql, CollectionUtils.mapOf("ContactType", ContactType.MOBILE, "AppId", appId), null);
  }

  public static String getCustomerNameBy(String appId) {
    String sql =
        "SELECT E.IdName " +
        "FROM InstallmentApplicationObjects I, EndUserExtensionObjects E " +
        "WHERE I.UserId = E.Id " +
        "    AND I.Id = :AppId";
    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  public static String getWeiXinUserNameBy(String appId) {
	    String sql =
	        "SELECT U.UserName " +
	        "FROM InstallmentApplicationObjects I, UserObjects U " +
	        "WHERE I.UserId = U.Id " +
	        "    AND I.Id = :AppId";
	    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
	  }

  public static String getPrincipalBy(String appId) {
	    String sql =
	        "SELECT I.Principal " +
	        "FROM InstallmentApplicationObjects I " +
	        "WHERE I.Id = :AppId";
	    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
	  }

  public static List<String> getAllD1WeChatOpenIdBy(String appId) {
      final String sql = "select DISTINCT uo.WeiXinUserId weiXinUserId"
      + " from POSDOrgRelationObjects pos "
      + " join DOrgNodeObjects dOrg on pos.OrgNodeId=dOrg.Id "
      + " join DOrgDUserRelationObjects dour on dOrg.Id=dour.OrgNodeId "
      + " join DealerUserObjects dUser on dour.AffiliateId=dUser.Id "
      + " join InstallmentApplicationObjects ins on ins.MerchantStoreId=pos.POSId "
      + " join UserObjects uo on uo.Id=dUser.UserId "
      + " where  ins.Id = :Id "
      + " and dOrg.DeletedOn is null and pos.DeletedOn is null ";
      return DatabaseApi.querySingleResult(
          sql, CollectionUtils.mapOf("Id", appId), DatabaseExtractors.MULTI_STRING_EXTRACTOR);
}
  
  
    public static String getMerchantStoreNameBy(String appId) {
        String sql="SELECT MerchantStoreObjects.Name from InstallmentApplicationObjects,MerchantStoreObjects  where InstallmentApplicationObjects.MerchantStoreId=MerchantStoreObjects.Id and InstallmentApplicationObjects.Id=:AppId";
        return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
    }

    //TODO sql似乎存在潜在问题 ? 限定条件 是否需要加上 AND dour.DeletedOn IS NULL ??
    public static List<AppMessageObject> getAllD1IDByAppId(String appId) {
        final String sql = "select DISTINCT dUser.Id id,device.device_type deviceType,device.device_token deviceToken"
                + " from POSDOrgRelationObjects pos "
                + " join DOrgNodeObjects dOrg on pos.OrgNodeId=dOrg.Id "
                + " join DOrgDUserRelationObjects dour on dOrg.Id=dour.OrgNodeId "
                + " join DealerUserObjects dUser on dour.AffiliateId=dUser.Id "
                + " join InstallmentApplicationObjects ins on ins.MerchantStoreId=pos.POSId "
                + " join LoginDeviceInfoObjects device on dUser.Id=device.login_person_id "
                + " where  ins.Id = :Id "
                + " and dOrg.DeletedOn is null and pos.DeletedOn is null ";
                Map<String, Object> params = new MapBuilder<String, Object>().add("Id", appId).build();
                return DatabaseApi.queryMultipleResults(sql, params,
                    new ResultSetExtractor<List<AppMessageObject>>() {
                        @Override
                        public List<AppMessageObject> extractData(ResultSet resultSet) throws SQLException,
                                                                                                   DataAccessException {
                            List<AppMessageObject> dateList = new LinkedList<AppMessageObject>();
                             while (resultSet.next()) {
                                AppMessageObject obj = AbstractDao
                                    .fillObject(AppMessageObject.class, resultSet);
                                if (obj != null && obj.getDeviceToken()!=null)
                                    dateList.add(obj);
                            }
                            return dateList;
                        }
                    });
    }
    
    /**
     * 查询d人员id取消走DNode节点关联表的方式, D人员Node相关表已迁移到SMSA系统服务维护,直接取值 POSDOrgRelationObjects.SmsaUserId字段
     * @param appId
     * @return
     */
    public static List<AppMessageObject> getAllD1IdByAppId_New(String appId){
    	final String sql = "select DISTINCT device.login_person_id id,device.device_type deviceType,device.device_token deviceToken"
                + " from InstallmentApplicationObjects ins  "
                + " join POSDOrgRelationObjects pos on ins.MerchantStoreId = pos.POSId "
                + " join LoginDeviceInfoObjects device on pos.SmsaUserId = device.login_person_id "
                + " where  ins.Id = :Id "
                + " and pos.DeletedOn is null ";
                Map<String, Object> params = new MapBuilder<String, Object>().add("Id", appId).build();
                return DatabaseApi.queryMultipleResults(sql, params,
                    new ResultSetExtractor<List<AppMessageObject>>() {
                        @Override
                        public List<AppMessageObject> extractData(ResultSet resultSet) throws SQLException,
                                                                                                   DataAccessException {
                            List<AppMessageObject> dateList = new LinkedList<AppMessageObject>();
                             while (resultSet.next()) {
                                AppMessageObject obj = AbstractDao
                                    .fillObject(AppMessageObject.class, resultSet);
                                if (obj != null && obj.getDeviceToken()!=null)
                                    dateList.add(obj);
                            }
                            return dateList;
                        }
                    });
    }

    public static List<String> getWeChatOpenIdByUserIds(String userIds) {
        final String sql = "SELECT DISTINCT  WeiXinUserId FROM WeiXinContextObjects WHERE UserId IN ("+userIds+") AND WeiXinUserId IS NOT NULL";
        return DatabaseApi.querySingleResult(sql, null, DatabaseExtractors.MULTI_STRING_EXTRACTOR);
    }
}