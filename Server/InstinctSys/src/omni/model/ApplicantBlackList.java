package omni.model;

import omni.database.catfish.object.BlackListObject;
import omni.model.type.BlackListType;
import util.EncryptUtil;

public class ApplicantBlackList extends Applicant 
{
	private String idNumber = null;		
	private String qqNumber = null;		
	private String weiXinUserId = null;	
	private String idName = null;		
	private String mobile = null;
	private String reason = null;		
	
	public ApplicantBlackList(BlackListObject blackListObj)
	{
		if (blackListObj != null)
		{
			switch (BlackListType.forValue(blackListObj.type))
			{
				case Phone:	
					this.mobile = blackListObj.content;	
					break;
				case QQ:	
					this.qqNumber = blackListObj.content;	
					break;
				case Weixin:	
					this.weiXinUserId = blackListObj.content;	
					break;
				case IdNumber:
					this.idNumber = blackListObj.content;	
					break;
				default:	
					break;
			}
			this.idName = blackListObj.name;
			this.reason = blackListObj.reason;
		}
	}	
	
	@Override
	public final String getIdNumber()
	{
		return EncryptUtil.getDecryptedString(this.idNumber, "DES");
	}
	
	@Override
	public final String getQQNumber()
	{
		return EncryptUtil.getDecryptedString(this.qqNumber, "DES");
	}
	
	@Override
	public final String getWeiXinUserId()
	{
		return EncryptUtil.getDecryptedString(this.weiXinUserId, "DES");
	}
	
	@Override
	public final String getIdName()
	{
		return EncryptUtil.getDecryptedString(this.idName, "DES");
	}

	@Override
	public final String getMobile()
	{
		return EncryptUtil.getDecryptedString(this.mobile, "DES");
	}
	
	public final String getReason()
	{
		return this.reason;
	}

}
