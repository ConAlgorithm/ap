package jma.handlers;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.util.EnumUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import jma.DatabaseEnumValues.BlacklistType;
import jma.DatabaseUtils;
import jma.EncryptUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.models.BlackListCategory;
import jma.models.BlackListInfo;
import jma.models.Result;

public class BlacklistPersonalInformationHandler extends JobHandler {
    public static String blackListUrl=StartupConfig.get("risk.blackbase.url");
    protected InstalmentChannel channel;
    protected String reason;
    
    public BlacklistPersonalInformationHandler() {
        sendsResponse = false;
    }

    @Override
    public void execute(String appId) throws RetryRequiredException {
        List<String> nameAndIdNumber = DatabaseUtils.getIdCardNameAndNumber(appId);
        String name = nameAndIdNumber == null ? null : nameAndIdNumber.get(0);
        String idNumber = nameAndIdNumber == null ? null : nameAndIdNumber.get(1);
        String userMobile = PhoneUtils.getUserMobile(appId);
        String userQQ = DatabaseUtils.getUserQQ(appId);
        reason = getReasonByAppId(appId);
        channel = getInstalmetnChannelByAppId(appId);
        if (channel == null) {
            Logger.get().warn("get instalmentchannel error! appId : " + appId);
            return;
        }

        blacklistInfo(name, idNumber, BlacklistType.ID_NUMBER, reason, channel.name());
        blacklistInfo(name, userMobile, BlacklistType.PHONE, reason, channel.name());
        blacklistInfo(name, userQQ, BlacklistType.QQ, reason, channel.name());
    }

    @Override
    public void writeJobResult(boolean isSuccess) {
        String appId = requestMessager.getAppId();
        if (isSuccess) {
            Logger.get().info(String.format("Blacklisted information for appId=%s", appId));
        } else {
            Logger.get().info(String.format("Failed to blacklist information for appId=%s", appId));
        }
        super.writeJobResult(isSuccess);
    }

    protected static void blacklistInfo(String name, String content, int type, String reason, String channel) {
        if (StringUtils.isNullOrWhiteSpaces(content)) {
            return;
        }

        if (name == null) {
            name = "";
        }
        if(reason == null){
            reason="";
        }
        String sql = "INSERT INTO BlacklistObjects " + "  (Name, Content, Type, Source, Reason, DateAdded, LastModified, OriginSource, OccuredDateTime) "
                     + "VALUES (:Name, :Content, :Type, :Source, :Reason, GETDATE(), GETDATE(), :OriginSource, GETDATE())";

        Map<String, Object> params = CollectionUtils.<String, Object> newMapBuilder()
                .add("Name", EncryptUtils.getDES(name))
                .add("Content", EncryptUtils.getDES(content))
                .add("Type", type)
                .add("Source", "F-RiskPlugins")
                .add("Reason", reason)
                .add("OriginSource", channel).build();
        try {
            DatabaseApi.updateTable(sql, params);            
        } catch (Exception e) {
            Logger.get().error("insert into BlacklistObjects has error ",e);
        }
        //调黑名单服务
        BlackListInfo model = new BlackListInfo();
        model.setCategory(BlackListCategory.INNER);
        model.setName(name);
        model.setContent(content);
        model.setType(type);
        model.setSource("F-RiskPlugins");
        model.setReason(reason);
        model.setProduct(channel);
        saveToBlackList(model);
    }

    /*
     * get application channel
     */
    protected static InstalmentChannel getInstalmetnChannelByAppId(String appId) {
        InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
        if (app != null) {
            return EnumUtils.parse(InstalmentChannel.class, app.InstalmentChannel);
        } else {
            return null;
        }
    }

    /**
    * <p>〈获取actionType〉</p>
    * 
    * @param appId
    * @return
    */
    protected static String getReasonByAppId(String appId) {
        String sql = "SELECT TOP 1 ActionType " + "FROM MerchantUserActionObjects as mua "
                + "join InstallmentApplicationObjects as ia " + "on mua.UserId=ia.UserId "
                + "where ia.Id=:appId";
        Integer actionType=DatabaseApi.querySingleInteger(sql, CollectionUtils.mapOf("appId", appId));
        return actionType == null ? null :actionType.toString();
    }
   
    /**
     * <p>〈调用黑名单服务保存至黑名单〉</p>
     * 
     * @param blackListInfo
     */
    protected static void saveToBlackList(BlackListInfo blackListInfo){
        if("".equals(blackListUrl) || blackListUrl==null){
            Logger.get().warn("blackListUrl is null,please add blackListUrl in configuration file ! ");
            return;
        }
        Gson gson = new Gson();
        Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
        try {
            String json = HttpClientApi.postString(blackListUrl + "/black/addBlackList", gson.toJson(blackListInfo), header);
            Result<Integer> result = gson.fromJson(json, new TypeToken<Result<Integer>>() {
            }.getType());
            if(result!=null&&result.getCode()==1){
                Logger.get().info("saveToBlackList success");
            }else{
                Logger.get().info("saveToBlackList fail");
            }
        } catch (Exception e) {
            Logger.get().error("saveToBlackList  has error ",e);
        }
        
    }
}
