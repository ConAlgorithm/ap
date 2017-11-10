package network.relationship.api;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import network.relationship.businessdomain.*;
import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;
import catfish.base.httpclient.HttpClientApi;
import network.relationship.domain.Application;

public class StatisticsApi {
    private static final String PAYMENT_URL = StartupConfig.get("catfish.payment.url") + "/settlement/batch/payment-info";
    private static final String RISK_ROUTER_DW_URL = StartupConfig.get("risk.router.dw.url");
    private static final int SPLIT_COUNT = 100;
    
    private static String Black_Code = "'C002', 'C008', 'C009', 'C010', 'C011', 'C012', "
            + "'C018', 'C020', 'C021', 'C013', 'C014', 'C019', 'F001'";
    
    private static String Fraud_Code = "'F001','F001','F002','F003','F004','F005','F006','F007','F009','F010','F011',"
            + "'F012','F013','F014','F015','F016','F017','F018','F019','F020','F021','F022',"
            + "'F023','F024','F025','F026','F027','F028'";
    
    private static String getGroupInfoUserCount = 
            "select COUNT(Distinct case when app.Status >= 40 then eu.IdNumber end) as Total, "
            + "COUNT(Distinct case when app.Status >= 100 then eu.IdNumber end) as Approved, "
            + "COUNT(Distinct case when app.Status = 40 then eu.IdNumber end) as Rejected, "
            + "COUNT(Distinct case when app.Status < 40 then eu.IdNumber end) as Cancelled, "
            + "COUNT(Distinct case when app.Status = 600 then eu.IdNumber end) as Prepayment "
            + "from InstallmentApplicationObjects app "
            + "inner join EndUserExtensionObjects eu on app.UserId = eu.id "
            + "where eu.IdNumber is not null and app.Id in ( :appIdSet ) ";
    
    /*
     * 得到申请的借款总金额
     */
    private static String getGroupInfoTotalLoanAmount = 
            "select sum(principal)  from InstallmentApplicationObjects app  "
            + "where status >= 100 and id in ( :appIdSet )";
    
    /*
     * 得到最近3天发生申请的用户数量
     */
    private static String getGroupInfoUserRecentThreeDays =
            "select " +
            "COUNT(Distinct case when app.Status >= 40 then eu.IdNumber end) as Total, " +
            "COUNT(Distinct case when app.Status >= 100 then eu.IdNumber end)  as Approved, " +
            "COUNT(Distinct case when app.Status = 40 then eu.IdNumber  end) as Rejected " +
            "from InstallmentApplicationObjects app " +
            "inner join EndUserExtensionObjects eu on app.UserId = eu.id " +
            "where DATEDIFF(DAY, app.InstallmentStartedOn, GETDATE()) <= 3 and app.Id in ( :appIdSet ) ";

    /*
     * 得到最近7天发生申请的用户数量
     */
    private static String getGroupInfoUserRecentSevenDays =
            "select " +
            "COUNT(Distinct case when app.Status >= 40 then eu.IdNumber end)  as Total, " +
            "COUNT(Distinct case when app.Status >= 100 then eu.IdNumber  end)  as Approved, " +
            "COUNT(Distinct case when app.Status = 40 then eu.IdNumber  end) as Rejected " +
            "from InstallmentApplicationObjects app " +
            "inner join EndUserExtensionObjects eu on app.UserId = eu.id " +
            "where DATEDIFF(DAY, app.InstallmentStartedOn, GETDATE()) <= 7 and app.Id in ( :appIdSet ) ";

    /*
     * 得到最近1个月发生申请的用户数量
     */
    private static String getGroupInfoUserRecentOneMonth =
            "select " +
            "COUNT(Distinct case when app.Status >= 40 then eu.IdNumber end) as Total, " +
            "COUNT(Distinct case when app.Status >= 100 then eu.IdNumber end) as Approved, " +
            "COUNT(Distinct case when app.Status = 40 then eu.IdNumber end) as Rejected " +
            "from InstallmentApplicationObjects app " +
            "inner join EndUserExtensionObjects eu on app.UserId = eu.id " +
            "where DATEDIFF(Month, app.InstallmentStartedOn, GETDATE()) <= 1 and app.Id in ( :appIdSet ) ";

    /*
     * 得到最近3个月发生申请的用户数量
     */
    private static String getGroupInfoUserRecentThreeMonth =
            "select " +
            "COUNT(Distinct case when app.Status >= 40 then eu.IdNumber end) as Total, " +
            "COUNT(Distinct case when app.Status >= 100 then eu.IdNumber end) as Approved, " +
            "COUNT(Distinct case when app.Status = 40 then eu.IdNumber end) as Rejected " +
            "from InstallmentApplicationObjects app " +
            "inner join EndUserExtensionObjects eu on app.UserId = eu.id " +
            "where DATEDIFF(Month, app.InstallmentStartedOn, GETDATE()) <= 3 and app.Id in ( :appIdSet ) ";

