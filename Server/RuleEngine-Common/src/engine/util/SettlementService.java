package engine.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.httpclient.HttpClientApi;
import engine.rule.model.ResponseModel;
import engine.rule.model.SettlementModel;
import engine.rule.model.creator.app.domain.InstallmentStatus;
import engine.rule.model.creator.app.domain.PayDetails;
import engine.rule.model.creator.app.domain.RepaymentScheduleById;
import engine.rule.model.creator.app.domain.RepaymentTableResult;


public class SettlementService {
    public static String routerdwUrl = StartupConfig.get("risk.router.dw.url");

    private static final int PAGE_SIZE = 1000;

    public static class PaymentInfo {
        /** 欠款金额  **/
        private BigDecimal owningTotal;
        /** 欠款属于第几期  **/
        private Integer instalmentNum;
        /** 当前期的还款日  **/
        private Long dueDate;
        /** 当前期的还款日  **/
        private Long payDate;

        public BigDecimal getOwningTotal() {
            return owningTotal;
        }

        public void setOwningTotal(BigDecimal owningTotal) {
            this.owningTotal = owningTotal;
        }

        public Integer getInstalmentNum() {
            return instalmentNum;
        }

        public void setInstalmentNum(Integer instalmentNum) {
            this.instalmentNum = instalmentNum;
        }

        public Long getDueDate() {
            return dueDate;
        }

        public void setDueDate(Long dueDate) {
            this.dueDate = dueDate;
        }

        public Long getPayDate() {
            return payDate;
        }

        public void setPayDate(Long payDate) {
            this.payDate = payDate;
        }
    }

    //最大还款
    public static BigDecimal maxRepayment(String appId) {

        String ipPort = StartupConfig.get("settlement.rest.host");
        BigDecimal maxRepayment = new BigDecimal(0);
        RepaymentScheduleById gson = null;
        try {
             gson = HttpClientApi.getGson(ipPort + "settlement/product/all-info?appId=" + appId, RepaymentScheduleById.class);
        } catch (Exception e) {
            Logger.get().info("call settlement occur user cancel exception");
            //因为用户申请取消，信息未进账务数据流，直接给一个默认值
            return maxRepayment;
        }
        List<PayDetails> payDetails = gson.getPayDetails();
        if (payDetails != null && payDetails.size()>0) {
            for (PayDetails e : payDetails) {
                Map<String, BigDecimal> extendFees = e.getExtendFees();
                if(extendFees !=null && extendFees.size()>0){
                    BigDecimal repayment1 = ((e.getPrincipal() == null ? new BigDecimal(0)
                        : e.getPrincipal())
                            .add((e.getInterest() == null ? new BigDecimal(0) : e.getInterest())));
                    BigDecimal tech_fee = extendFees.get("tech_fee") == null ? new BigDecimal(0)
                        : extendFees.get("tech_fee");
                    BigDecimal value_fee = extendFees.get("value_fee") == null ? new BigDecimal(0)
                        : extendFees.get("value_fee");
                    BigDecimal client_fee = extendFees.get("client_fee") == null ? new BigDecimal(0)
                        : extendFees.get("client_fee");
                    BigDecimal service_fee = extendFees.get("service_fee") == null ? new BigDecimal(0)
                        : extendFees.get("service_fee");
                    BigDecimal addvanced_fee = extendFees.get("addvanced_fee") == null
                        ? new BigDecimal(0) : extendFees.get("addvanced_fee");
                    BigDecimal repayment = repayment1.add(tech_fee).add(value_fee).add(client_fee)
                        .add(service_fee).add(addvanced_fee);
                    if (repayment.compareTo(maxRepayment) > 0) {
                        maxRepayment = repayment;
                    }
                }
            }
        }
        return maxRepayment;
    }

    public static Map<String, List<PaymentInfo>> queryPaymentInfo(List<String> appIdList) {
        Map<String, List<PaymentInfo>> owingInfoList = new HashMap<>();
        String url = StartupConfig.get("settlement.rest.host") + "settlement/batch/payment-info";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        long now = System.currentTimeMillis();
        // 目标接口仅支持1000条查询，需要组织1000条以下的参数
        for (int pageIndex = 1; (pageIndex - 1) * PAGE_SIZE < appIdList.size(); pageIndex++) {
            List<String> appIdList_tmp;
            if (appIdList.size() > pageIndex * PAGE_SIZE) {
                appIdList_tmp = appIdList.subList((pageIndex - 1) * PAGE_SIZE,
                    pageIndex * PAGE_SIZE);
            } else {
                appIdList_tmp = appIdList.subList((pageIndex - 1) * PAGE_SIZE, appIdList.size());
            }
            Map<String, Object> map = new HashMap<>();
            map.put("appIds", appIdList_tmp);
            map.put("date", now);
            Logger.get().info("begin request for " + url);
            try {
                String res = HttpClientApi.postJson(url, map, "");
                Map<String, List<PaymentInfo>> resMap = gson.fromJson(res,
                    new TypeToken<Map<String, List<PaymentInfo>>>() {
                    }.getType());
                owingInfoList.putAll(resMap);
            } catch (RuntimeException e) {
                Logger.get().warn("requst url:" + url + " with error: " + e.getMessage());
            }
        }
        return owingInfoList;
    }

