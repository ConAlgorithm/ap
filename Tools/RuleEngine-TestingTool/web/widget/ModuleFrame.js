Ext.define('Tool.ModuleFrame', {
    extend: 'Ext.form.Panel',
    xtype: 'module-frame',
    border: false,
    id: 'moduleFrame',
    bodyStyle:{
    	background: 'transparent'
    },
    layout: {
       type: 'hbox'
    },
    listeners: {
    	afterrender: function(){
    		this.loadProductsSelection();
    		var testPlat = Ext.getCmp('testPlatform');
    		var prodPlat = Ext.getCmp('prodPlatform');
    		testPlat.getEl().on('click', function(){
    		    this.fireEvent('click');
    		}, testPlat);
    		prodPlat.getEl().on('click', function(){
    		    this.fireEvent('click');
    		}, prodPlat);
    	}
    },
    items:[{
    	//html: '<div style="width:100%;height:40%;font-size:50px;text-align:center">测试平台</div><div style="width:100%;">概述:用于在测试环境进行操作</div>',
    	width: 260,
    	height: 260,
    	frame:true,
    	layout: 'anchor',
    	items:[
	       {
	    	   xtype: 'combobox',
	    	   id: 'prdSelection',
	    	   valueField: 'prd',
	    	   displayField: 'prd',
	    	   anchor: '100% 10%'
	       },
    	   {
	       	   id: 'testPlatform',
	       	   bodyCls: 'moduleItem',
	    	   html: '<div style="width:100%;height:40%;font-size:50px;text-align:center">测试平台</div><div style="width:100%;">概述:用于在测试环境进行操作</div>',
	    	   anchor: '100% 88%',
	    	   listeners: {
		       		click: function(){
		       			var me = Ext.getCmp('moduleFrame');
		       			var prd = Ext.getCmp('prdSelection').getValue();
		       			if(!prd)
		       			{
		       				Ext.MessageBox.alert("提醒","请选择产品！");
		       			}else{
		       				CURRENT = MAP[prd];
		       				me.loadAnalyzerUrl(function(){
			       				me.destroy();
				       			Ext.require('Tool.MainFrame', function(){
				   				    var mainFrame = Ext.create('Tool.MainFrame',{
				   				       renderTo: 'main',
				   				       region: 'center'
				   					});
				   				    mainFrame.show();
				   		        });
			       			})	
		       			}
		       				       			
		       		}
	       	   }
    	   }
    	]
    },
    {
    	width:260,
    	bodyStyle:{
        	background: 'transparent'
        },
        border:false
    },
    {
    	id: 'prodPlatform',
    	html: '<div style="width:100%;height:40%;font-size:50px;text-align:center">生产平台</div><div style="width:100%;">概述:用于在生产环境进行操作,操作时请务必小心！</div>',
    	width: 260,
    	height: 260,
    	frame:true,
    	bodyCls: 'moduleItem',
    	listeners: {
    		click: function(){
    			Ext.MessageBox.alert("提醒","暂未开放权限！");
    		}
    	}
    }],
    loadProductsSelection: function(){
    	var store = Ext.create('Ext.data.Store', {
    		fields: ['prd'],
    		data:[
    		   MAP.POS,
    		   MAP.PDL,
    		   MAP.CASHLOAN
    		]
    	});
    	Ext.getCmp('prdSelection').bindStore(store);
    },
    loadAnalyzerUrl: function(onSuccess){
    	$.ajax({
  		   async:false,
  		   url: 'testing/analyzer',
  		   type: "GET",
  		   timeout: 10000,
  		   success: function (res) {
  			   AnalyzerUrl = res;
  			   onSuccess();	   
  		   },
  		   error: function(XMLHttpRequest, textStatus){
  			  Ext.MessageBox.alert("错误","加载规则分析器地址失败！");
  		   }
  	    });
    }
});