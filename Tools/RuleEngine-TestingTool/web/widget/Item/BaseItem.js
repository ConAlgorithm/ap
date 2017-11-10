Ext.define('Tool.Item.BaseItem', {
	config:{
		data:{},
		width: 400,
		shadow: true,
		hover: true,
		showBottom: true,
		showBorder: true,
		elm: null,
		onClick: null
	},
	statics:{
		getClickItem: function(e){
			var dom = e.target;
			while(dom != null && !$(dom).hasClass('contentBox')){
				dom = dom.parentNode;
			}
			return dom;
		},
		getClassify: function(dom){
			return $(dom).find('[tag=classify]').text();
		},
		getFieldBindDomain: function(dom){
			return $(dom).find('[tag=bindDomain]').text();
		},
		getMethodParamDomains: function(dom){
			return $(dom).find('[tag=paramDomains]').text();
		},
		getMethodReturnDomain: function(dom){
			return $(dom).find('[tag=returnDomain]').text();
		}
	},
	constructor: function(config){
		this.initConfig(config);
	},
    getEl: function(){
    	if(this.getElm() == null)
    	{
    		this.setElm(this.createEl());
    	}
    	return this.getElm();
    },
    createEl: function(){
    	var outer = $('<div class="contentBox shadow"></div>');
    	var inner = $('<div class="innerBox"></div>');
    	outer.css({
    	    width: this.getWidth()
    	});
    	if(!this.getShadow()){
    		outer.removeClass('shadow');
    	}
    	if(this.getHover()){
    		outer.addClass('itemHover');
    	}
    	if(this.getShowBorder()){
    		outer.addClass('showBorder');
    	}
    	
    	var me = this;
    	$.each(this.infoList(), function(index, value){
    		var elm = $(value);
    		if(me.getShowBottom()){
        		elm.addClass('showBottom');
        	}
    		inner.append(elm);
    	});
    	outer.append(inner);
    	return outer;
    },
    content: function(tag, label, value)
    {
    	var dom = '<div class="item-line">'+
    	              '<div style="float:left; width:20%">{label}</div>'+
    	              '<div tag="{tag}" style="float:left; width:80%;">{value}</div>'+
    	          '</div>';
    	var elm = $(dom.replace('{tag}', tag).replace('{label}', label).replace('{value}', value == undefined ? "" : value));   
    	if(this.onClick)
    	{
    		var me = this;
    		elm.click(function(e){
    			me.onClick.apply(me, e);
    		})
    	}
    	return elm;
    },
});