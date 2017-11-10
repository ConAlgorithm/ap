/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.util.List;

/**
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version RepaymentScheduleById.java, V1.0 2016年10月17日 下午8:15:31
 */
public class RepaymentScheduleById {
    private String principal;
    private String appId;;
    private String firstPaybackDate;
    private String repayment;
    private String moneyTransferredDate;

    private ProductDetail productDetail;

    private  List<PayDetails>  payDetails;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getFirstPaybackDate() {
        return firstPaybackDate;
    }

    public void setFirstPaybackDate(String firstPaybackDate) {
        this.firstPaybackDate = firstPaybackDate;
    }

    public String getRepayment() {
        return repayment;
    }

    public void setRepayment(String repayment) {
        this.repayment = repayment;
    }

    public String getMoneyTransferredDate() {
        return moneyTransferredDate;
    }

    public void setMoneyTransferredDate(String moneyTransferredDate) {
        this.moneyTransferredDate = moneyTransferredDate;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public List<PayDetails> getPayDetails() {
        return payDetails;
    }

    public void setPayDetails(List<PayDetails> payDetails) {
        this.payDetails = payDetails;
    }

     
    
}
