var activitySize = {
	width:150,
	height:150
};
var padding = {
	top : 100,
	left : 800
};
function createActivityDom(activity, positions){
	var cssClass = "activity window";
	var domHtml;
	var id = "flowchart" + activity.name;
	var top, left;
	if(positions && positions[id]){
		top= positions[id].top;
		left = positions[id].left;
	} else {
		top = ((activity.level -1)* activitySize.height + padding.top) + 'px';
		left = (activity.offset * activitySize.width + padding.left) + 'px';
	}
	
	if(activity.type == "conditional"){
		
		cssClass = "activity conditional";
		domHtml = '<div class="'+cssClass+'" id="'+id+'" ';
		domHtml +=' style ="top:'+top+'; left:'+left+';" >';
		domHtml +='<svg width="100%" height="100%" viewBox="0,0,80,40" preserveAspectRatio="xMinYMin meet" version="1.1" xmlns="http://www.w3.org/2000/svg">';
		domHtml +='<polygon id="conditional'+activity.name+'" points="40,0 80,20 40,40 0,20" style="fill:gray; stroke:#000000;stroke-width:1"/> </svg>';
		domHtml +='<tspan style="position:absolute;top:0px;left:0px;">' +activity.name+'</tspan></div>';
	} else{
		if(activity.type == "terminate"){
			cssClass += " terminal";
		}
		domHtml = '<div class="'+cssClass+'" id="'+id+'" ';
		domHtml +=' style ="top:'+top+'; left:'+left+';background-color:gray;" >';
		domHtml +=activity.name + '</div>';
	}
    
    return domHtml;
}

function updatePadding(activities){
	var minOffset =0;
	for(var i=0; i< activities.length; i++){
		var activity = activities[i];
		if(activity.offset <minOffset){
			minOffset = activity.offset;
		}
	}
	var availWidth = window.screen.availWidth;
	padding.left = Math.max(-1 * minOffset * activitySize.width , availWidth/2, padding.left);
}

function sss(){
	if(application==null){
		console.log("application is null");
	}
	if(workflow == null){
		console.log("workflow is null");
	}
	
	var states = application.states;
	var pendings = [];
	for(var activityName in states){
		for(var i=0; i<workflow.activities.length; i++ ){
			var activity = workflow.activities[i];
			if(activity.name ==activityName){
				pendings.push(activity);
			}
		}
	}
	
	var result = {};
	for(var n=0; n<pendings.length; n++){
		var jobs = pendings[n].def.jobs;
		if(!jobs){
			continue;
		}
		
		for(var m=0; m<jobs.length; m++){
			var job = jobs[m];
			var found = false;
			for(var k = 0; k<application.messages.length; k++){
				var message = application.messages[k];
				if(message && message.jobName == job){
					found = true;
					break;
				}
			}
			if(!found){
				result[pendings[n].name] = result[pendings[n].name] || [];
				result[pendings[n].name].push(job);
			}
		}
	}
	console.log(result);
}

var workflow;
var currentWorkflowName;
var application;
var pendingApps;
var workflowReady = $.Deferred();
var jsPlumbReady = $.Deferred();

var workflowRawData, workflowPositions;
var workflowDataReady = $.Deferred();
var workflowPositionReady = $.Deferred();

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

