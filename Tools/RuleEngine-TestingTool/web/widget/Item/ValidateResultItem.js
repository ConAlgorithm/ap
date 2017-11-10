Ext.require("Tool.Item.BaseItem");

Ext.define('Tool.Item.ValidateResultItem', {
    extend: 'Tool.Item.BaseItem',
    shadow: false,
    width: '98%',
    infoList: function(){
    	var list = [];
    	console.log(this.getData());
    	var me = this;
    	return list.concat(this.validateResult());
    },
    
    validateResult: function()
    {
    	var list = [];
    	var validate = this.getData();
    	list.push(this.content("line", "行号", validate.line));
    	list.push(this.content("expected", "期望输出", validate.expected));
    	list.push(this.content("real", "实际输出", validate.real));
    	return list;
    },
    
    onClick: function(e){
    	Ext.create('Tool.ValidateResultDetailWindow', {
    		detail: this.getData()
    	}).show();
    }
});