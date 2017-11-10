Ext.define('Tool.HandlerWindow', {
	id: 'handlerWindow',
    extend: 'Ext.window.Window',
    xtype: 'handler-window',   
    width: 330,
    height: 330,
    modal: true,
    resizable: false,
    layout: 'absolute',
    defaultType: 'panel',
    outModels: [],
    inModels: [],
    listeners: {
    	show: function(){
    		var me = this;
    		this.loadAllModels(function(body){
    			var allModels = body;
    			me.loadInModels(function(body){
        			me.inModels = body;
        			me.outModels = allModels.filter(function(item){
        				return me.inModels.indexOf(item) == -1;
        			});
        			me.showModelsList(Ext.getCmp('inModels'), me.inModels);
        			me.showModelsList(Ext.getCmp('outModels'), me.outModels);
        		});
    		});
    		
    	}
    },
    items:[
       {
    	  id: 'outModels',
          x: 10,
          y: 15,
          width:130,
          height:240,
          layout:{
        	  type: 'vbox'
          },
          overflowX: 'auto',
          overflowY: 'auto',
          chooseModel: function(modelName){
        	  var me = Ext.getCmp('handlerWindow');
          	  me.inModels.push(modelName);
          	  var index = me.outModels.indexOf(modelName);
          	  if(index != -1)
          	    me.outModels.splice(index, 1);
          },
          removeModel: function(modelName){
        	  var me = Ext.getCmp('handlerWindow');
        	  var index = me.inModels.indexOf(modelName);
        	  if(index != -1)
          	    me.inModels.splice(index, 1);
          	  me.outModels.push(modelName);
          },
          isModelChosen: function(modelName){
        	  var me = Ext.getCmp('handlerWindow');
          	  return me.inModels.indexOf(modelName) != -1;
          }
       },
       {
          xtype: 'button',
          x:150,
          y:120,
        //  icon: 'close',
          listeners: {
        	  click: function(){
        		  var me = Ext.getCmp('handlerWindow');
        		  me.showModelsList(Ext.getCmp('inModels'), me.inModels);
      			  me.showModelsList(Ext.getCmp('outModels'), me.outModels);
        	  }
          }
       },
       {
    	  id: 'inModels',
          x: 180,
          y: 15,
          width:130,
          height:240,
          layout:{
        	  type: 'vbox'
          },
          overflowX: 'auto',
          overflowY: 'auto',
          chooseModel: function(modelName){
        	   var me = Ext.getCmp('handlerWindow');
           	   me.outModels.push(modelName);
           	   var index = me.inModels.indexOf(modelName);
           	   if(index != -1) 
           		   me.inModels.splice(index,1);
           },
           removeModel: function(modelName){
        	   var me = Ext.getCmp('handlerWindow');
        	   var index = me.outModels.indexOf(modelName);
        	   if(index != -1)
           	       me.outModels.splice(index,1);
           	   me.inModels.push(modelName);
           },
           isModelChosen: function(modelName){
        	   var me = Ext.getCmp('handlerWindow');
           	   return me.outModels.indexOf(modelName) != -1;
           }
       },
       {
    	  xtype: 'button',
    	  x: 45,
    	  y: 260,
    	  width: '4em',
    	  text: '提交',
    	  listeners:{
    		  click: function(){
    			  var me = Ext.getCmp('handlerWindow'); 
    			  me.updateInModels(me.inModels, function(e){
    				  if(e.success){
    					  me.destroy();
    					  Ext.MessageBox.alert("成功","更新模型成功！");
    				  }else{
    					  Ext.MessageBox.alert("错误","更新模型失败！");
    				  }
    			  });
    		  }
    	  }
       },
       {
    	  xtype: 'button',
    	  x: 215,
    	  y: 260,
    	  width: '4em',
    	  text: '取消',
    	  listeners: {
    		  click: function(){
    			  Ext.getCmp('handlerWindow').destroy();
    		  }
    	  }
       }
    ],
    loadAllModels: function(onSuccess){
    	$.ajax({
 		   async:false,
 		   url: AnalyzerUrl + 'package/' + CURRENT.prd + "/in/list",
 		   type: "GET",
 		   dataType: 'jsonp',
 		   jsonp: 'Callback',
 		   timeout: 10000,
 		   success: function (json) {
 			  onSuccess(json.modelNames);
 		   },
 		   error: function(XMLHttpRequest, textStatus){
 			  Ext.MessageBox.alert("错误","加载所有模型列表失败！");
 		   }
 	    });
    },
    loadInModels: function(onSuccess){
    	var me = this;
    	$.ajax({
  		   async:false,
  		   url: AnalyzerUrl + 'handler/model/' + ENVIROMENT + "/" + CURRENT.prd + "/handlers/" + me.title,
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
    updateInModels: function(inModels, onSuccess){
    	var me = this;
    	$.ajax({
   		   async:false,
   		   url: "testing/handler/model/" + ENVIROMENT + "/" + CURRENT.prd + "/handlers",
   		   type: "POST",
   		   timeout: 10000,
   		   data: JSON.stringify({
   			   name: me.title,
   			   models: inModels
   		   }),
   		   success: function (json) {
   			  onSuccess(json);
   		   },
   		   error: function(XMLHttpRequest, textStatus){
   			  Ext.MessageBox.alert("错误","加载"  + me.title + "模型列表失败！");
   		   }
   	    });
    },
    showModelsList: function(target, list){
    	var me = this;
    	target.removeAll();
    	Ext.Array.each(list, function(item, index){ 		
			var panel = Ext.create('Ext.panel.Panel',{
				html: item,
				bodyCls: 'modelLabel',
				boder: false,
				frame:false
			}); 				
			target.add(panel);
			var elm = panel.getEl();
			elm.set({
				modelName: item
			});
			elm.on('click', function(e){
				if(target.isModelChosen(item))
				{
					panel.removeBodyCls('chosen');
					target.removeModel(item);
				}else{
					panel.addBodyCls('chosen');
					target.chooseModel(item);
				}
			})
		});
    }
});