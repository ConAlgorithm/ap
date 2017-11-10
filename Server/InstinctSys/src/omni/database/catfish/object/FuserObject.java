package omni.database.catfish.object;

import java.util.Date;

import catfish.base.ForeignKey;
import catfish.base.business.object.BaseObject;

public class FuserObject extends BaseObject 
{
  
	public String idName;
  
	public Date realNameFilledOn;

	public String idNumber;

	public Integer role;
	
	public Integer status;
	
	public Date deletedOn;
	
	public String comments;
	
	@ForeignKey("catfish.base.business.object.UserObjects")
	public String userId;
	
	public String roleId;
	
}
