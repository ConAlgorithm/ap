/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.model;

import omni.database.DataContainer;
import omni.database.mongo.DerivativeVariables;

/**
 * 〈前海黑名单字段〉
 *
 * @author dengw
 * @version U2C.java, V1.0 2016年11月21日 下午5:46:33
 */
public class U2C {
    /** 前海欺诈风险 */
    private Boolean x_QHZX_HasFraudRisk;
    /** 前海风险得分 */
    private Integer x_QHZX_RiskScore;
    /** 前海手机号欺诈风险 */
    private Boolean x_QHZX_HasMobileFraudRisk;
    /** 前海银行卡号欺诈风险 */
    private Boolean x_QHZX_HasBankCardFraudRisk;
    /** 前海身份证号欺诈风险 */
    private Boolean x_QHZX_HasIdCardFraudRisk;
    
    
    /** 上海资信 -申请机构 */
    private String x_SHZX_ApplyOrganization;
    /** 上海资信 -申请时间 */
    private String x_SHZX_ApplyTime;
    /** 上海资信 -申请金额 */
    private String x_SHZX_ApplyMoney;
    /** 上海资信 -申请月数 */
    private String x_SHZX_ApplyMonth;
    /** 上海资信 -申请状态 */
    private String x_SHZX_ApplyStatus;
    /** 上海资信 -申请类型 */
    private String x_SHZX_ApplyType;
    /** 上海资信 -申请信息获取时间 */
    private String x_SHZX_ApplyInfoDateTime;
    /** 上海资信 -贷款笔数 */
    private String x_SHZX_LoanNumsTotal;
    /** 上海资信 -最大授信额度 */
    private String x_SHZX_MaxCreditLineTotal;
    /** 上海资信 -贷款总额 */
    private String x_SHZX_LoanMoneyTotal;
    /** 上海资信 -贷款余额 */
    private String x_SHZX_RemainMoneyTotal;
    /** 上海资信 -当前逾期总额 */
    private String x_SHZX_RepayOverdueMoneyTotal;
    /** 上海资信 -最高逾期金额 */
    private String x_SHZX_RepayOverdueMaxMoney;
    /** 上海资信 -借款机构查询次数 */
    private String x_SHZX_LoanReportQueryCount;
    
    /** 集奥 -在网时长 */
    private String x_JO_LengthOfNetwork;
    /** 集奥 - 在网时长描述 */
    private String x_JO_LengthOfNetworkDesc;
    /** 集奥 - 姓名身份证手机号三元素验证 */
    private String x_JO_IsRealAuthenticated3;
    /** 集奥 - 姓名身份证手机号三元素验证描述 */
    private String x_JO_IsRealAuthenticated3Desc;
    
    /** 百度 - 百度评分是否匹配上百度ID */
    private String x_BaiduScore_type;
    /** 百度 - 百度评分/分值越大风险越高 */
    private String x_BaiduMdx_usmbl;
    
    /** 同盾 - 3个月内手机多平台借款平台数*/
    private Integer x_TD_Rule_33674_LoanAmount;
    /** 同盾 - 3个月内身份证多平台借款平台数*/
    private Integer x_TD_Rule_33676_LoanAmount;

    public U2C(String appId) {
        this(appId, "");
    }

    public U2C(String appId, String instinctCall) {
        this.initialize(appId);
    }

    private void initialize(String appId) {
        DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
        if (mongodv != null) {
            this.x_QHZX_HasFraudRisk = mongodv.X_QHZX_HasFraudRisk;
            this.x_QHZX_RiskScore = mongodv.X_QHZX_RiskScore;
            this.x_QHZX_HasMobileFraudRisk = mongodv.X_QHZX_HasMobileFraudRisk;
            this.x_QHZX_HasBankCardFraudRisk = mongodv.X_QHZX_HasBankCardFraudRisk;
            this.x_QHZX_HasIdCardFraudRisk = mongodv.X_QHZX_HasIdCardFraudRisk;
            
            this.x_SHZX_ApplyOrganization = mongodv.X_SHZX_ApplyOrganization;
            this.x_SHZX_ApplyTime = mongodv.X_SHZX_ApplyTime;
            this.x_SHZX_ApplyMoney = mongodv.X_SHZX_ApplyMoney;
            this.x_SHZX_ApplyMonth = mongodv.X_SHZX_ApplyMonth;
            this.x_SHZX_ApplyStatus = mongodv.X_SHZX_ApplyStatus;
            this.x_SHZX_ApplyType = mongodv.X_SHZX_ApplyType;
            this.x_SHZX_ApplyInfoDateTime = mongodv.X_SHZX_ApplyInfoDateTime;
            this.x_SHZX_LoanNumsTotal = mongodv.X_SHZX_LoanNumsTotal;
            this.x_SHZX_MaxCreditLineTotal = mongodv.X_SHZX_MaxCreditLineTotal;
            this.x_SHZX_LoanMoneyTotal = mongodv.X_SHZX_LoanMoneyTotal;
            this.x_SHZX_RemainMoneyTotal = mongodv.X_SHZX_RemainMoneyTotal;
            this.x_SHZX_RepayOverdueMoneyTotal = mongodv.X_SHZX_RepayOverdueMoneyTotal;
            this.x_SHZX_RepayOverdueMaxMoney = mongodv.X_SHZX_RepayOverdueMaxMoney;
            this.x_SHZX_LoanReportQueryCount = mongodv.X_SHZX_LoanReportQueryCount;
            this.x_JO_LengthOfNetwork = mongodv.X_JO_LengthOfNetwork;
            this.x_JO_LengthOfNetworkDesc = mongodv.X_JO_LengthOfNetworkDesc;
            this.x_JO_IsRealAuthenticated3 = mongodv.X_JO_IsRealAuthenticated3;
            this.x_JO_IsRealAuthenticated3Desc = mongodv.X_JO_IsRealAuthenticated3Desc;
            this.x_BaiduScore_type = mongodv.X_BaiduScore_type;
            this.x_BaiduMdx_usmbl = mongodv.X_BaiduMdx_usmbl;
            this.x_TD_Rule_33674_LoanAmount = mongodv.X_TD_Rule_33674_LoanAmount;
            this.x_TD_Rule_33676_LoanAmount = mongodv.X_TD_Rule_33676_LoanAmount;
        }
    }

