Ext.Loader.setPath('Tool', 'widget');

Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.getDoc().on("contextmenu", function(e){
		e.stopEvent();
	})
	Ext.require('Tool.LoginForm', function(){
	    Ext.create('Tool.LoginForm',{
	       renderTo: 'login',
	       region: 'center'
		}).show();
	})
});