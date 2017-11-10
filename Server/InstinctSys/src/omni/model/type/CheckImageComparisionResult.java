package omni.model.type;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

/**
 * 外包头像对比三张照片是否一致
 * @author baowzh
 *
 */
public enum CheckImageComparisionResult implements CatfishEnum<Integer> 
{
	@Description(text = "一致，同一人")
	Consistent(10),

	@Description(text = "不一致")
	InConsistent(20),
	
	@Description(text = "身份证照片不一致")
	IdPhotoInConsistent(30),
    
    @Description(text = "现场照片不一致")
	LivePhotoInConsistent(50),
	
	@Description(text = "公安网照片不一致")
	PolicePhotoInConsistent(60),
	
	@Description(text = "三者均不一致")
	AllInConsistent(70);
    
	@Override
	public Integer getValue() 
	{
		return value;
	}
	private int value;

	CheckImageComparisionResult(int val)
	{
        this.value = val;
    }
}
