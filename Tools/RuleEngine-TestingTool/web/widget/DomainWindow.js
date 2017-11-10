Ext.require('Tool.Item.DomainItem');
Ext.require('Ext.form.Panel');

Ext.define('Tool.DomainWindow', {
	id: 'domainWindow',
    extend: 'Ext.window.Window',
    xtype: 'domain-window',   
    domain: {},
    width: 440,
    height: 420,
    modal: true,
    resizable: false,
    autoScroll: true,
    title: '绑定域',
	html:'<div class="domains-container"></div>',
    listeners: {
    	show: function(){
    		var me = this;
    		this.loadDomainInfo(function(data){
    		   $.each(data, function(index, value){
    			   me.addDomainItem(value);
    		   })		   
    		}, this.domain);
    	}
    },
    items:[  
    ],
    addDomainItem: function(domain){
    	var container = $(this.getEl().query('[class=domains-container]')[0]);
    	container.append(Ext.create('Tool.Item.DomainItem', {
			   data: domain
		}).getEl());
    },
    loadDomainInfo: function(onSuccess, domain){
    	$.ajax({
    		   async:false,
    		   url: AnalyzerUrl + 'domain',
    		   type: "GET",
    		   data: domain,
    		   dataType: 'jsonp',
    		   jsonp: 'Callback',
    		   timeout: 10000,
    		   success: function (json) {
    			  onSuccess(json);
    		   },
    		   error: function(XMLHttpRequest, textStatus){
    			  Ext.MessageBox.alert("错误","获取绑定域失败！");
    		   }
        });
    }

});