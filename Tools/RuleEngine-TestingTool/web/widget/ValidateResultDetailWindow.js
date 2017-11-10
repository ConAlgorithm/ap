Ext.define('Tool.ValidateResultDetailWindow', {
	id: 'validateResultDetailWindow',
    extend: 'Ext.window.Window',
    xtype: 'validateResultDetail-window',   
    width: 600,
    height: 600,
    modal: true,
    resizable: false,
    detail: null,
    frame: true,
    title: '测试结果详情',
    defaultType: 'panel',
    layout: 'anchor',
    listeners: {
    	show: function(){
    		var me = this;
    		$(me.items.getAt(0).getEl().query('[class=detail-line]')[0]).html(this.detail.line);
    		$(me.items.getAt(1).getEl().query('[class=detail-expected]')[0]).html(this.detail.expected);
    		$(me.items.getAt(2).getEl().query('[class=detail-real]')[0]).html(this.detail.real);
    		$(me.items.getAt(3).getEl().query('[class=detail-debug]')[0]).html(this.detail.debug);
    	}
    },
    items:[
       {
    	   anchor: '100% 3%',
    	   html: '<div style="float:left; width:30%;">行号：</div><div class="detail-line"></div>'
       },
       {
    	   overflowY: 'auto',
    	   anchor: '100% 20%',
    	   html: '<div style="width:100%;">期望输出：</div><div class="detail-expected"></div>'
       },
       {
    	   overflowY: 'auto',
    	   anchor: '100% 17%',
    	   html: '<div style="width:100%;">实际输出：</div><div class="detail-real"></div>'
       },
       {
    	   overflowY: 'auto',
    	   anchor: '100% 60%',
    	   html: '<div style="width:100%;">调试信息：</div><div class="detail-debug"></div>'
       }
    ]
});