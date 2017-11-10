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
        "name": "SecondLoanInfo",
        "jobs": [
            "CheckSecondLoanInfo"
        ],
        "prerequisiteJobs": [
            "LoginOrRegistered"
        ]
    },{
    	"name":"ApplicationAgreed",
    	"type":"dummy",
    	"prerequisiteJobs": [
            "ApplicationAgreed"
        ]
    },{
        "name": "IdentityInfoSubmitted",
        "jobs": [
            "CheckUserMobileReference",     
            "CheckIdCardBasicInfo",
            "CheckIdCardApplicationStatistics",
            "CheckUserMobileOn3rdParty",
            "CheckUserOnQhzx",
            "CheckUserOnBr",
            "CheckUserOnBaiDuBL",
            "CheckUserOnSHZX",
            "CheckUserOnBrScore",
            "CheckUserOnBrApplyVerification",
            "CheckApplyTimes",
            "CheckWeChatInfo",
            "CheckUserOnBrCredit",
            "CheckUserOnShuWei",
            "CheckUserOnBQS",
            "CheckUserOnFuShu",
            "CheckUserOnKaoLa",
            "CheckUserOnKaoLaIdentity",
            "ProductCategory",
            "CheckUserOnJYZX",
            "CheckUserOnSuanHua",
            "CheckUserDeviceInfo",
            "CheckPhotoOnShangTang"
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
        	"CheckQQLength",
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
            "JOInfoCrawl"
        ],
        "optionalJobs": [
            "CheckThirdContactMobileOn3rdParty",
            "CheckAdditionalContactMobileOn3rdParty",
            "CheckThirdContactReference",
            "CheckAdditionalContactReference",
            "CheckIsSurnameOfFatherAndSonSame",
            "CheckContactBook"
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
            "CheckBankCardOn3rdParty",
            "CheckUserOnBrPayConsum",
            "CheckUserOnTaiRong"
        ],
        "prerequisiteActivities": [
            "ApplicationAgreed"
        ]
    },
    {
    	 "name": "CalculateApplicationMaxCredit",
    	 "optionalJobs": [
    	 "CalculateApplicationMaxCredit"
    	 ],
    	 "prerequisiteActivities": [
    	    "ApplicationAgreed"
    	 ]
    },
    {
        "name": "JXLInfoCrawling",
        "optionalJobs": ["JXLInfoCrawling"],
        "prerequisiteJobs": [
            "JXLInfoSubmitted"
        ]
    },
    {
        "name": "ZmxyScoreCheck",
        "optionalJobs": ["CheckUserOnZmxy"],
        "prerequisiteJobs": [
            "ZmxyAuthorizeApprove"
        ]
    },
    {
	    "name": "REG007InfoCrawling",
	    "optionalJobs": ["REG007InfoCrawling"],
        "prerequisiteJobs": [
            "IdentityInfoSubmitted"
        ]
	},{
	    "name": "CheckUserDspTd",
	    "jobs": [
	    	"CheckUserOnTdV3"
	    ],
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
    	"name" : "CheckUserOnBaiDuScore",
    	"jobs" : [
    	      "CheckUserOnBaiDuScore"
    	 ],
    	 "prerequisiteActivities": [
              "ApplicationAgreed"
          ]
    },
    {
    	 "name": "AuditPrepared",
    	 "type": "dummy",
         "oneOff":true,
         "prerequisiteJobs": [
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
             "CheckBankCardOn3rdParty",
             "CheckUserOnQhzx",
             "CheckUserOnBr",
             "CheckUserOnBaiDuBL",
             "JOInfoCrawl",
             "CheckUserOnSHZX",
             "ProductCategory",
             "CheckUserOnTaiRong",
             "CheckPengyuanPersionalRisk",
             "CheckUserOnFuShu",
             "CheckUserOnBQS",
             "CheckUserOnKaoLa",
             "CheckUserOnKaoLaIdentity",
             "CheckUserDeviceInfo"
         ],
    	 "prerequisiteActivities" : [
           "CheckUserOnBaiDuScore",
           "CheckUserDspTd",
           "CalculateApplicationMaxCredit"
         ]
    },
    {
    	"name":"DynamicSplictMLAndRE",
    	"type": "split_dyna",
	    "branches": {
	        "TraditionRisk": 1,
	        "MachineLearning": 0,
            "TraditionObserve":0
	    },
        "prerequisiteActivities" : [
            "AuditPrepared"
        ]
    },
    {
    	"name":"TraditionRisk",
    	"jobs": ["TraditionRiskTag"]
    },
    {
    	"name":"TraditionObserve",
    	"jobs":["TraditionObserveTag"]
    },
    {
    	"name":"MachineLearning",
    	"jobs": ["MachineLearningTag"]
    },
    {
    	"name":"MLPreCheck",
    	"queueName": "MachineLearningQueue",
    	"jobs":["MachineLearningRuleCheck"],
    	"jobStatus" :{"MachineLearningRuleCheck":"2"},
	    "prerequisiteActivities" : [
	        "MachineLearning"
	    ]
    },
    {
    	"name":"MLPreCheckCondition",
    	"type": "conditional",
    	"choices":{
    		"1":"MLPreCheckApproved",
    		"0":"MLPreCheckRejecte"
    	},
    	"prerequisiteJobs": [
    		"MachineLearningRuleCheck"
		]
    },
    {
    	"name":"MLPreCheckApproved",
    	"jobs":["PreApproveApplication"]
    },
    {
    	"name":"MLResetFund",
		"queueName":"RiskNotificationPos",
		"jobs":["ResetFund"],
    	"prerequisiteActivities" : [
	        "MLPreCheckApproved"
	    ]
    },
    {
    	"name":"MLPreCheckRejecte",
    	"jobs":["RejectApplication"]
    },
    {
        "name": "NotifyForMLPreCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "MLPreCheckRejecte"
        ]
    },
    {
      "name": "PreCheckRejectedEnd",
      "type": "terminate",
      "prerequisiteActivities": [
	         "NotifyForMLPreCheckRejected"
	  ]
    },
    {
        "name": "CheckIdCardOnPoliceServiceForML",
        "jobs": ["CheckIdCardOnPoliceService"],
        "prerequisiteActivities": [
             "MLPreCheckApproved"
        ]
    },
    {
    	"name":"CheckHeadPhotoForML",
    	"jobs": ["CheckHeadPhoto"],
        "oneOff": true,
        "waitOne": true,
        "queueName": "HeadPhotoQueueV2",
        "prerequisiteActivities": [
            "MLPreCheckApproved"
        ],
        "prerequisiteJobs": [
            "HeadPhotoSubmitted"
        ]
    },
    {
        "name": "MLCollectCheck",
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
            "MLPreCheckApproved"
        ]
    },
    
    
    {
    	"name":"CheckIDCardPhotoForML",
    	"jobs": ["CheckIDCardPhoto"],
        "oneOff": true,
        "waitOne": true,
        "queueName": "IDCardPhotoQueueV2",
        "prerequisiteActivities": [
            "MLPreCheckApproved"
        ],
        "prerequisiteJobs": [
            "IdPhotoSubmitted"
        ]
    },
    {
        "name": "CheckBankCardForML",
        "jobs": ["CheckBankCardV3"],
        "oneOff": true,
        "queueName": "BankCardQueueV2",
        "prerequisiteJobs": [
            "BankCardPhotoSubmitted"
        ],
        "prerequisiteActivities": [
            "MLPreCheckApproved"
        ]
    },

    {
    	"name": "CheckPhotoFinished",
    	"type": "dummy",
    	"prerequisiteActivities" : [
	        "CheckIDCardPhotoForML",
	        "MLCollectCheck",
	        "CheckHeadPhotoForML",
	        "CheckBankCardForML"
	    ]
    },
    {
    	"name":"MLReCheck",
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
            "CheckPhotoFinished"
        ]
    },
    {
    	"name": "MLReCheckCondition",
    	"type": "conditional",
        "choices": {
            "Approved": "MLReCheckApproved",
            "Rejected": "MLRejectInReCheck",
            "Canceled": "MLCancelInReCheck"
        },
        "prerequisiteActivities": [
             "MLReCheck"
        ],
        "prerequisiteJobs": [
             "ReCheck"
        ]
    },
    {
        "name": "MLReCheckApproved",
        "type": "dummy"
    },
    {
    	"name": "MLRejectInReCheck",
    	"jobs": ["RejectApplication"]
    },
    {
        "name": "NotifyForMLReCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "MLRejectInReCheck"
        ]
    },{
        "name": "MLReCheckRejectedEnd",
        "type": "terminate",
        "prerequisiteActivities": [
	        "NotifyForMLReCheckRejected"
	    ]
    },{
    	"name": "MLCancelInReCheck",
    	"jobs": ["CancelApplication"]
    },{
        "name": "NotifyForMLReCheckCanceled",
        "type": "onsNotify",
        "message": "Cancel",
        "prerequisiteActivities": [
             "MLCancelInReCheck"
        ]
    },{
        "name": "MLReCheckCanceledEnd",
        "type": "terminate",
        "prerequisiteActivities": [
	        "NotifyForMLReCheckCanceled"
	    ]
    },
    {
    	"name":"MLFinalCheck",
    	"queueName": "MachineLearningQueue",
    	"jobs":["MachineLearning"],
    	"jobStatus" : {"MachineLearning":"2"},
    	"prerequisiteActivities": [
	        "MLReCheckApproved"
	    ]
    },
    {
    	"name":"MLFinalCheckCondition",
    	"type": "conditional",
    	"choices":{
    		"1":"MLFinalCheckApproved",
    		"0":"MLFinalCheckRejecred"
    	},
    	"prerequisiteJobs": [
            "MachineLearning"
        ],
        "prerequisiteActivities": [
           "MLFinalCheck"
        ]
    },
    {
    	"name": "MLFinalCheckRejecred",
    	"jobs": ["RejectApplication"]
    },
    {
        "name": "NotifyForMLFinalCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "MLFinalCheckRejecred"
        ]
    },
    {
        "name": "MLFinalCheckRejectedEnd",
        "type": "terminate",
        "prerequisiteActivities": [
	        "NotifyForMLFinalCheckRejected"
	    ]
    },
    {
    	"name": "MLFinalCheckApproved",
    	"type": "dummy"
    },
    
    
    {
        "name": "PreCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "jobs": ["PreCheck"],
        "oneOff":true,
        "waitOne":true,
   	 	"prerequisiteActivities" : [
          "TraditionRisk",
          "TraditionObserve"
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
      "prerequisiteActivities": [
            "ApplicationAgreed"
      ]
    },
	{
		"name":"IsSpecialFund",
		"jobs":["IsSpecialFund"],
		"queueName":"RiskNotificationPos",
		"prerequisiteActivities": [
            "PreCheckApproved"
        ]
	},
	{
		"name":"IsSpecialCondition",
		"type": "conditional",
		"choices": {
         "true": "IsSpecialApproval",
         "false": "IsSpecialFalse",
		 "default": "IsSpecialFalse"
		},
		"prerequisiteJobs": [
            "IsSpecialFund"
        ]
	},
	{
		"name":"IsSpecialFalse",
		"type": "dummy"
	},
	{
		"name":"IsSpecialApproval",
		"type": "dummy"
	},
	{
		"name":"SpecialFundApproval",
		"jobs":["SpecialFundApproval"],
		"queueName":"RiskNotificationPos",
		"prerequisiteActivities": [
           "IsSpecialApproval"
       ]
	},
	{
        "name": "SpecialFundApprovalDelay",
        "queueName": "JobStatusQueue",
        "jobStatus": {
            "SpecialFundApproval": "default"
        },
        "delay": "SpecialFundApprovalDelay",
        "dynamic": true,
        "jobs": [
            "SpecialFundApproval"
        ],
        "prerequisiteActivities": [
            "IsSpecialApproval"
        ]
    },
	{
	    "name":"SpecialFundApprovalCondition",
	    "type": "conditional",
	    "oneOff":true,
		"choices": {
         "success": "SpecialSign",
         "failed": "ResetFund",
         "default": "ResetFundApprovalDelay"
		},
		"prerequisiteJobs": [
            "SpecialFundApproval"
        ]
	},
	{
		"name":"SpecialSign",
		"jobs":["SpecialSign"],
		"queueName":"RiskNotificationPos"
	},
	{
        "name": "SpecialSignDelay",
        "queueName": "JobStatusQueue",
        "jobStatus": {
            "SpecialSign": "default"
        },
        "delay": "SpecialSignDelay",
        "dynamic": true,
        "jobs": [
            "SpecialSign"
        ],
        "prerequisiteActivities": [
            "SpecialFundApprovalCondition"
        ]
    },
	{
		"name":"SpecialSignCondition",
		"type": "conditional",
		"oneOff":true,
		"choices": {
         "success": "SpecialSignSuccess",
         "failed": "ResetFund",
		 "default": "ResetFundSignDelay"
		},
		"prerequisiteJobs": [
            "SpecialSign"
        ]
	},
	{
		"name":"SpecialSignSuccess",
		"type": "dummy"
	},
	{
		"name":"ResetFundApprovalDelay",
		"type": "dummy"
	},
	{
		"name":"ResetFundSignDelay",
		"type": "dummy"
	},
	{
		"name":"ResetFund",
		"queueName":"RiskNotificationPos",
		"jobs":["ResetFund"]
	},
	{
		"name":"ResetFundApproval",
		"jobStatus": {
            "ResetFund": "approvalDelay"
        },
		"jobs":["ResetFund"],
		"queueName":"RiskNotificationPos",
		"prerequisiteActivities": [
           "ResetFundApprovalDelay"
       ]
	},
	{
		"name":"ResetFundSign",
		"jobStatus": {
            "ResetFund": "signDelay"
        },
		"jobs":["ResetFund"],
		"queueName":"RiskNotificationPos",
		"prerequisiteActivities": [
           "ResetFundSignDelay"
       ]
	},
	{
		"name":"ResetFundCondition",
		"type": "conditional",
		"choices": {
         "success": "ResetFundSuccess",
         "failed": "ResetFundFailed"
		},
		"prerequisiteJobs": [
            "ResetFund"
        ]
	},
	{
		"name":"ResetFundSuccess",
		"type":"dummy"
	},
	{
		"name":"ResetFundFailed",
		"type":"dummy"
	},
	{
		"name":"FundDone",
		"type":"dummy",
		"waitOne":true,
		"oneOff":true,
		"prerequisiteActivities": [
			"SpecialSignSuccess",
            "ResetFundSuccess",
            "IsSpecialFalse"
        ]
	},
    {
    	"name": "CheckInstinctAntiFraudPre",
    	"jobs": ["CheckInstinctAntiFraudPre", "CheckBlackCompanyAndRejectTemp"],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
    	"name": "CheckInstinctAntiFraudPreDelay",
    	"jobs": ["EmptyInstinctPre"],
    	"delay":"InstinctPreDelay",
        "dynamic": true,
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
        "waitOne": true,
        "oneOff": true,
        "prerequisiteActivities": [
            "CheckInstinctAntiFraudPre",
            "CheckInstinctAntiFraudPreDelay"
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
    	"jobs": ["RejectApplication"]
    },{
        "name": "NotifyForBlackCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "RejectInBlackCheck"
        ]
    },{
      "name": "BlackCheckRejected",
      "type": "terminate",
      "prerequisiteActivities": [
         "NotifyForBlackCheckRejected"
	  ]
    },{
    	"name": "CancelInBlackCheck",
    	"jobs": ["CancelApplication"]
    },{
        "name": "NotifyForBlackCheckCanceled",
        "type": "onsNotify",
        "message": "Cancel",
        "prerequisiteActivities": [
             "CancelInBlackCheck"
        ]
    },{
      "name": "BlackCheckCanceled",
      "type": "terminate",
      "prerequisiteActivities": [
	         "NotifyForBlackCheckCanceled"
	  ]
    },{
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
            "CheckInfoFinished",
            "ApprovedInBlackCheck"
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
        	"name":"MachineLearningStarter",
        	"type":"dummy",
 	       "prerequisiteActivities": [
	       		"CheckFraud",
                "CalculateMerchantViewStatistics"
           ]
        },
        
        {
        	"name":"TraditionFlow",
        	"type":"dummy",
        	"prerequisiteActivities": [
	       		"TraditionObserve",
	       		"MachineLearningStarter"
           ]
        },
        {
        	"name":"MachineLearningFlow",
        	"type":"dummy",
        	"prerequisiteActivities": [
	       		"TraditionRisk",
	       		"MachineLearningStarter"
           ]
        },
        
		{
	       "name": "MachineLearningTestSplitDync",
	       "type": "split_dyna",
	       "branches": {
	          "MachineLearningTestNeedResult": 0,
	          "MachineLearningTestNoNeedResult": 1
	       },
	       "prerequisiteActivities": [
	       		"MachineLearningFlow"
	       ]
    	},{
        	"name": "MachineLearningTestNoNeedResult",
            "optionalJobs": [
                 "MachineLearning"
            ],
            "jobStatus" : {"MachineLearning":"0"},
            "queueName": "MachineLearningQueue"
     	},{
        	"name": "MachineLearningTestNeedResult",
            "optionalJobs": [
                 "MachineLearning"
            ],
            "jobStatus" : {"MachineLearning":"1"},
            "queueName": "MachineLearningQueue"
     	},{
	    	"name": "RejectMachineLearning",
	    	"jobs": ["RejectApplication"]
    	},{
        	"name": "ApproveMachineLearningAndStartCheck",
        	"type": "dummy"
    	},{
	        "name": "MachineLearningCheckResultDoneCondition",
	        "type": "conditional",
	        "choices" : {
	           "0": "RejectMachineLearning",
	           "1": "ApproveMachineLearningAndStartCheck"
	        },
	        "prerequisiteJobs": [
	           "MachineLearning"
	         ],
	         "prerequisiteActivities": [
		       		"MachineLearningTestNeedResult"
		     ]
    	},{
	        "name": "NotifyForMachineLearningRejected",
	        "type": "onsNotify",
	        "message": "Reject",
	        "prerequisiteActivities": [
	             "RejectMachineLearning"
	        ]
		},
	    {
	        "name": "MachineLearningRejected",
	        "type": "terminate",
	        "prerequisiteActivities": [
	           "NotifyForMachineLearningRejected"
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
        "waitOne": true,
        "prerequisiteActivities": [
           "MachineLearningTestNoNeedResult",
           "TraditionFlow"
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
       "jobs": ["EmptyForSegmentF4"],
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
           "ApprovedWithInstinctMonitor": "FinalCheckApprovedWithIM",
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
        "name": "FinalCheckApprovedWithIM",
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
    	"oneOff": true,
        "waitOne": true,
    	"jobs": ["CheckInstinctAntiFraudFinal"],
        "prerequisiteActivities": [
            "FinalCheckApprovedWithIM",
            "ApproveMachineLearningAndStartCheck"
        ]
    },
    {
    	"name": "FraudCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "oneOff": true,
        "waitOne": true,
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
    	"jobs": ["RejectApplication"]
    },{
        "name": "NotifyForFraudCheckRejected",
        "type": "onsNotify",
        "message": "Reject",
        "prerequisiteActivities": [
             "RejectInFraudCheck"
        ]
    },{
      "name": "FraudCheckRejected",
      "type": "terminate",
      "prerequisiteActivities": [
	         "NotifyForFraudCheckRejected"
	  ]
    },{
    	"name": "CancelInFraudCheck",
    	"jobs": ["CancelApplication"]
    },{
        "name": "NotifyForFraudCheckCanceled",
        "type": "onsNotify",
        "message": "Cancel",
        "prerequisiteActivities": [
             "CancelInFraudCheck"
        ]
    },{
      "name": "FraudCheckCanceled",
      "type": "terminate",
      "prerequisiteActivities": [
	         "NotifyForFraudCheckCanceled"
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
            "TransactionMonitor"
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
    	"name": "CheckInstinctAntiFraudFinalOptional",
    	"optionalJobs" : ["CheckInstinctAntiFraudFinal"],
    	"waitOne": true,
    	"oneOff": true,
        "prerequisiteActivities": [
	       "MonitorCheckApproved",
	       "FinalCheckApprovedWithoutTM"
	    ]
    },
    {
        "name": "ApproveApplication",
        "jobs": ["ApproveApplication"],
    	"waitOne": true,
    	"oneOff": true,
        "prerequisiteActivities": [
            "CheckInstinctAntiFraudFinalOptional",
            "ApprovedInFraudCheck",
            "MLFinalCheckApproved"
        ]
    },
    {
    	"name": "CheckMerchantBegin",
    	"type": "dummy",
    	"oneOff": true,
    	"waitOne": true,
    	"prerequisiteActivities": [
           "ApproveApplication",
           "ApproveInFluentCheck",
			"ApproveMachineLearningAndStartCheck"
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
		   "0": "NoNeedIOUAndBuckle",
           "16": "CheckIOUStart",
           "32": "CheckBuckleStart",
           "48": "CheckBothStart"
       },
       "prerequisiteActivities": [
          "CheckEvidenceBegin"
       ]
    },
	{
        "name": "NoNeedIOUAndBuckle",
        "type": "dummy"
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
           "CheckBothFinished",
		   "NoNeedIOUAndBuckle"
       ]
    },
	{
       "name": "NeedCheckEvidenceStart",
       "type": "dummy",
       "waitOne": true,
       "prerequisiteActivities": [
           "CheckIOUStart",
           "CheckBuckleStart",
           "CheckBothStart"
       ]
    },
    {
       "name": "NotifyAppForUploadEvidence",
       "type": "onsNotify",
       "message": "UploadFiles",
       "key": "dataInt",
       "prerequisiteJobs": [
            "ApprovalEvidenceRequirementCheck"
       ],
	   "prerequisiteActivities": [
	        "NeedCheckEvidenceStart"
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
           "CheckMerchantBegin"
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
           "LoanApproveInLoanCheck",
		   "FundDone"
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
       "queueName": "POSPaymentRequestQueue",
       "choices": {
          "MaunalPaymentRequired": "DoManualPayment",
          "Failed" : "PaymentFailed",
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
       "queueName": "POSPaymentRequestQueue",
       "choices": {
           "Failed": "PaymentFailed",
           "default": "PaymentSucceed"
       }
    },
    {
       "name": "FinishApplication",
	   "oneOff":true,
	   "waitOne": true,
       "jobs": ["CancelApplication"],
       "prerequisiteActivities": [
           "PaymentFailed",
		   "ResetFundFailed"
       ]
    },
    {
        "name": "PaymentFailed",
        "type": "dummy",
        "oneOff":true
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