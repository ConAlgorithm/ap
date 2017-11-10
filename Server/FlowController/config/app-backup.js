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
            "CheckUserCreditOn3rdParty",
            "CheckUserMobileOn3rdParty"
        ],
        "optionalJobs": [
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
            "CheckIsUserInfoInBlacklist",
            "CheckContactCreditOnTd"
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
    	 "name": "CalculateApplicationMaxCredit",
    	 "optionalJobs": [
    	 "CalculateApplicationMaxCredit"
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
	    "name": "CheckPengyuanPersionalRisk",
	    "optionalJobs": ["CheckPengyuanPersionalRisk"],
	    "prerequisiteJobs": [
            "IdentityInfoSubmitted"
        ]
    },{
        "name": "CheckUserCreditOn3rdPartyNew",
        "jobs": [ "CheckUserCreditOn3rdPartyNew" ],
        "prerequisiteJobs": [
            "CheckUserCreditOn3rdParty"
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
            "CheckContactCreditOnTd",
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
            "CheckUserCreditOn3rdPartyNew",
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
    	"name": "CheckInstinctAntiFraudPre",
    	"jobs": ["CheckInstinctAntiFraudPre"],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
        "name": "CollectCheck",
        "type": "appCollectCheck",
        "statusJob": "PhotosRequiredSubmitted",
        "oneOff": true,
        "eventJobMap": {
            "DGroupPhotoSubmitted": "CheckDGroupPhoto"
        },
        "jobQueueMap": {
            "CheckDGroupPhoto": "DGroupPhotoQueueV2"
        },
        "prerequisiteJobs": [
            "PhotosRequiredSubmitted"
        ],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
    	"name": "BlackCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "jobs": ["BlackCheck"],
        "prerequisiteActivities": [
            "CheckInstinctAntiFraudPre"
        ]
    },{
        "name": "BlackCheckDoneCondition",
        "type": "conditional",
        "choices": {
           "Approved": "ApprovedInBlackCheck",
           "Rejected": "RejectInBlackCheck",
           "Canceled": "CancelInBlackCheck"
        },
        "prerequisiteJobs": [
              "BlackCheck"
        ]
    },{
    	"name": "ApprovedInBlackCheck",
    	"type": "dummy"
    },{
    	"name": "RejectInBlackCheck",
    	"type": "dummy"
    },{
    	"name": "CancelInBlackCheck",
    	"type": "dummy"
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
      "jobs": ["CheckHeadPhoto"],
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
      "jobs": ["CheckIDCardPhoto"],
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
        "jobs": ["CheckBankCardV3"],
        "oneOff": true,
        "queueName": "BankCardQueueV2",
        "prerequisiteJobs": [
            "BankCardPhotoSubmitted"
        ],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },{
        "name": "ReCheck",
        "type": "appReCheck",
        "job": "ReCheck",
        "eventJobMap": {
            "HeadPhotoSubmitted": "CheckHeadPhoto",
            "IdPhotoSubmitted": "CheckIDCardPhoto",
            "BankCardPhotoSubmitted": "CheckBankCardV3",
            "DGroupPhotoSubmitted": "CheckDGroupPhoto"
        },
        "jobQueueMap": {
            "ReCheck": "TopRulesDecisionJobRequestQueue",
            "CheckHeadPhoto": "HeadPhotoQueueV2",
            "CheckIDCardPhoto": "IDCardPhotoQueueV2",
            "CheckBankCardV3": "BankCardQueueV2",
            "CheckDGroupPhoto": "DGroupPhotoQueueV2"
        },
        "prerequisiteActivities": [
            "CheckBankCard",
            "CollectCheck",
            "CheckInfoFinished"
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
            "ReCheck"
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
        "name": "CheckFraud",
        "jobs": ["CheckFraud"],
        "queueName": "FraudDecisionJobRequestQueue",
        "prerequisiteActivities": [
            "ReCheckApproved"
        ]
    },{
        "name": "CalculateMerchantViewStatistics",
        "jobs": ["CalculateMerchantViewStatistics"],
        "queueName": "JobRequestQueue",
        "prerequisiteActivities": [
            "ReCheckApproved"
        ]
    },{
        "name": "FluentCheckBeginDelay",
        "jobs": ["Empty"],
        "delay": "FluentCheckDelay",
        "dynamic": true,
        "prerequisiteJobs": [
            "CheckFraud",
            "CalculateMerchantViewStatistics"
        ]
     },{
    	 "name": "FluentCheck",
    	 "jobs": ["FluentCheck"],
    	 "queueName": "TopRulesDecisionJobRequestQueue",
    	 "prerequisiteActivities":[
    	     "FluentCheckBeginDelay"
    	 ]
     },{
         "name": "FluentCheckCondition",
         "type": "conditional",
         "choices": {
             "Approved": "ApproveInFluentCheck",
             "Rejected": "RejectInFluentCheck",
             "Canceled": "CancelInFluentCheck"
         }, 
         "prerequisiteJobs": [
             "FluentCheck"
         ]
      },{
    	  "name": "ApproveInFluentCheck",
    	  "jobs": ["ApproveApplication"]
      },{
          "name": "RejectInFluentCheck",
          "jobs": ["RejectApplication"]
       },{
           "name": "NotifyForFluentCheckRejected",
           "type": "onsNotify",
           "message": "Reject",
           "prerequisiteActivities": [
                "RejectInFluentCheck"
           ]
       },
       {
          "name": "FluentCheckRejected",
          "type": "terminate",
          "prerequisiteActivities": [
              "NotifyForFluentCheckRejected"
          ]
       },
       {
          "name": "CancelInFluentCheck",
          "jobs": ["CancelApplication"]
       },{
           "name": "NotifyForFluentCheckCanceled",
           "type": "onsNotify",
           "message": "Cancel",
           "prerequisiteActivities": [
              "CancelInFluentCheck"
           ]
       },{
           "name": "FluentCheckCanceled",
           "type": "terminate",
           "prerequisiteActivities": [
               "NotifyForFluentCheckCanceled"
           ]
        },
        {
        	"name": "MachineLearningTest",
            "optionalJobs": [
                 "MachineLearning"
            ],
            "queueName": "MachineLearningQueue",
            "prerequisiteActivities": [
                 "CheckFraud",
                 "CalculateMerchantViewStatistics"
             ]
        },{
	       "name": "MachineLearningTestSplitDync",
	       "type": "split_dyna",
	       "branches": {
	          "MachineLearningTestNeedResult": 0.2,
	          "Segmentation": 0.8
	       },
	       "prerequisiteActivities": [
	       		"MachineLearningTest"
	       ]
    	},{
        	"name": "MachineLearningTestNeedResult",
            "optionalJobs": [
                 "MachineLearning"
            ],
            "jobStatus" : {"MachineLearning":"MachineLearning"},
            "queueName": "MachineLearningQueue"
     	},{
	    	"name": "MachineLearningRejectInLoanCheck",
	    	"jobs": ["RejectApplication"]
    	},{
        	"name": "MachineLearningApproveInPreCheck",
        	"jobs": ["LoanApproveApplication"]
    	},{
	        "name": "MachineLearningCheckResultDoneCondition",
	        "type": "conditional",
	        "choices" : {
	           "0": "MachineLearningRejectInLoanCheck",
	           "1": "MachineLearningApproveInPreCheck"
	        },
	        "prerequisiteJobs": [
	           "MachineLearning"
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
        }
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
        "job": "CheckFirstContactForApp",
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
        "job": "CheckSecondContactForApp",
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
        "name": "CheckCompany",
        "queueName": "CompanyPhoneQueueV2",
        "jobs": ["CheckCompanyForApp"],
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
        "name": "CheckPersonalInfo",
        "queueName": "PersonalInfoQueueV2",
        "jobs": ["CheckPersonalInfoV3"],
        "waitOne": true,
        "linkedActivities": [
            "SegmentationPlanF6Block",
            "SegmentationPlanF9Block",
            "SegmentationPlanF1Block"
        ],
        "prerequisiteActivities": [
           "SegmentationPlanF1",
           "SegmentationPlanF6",
           "SegmentationPlanF9"
        ]
    },{
        "name": "InputIdCardInfo",
        "queueName": "InfoEnteringQueueV2",
        "jobs": ["InputIdCardInfo"],
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
        "jobs": ["CheckImageComparison"]
    },
    {
        "name": "CheckUser",
        "queueName": "CustomPhoneQueueV2",
        "jobs": ["CheckUserForApp"],
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
        "jobs": ["CheckCourtExecuted"],
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
        "jobs": ["CheckHomeCredit"],
        "queueName": "HomeCreditForMaleQueueV2"      
    },
    {
        "name": "CheckHomeCreditForFemale",
        "jobs": ["CheckHomeCredit"],
        "queueName": "HomeCreditForFemaleQueueV2"     
    },
    {
       "name": "CheckD1FeedbackPass",
       "type": "checkD1FeedbackPass",
       "message": "CheckD1FeedbackPass",
       "prerequisiteActivities": [
           "SegmentationPlanF1"
        ]
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
           "Canceled": "CancelInFinalCheck",
           "default":  "RejectInFinalCheck"
       },
       "prerequisiteJobs": [
           "FinalCheck"
        ]
    },{
        "name": "FinalCheckApprovedWithTM",
        "type": "dummy"
    },{
       "name": "FinalCheckApprovedWithoutTM",
       "type": "dummy"
    },
    {
       "name": "RejectInFinalCheck",
       "jobs": ["RejectApplication"]
    },{
        "name": "NotifyForFinalCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "RejectInFinalCheck"
        ]
    },
    {
       "name": "FinalCheckRejected",
       "type": "terminate",
       "prerequisiteActivities": [
           "NotifyForFinalCheckRejected"
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
    	"name": "CheckInstinctAntiFraudFinal",
    	"jobs": ["CheckInstinctAntiFraudFinal"],
    	"waitOne": true,
        "prerequisiteActivities": [
            "FinalCheckApprovedWithTM",
            "FinalCheckApprovedWithoutTM"
        ]
    },
    {
    	"name": "FraudCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "jobs": ["FraudCheck"],
        "prerequisiteActivities": [
            "CheckInstinctAntiFraudFinal"
        ]
    },{
        "name": "FraudCheckDoneCondition",
        "type": "conditional",
        "choices": {
           "Approved": "ApprovedInFraudCheck",
           "Rejected": "RejectInFraudCheck",
           "Canceled": "CancelInFraudCheck"
        },
        "prerequisiteJobs": [
              "FraudCheck"
        ]
    },{
    	"name": "ApprovedInFraudCheck",
    	"type": "dummy"
    },{
    	"name": "RejectInFraudCheck",
    	"type": "dummy"
    },{
    	"name": "CancelInFraudCheck",
    	"type": "dummy"
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
        "name": "TransactionMonitor",
        "jobs": ["TransactionMonitorForApp"],
        "queueName": "TransactionMonitorJobRequestQueue",
        "prerequisiteActivities": [
            "FinalCheckApprovedWithTM"
        ]
    },
    {
        "name": "MonitorCheck",
        "type": "conditional",
        "job": "MonitorCheck",
        "oneOff": true,
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
        "name": "NotifyForMonitorCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "RejectInMonitorCheck"
        ]
    },
    {
        "name": "MonitorCheckRejected",
        "type": "terminate",
        "prerequisiteActivities": [
            "NotifyForMonitorCheckRejected"
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
    	"name": "CheckMerchantBegin",
    	"type": "dummy",
    	"oneOff": true,
    	"waitOne": true,
    	"prerequisiteActivities": [
           "ApproveApplication",
           "ApproveInFluentCheck"
        ]
    },
    {
       "name": "CheckMerchant",
       "type": "loop",     
       "job": "CheckMerchantForApp",
       "queueName": "MerchantPhoneQueueV2",
       "prerequisiteActivities": [
           "CheckMerchantBegin"
        ]
    },
    {
    	"name": "CheckEvidenceBegin",
    	"type": "dummy",
    	"oneOff": true,
    	"waitOne": true,
    	"prerequisiteActivities": [
           "ApproveApplication",
           "ApproveInFluentCheck"
        ]
    },
    {
       "name" : "CheckEvidenceCondition",
       "job" : "ApprovalEvidenceRequirementCheck",
       "queueName": "TopRulesDecisionJobRequestQueue",
       "type": "conditional",
       "key": "dataInt",
       "choices": {
           "16": "CheckIOUStart",
           "32": "CheckBuckleStart",
           "48": "CheckBothStart"
       },
       "prerequisiteActivities": [
          "CheckEvidenceBegin"
       ]
    },
    {
        "name": "CheckIOUStart",
        "type": "dummy"
    },
    {
        "name": "CheckIOU",
        "jobs": ["CheckIOUV3"],
        "oneOff": true,
        "queueName": "IOUQueueV2",
        "prerequisiteActivities": [
           "CheckIOUStart"
        ],
        "prerequisiteJobs": [
            "IOUSubmitted"
        ]
    },
    {
       "name": "CheckBuckleStart",
       "type": "dummy"
    },
    {
       "name": "CheckBuckle",
       "jobs": ["CheckBuckle"],
       "oneOff": true,
       "queueName": "BuckleQueueV2",
       "prerequisiteActivities": [
           "CheckBuckleStart"
       ],
       "prerequisiteJobs": [
            "BuckleSubmitted"
       ]
    },
    {
       "name": "CheckBothStart",
       "type": "dummy"
    },
    {
        "name": "CheckIOUInBoth",
        "jobs": ["CheckIOUV3"],
        "oneOff": true,
        "queueName": "IOUQueueV2",
        "prerequisiteActivities": [
           "CheckBothStart"
        ],
        "prerequisiteJobs": [
           "IOUSubmitted"
        ]
    },
    {
       "name": "CheckBuckleInBoth",
       "jobs": ["CheckBuckle"],
       "oneOff": true,
       "queueName": "BuckleQueueV2",
       "prerequisiteActivities": [
           "CheckBothStart"
       ],
       "prerequisiteJobs": [
            "BuckleSubmitted"
       ]
    },
    {
       "name": "CheckBothFinished",
       "type": "dummy",
       "prerequisiteActivities": [
           "CheckIOUInBoth",
           "CheckBuckleInBoth"
       ]
    },
    {
       "name": "CheckEvidenceFinished",
       "type": "dummy",
       "waitOne": true,
       "prerequisiteActivities": [
           "CheckIOU",
           "CheckBuckle",
           "CheckBothFinished"
       ]
    },
    {
       "name": "NotifyAppForUploadEvidence",
       "type": "onsNotify",
       "message": "UploadFiles",
       "key": "dataInt",
       "prerequisiteJobs": [
            "ApprovalEvidenceRequirementCheck"
       ]
    },
    {
       "name": "CheckD1Feedback",
       "type": "checkD1Feedback",
       "message": "CheckD1Feedback",
       "prerequisiteActivities": [
           "ApproveApplication"
        ]
    },
    {
      "name": "DoCheckD1FeedbackJob",
      "jobs": ["CheckD1Feedback"],
      "queueName": "CheckD1FeedbackQueueV2",
      "prerequisiteActivities": [
           "CheckD1Feedback"
      ]
    },
    {
       "name": "LoanCheck",
       "type": "loanCheck",
       "job" : "LoanCheck",
       "ruleQueueName": "TopRulesDecisionJobRequestQueue",
       "checkIOUQueueName": "IOUQueueV2",
       "checkBuckleQueueName": "BuckleQueueV2",
       "prerequisiteActivities": [
           "CheckEvidenceFinished",
           "CheckMerchant"
       ]
    },
    {
       "name": "LoanCheckCondition",
       "type": "conditional",
       "choices": {
           "Approved": "LoanApproveInLoanCheck",
           "Rejected": "RejectInLoanCheck",
           "Canceled": "CancelInLoanCheck"
       },
   
       "prerequisiteJobs": [
           "LoanCheck"
       ]
    },{
    	"name": "LoanApproveInLoanCheck",
    	"jobs": ["LoanApproveApplication"]
    },{
        "name": "LoanCheckApproved",
        "type": "dummy",
        "prerequisiteActivities": [
           "LoanApproveInLoanCheck"
        ]
    },{
    	"name": "RejectInLoanCheck",
    	"jobs": ["RejectApplication"]
    },{
        "name": "NotifyForLoanCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "RejectInLoanCheck"
        ]
    },{
        "name": "LoanCheckRejected",
        "type": "terminate",
        "prerequisiteActivities": [
           "NotifyForLoanCheckRejected"
        ]
    },{
    	"name": "CancelInLoanCheck",
    	"jobs": ["CancelApplication"]
    },{
        "name": "NotifyForLoanCheckCanceled",
        "type": "onsNotify",
        "message": "CancelAfterLoanCheck",
        "prerequisiteActivities": [
             "CancelInLoanCheck"
        ]
    },
    {
        "name": "LoanCheckCanceled",
        "type": "terminate",
        "prerequisiteActivities": [
           "NotifyForLoanCheckCanceled"
        ]
    },
    {
       "name": "Payment",
       "type": "conditional", 
       "job": "AutoPayment",
       "queueName": "AutoPaymentRequestQueue",
       "choices": {
          "MaunalPaymentRequired": "DoManualPayment",
          "default": "PaymentSucceed"       
       },
       "prerequisiteActivities": [
           "LoanCheckApproved"
       ]
    },
    {
       "name": "DoManualPayment",
       "type": "conditional",
       "job": "LoanMoney",
       "queueName": "LoanQueueV2",
       "choices": {
           "Failed": "FinishApplication",
           "default": "PaymentSucceed"
       }
    },
    {
       "name": "FinishApplication",
       "jobs": ["FinishApplication"],
       "queueName": "FinishApplicationQueueV2"
    },
    {
       "name": "PaymentSucceed",
       "queueName": "CatfishServerQueue",
       "optionalJobs":[
          "AppMoneyTransferred",
          "ApplicationCompleted"
       ]
    },
    {
       "name": "WorkflowEnd",
       "type": "terminate",
       "waitOne": true,
       "prerequisiteActivities": [
           "PaymentSucceed",
           "FinishApplication"
       ]
    }
]