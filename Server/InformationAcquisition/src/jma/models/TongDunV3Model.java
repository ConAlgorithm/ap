/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈同盾V3〉
 *
 * @author dengw
 * @version TongDunV3Model.java, V1.0 2017年6月28日 下午12:06:16
 */
public class TongDunV3Model {
	private String sequenceId;
    private String finalDecision;
    private Boolean idCardIsFormatErr;
    private Boolean idCardIsFormatRight;
    private Boolean idCardIsSecond;
    private Boolean idCardRegionIsHighRisk;
    private Boolean idCardIsCourtDishonesty;
    private Boolean idCardIsCourtExecuted;
    private Boolean idCardIsCourtClosed;
    private Boolean idCardIsCrimeWanted;
    private Boolean idCardIsStudentLoan;
    private Boolean idCardIsLoanOverdue;
    private Boolean idCardIsLoanOverdueRepay;
    private Boolean idCardIsHighRiskFocus;
    private Boolean idCardIsMidRiskFocus;
    private Boolean idCardIsLowRiskFocus;
    private Boolean idCardIsVehicleHire;
    private Boolean idCardIsLost;
    private Boolean idCardIsOverdraftCorporate;
    private Boolean idCardIsTravelDishonesty;
    private Boolean idCardIsTaxes;
    private Boolean idCardIsCompanyTaxes;
    private Boolean idCardNameIsLoanOverdue;
    private Boolean idCardNameIsCourtDishonesty;
    private Boolean idCardNameIsCourtExecuted;
    private Boolean idCardNameIsCourtClosed;
    private Boolean mobileIsFormatErr;
    private Boolean mobileIsFormatRight;
    private Boolean mobileIsFake;
    private Boolean mobileIsSmall;
    private Boolean mobileIsBilk;
    private Boolean mobileIsRandomInput;
    private Boolean mobileIsHighRiskFocus;
    private Boolean mobileIsMidRiskFocus;
    private Boolean mobileIsLowRiskFocus;
    private Boolean mobileIsLoanBlackMedium;
    private Boolean mobileIsLoanOverdue;
    private Boolean mobileIsVehicleHire;
    private Boolean mobileIsOverdraftCorporate;
    private Boolean mobileIsLost;
    private Boolean mobileIsLoanOverdueRepay;
    private Boolean emailIsFormatErr;
    private Boolean emailIsFormatRight;
    private Boolean emailIsLoanOverdue;
    private Boolean emailIsLoanOverdueRepay;
    private Boolean emailIsHighRiskFocus;
    private Boolean emailIsMidRiskFocus;
    private Boolean emailIsLowRiskFocus;
    private Boolean phoneIsBilk;
    private Boolean phoneIsLoanOverdue;
    private Boolean phoneIsHighRiskFocus;
    private Boolean phoneIsMidRiskFocus;
    private Boolean phoneIsLowRiskFocus;
    private Boolean qqIsLoanOverdue;
    private Boolean qqIsLoanOverdueRepay;
    private Boolean qqIsHighRiskFocus;
    private Boolean qqIsMidRiskFocus;
    private Boolean qqIsLowRiskFocus;
    private Boolean loanApplyIsConsist;
    private Boolean unitNameIsMedium;
    private Boolean eduAgePosValidRisk;
    private Boolean eduAgeJobValidRisk;
    private Boolean isHighRiskAge;
    private Boolean idCardApplys3Month;
    private Boolean applyIdCards3Month;
    private Boolean bankNameIdCards3Month;
    private Boolean idCardAsContactNum2;
    private Boolean mobileAsContactNum2;
    private Boolean addrIsLoanOverdue;
    private Boolean disIsExceptInShortTime;
    private Boolean loanDevProxyExcept;
    private Boolean mobileAddrIsNotMatchIpCity;
    private Boolean applyIpIsNotMatchIpCity;
    private Boolean devIdCardMobileApplyOver7;
    private Boolean devIdCardMobileApplyOver30;
    private Boolean overIdCardMobile1;
    private Boolean overIdCardMobile7;
    private Boolean idCardOverDev1;
    private Boolean idCardOverDev7;
    private Boolean idCardOverDev30;
    private Boolean applyLoanOnPlats7;
    private Boolean applyLoanOnPlats30;
    private Boolean applyLoanOnPlats3Month;
    private Boolean applyLoanOnPlats6Month;
    private Boolean applyLoanOnPlats12Month;
    private Boolean applyLoanOnPlats18Month;
    private Boolean applyLoanOnPlats24Month;
    private Boolean applyLoanOnPlats60Month;
    private Integer applyLoanOnPlats7Cnt;
    private Integer applyLoanOnPlats30Cnt;
    private Integer applyLoanOnPlats3MonthCnt;
    private Integer applyLoanOnPlats6MonthCnt;
    private Integer applyLoanOnPlats12MonthCnt;
    private Integer applyLoanOnPlats18MonthCnt;
    private Integer applyLoanOnPlats24MonthCnt;
    private Integer applyLoanOnPlats60MonthCnt;
    private Boolean contact1IsClose;
    private Boolean contact1ClosedIdCardCourtDishonesty;
    private Boolean contact1ClosedIdCardCourtExecuted;
    private Boolean contact1ClosedIdCardCourtClosed;
    private Boolean contact1ClosedIdCardCrimeWanted;
    private Boolean contact1ClosedIdCardOverdraftCorporate;
    private Boolean contact1ClosedIdCardLoanOverdue;
    private Boolean contact1ClosedMobileLoanOverdue;
    private Boolean contact1ClosedMobileLoanBlackMedium;
    private Boolean contact1ClosedMobileLoanOverdueRepay;
    private Boolean contact1ClosedMobileIsFake;
    private Boolean contact1IsCommon;
    private Boolean contact1CommonIdCardCourtDishonesty;
    private Boolean contact1CommonIdCardCourtExecuted;
    private Boolean contact1CommonIdCardCourtClosed;
    private Boolean contact1CommonIdCardCrimeWanted;
    private Boolean contact1CommonIdCardOverdraftCorporate;
    private Boolean contact1CommonIdCardLoanOverdue;
    private Boolean contact1CommonMobileLoanOverdue;
    private Boolean contact1CommonMobileLoanBlackMedium;
    private Boolean contact1CommonMobileLoanOverdueRepay;
    private Boolean contact1CommonMobileIsFake;
    private Boolean contact2IsClose;
    private Boolean contact2ClosedIdCardCourtDishonesty;
    private Boolean contact2ClosedIdCardCourtExecuted;
    private Boolean contact2ClosedIdCardCourtClosed;
    private Boolean contact2ClosedIdCardCrimeWanted;
    private Boolean contact2ClosedIdCardOverdraftCorporate;
    private Boolean contact2ClosedIdCardLoanOverdue;
    private Boolean contact2ClosedMobileLoanOverdue;
    private Boolean contact2ClosedMobileLoanBlackMedium;
    private Boolean contact2ClosedMobileLoanOverdueRepay;
    private Boolean contact2ClosedMobileIsFake;
    private Boolean contact2IsCommon;
    private Boolean contact2CommonIdCardCourtDishonesty;
    private Boolean contact2CommonIdCardCourtExecuted;
    private Boolean contact2CommonIdCardCourtClosed;
    private Boolean contact2CommonIdCardCrimeWanted;
    private Boolean contact2CommonIdCardOverdraftCorporate;
    private Boolean contact2CommonIdCardLoanOverdue;
    private Boolean contact2CommonMobileLoanOverdue;
    private Boolean contact2CommonMobileLoanBlackMedium;
    private Boolean contact2CommonMobileLoanOverdueRepay;
    private Boolean contact2CommonMobileIsFake;
    private Boolean contact3IsClose;
    private Boolean contact3ClosedIdCardCourtDishonesty;
    private Boolean contact3ClosedIdCardCourtExecuted;
    private Boolean contact3ClosedIdCardCourtClosed;
    private Boolean contact3ClosedIdCardCrimeWanted;
    private Boolean contact3ClosedIdCardOverdraftCorporate;
    private Boolean contact3ClosedIdCardLoanOverdue;
    private Boolean contact3ClosedMobileLoanOverdue;
    private Boolean contact3ClosedMobileLoanBlackMedium;
    private Boolean contact3ClosedMobileLoanOverdueRepay;
    private Boolean contact3ClosedMobileIsFake;
    private Boolean contact3IsCommon;
    private Boolean contact3CommonIdCardCourtDishonesty;
    private Boolean contact3CommonIdCardCourtExecuted;
    private Boolean contact3CommonIdCardCourtClosed;
    private Boolean contact3CommonIdCardCrimeWanted;
    private Boolean contact3CommonIdCardOverdraftCorporate;
    private Boolean contact3CommonIdCardLoanOverdue;
    private Boolean contact3CommonMobileLoanOverdue;
    private Boolean contact3CommonMobileLoanBlackMedium;
    private Boolean contact3CommonMobileLoanOverdueRepay;
    private Boolean contact3CommonMobileIsFake;
    private Boolean contact4IsClose;
    private Boolean contact4ClosedIdCardCourtDishonesty;
    private Boolean contact4ClosedIdCardCourtExecuted;
    private Boolean contact4ClosedIdCardCourtClosed;
    private Boolean contact4ClosedIdCardCrimeWanted;
    private Boolean contact4ClosedIdCardOverdraftCorporate;
    private Boolean contact4ClosedIdCardLoanOverdue;
    private Boolean contact4ClosedMobileLoanOverdue;
    private Boolean contact4ClosedMobileLoanBlackMedium;
    private Boolean contact4ClosedMobileLoanOverdueRepay;
    private Boolean contact4ClosedMobileIsFake;
    private Boolean contact4IsCommon;
    private Boolean contact4CommonIdCardCourtDishonesty;
    private Boolean contact4CommonIdCardCourtExecuted;
    private Boolean contact4CommonIdCardCourtClosed;
    private Boolean contact4CommonIdCardCrimeWanted;
    private Boolean contact4CommonIdCardOverdraftCorporate;
    private Boolean contact4CommonIdCardLoanOverdue;
    private Boolean contact4CommonMobileLoanOverdue;
    private Boolean contact4CommonMobileLoanBlackMedium;
    private Boolean contact4CommonMobileLoanOverdueRepay;
    private Boolean contact4CommonMobileIsFake;
    private Boolean contact5IsClose;
    private Boolean contact5ClosedIdCardCourtDishonesty;
    private Boolean contact5ClosedIdCardCourtExecuted;
    private Boolean contact5ClosedIdCardCourtClosed;
    private Boolean contact5ClosedIdCardCrimeWanted;
    private Boolean contact5ClosedIdCardOverdraftCorporate;
    private Boolean contact5ClosedIdCardLoanOverdue;
    private Boolean contact5ClosedMobileLoanOverdue;
    private Boolean contact5ClosedMobileLoanBlackMedium;
    private Boolean contact5CommonMobileLoanOverdueRepay;
    private Boolean contact5CommonMobileIsFake;

