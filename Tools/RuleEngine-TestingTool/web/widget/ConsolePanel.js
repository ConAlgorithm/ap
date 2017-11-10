Ext.define('Tool.ConsolePanel', {
	id: 'consolePanel',
	extend: 'Ext.form.Panel',
    xtype: 'console-panel',
    width: '100%',
    height: '100%',
    //layout: 'accordion',
    border: false,
    autoRender: true,
    //autoScroll: true,
    html: '<div class="console-container"></div>',
    listeners: {
    	showlog: function(data){
    		this.addLogItem(data);
    	},
    	
    	showvalidate: function(data){
    		this.addValidateItem(data);
    	},
    	
    	showerror: function(data){
    		this.addLogItem(data);
    	}
    },
    items: [
    ],
    getContainer: function(){
    	return $(this.getEl().query('[class=console-container]')[0]);
    },
    
    addLogItem: function(data){    	
    	this.getContainer().append(Ext.create('Tool.Item.LogItem', {
			data: data
		}).getEl());
    },
    
    addValidateItems: function(list){
    	var container = this.getContainer();
    	Ext.Array.each(list, function(item, index){
    		container.append(Ext.create('Tool.Item.ValidateResultItem', {
    			data: item
    		}).getEl());
    	}) 	
    },
    
    refresh: function(){
    	var me = this;
    	$.ajax({
   		   async:false,
   		   url:  'testing/validateResult/' + CURRENT.prd,
   		   type: "GET",
   		   timeout: 10000,
   		   success: function (data) {
   			   if(data){
   				   me.addValidateItems(data.msg);
   			   }else{
   				me.addLogItem({
     				  info: '没有不匹配的结果!'
     			  });
   			   }
   			   console.log(data);
   		   },
   		   error: function(XMLHttpRequest, textStatus){
   			  me.addLogItem({
   				  error: '获取验证结果失败, error: ' + textStatus
   			  });
   		   }
         });
    },
    
    clear: function(){
    	this.getContainer().empty();
    }
});