    public Boolean getX_QHZX_HasFraudRisk() {
        return x_QHZX_HasFraudRisk;
    }

    public void setX_QHZX_HasFraudRisk(Boolean x_QHZX_HasFraudRisk) {
        this.x_QHZX_HasFraudRisk = x_QHZX_HasFraudRisk;
    }

    public Integer getX_QHZX_RiskScore() {
        return x_QHZX_RiskScore;
    }

    public void setX_QHZX_RiskScore(Integer x_QHZX_RiskScore) {
        this.x_QHZX_RiskScore = x_QHZX_RiskScore;
    }

    public Boolean getX_QHZX_HasMobileFraudRisk() {
        return x_QHZX_HasMobileFraudRisk;
    }

    public void setX_QHZX_HasMobileFraudRisk(Boolean x_QHZX_HasMobileFraudRisk) {
        this.x_QHZX_HasMobileFraudRisk = x_QHZX_HasMobileFraudRisk;
    }

    public Boolean getX_QHZX_HasBankCardFraudRisk() {
        return x_QHZX_HasBankCardFraudRisk;
    }

    public void setX_QHZX_HasBankCardFraudRisk(Boolean x_QHZX_HasBankCardFraudRisk) {
        this.x_QHZX_HasBankCardFraudRisk = x_QHZX_HasBankCardFraudRisk;
    }

    public Boolean getX_QHZX_HasIdCardFraudRisk() {
        return x_QHZX_HasIdCardFraudRisk;
    }

    public void setX_QHZX_HasIdCardFraudRisk(Boolean x_QHZX_HasIdCardFraudRisk) {
        this.x_QHZX_HasIdCardFraudRisk = x_QHZX_HasIdCardFraudRisk;
    }

    public String getX_SHZX_ApplyOrganization() {
        return x_SHZX_ApplyOrganization;
    }

    public void setX_SHZX_ApplyOrganization(String x_SHZX_ApplyOrganization) {
        this.x_SHZX_ApplyOrganization = x_SHZX_ApplyOrganization;
    }

    public String getX_SHZX_ApplyTime() {
        return x_SHZX_ApplyTime;
    }

    public void setX_SHZX_ApplyTime(String x_SHZX_ApplyTime) {
        this.x_SHZX_ApplyTime = x_SHZX_ApplyTime;
    }

    public String getX_SHZX_ApplyMoney() {
        return x_SHZX_ApplyMoney;
    }

    public void setX_SHZX_ApplyMoney(String x_SHZX_ApplyMoney) {
        this.x_SHZX_ApplyMoney = x_SHZX_ApplyMoney;
    }

    public String getX_SHZX_ApplyMonth() {
        return x_SHZX_ApplyMonth;
    }

    public void setX_SHZX_ApplyMonth(String x_SHZX_ApplyMonth) {
        this.x_SHZX_ApplyMonth = x_SHZX_ApplyMonth;
    }

    public String getX_SHZX_ApplyStatus() {
        return x_SHZX_ApplyStatus;
    }

    public void setX_SHZX_ApplyStatus(String x_SHZX_ApplyStatus) {
        this.x_SHZX_ApplyStatus = x_SHZX_ApplyStatus;
    }

    public String getX_SHZX_ApplyType() {
        return x_SHZX_ApplyType;
    }

    public void setX_SHZX_ApplyType(String x_SHZX_ApplyType) {
        this.x_SHZX_ApplyType = x_SHZX_ApplyType;
    }

    public String getX_SHZX_ApplyInfoDateTime() {
        return x_SHZX_ApplyInfoDateTime;
    }

    public void setX_SHZX_ApplyInfoDateTime(String x_SHZX_ApplyInfoDateTime) {
        this.x_SHZX_ApplyInfoDateTime = x_SHZX_ApplyInfoDateTime;
    }

