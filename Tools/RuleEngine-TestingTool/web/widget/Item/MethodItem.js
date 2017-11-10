Ext.require("Tool.Item.BaseItem");

Ext.define('Tool.Item.MethodItem', {
    extend: 'Tool.Item.BaseItem',
    infoList: function(){
    	var list = [];
    	list.push(this.content("classify", "类别", this.getData().classify));
    	list.push(this.content("name", "名称", this.getData().name));
    	list.push(this.content("rule", "规则", this.getData().rule));
    	list.push(this.content("paramTypes", "参数类型", this.getData().paramTypes));
    	list.push(this.content("paramDomains", "参数绑定域", this.getData().paramDomains))
    	list.push(this.content("returnType", "返回值类型", this.getData().returnType));
    	list.push(this.content("returnDomain", "返回值绑定域", this.getData().returnDomain));
    	return list;
    }
});