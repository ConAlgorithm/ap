package engine.rule.domain;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum KaolaOverdueLevel implements CatfishEnum<String> {
	  
	  @Description(text = "无结果或异常结果")
	  NoneRecord(""),
	  
	  @Description(text = "M1")
	  M1("M1"),

	  @Description(text = "M2")
	  M2("M2"),
	  
	  @Description(text = "M3")
	  M3("M3"),
	  
	  @Description(text = "M4")
	  M4("M4"),
	  
	  @Description(text = "M5")
	  M5("M5"),
	  
	  @Description(text = "M6")
	  M6("M6"),
	  
	  @Description(text = "M6+")
	  M6Plus("M6+")
	  ;
	  
	  private String value;

	  private KaolaOverdueLevel(String value) {
	    this.value = value;
	  }

	  @Override
	  public String getValue() {
	    return this.value;
	  }

	}

