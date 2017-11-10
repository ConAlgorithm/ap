package thirdparty.jxl;

import catfish.base.business.common.CatfishEnum;

public enum ReportStatus implements CatfishEnum<String>{
	
	NotStarted("未开始"),
	
	InCrawling("抓取中"),
	
	Finished("完成");

	private String value;
	
	private ReportStatus(String value)
	{
		this.value = value;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