    public String getFinalDecision() {
        return finalDecision;
    }

    public void setFinalDecision(String finalDecision) {
        this.finalDecision = finalDecision;
    }

    public Boolean getIdCardIsFormatErr() {
        return idCardIsFormatErr;
    }

    public void setIdCardIsFormatErr(Boolean idCardIsFormatErr) {
        this.idCardIsFormatErr = idCardIsFormatErr;
    }

    public Boolean getIdCardIsFormatRight() {
        return idCardIsFormatRight;
    }

    public void setIdCardIsFormatRight(Boolean idCardIsFormatRight) {
        this.idCardIsFormatRight = idCardIsFormatRight;
    }

    public Boolean getIdCardIsSecond() {
        return idCardIsSecond;
    }

    public void setIdCardIsSecond(Boolean idCardIsSecond) {
        this.idCardIsSecond = idCardIsSecond;
    }

    public Boolean getIdCardRegionIsHighRisk() {
        return idCardRegionIsHighRisk;
    }

    public void setIdCardRegionIsHighRisk(Boolean idCardRegionIsHighRisk) {
        this.idCardRegionIsHighRisk = idCardRegionIsHighRisk;
    }

    public Boolean getIdCardIsCourtDishonesty() {
        return idCardIsCourtDishonesty;
    }

    public void setIdCardIsCourtDishonesty(Boolean idCardIsCourtDishonesty) {
        this.idCardIsCourtDishonesty = idCardIsCourtDishonesty;
    }

    public Boolean getIdCardIsCourtExecuted() {
        return idCardIsCourtExecuted;
    }

    public void setIdCardIsCourtExecuted(Boolean idCardIsCourtExecuted) {
        this.idCardIsCourtExecuted = idCardIsCourtExecuted;
    }

    public Boolean getIdCardIsCourtClosed() {
        return idCardIsCourtClosed;
    }

    public void setIdCardIsCourtClosed(Boolean idCardIsCourtClosed) {
        this.idCardIsCourtClosed = idCardIsCourtClosed;
    }

    public Boolean getIdCardIsCrimeWanted() {
        return idCardIsCrimeWanted;
    }

    public void setIdCardIsCrimeWanted(Boolean idCardIsCrimeWanted) {
        this.idCardIsCrimeWanted = idCardIsCrimeWanted;
    }

    public Boolean getIdCardIsStudentLoan() {
        return idCardIsStudentLoan;
    }

    public void setIdCardIsStudentLoan(Boolean idCardIsStudentLoan) {
        this.idCardIsStudentLoan = idCardIsStudentLoan;
    }

    public Boolean getIdCardIsLoanOverdue() {
        return idCardIsLoanOverdue;
    }

    public void setIdCardIsLoanOverdue(Boolean idCardIsLoanOverdue) {
        this.idCardIsLoanOverdue = idCardIsLoanOverdue;
    }

    public Boolean getIdCardIsLoanOverdueRepay() {
        return idCardIsLoanOverdueRepay;
    }

    public void setIdCardIsLoanOverdueRepay(Boolean idCardIsLoanOverdueRepay) {
        this.idCardIsLoanOverdueRepay = idCardIsLoanOverdueRepay;
    }

    public Boolean getIdCardIsHighRiskFocus() {
        return idCardIsHighRiskFocus;
    }

    public void setIdCardIsHighRiskFocus(Boolean idCardIsHighRiskFocus) {
        this.idCardIsHighRiskFocus = idCardIsHighRiskFocus;
    }

    public Boolean getIdCardIsMidRiskFocus() {
        return idCardIsMidRiskFocus;
    }

    public void setIdCardIsMidRiskFocus(Boolean idCardIsMidRiskFocus) {
        this.idCardIsMidRiskFocus = idCardIsMidRiskFocus;
    }

    public Boolean getIdCardIsLowRiskFocus() {
        return idCardIsLowRiskFocus;
    }

    public void setIdCardIsLowRiskFocus(Boolean idCardIsLowRiskFocus) {
        this.idCardIsLowRiskFocus = idCardIsLowRiskFocus;
    }

    public Boolean getIdCardIsVehicleHire() {
        return idCardIsVehicleHire;
    }

    public void setIdCardIsVehicleHire(Boolean idCardIsVehicleHire) {
        this.idCardIsVehicleHire = idCardIsVehicleHire;
    }

    public Boolean getIdCardIsLost() {
        return idCardIsLost;
    }

    public void setIdCardIsLost(Boolean idCardIsLost) {
        this.idCardIsLost = idCardIsLost;
    }

    public Boolean getIdCardIsOverdraftCorporate() {
        return idCardIsOverdraftCorporate;
    }

    public void setIdCardIsOverdraftCorporate(Boolean idCardIsOverdraftCorporate) {
        this.idCardIsOverdraftCorporate = idCardIsOverdraftCorporate;
    }

    public Boolean getIdCardIsTravelDishonesty() {
        return idCardIsTravelDishonesty;
    }

    public void setIdCardIsTravelDishonesty(Boolean idCardIsTravelDishonesty) {
        this.idCardIsTravelDishonesty = idCardIsTravelDishonesty;
    }

    public Boolean getIdCardIsTaxes() {
        return idCardIsTaxes;
    }

    public void setIdCardIsTaxes(Boolean idCardIsTaxes) {
        this.idCardIsTaxes = idCardIsTaxes;
    }

    public Boolean getIdCardIsCompanyTaxes() {
        return idCardIsCompanyTaxes;
    }

    public void setIdCardIsCompanyTaxes(Boolean idCardIsCompanyTaxes) {
        this.idCardIsCompanyTaxes = idCardIsCompanyTaxes;
    }

    public Boolean getIdCardNameIsLoanOverdue() {
        return idCardNameIsLoanOverdue;
    }

    public void setIdCardNameIsLoanOverdue(Boolean idCardNameIsLoanOverdue) {
        this.idCardNameIsLoanOverdue = idCardNameIsLoanOverdue;
    }

    public Boolean getIdCardNameIsCourtDishonesty() {
        return idCardNameIsCourtDishonesty;
    }

    public void setIdCardNameIsCourtDishonesty(Boolean idCardNameIsCourtDishonesty) {
        this.idCardNameIsCourtDishonesty = idCardNameIsCourtDishonesty;
    }

    public Boolean getIdCardNameIsCourtExecuted() {
        return idCardNameIsCourtExecuted;
    }

    public void setIdCardNameIsCourtExecuted(Boolean idCardNameIsCourtExecuted) {
        this.idCardNameIsCourtExecuted = idCardNameIsCourtExecuted;
    }

    public Boolean getIdCardNameIsCourtClosed() {
        return idCardNameIsCourtClosed;
    }