$(function(){
    function loadApplication(json){
    	if(json==null || !json.length){
    		alert("application does not exist.");
    		return;
    	}
        application = JSON.parse(json);
        currentWorkflowName = application.workflow;
        getWorkflowFromServer(application.workflow);
    }
    var appId = getQueryString("appId");
    var workflowName = getQueryString("flow");
    if(appId){
    	$.ajax({
            type : "GET",
            url : "/application/"+ appId,
            crossDomain : "true",
            success : function(data) {
                loadApplication(data);
            },
            error : function(err) {
                alert("error");
            }
        });
    } else if(workflowName){
    	currentWorkflowName = workflowName;
    	$.ajax({
            type : "GET",
            url : "/overview/"+ workflowName,
            crossDomain : "true",
            success : function(data) {
            	pendingApps = JSON.parse(data);
            	getWorkflowFromServer(workflowName);
            },
            error : function(err) {
                alert("error");
            }
        });
    } else{
    	alert('Please specify appId or flow');
    }
    
    $("#refresh").click(function(){
    	var appId = getQueryString("appId");
    	var workflowName = getQueryString("flow");
    	if(appId){
    		$.ajax({
                type : "GET",
                url : "/application/"+ appId,
                crossDomain : "true",
                success : function(json) {
                	if(json==null || !json.length){
                		alert("application does not exist.");
                		return;
                	}
                    application = JSON.parse(json);
                	updateActivityStatus();
                },
                error : function(err) {
                    alert("error");
                }
            });
    	} else if(workflowName){
    		$.ajax({
                type : "GET",
                url : "/overview/"+ workflowName,
                crossDomain : "true",
                success : function(data) {
                	pendingApps = JSON.parse(data);
                	updatePendingApps();
                },
                error : function(err) {
                    alert("error");
                }
            });
    	}
    });
});

function loadWorkflow(){
	workflow = getWorkflow(workflowRawData);
	updatePadding(workflow.activities);
	var positions = workflowPositions;
	for(var i=0; i< workflow.activities.length; i++){
		var activity = workflow.activities[i];
		var domHtml = createActivityDom(activity, positions);
		$(domHtml).data("activity",activity).appendTo('#flowchart-demo');
	}
	workflowReady.resolve();
}

function getWorkflowFromServer(workflowName){
	$.ajax({
        type : "GET",
        url : "/config/"+workflowName+".js",
        crossDomain : "true",
        success : function(data) {
        	workflowRawData = JSON.parse(data);
        	workflowDataReady.resolve();
        },
        error : function(err) {
            alert("error");
        }
    });
	loadActivityPositions();
};

$.when(workflowDataReady, workflowPositionReady).then(loadWorkflow);

jsPlumb.ready(function () {
	jsPlumbReady.resolve();
});
$.when(workflowReady, jsPlumbReady).then(load);

