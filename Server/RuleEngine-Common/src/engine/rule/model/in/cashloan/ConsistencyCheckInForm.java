package engine.rule.model.in.cashloan;

import java.math.BigDecimal;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import catfish.base.business.common.AppDerivativeVariableConsts;
import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.DerivativeVariableNames;
import engine.rule.model.annotation.DBField;

/**
 * function:when u wanna the derivative variable working,
 * you have to create a new model, and then RE can decide how
 * the derivative variable work according to the rule configured.
 * date: 2016-11-11
 * @author jiaoh
 *
 */
@ModelInstance(description = "衍生变量材料")
public class ConsistencyCheckInForm extends BaseForm {
    
    /************** for precheck **************/
    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsUserInfoInBlacklist)
    @ModelField(name = "用户信息在内部黑名单中")
    private Boolean userInfoInBlackList = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.PosMaxOverdueCounts)
    @ModelField(name = "用户在pos最大逾期次数")
    private Integer posMaxOverdueCounts = -99;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.PosMaxOverdueDays)
    @ModelField(name = "用户在pos最大逾期天数")
    private Integer posMaxOverdueDays   = -99;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LTVCALLSTATUS)
    @ModelField(name = "电销是否接通", bindDomain = "engine.rule.domain.InOrNotResult")
    private int ltvCallStatus=-1;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LTVCALLWRONG)
    @ModelField(name = "电销是否空号错号", bindDomain = "engine.rule.domain.InOrNotResult")
    private int ltvCallWrong=-1;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LTVCALLREJECT)
    @ModelField(name = "电销是否拒绝", bindDomain = "engine.rule.domain.InOrNotResult")
    private int ltvCallReject=-1;
    
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.LTV_GENDER)
    @ModelField(name = "用户性别", bindDomain = "engine.rule.domain.Gender")
    private String ltvgender="";
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LTV_AGE)
    @ModelField(name = "用户年龄")
    private int ltvage=-1;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = DerivativeVariableNames.LTV_INCOME)
    @ModelField(name = "用户收入")
    private BigDecimal ltvincome=BigDecimal.valueOf(-1);
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = DerivativeVariableNames.LTV_PRINCIPAL)
    @ModelField(name = "ltv金额")
    private BigDecimal ltvprincipal=BigDecimal.valueOf(-1);
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.LTV_REPAYMENTS)
    @ModelField(name = "ltv期数")
    private int ltvrepayments=-1;
    
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.LTV_PROVIDER)
    @ModelField(name = "ltv用户运营商(电信、移动、联通)")
    private String ltvprovider="";
    

    public Integer getPosMaxOverdueDays() {
        return posMaxOverdueDays;
    }

    public void setPosMaxOverdueDays(Integer posMaxOverdueDays) {
        this.posMaxOverdueDays = posMaxOverdueDays;
    }

    public Integer getPosMaxOverdueCounts() {
        return posMaxOverdueCounts;
    }

    public void setPosMaxOverdueCounts(Integer posMaxOverdueCounts) {
        this.posMaxOverdueCounts = posMaxOverdueCounts;
    }

    public Boolean getUserInfoInBlackList() {
        return userInfoInBlackList;
    }

    public void setUserInfoInBlackList(Boolean userInfoInBlackList) {
        this.userInfoInBlackList = userInfoInBlackList;
    }

    public int getLtvCallStatus() {
        return ltvCallStatus;
    }

    public void setLtvCallStatus(int ltvCallStatus) {
        this.ltvCallStatus = ltvCallStatus;
    }

    public int getLtvCallWrong() {
        return ltvCallWrong;
    }

    public void setLtvCallWrong(int ltvCallWrong) {
        this.ltvCallWrong = ltvCallWrong;
    }

    public int getLtvCallReject() {
        return ltvCallReject;
    }

    public void setLtvCallReject(int ltvCallReject) {
        this.ltvCallReject = ltvCallReject;
    }

    public String getLtvgender() {
        return ltvgender;
    }

    public void setLtvgender(String ltvgender) {
        this.ltvgender = ltvgender;
    }

    public int getLtvage() {
        return ltvage;
    }

    public void setLtvage(int ltvage) {
        this.ltvage = ltvage;
    }

    public BigDecimal getLtvincome() {
        return ltvincome;
    }

    public void setLtvincome(BigDecimal ltvincome) {
        this.ltvincome = ltvincome;
    }

    public BigDecimal getLtvprincipal() {
        return ltvprincipal;
    }

    public void setLtvprincipal(BigDecimal ltvprincipal) {
        this.ltvprincipal = ltvprincipal;
    }

    public int getLtvrepayments() {
        return ltvrepayments;
    }

    public void setLtvrepayments(int ltvrepayments) {
        this.ltvrepayments = ltvrepayments;
    }

    public String getLtvprovider() {
        return ltvprovider;
    }

    public void setLtvprovider(String ltvprovider) {
        this.ltvprovider = ltvprovider;
    }
    
}