    public void setIdCardNameIsCourtClosed(Boolean idCardNameIsCourtClosed) {
        this.idCardNameIsCourtClosed = idCardNameIsCourtClosed;
    }

    public Boolean getMobileIsFormatErr() {
        return mobileIsFormatErr;
    }

    public void setMobileIsFormatErr(Boolean mobileIsFormatErr) {
        this.mobileIsFormatErr = mobileIsFormatErr;
    }

    public Boolean getMobileIsFormatRight() {
        return mobileIsFormatRight;
    }

    public void setMobileIsFormatRight(Boolean mobileIsFormatRight) {
        this.mobileIsFormatRight = mobileIsFormatRight;
    }

    public Boolean getMobileIsFake() {
        return mobileIsFake;
    }

    public void setMobileIsFake(Boolean mobileIsFake) {
        this.mobileIsFake = mobileIsFake;
    }

    public Boolean getMobileIsSmall() {
        return mobileIsSmall;
    }

    public void setMobileIsSmall(Boolean mobileIsSmall) {
        this.mobileIsSmall = mobileIsSmall;
    }

    public Boolean getMobileIsBilk() {
        return mobileIsBilk;
    }

    public void setMobileIsBilk(Boolean mobileIsBilk) {
        this.mobileIsBilk = mobileIsBilk;
    }

    public Boolean getMobileIsRandomInput() {
        return mobileIsRandomInput;
    }

    public void setMobileIsRandomInput(Boolean mobileIsRandomInput) {
        this.mobileIsRandomInput = mobileIsRandomInput;
    }

    public Boolean getMobileIsHighRiskFocus() {
        return mobileIsHighRiskFocus;
    }

    public void setMobileIsHighRiskFocus(Boolean mobileIsHighRiskFocus) {
        this.mobileIsHighRiskFocus = mobileIsHighRiskFocus;
    }

    public Boolean getMobileIsMidRiskFocus() {
        return mobileIsMidRiskFocus;
    }

    public void setMobileIsMidRiskFocus(Boolean mobileIsMidRiskFocus) {
        this.mobileIsMidRiskFocus = mobileIsMidRiskFocus;
    }

    public Boolean getMobileIsLowRiskFocus() {
        return mobileIsLowRiskFocus;
    }

    public void setMobileIsLowRiskFocus(Boolean mobileIsLowRiskFocus) {
        this.mobileIsLowRiskFocus = mobileIsLowRiskFocus;
    }

    public Boolean getMobileIsLoanBlackMedium() {
        return mobileIsLoanBlackMedium;
    }

    public void setMobileIsLoanBlackMedium(Boolean mobileIsLoanBlackMedium) {
        this.mobileIsLoanBlackMedium = mobileIsLoanBlackMedium;
    }

    public Boolean getMobileIsLoanOverdue() {
        return mobileIsLoanOverdue;
    }

    public void setMobileIsLoanOverdue(Boolean mobileIsLoanOverdue) {
        this.mobileIsLoanOverdue = mobileIsLoanOverdue;
    }

    public Boolean getMobileIsVehicleHire() {
        return mobileIsVehicleHire;
    }

    public void setMobileIsVehicleHire(Boolean mobileIsVehicleHire) {
        this.mobileIsVehicleHire = mobileIsVehicleHire;
    }

    public Boolean getMobileIsOverdraftCorporate() {
        return mobileIsOverdraftCorporate;
    }

    public void setMobileIsOverdraftCorporate(Boolean mobileIsOverdraftCorporate) {
        this.mobileIsOverdraftCorporate = mobileIsOverdraftCorporate;
    }

    public Boolean getMobileIsLost() {
        return mobileIsLost;
    }

    public void setMobileIsLost(Boolean mobileIsLost) {
        this.mobileIsLost = mobileIsLost;
    }

    public Boolean getMobileIsLoanOverdueRepay() {
        return mobileIsLoanOverdueRepay;
    }

    public void setMobileIsLoanOverdueRepay(Boolean mobileIsLoanOverdueRepay) {
        this.mobileIsLoanOverdueRepay = mobileIsLoanOverdueRepay;
    }

    public Boolean getEmailIsFormatErr() {
        return emailIsFormatErr;
    }

    public void setEmailIsFormatErr(Boolean emailIsFormatErr) {
        this.emailIsFormatErr = emailIsFormatErr;
    }

    public Boolean getEmailIsFormatRight() {
        return emailIsFormatRight;
    }

    public void setEmailIsFormatRight(Boolean emailIsFormatRight) {
        this.emailIsFormatRight = emailIsFormatRight;
    }

    public Boolean getEmailIsLoanOverdue() {
        return emailIsLoanOverdue;
    }

    public void setEmailIsLoanOverdue(Boolean emailIsLoanOverdue) {
        this.emailIsLoanOverdue = emailIsLoanOverdue;
    }

    public Boolean getEmailIsLoanOverdueRepay() {
        return emailIsLoanOverdueRepay;
    }

    public void setEmailIsLoanOverdueRepay(Boolean emailIsLoanOverdueRepay) {
        this.emailIsLoanOverdueRepay = emailIsLoanOverdueRepay;
    }

    public Boolean getEmailIsHighRiskFocus() {
        return emailIsHighRiskFocus;
    }

    public void setEmailIsHighRiskFocus(Boolean emailIsHighRiskFocus) {
        this.emailIsHighRiskFocus = emailIsHighRiskFocus;
    }

    public Boolean getEmailIsMidRiskFocus() {
        return emailIsMidRiskFocus;
    }

    public void setEmailIsMidRiskFocus(Boolean emailIsMidRiskFocus) {
        this.emailIsMidRiskFocus = emailIsMidRiskFocus;
    }

    public Boolean getEmailIsLowRiskFocus() {
        return emailIsLowRiskFocus;
    }

    public void setEmailIsLowRiskFocus(Boolean emailIsLowRiskFocus) {
        this.emailIsLowRiskFocus = emailIsLowRiskFocus;
    }

    public Boolean getPhoneIsBilk() {
        return phoneIsBilk;
    }

    public void setPhoneIsBilk(Boolean phoneIsBilk) {
        this.phoneIsBilk = phoneIsBilk;
    }

    public Boolean getPhoneIsLoanOverdue() {
        return phoneIsLoanOverdue;
    }

    public void setPhoneIsLoanOverdue(Boolean phoneIsLoanOverdue) {
        this.phoneIsLoanOverdue = phoneIsLoanOverdue;
    }

    public Boolean getPhoneIsHighRiskFocus() {
        return phoneIsHighRiskFocus;
    }

    public void setPhoneIsHighRiskFocus(Boolean phoneIsHighRiskFocus) {
        this.phoneIsHighRiskFocus = phoneIsHighRiskFocus;
    }

    public Boolean getPhoneIsMidRiskFocus() {
        return phoneIsMidRiskFocus;
    }

    public void setPhoneIsMidRiskFocus(Boolean phoneIsMidRiskFocus) {
        this.phoneIsMidRiskFocus = phoneIsMidRiskFocus;
    }

    public Boolean getPhoneIsLowRiskFocus() {
        return phoneIsLowRiskFocus;
    }

    public void setPhoneIsLowRiskFocus(Boolean phoneIsLowRiskFocus) {
        this.phoneIsLowRiskFocus = phoneIsLowRiskFocus;
    }

    public Boolean getQqIsLoanOverdue() {
        return qqIsLoanOverdue;
    }

    public void setQqIsLoanOverdue(Boolean qqIsLoanOverdue) {
        this.qqIsLoanOverdue = qqIsLoanOverdue;
    }

    public Boolean getQqIsLoanOverdueRepay() {
        return qqIsLoanOverdueRepay;
    }

    public void setQqIsLoanOverdueRepay(Boolean qqIsLoanOverdueRepay) {
        this.qqIsLoanOverdueRepay = qqIsLoanOverdueRepay;
    }

    public Boolean getQqIsHighRiskFocus() {
        return qqIsHighRiskFocus;
    }

    public void setQqIsHighRiskFocus(Boolean qqIsHighRiskFocus) {
        this.qqIsHighRiskFocus = qqIsHighRiskFocus;
    }

    public Boolean getQqIsMidRiskFocus() {
        return qqIsMidRiskFocus;
    }

    public void setQqIsMidRiskFocus(Boolean qqIsMidRiskFocus) {
        this.qqIsMidRiskFocus = qqIsMidRiskFocus;
    }

    public Boolean getQqIsLowRiskFocus() {
        return qqIsLowRiskFocus;
    }

    public void setQqIsLowRiskFocus(Boolean qqIsLowRiskFocus) {
        this.qqIsLowRiskFocus = qqIsLowRiskFocus;
    }

    public Boolean getLoanApplyIsConsist() {
        return loanApplyIsConsist;
    }

    public void setLoanApplyIsConsist(Boolean loanApplyIsConsist) {
        this.loanApplyIsConsist = loanApplyIsConsist;
    }