function load(){
    var instance = jsPlumb.getInstance({
        // default drag options
        DragOptions: { cursor: 'pointer', zIndex: 2000 },
        // the overlays to decorate each connection with.  note that the label overlay uses a function to generate the label text; in this
        // case it returns the 'labelText' member that we set on each connection in the 'init' method below.
        ConnectionOverlays: [
            [ "Arrow", { location: 1 } ],
            [ "Label", {
                location: 0.1,
                id: "label",
                cssClass: "aLabel"
            }]
        ],
        Container: "flowchart-demo"
    });

    var basicType = {
        connector: "StateMachine",
        paintStyle: { strokeStyle: "red", lineWidth: 2 },
        hoverPaintStyle: { strokeStyle: "blue" },
        overlays: [
            "Arrow"
        ]
    };
    instance.registerConnectionType("basic", basicType);

    // this is the paint style for the connecting lines..
    var connectorPaintStyle = {
            lineWidth: 2,
            strokeStyle: "#61B7CF",
            joinstyle: "round",
            outlineColor: "white",
            outlineWidth: 2
        },
    // .. and this is the hover style.
        connectorHoverStyle = {
            lineWidth: 2,
            strokeStyle: "#216477",
            outlineWidth: 2,
            outlineColor: "white"
        },
        endpointHoverStyle = {
            fillStyle: "#216477",
            strokeStyle: "#216477"
        },
    // the definition of source endpoints (the small blue ones)
        sourceEndpoint = {
            endpoint: "Dot",
            paintStyle: {
                strokeStyle: "#7AB02C",
                fillStyle: "transparent",
                radius: 8,
                lineWidth: 2
            },
            isSource: true,
            maxConnections: -1,
            connector: [ "Flowchart", { stub: [40, 60], gap: 2, cornerRadius: 8, alwaysRespectStubs: true } ],
            connectorStyle: connectorPaintStyle,
            hoverPaintStyle: endpointHoverStyle,
            connectorHoverStyle: connectorHoverStyle,
            dragOptions: {},
            // overlays: [
                // [ "Label", {
                    // location: [0.5, 1.5],
                    // label: "Drag",
                    // cssClass: "endpointSourceLabel"
                // } ]
            // ]
        },
    // the definition of target endpoints (will appear when the user drags a connection)
        targetEndpoint = {
            endpoint: "Dot",
            paintStyle: { fillStyle: "#7AB02C", radius: 8 },
            hoverPaintStyle: endpointHoverStyle,
            maxConnections: -1,
            dropOptions: { hoverClass: "hover", activeClass: "active" },
            isTarget: true,
            // overlays: [
                // [ "Label", { location: [0.5, -0.5], label: "Drop", cssClass: "endpointTargetLabel" } ]
            // ]
        },
        init = function (connection) {
            connection.getOverlay("label").setLabel(connection.sourceId.substring(15) + "-" + connection.targetId.substring(15));
        };

    var _addEndpoints = function (toId, sourceAnchors, targetAnchors) {
        for (var i = 0; i < sourceAnchors.length; i++) {
            var sourceUUID = toId + sourceAnchors[i];
            instance.addEndpoint("flowchart" + toId, sourceEndpoint, {
                anchor: sourceAnchors[i], uuid: sourceUUID
            });
        }
        for (var j = 0; j < targetAnchors.length; j++) {
            var targetUUID = toId + targetAnchors[j];
            instance.addEndpoint("flowchart" + toId, targetEndpoint, { anchor: targetAnchors[j], uuid: targetUUID });
        }
    };

    // suspend drawing and initialise.
    instance.batch(function () {
		for(var i=0; i< workflow.activities.length; i++){
			var activity = workflow.activities[i];
			if(activity.type == "terminate"){
				_addEndpoints(activity.name, ["TopCenter"], []);
			} else {
				_addEndpoints(activity.name, ["TopCenter"], ["BottomCenter"]);
			}
		}
		
		var connections = workflow.connections;
		for(var n=0; n< connections.length; n++){
			var connection = connections[n];
			instance.connect({uuids: [connection.source+"BottomCenter", connection.target + "TopCenter"], editable: true});
		}

        // listen for new connections; initialise them the same way we initialise the connections at startup.
        instance.bind("connection", function (connInfo, originalEvent) {
            init(connInfo.connection);
        });

        // make all the window divs draggable
        instance.draggable(jsPlumb.getSelector(".flowchart-demo .window"), { grid: [20, 20] });
        // THIS DEMO ONLY USES getSelector FOR CONVENIENCE. Use your library's appropriate selector
        // method, or document.querySelectorAll:
        //jsPlumb.draggable(document.querySelectorAll(".window"), { grid: [20, 20] });

        //
        // listen for clicks on connections, and offer to delete connections on click.
        //
        instance.bind("click", function (conn, originalEvent) {
           // if (confirm("Delete connection from " + conn.sourceId + " to " + conn.targetId + "?"))
             //   instance.detach(conn);
            conn.toggleType("basic");
        });

        instance.bind("connectionDrag", function (connection) {
            console.log("connection " + connection.id + " is being dragged. suspendedElement is ", connection.suspendedElement, " of type ", connection.suspendedElementType);
        });

        instance.bind("connectionDragStop", function (connection) {
            console.log("connection " + connection.id + " was dragged");
        });

        instance.bind("connectionMoved", function (params) {
            console.log("connection " + params.connection.id + " was moved");
        });
    });

    jsPlumb.fire("jsPlumbDemoLoaded", instance);
    updateActivityStatus();
    updatePendingApps();
    initTooltip();
};

function formatDate(date){
	return date.getFullYear() + "-"+(date.getMonth() + 1) + "-" + date.getDate() + " " + 
	date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+":"+date.getMilliseconds();
}

function arrayToText(values){
	var result=values[0];
	for(var i=1; i<values.length; i++){
		result +="<br />"+values[i];
	}
	return result;
}

