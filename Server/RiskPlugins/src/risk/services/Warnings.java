package risk.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.itextpdf.tool.xml.exceptions.NotImplementedException;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.MerchantUserActionType;
import catfish.base.business.common.MerchantUserRole;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.RuleEngineObject;
import catfish.base.business.util.Model2ObjectUtil;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.queue.QueueMessager;
import catfish.framework.queue.IQueueService;
import catfish.framework.restful.IRESTfulService;
import risk.datamodel.MerchantUserActionObject;
import risk.plugins.Driver;
import risk.services.restfulobjects.MerchantUserWarning;
import risk.services.restfulobjects.RestfulResponse;

@Path("Risk/Warnings")
public class Warnings {

//    private static final String FLOW_QUEUE_NAME = "StatusQueueV2";
    private static final String JOB_QUEUE_NAME = "JobRequestQueue";

//    private static final String FLOW_EVENT = "MerchantWarned";
    private static final String BLACKLIST_PERSONAL_INFO = "BlacklistPersonalInformation";
    private static final String BLACKLIST_ALL_INFO = "BlacklistAllInformation";
    //因为sql中关键字IN只支持1000个参数，因此，在参数大于1000的时候要特殊处理
    //默认sql最大in的调用数量
    private static final int SQL_IN_MAX_SIZE = 500;

    private static final Map<Integer, String> ACTION_MAPPING = CollectionUtils
        .<Integer, String> newMapBuilder()
        .add(MerchantUserActionType.Report_Bad_Credit.getValue(), BLACKLIST_PERSONAL_INFO)
        .add(MerchantUserActionType.Report_Fake_Information.getValue(), BLACKLIST_PERSONAL_INFO)
        .add(MerchantUserActionType.Report_Second_Hand.getValue(), BLACKLIST_PERSONAL_INFO)
        .add(MerchantUserActionType.Report_Fake_Identification.getValue(), BLACKLIST_ALL_INFO)
        .add(MerchantUserActionType.Report_Agential_Arbitrage.getValue(), BLACKLIST_ALL_INFO)
        .add(MerchantUserActionType.Report_Personal_Arbitrage.getValue(), BLACKLIST_ALL_INFO)
        .build();
    //开卡后不可拉警报的F身份
    private static final Set<String>Merchant_User_Roles = CollectionUtils.
            <String>newSetBuilder()
            .add(MerchantUserRole.F1.toString())
            .add(MerchantUserRole.F2.toString())
            .build();