    public Boolean getUnitNameIsMedium() {
        return unitNameIsMedium;
    }

    public void setUnitNameIsMedium(Boolean unitNameIsMedium) {
        this.unitNameIsMedium = unitNameIsMedium;
    }

    public Boolean getEduAgePosValidRisk() {
        return eduAgePosValidRisk;
    }

    public void setEduAgePosValidRisk(Boolean eduAgePosValidRisk) {
        this.eduAgePosValidRisk = eduAgePosValidRisk;
    }

    public Boolean getEduAgeJobValidRisk() {
        return eduAgeJobValidRisk;
    }

    public void setEduAgeJobValidRisk(Boolean eduAgeJobValidRisk) {
        this.eduAgeJobValidRisk = eduAgeJobValidRisk;
    }

    public Boolean getIsHighRiskAge() {
        return isHighRiskAge;
    }

    public void setIsHighRiskAge(Boolean isHighRiskAge) {
        this.isHighRiskAge = isHighRiskAge;
    }

    public Boolean getIdCardApplys3Month() {
        return idCardApplys3Month;
    }

    public void setIdCardApplys3Month(Boolean idCardApplys3Month) {
        this.idCardApplys3Month = idCardApplys3Month;
    }

    public Boolean getApplyIdCards3Month() {
        return applyIdCards3Month;
    }

    public void setApplyIdCards3Month(Boolean applyIdCards3Month) {
        this.applyIdCards3Month = applyIdCards3Month;
    }

    public Boolean getBankNameIdCards3Month() {
        return bankNameIdCards3Month;
    }

    public void setBankNameIdCards3Month(Boolean bankNameIdCards3Month) {
        this.bankNameIdCards3Month = bankNameIdCards3Month;
    }

    public Boolean getIdCardAsContactNum2() {
        return idCardAsContactNum2;
    }

    public void setIdCardAsContactNum2(Boolean idCardAsContactNum2) {
        this.idCardAsContactNum2 = idCardAsContactNum2;
    }

    public Boolean getMobileAsContactNum2() {
        return mobileAsContactNum2;
    }

    public void setMobileAsContactNum2(Boolean mobileAsContactNum2) {
        this.mobileAsContactNum2 = mobileAsContactNum2;
    }

    public Boolean getAddrIsLoanOverdue() {
        return addrIsLoanOverdue;
    }

    public void setAddrIsLoanOverdue(Boolean addrIsLoanOverdue) {
        this.addrIsLoanOverdue = addrIsLoanOverdue;
    }

    public Boolean getDisIsExceptInShortTime() {
        return disIsExceptInShortTime;
    }

    public void setDisIsExceptInShortTime(Boolean disIsExceptInShortTime) {
        this.disIsExceptInShortTime = disIsExceptInShortTime;
    }

    public Boolean getLoanDevProxyExcept() {
        return loanDevProxyExcept;
    }

    public void setLoanDevProxyExcept(Boolean loanDevProxyExcept) {
        this.loanDevProxyExcept = loanDevProxyExcept;
    }

    public Boolean getMobileAddrIsNotMatchIpCity() {
        return mobileAddrIsNotMatchIpCity;
    }

    public void setMobileAddrIsNotMatchIpCity(Boolean mobileAddrIsNotMatchIpCity) {
        this.mobileAddrIsNotMatchIpCity = mobileAddrIsNotMatchIpCity;
    }

    public Boolean getApplyIpIsNotMatchIpCity() {
        return applyIpIsNotMatchIpCity;
    }

    public void setApplyIpIsNotMatchIpCity(Boolean applyIpIsNotMatchIpCity) {
        this.applyIpIsNotMatchIpCity = applyIpIsNotMatchIpCity;
    }

    public Boolean getDevIdCardMobileApplyOver7() {
        return devIdCardMobileApplyOver7;
    }

    public void setDevIdCardMobileApplyOver7(Boolean devIdCardMobileApplyOver7) {
        this.devIdCardMobileApplyOver7 = devIdCardMobileApplyOver7;
    }

    public Boolean getDevIdCardMobileApplyOver30() {
        return devIdCardMobileApplyOver30;
    }

    public void setDevIdCardMobileApplyOver30(Boolean devIdCardMobileApplyOver30) {
        this.devIdCardMobileApplyOver30 = devIdCardMobileApplyOver30;
    }

    public Boolean getOverIdCardMobile1() {
        return overIdCardMobile1;
    }

    public void setOverIdCardMobile1(Boolean overIdCardMobile1) {
        this.overIdCardMobile1 = overIdCardMobile1;
    }

    public Boolean getOverIdCardMobile7() {
        return overIdCardMobile7;
    }

    public void setOverIdCardMobile7(Boolean overIdCardMobile7) {
        this.overIdCardMobile7 = overIdCardMobile7;
    }

    public Boolean getIdCardOverDev1() {
        return idCardOverDev1;
    }

    public void setIdCardOverDev1(Boolean idCardOverDev1) {
        this.idCardOverDev1 = idCardOverDev1;
    }

    public Boolean getIdCardOverDev7() {
        return idCardOverDev7;
    }

    public void setIdCardOverDev7(Boolean idCardOverDev7) {
        this.idCardOverDev7 = idCardOverDev7;
    }

    public Boolean getIdCardOverDev30() {
        return idCardOverDev30;
    }

    public void setIdCardOverDev30(Boolean idCardOverDev30) {
        this.idCardOverDev30 = idCardOverDev30;
    }

    public Boolean getApplyLoanOnPlats7() {
        return applyLoanOnPlats7;
    }

    public void setApplyLoanOnPlats7(Boolean applyLoanOnPlats7) {
        this.applyLoanOnPlats7 = applyLoanOnPlats7;
    }

    public Boolean getApplyLoanOnPlats30() {
        return applyLoanOnPlats30;
    }

    public void setApplyLoanOnPlats30(Boolean applyLoanOnPlats30) {
        this.applyLoanOnPlats30 = applyLoanOnPlats30;
    }

    public Boolean getApplyLoanOnPlats3Month() {
        return applyLoanOnPlats3Month;
    }

    public void setApplyLoanOnPlats3Month(Boolean applyLoanOnPlats3Month) {
        this.applyLoanOnPlats3Month = applyLoanOnPlats3Month;
    }

    public Boolean getApplyLoanOnPlats6Month() {
        return applyLoanOnPlats6Month;
    }

    public void setApplyLoanOnPlats6Month(Boolean applyLoanOnPlats6Month) {
        this.applyLoanOnPlats6Month = applyLoanOnPlats6Month;
    }

    public Boolean getApplyLoanOnPlats12Month() {
        return applyLoanOnPlats12Month;
    }

    public void setApplyLoanOnPlats12Month(Boolean applyLoanOnPlats12Month) {
        this.applyLoanOnPlats12Month = applyLoanOnPlats12Month;
    }

    public Boolean getApplyLoanOnPlats18Month() {
        return applyLoanOnPlats18Month;
    }

    public void setApplyLoanOnPlats18Month(Boolean applyLoanOnPlats18Month) {
        this.applyLoanOnPlats18Month = applyLoanOnPlats18Month;
    }

    public Boolean getApplyLoanOnPlats24Month() {
        return applyLoanOnPlats24Month;
    }

    public void setApplyLoanOnPlats24Month(Boolean applyLoanOnPlats24Month) {
        this.applyLoanOnPlats24Month = applyLoanOnPlats24Month;
    }

    public Boolean getApplyLoanOnPlats60Month() {
        return applyLoanOnPlats60Month;
    }

    public void setApplyLoanOnPlats60Month(Boolean applyLoanOnPlats60Month) {
        this.applyLoanOnPlats60Month = applyLoanOnPlats60Month;
    }

    public Integer getApplyLoanOnPlats7Cnt() {
        return applyLoanOnPlats7Cnt;
    }

    public void setApplyLoanOnPlats7Cnt(Integer applyLoanOnPlats7Cnt) {
        this.applyLoanOnPlats7Cnt = applyLoanOnPlats7Cnt;
    }

    public Integer getApplyLoanOnPlats30Cnt() {
        return applyLoanOnPlats30Cnt;
    }

    public void setApplyLoanOnPlats30Cnt(Integer applyLoanOnPlats30Cnt) {
        this.applyLoanOnPlats30Cnt = applyLoanOnPlats30Cnt;
    }

    public Integer getApplyLoanOnPlats3MonthCnt() {
        return applyLoanOnPlats3MonthCnt;
    }

    public void setApplyLoanOnPlats3MonthCnt(Integer applyLoanOnPlats3MonthCnt) {
        this.applyLoanOnPlats3MonthCnt = applyLoanOnPlats3MonthCnt;
    }

    public Integer getApplyLoanOnPlats6MonthCnt() {
        return applyLoanOnPlats6MonthCnt;
    }

