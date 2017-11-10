package jma.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import jma.models.OverDueInfoModel;
import jma.models.ResponseModel;
import jma.models.SettlementModel;

public class SettlementClient {
	
	private static final int PAGE_SIZE = 1000;
	public static String routerdwUrl = StartupConfig.get("risk.router.dw.url");
	
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

	public static int overdueApplicationCount(List<String> appIdList, int repaymentCount) {
		String url = StartupConfig.get("phoebus.rest.host") + "settlement/batch/payment-info";
		int count = 0;
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		long now = System.currentTimeMillis();
		// 目标接口仅支持1000条查询，需要组织1000条以下的参数
		for (int pageIndex = 1; (pageIndex - 1) * PAGE_SIZE < appIdList.size(); pageIndex ++) {
			List<String> appIdList_tmp;
			if (appIdList.size() > pageIndex * PAGE_SIZE) {
				appIdList_tmp = appIdList.subList((pageIndex - 1) * PAGE_SIZE, pageIndex * PAGE_SIZE);
			} else {
				appIdList_tmp = appIdList.subList((pageIndex - 1) * PAGE_SIZE, appIdList.size());
			}
			Map<String, Object> map = new HashMap<>();
			map.put("appIds", appIdList_tmp);
			map.put("date", now);
			Logger.get().info("begin request for " + url);
			try {
				String res = HttpClientApi.postJson(url, map, "");
				Map<String, List<PaymentInfo>> owingInfoList = gson.fromJson(res, new TypeToken<Map<String, List<PaymentInfo>>>(){}.getType());
				if (res != null) {
					for (String appId : owingInfoList.keySet()) {
						List<PaymentInfo> overdueInfo = owingInfoList.get(appId);
						long overdueCount = overdueInfo.stream().filter(m -> (m.getOwningTotal().compareTo(BigDecimal.ZERO) > 0)).count();
						if (overdueCount >= repaymentCount) {
							count ++;
						}
					}
				}
			} catch (RuntimeException e) {
				Logger.get().warn("requst url:" + url + " with error: " + e.getMessage());
			}
		}
		return count;
	}
	
	
	/**
	 * <p>〈通过数仓-账务接口查询〉</p>
	 * 
	 * @param appIdList
	 * @param repaymentCount
	 * @return
	 */
    public static int overdueApplicationCountNew(List<String> appIdList, int repaymentCount) {
        int count = 0;
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return count;
        }
        if(appIdList==null||appIdList.isEmpty()){
            return count;
        }
        Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
        try {
            String json = HttpClientApi.postString(routerdwUrl + "/overdueinfo/appIdList", new Gson().toJson(appIdList), header);
            ResponseModel<List<OverDueInfoModel>> response = new Gson().fromJson(json, new TypeToken<ResponseModel<List<OverDueInfoModel>>>() {
            }.getType());
            if (response != null && response.getCode() == 0) {
                Logger.get().info("getOverDueInfoModelList is success ! ");
                List<OverDueInfoModel> list = response.getData();
                if (list != null && list.size() > 0) {
                    for (OverDueInfoModel overDueInfoModel : list) {
                        if (overDueInfoModel.getM3() >= repaymentCount) {
                            count++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.get().error(String.format("overdueApplicationCountNew error! "), e);
        }

        return count;
    }
    
    
    public static SettlementModel getSettlementModel(String appId){
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return null;
        }
        try {
            //调接口获取数据
            String json = HttpClientApi.get(routerdwUrl + "/settlement/" + appId);
            ResponseModel<SettlementModel> response = new Gson().fromJson(json, new TypeToken<ResponseModel<SettlementModel>>() {
            }.getType());
            if (response != null && response.getCode() == 0) {
                Logger.get().info("getSettlementModel is success ! response is " + new Gson().toJson(response));
                return response.getData();
            }
        } catch (Exception e) {
            Logger.get().error("getSettlementModel function error,appId is " + appId, e);
        }
        return null;
        
    }
	
}
