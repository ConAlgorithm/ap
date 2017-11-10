Ext.require("Tool.Item.BaseItem");
Ext.require("Ext.window.MessageBox");
Ext.require("Tool.Item.LogItem");

Ext.define('Tool.Item.TestCaseItem', {
    extend: 'Tool.Item.BaseItem',
    shadow: false,
    hover: false,
    showBottom: false,
    infoList: function(){
    	var list = [];
    	console.log(this.getData());
    	var item = this.getData();
    	list.push(this.content(item.time, item.file));
    	return list;
    },
    content: function(time, file)
    {
    	var dom = '<div class="item-line">'+
    	              '<div style="float:left; width:60%">{time}</div>'+
    	              '<div style="float:left; width:20%;"><a href="{path}">下载</a></div>'+
    	              '<div style="float:left; width:20%;" file="{file}" class="run-case">执行测试</div>'+
    	          '</div>';
    	var elm = $(dom.replace('{time}', new Date(time).toString()).replace('{path}', file).replace('{file}', file.split('\\')[1]));
    	var me = this;
    	elm.find('.run-case').click(function(e){
    		var file = e.target.getAttribute('file');
    		Ext.Msg.show({
    			title: "提醒",
    			message:'确定要执行该测试吗？',
    			buttons: Ext.Msg.YESNO,
    			icon: Ext.Msg.QUESTION,
    			fn: function(btn){
    				if(btn === 'yes'){
    					me.executeTestCase(file, function(data){
    						Ext.getCmp('consolePanel').fireEvent('showlog', data);
    			 			Ext.getCmp('testcaseWindow').destroy();	
    					});
    				}
    			}
    		});
    	});
    	return elm;
    },
    uploadTestCase: function(){
    	
    },
    
    executeTestCase: function(fileName, onSuccess){
    	$.ajax({
 		   async:false,
 		   url:  'testing/testcase/execute',
 		   type: "POST",
 		   data: JSON.stringify({
 			   product: CURRENT.prd,
 			   decisionPoint: Ext.getCmp('testcaseWindow').handler,
 			   fileName: fileName
 		   }),
 		   timeout: 10000,
 		   success: function (data) {
 			   if(data.success)
 			      onSuccess({
 			    	  info: "开始执行，请稍后..."
 			      });
 			   else
			   {
 				  Ext.Msg.show({
					    title:'错误',
					    message: data.error,
					    buttons: Ext.Msg.OK,
					    icon: Ext.Msg.ERROR,
					    fn: function(btn) {				        
					    }
				  });
			   }
 		   },
 		   error: function(XMLHttpRequest, textStatus){
 			  Ext.MessageBox.alert("错误","执行测试失败！");
 		   }
       });
    }
});