    public void setApplyLoanOnPlats6MonthCnt(Integer applyLoanOnPlats6MonthCnt) {
        this.applyLoanOnPlats6MonthCnt = applyLoanOnPlats6MonthCnt;
    }

    public Integer getApplyLoanOnPlats12MonthCnt() {
        return applyLoanOnPlats12MonthCnt;
    }

    public void setApplyLoanOnPlats12MonthCnt(Integer applyLoanOnPlats12MonthCnt) {
        this.applyLoanOnPlats12MonthCnt = applyLoanOnPlats12MonthCnt;
    }

    public Integer getApplyLoanOnPlats18MonthCnt() {
        return applyLoanOnPlats18MonthCnt;
    }

    public void setApplyLoanOnPlats18MonthCnt(Integer applyLoanOnPlats18MonthCnt) {
        this.applyLoanOnPlats18MonthCnt = applyLoanOnPlats18MonthCnt;
    }

    public Integer getApplyLoanOnPlats24MonthCnt() {
        return applyLoanOnPlats24MonthCnt;
    }

    public void setApplyLoanOnPlats24MonthCnt(Integer applyLoanOnPlats24MonthCnt) {
        this.applyLoanOnPlats24MonthCnt = applyLoanOnPlats24MonthCnt;
    }

    public Integer getApplyLoanOnPlats60MonthCnt() {
        return applyLoanOnPlats60MonthCnt;
    }

    public void setApplyLoanOnPlats60MonthCnt(Integer applyLoanOnPlats60MonthCnt) {
        this.applyLoanOnPlats60MonthCnt = applyLoanOnPlats60MonthCnt;
    }

    public Boolean getContact1IsClose() {
        return contact1IsClose;
    }

    public void setContact1IsClose(Boolean contact1IsClose) {
        this.contact1IsClose = contact1IsClose;
    }

    public Boolean getContact1ClosedIdCardCourtDishonesty() {
        return contact1ClosedIdCardCourtDishonesty;
    }

    public void setContact1ClosedIdCardCourtDishonesty(Boolean contact1ClosedIdCardCourtDishonesty) {
        this.contact1ClosedIdCardCourtDishonesty = contact1ClosedIdCardCourtDishonesty;
    }

    public Boolean getContact1ClosedIdCardCourtExecuted() {
        return contact1ClosedIdCardCourtExecuted;
    }

    public void setContact1ClosedIdCardCourtExecuted(Boolean contact1ClosedIdCardCourtExecuted) {
        this.contact1ClosedIdCardCourtExecuted = contact1ClosedIdCardCourtExecuted;
    }

    public Boolean getContact1ClosedIdCardCourtClosed() {
        return contact1ClosedIdCardCourtClosed;
    }

    public void setContact1ClosedIdCardCourtClosed(Boolean contact1ClosedIdCardCourtClosed) {
        this.contact1ClosedIdCardCourtClosed = contact1ClosedIdCardCourtClosed;
    }

    public Boolean getContact1ClosedIdCardCrimeWanted() {
        return contact1ClosedIdCardCrimeWanted;
    }

    public void setContact1ClosedIdCardCrimeWanted(Boolean contact1ClosedIdCardCrimeWanted) {
        this.contact1ClosedIdCardCrimeWanted = contact1ClosedIdCardCrimeWanted;
    }

    public Boolean getContact1ClosedIdCardOverdraftCorporate() {
        return contact1ClosedIdCardOverdraftCorporate;
    }

    public void setContact1ClosedIdCardOverdraftCorporate(Boolean contact1ClosedIdCardOverdraftCorporate) {
        this.contact1ClosedIdCardOverdraftCorporate = contact1ClosedIdCardOverdraftCorporate;
    }

    public Boolean getContact1ClosedIdCardLoanOverdue() {
        return contact1ClosedIdCardLoanOverdue;
    }

    public void setContact1ClosedIdCardLoanOverdue(Boolean contact1ClosedIdCardLoanOverdue) {
        this.contact1ClosedIdCardLoanOverdue = contact1ClosedIdCardLoanOverdue;
    }

    public Boolean getContact1ClosedMobileLoanOverdue() {
        return contact1ClosedMobileLoanOverdue;
    }

    public void setContact1ClosedMobileLoanOverdue(Boolean contact1ClosedMobileLoanOverdue) {
        this.contact1ClosedMobileLoanOverdue = contact1ClosedMobileLoanOverdue;
    }

    public Boolean getContact1ClosedMobileLoanBlackMedium() {
        return contact1ClosedMobileLoanBlackMedium;
    }

    public void setContact1ClosedMobileLoanBlackMedium(Boolean contact1ClosedMobileLoanBlackMedium) {
        this.contact1ClosedMobileLoanBlackMedium = contact1ClosedMobileLoanBlackMedium;
    }

    public Boolean getContact1ClosedMobileLoanOverdueRepay() {
        return contact1ClosedMobileLoanOverdueRepay;
    }

    public void setContact1ClosedMobileLoanOverdueRepay(Boolean contact1ClosedMobileLoanOverdueRepay) {
        this.contact1ClosedMobileLoanOverdueRepay = contact1ClosedMobileLoanOverdueRepay;
    }

    public Boolean getContact1ClosedMobileIsFake() {
        return contact1ClosedMobileIsFake;
    }

    public void setContact1ClosedMobileIsFake(Boolean contact1ClosedMobileIsFake) {
        this.contact1ClosedMobileIsFake = contact1ClosedMobileIsFake;
    }

    public Boolean getContact1IsCommon() {
        return contact1IsCommon;
    }

    public void setContact1IsCommon(Boolean contact1IsCommon) {
        this.contact1IsCommon = contact1IsCommon;
    }

    public Boolean getContact1CommonIdCardCourtDishonesty() {
        return contact1CommonIdCardCourtDishonesty;
    }

    public void setContact1CommonIdCardCourtDishonesty(Boolean contact1CommonIdCardCourtDishonesty) {
        this.contact1CommonIdCardCourtDishonesty = contact1CommonIdCardCourtDishonesty;
    }

    public Boolean getContact1CommonIdCardCourtExecuted() {
        return contact1CommonIdCardCourtExecuted;
    }

    public void setContact1CommonIdCardCourtExecuted(Boolean contact1CommonIdCardCourtExecuted) {
        this.contact1CommonIdCardCourtExecuted = contact1CommonIdCardCourtExecuted;
    }

    public Boolean getContact1CommonIdCardCourtClosed() {
        return contact1CommonIdCardCourtClosed;
    }

    public void setContact1CommonIdCardCourtClosed(Boolean contact1CommonIdCardCourtClosed) {
        this.contact1CommonIdCardCourtClosed = contact1CommonIdCardCourtClosed;
    }

    public Boolean getContact1CommonIdCardCrimeWanted() {
        return contact1CommonIdCardCrimeWanted;
    }

    public void setContact1CommonIdCardCrimeWanted(Boolean contact1CommonIdCardCrimeWanted) {
        this.contact1CommonIdCardCrimeWanted = contact1CommonIdCardCrimeWanted;
    }

    public Boolean getContact1CommonIdCardOverdraftCorporate() {
        return contact1CommonIdCardOverdraftCorporate;
    }

    public void setContact1CommonIdCardOverdraftCorporate(Boolean contact1CommonIdCardOverdraftCorporate) {
        this.contact1CommonIdCardOverdraftCorporate = contact1CommonIdCardOverdraftCorporate;
    }

    public Boolean getContact1CommonIdCardLoanOverdue() {
        return contact1CommonIdCardLoanOverdue;
    }

    public void setContact1CommonIdCardLoanOverdue(Boolean contact1CommonIdCardLoanOverdue) {
        this.contact1CommonIdCardLoanOverdue = contact1CommonIdCardLoanOverdue;
    }

    public Boolean getContact1CommonMobileLoanOverdue() {
        return contact1CommonMobileLoanOverdue;
    }

    public void setContact1CommonMobileLoanOverdue(Boolean contact1CommonMobileLoanOverdue) {
        this.contact1CommonMobileLoanOverdue = contact1CommonMobileLoanOverdue;
    }

    public Boolean getContact1CommonMobileLoanBlackMedium() {
        return contact1CommonMobileLoanBlackMedium;
    }

    public void setContact1CommonMobileLoanBlackMedium(Boolean contact1CommonMobileLoanBlackMedium) {
        this.contact1CommonMobileLoanBlackMedium = contact1CommonMobileLoanBlackMedium;
    }

    public Boolean getContact1CommonMobileLoanOverdueRepay() {
        return contact1CommonMobileLoanOverdueRepay;
    }

    public void setContact1CommonMobileLoanOverdueRepay(Boolean contact1CommonMobileLoanOverdueRepay) {
        this.contact1CommonMobileLoanOverdueRepay = contact1CommonMobileLoanOverdueRepay;
    }

