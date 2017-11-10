Ext.require("Tool.Item.BaseItem");

Ext.define('Tool.Item.ExecuteResultItem', {
    extend: 'Tool.Item.BaseItem',
    shadow: false,
    showBottom: false,
    showBorder: false,
    hover: false,
    width: '100%',
    infoList: function(){
    	var list = [];
    	console.log(this.getData());
    	var data = this.getData();
    	var execute = (data.execute ? data.execute : {});
        list.push(this.content(execute));
    	return list;
    },
    content: function(execute){
    	var dom = '<div style="width:100%; text-align:center">\
					<div class="executeResult">\
					     <div class="label">总案例数:</div>\
						 <div tag= "total" class="value">{total}</div>\
					</div>\
					<div class="executeResult">\
					     <div class="label">已运行:</div>\
						 <div tag = "executed" class="value" style="color:#3300FF">{executed}</div>\
					</div>\
					<div class="executeResult">\
					     <div class="label">成功:</div>\
						 <div tag="succeed" class="value" style="color:#00FF00">{succeed}</div>\
					</div>\
					<div class="executeResult">\
					     <div class="label">失败:</div>\
						 <div tag= "fail" class="value" style="color:#FF0000">{fail}(最多显示20条)</div>\
					</div>\
				</div>';
    	return dom.replace("{total}", execute.total || 0).replace("{executed}", execute.executed || 0).replace("{succeed}", execute.succeed || 0).replace("{fail}", execute.fail || 0);
    },
    
    refresh: function(data){
    	this.getElm().find('[tag=total]').html(data.total);
    	this.getElm().find('[tag=executed]').html(data.executed);
    	this.getElm().find('[tag=succeed]').html(data.succeed);
    	this.getElm().find('[tag=fail]').html(data.fail);
    }
});