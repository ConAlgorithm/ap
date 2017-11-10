/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

import java.util.List;

/**
 * 〈JXLV4ResponseModel〉
 *
 * @author hwei
 * @version JXLV4ResponseModel.java, V1.0 2017年2月4日 下午4:51:41
 */
public class JXLV4ResponseModel {
    private UserInfoCheck x_JXL_ReportData_UserInfoCheck;// 用户信息检测
    private List<ApplicationCheckPointItem> x_JXL_ReportData_ApplicationCheck;// 用户申请表检测
    private List<BehaviorCheck> x_JXL_ReportData_BehaviorCheck;// 用户行为检测
    private List<CellBehavior> x_JXL_ReportData_CellBehavior;// 运营商数据整理
    private List<ContactRegion> x_JXL_ReportData_ContactRegion;// 联系人区域汇总
    private List<ContactList> x_JXL_ReportData_ContactList;// 运营商联系人通话详情
    private List<MainService> x_JXL_ReportData_MainService;// 常用服务
    private List<DeliverAddress> x_JXL_ReportData_DeliverAddress;// 电商地址分析
    private List<EbusinessExpense> x_JXL_ReportData_EbusinessExpense;// 电商月消费
    private List<CollectionContact> x_JXL_ReportData_CollectionContact;// 联系人名单
    private List<TripInfo> x_JXL_ReportData_TripInfo;// 出行分析
    private Integer x_JXL_ReportData_ContactRegion_Count;// JXL联系地区总量
    private Integer x_JXL_ReportData_Contact_Count;// JXL号码总量
    private Integer x_JXL_ReportData_CallOut_Length;// JXL电话呼出时间总量
    private Integer x_JXL_ReportData_CallOut_Count;// JXL电话呼出总量
    private Integer x_JXL_ReportData_CallIn_Length;// JXL电话呼入时间总量
    private Integer x_JXL_ReportData_CallIn_Count;// JXL电话呼入总量
    private Integer x_JXL_ReportData_DataExistMonth_Count;// JXL通话月份
    private Integer x_JXL_ReportData_ContactNumber_Count;// JXL去重的联系人手机号
    private String x_JXL_ReportData_IsRealAuthenticated;// JXL手机号码是否本人实名认证
    private String x_JXL_ReportData_IsProviderInfoMatch;// JXL运营商登记人身份证号码和姓名是否匹配
    private String x_JXL_ReportData_IsAlwaysPowerOff;// JXL长时间关机
    private String x_JXL_ReportData_CallLoanPhone;// JXL是否拨打贷
    private String x_JXL_ReportData_CallFinancePhone;// JXL是否拨打金融
    private String x_JXL_ReportData_CallJieXinPhone;// JXL是否拨打捷信
    private Integer x_JXL_ReportData_regContactPhoneInJXLNum;// 联系人在JXL占比
    
