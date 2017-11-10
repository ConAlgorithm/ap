/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version PayDetails.java, V1.0 2016年10月17日 下午8:06:51
 */
public class PayDetails {
    
    private BigDecimal principal;
    
    private BigDecimal interest;
     
    //服务费项，key值映射见"ExtendFeeEnum" //http://confluence.win.fenqi.im/pages/viewpage.action?pageId=2134404#id-清结算apimodel-服务费键值映射关系
    private Map<String, BigDecimal> extendFees;
    
    //还款日
    private Date dueDate;
     
    //当前是第几期
    private Integer instalmentNum;

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Map<String, BigDecimal> getExtendFees() {
        return extendFees;
    }

    public void setExtendFees(Map<String, BigDecimal> extendFees) {
        this.extendFees = extendFees;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getInstalmentNum() {
        return instalmentNum;
    }

    public void setInstalmentNum(Integer instalmentNum) {
        this.instalmentNum = instalmentNum;
    }
    
    
}