    @POST
    @Path("/MerchantUserActions")
    @Consumes(IRESTfulService.JSON_UTF8)
    @Produces(IRESTfulService.JSON_UTF8)
    public RestfulResponse insertMerchantUserAction(MerchantUserWarning merchantUserWarning) {

        try {
            InstallmentApplicationObject app = InstallmentApplicationDao
                    .getApplicationInfoById(merchantUserWarning.getAppId());
            
            if(app == null) {
                return RestfulResponse.createFailedResponse(0, "无法查找该申请！");
            }
            
            //用户是否被报过警
            Dao<MerchantUserActionObject> dao = Dao.create(MerchantUserActionObject.class,
                DatabaseApi.database);
            if (warnExists(dao, merchantUserWarning.getAppId())) {
                return RestfulResponse.createFailedResponse(0, "该用户已经被拉过警报。");
            }
            
            //当前申请状态报警是否还有效
             String merchantUserRole = merchantUserWarning.getMerchantUserRole();
             //过滤掉直销的F3，直销F3可以在开卡后拉警报
             if(merchantUserRole == null || Merchant_User_Roles.contains(merchantUserRole))
             {
                  if (!warnWorks(merchantUserWarning.getAppId(), app)) {
                     if(app.getInstalmentChannel() == InstalmentChannel.App.getValue() 
                             || app.getInstalmentChannel() == InstalmentChannel.WeChat.getValue()) {
                         return RestfulResponse.createFailedResponse(0, "报警太晚。");
                     } else if(app.getInstalmentChannel() == InstalmentChannel.PayDayLoanApp.getValue()) {
                         return RestfulResponse.createFailedResponse(0, "报警太晚，拉警报不成功,请钉钉群反馈或联系区域经理。");
                     }
                 }
             }
             
            //添加报警数据进数据库
            MerchantUserActionObject merchantUserActionObject = new MerchantUserActionObject();

            Model2ObjectUtil.copyProperties(merchantUserWarning, merchantUserActionObject);
            merchantUserActionObject.UserId = app.UserId;
            merchantUserActionObject.DateAdded = new Date();
            merchantUserActionObject.LastModified = new Date();

            //Insert into database
            dao.add(merchantUserActionObject);

            IQueueService service = Driver.getServer().getServiceProvider()
                .getService(IQueueService.class);
            //如果警报类型存在于预设定的类型中，给IA发消息，消息job名称为警报类型
            if (ACTION_MAPPING.containsKey(merchantUserWarning.getActionType())) {
                Logger.get()
                    .info(String.format("Send %s event of AppId: %s to AutoJob.",
                        ACTION_MAPPING.get(merchantUserWarning.getActionType()),
                        merchantUserWarning.getAppId()));
                service.getQueue(JOB_QUEUE_NAME).sendMessage("",
                    new QueueMessager(merchantUserWarning.getAppId(),
                        ACTION_MAPPING.get(merchantUserWarning.getActionType())));
            }

        } catch (Exception e) {
            Logger.get().error(e);
            return RestfulResponse.createFailedResponse(RestfulResponse.InternalServerError);
        }

        return RestfulResponse.createSuccessfulResponse();
    }

    @GET
    @Path("/MerchantUserActions/appId={appId}")
    @Consumes(IRESTfulService.JSON_UTF8)
    @Produces(IRESTfulService.JSON_UTF8)
    public RestfulResponse isUserWarned(@PathParam("appId") String appId) {

        try {
            Dao<MerchantUserActionObject> dao = Dao.create(MerchantUserActionObject.class,
                DatabaseApi.database);
            Integer warnActionType = getWarnActionType(dao, appId);
            if (warnActionType != null) {
                return RestfulResponse.createSuccessfulResponse(warnActionType);
            }
        } catch (Exception e) {
            Logger.get().error(e);
            return RestfulResponse.createFailedResponse(RestfulResponse.InternalServerError);
        }
        return RestfulResponse.createFailedResponse(null);
    }

    private static boolean warnExists(Dao<MerchantUserActionObject> dao, String appId) {
        return getWarnActionType(dao, appId) != null;
    }

    private static Integer getWarnActionType(Dao<MerchantUserActionObject> dao, String appId) {
        String sql = "SELECT TOP 1 * " + "FROM MerchantUserActionObjects as mua "
                     + "join InstallmentApplicationObjects as ia " + "on mua.UserId=ia.UserId "
                     + "where ia.Id=:appId";
        MerchantUserActionObject mua = dao.getSingle(sql, CollectionUtils.mapOf("appId", appId));
        return mua == null ? null : mua.ActionType;
    }

    private static boolean warnWorks(String appId, InstallmentApplicationObject app) {

        Dao<RuleEngineObject> ruleResultDao = Dao.create(RuleEngineObject.class,
            DatabaseApi.database);

        //TODO: RE应该提供restful api，可以查询”报警决策点是否都已经执行结束“
        //Weixin, App最后决策点是LoanCheck
        if (app.InstalmentChannel == InstalmentChannel.App.getValue()
            || app.InstalmentChannel == InstalmentChannel.WeChat.getValue()) {
            String sql = "SELECT TOP 1 * " + "FROM RuleEngineResultObjects "
                  + "where DecisionJobName like 'LoanCheck%' and AppId=:appId";
            RuleEngineObject ruleResult = ruleResultDao.getSingle(sql,
                CollectionUtils.mapOf("appId", appId));
            return ruleResult == null;
        } else if (app.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue()) {
            String sql = "SELECT TOP 1 * " + "FROM RuleEngineResultObjects "
                  + "where DecisionJobName like 'FinalCheck%' and AppId=:appId";
            RuleEngineObject ruleResult = ruleResultDao.getSingle(sql,
                CollectionUtils.mapOf("appId", appId));
            return ruleResult == null;
        }

        throw new NotImplementedException("Not supported InstalmentChannel.");
    }