    public UserInfoCheck getX_JXL_ReportData_UserInfoCheck() {
        return x_JXL_ReportData_UserInfoCheck;
    }
    public void setX_JXL_ReportData_UserInfoCheck(UserInfoCheck x_JXL_ReportData_UserInfoCheck) {
        this.x_JXL_ReportData_UserInfoCheck = x_JXL_ReportData_UserInfoCheck;
    }
    public List<ApplicationCheckPointItem> getX_JXL_ReportData_ApplicationCheck() {
        return x_JXL_ReportData_ApplicationCheck;
    }
    public void setX_JXL_ReportData_ApplicationCheck(List<ApplicationCheckPointItem> x_JXL_ReportData_ApplicationCheck) {
        this.x_JXL_ReportData_ApplicationCheck = x_JXL_ReportData_ApplicationCheck;
    }
    public List<BehaviorCheck> getX_JXL_ReportData_BehaviorCheck() {
        return x_JXL_ReportData_BehaviorCheck;
    }
    public void setX_JXL_ReportData_BehaviorCheck(List<BehaviorCheck> x_JXL_ReportData_BehaviorCheck) {
        this.x_JXL_ReportData_BehaviorCheck = x_JXL_ReportData_BehaviorCheck;
    }
    public List<CellBehavior> getX_JXL_ReportData_CellBehavior() {
        return x_JXL_ReportData_CellBehavior;
    }
    public void setX_JXL_ReportData_CellBehavior(List<CellBehavior> x_JXL_ReportData_CellBehavior) {
        this.x_JXL_ReportData_CellBehavior = x_JXL_ReportData_CellBehavior;
    }
    public List<ContactRegion> getX_JXL_ReportData_ContactRegion() {
        return x_JXL_ReportData_ContactRegion;
    }
    public void setX_JXL_ReportData_ContactRegion(List<ContactRegion> x_JXL_ReportData_ContactRegion) {
        this.x_JXL_ReportData_ContactRegion = x_JXL_ReportData_ContactRegion;
    }
    public List<ContactList> getX_JXL_ReportData_ContactList() {
        return x_JXL_ReportData_ContactList;
    }
    public void setX_JXL_ReportData_ContactList(List<ContactList> x_JXL_ReportData_ContactList) {
        this.x_JXL_ReportData_ContactList = x_JXL_ReportData_ContactList;
    }
    public List<MainService> getX_JXL_ReportData_MainService() {
        return x_JXL_ReportData_MainService;
    }
    public void setX_JXL_ReportData_MainService(List<MainService> x_JXL_ReportData_MainService) {
        this.x_JXL_ReportData_MainService = x_JXL_ReportData_MainService;
    }
    public List<DeliverAddress> getX_JXL_ReportData_DeliverAddress() {
        return x_JXL_ReportData_DeliverAddress;
    }
    public void setX_JXL_ReportData_DeliverAddress(List<DeliverAddress> x_JXL_ReportData_DeliverAddress) {
        this.x_JXL_ReportData_DeliverAddress = x_JXL_ReportData_DeliverAddress;
    }
    public List<EbusinessExpense> getX_JXL_ReportData_EbusinessExpense() {
        return x_JXL_ReportData_EbusinessExpense;
    }
    public void setX_JXL_ReportData_EbusinessExpense(List<EbusinessExpense> x_JXL_ReportData_EbusinessExpense) {
        this.x_JXL_ReportData_EbusinessExpense = x_JXL_ReportData_EbusinessExpense;
    }
    public List<CollectionContact> getX_JXL_ReportData_CollectionContact() {
        return x_JXL_ReportData_CollectionContact;
    }
    public void setX_JXL_ReportData_CollectionContact(List<CollectionContact> x_JXL_ReportData_CollectionContact) {
        this.x_JXL_ReportData_CollectionContact = x_JXL_ReportData_CollectionContact;
    }
    public List<TripInfo> getX_JXL_ReportData_TripInfo() {
        return x_JXL_ReportData_TripInfo;
    }
    public void setX_JXL_ReportData_TripInfo(List<TripInfo> x_JXL_ReportData_TripInfo) {
        this.x_JXL_ReportData_TripInfo = x_JXL_ReportData_TripInfo;
    }
    public Integer getX_JXL_ReportData_ContactRegion_Count() {
        return x_JXL_ReportData_ContactRegion_Count;
    }
    public void setX_JXL_ReportData_ContactRegion_Count(Integer x_JXL_ReportData_ContactRegion_Count) {
        this.x_JXL_ReportData_ContactRegion_Count = x_JXL_ReportData_ContactRegion_Count;
    }
    public Integer getX_JXL_ReportData_Contact_Count() {
        return x_JXL_ReportData_Contact_Count;
    }
    public void setX_JXL_ReportData_Contact_Count(Integer x_JXL_ReportData_Contact_Count) {
        this.x_JXL_ReportData_Contact_Count = x_JXL_ReportData_Contact_Count;
    }
    public Integer getX_JXL_ReportData_CallOut_Length() {
        return x_JXL_ReportData_CallOut_Length;
    }
    public void setX_JXL_ReportData_CallOut_Length(Integer x_JXL_ReportData_CallOut_Length) {
        this.x_JXL_ReportData_CallOut_Length = x_JXL_ReportData_CallOut_Length;
    }
    public Integer getX_JXL_ReportData_CallOut_Count() {
        return x_JXL_ReportData_CallOut_Count;
    }
    public void setX_JXL_ReportData_CallOut_Count(Integer x_JXL_ReportData_CallOut_Count) {
        this.x_JXL_ReportData_CallOut_Count = x_JXL_ReportData_CallOut_Count;
    }
    public Integer getX_JXL_ReportData_CallIn_Length() {
        return x_JXL_ReportData_CallIn_Length;
    }
    public void setX_JXL_ReportData_CallIn_Length(Integer x_JXL_ReportData_CallIn_Length) {
        this.x_JXL_ReportData_CallIn_Length = x_JXL_ReportData_CallIn_Length;
    }
    public Integer getX_JXL_ReportData_CallIn_Count() {
        return x_JXL_ReportData_CallIn_Count;
    }
    public void setX_JXL_ReportData_CallIn_Count(Integer x_JXL_ReportData_CallIn_Count) {
        this.x_JXL_ReportData_CallIn_Count = x_JXL_ReportData_CallIn_Count;
    }
    public Integer getX_JXL_ReportData_DataExistMonth_Count() {
        return x_JXL_ReportData_DataExistMonth_Count;
    }
    public void setX_JXL_ReportData_DataExistMonth_Count(Integer x_JXL_ReportData_DataExistMonth_Count) {
        this.x_JXL_ReportData_DataExistMonth_Count = x_JXL_ReportData_DataExistMonth_Count;
    }
    public Integer getX_JXL_ReportData_ContactNumber_Count() {
        return x_JXL_ReportData_ContactNumber_Count;
    }
    public void setX_JXL_ReportData_ContactNumber_Count(Integer x_JXL_ReportData_ContactNumber_Count) {
        this.x_JXL_ReportData_ContactNumber_Count = x_JXL_ReportData_ContactNumber_Count;
    }
    public String getX_JXL_ReportData_IsRealAuthenticated() {
        return x_JXL_ReportData_IsRealAuthenticated;
    }
    public void setX_JXL_ReportData_IsRealAuthenticated(String x_JXL_ReportData_IsRealAuthenticated) {
        this.x_JXL_ReportData_IsRealAuthenticated = x_JXL_ReportData_IsRealAuthenticated;
    }
    public String getX_JXL_ReportData_IsProviderInfoMatch() {
        return x_JXL_ReportData_IsProviderInfoMatch;
    }
    public void setX_JXL_ReportData_IsProviderInfoMatch(String x_JXL_ReportData_IsProviderInfoMatch) {
        this.x_JXL_ReportData_IsProviderInfoMatch = x_JXL_ReportData_IsProviderInfoMatch;
    }
    public String getX_JXL_ReportData_IsAlwaysPowerOff() {
        return x_JXL_ReportData_IsAlwaysPowerOff;
    }
    public void setX_JXL_ReportData_IsAlwaysPowerOff(String x_JXL_ReportData_IsAlwaysPowerOff) {
        this.x_JXL_ReportData_IsAlwaysPowerOff = x_JXL_ReportData_IsAlwaysPowerOff;
    }
    public String getX_JXL_ReportData_CallLoanPhone() {
        return x_JXL_ReportData_CallLoanPhone;
    }
    public void setX_JXL_ReportData_CallLoanPhone(String x_JXL_ReportData_CallLoanPhone) {
        this.x_JXL_ReportData_CallLoanPhone = x_JXL_ReportData_CallLoanPhone;
    }
    public String getX_JXL_ReportData_CallFinancePhone() {
        return x_JXL_ReportData_CallFinancePhone;
    }
    public void setX_JXL_ReportData_CallFinancePhone(String x_JXL_ReportData_CallFinancePhone) {
        this.x_JXL_ReportData_CallFinancePhone = x_JXL_ReportData_CallFinancePhone;
    }
    public String getX_JXL_ReportData_CallJieXinPhone() {
        return x_JXL_ReportData_CallJieXinPhone;
    }
    public void setX_JXL_ReportData_CallJieXinPhone(String x_JXL_ReportData_CallJieXinPhone) {
        this.x_JXL_ReportData_CallJieXinPhone = x_JXL_ReportData_CallJieXinPhone;
    }
    public Integer getX_JXL_ReportData_regContactPhoneInJXLNum() {
        return x_JXL_ReportData_regContactPhoneInJXLNum;
    }
    public void setX_JXL_ReportData_regContactPhoneInJXLNum(Integer x_JXL_ReportData_regContactPhoneInJXLNum) {
        this.x_JXL_ReportData_regContactPhoneInJXLNum = x_JXL_ReportData_regContactPhoneInJXLNum;
    }

    
}