    public static int getOverDueAppCount(Map<String, List<PaymentInfo>> paymentInfoMap,
                                         int overdueDays) {
        int count = 0;
        for (String appId : paymentInfoMap.keySet()) {
            List<PaymentInfo> owingInfo = paymentInfoMap.get(appId);
            for (PaymentInfo paymentInfo : owingInfo) {
                // 应还款日
                DateTime dueDate = new DateTime(paymentInfo.dueDate);
                // 实际还款日
                DateTime payDate = new DateTime();
                // 如果尚未还款以今天计算
                if (paymentInfo.payDate != null) {
                    payDate = new DateTime(paymentInfo.payDate);
                }
                int datediff = Days.daysBetween(dueDate, payDate).getDays();
                // 逾期天数大于5天就算逾期（overdueDays = 5）
                if (datediff >= overdueDays) {
                    count++;
                }
            }
        }
        return count;
    }

    public static List<String> getOverDueAppIdWithInstalmentNum(Map<String, List<PaymentInfo>> paymentInfoMap,
                                                                int overdueDays,
                                                                int instalmentNum) {
        List<String> appIdList = new ArrayList<>();
        for (String appId : paymentInfoMap.keySet()) {
            List<PaymentInfo> paymentInfoList = paymentInfoMap.get(appId);
            for (PaymentInfo paymentInfo : paymentInfoList) {
                // 仅考虑第一期（instalmentNum = 1）
                if (paymentInfo.instalmentNum == instalmentNum) {
                    // 应还款日
                    DateTime dueDate = new DateTime(paymentInfo.dueDate);
                    // 实际还款日
                    DateTime payDate = new DateTime();
                    // 如果尚未还款以今天计算
                    if (paymentInfo.payDate != null) {
                        payDate = new DateTime(paymentInfo.payDate);
                    }
                    int datediff = Days.daysBetween(dueDate, payDate).getDays();
                    // 逾期天数大于7天就返回appId（overdueDays = 7）
                    if (datediff >= overdueDays) {
                        appIdList.add(appId);
                    }
                    break;
                }
            }
        }
        return appIdList;
    }

    public static List<String> getNotOverDueAppIdWithInstalmentNum(Map<String, List<PaymentInfo>> paymentInfoMap,
                                                                   int overdueDays,
                                                                   int instalmentNum) {
        List<String> appIdList = new ArrayList<>();
        for (String appId : paymentInfoMap.keySet()) {
            List<PaymentInfo> paymentInfoList = paymentInfoMap.get(appId);
            for (PaymentInfo paymentInfo : paymentInfoList) {
                // 仅考虑第一期（instalmentNum = 1）
                if (paymentInfo.instalmentNum == instalmentNum) {
                    // 应还款日
                    DateTime dueDate = new DateTime(paymentInfo.dueDate);
                    // 实际还款日
                    DateTime payDate = new DateTime();
                    // 如果尚未还款以今天计算
                    if (paymentInfo.payDate != null) {
                        payDate = new DateTime(paymentInfo.payDate);
                    }
                    int datediff = Days.daysBetween(dueDate, payDate).getDays();
                    // 逾期天数大于0小于7天就返回appId（overdueDays = 7）
                    if (datediff > 0 && datediff < overdueDays) {
                        appIdList.add(appId);
                    }
                    break;
                }
            }
        }
        return appIdList;
    }

    //通过appId得到还款状态
    public static InstallmentStatus getStatus(String appId) {

        String ipPort = StartupConfig.get("settlement.rest.host");
        InstallmentStatus gson = null;
        try {
            gson = HttpClientApi.getGson(ipPort + "settlement/status?appId=" + appId,
                InstallmentStatus.class);
        } catch (Exception e) {
            Logger.get().error("getStatus exception", e);
        }

        if (gson == null) {
            Logger.get().error("getStatus result is null");
        }
        return gson;
    }