function showTooltip(){
	var activity = $(this).data("activity");
	 $("#activityName").text(activity.name);
	 var def = activity.def;
	 if(def.prerequisiteJobs){
		 $("#prerequisiteJobsContainer").show();
		 $("#prerequisiteJobs").html(arrayToText(def.prerequisiteJobs));
	 } else {
		 $("#prerequisiteJobsContainer").hide();
	 }
	 
	 if(def.prerequisiteActivities){
		 $("#prerequisiteActivitiesContainer").show();
		 $("#prerequisiteActivities").html(arrayToText(def.prerequisiteActivities));
	 } else {
		 $("#prerequisiteActivitiesContainer").hide();
	 }
	 
	 var status = $(this).data("status");
	 if(status){
		 var startTime = new Date(Number.parseInt(status.startTime));
	     var endTime = new Date(Number.parseInt(status.endTime));
	     $("#startTimeContainer").show();
	     $("#startTime").text(formatDate(startTime));
	     $("#endTimeContainer").show();
		 $("#endTime").text(formatDate(endTime));
	 } else {
		 $("#startTimeContainer").hide();
		 $("#endTimeContainer").hide();
	 }
	 $("#activityTooltip").show();
}

function initTooltip(){
	 $(".activity").mouseenter(function(){
		 showTooltip.apply(this);
	 });
	 $(".activity").mouseleave(function(){
		 $("#activityTooltip").hide();
	 });
}

function updatePendingApps(){
	if(!pendingApps || pendingApps.length==0){
		return;
	}
	var pendingCount = {};
	for(var i=0; i<pendingApps.length; i++){
		var app = pendingApps[i];
		if(!app || !app.states){
			continue;
		}
		for(var activity in app.states){
			var state = JSON.parse(app.states[activity]);
			if(state.status == "0"){
				pendingCount[activity] = pendingCount[activity] ? pendingCount[activity]+1 : 1;
			}
		}
	}
	$(".window").css("background-color","gray");
	for(var activity in pendingCount){
		addPending(activity, pendingCount[activity]);
	}
}

function addPending(activity, count){
	var  text = $(".window#flowchart"+activity).data('text');
	if(!text){
		text =  $(".window#flowchart"+activity).text();
		$(".window#flowchart"+activity).data('text',text);
	}
	$(".window#flowchart"+activity).text( count +" : "+text).css("background-color","yellow");
}

function updateActivityStatus(){
	if(!application){
		return;
	}
	
   var finishedActivities = application.finishedActivities;
   for(var activityName in finishedActivities){
	   var activityStatus = JSON.parse(finishedActivities[activityName]);
       $(".window#flowchart"+activityName).css("background-color","green").data("status",activityStatus);
       $("#conditional"+activityName+"").css("fill","green").data("status",activityStatus);
   }

    var states = application.states;
    for(var activityName in states){
    	var activityStatus = JSON.parse(states[activityName]);
    	var color = activityStatus.status == '-1' ? 'yellow' : 'red';
        $(".window#flowchart"+activityName).css("background-color",color).data("status",activityStatus);
        $("#conditional"+activityName+"").css("fill",color).data("status",activityStatus);
    }
}

function loadActivityPositions(){
	$.ajax({
        type : "GET",
        url : "/flowui/"+ currentWorkflowName,
        crossDomain : "true",
        success : function(data) {
        	var positionStr = data;
        	try{
        		workflowPositions = positionStr ? JSON.parse(positionStr) : null;
        	}catch(err){
        		workflowPositions = null;
        	}
        	workflowPositionReady.resolve();
        },
        error : function(err) {
            alert("error");
        }
    });
}

function saveActivityPositions(){
	var workflow = currentWorkflowName;
	var result = {};
	var activities$=$('.activity');
	for(var i=0; i<activities$.length; i++){
		var activity$ = $(activities$.get(i));
		result[activity$.attr('id')] = {
				top : activity$.css('top'),
				left : activity$.css('left')
		};
	}
	//localStorage.setItem(workflow, JSON.stringify(result));
	$.ajax({
        type : "POST",
        url : "/flowui/"+ workflow,
        dataType: 'json',
        data: JSON.stringify({
    		workflow: workflow,
        	coords: result
    	}),
        success : function(data) {
        	alert(data.success);
        },
        error : function(err) {
            alert("error");
        }
    });
}
