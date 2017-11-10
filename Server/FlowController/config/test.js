[
    {
        "name": "UbtDBWriting",
        "type": "ubtDBWriting",
        "queueName": "UbtDBWritingQueue",
        "optionalJobs": [
            "UbtDBWriting"
        ],
        "prerequisiteJobs": [
            "FirstCheck",
            "Recheck"
        ]
    },
    {
        "name": "CheckPersonalInfo",
        "queueName": "PersonalInfoQueueV2",
        "jobs": ["CheckPersonalInfo"],
        "prerequisiteJobs": [
            "NameIDCardMatchCheckApproved"
        ]
    }
]