    //通过appId得到用户贷后还款计划表
    @SuppressWarnings("unused")
    public static Map<Integer,RepaymentTableResult> getRepaymentTable(String appId) {

        String ipPort = StartupConfig.get("settlement.rest.host");
        HashMap<Integer,RepaymentTableResult> gson=new HashMap<Integer,RepaymentTableResult>();
        try {
            String string = HttpClientApi.get(ipPort+"settlement/repayment-table?appId="+appId);
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
             gson = mapper.readValue(string, getCollectionType(mapper,HashMap.class,Integer.class,RepaymentTableResult.class));
            
        } catch (Exception e) {
            Logger.get().error("getStatus exception",e);
        }
         RepaymentTableResult repaymentTableResult = gson.get(1);
        if(gson==null){
            Logger.get().error("getStatus result is null");
        }
 
       return   gson;
    }

    /**   
     * 获取泛型的Collection Type  
     * @param collectionClass 泛型的Collection   
     * @param elementClasses 元素类   
     * @return JavaType Java类型   
     * @since 1.0   
     */
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass,
                                             Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
    
    /**
     * <p>〈获取最大还款〉</p>
     * 
     * @param appId
     * @return
     */
    public static BigDecimal getMaxRepayment(String appId){
        BigDecimal maxRepayment = new BigDecimal(0);
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return maxRepayment;
        }
        try {
            InstallmentApplicationObject installmentApplicationObject = InstallmentApplicationDao.getLastInstallmentApplicationObjectsById(appId);
            if(installmentApplicationObject!=null&&installmentApplicationObject.getId()!=null){
                //调接口获取数据
                String json = HttpClientApi.get(routerdwUrl + "/settlement/" + installmentApplicationObject.getId());
                ResponseModel<SettlementModel> response = new Gson().fromJson(json, new TypeToken<ResponseModel<SettlementModel>>() {
                }.getType());
                if (response != null && response.getCode() == 0) {
                    Logger.get().info("getSettlementModel is success ! response is " + new Gson().toJson(response));
                    SettlementModel model =response.getData();
                    if(model!=null&&model.getMaxRepayment()!=null){
                        return model.getMaxRepayment();
                    }
                }                        
            }
        } catch (Exception e) {
            Logger.get().error(String.format("getMaxRepayment error! AppId:%s", appId), e);
        }
        return maxRepayment;        
    }
    
    /**
     * <p>〈根据app列表获取逾期单数〉</p>
     * 
     * @param appList
     * @return
     */
    public static int getNewOverDueAppCount(List<String> appList){
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return 0;
        }
        if (appList == null || appList.isEmpty()) {
            return 0;
        }
        int retryCount = 2;
        Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
        for (int i = 0; i < retryCount; i++) {
            try {
                //调接口获取数据
                String json = HttpClientApi.postString(routerdwUrl + "/settlement/count/appIdList", new Gson().toJson(appList), header);
                ResponseModel<Integer> response = new Gson().fromJson(json, new TypeToken<ResponseModel<Integer>>() {
                }.getType());
                if (response != null && response.getCode() == 0) {
                    Logger.get().info("getNewOverDueAppCount is success ! response is " + new Gson().toJson(response));
                    if (response.getData() != null) {
                        return response.getData();
                    }
                }
                return 0;
            } catch (Exception e) {
                if(i == 1){
                    Logger.get().error(String.format("getNewOverDueAppCount error! "), e);
                    return 0; 
                }
                Logger.get().warn("getNewOverDueAppCount need retry");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    Logger.get().error("getNewOverDueAppCount timeUnit has error",e1);
                }  
            }
        }
        return 0; 
    }
    
    /**
     * <p>〈根据appId列表查询对应appId是否首逾7天〉</p>
     * 
     * @param appList
     * @return
     */
    public static Map<String, Integer> getOverDueNdaysId(List<String> appList){
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return null;
        }
        if (appList == null || appList.isEmpty()) {
            return null;
        }
        Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
        try {
            String json = HttpClientApi.postString(routerdwUrl+"/settlement/overDueNdays/appIdList", new Gson().toJson(appList), header);
            ResponseModel<Map<String, Integer>> response = new Gson().fromJson(json, new TypeToken<ResponseModel<Map<String, Integer>>>() {
            }.getType()); 
            if (response != null && response.getCode() == 0) {
                Logger.get().info("getOverDueNdaysId is success ! response is " + new Gson().toJson(response)); 
                Map<String, Integer> map = response.getData();
                if(map!=null&&map.size()>0){
                    return map;
                }
            }
        } catch (Exception e) {
            Logger.get().error(String.format("getOverDueNdaysId error! "), e);
        }
        return null; 
    }
    
    
}
