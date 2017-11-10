Ext.require("Tool.Item.ExecuteResultItem");
Ext.require("Ext.Button");

Ext.define('Tool.ExecutePanel', {
	id: 'executePanel',
    extend: 'Ext.Container',
    defaultType: 'panel',
    layout: 'column',
    //hidden: true,
    run: true,
    executeItem: null,
    style: {
    	width:'80%',
    	marginLeft: '10%',
    	height: '40px',
    	marginTop: '4px'
    },
    layout: {
		  type: 'hbox',
		  align: 'center'
	},
    items: [
        {
        	flex: 8,
        	border: false,
        	html: '<div class="execute-container"></div>'
        },
        {
      	  flex: 1,
      	  xtype: 'tbspacer'
        },
        {
        	flex: 1,
        	height: '40px',
        	width: '50px',
        	xtype: 'button',
        	cls: 'stopExecute',
        	text: 'STOP',
        	handler: function(){
        		Ext.Msg.show({
        			title: "提醒",
        			message:'确定要停止该测试吗？',
        			buttons: Ext.Msg.YESNO,
        			icon: Ext.Msg.QUESTION,
        			fn: function(btn){
        				if(btn === 'yes'){
        			    	$.ajax({
        			  		   async:false,
        			  		   url:  'testing/stop/' + CURRENT.prd,
        			  		   type: "GET",
        			  		   timeout: 10000,
        			  		   success: function (data) {
        			  			   var result = {
        			  					   info: "停止成功!"
        			  			   }
        			  			     if(!data.success)
        			  			     {
        			  			    	 result = {
        			  			    			 error: "停止失败!"
        			  			    	 }
        			  			     }
	        			  			 Ext.getCmp('consolePanel').fireEvent('showlog', result);       			  			 
        			  		   },
        			  		   error: function(XMLHttpRequest, textStatus){
        			  			   Ext.getCmp('consolePanel').fireEvent('showerror', {
        			  				   error: '停止测试失败, error:'  + textStatus
        			  			   });
        			  		   }
        			        });
        				}
        			}
        		});
        	}
        }
    ],
    listeners: {
    	afterrender: function(){
    		var resultDiv = $(this.items.getAt(0).getEl().query('.execute-container')[0]);
    		this.executeItem = Ext.create('Tool.Item.ExecuteResultItem');
    		resultDiv.append(this.executeItem.getEl());
    		this.intervalGetResult();		   
			/*this.animate({
 	    		   duration: 3000,       	    		  
 	    		   alternate: true,
 	    		   iterations: 10000000,
 	    		   from: {
 	    			  opacity: 0
 	    		   },
 	    		   to: {
 	    			  opacity: 1
 	    		   }
 	        });*/
    	},
    	stop: function(){
    		this.run = false;
    	},
    	start: function(){
    		this.run = true;
    	}
    },
    
    intervalGetResult: function(){
    	if(this.run)
        {
    		this.loadExecuteResult();
        } 	
    	var me = this;
    	setTimeout(function(){
    		    try{
    		    	me.intervalGetResult();
    		    }catch(error){
    		    	Ext.getCmp('consolePanel').fireEvent('showerror', {
    					   error: '获取执行结果失败, error: ' + JSON.stringify(error)
    				});
    		    }
	    		
	    	}, 5000);
    },
    
    loadExecuteResult: function(){
    	var me = Ext.getCmp('executePanel');
    	$.ajax({
  		   async:false,
  		   url:  'testing/executeResult/' + CURRENT.prd,
  		   type: "GET",
  		   timeout: 10000,
  		   success: function (data) {	
  			   if(data)
  			      me.showResult(data);
  		   },
  		   error: function(XMLHttpRequest, textStatus){
  			 Ext.getCmp('consolePanel').fireEvent('showerror', {
				   error: '获取执行结果失败, error: ' + textStatus
			   });
  		   }
        });
    },
    
    showResult: function(data){
		if(this.executeItem)
		{
			this.executeItem.refresh(data);
		}
    }
});