    /*
     * 得到申请门店所在城市和城市出现次数、省份和省份出现的次数
     */
    private static String getGroupUserMaxProvinceAndCity =
            "select " +
            "t.cityName, T.cityCount, t.provinceName, T.provinceCount " +
            "from ( " +
            "select " +
            "cn2.Name as cityName, " +
            "count(cn2.code) over(partition by cn2.code) as cityCount," +
            "cn3.Name as provinceName, " +
            "count(cn3.code) over(partition by cn3.code) as provinceCount " +
            "from InstallmentApplicationObjects app " +
            "join MerchantStoreObjects ms on app.MerchantStoreId = ms.id " +
            "join CNAreaObjects cn1 on ms.CNAreaId = cn1.Id " +
            "join CNAreaObjects cn2 on cn1.ParentCode = cn2.Code " +
            "join CNAreaObjects cn3 on cn2.ParentCode = cn3.Code " +
            "where app.Id in ( :appIdSet ) "
            + "and not exists ( "
            + "select Id from InstallmentApplicationObjects app1  "
            + "where app.UserId = app1.UserId "
            + "and app.InstallmentStartedOn < app1.InstallmentStartedOn )" +
            ") T " +
            "where T.cityCount > 1 or T.provinceCount > 1 " +
            "group by cityName, provinceName, cityCount, provinceCount ";
    
    /*
     * 查看申请中拉警报的用户数量
     */
    private static String getGroupUserReportedRejectedCount = 
            "select COUNT(Distinct eu.IdNumber) from InstallmentApplicationObjects app "
            + "inner join EndUserExtensionObjects eu on app.UserId = eu.Id "
            + "inner join AffiliateOperationObjects af on app.Id = af.AppId "
            + "where app.id in ( :appIdSet) and af.Tag >= 20";
    
    /*
     * 查看被黑名单拒绝的用户数量
     */
    private static String getGroupUserBlackRejectedCount =             
            "SELECT count(distinct eu.IdNumber) FROM installmentapplicationobjects app  "
            + "JOIN ruleengineresultobjects r ON app.id = r.appid "
            + "JOIN ruleengineresultdetailsobjects rd ON rd.ruleengineresultid = r.Id "
            + "inner join EndUserExtensionObjects eu on app.UserId = eu.Id "
            + "WHERE rd.Message IN (  "
            + Black_Code
            + ")  AND app.id IN ( :appIdSet )  AND r.IsPassed = 0 ";
    
    /*
     * 查看被规则引擎拒绝申请的用户数量
     */
    private static String getGroupUserFraudRejectedCount =             
            "SELECT count(distinct eu.IdNumber) FROM installmentapplicationobjects app  "
            + "JOIN ruleengineresultobjects r ON app.id = r.appid "
            + "JOIN ruleengineresultdetailsobjects rd ON rd.ruleengineresultid = r.Id "
            + "inner join EndUserExtensionObjects eu on app.UserId = eu.Id "
            + "WHERE rd.Message IN (  "
            + Fraud_Code
            + ")  AND app.id IN ( :appIdSet ) AND r.IsPassed = 0 ";
    
    /*
     * 获取申请Id以及对应的身份证号
     */
    private static String getIdNumberByAppIdList = 
            "select app.Id, eu.IdNumber from InstallmentApplicationObjects app "
            + "inner join EndUserExtensionObjects eu on app.userid = eu.id "
            + "where IdNumber is not null AND app.id in ( :appIdSet)";
    
