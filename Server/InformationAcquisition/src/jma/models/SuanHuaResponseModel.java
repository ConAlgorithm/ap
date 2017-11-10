/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈算话征信〉
 *
 * @author dengw
 * @version SuanHuaResponseModel.java, V1.0 2017年8月22日 下午2:44:15
 */
public class SuanHuaResponseModel {
    //业务流水
    private String appNum;
    //业务类型
    private String frdCheckType;
    //申请日期
    private String appDate;
    //算话流水
    private String appNo;
    //申请数据校验结果
    private String appValid;
    //格式检验报警
    private String appWarn;
    //欺诈得分
    private String appRst;
    //欺诈情况说明
    private String appRstRemark;
    //人脸识别结果（预留, 暂无返回值）
    private String appFaceRst;
    //涉诉黑名单（预留, 暂无返回值）
    private String appBlackRst;
    //执行状态0成功1错误2警告
    private String appStatus;
    //申请数据校验结果（app_valid）详细说明
    private String errMsg;
    //欺诈预警
    private String stanFrdLevel;
    //身份证号与姓名是否匹配
    private String stanIdNameMatch;
    //身份证号是否涉及黑名单
    private String stanStatusB001;
    //是否涉及不良信息
    private String stanStatus3007;
    //裁判文书数量
    private String stan6001Cpws;
    //执行公告数量
    private String stan6001Zxgg;
    //失信公告数量
    private String stan6001Sxgg;
    //开庭公告数量
    private String stan6001Ktgg;
    //法院公告数量
    private String stan6001Fygg;
    //网贷黑名单数量
    private String stan6001Wdhmd;
    //家庭电话查得地址
    private String stan3004Address;
    //家庭电话查得名称
    private String stan3004Name;
    //公司电话查得名称
    private String stan3004EmpName;
    //公司电话查得地址
    private String stan3004EmpAddress;
    //欠费标识
    private String stan3005Hfbalsign;
    //运营商
    private String stan3005Telecomoperator;
    //在网时长
    private String stan3005Onlinetimes;
    //月均联系电话数量
    private String stan8007AMonthlyContacts;
    //日均通话次数
    private String stan8007ADailycalltimes;
    //标记
    private String stan8007ALabel;
    //与联系人1之间关系密切度
    private String stan8007Intimacy_1;
    //与联系人2之间关系密切度
    private String stan8007Intimacy_2;
    //外部服务执行状态
    private String stanExternalStatus;
    
