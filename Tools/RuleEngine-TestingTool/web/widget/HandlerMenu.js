Ext.define('Tool.HandlerMenu', {
    extend: 'Ext.menu.Menu',
    xtype: 'handler-menu',
    listeners: {
    },
    items:[
       {
	    	text: '模型管理',
	    	handler: function(){
	    		var menu = this.findParentByType('handler-menu');
	    		Ext.require('Tool.HandlerWindow', function(){
	    		    Ext.create('Tool.HandlerWindow',{
	    		       title: menu.handlerName,
	    		       region: 'center'
	    			}).show();
	    		})
	    	}
      },
      {
    	  text: '案例管理',
    	  handler: function(){
    		    var menu = this.findParentByType('handler-menu');
	    		Ext.require('Tool.TestCaseWindow', function(){
	    		    Ext.create('Tool.TestCaseWindow',{
	    		       handler: menu.handlerName,
	    		       region: 'center'
	    			}).show();
	    		})
    	  }
      }
    ]
});