    public Boolean getContact1CommonMobileIsFake() {
        return contact1CommonMobileIsFake;
    }

    public void setContact1CommonMobileIsFake(Boolean contact1CommonMobileIsFake) {
        this.contact1CommonMobileIsFake = contact1CommonMobileIsFake;
    }

    public Boolean getContact2IsClose() {
        return contact2IsClose;
    }

    public void setContact2IsClose(Boolean contact2IsClose) {
        this.contact2IsClose = contact2IsClose;
    }

    public Boolean getContact2ClosedIdCardCourtDishonesty() {
        return contact2ClosedIdCardCourtDishonesty;
    }

    public void setContact2ClosedIdCardCourtDishonesty(Boolean contact2ClosedIdCardCourtDishonesty) {
        this.contact2ClosedIdCardCourtDishonesty = contact2ClosedIdCardCourtDishonesty;
    }

    public Boolean getContact2ClosedIdCardCourtExecuted() {
        return contact2ClosedIdCardCourtExecuted;
    }

    public void setContact2ClosedIdCardCourtExecuted(Boolean contact2ClosedIdCardCourtExecuted) {
        this.contact2ClosedIdCardCourtExecuted = contact2ClosedIdCardCourtExecuted;
    }

    public Boolean getContact2ClosedIdCardCourtClosed() {
        return contact2ClosedIdCardCourtClosed;
    }

    public void setContact2ClosedIdCardCourtClosed(Boolean contact2ClosedIdCardCourtClosed) {
        this.contact2ClosedIdCardCourtClosed = contact2ClosedIdCardCourtClosed;
    }

    public Boolean getContact2ClosedIdCardCrimeWanted() {
        return contact2ClosedIdCardCrimeWanted;
    }

    public void setContact2ClosedIdCardCrimeWanted(Boolean contact2ClosedIdCardCrimeWanted) {
        this.contact2ClosedIdCardCrimeWanted = contact2ClosedIdCardCrimeWanted;
    }

    public Boolean getContact2ClosedIdCardOverdraftCorporate() {
        return contact2ClosedIdCardOverdraftCorporate;
    }

    public void setContact2ClosedIdCardOverdraftCorporate(Boolean contact2ClosedIdCardOverdraftCorporate) {
        this.contact2ClosedIdCardOverdraftCorporate = contact2ClosedIdCardOverdraftCorporate;
    }

    public Boolean getContact2ClosedIdCardLoanOverdue() {
        return contact2ClosedIdCardLoanOverdue;
    }

    public void setContact2ClosedIdCardLoanOverdue(Boolean contact2ClosedIdCardLoanOverdue) {
        this.contact2ClosedIdCardLoanOverdue = contact2ClosedIdCardLoanOverdue;
    }

    public Boolean getContact2ClosedMobileLoanOverdue() {
        return contact2ClosedMobileLoanOverdue;
    }

    public void setContact2ClosedMobileLoanOverdue(Boolean contact2ClosedMobileLoanOverdue) {
        this.contact2ClosedMobileLoanOverdue = contact2ClosedMobileLoanOverdue;
    }

    public Boolean getContact2ClosedMobileLoanBlackMedium() {
        return contact2ClosedMobileLoanBlackMedium;
    }

    public void setContact2ClosedMobileLoanBlackMedium(Boolean contact2ClosedMobileLoanBlackMedium) {
        this.contact2ClosedMobileLoanBlackMedium = contact2ClosedMobileLoanBlackMedium;
    }

    public Boolean getContact2ClosedMobileLoanOverdueRepay() {
        return contact2ClosedMobileLoanOverdueRepay;
    }

    public void setContact2ClosedMobileLoanOverdueRepay(Boolean contact2ClosedMobileLoanOverdueRepay) {
        this.contact2ClosedMobileLoanOverdueRepay = contact2ClosedMobileLoanOverdueRepay;
    }

    public Boolean getContact2ClosedMobileIsFake() {
        return contact2ClosedMobileIsFake;
    }

    public void setContact2ClosedMobileIsFake(Boolean contact2ClosedMobileIsFake) {
        this.contact2ClosedMobileIsFake = contact2ClosedMobileIsFake;
    }

    public Boolean getContact2IsCommon() {
        return contact2IsCommon;
    }

    public void setContact2IsCommon(Boolean contact2IsCommon) {
        this.contact2IsCommon = contact2IsCommon;
    }

    public Boolean getContact2CommonIdCardCourtDishonesty() {
        return contact2CommonIdCardCourtDishonesty;
    }

    public void setContact2CommonIdCardCourtDishonesty(Boolean contact2CommonIdCardCourtDishonesty) {
        this.contact2CommonIdCardCourtDishonesty = contact2CommonIdCardCourtDishonesty;
    }

    public Boolean getContact2CommonIdCardCourtExecuted() {
        return contact2CommonIdCardCourtExecuted;
    }

    public void setContact2CommonIdCardCourtExecuted(Boolean contact2CommonIdCardCourtExecuted) {
        this.contact2CommonIdCardCourtExecuted = contact2CommonIdCardCourtExecuted;
    }

    public Boolean getContact2CommonIdCardCourtClosed() {
        return contact2CommonIdCardCourtClosed;
    }

    public void setContact2CommonIdCardCourtClosed(Boolean contact2CommonIdCardCourtClosed) {
        this.contact2CommonIdCardCourtClosed = contact2CommonIdCardCourtClosed;
    }

    public Boolean getContact2CommonIdCardCrimeWanted() {
        return contact2CommonIdCardCrimeWanted;
    }

    public void setContact2CommonIdCardCrimeWanted(Boolean contact2CommonIdCardCrimeWanted) {
        this.contact2CommonIdCardCrimeWanted = contact2CommonIdCardCrimeWanted;
    }

    public Boolean getContact2CommonIdCardOverdraftCorporate() {
        return contact2CommonIdCardOverdraftCorporate;
    }

    public void setContact2CommonIdCardOverdraftCorporate(Boolean contact2CommonIdCardOverdraftCorporate) {
        this.contact2CommonIdCardOverdraftCorporate = contact2CommonIdCardOverdraftCorporate;
    }

    public Boolean getContact2CommonIdCardLoanOverdue() {
        return contact2CommonIdCardLoanOverdue;
    }

    public void setContact2CommonIdCardLoanOverdue(Boolean contact2CommonIdCardLoanOverdue) {
        this.contact2CommonIdCardLoanOverdue = contact2CommonIdCardLoanOverdue;
    }

    public Boolean getContact2CommonMobileLoanOverdue() {
        return contact2CommonMobileLoanOverdue;
    }

    public void setContact2CommonMobileLoanOverdue(Boolean contact2CommonMobileLoanOverdue) {
        this.contact2CommonMobileLoanOverdue = contact2CommonMobileLoanOverdue;
    }

    public Boolean getContact2CommonMobileLoanBlackMedium() {
        return contact2CommonMobileLoanBlackMedium;
    }

    public void setContact2CommonMobileLoanBlackMedium(Boolean contact2CommonMobileLoanBlackMedium) {
        this.contact2CommonMobileLoanBlackMedium = contact2CommonMobileLoanBlackMedium;
    }

    public Boolean getContact2CommonMobileLoanOverdueRepay() {
        return contact2CommonMobileLoanOverdueRepay;
    }

    public void setContact2CommonMobileLoanOverdueRepay(Boolean contact2CommonMobileLoanOverdueRepay) {
        this.contact2CommonMobileLoanOverdueRepay = contact2CommonMobileLoanOverdueRepay;
    }

    public Boolean getContact2CommonMobileIsFake() {
        return contact2CommonMobileIsFake;
    }

    public void setContact2CommonMobileIsFake(Boolean contact2CommonMobileIsFake) {
        this.contact2CommonMobileIsFake = contact2CommonMobileIsFake;
    }

    public Boolean getContact3IsClose() {
        return contact3IsClose;
    }

    public void setContact3IsClose(Boolean contact3IsClose) {
        this.contact3IsClose = contact3IsClose;
    }

    public Boolean getContact3ClosedIdCardCourtDishonesty() {
        return contact3ClosedIdCardCourtDishonesty;
    }

    public void setContact3ClosedIdCardCourtDishonesty(Boolean contact3ClosedIdCardCourtDishonesty) {
        this.contact3ClosedIdCardCourtDishonesty = contact3ClosedIdCardCourtDishonesty;
    }

    public Boolean getContact3ClosedIdCardCourtExecuted() {
        return contact3ClosedIdCardCourtExecuted;
    }

    public void setContact3ClosedIdCardCourtExecuted(Boolean contact3ClosedIdCardCourtExecuted) {
        this.contact3ClosedIdCardCourtExecuted = contact3ClosedIdCardCourtExecuted;
    }

    public Boolean getContact3ClosedIdCardCourtClosed() {
        return contact3ClosedIdCardCourtClosed;
    }

