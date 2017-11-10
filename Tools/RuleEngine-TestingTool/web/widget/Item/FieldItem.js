Ext.require("Tool.Item.BaseItem");

Ext.define('Tool.Item.FieldItem', {
	extend: 'Tool.Item.BaseItem',
	infoList: function(){
    	var list = [];
    	list.push(this.content("classify", "类别", this.getData().classify));
    	list.push(this.content("name", "名称", this.getData().name));
    	list.push(this.content("rule", "规则", this.getData().rule));
    	list.push(this.content("type", "类型", this.getData().type));
    	list.push(this.content("bindDomain", "绑定域", this.getData().bindDomain));
    	list.push(this.content("readMethod", "读方法", this.getData().readMethod));
    	list.push(this.content("writeMethod", "写方法", this.getData().writeMethod));
    	return list;
    }
});