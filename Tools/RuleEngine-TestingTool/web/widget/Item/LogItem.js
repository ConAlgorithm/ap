Ext.require("Tool.Item.BaseItem");

Ext.define('Tool.Item.LogItem', {
    extend: 'Tool.Item.BaseItem',
    shadow: false,
    width: '98%',
    infoList: function(){
    	var list = [];
    	console.log(this.getData());
    	var me = this;
    	list.push(this.content('info',"信息", this.getData().info));
    	list.push(this.content('warn', "警告", this.getData().warn));
    	list.push(this.content('error', "错误", this.getData().error));
    	return list;
    }
});