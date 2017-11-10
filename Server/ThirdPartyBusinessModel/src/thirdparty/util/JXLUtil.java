package thirdparty.util;

import catfish.base.business.common.jxl.CheckPointResult;

public class JXLUtil {

	public static CheckPointResult GetCheckPointResult(String src)
	{
		if(src == null)
			return CheckPointResult.NoData;
		if(src.equals("是") || src.equals("出现"))
			return CheckPointResult.Yes;
		if(src.equals("未出现") || src.equals("否"))
			return CheckPointResult.No;
		//TODO 其他情况暂定为无数据
		return CheckPointResult.NoData;
	}
}
