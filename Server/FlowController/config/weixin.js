[
    {
        "name": "LoginOrRegistered",
        "jobs": [
            "CheckMerchantArea",
            "CheckMerchantCompanyApplicationStatistics",
            "CheckMerchantApplicationStatistics",
            "CheckMerchantUserApplicationStatistics"
        ],
        "prerequisiteJobs": [
            "LoginOrRegistered"
        ]
    },
    {
        "name": "IdentityInfoSubmitted",
        "jobs": [
            "CheckUserMobileReference",
            "CheckUserCreditOn3rdParty",
            "CheckIdCardBasicInfo",
            "CheckIdCardApplicationStatistics",
            "CheckUserMobileOn3rdParty"
        ],
        "optionalJobs": [
            "ExecuteIdentityStageQueries",
            "CheckBlacklistWithSupremeCourt"
        ],
        "prerequisiteJobs": [
            "IdentityInfoSubmitted"
        ]
    },
    {
        "name": "PersonalInfoSubmitted",
        "jobs": [
            "CheckQQOtherUserReference"
        ],
        "optionalJobs": [
            "CheckQQNumberInfo"
        ],
        "prerequisiteJobs": [
            "PersonalInfoSubmitted"
        ]
    },
    {
        "name": "JobInfoSubmitted",
        "jobs": [
            "CheckCompanyTelReference",
            "CheckCompanyNameOtherUserReference",
            "CheckCompanyAddrOtherUserReference"
        ],
        "optionalJobs": [
            "CheckCompanyPhoneOn3rdParty",
            "CheckAppFromSameStoreAndCompany",
            "CheckAddressApplicantWork"
        ],
        "prerequisiteJobs": [
            "JobInfoSubmitted"
        ]
    },
    {
        "name": "ContactInfoSubmitted",
        "jobs": [
            "CheckFirstContactMobileOn3rdParty",
            "CheckSecondContactMobileOn3rdParty",
            "CheckFirstContactReference",
            "CheckSecondContactReference",
            "CheckSpouseRelationship",
            "CheckContactRelationship",
            "CheckHasSamePhoneNumber",
            "CheckIsUserInfoInBlacklist"
        ],
        "optionalJobs": [
            "CheckThirdContactMobileOn3rdParty",
            "CheckAdditionalContactMobileOn3rdParty",
            "CheckThirdContactReference",
            "CheckAdditionalContactReference",
            "CheckIsSurnameOfFatherAndSonSame"
        ],
        "prerequisiteJobs": [
            "ContactInfoSubmitted"
        ]
    },
    {
        "name": "CheckMobileCityCode",
        "jobs": [
            "CheckMobileCityCode"
        ],
        "prerequisiteJobs": [
            "CheckUserMobileOn3rdParty",
            "CheckFirstContactMobileOn3rdParty",
            "CheckSecondContactMobileOn3rdParty"
        ]
    },
    {
        "name": "ApplicationAgreed",
        "jobs": [
            "CheckBankCardOn3rdParty"
        ],
        "prerequisiteJobs": [
            "ApplicationAgreed"
        ]
    },
    {
        "name": "PreCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "jobs": [
            "PreCheck"
        ],
        "prerequisiteJobs": [
            "ApplicationAgreed",
            "CheckUserMobileReference",
            "CheckMerchantCompanyApplicationStatistics",
            "CheckMerchantApplicationStatistics",
            "CheckMerchantUserApplicationStatistics",
            "CheckIdCardBasicInfo",
            "CheckIdCardApplicationStatistics",
            "CheckIsUserInfoInBlacklist",
            "CheckQQOtherUserReference",
            "CheckCompanyTelReference",
            "CheckCompanyNameOtherUserReference",
            "CheckCompanyAddrOtherUserReference",
            "CheckFirstContactReference",
            "CheckSecondContactReference",
            "CheckSpouseRelationship",
            "CheckContactRelationship",
            "CheckHasSamePhoneNumber",
            "CheckMobileCityCode",
            "CheckUserCreditOn3rdParty",
            "CheckBankCardOn3rdParty"
        ]
    },
    {
        "name": "CheckIdCardOnPoliceService",
        "jobs": [
            "CheckIdCardOnPoliceService"
        ],
        "prerequisiteJobs": [
            "PreCheck"
        ]
    },
    {
        "name": "JXLInfoCrawling",
        "jobs": [
            "JXLInfoCrawling"
        ],
        "prerequisiteJobs": [
            "JXLInfoSubmitted"
        ]
    },
    {
        "name": "CheckNameIDCardMatch",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "optionalJobs": [
            "CheckNameIDCardMatch"
        ],
        "prerequisiteJobs": [
            "CheckIdCardOnPoliceService"
        ]
    },
    {
        "name": "NameIDCardMatchCheckApproved",
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "ApplicationSubmitted",
        "queueName": "CatfishServerQueue",
        "optionalJobs": ["ApplicationSubmitted"],
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "TransactionMonitor",
        "queueName": "TransactionMonitorJobRequestQueue",
        "optionalJobs": ["TransactionMonitor"],
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "CheckPersonalInfo",
        "queueName": "PersonalInfoQueueV2",
        "jobs": ["CheckPersonalInfo"],
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "CheckCompany",
        "queueName": "CompanyPhoneQueueV2",
        "jobs": ["CheckCompany"],
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "CheckFirstContact",
        "type": "loop",
        "queueName": "ContactPhoneQueueV2",
        "job": "CheckFirstContact",
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "CheckSecondContact",
        "type": "loop",
        "queueName": "ContactPhoneQueueV2",
        "job": "CheckSecondContact",
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "CheckAdditionalContact",
        "type": "checkAdditionalContact",
        "queueName": "ContactPhoneQueueV2",
        "delay": 180,
        "retry": 3,
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "ClassifyPhoto",
        "type": "classify",
        "ruleQueueName": "TopRulesDecisionJobRequestQueue",
        "classifyQueueName": "PhotoClassifyQueueV2",
        "setImageUploadFirstStageReadyQueueName": "JobRequestQueue",
        "job": "PhotoCheck",
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    },
    {
        "name": "CheckCourtExecuted",
        "jobs": ["CheckCourtExecuted"],
        "queueName": "CourtExecutedQueueV2"
    },
    {
        "name": "CheckCourtExecutedExist",
        "type": "conditional",
        "queueName": "JobRequestQueue",
        "job": "CheckCourtExecutedExist",
        "choices" : {
			"ManualCheckRequired" : "CheckCourtExecuted"
		},
        "prerequisiteActivities": [
            "ClassifyPhoto"
        ]
    },
    {
        "name": "CheckHomeCreditForMale",
        "jobs": ["CheckHomeCreditForMale"],
        "queueName": "HomeCreditForMaleQueueV2"
        
    },
    {
        "name": "CheckHomeCreditForFemale",
        "jobs": ["CheckHomeCreditForFemale"],
        "queueName": "HomeCreditForFemaleQueueV2"     
    },
    {
        "name": "CheckUserGender",
        "type": "conditional",
        "queueName": "JobRequestQueue",
        "job": "CheckUserGender",
        "choices" : {
			"M" : "CheckHomeCreditForMale",
			"F" : "CheckHomeCreditForFemale",
			"default": "CheckHomeCreditForFemale"
		},
        "prerequisiteActivities": [
            "ClassifyPhoto"
        ]
    },
    {
        "name": "CheckImage",
        "jobs": ["CheckImage"],
        "queueName": "ImageQueueV2",
        "prerequisiteActivities": [
            "ClassifyPhoto"
        ]
    },
    {
        "name": "CheckBankCard",
        "jobs": ["CheckBankCard"],
        "queueName": "BankCardQueueV2",
        "prerequisiteActivities": [
            "ClassifyPhoto"
        ]
    },
    {
        "name": "FirstCheck",
        "optionalJobs": ["FirstCheck"],
        "queueName": "TopRulesDecisionJobRequestQueue",
        "prerequisiteActivities": [
            "CheckPersonalInfo",
            "CheckCompany",
            "CheckFirstContact",
            "CheckSecondContact",
            "CheckAdditionalContact",
            "ClassifyPhoto",
            "CheckCourtExecutedExist",
            "CheckUserGender"
        ]
    },
    {
        "name": "FirstCheckDoneCondition",
        "type": "conditional",
        "choices" : {
			"RecheckingRequired" : "ReCheck",
			"Approved" : "UbtDBWriting"
		},
		"prerequisiteJobs": [
            "ManualFirstCheckDone"
        ]
    },
    {
        "name": "ReCheck",
        "type": "weixinReCheck",
        "job": "ReCheck",
        "ruleQueueName": "TopRulesDecisionJobRequestQueue",
        "reCheckImageQueueName": "ImageQueueV2",
        "reCheckBankCardQueueName": "BankCardQueueV2"
    },
    {
        "name": "UbtDBWriting",
        "type": "ubtDBWriting",
        "queueName": "UbtDBWritingQueue",
        "optionalJobs": [
            "UbtDBWriting"
        ],
        "prerequisiteJobs": [
            "ManualRecheckDone"
        ]
    }
]