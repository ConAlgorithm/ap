function addJobs(jobs, activity, jobInActivity){
    if(!jobs || !jobs.length){
        return;
    }
    
    for(var i=0; i< jobs.length; i++){
        var job = jobs[i];
        jobInActivity[job] = activity.name;
    }
}

function addCountInLevel(level, countInLevel){
    if(!countInLevel[level]){
        countInLevel[level] = 1;
    } else {
        countInLevel[level] ++;
    }
}

function subtractCountInLevel(level, countInLevel){
    if(!countInLevel[level]){
        countInLevel[level] = 0;
    } else {
        countInLevel[level] --;
    }
}

function addConnection(activity, prerequisiteActivities, connections){
    if(!prerequisiteActivities || !prerequisiteActivities.length){
        return;
    }
    
    for(var i=0; i< prerequisiteActivities.length; i++){
        connections.push({
            source : prerequisiteActivities[i],
            target : activity.name
        });
    }
}

function addChoices(activity, choices, connections){
    if(!choices){
        return;
    }
    for(var p in choices){
        connections.push({
            source : activity.name,
            target : choices[p]
        });
    }
}

function addBranches(activity, branches, connections){
    if(!branches){
        return;
    }
    for(var p in branches){
        connections.push({
            source : activity.name,
            target : p
        });
    }
}

function getConnectionsBySource(connections, source){
	var result = [];
	for(var i=0; i<connections.length; i++){
		if(connections[i].source == source){
			result.push(connections[i]);
		}
	}
	return result;
}

function getWorkflow(rawData){
	var activitiesMap = {}; //name -> activity
	var countInLevel = {}; //level -> activities count
	var jobInActivity = {
	    "NameIDCardMatchCheckApproved" : "CheckNameIDCardMatch",
	    "ManualFirstCheckDone" : "FirstCheck"
	}; //job -> activity
	var connections = [];
	var activities = [];
	
	var pendings = [];
	var workflow = rawData;
	//var workflow = app;
	for(var i=0; i< workflow.length; i++){
	    var source = workflow[i];
	    if(source == null){
	        continue;
	    }
	    var activity = {
            name : source.name,
            type : source.type,
            def : source,
            offset : 0
        };
        if(!source.prerequisiteJobs && !source.prerequisiteActivities){
            activity.level = 1;
            addCountInLevel(1, countInLevel);
        }
        if(source.prerequisiteJobs && source.prerequisiteJobs.length){
            pendings.push(source);
        }
        activities.push(activity);
	    activitiesMap[source.name] = activity;
	    if(source.job){
	    	jobInActivity[source.job] = activity.name;
	    }
	    addJobs(source.jobs, activity, jobInActivity);
	    addJobs(source.optionalJobs, activity, jobInActivity);
	    addConnection(activity, source.prerequisiteActivities, connections);
	    addChoices(activity, source.choices, connections);
	    addBranches(activity, source.branches, connections);
	}
	
	for(var n=0; n<pendings.length; n++){
	    var source = pendings[n];
	    var hasConnection = false;
	    for(var m =0; m<source.prerequisiteJobs.length; m++){
	        var job = source.prerequisiteJobs[m];
	        if(!jobInActivity[job]){
	            continue;
	        }
	        hasConnection = true;
	        connections.push({
                source : jobInActivity[job],
                target : source.name
            });
	    }
	    if(!hasConnection){
	        activitiesMap[source.name].level = 1;
	        addCountInLevel(1, countInLevel);
	    }
	}
	//update level
	var activityAfter = {};
	pendings = connections.slice();
	while(pendings.length){
	    var temp = [];
	    var notFinished = false;
	    for(var i=0; i<pendings.length; i++){
            var conn = pendings[i];
            var sa = activitiesMap[conn.source];
            if(sa.level != null){
                var newLevel = sa.level + 1;
                if(activitiesMap[conn.target] == null){
                	console.log("unknown activity:" + conn.target);
                	continue;
                } 
                if(activitiesMap[conn.target].level == null || activitiesMap[conn.target].level<newLevel){
                    if(activitiesMap[conn.target].level != null){
                        subtractCountInLevel(activitiesMap[conn.target].level, countInLevel);
                        var newConnections = getConnectionsBySource(connections, conn.target);
                        if(newConnections && newConnections.length){
                        	temp = temp.concat(newConnections);
                        	notFinished = true;
                        }
                    }
                    activitiesMap[conn.target].level = newLevel;
                    addCountInLevel(newLevel, countInLevel);
                }
            } else {
                temp.push(conn);
            }
        }
        if(notFinished || temp.length != pendings.length){
        	pendings = temp;
        } else {
        	break;
        }
	}
	//update offset
	var offsetMap = {}; //level -> index
	for(var i =0; i<activities.length; i++){
	    var activity = activities[i];
	    if(activity.level !=null){
	        var level = activity.level;
	        if(countInLevel[level] > 1){
	            if(offsetMap[level] == null){
	                offsetMap[level] = -1;
	            }
	            offsetMap[level]++;
	            activity.offset = offsetMap[level] - countInLevel[level] /2 + 0.5;
	        }
	    }
	}
	
	var result = {
	    activities : activities,
	    connections : connections
	};
	
	return result;
};

function getAppStatus(){
    return appStatus;
}
