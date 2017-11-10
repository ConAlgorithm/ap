//Ext.require('Tool.ExecutePanel');
Ext.define('Tool.MainFrame', {
	id: 'mainPanel',
    extend: 'Ext.form.Panel',
    xtype: 'main-frame',
    frame:true,
    height: 1000,
    layout: 'border',
    items:[
       {
    	   region: 'north',
    	   title: '信息',
    	   height: 80,
    	   collapsible: true,
    	   listeners: {
    		   afterrender: function(){
    			   var execute = Ext.create('Tool.ExecutePanel');
    			   this.add(execute);		   
    		   }
    	   },
           tools:[
				{
					type: 'close',
					qtip: '关闭执行结果同步',
				    handler: function()
				    {   
				    	this.hide();
				    	this.nextNode().show();
				    	Ext.getCmp('executePanel').fireEvent('stop');
				    }
			    },
			    {
	   	        	 type: 'refresh',
	   	        	 hidden: true,
	   	        	 qtip: '打开执行结果同步',
	   	             handler: function()
	   	             {           
	   	            	this.hide();    
	   	            	this.previousNode().show();
	   	            	Ext.getCmp('executePanel').fireEvent('start');
	   	             }
		    	}
           ]
       },
       {
    	   region: 'west',
    	   width: 150,
    	   id: 'checkpointPanel',
    	   title: '决策点',
    	   defaultType: 'panel', 
    	   collapsible: true,
    	   split: true,
    	   autoScroll: true,
    	   layout: {
    	        type: 'vbox',
    	        align: 'middle'
    	   },   
    	   listeners:{
    		   afterrender: function(target)
    		   {
    			   Ext.getCmp('mainPanel').loadHandlers(CURRENT.prd);	  
    			   
    		   }
    	   }
       },
       {
    	   id: 'modelTabPanel',
    	   region: 'center',
    	   title: '模型',
    	   //frame: true,
    	   xtype: 'tabpanel'   	 
       },
       {
    	   region: 'south',
    	   height: 200,
    	   title: '控制台',
    	   collapsible: true,
    	   split: true,
    	   autoScroll: true,
    	   tools: [
	          {
	        	 type: 'refresh',
	        	 qtip: '刷新日志',
	             handler: function()
	             {           
	            	 Ext.getCmp('consolePanel').refresh();
	             }
	          },
	          {
	        	  type: 'close',
	        	  qtip: '清空日志',
	        	  handler: function()
	        	  {
	        		  Ext.getCmp('consolePanel').clear();
	        	  }
	          }
	       ],
	       listeners: {
	    	   afterrender: function(){
	    		   this.add(Ext.create('Tool.ConsolePanel'));
	    	   }
	       }
       }
    ],
    loadHandlers: function(product)
    {
    	$.ajax({
 		   async:false,
 		   url: AnalyzerUrl + 'handler/' + product,
 		   type: "GET",
 		   dataType: 'jsonp',
 		   jsonp: 'Callback',
 		   timeout: 10000,
 		   success: function (json) {
 			  Ext.getCmp('mainPanel').addHandlers(json.handlers);	   
 		   },
 		   error: function(XMLHttpRequest, textStatus){
 			  Ext.MessageBox.alert("错误","加载决策点失败！");
 		   }
 	    });
    },
    addHandlers: function(handlerArray){
    	handlerArray.push("EntryCheck");
    	var west = Ext.getCmp('checkpointPanel');
    	var me = Ext.getCmp('mainPanel');
    	Ext.Array.forEach(handlerArray, function(e){
    		var handler = Ext.create('Ext.panel.Panel',{
    			border: false,
    			height: 50,
    			width: 120,
    			frame:true,
    			margin: '10,0,0,0',
    			align: 'center',
    			bodyCls: 'handler',
    			html: e,
    			listeners:{
    				click: function(handlerName)
    				{
    					me.loadInModels(handlerName, function(list){
    						me.loadModelsDescription(function(desc){
    							var modelTabPanel = Ext.getCmp('modelTabPanel');
								modelTabPanel.removeAll();
    							Ext.require('Tool.ModelPanel', function(){
    								$.each(list, function(index, value){
        								if(desc[value]){
        									var panel = Ext.create('Tool.ModelPanel',{
        										title: desc[value],
        										name: value
        									});
        									modelTabPanel.add(panel);
        								/*	if(index == 0)
        										panel.show();*/
        								}
        							});
    							});
    							
    						});
    					})
    				}
    			}
    		});
    		west.add(handler);
    		var elm = handler.getEl();
    		elm.set({
    			handlerName: e
    		});
    		elm.on('click', function(){
    			this.fireEvent('click', e);
    		}, handler);
    		elm.on("contextmenu", function(target){			   
				   Ext.require('Tool.HandlerMenu', function(){
		    		    Ext.create('Tool.HandlerMenu', {
		    		    	handlerName: e
		    		    }).showAt(target.getXY());
		    	   });
			});
    	});
    },
    loadInModels: function(handlerName, onSuccess){
    	var me = this;
    	$.ajax({
  		   async:false,
  		   url: AnalyzerUrl + 'handler/model/' + ENVIROMENT + "/" + CURRENT.prd + "/handlers/" + handlerName,
  		   type: "GET",
  		   dataType: 'jsonp',
  		   jsonp: 'Callback',
  		   timeout: 10000,
  		   success: function (json) {
  			  onSuccess(!json.models ? [] : json.models);
  		   },
  		   error: function(XMLHttpRequest, textStatus){
  			  Ext.MessageBox.alert("错误","加载"  + me.title + "模型列表失败！");
  		   }
  	   });
    },
    loadModelsDescription: function(onSuccess){
    	var me = this;
    	$.ajax({
  		   async:false,
  		   url: AnalyzerUrl + 'description/' + CURRENT.prd + "/in",
  		   type: "GET",
  		   dataType: 'jsonp',
  		   jsonp: 'Callback',
  		   timeout: 10000,
  		   success: function (json) {
  			  onSuccess(json.modelDescs);
  		   },
  		   error: function(XMLHttpRequest, textStatus){
  			  Ext.MessageBox.alert("错误","加载模型中文描述失败！");
  		   }
  	   });
    }
});