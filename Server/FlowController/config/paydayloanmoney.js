[
    {
       "name": "CheckPayDayLoan",
       "jobs": ["CheckPayDayLoanCondition"],
       "prerequisiteActivities": [
           "AntiFraudCheckApproved"
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
    	"name": "CalculatePDLCurrentBasicInfo",
    	"jobs": ["CalculatePDLCurrentBasicInfo"],
        "prerequisiteJobs": [
            "MoneyTransferSubmitted"
        ]
    },
    {
    	"name": "CalculatePDLPreviousBasicInfo",
    	"jobs": ["CalculatePDLPreviousBasicInfo"],
        "prerequisiteJobs": [
            "MoneyTransferSubmitted"
        ]
    },
    {
    	"name": "CalculatePDLGPSInfo",
    	"jobs": ["CalculatePDLGPSInfo"],
        "prerequisiteJobs": [
            "MoneyTransferSubmitted"
        ]
    },
    {
    	"name": "CalculatePDLStatistics",
    	"jobs": ["CalculatePDLStatistics"],
        "prerequisiteJobs": [
            "MoneyTransferSubmitted"
        ]
    },
    {
    	"name": "CalculatePDLPreviousPaybackInfo",
    	"jobs": ["CalculatePDLPreviousPaybackInfo"],
        "prerequisiteJobs": [
            "MoneyTransferSubmitted"
        ]
    },
    {
    	"name": "CalculatePDLLatestTwoBillsStatus",
    	"jobs": ["CalculatePDLLatestTwoBillsStatus"],
    	"queueName": "PDLInformationAcquisitionQueue",
        "prerequisiteJobs": [
            "MoneyTransferSubmitted"
        ]
    },
    {
    	"name": "CalculatePDLLastRepaymentTime",
    	"jobs": ["CalculatePDLLastRepaymentTime"],
    	"queueName": "PDLInformationAcquisitionQueue",
    	"prerequisiteActivities": [
            "CalculatePDLPreviousBasicInfo"
        ]
    },
    {
        "name": "AntiFraudCheck",
        "type": "conditional",
        "job": "AntiFraudCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "choices": {
            "Approved": "AntiFraudCheckApproved",
            "Rejected": "FreezeInAntiFraudCheck",
            "Canceled": "CancelInAntiFraudCheck"
        },
        "prerequisiteActivities": [
            "CalculatePDLCurrentBasicInfo",
			"CalculatePDLPreviousBasicInfo",
			"CalculatePDLGPSInfo",
			"CalculatePDLStatistics",
			"CalculatePDLPreviousPaybackInfo",
			"CalculatePDLLatestTwoBillsStatus",
			"CalculatePDLLastRepaymentTime"
        ]
    },
    {
    	"name": "AntiFraudCheckApproved",
    	"type": "dummy"
    },
    {
        "name": "FreezeInAntiFraudCheck",
        "type": "dummy"
    },
    {
    	"name": "FreezeApplication",
    	"jobs": ["FreezeApplication"],
    	"prerequisiteActivities": [
    	    "FreezeInAntiFraudCheck"
    	]
    },
    {
    	"name": "CancelApplication",
    	"jobs": ["CancelApplication"],
    	"prerequisiteActivities": [
       	    "FreezeInAntiFraudCheck"
       	]
    },
    {
       "name": "NotifyAppForAntiFraudCheckFrozen",
       "type": "onsNotify",
       "message": "PDLFrozen",
       "prerequisiteActivities": [
            "FreezeApplication",
            "CancelApplication"
       ]
    },  
    {
         "name": "AntiFraudCheckFrozen",
         "type": "terminate",
         "prerequisiteActivities": [
             "NotifyAppForAntiFraudCheckFrozen"
         ]
    },
    {
         "name": "CancelInAntiFraudCheck",
         "jobs": ["CancelApplication"]
    },{
         "name": "NotifyForAntiFraudCheckCanceled",
         "type": "onsNotify",
         "message": "Cancel",
         "prerequisiteActivities": [
              "CancelInAntiFraudCheck"
         ]
    },
    {
         "name": "AntiFraudCheckCanceled",
         "type": "terminate",
         "prerequisiteActivities": [
             "NotifyForAntiFraudCheckCanceled"
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
           "PDLPass"
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
           "NotifyAppForPDLRejected"
       ]
    }
]