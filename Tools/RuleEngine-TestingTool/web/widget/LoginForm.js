Ext.define('Tool.LoginForm', {
    extend: 'Ext.form.Panel',
    xtype: 'login-form',   
    title: '登录',
    frame:true,
    width: 320,
    bodyPadding: 10,
    defaultType: 'textfield',  
    items: [
        {
            allowBlank: false,
            fieldLabel: '用户名',
            id: 'user',
            emptyText: '用户名'
        },
        {
            allowBlank: false,
            fieldLabel: '密码',
            id: 'pass',
            emptyText: '密码',
            inputType: 'password'
        },
        {
            xtype:'checkbox',
            fieldLabel: '记住我',
            name: 'remember'
        }
    ],
    
    buttons: [
        { 
          text:'登录',
          listeners:{
        	  click: function(){
        		  var me = this;
        		  Ext.Ajax.request({
        			  url: 'testing/login',
        			  params:JSON.stringify({
        				  userName: Ext.getCmp('user').getValue(),
        				  password: Ext.getCmp('pass').getValue()
        			  }),
        			  method: 'POST',
        			  success: function(response){
        				  var result = JSON.parse(response.responseText);
        				  if(!result.success)
        			      {
        					   Ext.MessageBox.alert("错误","用户名或密码错误!");  
        				  }else{
        					  Ext.get('login').destroy();
        					  Ext.require('Tool.ModuleFrame', function(){
        						  var moduleFrame = Ext.create('Tool.ModuleFrame', {
        							  renderTo: 'moduleFrame',
       						          region: 'center'
        						  });
        						  moduleFrame.show();
        					  });
        				  }
        			  },
        			  failure: function(response){
        				  Ext.MessageBox.alert("错误","登录失败！");
        			  }
        		  });
        	  }
          }
        },
        { text:'注册' }
    ],
    
    initComponent: function() {
        this.defaults = {
            anchor: '100%',
            labelWidth: 120
        };
        this.callParent();
    }
});