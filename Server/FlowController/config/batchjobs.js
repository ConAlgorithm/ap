[
    {
        "name": "Trigger",
        "prerequisiteJobs": [
            "triggerBatchJobs"
        ]
    },
    {
        "name": "RunCollectionBatch",
        "queueName": "BatchQueue",
        "optionalJobs" : ["RunCollectionBatch"],
        "prerequisiteActivities": [
            "Trigger"
        ]
    },   
    {
        "name": "GenerateWelcomeCallListJob",
        "queueName": "ScheduledBatchJobsFromTriggerQueue",
        "jobs" : ["GenerateWelcomeCallListJob"],
        "prerequisiteActivities": [
            "RunCollectionBatch"
        ]
    },
    {
        "name": "BatchJobsDone",
        "type": "terminate",
        "prerequisiteActivities": [
            "GenerateWelcomeCallListJob"
        ]
    }
]