    public String getX_SHZX_LoanNumsTotal() {
        return x_SHZX_LoanNumsTotal;
    }

    public void setX_SHZX_LoanNumsTotal(String x_SHZX_LoanNumsTotal) {
        this.x_SHZX_LoanNumsTotal = x_SHZX_LoanNumsTotal;
    }

    public String getX_SHZX_MaxCreditLineTotal() {
        return x_SHZX_MaxCreditLineTotal;
    }

    public void setX_SHZX_MaxCreditLineTotal(String x_SHZX_MaxCreditLineTotal) {
        this.x_SHZX_MaxCreditLineTotal = x_SHZX_MaxCreditLineTotal;
    }

    public String getX_SHZX_LoanMoneyTotal() {
        return x_SHZX_LoanMoneyTotal;
    }

    public void setX_SHZX_LoanMoneyTotal(String x_SHZX_LoanMoneyTotal) {
        this.x_SHZX_LoanMoneyTotal = x_SHZX_LoanMoneyTotal;
    }

    public String getX_SHZX_RemainMoneyTotal() {
        return x_SHZX_RemainMoneyTotal;
    }

    public void setX_SHZX_RemainMoneyTotal(String x_SHZX_RemainMoneyTotal) {
        this.x_SHZX_RemainMoneyTotal = x_SHZX_RemainMoneyTotal;
    }

    public String getX_SHZX_RepayOverdueMoneyTotal() {
        return x_SHZX_RepayOverdueMoneyTotal;
    }

    public void setX_SHZX_RepayOverdueMoneyTotal(String x_SHZX_RepayOverdueMoneyTotal) {
        this.x_SHZX_RepayOverdueMoneyTotal = x_SHZX_RepayOverdueMoneyTotal;
    }

    public String getX_SHZX_RepayOverdueMaxMoney() {
        return x_SHZX_RepayOverdueMaxMoney;
    }

    public void setX_SHZX_RepayOverdueMaxMoney(String x_SHZX_RepayOverdueMaxMoney) {
        this.x_SHZX_RepayOverdueMaxMoney = x_SHZX_RepayOverdueMaxMoney;
    }

    public String getX_SHZX_LoanReportQueryCount() {
        return x_SHZX_LoanReportQueryCount;
    }

    public void setX_SHZX_LoanReportQueryCount(String x_SHZX_LoanReportQueryCount) {
        this.x_SHZX_LoanReportQueryCount = x_SHZX_LoanReportQueryCount;
    }

	public String getX_JO_LengthOfNetwork() {
		return x_JO_LengthOfNetwork;
	}

	public void setX_JO_LengthOfNetwork(String x_JO_LengthOfNetwork) {
		this.x_JO_LengthOfNetwork = x_JO_LengthOfNetwork;
	}

	public String getX_JO_LengthOfNetworkDesc() {
		return x_JO_LengthOfNetworkDesc;
	}

	public void setX_JO_LengthOfNetworkDesc(String x_JO_LengthOfNetworkDesc) {
		this.x_JO_LengthOfNetworkDesc = x_JO_LengthOfNetworkDesc;
	}

	public String getX_JO_IsRealAuthenticated3() {
		return x_JO_IsRealAuthenticated3;
	}

	public void setX_JO_IsRealAuthenticated3(String x_JO_IsRealAuthenticated3) {
		this.x_JO_IsRealAuthenticated3 = x_JO_IsRealAuthenticated3;
	}

	public String getX_JO_IsRealAuthenticated3Desc() {
		return x_JO_IsRealAuthenticated3Desc;
	}

	public void setX_JO_IsRealAuthenticated3Desc(String x_JO_IsRealAuthenticated3Desc) {
		this.x_JO_IsRealAuthenticated3Desc = x_JO_IsRealAuthenticated3Desc;
	}

	public String getX_BaiduScore_type() {
		return x_BaiduScore_type;
	}

	public void setX_BaiduScore_type(String x_BaiduScore_type) {
		this.x_BaiduScore_type = x_BaiduScore_type;
	}

	public String getX_BaiduMdx_usmbl() {
		return x_BaiduMdx_usmbl;
	}

	public void setX_BaiduMdx_usmbl(String x_BaiduMdx_usmbl) {
		this.x_BaiduMdx_usmbl = x_BaiduMdx_usmbl;
	}

	public Integer getX_TD_Rule_33674_LoanAmount() {
		return x_TD_Rule_33674_LoanAmount;
	}

	public void setX_TD_Rule_33674_LoanAmount(Integer x_TD_Rule_33674_LoanAmount) {
		this.x_TD_Rule_33674_LoanAmount = x_TD_Rule_33674_LoanAmount;
	}

	public Integer getX_TD_Rule_33676_LoanAmount() {
		return x_TD_Rule_33676_LoanAmount;
	}

	public void setX_TD_Rule_33676_LoanAmount(Integer x_TD_Rule_33676_LoanAmount) {
		this.x_TD_Rule_33676_LoanAmount = x_TD_Rule_33676_LoanAmount;
	}
    
    
}
