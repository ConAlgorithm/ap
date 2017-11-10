package omni.database.catfish.object;

import catfish.base.ForeignKey;
import catfish.base.business.object.BaseObject;

public class RuleEngineResultObject extends BaseObject 
{
  
	@ForeignKey("catfish.base.business.object.InstallmentApplicationObjects")
    public String appId;

	public Boolean isPassed;
	
    public Float score;

    public String decisionJobName;

    public String execSequence;

}
