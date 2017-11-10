package engine.rule.domain;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum BQSFinalDecision implements CatfishEnum<String> {
	  
	  @Description(text = "无结果或异常结果")
	  NoneRecord(""),
	  

	  @Description(text = "通过， 无匹配记录")
	  Accept("Accept"),

	  @Description(text = "拒绝， 高风险黑名单有击中， 建议拒绝")
	  Reject("Reject"),
	  
	  @Description(text = "审核， 低风险灰名单有击中， 建议人工核实")
	  Review("Review");
	  
	  private String value;

	  private BQSFinalDecision(String value) {
	    this.value = value;
	  }

	  @Override
	  public String getValue() {
	    return this.value;
	  }

	}

