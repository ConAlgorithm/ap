[
    {
        "name": "ApplicationStarted",
        "optionalJobs": [
            "CheckUserCreditOn3rdParty",
            "CheckUserCreditOnTd",
            "CheckPosPayInfo",
            "CheckIsUserInfoInBlacklistForCL"
            //"CheckPosMaxOverdueCountsForCL",
            //"CheckPosMaxOverdueDaysForCL"
        ],
        "channel": "cashloan",
        "prerequisiteJobs": [
            "ApplicationStarted"
        ]
    },
    {
        "name": "CLPreCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "channel": "cashloan",
        "jobs": [
            "CLPreCheck"
        ],
        "prerequisiteJobs": [
            "ApplicationStarted",
            "AgreementSigned",
            "CheckIsUserInfoInBlacklistForCL"
            //"CheckPosMaxOverdueCountsForCL",
            //"CheckPosMaxOverdueDaysForCL"
        ]
    },
    {
        "name": "PreCheckDoneCondition",
        "type": "conditional",
        "choices": {
            "Approved": "ApprovedInPreCheck",
            "Rejected": "RejectedInPreCheck"
        },
        "prerequisiteJobs": [
            "CLPreCheck"
        ]
    },
    {
        "name": "ApprovedInPreCheck",
        "queueName": "CLCowfishServiceQueue",
        "jobs": [
            "PreCheckApproved"
        ]
    },
    {
        "name": "PreCheckApproved",
        "type": "dummy",
        "prerequisiteActivities": [
            "ApprovedInPreCheck"
        ]
    },
    {
        "name": "RejectedInPreCheck",
        "queueName": "CLCowfishServiceQueue",
        "jobs": [
            "PreCheckRejected"
        ]
    },
    {
        "name": "NotifyForPreCheckRejected",
        "queueName": "CLCowfishMessageServiceQueue",
        "optionalJobs": [
            "Rejected"
        ],
        "prerequisiteActivities": [
            "RejectedInPreCheck"
        ]
    },
    {
        "name": "TerminatedByPreCheckRejected",
        "type": "terminate",
        "prerequisiteActivities": [
            "RejectedInPreCheck"
        ]
    },
    {
        "name": "CLSecurityCheck",
        "type": "CLSecurityCheck",
        "prerequisiteJobs": [
            "HeadPhotoSubmitted"
        ],
        "prerequisiteActivities": [
            "PreCheckApproved"
        ]
    },
    {
        "name": "CheckUser",
        "queueName": "CLCustomPhoneQueue",
        "jobs": [
            "CheckUserForCL"
        ],
        "oneOff": true,
        "prerequisiteActivities": [
            "CLSecurityCheck"
        ]
    },
    {
        "name": "CLFinalCheck",
        "queueName": "TopRulesDecisionJobRequestQueue",
        "channel": "cashloan",
        "jobs": [
            "CLFinalCheck"
        ],
        "prerequisiteActivities": [
            "CheckUser"
        ]
    },
    {
        "name": "FinalCheckDoneCondition",
        "type": "conditional",
        "choices" : {
            "Approved": "ApprovedInFinalCheck",
            "Rejected": "RejectedInFinalCheck",
            "Canceled": "CanceledInFinalCheck"
        },
        "prerequisiteJobs": [
            "CLFinalCheck"
        ]
    },
    {
        "name": "RejectedInFinalCheck",
        "queueName": "CLCowfishServiceQueue",
        "jobs": [
            "FinalCheckRejected"
        ]
    },
    {
        "name": "NotifyAppForFinalCheckRejected",
        "queueName": "CLCowfishMessageServiceQueue",
        "optionalJobs": [
            "Rejected"
        ],
        "prerequisiteActivities": [
            "RejectedInFinalCheck"
        ]
    },
    {
        "name": "TerminatedByFinalCheckReject",
        "type": "terminate",
        "prerequisiteActivities": [
            "RejectedInFinalCheck"
        ]
    },
    {
        "name": "CanceledInFinalCheck",
        "queueName": "CLCowfishServiceQueue",
        "jobs": [
            "FinalCheckCanceled"
        ]
    },
    {
        "name": "NotifyForFinalCheckCanceled",
        "queueName": "CLCowfishMessageServiceQueue",
        "optionalJobs": [
            "Canceled"
        ],
        "prerequisiteActivities": [
            "CanceledInFinalCheck"
        ]
    },
    {
        "name": "TerminatedByFinalCheckCanceled",
        "type": "terminate",
        "prerequisiteActivities": [
            "CanceledInFinalCheck"
        ]
    },
    {
        "name": "ApprovedInFinalCheck",
        "queueName": "CLCowfishServiceQueue",
        "jobs": [
            "FinalCheckApproved"
        ]
    },
    {
        "name": "NotifyAppForCLApproved",
        "queueName": "CLCowfishMessageServiceQueue",
        "optionalJobs": [
            "Approved"
        ],
        "prerequisiteActivities": [
            "ApprovedInFinalCheck"
        ]
    },
    {
        "name": "Remittance",
        "type": "conditional",
        "job": "Remittance",
        "queueName": "CLCowfishServiceQueue",
        "choices": {
            "success": "PaymentSucceed",
            "failed": "PaymentFailed"
        },
        "prerequisiteActivities": [
            "NotifyAppForCLApproved"
        ]
    },
    {
        "name": "PaymentFailed",
        "type": "dummy",
        "oneOff": true
    },
    {
        "name": "SystemCancelledByPaymentFailed",
        "queueName": "CLCowfishServiceQueue",
        "jobs": [
            "SystemCancelled"
        ],
        "prerequisiteActivities": [
            "PaymentFailed"
        ]
    },
    {
        "name": "NotifyForPaymentFailed",
        "queueName": "CLCowfishMessageServiceQueue",
        "optionalJobs": [
            "Canceled"
        ],
        "prerequisiteActivities": [
            "SystemCancelledByPaymentFailed"
        ]
    },
    {
        "name": "TerminatedByPaymentFailed",
        "type": "terminate",
        "prerequisiteActivities": [
            "SystemCancelledByPaymentFailed"
        ]
    },
    {
        "name": "PaymentSucceed",
        "type": "dummy",
        "oneOff": true
    },
    {
        "name": "NotifyAppLoanMoneySuccess",
        "queueName": "CLCowfishMessageServiceQueue",
        "optionalJobs": [
            "MoneyTransferred"
        ],
        "prerequisiteActivities": [
            "PaymentSucceed"
        ]
    },
    {
        "name": "completeApplication",
        "queueName": "CLCowfishServiceQueue",
        "jobs": [
            "MoneyTransferred"
        ],
        "prerequisiteActivities": [
            "PaymentSucceed"
        ]
    },
    {
        "name": "WorkflowEnd",
        "type": "terminate",
        "waitOne": true,
        "prerequisiteActivities": [
            "completeApplication"
        ]
    }
]
