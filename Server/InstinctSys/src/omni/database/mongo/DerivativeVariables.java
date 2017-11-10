package omni.database.mongo;

/**
 * This class contains all possible derivatives stored in Mongo database.<p>
 * Note that, in version 1.0.0, Mongo queries is the bottleneck for CPU utilization. 
 * And usually, it takes up to 40 seconds to retrieve 200 documents of data from remote Mongo database. 
 * 
 * @author guoqing
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class DerivativeVariables {

    public DerivativeVariables(String appId) {
        this._id = appId;
    }

    public DerivativeVariables() {
        // TODO Auto-generated constructor stub
    }

    public String _id;

    public String X_MerchantProvinceCode;

    public String X_MerchantProvince;

    public String X_MerchantCityCode;

    public String X_MerchantCity;

    public String X_MerchantDistrictCode;

    public String X_MerchantDistrict;

    public Integer X_MerchantCompanyApplicationHistoryApproved;

    public Integer X_MerchantCompanyApplicationHistoryRejected;

    public Integer X_MerchantCompanyApplicationHistoryTotal;

    public Integer X_MerchantCompanyApplicationHistoryDelayed;

    public Integer X_MerchantCompanyApplicationTodayApproved;

    public Integer X_MerchantCompanyApplicationTodayRejected;

    public Integer X_MerchantCompanyApplicationTodayTotal;

    public Integer X_MerchantApplicationHistoryTotal;

    /** POS批核数 */
    public Integer X_MerchantApplicationHistoryApproved;

    /** POS拒绝数  */
    public Integer X_MerchantApplicationHistoryRejected;

    /** POS逾期数  */
    public Integer X_MerchantApplicationHistoryDelayed;

    public Integer X_MerchantApplicationTodayTotal;

    public Integer X_MerchantApplicationTodayApproved;

    public Integer X_MerchantApplicationTodayRejected;

    //public String X_MerchantCooperationStartTime;

    public Integer X_MerchantUserApplicationHistoryTotal;

    /** S1批核数 */
    public Integer X_MerchantUserApplicationHistoryApproved;

    /** S1拒绝数 */
    public Integer X_MerchantUserApplicationHistoryRejected;

    /** S1逾期数 */
    public Integer X_MerchantUserApplicationHistoryDelayed;

    public Integer X_MerchantUserApplicationTodayTotal;

    public Integer X_MerchantUserApplicationTodayApproved;

    public Integer X_MerchantUserApplicationTodayRejected;

    public Integer X_UserMobileOtherUserReference;

    public Integer X_UserMobileOtherCompanyTelReference;

    public Integer X_UserMobileOtherFirstContactReference;

    public Integer X_UserMobileOtherSecondContactReference;

    public Integer X_UserMobileOtherThirdContactReference;

    public Integer X_UserMobileOtherAdditionalContactRefernce;

    public Boolean X_UserMobileDoesOtherUserOrParentReferenceExist;

    public Boolean X_IdCardIsChecksumValid;

    public String X_IdCardGender;

    public Integer X_IdCardAge;

    public String X_IdCardProvince;

    public String X_IdCardCity;

    public String X_IdCardDistrict;

    //public String X_ApplicationStartedOn;

    public String X_UserMobileProvince;

    public String X_UserMobileCity;

    public String X_UserMobileServiceProvider;

    public Boolean X_IsUserMobileLocal;

    public Integer X_IdCardNumberRepayingCount;

    public Integer X_IdCardNumberDelayedCount;

    public Integer X_IdCardNumberRejectedTimes;

    public Integer X_IdCardNumberApprovedTimes;

    public Integer X_CompanyNameOtherUserReference;

    public String X_CompanyPhoneProvince;

    public String X_CompanyPhoneCity;

    public String X_CompanyPhoneServiceProvider;

    public Integer X_CompanyAddrOtherUserReference;

    public Integer X_CompanyTelOtherUserReference;

    public Integer X_CompanyTelOtherCompanyTelReference;

    public Integer X_CompanyTelOtherFirstContactReference;

    public Integer X_CompanyTelOtherSecondContactReference;

    public Integer X_CompanyTelOtherThirdContactReference;

    public Integer X_CompanyTelOtherAdditionalContactRefernce;

    public Boolean X_CompanyTelDoesOtherReferenceExceptCompanyExist;

    public Integer X_NumberOfSpecifiedAppFromSameStoreAndCompany;

    public Integer X_QQOtherUserReference;

    public Integer X_CheckUserAdditionalContactCount;

    public Integer X_AllContactCount;

    public String X_FirstContactMobileProvince;

    public String X_FirstContactMobileCity;

    public String X_FirstContactMobileServiceProvider;

    public Integer X_FirstContactOtherUserReference;

    public Integer X_FirstContactOtherCompanyTelReference;

    public Integer X_FirstContactOtherFirstContactReference;

    public Integer X_FirstContactOtherSecondContactReference;

    public Integer X_FirstContactOtherThirdContactReference;

    public Integer X_FirstContactOtherAdditionalContactRefernce;

    public Boolean X_ParentPhoneDoesAnyOtherReferenceExist;

    public Integer X_SecondContactOtherUserReference;

    public Integer X_SecondContactOtherCompanyTelReference;

    public Integer X_SecondContactOtherFirstContactReference;

    public Integer X_SecondContactOtherSecondContactReference;

    public Integer X_SecondContactOtherThirdContactReference;

    public Integer X_SecondContactOtherAdditionalContactRefernce;

    public String X_SecondContactMobileProvince;

    public String X_SecondContactMobileCity;

    public String X_SecondContactMobileServiceProvider;

    public Boolean X_IsContactRelationshipInconsistent;

    public Boolean X_IsSpouseRelationshipInconsistent;

    public Boolean X_IsUserInfoInBlacklist;

    public Boolean X_HasSamePhoneNumber;

    public Boolean X_IsSurnameOfFatherAndSonSame;

    public String X_UserMobileCityCode;

    public String X_FirstContactMobileCityCode;

    public String X_SecondContactMobileCityCode;

    public String X_ApplicationMaxCredit;

    public String X_IdCardIdentificationResult;

    public String X_IdCardIdentificationPhotoPath;

    public Integer X_GroupInfoAppTotalCount;

    public Integer X_GroupInfoAppApprovedCount;

    public Integer X_GroupInfoAppRejectedCount;

    public Integer X_GroupInfoAppCancelledCount;

    public Integer X_GroupInfoAppFPD1Count;

    public Integer X_GroupInfoAppFPD1TotalCount;

    public Integer X_GroupInfoAppFPD30Count;

    public Integer X_GroupInfoAppFPD30TotalCount;
    /** 用户总申请次数 */
    public Integer X_GroupInfoUserTotalCount;
    /** 用户总申请批准次数 */
    public Integer X_GroupInfoUserApprovedCount;
    /** 用户总申请拒绝次数 */
    public Integer X_GroupInfoUserRejectedCount;

    public Integer X_GroupInfoUserCancelledCount;

    public Integer X_GroupInfoUserFPD1Count;

    public Integer X_GroupInfoUserFPD1TotalCount;
    /** 批核件fpd30已表现人数 */
    public Integer X_GroupInfoUserFPD30Count;

    public Integer X_GroupInfoUserFPD30TotalCount;

    public Integer X_CheckWhetherImageIsBankCard;

    public Integer X_CheckWhetherBankCardPhotoIsRecognizable;

    public Integer X_CheckWhetherBankCardInfoIsRecognizable;

    public Integer X_CheckImageHeadPhotoPersonalPic;

    public Integer X_CheckImageHeadPhotoHeadDirection;

    public Integer X_CheckImageHeadPhotoFaceExpression;

    public Integer X_CheckWhetherImageIsIdCardPhoto;

    public Integer X_CheckImageIdCardPersonalPic;

    public Integer X_CheckImageIdCardInfo;

    public Integer X_IsUserSelf;

    public Integer X_IsUserMobile;

    public Integer X_AccompanyNum;

    public Integer X_D1CheckLocaleCustomerBankName;

    public String X_D1CheckCustomerCoatColor;

    public Integer X_D1CheckCustomerSex;

    public String X_MerchantViewStatisticsPrincipals;

    public String X_MerchantViewStatisticsProducts;

    public String X_MerchantViewStatisticsRepayMonths;

    public String X_SegmentationCode;

    public Integer X_CheckCreditEaseRecord;

    public Integer X_CheckDafyRecord;

    public Integer X_CheckHaodaiRecord;

    public Integer X_CheckVCashRecord;

    public Integer X_CheckBilFinanceRecord;

    public Integer X_CheckImagePhotoFromPoliceExist;

    public Integer X_CheckImageComparision;
    /** 外包个人信息审核是否信贷类公司 */
    public Integer X_CheckPersonalInfoCompanyTypeResult;

    public Integer X_CheckHomeCreditRecord;

    public Integer X_CheckCompanyPhoneCallResult;

    public Integer X_CheckCompanyIdentificationResult;

    public Integer X_CheckCompanyApplicantCheckResult;

    public Integer X_CheckCompanyAddressConsistency;

    public Integer X_CheckCompanyAnswerPersonRiskPromptResult;

    public Integer X_CheckCompanyAnswerPersonAnswerResult;

    public Integer X_CheckSecondContactPhoneCallResult;

    public Integer X_InputIdCardInfoIsOtherCertProvided;
    /** 外包收集身份证地址 */
    public String X_InputIdCardInfoAddress;

    public String X_InputIdCardInfoNationality;

    public Integer X_CheckUserPhoneCallResult;

    public Integer X_CheckUserPhoneAnswererResult;

    public Integer X_CheckUserSceneIntroduceResult;

    public Integer X_CheckUserIsCancelApplication;

    public Integer X_CheckUserIdentification;

    public Integer X_CheckUserCompanyName;

    public Integer X_CheckUserInstalmentNumberAndValue;

    public Integer X_CheckUserConsequenceIntroduceResult;

    public Integer X_CheckUserRiskReveal;

    public Integer X_CheckUserSound;

    public Integer X_CheckUserCarenessLevel;

    public Integer X_CheckUserDislikeLevel;

    /** 客户电核首付金额 */
    public String X_CheckUserDownPayment;
    /** 客户电核购机价格 */
    public String X_CheckUserProductPrice;

    public Integer X_CheckFirstContactPhoneCallResult;

    public Integer X_CheckFirstContactPhoneAnswererResult;

    public Integer X_CheckFirstContactSceneIntroduceResult;

    public Integer X_CheckFirstContactIdentificationResult;

    public Integer X_CheckFirstContactSoundJudgeResult;

    public Integer X_CheckFirstContactHereJudgeResult;

    public Integer X_CheckFirstContactCareApplicantResult;

    public Integer X_CheckFirstContactAttitudeOfPhoneResult;

    public Integer X_CheckFirstContactRiskPromptResult;

    public Integer X_CheckFirstContactZodiacResult;

    public Integer X_TransactionMonitorJobCount;

    public String X_StrategyCode;

    public Integer X_CheckMerchantPhoneCallResult;

    public Integer X_CheckMerchantAnswererResult;

    public Integer X_CheckMerchantStoreNameConsistent;

    public Integer X_CheckMerchantCustomerNameConsistent;

    public Integer X_CheckMerchantCustomerOnSpot;

    public Integer X_CheckMerchantPurchaseConfirmed;

    public Integer X_CheckMerchantRiskHint;

    public String X_CheckMerchantDownPayment;

    public String X_CheckMerchantPrice;

    public Integer X_CheckIOUIsIOU;

    public Integer X_CheckIOUIsHeadPhotoClarity;

    public Integer X_CheckIOUIsTextClarify;

    public Integer X_CheckIOUImageComparision;

    public Integer X_CheckIOUMobileColor;

    public Integer X_CheckIOURingFinger;

    public Integer X_CheckIOUIsSmile;

    public Integer X_CheckBuckleIsBuckleAgreement;

    public Integer X_CheckBuckleTextClarifyResult;

    public Integer X_CheckBuckleSignatureClarifyResult;

    public Integer X_CheckBuckleDoesSignatureAndIdNameMatch;

    public Integer X_WelcomeCallPhoneStatus;

    public Integer X_WelcomeCallPhoneAnswererResult;

    public Integer X_WelcomeCallIdentityConfirmResult;

    public Integer X_WelcomeCallMDXServiceConfirmResult;

    public Integer X_WelcomeCallSceneFeedback;

    public Integer X_WelcomeCallIdentityInfoResult;

    public Integer X_WelcomeCallGoodsRiskReminder;

    public Integer X_WelcomeCallCustomerAttitudeResult;

    //	Additional contact person check:
    public Integer X_CheckSecondContactIdentificationResult;
    public Integer X_CheckSecondContactPhoneAnswererResult;
    //  public Integer X_CheckSecondContactPhoneCallResult;
    public Integer X_CheckSecondContactRiskPromptResult;
    public Integer X_CheckSecondContactSoundJudgeResult;
    public Integer X_CheckSecondContactZodiacResult;
    public Integer X_CheckSecondContactHereJudgeResult;

    public Integer X_CheckThirdContactIdentificationResult;
    public Integer X_CheckThirdContactPhoneAnswererResult;
    public Integer X_CheckThirdContactPhoneCallResult;
    public Integer X_CheckThirdContactRiskPromptResult;
    public Integer X_CheckThirdContactSoundJudgeResult;
    public Integer X_CheckThirdContactZodiacResult; // N/A	TODO
    public Integer X_CheckThirdContactHereJudgeResult;

    public Integer X_CheckAdditionalContactIdentificationResult; // N/A	TODO
    public Integer X_CheckAdditionalContactPhoneAnswererResult; // N/A	TODO
    public Integer X_CheckAdditionalContactPhoneCallResult; // N/A	TODO
    public Integer X_CheckAdditionalContactRiskPromptResult; // N/A	TODO
    public Integer X_CheckAdditionalContactSoundJudgeResult; // N/A	TODO
    public Integer X_CheckAdditionalContactZodiacResult; // N/A	TODO
    public Integer X_CheckAdditionalContactHereJudgeResult; // N/A	TODO

    public String X_IdCardNumberLastRejectedDate;
    public Boolean X_IsIdInSupremeCourtBlacklist;
    public Integer X_LoanMoneyFailureReason;
    public Integer X_LoanMoneyResult;

    public Integer X_CheckUserIDCardAddress;
    public Integer X_CheckUserJXLInforCrawlStatus;
    public Integer X_CheckUserSymbolicAnimal;
    public Integer X_CheckPersonalInfoResidenceVSCompanyAddressResult;
    public Integer X_TransactionMonitorRejectReason;
    public Integer X_TransactionMonitorResult;

    // User2
    public String X_YLZH_BankCardMobileMatch;
    public Integer X_YLZH_IsMaxConsumptionAmountNative;
    public Integer X_YLZH_IsMaxConsumptionCountNative;
    public String X_YLZH_ConsumptionTotalAmount;
    public Integer X_YLZH_ConsumptionTotalCount;
    public Integer X_YLZH_ConsumptionTotalRegionCount;
    public Integer X_JXL_GetReportStatusResult;
    public Boolean X_JXL_ReportData_Success;

    public Boolean X_TD_Rule_33638;
    public Boolean X_TD_Rule_33640;
    public Boolean X_TD_Rule_33642;
    public Boolean X_TD_Rule_33652;
    public Boolean X_TD_Rule_33654;

    public Integer X_CheckCompanyPhoneRelationshipResult;
    public Integer X_CheckCourtExecuted;

    // Application
    public String X_UbtTotallySpentSeconds_Summation;
    public String X_UbtPhoneType_Latest;

    // User
    public String X_JXL_ReportData_IsAlwaysPowerOff;
    public Integer X_JXL_ReportData_CallIn_Length;
    public Integer X_JXL_ReportData_CallIn_Count;
    public Integer X_JXL_ReportData_CallOut_Count;
    public Integer X_JXL_ReportData_CallOut_Length;
    public Integer X_JXL_ReportData_Contact_Count;
    public Integer X_JXL_ReportData_ContactNumber_Count;
    public Integer X_JXL_ReportData_regContactPhoneInJXLNum;
    public Integer X_JXL_ReportData_ContactRegion_Count;
    public Integer X_JXL_ReportData_DataExistMonth_Count;
    public Boolean X_JXL_ReportData_CallFinancePhone;
    public Boolean X_JXL_ReportData_CallJieXinPhone;
    public Boolean X_JXL_ReportData_CallLoanPhone;
    public String X_JXL_ReportData_IsNewNumber;
    public String X_JXL_ReportData_IsRealAuthenticated;
    public String X_JXL_ReportData_IsProviderInfoMatch;

    // UCA
    public Integer X_CheckUserCompanyPaydayForPDL;
    public Integer X_CheckWhetherImageIsWorkPermitForPDL;
    public Integer X_CheckThirdContactCompanyNameResultPDL; // N/A	TODO
    public Integer X_CheckWhetherImageIsGroupPhotoForPDL;
    public Integer X_CheckThirdContactMarriageStatusResultForPDL; // N/A	TODO
    public Integer X_CheckSecondContactMarriageStatusResultForPDL;
    public Integer X_CheckSecondContactCompanyNameResultPDL;
    public Integer X_CheckUserCompanyNameForPDL;
    public Integer X_CheckSecondContactIdentificationResultForPDL;
    public Integer X_CheckUserIDCardAddressForPDL;
    public Integer X_CheckFirstContactMarriageStatusResultForPDL; // N/A	TODO
    public Integer X_CheckBuckleDoesSignatureAndIdNameMatchForPDL;
    public Integer X_CheckWhetherWorkPermitOfficialSealConsistentForPDL;
    public Integer X_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL;
    /** 团内批核率[status>=40] */
    public String X_GroupInfoUserApprovedRate;

    public String X_GroupInfoRejectedRate;

    public Boolean X_IsIdCardNumberInBlacklist;

    public Boolean X_IsFirstSevenDigitsOfMobilePhonesSame;

    public Boolean X_TD_SecondContact_Rule33674Hit;

    public Integer X_TD_SecondContact_Rule33674Hit_LoanAmount;

    /** 同盾3个月内第二联系人身份证在本平台外的借款申请次数 */
    public Integer X_TD_SecondContact_Rule33674Hit_PlatformAmount;

    public Boolean X_TD_Rule_33674;

    public Boolean X_TD_Rule_33676;

    public Integer X_CheckFirstContactPermanentResidenceAddressResult;

    public Integer X_CheckSecondContactPhoneCallResultForPDL;

    public Integer X_CheckSecondContactPhoneAnswererResultForPDL;

    public Integer X_CheckSecondContactSceneIntroduceResultForPDL;

    public Integer X_CheckSecondContactRiskPromptResultForPDL;

    public Integer X_CheckSecondContactSoundForPDL;

    public Integer X_CheckSecondContactCareApplicantResultForPDL;

    public Integer X_CheckSecondContactAttitudeOfPhoneResultForPDL;

    public Integer X_CheckSecondContactSceneIntroduceResult;

    public Integer X_CheckSecondContactCareApplicantResult;

    public Integer X_CheckSecondContactAttitudeOfPhoneResult;

    public String X_ThirdContactMobileProvince;

    public String X_ThirdContactMobileCity;

    public String X_ThirdContactMobileServiceProvider;

    public Integer X_ThirdContactOtherUserReference;

    public Integer X_ThirdContactOtherFirstContactReference;

    public Integer X_ThirdContactOtherSecondContactReference;

    public Integer X_ThirdContactOtherThirdContactReference;

    public Integer X_ThirdContactOtherAdditionalContactRefernce;

    public Integer X_CheckThirdContactSomeoneElseHelpResult;

    public Integer X_ThirdContactOtherCompanyRefernce; // TODO:Spelling error

    public Integer X_CheckBuckleIsBuckleAgreementForPDL;

    public Integer X_CheckBuckleTextClarifyResultForPDL;

    public Integer X_CheckBuckleSignatureClarifyResultForPDL;

    public Integer X_CheckImageIdCardValidation;

    public Integer X_CheckImageIdCardAccordance;

    public Integer X_CheckImageIdCardPhotography;

    public Integer X_CheckImageIdCardInfoComparision;

    public Integer X_CheckImageIdCardPhotoComparision;

    public Integer X_CheckImageHeadPhotoValidation;

    public Integer X_CheckImageHeadPhotoAccordance;

    public Integer X_CheckImageHeadPhotoPhotographySite;

    public Integer X_CheckImageHeadPhotoPhotography;

    public Integer X_CheckImageHeadPhotoSite;

    public Integer X_CheckImageHeadPhotoLongCloseShot;

    public Integer X_CheckUserPhoneCallResultForPDL;

    public Integer X_CheckUserPhoneAnswererResultForPDL;

    public Integer X_CheckUserSceneIntroduceResultForPDL;

    public Integer X_CheckUserIdentificationForPDL;

    public Integer X_CheckUserSymbolicAnimalForPDL;

    public Integer X_CheckUserConsequenceIntroduceResultForPDL;

    public Integer X_CheckUserRiskRevealForPDL;

    public Integer X_CheckUserSoundForPDL;

    public Integer X_CheckUserCarenessLevelForPDL;

    public Integer X_CheckUserDislikeLevelForPDL;

    public Integer X_CheckUserCompanyFamilarity;

    public Integer X_CheckUserResidencyFamilarity;

    public Integer X_CheckUserDownPaymentCategory;

    public Integer X_CheckUserCommodityNameAndPrice;

    public Integer X_CheckUserWhatIsMDX;

    public Integer X_CheckUserRepayment;

    public Integer X_CheckUserRepaymentDateAndValue;

    public Integer X_CheckUserRepaymentHow;

    public Integer X_CheckUserRepaymentOnSchedule;

    public Integer X_CheckUserAnswerWithReminder;

    public Integer X_CheckUserCancelApplicationReason;

    public Integer X_CheckWhetherGroupPhotoIsRecognizableForPDL;

    public Integer X_CheckWhetherCustomerPhotoIsTheSamePersonForPDL;

    public Integer X_CheckWhetherSalesmanPhotoIsTheSamePersonForPDL;

    public Integer X_CheckWhetherImageIsBankCardForPDL;

    public Integer X_CheckWhetherBankCardPhotoIsRecognizableForPDL;

    public Integer X_CheckWhetherBankCardInfoIsRecognizableForPDL;

    public Integer X_CheckWhetherImageIsConsistentWithTemplateWorkPermitForPDL;

    public Integer X_CheckWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL;

    public Integer X_CheckWhetherWorkPermitConsistentForPDL;

    public Integer X_CheckCompanySomeElseHelpResult;

    public Integer X_D1CheckCustomerIsAdornGlass;

    public Integer X_CheckPersonalInfoProfessionIdentificationResult;

    public String X_AdditionalContactMobileCity;

    public Integer X_CheckFirstContactIdentificationResultForPDL;

    public Integer X_CheckFirstContactPhoneAnswererResultForPDL;

    public Integer X_CheckFirstContactPhoneCallResultForPDL;

    public Integer X_CheckFirstContactRiskPromptResultForPDL;

    public Integer X_CheckFirstContactSoundForPDL;

    public Integer X_CheckFirstContactZodiacResultForPDL;

    public Integer X_CheckSecondContactZodiacResultForPDL; // N/A TODO

    public Integer X_HistoricalLoanTotalCount;
    public Integer X_HistoricalFullLoanTotalCount;
    public String X_HistoricalLoanTotalAmount;
    public String X_HistoricalLoanAverageAmount;
    public String X_HistoricalLoanMaxAmount;
    public String X_LastLoanAmount;
    public String X_HistoricalLoanTotalLimitAmount;
    public String X_LastLoanLimitAmount;
    public String X_HistoricalLoanAverageLimitAmount;
    public Integer X_LastLoanDays;
    //public String X_LastRepaymentTime;
    //public String X_LastLoanTime;
    public String X_HistoricalLoanMaxLimitAmount;
    public String X_HistoricalLimitAmountUsageRate;
    public Integer X_HistoricalLoanMaxDays;
    public String X_HistoricalLoanAverageDays;
    public String X_CircleLoanAverageDays;
    public String X_ThisLoanAmount;
    public String X_ThisLoanLimitAmount;
    public Integer X_ThisLoanDays;
    //public String X_ThisLoanTime;
    public Integer X_HistoricalContinuousFullLoanMaxCount;
    public Integer X_HistoricalLoanDayAwayFromPayDayMaxDays;
    public Integer X_HistoricalLoanDayAwayFromPayDayMinDays;
    public String X_HistoricalLoanDayAwayFromPayDayAverageDays;
    public Integer X_MonthlyPayDay;
    public Integer X_ThisLoanDayAwayFromPayDayDays;
    public String X_CurrentGPSAwayFromApplicationPostionDistance;
    public String X_HistoricalGPSAwayFromApplicationPostionMaxDistance;
    public String X_HistoricalGPSAwayFromApplicationPostionAverageDistance;
    public Integer X_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount;
    //public String X_HistoricalPerformanceFrozenStartDate;
    public Integer X_HistoricalPerformanceFrozenDays;
    public String X_HistoricalPerformanceFrozenReason;
    public String X_InputIdCardInfoAddressForPDL;

    public Integer X_CheckImageHeadPhotoFaceExpressionForPDL;

    public Integer X_CheckCourtExecutedForPDL;

    public Integer X_TransactionMonitorRejectReasonForPDL;

    public Integer X_TransactionMonitorResultForPDL;

    public Integer X_LoanMoneyFailureReasonPDL;

    public Integer X_LoanMoneyResultForPDL;

    /** 依图比对相似度 */
    public String X_YT_Similarity;
    /** 查询照对证件照（网纹照）的相似度 */
    public String X_YT_SimilarityQueryDatabase;
    /** 查询照对翻拍身份证照的相似度 */
    public String X_YT_SimilarityQueryIdcard;
    /** 翻拍身份证照对证件照（网纹照）的相似度 */
    public String X_YT_SimilarityIdcardDatabase;
    /** 依图比对是否通过 */
    public String X_YT_IsPass;
    /** D1合影审核客户照片判断是否同一人 */
    public Integer X_CheckWhetherCustomerPhotoIsTheSamePerson;
    /** D1合影审核D1照片判断是否同一人 */
    public Integer X_CheckWhetherDPhotoIsTheSamePerson;
    /** 同盾3个月内手机在本平台外的借款申请次数 */
    public Integer X_TD_Rule_33674_OuterPlatformLoanAmount;
    /** 同盾3个月内身份证在本平台外的借款申请次数 */
    public Integer X_TD_Rule_33676_OuterPlatformLoanAmount;
    /** 同盾3个月内第一联系人身份证在本平台外的借款申请次数 */
    public Integer X_TD_FirstContact_Rule33674Hit_PlatformAmount;

    /** 拒绝的黑名单人数 */
    public String X_GroupInfoBlackListCount;
    /** 拒绝的黑名单率 */
    public String X_GroupInfoBlackListCountRate;
    /** 拒绝的拉警报人数 */
    public String X_GroupInfoReportedRejectedCount;
    /** 拒绝的拉警报率 */
    public String X_GroupInfoReportedRejectedCountRate;
    /** 批核件fpd7已表现人数 */
    public String X_GroupInfoUserFPD7Count;
    /** 批核件fpd7% */
    public String X_GroupInfoUserFPD7Rate;
    /** 批核件fpd30% */
    public String X_GroupInfoUserFPD30Rate;
    /** 批核件fs30已表现人数 */
    public String X_GroupInfoUserFS30Count;
    /** 批核件fs30% */
    public String X_GroupInfoUserFS30Rate;
    /** 批核件fst30已表现人数 */
    public String X_GroupInfoUserFST30Count;
    /** 批核件fst30% */
    public String X_GroupInfoUserFST30Rate;
    /** 批核件fstq30已表现人数 */
    public String X_GroupInfoUserFSTQ30Count;
    /** 批核件fstq30% */
    public String X_GroupInfoUserFSTQ30Rate;
    /** 批核件m3+% */
    public String X_GroupInfoUserM3PlusRate;
    /** 团最近3天申请人数 */
    public String X_GroupInfoUserAppRecent3daysCount;
    /** 团最近7天申请人数 */
    public String X_GroupInfoUserAppRecent7daysCount;
    /** 团最近一个月申请人数 */
    public String X_GroupInfoUserAppRecent1monthCount;
    /** 团最近三个月申请人数 */
    public String X_GroupInfoUserAppRecent3monthsCount;
    /** 团内申请地最大集中省份 */
    public String X_GroupInfoUserMainProvince;
    /** 团内申请地最大集中城市 */
    public String X_GroupInfoUserMainCity;
    /** 团欺诈拒绝人数 */
    public String X_GroupInfoFausd;

    /** 百融身份证银行欺诈 */
    public String X_BR_SpecialListIdBankFraud;
    /** 百融身份证银行失联 */
    public String X_BR_SpecialListIdBankLost;
    /** 百融身份证银行拒绝 */
    public String X_BR_SpecialListIdBankRefuse;
    /** 百融身份证P2P欺诈 */
    public String X_BR_SpecialListIdP2PFraud;
    /** 百融身份证P2P失联 */
    public String X_BR_SpecialListIdP2PLost;
    /** 百融身份证P2P拒绝*/
    public String X_BR_SpecialListIdP2PRefuse;
    /** 百融身份证保险骗保 */
    public String X_BR_SpecialListIdInsFraud;
    /** 百融手机银行欺诈 */
    public String X_BR_SpecialListCellBankFraud;
    /** 百融手机银行失联 */
    public String X_BR_SpecialListCellBankLost;
    /** 百融手机银行拒绝 */
    public String X_BR_SpecialListCellBankRefuse;
    /** 百融手机P2P欺诈 */
    public String X_BR_SpecialListCellP2PFraud;
    /** 百融手机P2P失联 */
    public String X_BR_SpecialListCellP2PLost;
    /** 百融手机P2P拒绝 */
    public String X_BR_SpecialListCellP2PRefuse;
    /** 百融手机保险骗保 */
    public String X_BR_SpecialListCellInsFraud;

    /** 前海欺诈风险 */
    public Boolean X_QHZX_HasFraudRisk;
    /** 前海风险得分 */
    public Integer X_QHZX_RiskScore;
    /** 前海手机号欺诈风险 */
    public Boolean X_QHZX_HasMobileFraudRisk;
    /** 前海银行卡号欺诈风险 */
    public Boolean X_QHZX_HasBankCardFraudRisk;
    /** 前海身份证号欺诈风险 */
    public Boolean X_QHZX_HasIdCardFraudRisk;
    
    /** 黑名单等级 A/B/C/D,未命中-9999 */
    public String X_Baidu_blackLevel;
    /** 加黑原因,T01~T09,未命中-9999 */
    public String X_Baidu_blackReason;
    
    
    
    /** 上海资信 -申请机构 */
    public String X_SHZX_ApplyOrganization;
    /** 上海资信 -申请时间 */
    public String X_SHZX_ApplyTime;
    /** 上海资信 -申请金额 */
    public String X_SHZX_ApplyMoney;
    /** 上海资信 -申请月数 */
    public String X_SHZX_ApplyMonth;
    /** 上海资信 -申请状态 */
    public String X_SHZX_ApplyStatus;
    /** 上海资信 -申请类型 */
    public String X_SHZX_ApplyType;
    /** 上海资信 -申请信息获取时间 */
    public String X_SHZX_ApplyInfoDateTime;
    /** 上海资信 -贷款笔数 */
    public String X_SHZX_LoanNumsTotal;
    /** 上海资信 -最大授信额度 */
    public String X_SHZX_MaxCreditLineTotal;
    /** 上海资信 -贷款总额 */
    public String X_SHZX_LoanMoneyTotal;
    /** 上海资信 -贷款余额 */
    public String X_SHZX_RemainMoneyTotal;
    /** 上海资信 -当前逾期总额 */
    public String X_SHZX_RepayOverdueMoneyTotal;
    /** 上海资信 -最高逾期金额 */
    public String X_SHZX_RepayOverdueMaxMoney;
    /** 上海资信 -借款机构查询次数 */
    public String X_SHZX_LoanReportQueryCount;
    /** 上海资信 -姓名*/
    public String X_SHZX_Name;
    /** 上海资信 -证件号码*/
    public String X_SHZX_IdNo;
    /** 上海资信 -配偶姓名*/
    public String X_SHZX_MateName;
    /**上海资信 -配偶证件号码 */
    public String X_SHZX_MateIdNo;
    /**上海资信 -婚姻明细 */
    public String X_SHZX_MaritalStatus;
    /** 上海资信 -婚姻信息获取日期*/
    public String X_SHZX_MaritalInfoDateTime;
    /**上海资信 -学历明细 */
    public String X_SHZX_EducationDegree;
    /**上海资信 -职称明细 */
    public String X_SHZX_TitleDetails;
    /** 上海资信 -住宅电话明细*/
    public String X_SHZX_HomePhone;
    /** 上海资信 -手机号码明细    */
    public String X_SHZX_Mobile;
    /** 上海资信 -电子邮箱明细*/
    public String X_SHZX_Email;
    /** 上海资信 -配偶工作单位明细*/
    public String X_SHZX_MateWorkDetail;
    /** 上海资信 -配偶联系电话明细*/
    public String X_SHZX_MateContactPhone;
    /** 上海资信 -手机号码信息获取日期*/
    public String X_SHZX_MobileInfoDateTime;
    /** 上海资信 -配偶联系电话信息获取日期*/
    public String X_SHZX_MateContactInfoDateTime;
    /** 上海资信 联系人一列表*/
    public String X_SHZX_Contact1InfoBeanList;
    /** 上海资信 工作单位列表*/
    public String X_SHZX_PersonWorkDetailList;
    /** 上海资信 地址列表*/
    public String X_SHZX_AddressInfoList;
    
    /** 是否为二次客户 */
    public Boolean X_Loan_IsSecondTimeUser;
    /** 上一次已完成借款最大逾期天数 */
    public Integer X_Loan_LastAppMaxDelayedDays;
    /** 距离上一次已完成借款最后还款日期时间 */
    public Integer X_Loan_LastApplicationInterval;
    /**上一次借款申请状态 */
    public Integer X_Loan_LastLoanStatus;
    /** 上一次借款提前还款天数*/
    public Integer X_Loan_LastLoanPrepaymentdays;
    
    /**第一联系人是否存在于用户通讯录*/
    public Boolean X_IsContact1Inlist;
    /**第二联系人是否存在于用户通讯录*/
    public Boolean X_IsContact2Inlist;
    /**第一联系人手机号码在用户通讯录中的称呼，不存在的话为-1*/
    public String  X_Contact1nameInList;
    /**第二联系人手机号码在用户通讯录中的称呼，不存在的话为-1*/
    public String X_Contact2nameInList;
    /**第1联系人在通讯录里的称呼中是否包含姓名中的某一个字---模糊匹配*/
    public Boolean X_IsContact1True;
    /**第2联系人在通讯录里的称呼中是否包含姓名中的某一个字---模糊匹配*/
    public Boolean X_IsContact2True;
    /**通讯录中的所有称呼里面包含敏感词库中的敏感词的个数*/
    public int X_ContactNumberOfBlack;
    /**通讯录厚度----计算通讯录电话非重复个数，包括固话和短号*/
    public int X_ContactNumberOfMobile;
    /**通讯录中的所有称呼里面包含“买单侠”的称呼的个数*/
    public int X_ContactNumberOfMDX;
    
    /** 集奥 -在网时长 */
    public String X_JO_LengthOfNetwork;
    /** 集奥 - 在网时长描述 */
    public String X_JO_LengthOfNetworkDesc;
    /** 集奥 - 姓名身份证手机号三元素验证 */
    public String X_JO_IsRealAuthenticated3;
    /** 集奥 - 姓名身份证手机号三元素验证描述 */
    public String X_JO_IsRealAuthenticated3Desc;
    
    /** 百度 - 百度评分是否匹配上百度ID */
    public String X_BaiduScore_type;
    /** 百度 - 百度评分/分值越大风险越高 */
    public String X_BaiduMdx_usmbl;
    
    /** 同盾 - 3个月内手机多平台借款平台数*/
    public Integer X_TD_Rule_33674_LoanAmount;
    /** 同盾 - 3个月内身份证多平台借款平台数*/
    public Integer X_TD_Rule_33676_LoanAmount;
    
    //PSL设备信息
    /** 设备信息id**/
    public String X_DEVICEINFO_ID;
    /** 设备信息name**/
    public String X_DEVICEINFO_NAME;
    /** 设备信息纬度**/
    public Double X_DEVICEINFO_LATITUDE;
    /** 设备信息经度**/
    public Double X_DEVICEINFO_LOGITUDE;
        
}
