package omni.model.type;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

/**
 * 客户照片判断（是否为同一个人）
 * @author baowzh
 *
 */
public enum CheckWhetherCustomerPhotoIsTheSamePersonResult implements CatfishEnum<Integer> 
{
	@Description(text = "是")
	Yes(10),

	@Description(text = "否")
	No(20),
    
    @Description(text = "无法判断")
    None(30);
    
	@Override
	public Integer getValue() 
	{
		return value;
	}
	private int value;

	CheckWhetherCustomerPhotoIsTheSamePersonResult(int val)
	{
        this.value = val;
    }
}
