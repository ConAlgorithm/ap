package omni.database.catfish.object;

import java.util.Date;

import catfish.base.ForeignKey;
import catfish.base.business.object.BaseObject;

public class EndUserOutOfTouchRecordObject extends BaseObject 
{
  
    @ForeignKey("omni.database.catfish.object.EndUserObject")
    public String endUserId;
  
	public String idName;

	public String idNumber;

	public Boolean isOutOfTouch;

	public Date markTime;

	public String collectorId;

	public String collectorName;

}
