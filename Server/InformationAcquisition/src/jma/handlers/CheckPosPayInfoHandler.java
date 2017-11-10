package jma.handlers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import jma.Configuration;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.models.SettlementModel;
import jma.util.SettlementClient;

public class CheckPosPayInfoHandler extends JobHandler{

    @Override
    public void execute(String appId) throws RetryRequiredException {

        // 在Cowfish中通过appId获取userId
        StringBuilder cowfishServiceUrl = new StringBuilder();
        cowfishServiceUrl.append(Configuration.getCowfishUrl());
        cowfishServiceUrl.append("/application/").append(appId);
        String appResponse = HttpClientApi.get(cowfishServiceUrl.toString());
        JSONObject appJson = JSONObject.fromObject(appResponse);
        String userId = appJson.getString("userId");
        
        // 根据新账务接口获取POS逾期信息
        StringBuilder url = new StringBuilder();
        url.append(Configuration.getPhoebusUrl()).append("settlement/pay-info?appId=");
        url.append(this.getPosAppIdByCLUserId(userId)).append("&date=").append(new Date().getTime());
        try {
        	String response = HttpClientApi.get(url.toString());
        	JSONObject json = JSONObject.fromObject(response);
          if(json.has("error")) {
              // 用户POS贷有问题，或者账务系统返回值有误，暂不处理
          }else{
              BigDecimal overdueAmount = new BigDecimal(json.getString("overDueAmount"));
              int overdueDays = json.getInt("overdueDays");
              Logger.get().info("The pos overdue info is: appId:" + appId + ", overdueAmount:" + overdueAmount
                                  + ", overdueDays:" + overdueDays);
              
              AppDerivativeVariableManager.addVariables(
                  new AppDerivativeVariable(
                      appId,
                      AppDerivativeVariableNames.CHECK_POS_OVERDUE_AMOUT,
                      overdueAmount));
              
              AppDerivativeVariableManager.addVariables(
                  new AppDerivativeVariable(
                      appId,
                      AppDerivativeVariableNames.CHECK_POS_OVERDUE_DAYS,
                      overdueDays));     
          }
		} catch (Exception e) {
			Logger.get().warn("还未放款，请放款之后再查询!!!");
		}
    }
    
    // 查询数据库，根据userId，获取有效的POS申请appId
    private String getPosAppIdByCLUserId(String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select id from dbo.InstallmentApplicationObjects  ");
        sql.append("where (InstalmentChannel = 1 or InstalmentChannel = 0) and MoneyTransferredOn is not null and userid=:userId");
        
        Map<String, ?> params = CollectionUtils.mapOf("userId", userId);

        return DatabaseApi.querySingleString(sql.toString(), params);
    }
    
 // POS还款金额返回值bean作为内部类参考使用
    class PayInfoResponse {
     
        //还款日应还金额（小于等于还款日的金额总和）
        private BigDecimal overDueAmount;
         
        //提前还款应还金额,如果不能提前还款则返回0
        private BigDecimal advancedPayAmount;
         
        //当前期应还金额（不考虑查询时间，会把数据库所有期数值为当期的金额都加上）
        private BigDecimal curRepaymentPayAmount;
         
        //逾期天数（逾期条目所在期数的还款日与查询天数的差值）
        private Integer overdueDays;

        public BigDecimal getOverDueAmount() {
            return overDueAmount;
        }

        public void setOverDueAmount(BigDecimal overDueAmount) {
            this.overDueAmount = overDueAmount;
        }

        public BigDecimal getAdvancedPayAmount() {
            return advancedPayAmount;
        }

        public void setAdvancedPayAmount(BigDecimal advancedPayAmount) {
            this.advancedPayAmount = advancedPayAmount;
        }

        public BigDecimal getCurRepaymentPayAmount() {
            return curRepaymentPayAmount;
        }

        public void setCurRepaymentPayAmount(BigDecimal curRepaymentPayAmount) {
            this.curRepaymentPayAmount = curRepaymentPayAmount;
        }

        public Integer getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(Integer overdueDays) {
            this.overdueDays = overdueDays;
        }
        
        
    }
    
    

}
