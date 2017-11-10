package engine.rule.model.in.cashloan;

import catfish.base.business.common.JORealAuthenticated2;
import catfish.base.business.util.AppDerivativeVariableNames;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.domain.BQSFinalDecision;
import engine.rule.domain.MobileNetWork;
import engine.rule.domain.MobileThree;
import engine.rule.model.BaseForm;
import engine.rule.model.DerivativeVariableNames;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "第三方数据信息")
public class ThirdpartyDataInForm extends BaseForm{

	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsFirstContactRealAuthenticated2)
	@ModelField(name = "集奥第一联系人二要素验证",  bindDomain = "engine.rule.domain.JORealAuthenticated2Result")
	private String joIsFirstContactRealAuthenticated2 = JORealAuthenticated2.NoneRecord.getValue();
	
	/****************同盾*******************/
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33638)
	@ModelField(name = "同盾_借款手机号命中全局失信证据库")
	private boolean tdRule33638Hit = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33640)
	@ModelField(name = "同盾_借款身份证命中全局失信证据库")
	private boolean tdRule33640Hit = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33642)
	@ModelField(name = "同盾_借款人身份证命中法院执行证据库")
	private boolean tdRule33642Hit = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33652)
	@ModelField(name = "同盾_借款人手机号命中虚假号码证据库")
	private boolean tdRule33652Hit = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33654)
	@ModelField(name = "同盾_借款人手机号命中通信小号证据库")
	private boolean tdRule33654Hit = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33674)
	@ModelField(name = "同盾_3个月内手机在多个平台进行借款")
	private boolean tdRule33674Hit = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33676)
	@ModelField(name = "同盾_3个月内身份证在多个平台进行借款")
	private boolean tdRule33676Hit = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_57284)
    @ModelField(name = "同盾_借款人身份证命中法院失信证据库")
    private boolean tdRule57284Hit = false;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33674_OUTER_PLATFORM_LOAN_AMOUNT)
	@ModelField(name = "同盾_3个月内手机在本平台外的借款申请次数")
	private int tdRule33674OuterPlatformLoanAmount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33674_LOAN_AMOUNT)
	@ModelField(name = "同盾_3个月内手机多平台借款平台数")
	private int tdRule33674LoanAmount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33676_OUTER_PLATFORM_LOAN_AMOUNT)
	@ModelField(name = "同盾_3个月内身份证在本平台外的借款申请次数")
	private int tdRule33676OuterPlatformLoanAmount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33676_LOAN_AMOUNT)
	@ModelField(name = "同盾_3个月内身份证多平台借款平台数")
	private int tdRule33676LoanAmount = -1;
	/******************************************************/
	
	/****************同盾V3*******************/
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FINALDECISION)
	@ModelField(name = "同盾V3_风险评估结果，取所有策略中分数最高的结果，结果有三种：Accept无风险，通过；Review低风险，审查；Reject高风险，拒绝")
	private String finalDecision = "";

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCOURTDISHONESTY)
	@ModelField(name = "同盾V3_身份证命中法院失信名单")
	private boolean idCardIsCourtDishonesty = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCOURTEXECUTED)
	@ModelField(name = "同盾V3_身份证命中法院执行名单")
	private boolean idCardIsCourtExecuted = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCOURTCLOSED)
	@ModelField(name = "同盾V3_身份证命中法院结案名单")
	private boolean idCardIsCourtClosed = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCRIMEWANTED)
	@ModelField(name = "同盾V3_身份证命中犯罪通缉名单")
	private boolean idCardIsCrimeWanted = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISSTUDENTLOAN)
	@ModelField(name = "同盾V3_身份证号对应人存在助学贷款逾期历史")
	private boolean idCardIsStudentLoan = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISLOANOVERDUE)
	@ModelField(name = "同盾V3_身份证命中信贷逾期名单")
	private boolean idCardIsLoanOverdue = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISLOANOVERDUEREPAY)
	@ModelField(name = "同盾V3_身份证命中信贷逾期后还款名单")
	private boolean idCardIsLoanOverdueRepay = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISFAKE)
	@ModelField(name = "同盾V3_手机号命中虚假号码库")
	private boolean mobileIsFake = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISSMALL)
	@ModelField(name = "同盾V3_手机号命中通信小号库")
	private boolean mobileIsSmall = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISBILK)
	@ModelField(name = "同盾V3_手机号命中诈骗骚扰库")
	private boolean mobileIsBilk = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOANBLACKMEDIUM)
	@ModelField(name = "同盾V3_手机号命中贷款黑中介库")
	private boolean mobileIsLoanBlackMedium = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOANOVERDUE)
	@ModelField(name = "同盾V3_手机号命中信贷逾期名单")
	private boolean mobileIsLoanOverdue = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISVEHICLEHIRE)
	@ModelField(name = "同盾V3_手机号命中车辆租赁违约名单")
	private boolean mobileIsVehicleHire = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISOVERDRAFTCORPORATE)
	@ModelField(name = "同盾V3_手机号命中欠款公司法人名单")
	private boolean mobileIsOverdraftCorporate = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOST)
	@ModelField(name = "同盾V3_手机号命中失联名单")
	private boolean mobileIsLost = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOANOVERDUEREPAY)
	@ModelField(name = "同盾V3_手机号命中信贷逾期后还款名单")
	private boolean mobileIsLoanOverdueRepay = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDAPPLYS3MONTH)
	@ModelField(name = "同盾V3_3个月内身份证关联多个申请信息")
	private boolean idCardApplys3Month = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.APPLYIDCARDS3MONTH)
	@ModelField(name = "同盾V3_3个月内申请信息关联多个身份证")
	private boolean applyIdCards3Month = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.BANKNAMEIDCARDS3MONTH)
	@ModelField(name = "同盾V3_3个月内银行卡_姓名关联多个身份证")
	private boolean bankNameIdCards3Month = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDASCONTACTNUM2)
	@ModelField(name = "同盾V3_3个月内申请人身份证作为联系人身份证出现的次数大于等于2")
	private boolean idCardAsContactNum2 = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEASCONTACTNUM2)
	@ModelField(name = "同盾V3_3个月内申请人手机号作为联系人手机号出现的次数大于等于2")
	private boolean mobileAsContactNum2 = false;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS7CNT)
	@ModelField(name = "同盾V3_7天内申请人在多个平台申请借款")
	private int applyLoanOnPlats7Cnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS30CNT)
	@ModelField(name = "同盾V3_30天内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats30Cnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS3MONTHCNT)
	@ModelField(name = "同盾V3_3个月内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats3MonthCnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS6MONTHCNT)
	@ModelField(name = "同盾V3_6个月内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats6MonthCnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS12MONTHCNT)
	@ModelField(name = "同盾V3_12个月内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats12MonthCnt = -1;
	
	/******************************************************/
	
	//手机在网时长&三要素-start
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.MOBILENETWORK_MESSAGE)
	@ModelField(name = "手机在网时长-描述")
	private String	mobileNetWork_message = "";
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.MOBILENETWORK_RESULT)
	@ModelField(name = "手机在网时长结果",bindDomain="engine.rule.domain.MobileNetWorkResult")
	private int	mobileNetWork_result = MobileNetWork.NoneRecord.getValue();
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.MOBILETHREE_MESSAGE)
	@ModelField(name = "手机三要素-描述")
	private String	mobileThree_message = "";
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.MOBILETHREE_RESULT)
	@ModelField(name = "手机三要素结果",bindDomain="engine.rule.domain.MobileThreeResult")
	private int mobileThree_result = MobileThree.NoneRecord.getValue();
	
	//手机在网时长&三要素-end
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.SHUWEIRISK_SPECIALLEVEL)
	@ModelField(name = "数维黑名单特殊名单等级")
	private int	SHUWEIRISK_SPECIALLEVEL = -1;
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.BQS_FINALDECISION)
	@ModelField(name = "白骑士最终决定",bindDomain="engine.rule.domain.BQSFinalDecisionResult")
	private String BQS_FINALDECISION= BQSFinalDecision.NoneRecord.getValue();
	
	//富数
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_HIT_CONDITION)
    @ModelField(name = "富数黑名单命中条件")
    private String fushuHitCondition = "";
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.FUSHU_INBLACKLIST)
	@ModelField(name = "富数是否在黑名单")
	private boolean fushuInBlackList = false;
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.FUSHU_INBLACKLIST_COURT)
	@ModelField(name = "富数是否在法院黑名单")
	private boolean fushuInBlackListOfCourt = false;
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.FUSHU_INBLACKLIST_FINANCE)
	@ModelField(name = "富数是否在金融机构黑名单")
	private boolean fushuInBlackListOfFinance = false;
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_TYPE)
    @ModelField(name = "富数黑名单来源")
    private String fushuType = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_DUTY)
	@ModelField(name = "富数法院黑名单记录详情")
	private String fushuDuty = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_REGISTER_DATE)
	@ModelField(name = "富数金融机构黑名单")
	private String fushuRegisterDate = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_DATE)
	@ModelField(name = "富数逾期日期")
	private String fushuDate = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_LEVEL)
	@ModelField(name = "富数黑名单等级")
	private String fushuLevel = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_DETAIL)
	@ModelField(name = "富数黑名单详情")
	private String fushuDetail = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_UPDATETIME)
	@ModelField(name = "富数更新时间")
	private String fushuUpdatetime = "";
	
	public boolean isTdRule33638Hit() {
		return tdRule33638Hit;
	}

	public void setTdRule33638Hit(boolean tdRule33638Hit) {
		this.tdRule33638Hit = tdRule33638Hit;
	}

	public boolean isTdRule33640Hit() {
		return tdRule33640Hit;
	}

	public void setTdRule33640Hit(boolean tdRule33640Hit) {
		this.tdRule33640Hit = tdRule33640Hit;
	}

	public boolean isTdRule33642Hit() {
		return tdRule33642Hit;
	}

	public void setTdRule33642Hit(boolean tdRule33642Hit) {
		this.tdRule33642Hit = tdRule33642Hit;
	}

	public boolean isTdRule33652Hit() {
		return tdRule33652Hit;
	}

	public void setTdRule33652Hit(boolean tdRule33652Hit) {
		this.tdRule33652Hit = tdRule33652Hit;
	}

	public boolean isTdRule33654Hit() {
		return tdRule33654Hit;
	}

	public void setTdRule33654Hit(boolean tdRule33654Hit) {
		this.tdRule33654Hit = tdRule33654Hit;
	}

	public boolean isTdRule33674Hit() {
		return tdRule33674Hit;
	}

	public void setTdRule33674Hit(boolean tdRule33674Hit) {
		this.tdRule33674Hit = tdRule33674Hit;
	}

	public boolean isTdRule33676Hit() {
		return tdRule33676Hit;
	}

	public void setTdRule33676Hit(boolean tdRule33676Hit) {
		this.tdRule33676Hit = tdRule33676Hit;
	}

    public boolean isTdRule57284Hit() {
        return tdRule57284Hit;
    }

    public void setTdRule57284Hit(boolean tdRule57284Hit) {
        this.tdRule57284Hit = tdRule57284Hit;
    }

	public int getTdRule33674OuterPlatformLoanAmount() {
		return tdRule33674OuterPlatformLoanAmount;
	}

	public void setTdRule33674OuterPlatformLoanAmount(
			int tdRule33674OuterPlatformLoanAmount) {
		this.tdRule33674OuterPlatformLoanAmount = tdRule33674OuterPlatformLoanAmount;
	}

	public int getTdRule33674LoanAmount() {
		return tdRule33674LoanAmount;
	}

	public void setTdRule33674LoanAmount(int tdRule33674LoanAmount) {
		this.tdRule33674LoanAmount = tdRule33674LoanAmount;
	}

	public int getTdRule33676OuterPlatformLoanAmount() {
		return tdRule33676OuterPlatformLoanAmount;
	}

	public void setTdRule33676OuterPlatformLoanAmount(
			int tdRule33676OuterPlatformLoanAmount) {
		this.tdRule33676OuterPlatformLoanAmount = tdRule33676OuterPlatformLoanAmount;
	}

	public int getTdRule33676LoanAmount() {
		return tdRule33676LoanAmount;
	}

	public void setTdRule33676LoanAmount(int tdRule33676LoanAmount) {
		this.tdRule33676LoanAmount = tdRule33676LoanAmount;
	}

    public String getJoIsFirstContactRealAuthenticated2() {
        return joIsFirstContactRealAuthenticated2;
    }

    public void setJoIsFirstContactRealAuthenticated2(String joIsFirstContactRealAuthenticated2) {
        this.joIsFirstContactRealAuthenticated2 = joIsFirstContactRealAuthenticated2;
    }

	public int getApplyLoanOnPlats3MonthCnt() {
		return applyLoanOnPlats3MonthCnt;
	}

	public void setApplyLoanOnPlats3MonthCnt(int applyLoanOnPlats3MonthCnt) {
		this.applyLoanOnPlats3MonthCnt = applyLoanOnPlats3MonthCnt;
	}

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}

	public boolean isIdCardIsCourtDishonesty() {
		return idCardIsCourtDishonesty;
	}

	public void setIdCardIsCourtDishonesty(boolean idCardIsCourtDishonesty) {
		this.idCardIsCourtDishonesty = idCardIsCourtDishonesty;
	}

	public boolean isIdCardIsCourtExecuted() {
		return idCardIsCourtExecuted;
	}

	public void setIdCardIsCourtExecuted(boolean idCardIsCourtExecuted) {
		this.idCardIsCourtExecuted = idCardIsCourtExecuted;
	}

	public boolean isIdCardIsCourtClosed() {
		return idCardIsCourtClosed;
	}

	public void setIdCardIsCourtClosed(boolean idCardIsCourtClosed) {
		this.idCardIsCourtClosed = idCardIsCourtClosed;
	}

	public boolean isIdCardIsCrimeWanted() {
		return idCardIsCrimeWanted;
	}

	public void setIdCardIsCrimeWanted(boolean idCardIsCrimeWanted) {
		this.idCardIsCrimeWanted = idCardIsCrimeWanted;
	}

	public boolean isIdCardIsStudentLoan() {
		return idCardIsStudentLoan;
	}

	public void setIdCardIsStudentLoan(boolean idCardIsStudentLoan) {
		this.idCardIsStudentLoan = idCardIsStudentLoan;
	}

	public boolean isIdCardIsLoanOverdue() {
		return idCardIsLoanOverdue;
	}

	public void setIdCardIsLoanOverdue(boolean idCardIsLoanOverdue) {
		this.idCardIsLoanOverdue = idCardIsLoanOverdue;
	}

	public boolean isIdCardIsLoanOverdueRepay() {
		return idCardIsLoanOverdueRepay;
	}

	public void setIdCardIsLoanOverdueRepay(boolean idCardIsLoanOverdueRepay) {
		this.idCardIsLoanOverdueRepay = idCardIsLoanOverdueRepay;
	}

	public boolean isMobileIsFake() {
		return mobileIsFake;
	}

	public void setMobileIsFake(boolean mobileIsFake) {
		this.mobileIsFake = mobileIsFake;
	}

	public boolean isMobileIsSmall() {
		return mobileIsSmall;
	}

	public void setMobileIsSmall(boolean mobileIsSmall) {
		this.mobileIsSmall = mobileIsSmall;
	}

	public boolean isMobileIsBilk() {
		return mobileIsBilk;
	}

	public void setMobileIsBilk(boolean mobileIsBilk) {
		this.mobileIsBilk = mobileIsBilk;
	}

	public boolean isMobileIsLoanBlackMedium() {
		return mobileIsLoanBlackMedium;
	}

	public void setMobileIsLoanBlackMedium(boolean mobileIsLoanBlackMedium) {
		this.mobileIsLoanBlackMedium = mobileIsLoanBlackMedium;
	}

	public boolean isMobileIsLoanOverdue() {
		return mobileIsLoanOverdue;
	}

	public void setMobileIsLoanOverdue(boolean mobileIsLoanOverdue) {
		this.mobileIsLoanOverdue = mobileIsLoanOverdue;
	}

	public boolean isMobileIsVehicleHire() {
		return mobileIsVehicleHire;
	}

	public void setMobileIsVehicleHire(boolean mobileIsVehicleHire) {
		this.mobileIsVehicleHire = mobileIsVehicleHire;
	}

	public boolean isMobileIsOverdraftCorporate() {
		return mobileIsOverdraftCorporate;
	}

	public void setMobileIsOverdraftCorporate(boolean mobileIsOverdraftCorporate) {
		this.mobileIsOverdraftCorporate = mobileIsOverdraftCorporate;
	}

	public boolean isMobileIsLost() {
		return mobileIsLost;
	}

	public void setMobileIsLost(boolean mobileIsLost) {
		this.mobileIsLost = mobileIsLost;
	}

	public boolean isMobileIsLoanOverdueRepay() {
		return mobileIsLoanOverdueRepay;
	}

	public void setMobileIsLoanOverdueRepay(boolean mobileIsLoanOverdueRepay) {
		this.mobileIsLoanOverdueRepay = mobileIsLoanOverdueRepay;
	}

	public boolean isIdCardApplys3Month() {
		return idCardApplys3Month;
	}

	public void setIdCardApplys3Month(boolean idCardApplys3Month) {
		this.idCardApplys3Month = idCardApplys3Month;
	}

	public boolean isApplyIdCards3Month() {
		return applyIdCards3Month;
	}

	public void setApplyIdCards3Month(boolean applyIdCards3Month) {
		this.applyIdCards3Month = applyIdCards3Month;
	}

	public boolean isBankNameIdCards3Month() {
		return bankNameIdCards3Month;
	}

	public void setBankNameIdCards3Month(boolean bankNameIdCards3Month) {
		this.bankNameIdCards3Month = bankNameIdCards3Month;
	}

	public boolean isIdCardAsContactNum2() {
		return idCardAsContactNum2;
	}

	public void setIdCardAsContactNum2(boolean idCardAsContactNum2) {
		this.idCardAsContactNum2 = idCardAsContactNum2;
	}

	public boolean isMobileAsContactNum2() {
		return mobileAsContactNum2;
	}

	public void setMobileAsContactNum2(boolean mobileAsContactNum2) {
		this.mobileAsContactNum2 = mobileAsContactNum2;
	}

	public int getApplyLoanOnPlats7Cnt() {
		return applyLoanOnPlats7Cnt;
	}

	public void setApplyLoanOnPlats7Cnt(int applyLoanOnPlats7Cnt) {
		this.applyLoanOnPlats7Cnt = applyLoanOnPlats7Cnt;
	}

	public int getApplyLoanOnPlats30Cnt() {
		return applyLoanOnPlats30Cnt;
	}

	public void setApplyLoanOnPlats30Cnt(int applyLoanOnPlats30Cnt) {
		this.applyLoanOnPlats30Cnt = applyLoanOnPlats30Cnt;
	}

	public int getApplyLoanOnPlats6MonthCnt() {
		return applyLoanOnPlats6MonthCnt;
	}

	public void setApplyLoanOnPlats6MonthCnt(int applyLoanOnPlats6MonthCnt) {
		this.applyLoanOnPlats6MonthCnt = applyLoanOnPlats6MonthCnt;
	}

	public int getApplyLoanOnPlats12MonthCnt() {
		return applyLoanOnPlats12MonthCnt;
	}

	public void setApplyLoanOnPlats12MonthCnt(int applyLoanOnPlats12MonthCnt) {
		this.applyLoanOnPlats12MonthCnt = applyLoanOnPlats12MonthCnt;
	}

	public String getMobileNetWork_message() {
		return mobileNetWork_message;
	}

	public void setMobileNetWork_message(String mobileNetWork_message) {
		this.mobileNetWork_message = mobileNetWork_message;
	}

	public int getMobileNetWork_result() {
		return mobileNetWork_result;
	}

	public void setMobileNetWork_result(int mobileNetWork_result) {
		this.mobileNetWork_result = mobileNetWork_result;
	}

	public String getMobileThree_message() {
		return mobileThree_message;
	}

	public void setMobileThree_message(String mobileThree_message) {
		this.mobileThree_message = mobileThree_message;
	}

	public int getMobileThree_result() {
		return mobileThree_result;
	}

	public void setMobileThree_result(int mobileThree_result) {
		this.mobileThree_result = mobileThree_result;
	}

	public int getSHUWEIRISK_SPECIALLEVEL() {
		return SHUWEIRISK_SPECIALLEVEL;
	}

	public void setSHUWEIRISK_SPECIALLEVEL(int sHUWEIRISK_SPECIALLEVEL) {
		SHUWEIRISK_SPECIALLEVEL = sHUWEIRISK_SPECIALLEVEL;
	}

	public String getBQS_FINALDECISION() {
		return BQS_FINALDECISION;
	}

	public void setBQS_FINALDECISION(String bQS_FINALDECISION) {
		BQS_FINALDECISION = bQS_FINALDECISION;
	}

	@Override
	public String toString() {
		return "ThirdpartyDataInForm [joIsFirstContactRealAuthenticated2=" + joIsFirstContactRealAuthenticated2
				+ ", tdRule33638Hit=" + tdRule33638Hit + ", tdRule33640Hit=" + tdRule33640Hit + ", tdRule33642Hit="
				+ tdRule33642Hit + ", tdRule33652Hit=" + tdRule33652Hit + ", tdRule33654Hit=" + tdRule33654Hit
				+ ", tdRule33674Hit=" + tdRule33674Hit + ", tdRule33676Hit=" + tdRule33676Hit + ", tdRule57284Hit="
				+ tdRule57284Hit + ", tdRule33674OuterPlatformLoanAmount=" + tdRule33674OuterPlatformLoanAmount
				+ ", tdRule33674LoanAmount=" + tdRule33674LoanAmount + ", tdRule33676OuterPlatformLoanAmount="
				+ tdRule33676OuterPlatformLoanAmount + ", tdRule33676LoanAmount=" + tdRule33676LoanAmount
				+ ", finalDecision=" + finalDecision + ", idCardIsCourtDishonesty=" + idCardIsCourtDishonesty
				+ ", idCardIsCourtExecuted=" + idCardIsCourtExecuted + ", idCardIsCourtClosed=" + idCardIsCourtClosed
				+ ", idCardIsCrimeWanted=" + idCardIsCrimeWanted + ", idCardIsStudentLoan=" + idCardIsStudentLoan
				+ ", idCardIsLoanOverdue=" + idCardIsLoanOverdue + ", idCardIsLoanOverdueRepay="
				+ idCardIsLoanOverdueRepay + ", mobileIsFake=" + mobileIsFake + ", mobileIsSmall=" + mobileIsSmall
				+ ", mobileIsBilk=" + mobileIsBilk + ", mobileIsLoanBlackMedium=" + mobileIsLoanBlackMedium
				+ ", mobileIsLoanOverdue=" + mobileIsLoanOverdue + ", mobileIsVehicleHire=" + mobileIsVehicleHire
				+ ", mobileIsOverdraftCorporate=" + mobileIsOverdraftCorporate + ", mobileIsLost=" + mobileIsLost
				+ ", mobileIsLoanOverdueRepay=" + mobileIsLoanOverdueRepay + ", idCardApplys3Month="
				+ idCardApplys3Month + ", applyIdCards3Month=" + applyIdCards3Month + ", bankNameIdCards3Month="
				+ bankNameIdCards3Month + ", idCardAsContactNum2=" + idCardAsContactNum2 + ", mobileAsContactNum2="
				+ mobileAsContactNum2 + ", applyLoanOnPlats7Cnt=" + applyLoanOnPlats7Cnt + ", applyLoanOnPlats30Cnt="
				+ applyLoanOnPlats30Cnt + ", applyLoanOnPlats3MonthCnt=" + applyLoanOnPlats3MonthCnt
				+ ", applyLoanOnPlats6MonthCnt=" + applyLoanOnPlats6MonthCnt + ", applyLoanOnPlats12MonthCnt="
				+ applyLoanOnPlats12MonthCnt + ", mobileNetWork_message=" + mobileNetWork_message
				+ ", mobileNetWork_result=" + mobileNetWork_result + ", mobileThree_message=" + mobileThree_message
				+ ", mobileThree_result=" + mobileThree_result + ", SHUWEIRISK_SPECIALLEVEL=" + SHUWEIRISK_SPECIALLEVEL
				+ ", BQS_FINALDECISION=" + BQS_FINALDECISION + "]";
	}

	public String getFushuHitCondition() {
		return fushuHitCondition;
	}

	public void setFushuHitCondition(String fushuHitCondition) {
		this.fushuHitCondition = fushuHitCondition;
	}

	public boolean isFushuInBlackList() {
		return fushuInBlackList;
	}

	public void setFushuInBlackList(boolean fushuInBlackList) {
		this.fushuInBlackList = fushuInBlackList;
	}

	public boolean isFushuInBlackListOfCourt() {
		return fushuInBlackListOfCourt;
	}

	public void setFushuInBlackListOfCourt(boolean fushuInBlackListOfCourt) {
		this.fushuInBlackListOfCourt = fushuInBlackListOfCourt;
	}

	public boolean isFushuInBlackListOfFinance() {
		return fushuInBlackListOfFinance;
	}

	public void setFushuInBlackListOfFinance(boolean fushuInBlackListOfFinance) {
		this.fushuInBlackListOfFinance = fushuInBlackListOfFinance;
	}

	public String getFushuType() {
		return fushuType;
	}

	public void setFushuType(String fushuType) {
		this.fushuType = fushuType;
	}

	public String getFushuDuty() {
		return fushuDuty;
	}

	public void setFushuDuty(String fushuDuty) {
		this.fushuDuty = fushuDuty;
	}

	public String getFushuRegisterDate() {
		return fushuRegisterDate;
	}

	public void setFushuRegisterDate(String fushuRegisterDate) {
		this.fushuRegisterDate = fushuRegisterDate;
	}

	public String getFushuDate() {
		return fushuDate;
	}

	public void setFushuDate(String fushuDate) {
		this.fushuDate = fushuDate;
	}

	public String getFushuLevel() {
		return fushuLevel;
	}

	public void setFushuLevel(String fushuLevel) {
		this.fushuLevel = fushuLevel;
	}

	public String getFushuDetail() {
		return fushuDetail;
	}

	public void setFushuDetail(String fushuDetail) {
		this.fushuDetail = fushuDetail;
	}

	public String getFushuUpdatetime() {
		return fushuUpdatetime;
	}

	public void setFushuUpdatetime(String fushuUpdatetime) {
		this.fushuUpdatetime = fushuUpdatetime;
	}

}
