package jma;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum JobHandlerSwitch implements CatfishEnum<String>{

	@Description(text = "打开")
	On("ON"),
	
	@Description(text = "关闭")
	Off("OFF");
	
    private final String value;
    
    public String getValue()
    {
    	return this.value;
    }
    JobHandlerSwitch(String value)
    {
    	this.value = value;
    }
}