    /**
     * 给定appId列表，返回每个appId是否已经拉过警报
     * @param List<String>
     * @return RestfulResponse 包含appId到int类型的映射，int代表警报动作代号
     * @author t_mengxh
     * @date 2016/10/14
     * @email t_mengxh@fenqi.im
     */
    @POST
    @Path("/MerchantUserActions/appIdList/hasWarned")
    @Consumes(IRESTfulService.JSON_UTF8)
    @Produces(IRESTfulService.JSON_UTF8)
    public RestfulResponse queryHasWarnedByAppIdList(List<String> appIdList) {
        if(appIdList == null || appIdList.isEmpty())
            return RestfulResponse.createFailedResponse(0,"未获取到参数或参数为空");
        //返回结果映射，key为appId，value为拉警报的动作代号
        Map<String, Integer> appIdToWarnMap = new HashMap<>();
        //查询结果存放
        List<MerchantUserActionObject> merchantUserActionObjectListForMap = new ArrayList<MerchantUserActionObject>();
        //得到数据库查询的各个参数

        Dao<MerchantUserActionObject> merchantUserActionObjectDao = Dao
            .create(MerchantUserActionObject.class, DatabaseApi.database);
            //查询语句
            String sql = "SELECT mua1.AppId,mua1.ActionType FROM MerchantUserActionObjects mua1"+
            " INNER JOIN (SELECT mua2.AppId,max(mua2.DateAdded) DateAddedMax FROM "+
                    "MerchantUserActionObjects mua2 GROUP BY mua2.appid) t on t.AppId = mua1.AppId "+
            "AND t.DateAddedMax = mua1.DateAdded WHERE mua1.AppId IN  (:appIdList)";
            //得到参数的个数
            int listSize = appIdList.size();
            //求出分几组
            int length = listSize % SQL_IN_MAX_SIZE == 0 ? listSize / SQL_IN_MAX_SIZE : listSize / SQL_IN_MAX_SIZE + 1;
            for (int i = 0; i < length; i++) {
                    //得到当前分组的左边界
                   int left = SQL_IN_MAX_SIZE * i;
                   //得到当前分组的右边界（在不能正好分组的时候最后一组需要特殊考虑，因此会比较计算的右边界和实际的右边界，取其较小的那个作为右边界）
                    int  right = SQL_IN_MAX_SIZE * (i + 1) > listSize ? listSize : SQL_IN_MAX_SIZE * (i + 1);
                   //拆参数列表，查询多个结果
                    try {
                              List<MerchantUserActionObject> merchantUserActionObjectList = merchantUserActionObjectDao
                                  .getMultiple(sql, CollectionUtils.mapOf("appIdList", appIdList.subList(left, right)));
                              //如果分组结果查询不为null的话，把分组的临时结果合并
                              if(merchantUserActionObjectList.size() != 0)
                              {
                                  merchantUserActionObjectListForMap.addAll(merchantUserActionObjectList);
                              }
                    } catch (Exception e) {
                        Logger.get().error(e);
                        return RestfulResponse.createFailedResponse(RestfulResponse.InternalServerError);
                    }
              }
            //遍历结果list，整理结果为key（设置为appId），value（设置结果为警报动作代号）形式
            Iterator<MerchantUserActionObject>iter = merchantUserActionObjectListForMap.iterator();
            while(iter.hasNext()){
                MerchantUserActionObject muao = iter.next();
                appIdToWarnMap.put(muao.AppId, muao.ActionType);
            }
        //结果map序列化为json字符串
        return RestfulResponse.createSuccessfulResponse(new Gson().toJson(appIdToWarnMap));
    }
}