	public String getAppNum() {
		return appNum;
	}
	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}
	public String getFrdCheckType() {
		return frdCheckType;
	}
	public void setFrdCheckType(String frdCheckType) {
		this.frdCheckType = frdCheckType;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getAppValid() {
		return appValid;
	}
	public void setAppValid(String appValid) {
		this.appValid = appValid;
	}
	public String getAppWarn() {
		return appWarn;
	}
	public void setAppWarn(String appWarn) {
		this.appWarn = appWarn;
	}
	public String getAppRst() {
		return appRst;
	}
	public void setAppRst(String appRst) {
		this.appRst = appRst;
	}
	public String getAppRstRemark() {
		return appRstRemark;
	}
	public void setAppRstRemark(String appRstRemark) {
		this.appRstRemark = appRstRemark;
	}
	public String getAppFaceRst() {
		return appFaceRst;
	}
	public void setAppFaceRst(String appFaceRst) {
		this.appFaceRst = appFaceRst;
	}
	public String getAppBlackRst() {
		return appBlackRst;
	}
	public void setAppBlackRst(String appBlackRst) {
		this.appBlackRst = appBlackRst;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getStanFrdLevel() {
		return stanFrdLevel;
	}
	public void setStanFrdLevel(String stanFrdLevel) {
		this.stanFrdLevel = stanFrdLevel;
	}
	public String getStanIdNameMatch() {
		return stanIdNameMatch;
	}
	public void setStanIdNameMatch(String stanIdNameMatch) {
		this.stanIdNameMatch = stanIdNameMatch;
	}
	public String getStanStatusB001() {
		return stanStatusB001;
	}
	public void setStanStatusB001(String stanStatusB001) {
		this.stanStatusB001 = stanStatusB001;
	}
	public String getStanStatus3007() {
		return stanStatus3007;
	}
	public void setStanStatus3007(String stanStatus3007) {
		this.stanStatus3007 = stanStatus3007;
	}
	public String getStan6001Cpws() {
		return stan6001Cpws;
	}
	public void setStan6001Cpws(String stan6001Cpws) {
		this.stan6001Cpws = stan6001Cpws;
	}
	public String getStan6001Zxgg() {
		return stan6001Zxgg;
	}
	public void setStan6001Zxgg(String stan6001Zxgg) {
		this.stan6001Zxgg = stan6001Zxgg;
	}
	public String getStan6001Sxgg() {
		return stan6001Sxgg;
	}
	public void setStan6001Sxgg(String stan6001Sxgg) {
		this.stan6001Sxgg = stan6001Sxgg;
	}
	public String getStan6001Ktgg() {
		return stan6001Ktgg;
	}
	public void setStan6001Ktgg(String stan6001Ktgg) {
		this.stan6001Ktgg = stan6001Ktgg;
	}
	public String getStan6001Fygg() {
		return stan6001Fygg;
	}
	public void setStan6001Fygg(String stan6001Fygg) {
		this.stan6001Fygg = stan6001Fygg;
	}
	public String getStan6001Wdhmd() {
		return stan6001Wdhmd;
	}
	public void setStan6001Wdhmd(String stan6001Wdhmd) {
		this.stan6001Wdhmd = stan6001Wdhmd;
	}
	public String getStan3004Address() {
		return stan3004Address;
	}
	public void setStan3004Address(String stan3004Address) {
		this.stan3004Address = stan3004Address;
	}
	public String getStan3004Name() {
		return stan3004Name;
	}
	public void setStan3004Name(String stan3004Name) {
		this.stan3004Name = stan3004Name;
	}
	public String getStan3004EmpName() {
		return stan3004EmpName;
	}
	public void setStan3004EmpName(String stan3004EmpName) {
		this.stan3004EmpName = stan3004EmpName;
	}
	public String getStan3004EmpAddress() {
		return stan3004EmpAddress;
	}
	public void setStan3004EmpAddress(String stan3004EmpAddress) {
		this.stan3004EmpAddress = stan3004EmpAddress;
	}
	public String getStan3005Hfbalsign() {
		return stan3005Hfbalsign;
	}
	public void setStan3005Hfbalsign(String stan3005Hfbalsign) {
		this.stan3005Hfbalsign = stan3005Hfbalsign;
	}
	public String getStan3005Telecomoperator() {
		return stan3005Telecomoperator;
	}
	public void setStan3005Telecomoperator(String stan3005Telecomoperator) {
		this.stan3005Telecomoperator = stan3005Telecomoperator;
	}
	public String getStan3005Onlinetimes() {
		return stan3005Onlinetimes;
	}
	public void setStan3005Onlinetimes(String stan3005Onlinetimes) {
		this.stan3005Onlinetimes = stan3005Onlinetimes;
	}
	public String getStan8007AMonthlyContacts() {
		return stan8007AMonthlyContacts;
	}
	public void setStan8007AMonthlyContacts(String stan8007aMonthlyContacts) {
		stan8007AMonthlyContacts = stan8007aMonthlyContacts;
	}
	public String getStan8007ADailycalltimes() {
		return stan8007ADailycalltimes;
	}
	public void setStan8007ADailycalltimes(String stan8007aDailycalltimes) {
		stan8007ADailycalltimes = stan8007aDailycalltimes;
	}
	public String getStan8007ALabel() {
		return stan8007ALabel;
	}
	public void setStan8007ALabel(String stan8007aLabel) {
		stan8007ALabel = stan8007aLabel;
	}
	public String getStan8007Intimacy_1() {
		return stan8007Intimacy_1;
	}
	public void setStan8007Intimacy_1(String stan8007Intimacy_1) {
		this.stan8007Intimacy_1 = stan8007Intimacy_1;
	}
	public String getStan8007Intimacy_2() {
		return stan8007Intimacy_2;
	}
	public void setStan8007Intimacy_2(String stan8007Intimacy_2) {
		this.stan8007Intimacy_2 = stan8007Intimacy_2;
	}
	public String getStanExternalStatus() {
		return stanExternalStatus;
	}
	public void setStanExternalStatus(String stanExternalStatus) {
		this.stanExternalStatus = stanExternalStatus;
	}
}
