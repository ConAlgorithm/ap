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
    },{
        "name": "IdentityInfoSubmitted",
        "jobs": [
            "CheckUserMobileReference",     
            "CheckIdCardBasicInfo",
            "CheckIdCardApplicationStatistics",
            "CheckUserMobileOn3rdParty"
        ],
        "optionalJobs": [
            "CheckUserCreditOn3rdParty",
            "ExecuteIdentityStageQueries",
            "CheckBlacklistWithSupremeCourt"
        ],
        "prerequisiteJobs": [
            "IdentityInfoSubmitted"
        ]
    },{
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
    },{
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
    },{
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
        "name": "CheckContactInJXLCount",
        "jobs": ["CheckContactInJXLCount"],
        "prerequisiteJobs": [
            "ContactInfoSubmitted",
            "JXLInfoCrawlFinished"
        ]
    },{
        "name": "CheckMobileCityCode",
        "jobs": [
            "CheckMobileCityCode"
        ],
        "prerequisiteJobs": [
            "CheckUserMobileOn3rdParty",
            "CheckFirstContactMobileOn3rdParty",
            "CheckSecondContactMobileOn3rdParty"
        ]
    },{
        "name": "CheckBankCardOn3rdParty",
        "jobs": [
            "CheckBankCardOn3rdParty"
        ],
        "prerequisiteJobs": [
            "ApplicationAgreed"
        ]
    },
    {
        "name": "JXLInfoCrawling",
        "optionalJobs": ["JXLInfoCrawling"],
        "prerequisiteJobs": [
            "JXLInfoSubmitted"
        ]
    },{
	    "name": "REG007InfoCrawling",
	    "optionalJobs": ["REG007InfoCrawling"],
        "prerequisiteJobs": [
            "IdentityInfoSubmitted"
        ]
	},{
	    "name": "CheckUserCreditOnTd",
	    "optionalJobs": ["CheckUserCreditOnTd"],
	    "prerequisiteJobs": [
            "IdentityInfoSubmitted"
        ]
	},{
        "name": "PreCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "jobs": ["PreCheck"],   
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
    },{
        "name": "PreCheckDoneCondition",
        "type": "conditional",
        "choices": {
           "Approved": "PreApproveInPreCheck",
           "Rejected": "RejectInPreCheck",
           "Canceled": "CancelInPreCheck"
        },
        "prerequisiteJobs": [
              "PreCheck"
        ]
    },{
          "name": "PreApproveInPreCheck",
          "jobs": ["PreApproveApplication"]
    },{
        "name": "PreCheckApproved",
        "type": "dummy",
        "prerequisiteActivities": [
              "PreApproveInPreCheck"
        ]
    },{
    	"name": "RejectInPreCheck",
    	"jobs": ["RejectApplication"]
    },{
        "name": "NotifyForPreCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "RejectInPreCheck"
        ]
    },{
      "name": "PreCheckRejected",
      "type": "terminate",
      "prerequisiteActivities": [
	         "NotifyForPreCheckRejected"
	  ]
    },{
    	"name": "CancelInPreCheck",
    	"jobs": ["CancelApplication"]
    },{
        "name": "NotifyForPreCheckCanceled",
        "type": "onsNotify",
        "message": "Cancel",
        "prerequisiteActivities": [
             "CancelInPreCheck"
        ]
    },{
      "name": "PreCheckCanceled",
      "type": "terminate",
      "prerequisiteActivities": [
	         "NotifyForPreCheckCanceled"
	  ]
    },{
        "name": "ApplicationSubmitted",
        "optionalJobs": ["ApplicationSubmitted"],
        "queueName": "CatfishServerQueue",
        "prerequisiteActivities": [
            "PreCheckApproved"
      ]
    },{
      "name": "CalculateNetworkRelationship",
      "optionalJobs": ["UpdateGraphByApplication"],
      "queueName": "GraphServiceRequestQueue",
      "prerequisiteJobs": [
            "ApplicationAgreed"
      ]
    },
    {
      "name": "CheckJunyu3Photo",
      "type": "conditional",
      "oneOff": true,
      "job": "CheckJunyu3Photo",
      "queueName": "JobRequestQueue",
      "key": "dataInt",
      "choices":{
          "-1": "NameIdCardNotMatchFromJunyu",
          "7": "NeedCheckAll",
          "6": "NeedCheckHeadIdCardPhoto",        
          "default": "NeedCheckAll"
      },
      "prerequisiteActivities": [
          "PreCheckApproved"
      ],
      "prerequisiteJobs": [
          "HeadPhotoSubmitted",
          "IdPhotoSubmitted"
      ]
    },
    {
       "name": "NameIdCardNotMatchFromJunyu",
       "type": "dummy"
    },
    {
       "name": "NeedCheckAll",
       "type": "dummy"
    },
    {
        "name": "NeedCheckHeadIdCardPhoto",
        "type": "dummy"
    },
    {
        "name": "CheckIdCardOnPoliceService",
        "jobs": ["CheckIdCardOnPoliceService"],
        "prerequisiteActivities": [
             "NeedCheckAll"
        ]
    },
    {
      "name": "CheckHeadPhoto",
      "jobs": ["CheckHeadPhotoForPDL"],
      "oneOff": true,
      "waitOne": true,
      "queueName": "HeadPhotoQueueV2",
      "prerequisiteActivities": [
          "NeedCheckHeadIdCardPhoto",
          "NeedCheckAll"
      ],
      "prerequisiteJobs": [
          "HeadPhotoSubmitted"
      ]
    },
    {
      "name": "CheckIDCardPhoto",
      "jobs": ["CheckIDCardPhotoForPDL"],
      "oneOff": true,
      "waitOne": true,
      "queueName": "IDCardPhotoQueueV2",
      "prerequisiteActivities": [
          "NeedCheckHeadIdCardPhoto",
          "NeedCheckAll"
      ],
      "prerequisiteJobs": [
          "IdPhotoSubmitted"
      ]
    },
    {
       "name": "CheckHeadIDCardPhotoFinished",
       "type": "dummy",
       "prerequisiteActivities": [
          "CheckHeadPhoto",
          "CheckIDCardPhoto",
          "NeedCheckHeadIdCardPhoto"
       ]
    },
    {
      "name": "CheckIDCardInfoAndPhotoFinished",
      "type": "dummy",
      "prerequisiteActivities": [
          "CheckHeadPhoto",
          "CheckIDCardPhoto",
          "CheckIdCardOnPoliceService",
          "NeedCheckAll"
      ]
    },
    {
      "name": "CheckInfoFinished",
      "type": "dummy",
      "waitOne": true,
      "prerequisiteActivities": [
          "CheckIDCardInfoAndPhotoFinished",
          "CheckHeadIDCardPhotoFinished",
          "NameIdCardNotMatchFromJunyu"
      ]
    },
    {
        "name": "CheckBankCard",
        "jobs": ["CheckBankCardForPDL"],
        "oneOff": true,
        "queueName": "BankCardQueueV2",
        "prerequisiteJobs": [
            "BankCardPhotoSubmitted"
        ],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
        "name": "CheckNoticeForm",
        "jobs": ["CheckNoticeForm"],
        "oneOff": true,
        "queueName": "NoticeFormQueueV2",
        "prerequisiteJobs": [
            "NoticeFormSubmitted"
        ],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
       "name": "CheckGroupPhoto",
       "jobs": ["CheckGroupPhotoForPDL"],
       "oneOff": true,
       "queueName": "GroupPhotoQueueV2",
       "prerequisiteJobs": [
            "GroupPhotoSubmitted"
        ],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
       "name": "NoNeedBuckle",
       "type": "dummy",
       "prerequisiteActivities": [
            "PreCheckApproved"
       ],
       "prerequisiteJobs": [
            "NoNeedBuckleSubmitted"
       ]
    },
    {
       "name": "CheckBuckleForPDL",
       "jobs": ["CheckBuckleForPDL"],
       "oneOff": true,
       "queueName": "BuckleQueueV2",
       "prerequisiteActivities": [
            "PreCheckApproved"
       ],
       "prerequisiteJobs": [
            "BuckleSubmitted",
            "NeedBuckleSubmitted"
       ]
    },
    {
      "name": "BuckleEvidenceFinished",
      "type": "dummy",
      "waitOne": true,
      "prerequisiteActivities": [
            "NoNeedBuckle",
            "CheckBuckleForPDL"
      ]
    },
    {
        "name": "CollectWorkEvidence",
        "type": "collectWorkEvidence",
        "statusJob": "WorkEvidenceMetaInfoSubmitted",
        "oneOff": true,
        "eventJobMap": {
            "WorkPermitSubmitted": "CheckWorkPermitOrLicenseForPDL",
            "ChestCardPhotoSubmitted": "CheckBadgeForPDL",
            "UniformPhotoSubmitted": "CheckWorkClothForPDL",
            "HealthCertificatePhotoSubmitted": "CheckHealthCertificateForPDL",
            "TimeCardPhotoSubmitted": "CheckTimeCardForPDL",
            "SocialSecurityCardPhotoSubmitted": "CheckSocialSecurityCardForPDL",
            "WageCeritificatePhotoSubmitted": "CheckSalaryMessageForPDL"
        },
        "jobQueueMap": {
            "CheckWorkPermitOrLicenseForPDL": "WorkPermitQueueV2",
            "CheckBadgeForPDL": "WorkPermitQueueV2",
            "CheckWorkClothForPDL": "WorkPermitQueueV2",
            "CheckHealthCertificateForPDL": "WorkPermitQueueV2",
            "CheckTimeCardForPDL": "WorkPermitQueueV2",
            "CheckSocialSecurityCardForPDL": "WorkPermitQueueV2",
            "CheckSalaryMessageForPDL": "WorkPermitQueueV2"
        },
        "prerequisiteJobs": [
            "WorkEvidenceMetaInfoSubmitted"
        ],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
        "name": "PdlReCheck",
        "type": "pdlReCheck",
        "job": "ReCheck",
        "eventJobMap": {
            "HeadPhotoSubmitted": "CheckHeadPhotoForPDL",
            "IdPhotoSubmitted": "CheckIDCardPhotoForPDL",
            "BankCardPhotoSubmitted": "CheckBankCardForPDL",
            "NoticeFormSubmitted": "CheckNoticeForm",
            "GroupPhotoSubmitted": "CheckGroupPhotoForPDL",
            "BuckleSubmitted": "CheckBuckleForPDL",
            "WorkPermitSubmitted": "CheckWorkPermitOrLicenseForPDL",
            "ChestCardPhotoSubmitted": "CheckBadgeForPDL",
            "UniformPhotoSubmitted": "CheckWorkClothForPDL",
            "HealthCertificatePhotoSubmitted": "CheckHealthCertificateForPDL",
            "TimeCardPhotoSubmitted": "CheckTimeCardForPDL",
            "SocialSecurityCardPhotoSubmitted": "CheckSocialSecurityCardForPDL",
            "WageCeritificatePhotoSubmitted": "CheckSalaryMessageForPDL"
        },
        "jobQueueMap": {
            "ReCheck": "TopRulesDecisionJobRequestQueue",
            "CheckHeadPhotoForPDL": "HeadPhotoQueueV2",
            "CheckIDCardPhotoForPDL": "IDCardPhotoQueueV2",
            "CheckBankCardForPDL": "BankCardQueueV2",
            "CheckNoticeForm": "NoticeFormQueueV2",
            "CheckGroupPhotoForPDL": "GroupPhotoQueueV2",
            "CheckBuckleForPDL": "BuckleQueueV2",
            "CheckWorkPermitOrLicenseForPDL": "WorkPermitQueueV2",
            "CheckBadgeForPDL": "WorkPermitQueueV2",
            "CheckWorkClothForPDL": "WorkPermitQueueV2",
            "CheckHealthCertificateForPDL": "WorkPermitQueueV2",
            "CheckTimeCardForPDL": "WorkPermitQueueV2",
            "CheckSocialSecurityCardForPDL": "WorkPermitQueueV2",
            "CheckSalaryMessageForPDL": "WorkPermitQueueV2"
        },
        "prerequisiteActivities": [
            "CheckBankCard",
            "CheckGroupPhoto",
            "CheckNoticeForm",
            "BuckleEvidenceFinished",
            "CheckInfoFinished",
            "CollectWorkEvidence"
        ]
    },{
        "name": "ReCheckCondition",
        "type": "conditional",
        "choices": {
            "Approved": "ReCheckApproved",
            "Rejected": "RejectInReCheck",
            "Canceled": "CancelInReCheck"
        },
        "prerequisiteActivities": [
             "PdlReCheck"
        ],
        "prerequisiteJobs": [
             "ReCheck"
        ]
     },{
         "name": "ReCheckApproved",
         "type": "dummy"
     },{
    	"name": "RejectInReCheck",
    	"jobs": ["RejectApplication"]
    },{
        "name": "NotifyForReCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "RejectInReCheck"
        ]
    },{
        "name": "ReCheckRejected",
        "type": "terminate",
        "prerequisiteActivities": [
	        "NotifyForReCheckRejected"
	    ]
    },{
    	"name": "CancelInReCheck",
    	"jobs": ["CancelApplication"]
    },{
        "name": "NotifyForReCheckCanceled",
        "type": "onsNotify",
        "message": "Cancel",
        "prerequisiteActivities": [
             "CancelInReCheck"
        ]
    },{
        "name": "ReCheckCanceled",
        "type": "terminate",
        "prerequisiteActivities": [
	        "NotifyForReCheckCanceled"
	    ]
    },{
        "name": "Segmentation",
        "type": "conditional",
        "job": "Segmentation",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "choices": {
            "F3": "SegmentationPlanF3",
            "F4": "SegmentationPlanF4",
            "F6": "SegmentationPlanF6",
            "F9": "SegmentationPlanF9",
            "default": "SegmentationPlanF1"
        },
        "prerequisiteActivities": [
            "ReCheckApproved"
        ]
    },
    {
      "name": "SegmentationPlanF3",
      "type": "dummy"
    },
    {
      "name": "SegmentationPlanF4",
      "type": "dummy"
    },
    {
      "name": "SegmentationPlanF6",
      "type": "dummy"
    },
    {
       "name": "SegmentationPlanF4Delay",
       "jobs": ["Empty"],
       "delay": 60,
       "prerequisiteActivities": [
           "SegmentationPlanF4"
       ]
    },
    {
      "name": "SegmentationPlanF6Block",
      "type": "block",
      "prerequisiteActivities": [
           "SegmentationPlanF6"
      ]
    },
    {
      "name": "SegmentationPlanF9",
      "type": "dummy"
    },
    {
      "name": "SegmentationPlanF9Block",
      "type": "block",
      "prerequisiteActivities": [
           "SegmentationPlanF9"
      ]
    },
    {
        "name": "SegmentationPlanF1",    
        "type": "dummy"
    },{
        "name": "SegmentationPlanF1Block",
        "type": "block",
        "prerequisiteActivities": [
           "SegmentationPlanF1"
        ]
    },{
        "name": "UbtDBWriting",
        "optionalJobs": ["UbtDBWriting"],
        "queueName": "UbtDBWritingQueue",
        "prerequisiteActivities": [
           "SegmentationPlanF1"
        ]
    },{
        "name": "CheckFirstContact",
        "type": "loop",
        "queueName": "ContactPhoneQueueV2",
        "job": "CheckFirstContactForPDL",
        "waitOne": true,
        "linkedActivities": [
            "SegmentationPlanF9Block",
            "SegmentationPlanF1Block"
        ],
        "prerequisiteActivities": [
           "SegmentationPlanF1",
           "SegmentationPlanF9"
        ]
    },{
        "name": "CheckSecondContact",
        "type": "loop",
        "queueName": "ContactPhoneQueueV2",
        "job": "CheckSecondContactForPDL",
        "waitOne": true,
        "linkedActivities": [
            "SegmentationPlanF9Block",
            "SegmentationPlanF1Block"
        ],
        "prerequisiteActivities": [
           "SegmentationPlanF1",
           "SegmentationPlanF9"
        ]
    },{
        "name": "InputIdCardInfo",
        "queueName": "InfoEnteringQueueV2",
        "jobs": ["InputIdCardInfoForPDL"],
        "waitOne": true,
        "linkedActivities": [
            "SegmentationPlanF1Block",
            "SegmentationPlanF6Block",
            "SegmentationPlanF9Block"
        ],
        "prerequisiteActivities": [
           "SegmentationPlanF1",
           "SegmentationPlanF6",
           "SegmentationPlanF9"
        ]
    },
    {
      "name": "CheckJunyu2Photo",
      "type": "conditional",
      "job": "CheckJunyu2Photo",
      "queueName": "JobRequestQueue",
      "choices":{
          "Success": "Junyu2PhotoCheckPassed",
          "ManualCheckRequired": "CheckImageComparison"
      },
      "waitOne": true,
      "linkedActivities": [
           "SegmentationPlanF1Block",
           "SegmentationPlanF6Block",
           "SegmentationPlanF9Block"
      ],
      "prerequisiteActivities": [
          "SegmentationPlanF1",
          "SegmentationPlanF6",
          "SegmentationPlanF9"
      ]
    },
    {
       "name": "Junyu2PhotoCheckPassed",
       "type": "dummy"
    },
    { 
        "name": "CheckImageComparison",
        "queueName": "ImageComparisonQueueV2",
        "jobs": ["CheckImageComparisonForPDL"]
    },
    {
        "name": "CheckUser",
        "queueName": "CustomPhoneQueueV2",
        "jobs": ["CheckUserForPDL"],
        "waitOne": true,
        "linkedActivities": [
            "SegmentationPlanF1Block",
            "SegmentationPlanF9Block"
        ],
        "prerequisiteActivities": [
           "SegmentationPlanF1",
           "SegmentationPlanF9"
        ]
    },
    {
        "name": "CheckCourtExecutedExist",
        "type": "conditional",
        "queueName": "JobRequestQueue",
        "job": "CheckCourtExecutedExist",
        "choices" : {
			"ManualCheckRequired" : "CheckCourtExecuted"
		},
		"linkedActivities": [
           "SegmentationPlanF1Block"
        ],
        "prerequisiteActivities": [
           "SegmentationPlanF1"
        ]
    },{
        "name": "CheckCourtExecuted",
        "jobs": ["CheckCourtExecutedForPDL"],
        "queueName": "CourtExecutedQueueV2"
    },
    { 
        "name": "CheckUserGender",
        "queueName": "JobRequestQueue",
        "job": "CheckUserGender",
        "waitOne": true,
        "type": "conditional",
        "choices" : {
			"M" : "CheckHomeCreditForMale",
			"F" : "CheckHomeCreditForFemale",
			"default": "CheckHomeCreditForFemale"
		},
        "linkedActivities": [
            "SegmentationPlanF1Block",
            "SegmentationPlanF6Block",
            "SegmentationPlanF9Block"
        ],
        "prerequisiteActivities": [
           "SegmentationPlanF1",
           "SegmentationPlanF6",
           "SegmentationPlanF9"
        ]
    },{
        "name": "CheckHomeCreditForMale",
        "jobs": ["CheckHomeCreditForPDL"],
        "queueName": "HomeCreditForMaleQueueV2"      
    },
    {
        "name": "CheckHomeCreditForFemale",
        "jobs": ["CheckHomeCreditForPDL"],
        "queueName": "HomeCreditForFemaleQueueV2"     
    },
    {
       "name": "FinalCheckSplit",
       "type": "split",
       "branches": {
          "RecordFinalCheckByMachine": 1.0,
          "FinalCheckByMachine": 0.0
       },
       "prerequisiteActivities": [
           "SegmentationPlanF1"
       ]
    },
    {
       "name": "RecordFinalCheckByMachine",
       "optionalJobs": ["RecordWholeProcessByMachine"],
       "queueName": "MachineDecisionJobRequestQueue"
    },
    {  "name": "FinalCheckByMachine",
       "jobs": ["CheckWholeProcessByMachine"],
       "queueName": "MachineDecisionJobRequestQueue"
    },
    {
        "name": "CheckTransactionMonitorJobs",
        "jobs": ["CheckTransactionMonitorJobs"],
        "waitOne": true,
        "prerequisiteActivities": [
             "SegmentationPlanF1Block",
             "SegmentationPlanF3",
             "SegmentationPlanF4Delay",
             "SegmentationPlanF6Block",
             "SegmentationPlanF9Block"
        ]
    },
    {
      "name": "FinalCheck",
      "jobs": ["FinalCheck"],
      "queueName": "TopRulesDecisionJobRequestQueue",
      "prerequisiteActivities": [
           "CheckTransactionMonitorJobs"
      ]
    },
    {
       "name": "FinalCheckDoneCondition",
       "type": "conditional",
       "choices" : {
           "ApprovedWithTransactionMonitor": "FinalCheckApprovedWithTM",
           "ApprovedWithoutTransactionMonitor": "FinalCheckApprovedWithoutTM",
           "Rejected": "RejectInFinalCheck",
           "Canceled": "CancelInFinalCheck"
       },
       "prerequisiteJobs": [
           "FinalCheck"
        ]
    },
    {
       "name": "FinalCheckApprovedWithTM",
       "type": "dummy"
    },
    {
      "name": "FinalCheckApprovedWithoutTM",
      "type": "dummy"
    },
    {
       "name": "RejectInFinalCheck",
       "jobs": ["RejectApplication"]
    },
    {
      "name": "NotifyAppForFinalCheckRejected",
      "type": "onsNotify",
      "message": "PDLReject",
      "prerequisiteActivities": [
           "RejectInFinalCheck"
      ]
    },  
    {
       "name": "FinalCheckRejected",
       "type": "terminate",
       "prerequisiteActivities": [
           "NotifyAppForFinalCheckRejected"
       ]
    },
    {
        "name": "CancelInFinalCheck",
        "jobs": ["CancelApplication"]
     },{
         "name": "NotifyForFinalCheckCanceled",
         "type": "onsNotify",
         "message": "Cancel",
         "prerequisiteActivities": [
            "CancelInFinalCheck"
         ]
     },
     {
        "name": "FinalCheckCanceled",
        "type": "terminate",
        "prerequisiteActivities": [
            "NotifyForFinalCheckCanceled"
        ]
    },
    {
        "name": "TransactionMonitor",
        "jobs": ["TransactionMonitorForPDL"],
        "queueName": "TransactionMonitorJobRequestQueue",
        "prerequisiteActivities": [
           "FinalCheckApprovedWithTM"
        ]
    },
    {
       "name": "MerchantWarned",
       "type": "dummy",
       "prerequisiteJobs": [
            "MerchantWarned"
        ],
        "prerequisiteActivities": [
            "FinalCheck"
        ] 
    },
    {
        "name": "MonitorCheck",
        "type": "conditional",
        "job": "MonitorCheck",
        "waitOne": true,
        "queueName": "TopRulesDecisionJobRequestQueue",
        "choices": {
            "Approved": "MonitorCheckApproved",
            "Rejected": "RejectInMonitorCheck",
            "Canceled": "CancelInMonitorCheck"
        },
        "prerequisiteActivities": [
            "TransactionMonitor",
            "MerchantWarned"
        ]
    },
    {
       "name": "RejectInMonitorCheck",
       "jobs": ["RejectApplication"]
    },
    {
      "name": "NotifyAppForMonitorCheckRejected",
      "type": "onsNotify",
      "message": "PDLReject",
      "prerequisiteActivities": [
           "RejectInMonitorCheck"
      ]
    },  
    {
        "name": "MonitorCheckRejected",
        "type": "terminate",
        "prerequisiteActivities": [
            "NotifyAppForMonitorCheckRejected"
        ]
    },
    {
        "name": "CancelInMonitorCheck",
        "jobs": ["CancelApplication"]
    },{
        "name": "NotifyForMonitorCheckCanceled",
        "type": "onsNotify",
        "message": "Cancel",
        "prerequisiteActivities": [
             "CancelInMonitorCheck"
        ]
    },
    {
        "name": "MonitorCheckCanceled",
        "type": "terminate",
        "prerequisiteActivities": [
            "NotifyForMonitorCheckCanceled"
        ]
    },
    {
        "name": "MonitorCheckApproved",
        "type": "dummy"
    },
    {
        "name": "ApproveApplication",
        "jobs": ["ApproveApplication"],
        "waitOne": true,
        "prerequisiteActivities": [
            "MonitorCheckApproved",
            "FinalCheckApprovedWithoutTM"           
        ]
    },
    {
       "name": "NotifyAppForPDLApproved",
       "type": "onsNotify",
       "message": "PDLApprove",
       "prerequisiteActivities": [
            "ApproveApplication"       
       ]
    },
    {
       "name": "PDLOpenCardSuccess",
       "type": "block",
       "prerequisiteActivities": [
           "ApproveApplication"
       ]
    },
    {
       "name": "PDLProductSelectCondition",
       "type": "conditional",
       "key": "dataInt",
       "choices": {
          "1": "PreSelected",
          "2": "DelaySelected"
       },
       "prerequisiteJobs": [
           "FinalCheck"
       ]
    },
    {
       "name": "PreSelected",
       "type": "dummy"
    },
    {
       "name": "DelaySelected",
       "type": "dummy"
    },
    {
       "name": "LoanApprovePDL",
       "jobs": ["LoanApprovePayDayLoan"],
       "prerequisiteActivities": [
           "PreSelected",
           "ApproveApplication"
       ]
    },
    {
       "name": "CheckPayDayLoan",
       "jobs": ["CheckPayDayLoanCondition"],
       "prerequisiteJobs": [
           "MoneyTransferSubmitted"
       ]
    },
    {
       "name": "CheckPayDayLoanCondition",
       "type": "conditional",
       "choices": {
          "PDLPass": "PDLPass",
          "PDLReject": "PDLRejected"
       },
       "prerequisiteJobs": [
           "CheckPayDayLoanCondition"
       ]
    },
    {
       "name": "PDLPass",
       "type": "dummy"
    },
    {
       "name": "PDLRejected",
       "type": "dummy"
    },
    {
       "name": "NotifyAppForPDLRejected",
       "type": "onsNotify",
       "message": "PDLLoanFail",
       "prerequisiteActivities": [
           "PDLRejected"
       ]
    },
    {
       "name": "BeginPaymentStarted",
       "type": "dummy",
       "waitOne": true,
       "prerequisiteActivities": [
           "LoanApprovePDL",
           "PDLPass"
       ]
    },
    {
       "name": "PaymentForPDL",
       "type": "conditional", 
       "job": "Remittance",
       "queueName": "RemittanceQueue",
       "choices": {
          "failed": "FinishApplication",
          "default": "PaymentSucceed"       
       },
       "prerequisiteActivities": [
           "BeginPaymentStarted"
       ]
    },
    {
       "name": "DoManualPayment",
       "type": "conditional",
       "job": "LoanMoneyForPDL",
       "queueName": "LoanQueueV2",
       "choices": {
           "Failed": "FinishApplication",
           "default": "PaymentSucceed"
       }
    },
    {
       "name": "FinishApplication",
       "jobs": ["FinishApplicationForPDL"],
       "queueName": "FinishApplicationQueueV2"
    },
    {
       "name": "PaymentSucceed",
       "queueName": "CatfishServerQueue",
       "jobsMap": {
          "FlagApplicationCompleted" : "JobRequestQueue"
       },
       "optionalJobs":[
          "AppMoneyTransferred",
          "ApplicationCompleted"
       ],
       "optionalJobsMap" : {
     	  "GeneratePDF" : "GeneratePDFQueue" 
        }
    },
    {
       "name": "NotifyAppForPDLLoanMoneySuccess",
       "type": "onsNotify",
       "message": "PDLLoanSuccess",
       "prerequisiteActivities": [
           "PaymentSucceed"
       ]
    },
    {
       "name": "WorkflowEnd",
       "type": "terminate",
       "waitOne": true,
       "prerequisiteActivities": [
           "PaymentSucceed",
           "FinishApplication",
           "PDLRejected"
       ]
    }
]