    public void setContact3ClosedIdCardCourtClosed(Boolean contact3ClosedIdCardCourtClosed) {
        this.contact3ClosedIdCardCourtClosed = contact3ClosedIdCardCourtClosed;
    }

    public Boolean getContact3ClosedIdCardCrimeWanted() {
        return contact3ClosedIdCardCrimeWanted;
    }

    public void setContact3ClosedIdCardCrimeWanted(Boolean contact3ClosedIdCardCrimeWanted) {
        this.contact3ClosedIdCardCrimeWanted = contact3ClosedIdCardCrimeWanted;
    }

    public Boolean getContact3ClosedIdCardOverdraftCorporate() {
        return contact3ClosedIdCardOverdraftCorporate;
    }

    public void setContact3ClosedIdCardOverdraftCorporate(Boolean contact3ClosedIdCardOverdraftCorporate) {
        this.contact3ClosedIdCardOverdraftCorporate = contact3ClosedIdCardOverdraftCorporate;
    }

    public Boolean getContact3ClosedIdCardLoanOverdue() {
        return contact3ClosedIdCardLoanOverdue;
    }

    public void setContact3ClosedIdCardLoanOverdue(Boolean contact3ClosedIdCardLoanOverdue) {
        this.contact3ClosedIdCardLoanOverdue = contact3ClosedIdCardLoanOverdue;
    }

    public Boolean getContact3ClosedMobileLoanOverdue() {
        return contact3ClosedMobileLoanOverdue;
    }

    public void setContact3ClosedMobileLoanOverdue(Boolean contact3ClosedMobileLoanOverdue) {
        this.contact3ClosedMobileLoanOverdue = contact3ClosedMobileLoanOverdue;
    }

    public Boolean getContact3ClosedMobileLoanBlackMedium() {
        return contact3ClosedMobileLoanBlackMedium;
    }

    public void setContact3ClosedMobileLoanBlackMedium(Boolean contact3ClosedMobileLoanBlackMedium) {
        this.contact3ClosedMobileLoanBlackMedium = contact3ClosedMobileLoanBlackMedium;
    }

    public Boolean getContact3ClosedMobileLoanOverdueRepay() {
        return contact3ClosedMobileLoanOverdueRepay;
    }

    public void setContact3ClosedMobileLoanOverdueRepay(Boolean contact3ClosedMobileLoanOverdueRepay) {
        this.contact3ClosedMobileLoanOverdueRepay = contact3ClosedMobileLoanOverdueRepay;
    }

    public Boolean getContact3ClosedMobileIsFake() {
        return contact3ClosedMobileIsFake;
    }

    public void setContact3ClosedMobileIsFake(Boolean contact3ClosedMobileIsFake) {
        this.contact3ClosedMobileIsFake = contact3ClosedMobileIsFake;
    }

    public Boolean getContact3IsCommon() {
        return contact3IsCommon;
    }

    public void setContact3IsCommon(Boolean contact3IsCommon) {
        this.contact3IsCommon = contact3IsCommon;
    }

    public Boolean getContact3CommonIdCardCourtDishonesty() {
        return contact3CommonIdCardCourtDishonesty;
    }

    public void setContact3CommonIdCardCourtDishonesty(Boolean contact3CommonIdCardCourtDishonesty) {
        this.contact3CommonIdCardCourtDishonesty = contact3CommonIdCardCourtDishonesty;
    }

    public Boolean getContact3CommonIdCardCourtExecuted() {
        return contact3CommonIdCardCourtExecuted;
    }

    public void setContact3CommonIdCardCourtExecuted(Boolean contact3CommonIdCardCourtExecuted) {
        this.contact3CommonIdCardCourtExecuted = contact3CommonIdCardCourtExecuted;
    }

    public Boolean getContact3CommonIdCardCourtClosed() {
        return contact3CommonIdCardCourtClosed;
    }

    public void setContact3CommonIdCardCourtClosed(Boolean contact3CommonIdCardCourtClosed) {
        this.contact3CommonIdCardCourtClosed = contact3CommonIdCardCourtClosed;
    }

    public Boolean getContact3CommonIdCardCrimeWanted() {
        return contact3CommonIdCardCrimeWanted;
    }

    public void setContact3CommonIdCardCrimeWanted(Boolean contact3CommonIdCardCrimeWanted) {
        this.contact3CommonIdCardCrimeWanted = contact3CommonIdCardCrimeWanted;
    }

    public Boolean getContact3CommonIdCardOverdraftCorporate() {
        return contact3CommonIdCardOverdraftCorporate;
    }

    public void setContact3CommonIdCardOverdraftCorporate(Boolean contact3CommonIdCardOverdraftCorporate) {
        this.contact3CommonIdCardOverdraftCorporate = contact3CommonIdCardOverdraftCorporate;
    }

    public Boolean getContact3CommonIdCardLoanOverdue() {
        return contact3CommonIdCardLoanOverdue;
    }

    public void setContact3CommonIdCardLoanOverdue(Boolean contact3CommonIdCardLoanOverdue) {
        this.contact3CommonIdCardLoanOverdue = contact3CommonIdCardLoanOverdue;
    }

    public Boolean getContact3CommonMobileLoanOverdue() {
        return contact3CommonMobileLoanOverdue;
    }

    public void setContact3CommonMobileLoanOverdue(Boolean contact3CommonMobileLoanOverdue) {
        this.contact3CommonMobileLoanOverdue = contact3CommonMobileLoanOverdue;
    }

    public Boolean getContact3CommonMobileLoanBlackMedium() {
        return contact3CommonMobileLoanBlackMedium;
    }

    public void setContact3CommonMobileLoanBlackMedium(Boolean contact3CommonMobileLoanBlackMedium) {
        this.contact3CommonMobileLoanBlackMedium = contact3CommonMobileLoanBlackMedium;
    }

    public Boolean getContact3CommonMobileLoanOverdueRepay() {
        return contact3CommonMobileLoanOverdueRepay;
    }

    public void setContact3CommonMobileLoanOverdueRepay(Boolean contact3CommonMobileLoanOverdueRepay) {
        this.contact3CommonMobileLoanOverdueRepay = contact3CommonMobileLoanOverdueRepay;
    }

    public Boolean getContact3CommonMobileIsFake() {
        return contact3CommonMobileIsFake;
    }

    public void setContact3CommonMobileIsFake(Boolean contact3CommonMobileIsFake) {
        this.contact3CommonMobileIsFake = contact3CommonMobileIsFake;
    }

    public Boolean getContact4IsClose() {
        return contact4IsClose;
    }

    public void setContact4IsClose(Boolean contact4IsClose) {
        this.contact4IsClose = contact4IsClose;
    }

    public Boolean getContact4ClosedIdCardCourtDishonesty() {
        return contact4ClosedIdCardCourtDishonesty;
    }

    public void setContact4ClosedIdCardCourtDishonesty(Boolean contact4ClosedIdCardCourtDishonesty) {
        this.contact4ClosedIdCardCourtDishonesty = contact4ClosedIdCardCourtDishonesty;
    }

    public Boolean getContact4ClosedIdCardCourtExecuted() {
        return contact4ClosedIdCardCourtExecuted;
    }

    public void setContact4ClosedIdCardCourtExecuted(Boolean contact4ClosedIdCardCourtExecuted) {
        this.contact4ClosedIdCardCourtExecuted = contact4ClosedIdCardCourtExecuted;
    }

    public Boolean getContact4ClosedIdCardCourtClosed() {
        return contact4ClosedIdCardCourtClosed;
    }

    public void setContact4ClosedIdCardCourtClosed(Boolean contact4ClosedIdCardCourtClosed) {
        this.contact4ClosedIdCardCourtClosed = contact4ClosedIdCardCourtClosed;
    }

    public Boolean getContact4ClosedIdCardCrimeWanted() {
        return contact4ClosedIdCardCrimeWanted;
    }

    public void setContact4ClosedIdCardCrimeWanted(Boolean contact4ClosedIdCardCrimeWanted) {
        this.contact4ClosedIdCardCrimeWanted = contact4ClosedIdCardCrimeWanted;
    }

    public Boolean getContact4ClosedIdCardOverdraftCorporate() {
        return contact4ClosedIdCardOverdraftCorporate;
    }

    public void setContact4ClosedIdCardOverdraftCorporate(Boolean contact4ClosedIdCardOverdraftCorporate) {
        this.contact4ClosedIdCardOverdraftCorporate = contact4ClosedIdCardOverdraftCorporate;
    }

    public Boolean getContact4ClosedIdCardLoanOverdue() {
        return contact4ClosedIdCardLoanOverdue;
    }

    public void setContact4ClosedIdCardLoanOverdue(Boolean contact4ClosedIdCardLoanOverdue) {
        this.contact4ClosedIdCardLoanOverdue = contact4ClosedIdCardLoanOverdue;
    }

