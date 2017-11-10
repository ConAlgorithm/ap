[
{
    "name" : "LoginOrRegistered",
    "job" : "LoginOrRegistered",
    "message" : {
        "jobStatus" : "app"
    },
    "expectedJobs" : ["CheckMerchantArea", 
                      "CheckMerchantCompanyApplicationStatistics",
                      "CheckMerchantApplicationStatistics",
                      "CheckMerchantUserApplicationStatistics"]
},{
    "name" : "IdentityInfoSubmitted",
    "job"  : "IdentityInfoSubmitted",
    "expectedJobs" : ["CheckUserMobileReference", 
                      "CheckUserCreditOn3rdParty",
                      "CheckIdCardBasicInfo",
                      "CheckIdCardApplicationStatistics",
                      "CheckUserMobileOn3rdParty",
                      "ExecuteIdentityStageQueries",
                      "CheckCreditReference",
                      "CheckBlacklistWithSupremeCourt"
                      ]
},{
    "name" : "PersonalInfoSubmitted",
    "job" : "PersonalInfoSubmitted",
    "expectedJobs" : ["CheckQQOtherUserReference",
                      "CheckQQNumberInfo"
                      ]
},{
    "name" : "JobInfoSubmitted",
    "job" : "JobInfoSubmitted",
    "expectedJobs" : ["CheckCompanyTelReference",
                      "CheckCompanyNameOtherUserReference",
                      "CheckCompanyAddrOtherUserReference",
                      "CheckCompanyPhoneOn3rdParty",
                      "CheckAppFromSameStoreAndCompany",
                      "CheckAddressApplicantWork"
                      ]
},{
    "name" : "ContactInfoSubmitted",
    "job" : "ContactInfoSubmitted",
    "expectedJobs" : ["CheckFirstContactMobileOn3rdParty",
                      "CheckSecondContactMobileOn3rdParty",
                      "CheckFirstContactReference",
                      "CheckSecondContactReference",
                      "CheckSpouseRelationship",
                      "CheckContactRelationship",
                      "CheckHasSamePhoneNumber",
                      "CheckIsUserInfoInBlacklist",
                      "CheckThirdContactMobileOn3rdParty",
                      "CheckAdditionalContactMobileOn3rdParty",
                      "CheckThirdContactReference",
                      "CheckAdditionalContactReference",
                      "CheckIsSurnameOfFatherAndSonSame",
                      "CheckMobileCityCode"
                      ]
},{
    "name" : "ApplicationAgreed",
    "job" : "ApplicationAgreed",
    "expectedJobs" : ["CheckBankCardOn3rdParty"
                      ]
},{
    "name" : "REG007InfoCrawling",
    "expectedJobs" : ["REG007InfoCrawling"]
},{
    "name" : "PreCheck",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["PreCheck"],
    "responseExpectedJob" : false
},{
    "name" : "PreCheckResponse",
    "job" : "PreCheck",
    "message" : {
        "jobStatus" : "Approved"
    }
},{
    "name" : "CheckIdCardOnPoliceService",
    "expectedJobs" : ["CheckIdCardOnPoliceService"]
},{
    "name" : "JXL",
    "job" : "JXLInfoSubmitted",
    "expectedJobs" : ["JXLInfoCrawling"]
},{
    "name" : "FirstPhotoCheck",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["PhotoCheck"],
    "responseExpectedJob" : false
},{
    "name" : "FirstPhotoCheckResponse",
    "job" : "PhotoCheck",
    "message" : {
        "jobStatus" : "1"
    }
},{
    "name" : "HeadPhotoSubmitted",
    "job" : "HeadPhotoSubmitted",
    "expectedQueue" : "HeadPhotoQueueV2",
    "expectedJobs" : ["CheckHeadPhoto"]
},{
    "name" : "IdPhotoSubmitted",
    "job" : "IdPhotoSubmitted",
    "expectedQueue" : "IDCardPhotoQueueV2",
    "expectedJobs" : ["CheckIDCardPhoto"]
},{
    "name" : "BankCardPhotoSubmitted",
    "job" : "BankCardPhotoSubmitted",
    "expectedQueue" : "BankCardQueueV2",
    "expectedJobs" : ["CheckBankCardV3"]
},{
    "name" : "ReCheck",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["ReCheck"],
    "responseExpectedJob" : false
},{
    "name" : "ReCheckResponse",
    "job" : "ReCheck",
    "message" : {
    	"jobStatus" : "RecheckingRequired",
        "jobDataInt" : 7
    }
},{
    "name" : "HeadPhotoSubmitted",
    "job" : "HeadPhotoSubmitted",
    "expectedQueue" : "HeadPhotoQueueV2",
    "expectedJobs" : ["CheckHeadPhoto"]
},{
    "name" : "IdPhotoSubmitted",
    "job" : "IdPhotoSubmitted",
    "expectedQueue" : "IDCardPhotoQueueV2",
    "expectedJobs" : ["CheckIDCardPhoto"]
},{
    "name" : "BankCardPhotoSubmitted",
    "job" : "BankCardPhotoSubmitted",
    "expectedQueue" : "BankCardQueueV2",
    "expectedJobs" : ["CheckBankCardV3"]
},{
    "name" : "ReCheck",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["ReCheck"],
    "responseExpectedJob" : false
},{
    "name" : "ReCheckResponse",
    "job" : "ReCheck",
    "message" : {
        "jobStatus" : "Approved"
    }
},{
    "name" : "CheckFraud",
    "expectedQueue" : "FraudDecisionJobRequestQueue",
    "expectedJobs" : ["CheckFraud"]
},{
    "name" : "CalculateMerchantViewStatistics",
    "expectedQueue" : "JobRequestQueue",
    "expectedJobs" : ["CalculateMerchantViewStatistics"]
},{
    "name" : "Segmentation",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["Segmentation"],
    "responseExpectedJob" : false
},{
    "name" : "SegmentationResponse",
    "job" : "Segmentation",
    "message" : {
        "jobStatus" : "default"
    }
},{
    "name" : "UbtDBWriting",
    "expectedQueue" : "UbtDBWritingQueue",
    "expectedJobs" : ["UbtDBWriting"]
},{
    "name" : "CheckFirstContact",
    "expectedQueue" : "ContactPhoneQueueV2",
    "expectedJobs" : ["CheckFirstContactForApp"]
},{
    "name" : "CheckSecondContact",
    "expectedQueue" : "ContactPhoneQueueV2",
    "expectedJobs" : ["CheckSecondContactForApp"],
    "responseExpectedJob" : false
},{
    "name" : "CheckSecondContactResponse",
    "job" : "CheckSecondContactForApp",
    "message" : {
        "jobStatus" : "RecheckingRequired"
    }
},{
    "name" : "CheckSecondContactDone",
    "expectedQueue" : "ContactPhoneQueueV2",
    "expectedJobs" : ["CheckSecondContactForApp"]
},{
    "name" : "CheckCompany",
    "expectedQueue" : "CompanyPhoneQueueV2",
    "expectedJobs" : ["CheckCompanyForApp"]
},{
    "name" : "CheckPersonalInfo",
    "expectedQueue" : "PersonalInfoQueueV2",
    "expectedJobs" : ["CheckPersonalInfoV3"]
},{
    "name" : "InputIdCardInfo",
    "expectedQueue" : "InfoEnteringQueueV2",
    "expectedJobs" : ["InputIdCardInfo"]
},{
    "name" : "CheckImageComparison",
    "expectedQueue" : "ImageComparisonQueueV2",
    "expectedJobs" : ["CheckImageComparison"]
},{
    "name" : "CheckUser",
    "expectedQueue" : "CustomPhoneQueueV2",
    "expectedJobs" : ["CheckUserForApp"]
},{
    "name" : "CheckCourtExecutedExist",
    "expectedQueue" : "JobRequestQueue",
    "expectedJobs" : ["CheckCourtExecutedExist"],
    "responseExpectedJob" : false
},{
    "name" : "CheckCourtExecutedExistResponse",
    "job" : "CheckCourtExecutedExist",
    "message" : {
        "jobStatus" : "ManualCheckRequired"
    }
},{
    "name" : "CheckCourtExecuted",
    "expectedQueue" : "CourtExecutedQueueV2",
    "expectedJobs" : ["CheckCourtExecuted"]
},{
    "name" : "CheckUserGender",
    "expectedQueue" : "JobRequestQueue",
    "expectedJobs" : ["CheckUserGender"]
},{
    "name" : "CheckHomeCreditForFemale",
    "expectedQueue" : "HomeCreditForFemaleQueueV2",
    "expectedJobs" : ["CheckHomeCredit"]
},{
    "name" : "CheckCompetitorSituation",
    "expectedQueue" : "CompetitorSituationQueueV2",
    "expectedJobs" : ["CheckBilFinance",
                      "CheckDafyAndHaodai",
                      "CheckCreditEase",
                      "CheckVCash"]
},{
    "name" : "TransactionMonitor",
    "expectedQueue" : "TransactionMonitorJobRequestQueue",
    "expectedJobs" : ["TransactionMonitorForApp"]
},{
    "name" : "CheckD1FeedbackPass",
    "job" : "CheckD1FeedbackPass",
    "message" : {
        "jobStatus" : "D1Feedbacked"
    }
},{
    "name" : "RecordFinalCheckByMachine",
    "expectedQueue" : "MachineDecisionJobRequestQueue",
    "expectedJobs" : ["RecordWholeProcessByMachine"]
},{
    "name" : "FinalCheck",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["FinalCheck"],
    "responseExpectedJob" : false
},{
    "name" : "FinalCheckResponse",
    "job" : "FinalCheck",
    "message" : {
        "jobStatus" : "Approved"
    }
},{
    "name" : "CheckMerchant",
    "expectedQueue" : "MerchantPhoneQueueV2",
    "expectedJobs" : ["CheckMerchantForApp"]
},{
    "name" : "CheckIfNeedIOUBuckle",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["ApprovalEvidenceRequirementCheck"],
    "responseExpectedJob" : false
},{
    "name" : "CheckIfNeedIOUBuckleResponse",
    "job" : "ApprovalEvidenceRequirementCheck",
    "message" : {
        "jobDataInt" : 48
    }
},{
    "name" : "CheckD1Feedback",
    "job" : ["CheckD1Feedback"],
    "responseExpectedJob" : false
},{
    "name" : "CheckD1FeedbackResponse",
    "job" : "CheckD1Feedback",
    "message" : {
        "jobStatus" : "D1Feedbacked"
    }
},{
    "name" : "DoCheckD1FeedbackJob",
    "expectedQueue" : "CheckD1FeedbackQueueV2",
    "expectedJobs" : ["CheckD1Feedback"]
},{
    "name" : "IOUSubmitted",
    "job" : "IOUSubmitted",
    "expectedQueue" : "IOUQueueV2",
    "expectedJobs" : ["CheckIOUV3"]
},{
    "name" : "BuckleSubmitted",
    "job" : "BuckleSubmitted",
    "expectedQueue" : "BuckleQueueV2",
    "expectedJobs" : ["CheckBuckle"]
},{
    "name" : "LoanCheck",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["LoanCheck"],
    "responseExpectedJob" : false
},{
    "name" : "LoanCheckResponse",
    "job" : "LoanCheck",
    "message" : {
        "jobStatus" : "RecheckingRequired",
        "jobDataInt" : 16
    }
},{
    "name" : "IOUSubmitted",
    "job" : "IOUSubmitted",
    "expectedQueue" : "IOUQueueV2",
    "expectedJobs" : ["CheckIOUV3"]
},{
    "name" : "LoanCheck",
    "expectedQueue" : "TopRulesDecisionJobRequestQueue",
    "expectedJobs" : ["LoanCheck"],
    "responseExpectedJob" : false
},{
    "name" : "LoanCheckResponse",
    "job" : "LoanCheck",
    "message" : {
        "jobStatus" : "Approved"
    }
},{
    "name" : "Payment",
    "expectedQueue" : "AutoPaymentRequestQueue",
    "expectedJobs" : ["AutoPayment"]
},{
    "name" : "PaymentSucceed",
    "expectedQueue" : "CatfishServerQueue",
    "expectedJobs" : ["AppMoneyTransferred","ApplicationCompleted"]
}
]