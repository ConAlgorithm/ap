/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.in.pdl;

import java.util.HashSet;
import java.util.Set;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.MerchantUserActionType;
import catfish.base.business.common.fraud.WXHeadPhotoCheckResult;
import catfish.base.business.util.CommonUtil;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "欺诈信息")
public class FraudCheckInForm extends BaseForm {
    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.CompanyTelDoesOtherReferenceExceptCompanyExist)
    @ModelField(name = "单位电话被其他人用于除了单位电话的任何联系电话中")
    private boolean companyPhoneRefCheck = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.UserMobileDoesOtherUserOrParentReferenceExist)
    @ModelField(name = "申请人手机被其他申请人作为手机或者父母手机")
    private boolean userMobileRefCheck = false;

    @ModelField(name = "所有手机前7位相同")
    private boolean mobileNumberSimilarityCheck = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.ParentPhoneDoesAnyOtherReferenceExist)
    @ModelField(name = "父母电话被其他申请人作为任何电话")
    private boolean parentPhoneRefCheck = false;

    @ModelField(name = "父母电话不在户籍所在地和本人手机、单位电话所在地")
    private boolean parentPhoneAreaCheck = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsSpouseRelationshipInconsistent)
    @ModelField(name = "配偶作为申请人，关系一致，手机或者姓名不一致")
    private boolean spouseRelationshipCheck = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsContactRelationshipInconsistent)
    @ModelField(name = "申请人的联系人关系和联系人作为申请人写的关系互斥，或者和联系人申请时填的手机或姓名不一致")
    private boolean contactRelationshipCheck = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.NumberOfSpecifiedAppFromSameStoreAndCompany)
    @ModelField(name = "过去3天内同一门店同一单位电话，购买相同一款手机，且分期额相同的人数>=3")
    private Integer sameCompanyAppCheck = 0;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.QQOtherUserReference)
    @ModelField(name = "申请人QQ被其他申请人引用")
    private Integer qqRefCheck = 0;

    @ModelField(name = "4天内同一门店，相同单位，相同借款金额，上传文件时间<90s的人数>=2")
    private boolean sameStoreCompanyPrincipalUploadFileFastCheck;

    @ModelField(name = "7天内同一商圈，相同借款金额，相同期数，申请时间<700s,上传文件时间<90s的人数>=2")
    private boolean sameCommercialPrincipalRepaymentsApplicantFastCheck;

    //	@ModelField(name = "微信无头像")
    //	private boolean weixinHeadPhoto;
    //	
    @ModelField(name = "微信头像(默认为不确定)", bindDomain = "engine.rule.domain.WXHeadPhotoCheckResult")
    private int weixinHeadPhoto = WXHeadPhotoCheckResult.NotSure.getValue();

    public int getWeixinHeadPhoto() {
        return weixinHeadPhoto;
    }

    public void setWeixinHeadPhoto(int weixinHeadPhoto) {
        this.weixinHeadPhoto = weixinHeadPhoto;
    }

    //前端报警 --- 服务接口，当前用户是否被前端报过警
    private boolean userWarnedByMerchantUser;

    //销售报警类型
    @ModelField(name = "销售报警类型", bindDomain = "engine.rule.domain.MerchantUserActionType")
    private Integer merchantWarnActionType = MerchantUserActionType.None.getValue();

    // 前端反欺诈标识
    private Set<Integer> manualWeixinFraudCheckResultSet = new HashSet<>();

    // 公司CatfishAdmin审批后台反欺诈标识
    private Set<String> manualCatfishAdminCheckResultSet = new HashSet<>();

    public void addManualWeixinFraudCheckResultSet(Object role) {
        if (role != null) {
            Set<Integer> roleSet = CommonUtil.decomposeBinarySum(Integer.parseInt(role.toString()));
            this.manualWeixinFraudCheckResultSet.addAll(roleSet);
        }
    }

    public void addManualCatfishAdminCheckResultSet(Object role) {
        if (role != null)
            this.manualCatfishAdminCheckResultSet.add(role.toString());
    }

    @ModelMethod(name = "(this)的前端(#1,<角色>)触发了人工反欺诈", paramDomains = "engine.rule.domain.MerchantUserRole")
    public boolean isManualFrontFraudCheckHit(int role) {
        return manualWeixinFraudCheckResultSet.contains(role);
    }

    @ModelMethod(name = "(this)的交易监控触发了人工反欺诈")
    public boolean isManualBackFraudCheckHit() {
        return this.manualCatfishAdminCheckResultSet.size() > 0;
    }

    @ModelMethod(name = "(this)的前端触发了人工反欺诈")
    public boolean isManualFrontFraudCheckHit() {
        return manualWeixinFraudCheckResultSet.size() > 0 || isUserWarnedByMerchantUser();
    }

    @ModelField(name = "A005")
    private boolean A005;

    @ModelField(name = "A006")
    private boolean A006;

    @ModelField(name = "A013")
    private boolean A013;

    @ModelField(name = "A012")
    private boolean A012;

    @ModelField(name = "E001")
    private boolean E001;

    @ModelField(name = "E002")
    private boolean E002;

    @ModelField(name = "E003")
    private boolean E003;

    @ModelField(name = "E004")
    private boolean E004;

    @ModelField(name = "A014")
    private boolean A014;

    @ModelField(name = "A015")
    private boolean A015;

    @ModelField(name = "A016")
    private boolean A016;

    @ModelField(name = "B004")
    private boolean B004;

    @ModelField(name = "F1是否为运营预警(默认false)")
    private boolean f1OperationWarning = false;
    @ModelField(name = "F1是否为逾期预警(默认false)")
    private boolean f1PDWarning = false; 

    public boolean isF1OperationWarning() {
        return f1OperationWarning;
    }

    public void setF1OperationWarning(boolean f1OperationWarning) {
        this.f1OperationWarning = f1OperationWarning;
    }

    public boolean isF1PDWarning() {
        return f1PDWarning;
    }

    public void setF1PDWarning(boolean f1pdWarning) {
        f1PDWarning = f1pdWarning;
    }

    public boolean isCompanyPhoneRefCheck() {
        return companyPhoneRefCheck;
    }

    public void setCompanyPhoneRefCheck(boolean companyPhoneRefCheck) {
        this.companyPhoneRefCheck = companyPhoneRefCheck;
    }

    public boolean isUserMobileRefCheck() {
        return userMobileRefCheck;
    }

    public void setUserMobileRefCheck(boolean userMobileRefCheck) {
        this.userMobileRefCheck = userMobileRefCheck;
    }

    public boolean isMobileNumberSimilarityCheck() {
        return mobileNumberSimilarityCheck;
    }

    public void setMobileNumberSimilarityCheck(boolean mobileNumberSimilarityCheck) {
        this.mobileNumberSimilarityCheck = mobileNumberSimilarityCheck;
    }

    public boolean isParentPhoneRefCheck() {
        return parentPhoneRefCheck;
    }

    public void setParentPhoneRefCheck(boolean parentPhoneRefCheck) {
        this.parentPhoneRefCheck = parentPhoneRefCheck;
    }

    public boolean isParentPhoneAreaCheck() {
        return parentPhoneAreaCheck;
    }

    public void setParentPhoneAreaCheck(boolean parentPhoneAreaCheck) {
        this.parentPhoneAreaCheck = parentPhoneAreaCheck;
    }

    public boolean isSpouseRelationshipCheck() {
        return spouseRelationshipCheck;
    }

    public void setSpouseRelationshipCheck(boolean spouseRelationshipCheck) {
        this.spouseRelationshipCheck = spouseRelationshipCheck;
    }

    public boolean isContactRelationshipCheck() {
        return contactRelationshipCheck;
    }

    public void setContactRelationshipCheck(boolean contactRelationshipCheck) {
        this.contactRelationshipCheck = contactRelationshipCheck;
    }

    public Integer getSameCompanyAppCheck() {
        return sameCompanyAppCheck;
    }

    public void setSameCompanyAppCheck(Integer sameCompanyAppCheck) {
        this.sameCompanyAppCheck = sameCompanyAppCheck;
    }

    public Integer getQqRefCheck() {
        return qqRefCheck;
    }

    public void setQqRefCheck(Integer qqRefCheck) {
        this.qqRefCheck = qqRefCheck;
    }

    public boolean isSameStoreCompanyPrincipalUploadFileFastCheck() {
        return sameStoreCompanyPrincipalUploadFileFastCheck;
    }

    public void setSameStoreCompanyPrincipalUploadFileFastCheck(boolean sameStoreCompanyPrincipalUploadFileFastCheck) {
        this.sameStoreCompanyPrincipalUploadFileFastCheck = sameStoreCompanyPrincipalUploadFileFastCheck;
    }

    public boolean isSameCommercialPrincipalRepaymentsApplicantFastCheck() {
        return sameCommercialPrincipalRepaymentsApplicantFastCheck;
    }

    public void setSameCommercialPrincipalRepaymentsApplicantFastCheck(boolean sameCommercialPrincipalRepaymentsApplicantFastCheck) {
        this.sameCommercialPrincipalRepaymentsApplicantFastCheck = sameCommercialPrincipalRepaymentsApplicantFastCheck;
    }

    public boolean isA005() {
        return A005;
    }

    public void setA005(boolean a005) {
        A005 = a005;
    }

    public boolean isA006() {
        return A006;
    }

    public void setA006(boolean a006) {
        A006 = a006;
    }

    public boolean isA013() {
        return A013;
    }

    public void setA013(boolean a013) {
        A013 = a013;
    }

    public boolean isA012() {
        return A012;
    }

    public void setA012(boolean a012) {
        A012 = a012;
    }

    public boolean isE001() {
        return E001;
    }

    public void setE001(boolean e001) {
        E001 = e001;
    }

    public boolean isE002() {
        return E002;
    }

    public void setE002(boolean e002) {
        E002 = e002;
    }

    public boolean isE003() {
        return E003;
    }

    public void setE003(boolean e003) {
        E003 = e003;
    }

    public boolean isE004() {
        return E004;
    }

    public void setE004(boolean e004) {
        E004 = e004;
    }

    public boolean isA014() {
        return A014;
    }

    public void setA014(boolean a014) {
        A014 = a014;
    }

    public boolean isA015() {
        return A015;
    }

    public void setA015(boolean a015) {
        A015 = a015;
    }

    public boolean isA016() {
        return A016;
    }

    public void setA016(boolean a016) {
        A016 = a016;
    }

    public boolean isB004() {
        return B004;
    }

    public void setB004(boolean b004) {
        B004 = b004;
    }

    public Set<Integer> getManualWeixinFraudCheckResultSet() {
        return manualWeixinFraudCheckResultSet;
    }

    public void setManualWeixinFraudCheckResultSet(Set<Integer> manualWeixinFraudCheckResultSet) {
        this.manualWeixinFraudCheckResultSet = manualWeixinFraudCheckResultSet;
    }

    public Set<String> getManualCatfishAdminCheckResultSet() {
        return manualCatfishAdminCheckResultSet;
    }

    public void setManualCatfishAdminCheckResultSet(Set<String> manualCatfishAdminCheckResultSet) {
        this.manualCatfishAdminCheckResultSet = manualCatfishAdminCheckResultSet;
    }

    public boolean isUserWarnedByMerchantUser() {
        return userWarnedByMerchantUser;
    }

    public void setUserWarnedByMerchantUser(boolean userWarnedByMerchantUser) {
        this.userWarnedByMerchantUser = userWarnedByMerchantUser;
    }

    public Integer getMerchantWarnActionType() {
        return merchantWarnActionType;
    }

    public void setMerchantWarnActionType(Integer merchantWarnActionType) {
        this.merchantWarnActionType = merchantWarnActionType;
    }
}