    public Boolean getContact4ClosedMobileLoanOverdue() {
        return contact4ClosedMobileLoanOverdue;
    }

    public void setContact4ClosedMobileLoanOverdue(Boolean contact4ClosedMobileLoanOverdue) {
        this.contact4ClosedMobileLoanOverdue = contact4ClosedMobileLoanOverdue;
    }

    public Boolean getContact4ClosedMobileLoanBlackMedium() {
        return contact4ClosedMobileLoanBlackMedium;
    }

    public void setContact4ClosedMobileLoanBlackMedium(Boolean contact4ClosedMobileLoanBlackMedium) {
        this.contact4ClosedMobileLoanBlackMedium = contact4ClosedMobileLoanBlackMedium;
    }

    public Boolean getContact4ClosedMobileLoanOverdueRepay() {
        return contact4ClosedMobileLoanOverdueRepay;
    }

    public void setContact4ClosedMobileLoanOverdueRepay(Boolean contact4ClosedMobileLoanOverdueRepay) {
        this.contact4ClosedMobileLoanOverdueRepay = contact4ClosedMobileLoanOverdueRepay;
    }

    public Boolean getContact4ClosedMobileIsFake() {
        return contact4ClosedMobileIsFake;
    }

    public void setContact4ClosedMobileIsFake(Boolean contact4ClosedMobileIsFake) {
        this.contact4ClosedMobileIsFake = contact4ClosedMobileIsFake;
    }

    public Boolean getContact4IsCommon() {
        return contact4IsCommon;
    }

    public void setContact4IsCommon(Boolean contact4IsCommon) {
        this.contact4IsCommon = contact4IsCommon;
    }

    public Boolean getContact4CommonIdCardCourtDishonesty() {
        return contact4CommonIdCardCourtDishonesty;
    }

    public void setContact4CommonIdCardCourtDishonesty(Boolean contact4CommonIdCardCourtDishonesty) {
        this.contact4CommonIdCardCourtDishonesty = contact4CommonIdCardCourtDishonesty;
    }

    public Boolean getContact4CommonIdCardCourtExecuted() {
        return contact4CommonIdCardCourtExecuted;
    }

    public void setContact4CommonIdCardCourtExecuted(Boolean contact4CommonIdCardCourtExecuted) {
        this.contact4CommonIdCardCourtExecuted = contact4CommonIdCardCourtExecuted;
    }

    public Boolean getContact4CommonIdCardCourtClosed() {
        return contact4CommonIdCardCourtClosed;
    }

    public void setContact4CommonIdCardCourtClosed(Boolean contact4CommonIdCardCourtClosed) {
        this.contact4CommonIdCardCourtClosed = contact4CommonIdCardCourtClosed;
    }

    public Boolean getContact4CommonIdCardCrimeWanted() {
        return contact4CommonIdCardCrimeWanted;
    }

    public void setContact4CommonIdCardCrimeWanted(Boolean contact4CommonIdCardCrimeWanted) {
        this.contact4CommonIdCardCrimeWanted = contact4CommonIdCardCrimeWanted;
    }

    public Boolean getContact4CommonIdCardOverdraftCorporate() {
        return contact4CommonIdCardOverdraftCorporate;
    }

    public void setContact4CommonIdCardOverdraftCorporate(Boolean contact4CommonIdCardOverdraftCorporate) {
        this.contact4CommonIdCardOverdraftCorporate = contact4CommonIdCardOverdraftCorporate;
    }

    public Boolean getContact4CommonIdCardLoanOverdue() {
        return contact4CommonIdCardLoanOverdue;
    }

    public void setContact4CommonIdCardLoanOverdue(Boolean contact4CommonIdCardLoanOverdue) {
        this.contact4CommonIdCardLoanOverdue = contact4CommonIdCardLoanOverdue;
    }

    public Boolean getContact4CommonMobileLoanOverdue() {
        return contact4CommonMobileLoanOverdue;
    }

    public void setContact4CommonMobileLoanOverdue(Boolean contact4CommonMobileLoanOverdue) {
        this.contact4CommonMobileLoanOverdue = contact4CommonMobileLoanOverdue;
    }

    public Boolean getContact4CommonMobileLoanBlackMedium() {
        return contact4CommonMobileLoanBlackMedium;
    }

    public void setContact4CommonMobileLoanBlackMedium(Boolean contact4CommonMobileLoanBlackMedium) {
        this.contact4CommonMobileLoanBlackMedium = contact4CommonMobileLoanBlackMedium;
    }

    public Boolean getContact4CommonMobileLoanOverdueRepay() {
        return contact4CommonMobileLoanOverdueRepay;
    }

    public void setContact4CommonMobileLoanOverdueRepay(Boolean contact4CommonMobileLoanOverdueRepay) {
        this.contact4CommonMobileLoanOverdueRepay = contact4CommonMobileLoanOverdueRepay;
    }

    public Boolean getContact4CommonMobileIsFake() {
        return contact4CommonMobileIsFake;
    }

    public void setContact4CommonMobileIsFake(Boolean contact4CommonMobileIsFake) {
        this.contact4CommonMobileIsFake = contact4CommonMobileIsFake;
    }

    public Boolean getContact5IsClose() {
        return contact5IsClose;
    }

    public void setContact5IsClose(Boolean contact5IsClose) {
        this.contact5IsClose = contact5IsClose;
    }

    public Boolean getContact5ClosedIdCardCourtDishonesty() {
        return contact5ClosedIdCardCourtDishonesty;
    }

    public void setContact5ClosedIdCardCourtDishonesty(Boolean contact5ClosedIdCardCourtDishonesty) {
        this.contact5ClosedIdCardCourtDishonesty = contact5ClosedIdCardCourtDishonesty;
    }

    public Boolean getContact5ClosedIdCardCourtExecuted() {
        return contact5ClosedIdCardCourtExecuted;
    }

    public void setContact5ClosedIdCardCourtExecuted(Boolean contact5ClosedIdCardCourtExecuted) {
        this.contact5ClosedIdCardCourtExecuted = contact5ClosedIdCardCourtExecuted;
    }

    public Boolean getContact5ClosedIdCardCourtClosed() {
        return contact5ClosedIdCardCourtClosed;
    }

    public void setContact5ClosedIdCardCourtClosed(Boolean contact5ClosedIdCardCourtClosed) {
        this.contact5ClosedIdCardCourtClosed = contact5ClosedIdCardCourtClosed;
    }

    public Boolean getContact5ClosedIdCardCrimeWanted() {
        return contact5ClosedIdCardCrimeWanted;
    }

    public void setContact5ClosedIdCardCrimeWanted(Boolean contact5ClosedIdCardCrimeWanted) {
        this.contact5ClosedIdCardCrimeWanted = contact5ClosedIdCardCrimeWanted;
    }

    public Boolean getContact5ClosedIdCardOverdraftCorporate() {
        return contact5ClosedIdCardOverdraftCorporate;
    }

    public void setContact5ClosedIdCardOverdraftCorporate(Boolean contact5ClosedIdCardOverdraftCorporate) {
        this.contact5ClosedIdCardOverdraftCorporate = contact5ClosedIdCardOverdraftCorporate;
    }

    public Boolean getContact5ClosedIdCardLoanOverdue() {
        return contact5ClosedIdCardLoanOverdue;
    }

    public void setContact5ClosedIdCardLoanOverdue(Boolean contact5ClosedIdCardLoanOverdue) {
        this.contact5ClosedIdCardLoanOverdue = contact5ClosedIdCardLoanOverdue;
    }

    public Boolean getContact5ClosedMobileLoanOverdue() {
        return contact5ClosedMobileLoanOverdue;
    }

    public void setContact5ClosedMobileLoanOverdue(Boolean contact5ClosedMobileLoanOverdue) {
        this.contact5ClosedMobileLoanOverdue = contact5ClosedMobileLoanOverdue;
    }

    public Boolean getContact5ClosedMobileLoanBlackMedium() {
        return contact5ClosedMobileLoanBlackMedium;
    }

    public void setContact5ClosedMobileLoanBlackMedium(Boolean contact5ClosedMobileLoanBlackMedium) {
        this.contact5ClosedMobileLoanBlackMedium = contact5ClosedMobileLoanBlackMedium;
    }

    public Boolean getContact5CommonMobileLoanOverdueRepay() {
        return contact5CommonMobileLoanOverdueRepay;
    }

    public void setContact5CommonMobileLoanOverdueRepay(Boolean contact5CommonMobileLoanOverdueRepay) {
        this.contact5CommonMobileLoanOverdueRepay = contact5CommonMobileLoanOverdueRepay;
    }

    public Boolean getContact5CommonMobileIsFake() {
        return contact5CommonMobileIsFake;
    }

    public void setContact5CommonMobileIsFake(Boolean contact5CommonMobileIsFake) {
        this.contact5CommonMobileIsFake = contact5CommonMobileIsFake;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }
}
