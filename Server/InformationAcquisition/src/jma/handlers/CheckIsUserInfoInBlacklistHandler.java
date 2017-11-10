package jma.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;
import catfish.base.httpclient.HttpClientApi;
import jma.DatabaseEnumValues.BlacklistType;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.models.BlackListInfo;
import jma.models.Result;

/**
 * 
 * @author yeyb
 * @date 2017年7月25日
 */
public class CheckIsUserInfoInBlacklistHandler extends JobHandler {
	
	public static String blackListUrl=StartupConfig.get("risk.blackbase.url");
	
	@Override
	  public void execute(String appId) throws RetryRequiredException {
		
		List<BlackListInfo> allParams = new ArrayList<>();
		
		List<String> contactPhones = getContactPhones(appId);
		if(contactPhones!=null && contactPhones.size()>0){
			//联系人号码
			for (String phone : contactPhones) {
				BlackListInfo contact=new BlackListInfo();
				contact.setContent(phone);
				contact.setType(BlacklistType.PHONE);
				allParams.add(contact);
			}
		}
		
		//身份证号
		BlackListInfo id=new BlackListInfo();
		id.setContent(DatabaseUtils.getIdCardNumberBy(appId));
		id.setType(BlacklistType.ID_NUMBER);
		allParams.add(id);
		
		//用户手机号码
		BlackListInfo usermobile=new BlackListInfo();
		usermobile.setContent(PhoneUtils.getUserMobile(appId));
		usermobile.setType(BlacklistType.PHONE);
		allParams.add(usermobile);
		
		//QQ号码
		BlackListInfo qq=new BlackListInfo();
		qq.setContent(DatabaseUtils.getUserQQ(appId));
		qq.setType(BlacklistType.QQ);
		allParams.add(qq);
		
		boolean checkWhetherOrNotBlack = checkWhetherOrNotBlack(allParams);
		
		AppDerivativeVariableManager.addVariables(
		        new AppDerivativeVariable(
		            appId, AppDerivativeVariableNames.IS_USER_INFO_IN_BLACKLIST, checkWhetherOrNotBlack));
		
	}
	//调用blackbase服务查看是否在黑名单内
		public boolean checkWhetherOrNotBlack(List<BlackListInfo> allParams){
			if("".equals(blackListUrl) || blackListUrl==null){
	            Logger.get().warn("blackListUrl is null,please add blackListUrl in configuration file ! ");
	            return true;
	        }
			if(allParams!=null&&allParams.size()>0){
	            Gson gson = new Gson();
	            Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
	            try {
	                String json = HttpClientApi.postString(blackListUrl + "/black/checkUserIsBlackList", gson.toJson(allParams), header);
	                Result<Boolean> result = gson.fromJson(json, new TypeToken<Result<Boolean>>() {
	                }.getType());
	                if(result!=null&&result.getCode()==1){
	                    Logger.get().info("checkUserIsBlackList success,result is "+gson.toJson(result));
	                    return result.getData()==true?true:false;
	                }else{
	                    Logger.get().info("checkUserIsBlackList failed");
	                }
	            } catch (Exception e) {
	                Logger.get().error("checkUserIsBlackList  has error ",e);
	            }            
	        }
			return false;
		}
  private List<String> getContactPhones(String appId) {
    String sql =
        "SELECT co.Content " +
        "FROM ContactPersonObjects AS cp1, ContactObjects AS co " +
        "WHERE cp1.AppId = :AppId " +
        "    AND cp1.MobileContactId = co.Id" +
        "    AND NOT EXISTS ( " +
        "        SELECT cp2.Id " +
        "        FROM ContactPersonObjects AS cp2 " +
        "        WHERE cp2.AppId = cp1.AppId " +
        "            AND cp2.ContactPersonType = cp1.ContactPersonType " +
        "            AND cp2.LastModified > cp1.LastModified)";

    return DatabaseApi.queryMultipleResults(
        sql, CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.STRING_EXTRACTOR);
  }
}
