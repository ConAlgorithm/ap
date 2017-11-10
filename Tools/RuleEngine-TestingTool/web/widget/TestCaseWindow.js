Ext.require('Ext.form.Panel');
Ext.require('Ext.picker.Date');
Ext.require('Tool.Item.TestCaseItem');

Ext.define('Tool.TestCaseWindow', {
	id: 'testcaseWindow',
    extend: 'Ext.window.Window',
    xtype: 'testcase-window',   
    width: 430,
    height: 420,
    modal: true,
    resizable: false,
    frame: true,
    title: '案例管理',
    defaultType: 'panel',
    listeners: {
    	show: function(){
    		
    	}
    },
    items:[  
       {
    	   frame: true,
    	   border: false,
    	   cls: 'no-border',
    	   bodyCls: 'panel-border',
    	   layout: {
    		  type: 'hbox',
    		  align: 'center'
    	   },
    	   items:[
	          {
	        	  id: 'testcaseDate',
	        	  flex: 5,
	        	  xtype: 'datefield',
	        	  hideLabel: true,
	    	      format: 'Y-m-d',
	    	      maxValue: new Date(),
	    	      value: new Date()  
	          },
	          {
	        	  flex: 1,
	        	  xtype: 'tbspacer'
	          },
	          {
	        	  flex: 2,
			      xtype: 'button',
				  text: '搜索',
				  handler: function(){
					  var window =  Ext.getCmp('testcaseWindow');
					  var container = $(window.getEl().query('[class=testcases-container]')[0]);
					  container.empty();
					  window.loadTestCaseInfo(function(data){	
						    $.each(data, function(index, item){
						    	container.append(Ext.create('Tool.Item.TestCaseItem', {
									   data: item
								}).getEl());
						    });				    	
					  });
					  
				  }
	          },
	          {
	        	  flex: 1,
	        	  xtype: 'tbspacer'
	          }
    	   ]
       },
       {
    	   xtype: 'form',
    	   frame: true,
    	   border: false,
    	   cls: 'panel-border',
    	   layout: {
     		  type: 'hbox',
     		  align: 'center'
     	   },
    	   items: [
    	      {
    	    	  flex: 5,
    	          xtype: 'filefield',
    	          name: 'testcaseFile',
    	          hideLabel: true,
    	          msgTarget: 'qtip',
    	          allowBlank: false,
    	          buttonText: '选择案例'
    	      },
    	      {
	        	  flex: 1,
	        	  xtype: 'tbspacer'
	          },
	          {
	        	  flex: 2,
			      xtype: 'button',
			      text: '上传',
	    	      handler: function() {
	    	            var form = this.up('form').getForm();
	    	            if(form.isValid()){
	    	                form.submit({  	                	
	    	                    url: 'testing/testcase/upload/' + CURRENT.prd + "/" + Ext.getCmp('testcaseWindow').handler,
	    	                    waitMsg: '正在上传案例...',
	    	                    success: function(form, action) {
	    	                        Ext.Msg.alert('成功', '你的案例已经上传成功!');
	    	                    },
	    	                    failure: function(form, action){
	    	                    	Ext.Msg.alert('错误', '你的案例上传失败,原因：' + action.failureType);
	    	                    }
	    	                });
	    	            }
	    	      }	  
	          },
	          {
	        	  flex: 1,
	        	  xtype: 'tbspacer'
	          }
    	   ]
       },
       //案例列表
       {
    	   id: 'testcaseList',
    	   height: 300,
    	   autoScroll: true,
    	   html:'<div class="testcases-container"></div>'
       }
    ],
    loadTestCaseInfo: function(onSuccess){
    	var date = Ext.getCmp('testcaseDate').getValue();
    	$.ajax({
    		   async:false,
    		   url:  'testing/testcase/' +　CURRENT.prd + '/' + this.handler + '?date=' + date.getTime(),
    		   type: "GET",
    		   timeout: 10000,
    		   success: function (json) {
    			  onSuccess(json);
    		   },
    		   error: function(XMLHttpRequest, textStatus){
    			  Ext.MessageBox.alert("错误","查询测试案例列表失败！");
    		   }
        });
    }

});