    public static void getStatisticsFromApps(List<Application> apps, GroupInfo groupInfo) {
        if(apps == null || apps.size() == 0 || groupInfo == null) {
            Logger.get().warn("Input parameters error!");
            return;
        }
        
        Set<String> appIdSets = apps.parallelStream()
                .filter( x -> x != null).map( x -> x.appId).collect(Collectors.toSet());
        if(appIdSets == null || appIdSets.isEmpty()) {
            Logger.get().warn("Filter apps error!");
            return;
        }
        
        Map<String, ?> params = CollectionUtils.mapOf("appIdSet", appIdSets);
        
        List<Integer> queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupInfoUserCount, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0, 0, 0, 0, 0));
        if(queryResult != null && queryResult.size() == 5) {
            groupInfo.groupInfoUserTotalCount =  queryResult.get(0);
            groupInfo.groupInfoUserApprovedCount =  queryResult.get(1);
            if(groupInfo.groupInfoUserTotalCount > 0 && groupInfo.groupInfoUserApprovedCount > 0 )  {
                groupInfo.groupInfoUserApprovedRate = new BigDecimal(groupInfo.groupInfoUserApprovedCount)
                        .divide(new BigDecimal(groupInfo.groupInfoUserTotalCount), 4, BigDecimal.ROUND_HALF_UP);
            } else {
                groupInfo.groupInfoUserApprovedRate = BigDecimal.ZERO;
            }
            
            groupInfo.groupInfoUserRejectedCount =  queryResult.get(2);
            
            if(groupInfo.groupInfoUserRejectedCount > 0 && groupInfo.groupInfoUserTotalCount > 0) {
                groupInfo.groupInfoUserRejectedRate = new BigDecimal(groupInfo.groupInfoUserRejectedCount)
                        .divide(new BigDecimal(groupInfo.groupInfoUserTotalCount), 4, BigDecimal.ROUND_HALF_UP);
            } else {
                groupInfo.groupInfoUserRejectedRate = BigDecimal.ZERO;
            }
            
            groupInfo.groupInfoUserCancelledCount =  queryResult.get(3);
            groupInfo.groupInfoUserPreRepayLoanCount = queryResult.get(4);
        }
                
        groupInfo.groupInfoUserLoanAmountTotal = 
                DatabaseApi.querySingleIntegerOrDefault(getGroupInfoTotalLoanAmount, params, 0);
        
        queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupInfoUserRecentThreeDays, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0, 0, 0));
        if(queryResult != null && queryResult.size() == 3) {
            groupInfo.groupInfoUserAppRecent3daysCount =  queryResult.get(0);
            groupInfo.groupInfoUserAproveRecent3daysCount =  queryResult.get(1);
            groupInfo.groupInfoUserRejectRecent3daysCount =  queryResult.get(2);
        }
        
        queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupInfoUserRecentSevenDays, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0, 0, 0));
        if(queryResult != null && queryResult.size() == 3) {
            groupInfo.groupInfoUserAppRecent7daysCount =  queryResult.get(0);
            groupInfo.groupInfoUserAproveRecent7daysCount =  queryResult.get(1);
            groupInfo.groupInfoUserRejectRecent7daysCount =  queryResult.get(2);
        }
        
        queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupInfoUserRecentOneMonth, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0, 0, 0));
        if(queryResult != null && queryResult.size() == 3) {
            groupInfo.groupInfoUserAppRecent1monthCount =  queryResult.get(0);
            groupInfo.groupInfoUserAproveRecent1monthCount =  queryResult.get(1);
            groupInfo.groupInfoUserRejectRecent1monthCount =  queryResult.get(2);
        }
        
        queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupInfoUserRecentThreeMonth, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0, 0, 0));
        if(queryResult != null && queryResult.size() == 3) {
            groupInfo.groupInfoUserAppRecent3monthsCount =  queryResult.get(0);
            groupInfo.groupInfoUserAproveRecent3monthCount =  queryResult.get(1);
            groupInfo.groupInfoUserRejectRecent3monthCount =  queryResult.get(2);
        }
        
        queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupUserBlackRejectedCount, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0));
        if(queryResult != null && queryResult.size() == 1) {
            groupInfo.groupInfoBlackListCount =  queryResult.get(0);
            if(groupInfo.groupInfoBlackListCount > 0 && groupInfo.groupInfoUserRejectedCount > 0 ) {
                groupInfo.groupInfoBlackListCountRate = new BigDecimal(groupInfo.groupInfoBlackListCount)
                        .divide(new BigDecimal(groupInfo.groupInfoUserRejectedCount), 4, BigDecimal.ROUND_HALF_UP);
            } else {
                groupInfo.groupInfoBlackListCountRate = BigDecimal.ZERO;
            }
        }
        
        queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupUserFraudRejectedCount, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0));
        if(queryResult != null && queryResult.size() == 1) {
            groupInfo.groupInfoFausd =  queryResult.get(0);
        }
        
        queryResult = DatabaseApi.querySingleResultOrDefault(
            getGroupUserReportedRejectedCount, params, INTEGERPAIR_EXTRACTOR, Arrays.asList(0));
        if(queryResult != null && queryResult.size() == 1) {
            groupInfo.groupInfoReportedRejectedCount =  queryResult.get(0);
            if(groupInfo.groupInfoReportedRejectedCount > 0 && groupInfo.groupInfoUserRejectedCount > 0 ) {
                groupInfo.groupInfoReportedRejectedCountRate = new BigDecimal(groupInfo.groupInfoReportedRejectedCount)
                        .divide(new BigDecimal(groupInfo.groupInfoUserRejectedCount), 4, BigDecimal.ROUND_HALF_UP);
            } else {
                groupInfo.groupInfoReportedRejectedCountRate = BigDecimal.ZERO;
            }
        }
        
        
        List<AreaInfo> areaInfoList = DatabaseApi.queryMultipleResults(
            getGroupUserMaxProvinceAndCity, params, AREA_EXTRACTOR);
        if(areaInfoList != null && !areaInfoList.isEmpty()) {
            Optional<Integer> maxCityCount = areaInfoList.parallelStream()
                    .map( x -> x.cityCount).max( (x, y) -> x.compareTo(y));
            if(maxCityCount.isPresent()) {
                Set<String> result = areaInfoList.parallelStream()
                        .filter( x -> x.cityCount == maxCityCount.get()).map( x -> x.cityName).collect(Collectors.toSet());
                if(result != null && !result.isEmpty()) {
                    groupInfo.groupInfoUserMainCity = result.toString();
                }
            }
            
            Optional<Integer> maxProvinceCount = areaInfoList.parallelStream()
                    .map( x -> x.provinceCount).max( (x, y) -> x.compareTo(y));
            if(maxProvinceCount.isPresent()) {
                Set<String> result = areaInfoList.parallelStream()
                        .filter( x -> x.provinceCount == maxProvinceCount.get()).map( x -> x.provinceName).collect(Collectors.toSet());
                if(result != null && !result.isEmpty()) {
                    groupInfo.groupInfoUserMainProvince = result.toString();
                }
            }
        }
        
        //Fmale
        List<List<String>> idNumberList = DatabaseApi.queryMultipleResults(
            getIdNumberByAppIdList, params,  DatabaseExtractors.TWO_STRING_EXTRACTOR);
        if(idNumberList == null || idNumberList.isEmpty()) {
            Logger.get().warn("Get idNumber is null or empty!");
            return;
        }
        groupInfo.groupInfoUserFemaleCount = (int) idNumberList.parallelStream()
            .map( x -> {
                if(x.size() == 2) {
                    return x.get(1);
                } else {
                    Logger.get().warn("input idnumber error!");
                    return StringUtils.EMPTY_STRING;
                }
            })
            .filter( y -> isFemale(y))
            .distinct()
            .count();
    }
    
    /*
     * 计算与还款相关的抱团字段
     */
    public static void getPaymentStatisticsFromApps(List<Application> apps, GroupInfo groupInfo) {
        if(apps == null || apps.size() == 0 || groupInfo == null) {
            Logger.get().warn("Input parameters error!");
            return;
        }
        
        List<String> appIdList = apps.parallelStream()
                .filter( x -> x != null).map( x -> x.appId).collect(Collectors.toList());
        
        //Map<String, PaymentTag> paymentTagMap = generatePaymentTagMap(appIdList);
        Map<String,OverDueInfoModel> overDueInfos = getOverDueInfo(appIdList);

        if(overDueInfos == null || overDueInfos.isEmpty()) {
            Logger.get().warn("Generate payment tag is null or empty!");
            return;
        }
        
        generatePaymentFieldForGroupInfo(groupInfo, overDueInfos, appIdList);
        
    }
    
    private static Map<String, PaymentTag> generatePaymentTagMap(List<String> appIdList) {        
        Map<String, List<PaymentInfoResponse>> response = getPaymentInfo(appIdList);
        if(response == null || response.isEmpty()) {
            return null;
        }
        
        Map<String, PaymentTag> paymentTagMap = new HashMap<>();
        for(Map.Entry<String, List<PaymentInfoResponse>> entry : response.entrySet()) {
            PaymentTag tag = new PaymentTag(); 
            int continueOverdueCount = 0;

            entry.getValue().sort( (x,y) -> x.getInstalmentNum().compareTo(y.getInstalmentNum()));
            for(PaymentInfoResponse responseEle : entry.getValue()) {
                if(responseEle.getInstalmentNum() == 1) {
                    tag.setFpd1Tag(calculatePaymentTag(responseEle,  1));
                    tag.setFpd7Tag(calculatePaymentTag(responseEle,  7));
                    tag.setFpd30Tag(calculatePaymentTag(responseEle,  30));
                } else if(responseEle.getInstalmentNum() == 2) {
                    tag.setSpd30Tag(calculatePaymentTag(responseEle,  30));
                } else if(responseEle.getInstalmentNum() == 3) {
                    tag.setTpd30Tag(calculatePaymentTag(responseEle,  30));
                } else if(responseEle.getInstalmentNum() == 4) {
                    tag.setQpd30Tag(calculatePaymentTag(responseEle,  30));
                }
                
                if(responseEle.getOwningTotal().compareTo(BigDecimal.ZERO) > 0) {
                    continueOverdueCount += 1;
                } else {
                    continueOverdueCount = 0;
                }
            }
            
            tag.setOverdueCount(continueOverdueCount);
            setDefaultPaymentTagTypeWhenNull(tag);
            paymentTagMap.put(entry.getKey(), tag);
        }
        return paymentTagMap;
    }
    
    private static void generatePaymentFieldForGroupInfo(GroupInfo groupInfo, Map<String,OverDueInfoModel> overDueMap, List<String> appIdList) {
    	if(overDueMap == null || overDueMap.isEmpty()) {
            return;
        }
        
        List<List<String>> IdNumberList = DatabaseApi.queryMultipleResults(
            getIdNumberByAppIdList, CollectionUtils.mapOf("appIdSet", appIdList),  DatabaseExtractors.TWO_STRING_EXTRACTOR);
        
        if(IdNumberList == null || IdNumberList.isEmpty()) {
            Logger.get().warn("Get idnumber is null or empty!");
            return;
        }
        
        Map<String, String> appIdNumberMap = new HashMap<>();
        IdNumberList.parallelStream().filter( x -> {
            if(x.size() == 2) {
                return true;
            } else {
                Logger.get().warn("Get idNumberList is error! " + x.toString());
                return false;
            }
        }).forEach( x -> {
            if(appIdNumberMap.containsKey(x.get(0))) {
                Logger.get().warn("appId has been put map!");
            } else {
                appIdNumberMap.put(x.get(0), x.get(1));
            }
        });
        // M3
        groupInfo.groupInfoUserM3PlusCount = (int) overDueMap.keySet().stream()
                .filter( x -> overDueMap.get(x).getM3() >= 3)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserM3PlusTotalCount = (int) overDueMap.keySet().stream()
                .filter( x -> overDueMap.get(x).getTpd30() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserM3PlusCount.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserApprovedCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserM3PlusRate = new BigDecimal(groupInfo.groupInfoUserM3PlusCount)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        // FPD1
        groupInfo.groupInfoUserFPD1Count = (int) overDueMap.keySet().stream()
                .filter( x -> overDueMap.get(x).getFpd1() == 300)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserFPD1TotalCount = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getFpd1() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserFPD1Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserFPD1TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFPD1Rate = new BigDecimal(groupInfo.groupInfoUserFPD1Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserFPD1TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FPD7
        groupInfo.groupInfoUserFPD7Count = (int) overDueMap.keySet().stream()
                .filter( x -> overDueMap.get(x).getFpd7() == 300)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserFPD7TotalCount = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getFpd7() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserFPD7Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserFPD7TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFPD7Rate = new BigDecimal(groupInfo.groupInfoUserFPD7Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserFPD7TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FPD30
        groupInfo.groupInfoUserFPD30Count = (int) overDueMap.keySet().stream()
                .filter( x -> overDueMap.get(x).getFpd30() == 300 )
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserFPD30TotalCount = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getFpd30() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserFPD30Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserFPD30TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFPD30Rate = new BigDecimal(groupInfo.groupInfoUserFPD30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserFPD30TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //SPD30
        groupInfo.groupInfoUserSPD30Count = (int) overDueMap.keySet().stream()
                .filter( x -> overDueMap.get(x).getSpd30() == 300)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserSPD30TotalCount = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getSpd30() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserSPD30Count > 0 && groupInfo.groupInfoUserSPD30TotalCount > 0 ) {
            groupInfo.groupInfoUserSPD30Rate = new BigDecimal(groupInfo.groupInfoUserSPD30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserSPD30TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //TPD30
        groupInfo.groupInfoUserTPD30Count = (int) overDueMap.keySet().stream()
                .filter( x -> overDueMap.get(x).getTpd30() == 300)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserTPD30TotalCount = (int) overDueMap.keySet().stream()

                .filter(x -> overDueMap.get(x).getTpd30() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserTPD30Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserTPD30TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserTPD30Rate = new BigDecimal(groupInfo.groupInfoUserTPD30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserTPD30TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }

        //FS30
        groupInfo.groupInfoUserFS30Count = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getFpd30() == 300 ||
                		overDueMap.get(x).getSpd30() == 300)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        groupInfo.groupInfoUserFS30TotalCount = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getSpd30() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        if(groupInfo.groupInfoUserFS30Count.compareTo(Integer.valueOf(0)) > 0 &&
        		groupInfo.groupInfoUserApprovedCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFS30Rate = new BigDecimal(groupInfo.groupInfoUserFS30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FST30
        groupInfo.groupInfoUserFST30Count = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getFpd30() == 300 ||
                		overDueMap.get(x).getSpd30() == 300 ||
                		overDueMap.get(x).getTpd30() == 300 )
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        groupInfo.groupInfoUserFST30TotalCount = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getTpd30() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        if(groupInfo.groupInfoUserFST30Count.compareTo(Integer.valueOf(0)) > 0 &&
        		groupInfo.groupInfoUserApprovedCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFST30Rate = new BigDecimal(groupInfo.groupInfoUserFST30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FSTQ30
        groupInfo.groupInfoUserFSTQ30Count = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getFpd30() == 300 ||
                		overDueMap.get(x).getSpd30() == 300 ||
                		overDueMap.get(x).getTpd30() == 300 ||
                		overDueMap.get(x).getQpd30() == 300)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        groupInfo.groupInfoUserFSTQ30TotalCount = (int) overDueMap.keySet().stream()
                .filter(x -> overDueMap.get(x).getQpd30() != 100)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        if(groupInfo.groupInfoUserFSTQ30Count.compareTo(Integer.valueOf(0)) > 0 &&
        		groupInfo.groupInfoUserApprovedCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFSTQ30Rate = new BigDecimal(groupInfo.groupInfoUserFSTQ30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
    }
    
    private static void generatePaymentFieldForGroupInfoOld(GroupInfo groupInfo, Map<String, PaymentTag> paymentTagMap, List<String> appIdList) {
        if(paymentTagMap == null || paymentTagMap.isEmpty()) {
            return;
        }
        
        List<List<String>> IdNumberList = DatabaseApi.queryMultipleResults(
            getIdNumberByAppIdList, CollectionUtils.mapOf("appIdSet", appIdList),  DatabaseExtractors.TWO_STRING_EXTRACTOR);
        
        if(IdNumberList == null || IdNumberList.isEmpty()) {
            Logger.get().warn("Get idnumber is null or empty!");
            return;
        }
        
        Map<String, String> appIdNumberMap = new HashMap<>();
        IdNumberList.parallelStream().filter( x -> {
            if(x.size() == 2) {
                return true;
            } else {
                Logger.get().warn("Get idNumberList is error! " + x.toString());
                return false;
            }
        }).forEach( x -> {
            if(appIdNumberMap.containsKey(x.get(0))) {
                Logger.get().warn("appId has been put map!");
            } else {
                appIdNumberMap.put(x.get(0), x.get(1));
            }
        });
        
        // M3
        groupInfo.groupInfoUserM3PlusCount = (int) paymentTagMap.keySet().stream()
                .filter( x -> paymentTagMap.get(x).getOverdueCount() >= 3)
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserM3PlusTotalCount = (int) paymentTagMap.keySet().stream()
                .filter( x -> !paymentTagMap.get(x).getTpd30Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserM3PlusCount.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserApprovedCount.compareTo(Integer.valueOf(0)) > 0 ) {
//            groupInfo.groupInfoUserFPD1Rate = new BigDecimal(groupInfo.groupInfoUserM3PlusCount)
            groupInfo.groupInfoUserM3PlusRate = new BigDecimal(groupInfo.groupInfoUserM3PlusCount)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        // FPD1
        groupInfo.groupInfoUserFPD1Count = (int) paymentTagMap.keySet().stream()
                .filter( x -> paymentTagMap.get(x).getFpd1Tag().equals(PaymentTagType.Overdue))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserFPD1TotalCount = (int) paymentTagMap.keySet().stream()
                .filter(x -> !paymentTagMap.get(x).getFpd1Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserFPD1Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserFPD1TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFPD1Rate = new BigDecimal(groupInfo.groupInfoUserFPD1Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserFPD1TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FPD7
        groupInfo.groupInfoUserFPD7Count = (int) paymentTagMap.keySet().stream()
                .filter( x -> paymentTagMap.get(x).getFpd7Tag().equals(PaymentTagType.Overdue))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserFPD7TotalCount = (int) paymentTagMap.keySet().stream()
                .filter(x -> !paymentTagMap.get(x).getFpd7Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserFPD7Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserFPD7TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFPD7Rate = new BigDecimal(groupInfo.groupInfoUserFPD7Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserFPD7TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FPD30
        groupInfo.groupInfoUserFPD30Count = (int) paymentTagMap.keySet().stream()
                .filter( x -> paymentTagMap.get(x).getFpd30Tag().equals(PaymentTagType.Overdue))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserFPD30TotalCount = (int) paymentTagMap.keySet().stream()
                .filter(x -> !paymentTagMap.get(x).getFpd30Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserFPD30Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserFPD30TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFPD30Rate = new BigDecimal(groupInfo.groupInfoUserFPD30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserFPD30TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //SPD30
        groupInfo.groupInfoUserSPD30Count = (int) paymentTagMap.keySet().stream()
                .filter( x -> paymentTagMap.get(x).getSpd30Tag().equals(PaymentTagType.Overdue))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        groupInfo.groupInfoUserSPD30TotalCount = (int) paymentTagMap.keySet().stream()
                .filter(x -> !paymentTagMap.get(x).getSpd30Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserSPD30Count > 0 && groupInfo.groupInfoUserSPD30TotalCount > 0 ) {
            groupInfo.groupInfoUserSPD30Rate = new BigDecimal(groupInfo.groupInfoUserSPD30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserSPD30TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //TPD30
        groupInfo.groupInfoUserTPD30Count = (int) paymentTagMap.keySet().stream()
                .filter( x -> paymentTagMap.get(x).getTpd30Tag().equals(PaymentTagType.Overdue))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
//        groupInfo.groupInfoUserSPD30TotalCount = (int) paymentTagMap.keySet().stream()
        groupInfo.groupInfoUserTPD30TotalCount = (int) paymentTagMap.keySet().stream()

                .filter(x -> !paymentTagMap.get(x).getTpd30Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        
        if(groupInfo.groupInfoUserTPD30Count.compareTo(Integer.valueOf(0)) > 0 && 
                groupInfo.groupInfoUserTPD30TotalCount.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserTPD30Rate = new BigDecimal(groupInfo.groupInfoUserTPD30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserTPD30TotalCount), 4, BigDecimal.ROUND_HALF_UP);
        }

        //FS30
        groupInfo.groupInfoUserFS30Count = (int) paymentTagMap.keySet().stream()
                .filter(x -> paymentTagMap.get(x).getFpd30Tag().equals(PaymentTagType.Overdue) ||
                    paymentTagMap.get(x).getSpd30Tag().equals(PaymentTagType.Overdue))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        groupInfo.groupInfoUserFS30TotalCount = (int) paymentTagMap.keySet().stream()
                .filter(x -> !paymentTagMap.get(x).getSpd30Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        if(groupInfo.groupInfoUserFS30Count.compareTo(Integer.valueOf(0)) > 0 ) {
            groupInfo.groupInfoUserFS30Rate = new BigDecimal(groupInfo.groupInfoUserFS30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FST30
        groupInfo.groupInfoUserFST30Count = (int) paymentTagMap.keySet().stream()
                .filter(x -> paymentTagMap.get(x).getFpd30Tag().equals(PaymentTagType.Overdue) ||
                    paymentTagMap.get(x).getSpd30Tag().equals(PaymentTagType.Overdue) ||
                    paymentTagMap.get(x).getTpd30Tag().equals(PaymentTagType.Overdue) )
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        groupInfo.groupInfoUserFST30TotalCount = (int) paymentTagMap.keySet().stream()
                .filter(x -> !paymentTagMap.get(x).getTpd30Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        if(groupInfo.groupInfoUserFST30Count.compareTo(Integer.valueOf(0)) > 0) {
//            groupInfo.groupInfoUserFS30Rate = new BigDecimal(groupInfo.groupInfoUserFS30Count)
            groupInfo.groupInfoUserFST30Rate = new BigDecimal(groupInfo.groupInfoUserFST30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
        
        //FSTQ30
        groupInfo.groupInfoUserFSTQ30Count = (int) paymentTagMap.keySet().stream()
                .filter(x -> paymentTagMap.get(x).getFpd30Tag().equals(PaymentTagType.Overdue) ||
                    paymentTagMap.get(x).getSpd30Tag().equals(PaymentTagType.Overdue) ||
                    paymentTagMap.get(x).getTpd30Tag().equals(PaymentTagType.Overdue) ||
                    paymentTagMap.get(x).getQpd30Tag().equals(PaymentTagType.Overdue))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
        groupInfo.groupInfoUserFSTQ30TotalCount = (int) paymentTagMap.keySet().stream()
                .filter(x -> !paymentTagMap.get(x).getQpd30Tag().equals(PaymentTagType.NoProference))
                .map( y -> appIdNumberMap.get(y))
                .distinct()
                .count();
//        if(groupInfo.groupInfoUserFS30Count.compareTo(Integer.valueOf(0)) > 0 ) {
        if(groupInfo.groupInfoUserFSTQ30Count.compareTo(Integer.valueOf(0)) > 0 ) {
//            groupInfo.groupInfoUserFS30Rate = new BigDecimal(groupInfo.groupInfoUserFS30Count)
            groupInfo.groupInfoUserFSTQ30Rate = new BigDecimal(groupInfo.groupInfoUserFSTQ30Count)
                    .divide(new BigDecimal(groupInfo.groupInfoUserApprovedCount), 4, BigDecimal.ROUND_HALF_UP);
        }
    }
    
    /**
     * 判断输入的身份证号是否为女性
     * 判断逻辑：如果身份证号为18为，则看17为是否为偶数。如果身份证号为15位，则看15位是否为偶数
     * @param idNumber
     * @return
     */
    private static Boolean isFemale(String idNumber) {
        if(idNumber.length() == 18) {
            try{
                int intValue = Integer.parseInt(String.valueOf(idNumber.charAt(16)));
                return ( intValue % 2 ) == 0 ? true : false;
            } catch (NumberFormatException ex) {
                Logger.get().warn("Format idnumber 17 char error! origin idnumer " + idNumber , ex);
                return false;
            }
        } else if(idNumber.length() == 15) {
            try{
                int intValue = Integer.parseInt(String.valueOf(idNumber.charAt(14)));
                return ( intValue % 2 ) == 0 ? true : false;
            } catch (NumberFormatException ex) {
                Logger.get().warn("Format idnumber 15 char error! origin idnumber " + idNumber , ex);
                return false;
            }
        } else {
            Logger.get().warn("Format idnumber error! " + idNumber);
            return false;
        }
    }
    
    /**
     * 如果输入的PaymenTag中类型为PaymentTagType的变量为Null，则将其设置为NoPreference
     * @param tag
     */
    private static void setDefaultPaymentTagTypeWhenNull(PaymentTag tag) {
        if(tag == null) {
            Logger.get().warn("Input parameters is null!");
            return;
        }
        PropertyDescriptor pd = null;
        for(Field field : tag.getClass().getDeclaredFields()) {
            try {
                if(field.getType().equals(PaymentTagType.class)) {
                    pd = new PropertyDescriptor(field.getName(), tag.getClass());
                    Method gM = pd.getReadMethod();
                    Object type = gM.invoke(tag, new Object[] {});
                    if(type == null) {
                        Method sM = pd.getWriteMethod();
                        sM.invoke(tag, PaymentTagType.NoProference);
                    }
                }
            } catch (IntrospectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.get().warn("Set Payment tag error!" , ex);
            }
        }
    }
    
    /*
     * 依据还款信息，计算当期的标签
     * 逻辑为：如果OwningTotal不为0，则判断DueDay和当前时间的差值，依据差值判断标签
     * 如果OwningTotal为0，则判断PayDay是否在DueDay之前，在之前，则表明正常还款，否则计算PayDay与DueDay的差值，并根据差值打标签
     */
    private static PaymentTagType calculatePaymentTag(PaymentInfoResponse response, int compareDay) {
        try{
            if(response.getOwningTotal().compareTo(BigDecimal.ZERO) == 0) {
                if(response.getPayDate().compareTo(response.getDueDate()) <= 0) {
                    return PaymentTagType.Normal;
                } else {
                    int overdueDays = DateTimeUtils.getDayDiff(
                        new Date(response.getDueDate()), new Date(response.getPayDate()));
                    return overdueDays >= compareDay ? PaymentTagType.Overdue : PaymentTagType.Normal;
                }
            } else {
                int overdueDays = DateTimeUtils.getDayDiff(new Date(response.getDueDate()), new Date());
                return overdueDays >= compareDay ? PaymentTagType.Overdue : PaymentTagType.NoProference;
            }
        }
        catch(Exception ex) {
            Logger.get().error("Calculate Payment tag error! response : " + new Gson().toJson(response));
            return null;
        }
    }

    /*
     * 获取申请的还款信息，由risk-router-dw提供
     */
    private static Map<String, OverDueInfoModel> getOverDueInfo(List<String> appIds) {
    	if(appIds == null || appIds.isEmpty()) {
            Logger.get().warn("Input appId is null or empty!");
            return null;
        }
    	if(RISK_ROUTER_DW_URL ==null || "".equals(RISK_ROUTER_DW_URL)){
    		Logger.get().warn("risk-router-dw-url is null,please add Url in configuration file !");
    		return null;
    	}
    	Map<String, OverDueInfoModel> result = new HashMap<>();
        Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
        try{
            String json = HttpClientApi.postString(RISK_ROUTER_DW_URL+"/overdueinfo/appIdList", new Gson().toJson(appIds), header);
            ResponseModel<List<OverDueInfoModel>> response = new Gson().fromJson(json, new TypeToken<ResponseModel<List<OverDueInfoModel>>>() {
            }.getType());

            if (response != null && response.getCode() == 0) {
                Logger.get().info("get overdue info is success ! response is"+ new Gson().toJson(response));
                List<OverDueInfoModel> overDueList = response.getData();
                if(overDueList !=null && !overDueList.isEmpty()){
                	overDueList.forEach(x -> {
                    	result.put(x.getAppId(), x);
                    });
                }
            }else{
                Logger.get().info("Get overdue info is null or empty!");
                return null;
            }
        } catch(Exception ex) {
            Logger.get().warn("get overdue info error!" + ex);
        }
        return  result;
    }
    
    /*
     * 获取申请的还款信息，如果申请的数量超过阈值，则分拆请求
     */
    private static Map<String, List<PaymentInfoResponse>> getPaymentInfo(List<String> appIds) {
        if(appIds == null || appIds.isEmpty()) {
            Logger.get().warn("Input appId is null or empty!");
            return null;
        }
        Map<String, List<PaymentInfoResponse>> result = new HashMap<>();
        List<List<String>> splitAppidList = new ArrayList<>();
        for(int i = 0 ; i < appIds.size(); i += SPLIT_COUNT) {
            if( (i + SPLIT_COUNT) > appIds.size()) {
                splitAppidList.add(appIds.subList(i, appIds.size()));
            } else {
                splitAppidList.add(appIds.subList(i, i + SPLIT_COUNT));    
            }
        }
        
        splitAppidList.parallelStream().forEach( x -> {
            String response =  HttpClientApi.postJson(PAYMENT_URL,
                CollectionUtils.mapOf("appIds", x, "date", new Date().getTime()), "");
            if(StringUtils.isNullOrWhiteSpaces(response)) {
                Logger.get().info("Get payment info is null or empty! appIdList : " + x.toString());
                return;
            }
            
            try{
                result.putAll(new Gson().fromJson(response, 
                    new TypeToken<Map<String, List<PaymentInfoResponse>>>() { }.getType()));
            } catch(Exception ex) {
                Logger.get().warn("Parse payment response error! origin response : " + response, ex);
            }
        });
        return result;
    }
    
    private static RowMapper<AreaInfo> AREA_EXTRACTOR = new RowMapper<AreaInfo>() {
        @Override
        public AreaInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            AreaInfo areaInfo = new AreaInfo();
                areaInfo.setCityName(rs.getString("cityName"));
                areaInfo.setCityCount(rs.getInt("cityCount"));
                areaInfo.setProvinceCount(rs.getInt("provinceCount"));
                areaInfo.setProvinceName(rs.getString("provinceName"));            
            return areaInfo;
        }
    };
    
    /**
     * Return all 
     */
    private static RowMapper<List<Integer>> INTEGERPAIR_EXTRACTOR = new RowMapper<List<Integer>>() {
        @Override
        public List<Integer> mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
            List<Integer> resultList = new ArrayList<Integer>();
            ResultSetMetaData md = resultSet.getMetaData();
            int colummns = md.getColumnCount();
            
            for(int i = 1 ; i <= colummns; i++ ) {
                resultList.add(resultSet.getInt(i));
            }            
            return resultList;
        }
    };
    
    private static class AreaInfo {
        private String cityName;
        private Integer cityCount;
        private String provinceName;
        private Integer provinceCount;
        public String getCityName() {
            return cityName;
        }
        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
        public Integer getCityCount() {
            return cityCount;
        }
        public void setCityCount(Integer cityCount) {
            this.cityCount = cityCount;
        }
        public String getProvinceName() {
            return provinceName;
        }
        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }
        public Integer getProvinceCount() {
            return provinceCount;
        }
        public void setProvinceCount(Integer provinceCount) {
            this.provinceCount = provinceCount;
        }
        
        
    }
}
