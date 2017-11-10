Ext.require("Tool.Item.BaseItem");

Ext.define('Tool.Item.DomainItem', {
    extend: 'Tool.Item.BaseItem',
    shadow: false,
    infoList: function(){
    	var list = [];
    	console.log(this.getData());
    	var me = this;
    	list.push(this.header(this.getData().name));
    	list.push(this.content("字段名", "字段值"));
    	$.each(this.getData().domain, function(index, item){
    		list.push(me.content(item.description, item.value));
    	})
    	return list;
    },
    content: function(label, value)
    {
    	var dom = '<div class="item-line">'+
    	              '<div style="float:left; width:50%">{label}</div>'+
    	              '<div style="float:left; width:50%;">{value}</div>'+
    	          '</div>';
    	return dom.replace('{label}', label).replace('{value}', value == undefined ? "" : value);
    },
    header: function(domainName)
    {
    	var dom = '<div class="item-line" style="font-size:1.5em">{domain}</div>';
    	return dom.replace('{domain}', domainName);
    }
});