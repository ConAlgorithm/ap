package engine.rule.model.in.app;

import java.math.BigDecimal;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.CheckNameIDCardResult;
import catfish.base.business.common.MobileserverType;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.enums.WechatHead;
import engine.rule.model.BaseForm;
import engine.rule.model.DerivativeVariableNames;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "衍生变量材料")
public class ConsistencyCheckInForm extends BaseForm {

    // finished
    // 在creator中手动填写
    @ModelField(name = "联系人不同城市数")
    private int contactorCitiesNumber = -99;

    @ModelField(name = "是否本地人（出生地和门店所在城市是否匹配）")
    private boolean nativePerson;

    /************** for precheck **************/
    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsUserInfoInBlacklist)
    @ModelField(name = "用户信息在内部黑名单中")
    private Boolean userInfoInBlackList = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsIdInSupremeCourtBlacklist)
    @ModelField(name = "用户身份证在最高法院黑名单中")
    private Boolean idInSupremeCourtBlackList = false;

    // 身份证校验码为假
    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IdCardIsChecksumValid)
    private Boolean idCardIsChecksumValid;

    //
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.AllContactCount)
    @ModelField(name = "联系人数")
    private Integer allContactCount = 2; //default: no additional contacts

    @ModelField(name = "首付比例") //默认为-1
    private BigDecimal downPaymentRate = new BigDecimal(-1);

    @ModelField(name = "申请日周几(1-7)")
    private String openweekday = "";//申请日周几

    @ModelField(name = "申请时间段(00-23)")
    private String opentime = "";//申请时间段

    @ModelField(name = "申请手机号")
    private String Mobile_o = "";//申请手机号

    @ModelField(name = "申请手机号前三位")
    private String mobile_osec = "";//申请手机号前三位

    @ModelField(name = "申请手机号运营商",bindDomain = "engine.rule.domain.MobileserverType")
    private Integer ServiceProvider = MobileserverType.NONE.getValue();//申请手机号运营商

    @ModelField(name = "最新手机号")
    private String mobile = "";//最新手机号

    @ModelField(name = "最新手机号前三位")
    private String mobilesec = "";//最新手机号前三位
    
    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.ISSECONDTIMEUSER)
    @ModelField(name = "是否为二次客户")
    private boolean secondTimeUser = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LASTAPPMAXDELAYEDDAYS)
    @ModelField(name = "上一次已完成借款最大逾期天数")
    private int lastAppMaxDelayedDays = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LASTAPPLICATIONINTERVAL)
    @ModelField(name = "距离上一次已完成借款最后还款日期时间")
    private int lastApplicationInterval = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LASTLOANSTATUS)
    @ModelField(name = "上一次借款申请状态", bindDomain = "engine.rule.domain.ApplicationStatus")
    private Integer lastLoanIsPrepaid = ApplicationStatus.None.getValue();

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LASTLOANPREPAYMENTDAYS)
    @ModelField(name = "上一次借款提前还款天数")
    private int lastLoanPrepaymentdays = -1; 

    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.QQ_LENGHT)
    @ModelField(name = "QQ号码长度")
    private Integer qqLength = -1;
    
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.NICK_NAME)
    @ModelField(name = "微信名")
    private String nickname = "";
    
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.WECHAT_HEAD )
    @ModelField(name = "微信头像" , bindDomain = "engine.rule.domain.WechatHead")
    private String wechatHead = WechatHead.exist.getValue();
    
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.ISINAGENCY_BLACKLISTOBJECTS)
    @ModelField(name = "通讯录是否在黑中介名单中", bindDomain = "engine.rule.domain.InOrNotResult")
    private int InAgencyBlacklistObjects = -1;
    
    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.BLACK_COMPANY_NAME)
    @ModelField(name = "公司名称是否命中移动黑名单")
    private Boolean blackCompanyName = false;
    
    
    public Integer getQqLength() {
		return qqLength;
	}

	public void setQqLength(Integer qqLength) {
		this.qqLength = qqLength;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWechatHead() {
		return wechatHead;
	}

	public void setWechatHead(String wechatHead) {
		this.wechatHead = wechatHead;
	}

    public int getInAgencyBlacklistObjects() {
        return InAgencyBlacklistObjects;
    }

    public void setInAgencyBlacklistObjects(int inAgencyBlacklistObjects) {
        InAgencyBlacklistObjects = inAgencyBlacklistObjects;
    }

    public boolean isSecondTimeUser() {
        return secondTimeUser;
    }

    public void setSecondTimeUser(boolean secondTimeUser) {
        this.secondTimeUser = secondTimeUser;
    }

    public int getLastAppMaxDelayedDays() {
        return lastAppMaxDelayedDays;
    }

    public void setLastAppMaxDelayedDays(int lastAppMaxDelayedDays) {
        this.lastAppMaxDelayedDays = lastAppMaxDelayedDays;
    }

    public int getLastApplicationInterval() {
        return lastApplicationInterval;
    }

    public void setLastApplicationInterval(int lastApplicationInterval) {
        this.lastApplicationInterval = lastApplicationInterval;
    }

    public Integer getLastLoanIsPrepaid() {
        return lastLoanIsPrepaid;
    }

    public void setLastLoanIsPrepaid(Integer lastLoanIsPrepaid) {
        this.lastLoanIsPrepaid = lastLoanIsPrepaid;
    }

    public int getLastLoanPrepaymentdays() {
        return lastLoanPrepaymentdays;
    }

    public void setLastLoanPrepaymentdays(int lastLoanPrepaymentdays) {
        this.lastLoanPrepaymentdays = lastLoanPrepaymentdays;
    }

    public String getOpenweekday() {
        return openweekday;
    }

    public void setOpenweekday(String openweekday) {
        this.openweekday = openweekday;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getMobile_o() {
        return Mobile_o;
    }

    public void setMobile_o(String mobile_o) {
        Mobile_o = mobile_o;
    }

    public String getMobile_osec() {
        return mobile_osec;
    }

    public void setMobile_osec(String mobile_osec) {
        this.mobile_osec = mobile_osec;
    } 

    public Integer getServiceProvider() {
        return ServiceProvider;
    }

    public void setServiceProvider(Integer serviceProvider) {
        ServiceProvider = serviceProvider;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobilesec() {
        return mobilesec;
    }

    public void setMobilesec(String mobilesec) {
        this.mobilesec = mobilesec;
    }

    public BigDecimal getDownPaymentRate() {
        return downPaymentRate;
    }

    public void setDownPaymentRate(BigDecimal downPaymentRate) {
        this.downPaymentRate = downPaymentRate;
    }

    public Boolean getIdCardIsChecksumValid() {
        return idCardIsChecksumValid;
    }

    public void setIdCardIsChecksumValid(Boolean idCardIsChecksumValid) {
        this.idCardIsChecksumValid = idCardIsChecksumValid;
    }

    @ModelMethod(name = "(this)的身份证校验码为假")
    public boolean isIdCardIsChecksumInvalid() {
        return (idCardIsChecksumValid == null ? true : !idCardIsChecksumValid);
    }

    /****************************************/

    /*************************** for nameIdcardmatch check ******************/
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.IdCardIdentificationResult)
    @ModelField(name = "(this)的身份证和姓名是否一致(默认一致)", bindDomain = "engine.rule.domain.CheckNameIDCardResult")
    private String idCardIdentificationResult = CheckNameIDCardResult.Match.getValue();

    public String getIdCardIdentificationResult() {
        return idCardIdentificationResult;
    }

    public void setIdCardIdentificationResult(String idCardIdentificationResult) {
        this.idCardIdentificationResult = idCardIdentificationResult;
    }

    /**********************************************************************/
    public boolean isNativePerson() {
        return nativePerson;
    }

    public void setNativePerson(boolean nativePerson) {
        this.nativePerson = nativePerson;
    }

    public int getContactorCitiesNumber() {
        return contactorCitiesNumber;
    }

    public void setContactorCitiesNumber(int contactorCitiesNumber) {
        this.contactorCitiesNumber = contactorCitiesNumber;
    }

    public Boolean getUserInfoInBlackList() {
        return userInfoInBlackList;
    }

    public void setUserInfoInBlackList(Boolean userInfoInBlackList) {
        this.userInfoInBlackList = userInfoInBlackList;
    }

    public Boolean getIdInSupremeCourtBlackList() {
        return idInSupremeCourtBlackList;
    }

    public void setIdInSupremeCourtBlackList(Boolean idInSupremeCourtBlackList) {
        this.idInSupremeCourtBlackList = idInSupremeCourtBlackList;
    }

    public Integer getAllContactCount() {
        return allContactCount;
    }

    public void setAllContactCount(Integer allContactCount) {
        this.allContactCount = allContactCount;
    }

	public Boolean getBlackCompanyName() {
		return blackCompanyName;
	}

	public void setBlackCompanyName(Boolean blackCompanyName) {
		this.blackCompanyName = blackCompanyName;
	}
}
