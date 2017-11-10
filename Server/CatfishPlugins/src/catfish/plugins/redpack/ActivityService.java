package catfish.plugins.redpack;

import java.util.*;
import java.util.function.Predicate;

import catfish.base.CollectionUtils;
import catfish.base.StartupConfig;
import catfish.base.business.dao.ActivityWeixinRedPackDao;
import catfish.base.business.dao.ActivityWeixinRedPackRecordDao;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.UserDao;
import catfish.base.business.object.ActivityWeixinRedPackObject;
import catfish.base.business.object.ActivityWeixinRedPackRecordObject;
import catfish.base.business.object.CNAreaObject;
import catfish.base.database.DatabaseApi;

public interface ActivityService {
    ActivityWeixinRedPackObject getActivity(String appId);
    String createRedPackRecord(String appId, String activityId, String mchBillNo, double reward);
    String createRedPackRecord(String appId, String activityId, String mchBillNo, double reward, String wxAppId, String openId);
}

class ActivityRedPackService implements ActivityService {
    private static final String WX_APPID = StartupConfig.get("catfish.wx.appid");
    private static final int CACHE_DURATION = StartupConfig.getAsInt("Activity.WeixinRedPack.CacheDuration", 60);  // minutes
    private static List<ActivityWeixinRedPackObject> Config;
    private static Date CacheTime;

    @Override
    public ActivityWeixinRedPackObject getActivity(String appId) {
        updateConfig();
//		list<string> areaids = getcnareaids(appid);
//		date now = new date();
//	    return config
//	        .stream()
//	        .filter(f -> areaids.contains(f.areaidentity)
//	            && now.after(f.starttime) && now.before(f.endtime))
//	        .findFirst()
//	        .orElse(null);

        ActivityWeixinRedPackObject result;
        RedPackArea area = getRedPackArea(appId);
        Date now = new Date();
        List<ActivityWeixinRedPackObject> activityOnGoing = new ArrayList<ActivityWeixinRedPackObject>();
        Predicate<ActivityWeixinRedPackObject> activity = a -> now.after(a.StartTime) && now.before(a.EndTime);
        Config.forEach(act -> {
            if (activity.test(act)) activityOnGoing.add(act);
        });
        // 优先匹配区，然后市，最后省
        result = activityOnGoing.stream().filter(p -> p.AreaIdentity.equals(area.getBlockId())).findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        result = activityOnGoing.stream().filter(p -> p.AreaIdentity.equals(area.getCityId())).findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        result = activityOnGoing.stream().filter(p -> p.AreaIdentity.equals(area.getProvinceId())).findFirst().orElse(null);
        if (result != null) {
            return result;
        }

        return null;
    }

    @Override
    public String createRedPackRecord(String appId, String activityId, String mchBillNo, double reward) {

        ActivityWeixinRedPackRecordDao dao = new ActivityWeixinRedPackRecordDao();
        dao.setAppId(appId);
        ActivityWeixinRedPackRecordObject record = dao.getSingle();
        if (record != null) {
            //Already sent redpack for this AppId, return record id;
            return record.Id;
        }
        boolean isOk = dao.insert(
                appId,
                mchBillNo,
                activityId,
                WX_APPID,
                UserDao.getMerchantWxOpenIdByAppId(appId),
                reward,
                WeixinRedPackStatus.CREATED,
                WeixinRedPackSourceType.APPLICATION_COMPLETE);
        if (isOk) {
            record = dao.getSingle();
            return record.Id;
        } else {
            return null;
        }
    }

    @Override
    public String createRedPackRecord(String appId, String activityId, String mchBillNo, double reward, String wxAppId, String openId) {
        String recordId = UUID.randomUUID().toString();
        String sql =
                "INSERT INTO ActivityWeixinRedPackRecordObjects " +
                        "(Id, AppId, MchBillNo, ActivityId, WXAppId, WeixinOpenId, Reward, Status, SourceType, DateAdded, LastModified) " +
                        "VALUES " +
                        "(:Id, :AppId, :MchBillNo, :ActivityId, :WXAppId, :WeixinOpenId, :Reward, :Status, :SourceType, getdate(), getdate())";
        Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
                .add("Id", recordId)
                .add("AppId", appId)
                .add("MchBillNo", mchBillNo)
                .add("ActivityId", activityId)
                .add("WXAppId", wxAppId)
                .add("WeixinOpenId", openId)
                .add("Reward", reward)
                .add("Status", WeixinRedPackStatus.CREATED)
                .add("SourceType", WeixinRedPackSourceType.APPLICATION_COMPLETE)
                .build();

        boolean isOk = DatabaseApi.updateTable(sql, params) == 1;

        if (isOk) {
            return recordId;
        } else {
            return null;
        }
    }

    private List<String> getCNAreaIds(String appId) {
        List<String> validAreaIds = new ArrayList<String>();
        String storeId = new InstallmentApplicationDao(appId).getSingle().MerchantStoreId;
        String areaId = new MerchantStoreDao(storeId).getSingle().CNAreaId;
        CNAreaObject area = new CNAreaDao(areaId).getSingle();
        validAreaIds.add(areaId);
        String parentCode = area.parentCode;
        while (parentCode != null && !parentCode.isEmpty()) {
            area = CNAreaDao.getCNAreaObjByAreaCode(parentCode);
            validAreaIds.add(area.Id);
            parentCode = area.parentCode;
        }

        return validAreaIds;
    }

    private RedPackArea getRedPackArea(String appId) {
        RedPackArea area = new RedPackArea();
        String storeId = new InstallmentApplicationDao(appId).getSingle().MerchantStoreId;
        String blockId = new MerchantStoreDao(storeId).getSingle().CNAreaId;
        area.setBlockId(blockId);
        CNAreaObject block = new CNAreaDao(blockId).getSingle();
        if (block.parentCode == null)
            return area;
        CNAreaObject city = CNAreaDao.getCNAreaObjByAreaCode(block.parentCode);
        area.setCityId(city.Id);
        if (city.parentCode == null)
            return area;
        CNAreaObject province = CNAreaDao.getCNAreaObjByAreaCode(city.parentCode);
        area.setProvinceId(province.Id);
        return area;
    }

    private void updateConfig() {
//	    if (CacheTime == null || isCacheExpired()) {
//	      Config = new ActivityWeixinRedPackDao().getMultiple();
//	      CacheTime = new Date();
//	    }
        Config = new ActivityWeixinRedPackDao().getMultiple();
    }

    private boolean isCacheExpired() {
        return (new Date().getTime() - CacheTime.getTime()) / (1000 * 60) > CACHE_DURATION;
    }
}
