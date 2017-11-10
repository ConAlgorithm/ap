/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

import java.util.List;

/**
 * 〈联系人相关信息〉
 *
 * @author hwei
 * @version CollectionContact.java, V1.0 2017年2月6日 下午2:19:44
 */
public class CollectionContact {
    // 联系人姓名
    private String contactName;
    // 最早出现时间
    private String beginDate;
    // 最晚出现时间
    private String endDate;
    // 电商送货总数
    private Integer totalCount;
    // 电商送货总金额
    private Float totalAmount;
    // 呼叫信息统计
    private List<ContactDetails> contactDetails;
    
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    public Float getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }
    public List<ContactDetails> getContactDetails() {
        return contactDetails;
    }
    public void setContactDetails(List<ContactDetails> contactDetails) {
        this.contactDetails = contactDetails;
    }

}
