package thirdparty.ylzh.response;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum ResponseCode implements CatfishEnum<String>{

	@Description(text = "提交成功")
	SubmitSuccess("0000"),
	
	@Description(text = "提交失败")
	SubmitFailed("0002"),
	
	@Description(text = "该商户交易起止期内的交易笔数过大，超过输出条数限制，请选择单个商户编号查询，或缩短交易起止期")
	TradeOutOfBound("0003"),
	
	@Description(text = "账号不存在或被禁用")
	AcountNotExist("1000"),
	
	@Description(text = "没有此接口访问权限")
	AcessDenied("1001"),
	
	@Description(text = "请求的资源不存在")
	ResourceNotExit("1004"),
	
	@Description(text = "参数为空或格式错误")
	ParamError("1005"),
	
	@Description(text = "验签失败")
	SignCheckFailed("1006"),
	
	@Description(text = "没有查询到结果")
	EmptyResult("1007"),
	
	@Description(text = "重复的请求")
	RepeatingRequest("2000"),
	
	@Description(text = "请求的数据无效")
	DataInvalid("2001"),
	
	@Description(text = "银行卡未通过认证")
	CardNotCertified("2002"),
	
	@Description(text = "银行卡已通过认证")
	CardCertified("2003"),
	
	@Description(text = "银行卡正在认证监控")
	CardIsCertifying("2004"),
	
	@Description(text = "系统错误")
	ServerError("9999");
	
	private final String value;
    
    public String getValue()
    {
    	return this.value;
    }
    ResponseCode(String value)
    {
    	this.value = value;
    }
}
