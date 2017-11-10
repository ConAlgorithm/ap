Ext.require("Tool.Item.MethodItem");
Ext.require("Tool.Item.FieldItem");

Ext.define('Tool.ModelPanel', {
    extend: 'Ext.form.Panel',
    xtype: 'model-panel',
    width: '100%',
    height: '100%',
    layout: 'accordion',
    defaultType: 'panel',
    items: [
       {
    	   title: '字段',
    	   autoScroll: true,
    	   html:'<div class="fields-container"></div>'
       },
       {
    	   title: '方法',
    	   autoScroll: true,
    	   html:'<div class="methods-container"></div>'
       }
    ],
    listeners:{
    	afterrender: function(){
    		var me = this;
    		var fieldsContainer = $(me.items.getAt(0).getEl().query('[class=fields-container]')[0]);
    		var methodsContainer = $(me.items.getAt(1).getEl().query('[class=methods-container]')[0]);
    		this.loadModelInfo(function(result){
    			    var model = JSON.parse(result.model);
    				$.each(model.members, function(index, data){
        				if(data.classify == 'field'){
        					me.addFieldInfo(fieldsContainer, data);
        				}else if(data.classify == 'method'){
        					me.addMethodInfo(methodsContainer, data);
        				}
        			})		
    		});
    		this.bindClickEvent();
        },
        click: function(domain){
        	console.log(domain);
        	var sum = "";
        	$.each(domain, function(index, value){
        		sum += value;
        	})
        	if(sum == "")
        	{
        		Ext.MessageBox.alert("警告","没有绑定域！");
        	}else{
        		Ext.require('Tool.DomainWindow',function(){
        			Ext.create('Tool.DomainWindow', {
        				domain: domain
        			}).show();
        		});
        	}
        }
    },
    
    bindClickEvent: function(){
    	var elm = this.getEl();
    	var me = this;
		elm.on('click', function(e){
			var dom = Tool.Item.BaseItem.getClickItem(e);
			if(dom != null)
			{
				var classify = Tool.Item.BaseItem.getClassify(dom);
				console.log('classify' + classify);
				if(classify == 'field'){
					me.fireEvent('click', {
						bindDomain: Tool.Item.BaseItem.getFieldBindDomain(dom)
					}, me);
				}else if(classify == 'method'){
					me.fireEvent('click', {
						paramDomains: Tool.Item.BaseItem.getMethodParamDomains(dom),
						returnDomain: Tool.Item.BaseItem.getMethodReturnDomain(dom)
					}, me);
				}
			}			    
		});
    },
    
    addFieldInfo: function(container, data){
    	container.append(Ext.create('Tool.Item.FieldItem',{
			data: data
		}).getEl());
    },
    addMethodInfo: function(container, data){
    	container.append(Ext.create('Tool.Item.MethodItem',{
			data: data
		}).getEl());
    },
    loadModelInfo: function(onSuccess){
    	$.ajax({
   		   async:false,
   		   url: AnalyzerUrl + 'class/' + CURRENT.prd + "/in/" + this.name,
   		   type: "GET",
   		   dataType: 'jsonp',
   		   jsonp: 'Callback',
   		   timeout: 10000,
   		   success: function (json) {
   			  onSuccess(json);
   		   },
   		   error: function(XMLHttpRequest, textStatus){
   			  Ext.MessageBox.alert("错误","加载"  + me.title + "模型列表失败！");
   		   }
   	   });
    }
});