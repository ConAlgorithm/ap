package engine.rule.domain.app.consts;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum PicReuploadReason implements CatfishEnum<String> {
	
	//身份证
	@Description(text = "身份证图片姓名/号码与填写信息不一致")
	IdPhoto_ReuploadReason1("身份证图片姓名/号码与填写信息不一致"),
    @Description(text = "身份证图片拍摄模糊")
	IdPhoto_ReuploadReason2("身份证图片拍摄模糊"),
    @Description(text = "身份证图片有效信息未拍摄完整或者被遮挡")
	IdPhoto_ReuploadReason3("身份证图片有效信息未拍摄完整或者被遮挡"),
    @Description(text = "身份证图片姓名不清晰")
	IdPhoto_ReuploadReason4("身份证图片姓名不清晰"),
    @Description(text = "身份证图片号码不清晰")
	IdPhoto_ReuploadReason5("身份证图片号码不清晰"),
	@Description(text = "身份证图片人脸不清晰")
	IdPhoto_ReuploadReason6("身份证图片人脸不清晰"),
	@Description(text = "身份证图片性别不清晰")
	IdPhoto_ReuploadReason7("身份证图片性别不清晰"),
    
	//银行卡
    @Description(text = "银行卡图片不是银行卡或借记卡")
	BankPhoto_ReuploadReason1("银行卡图片不是银行卡或借记卡"),
    @Description(text = "银行卡图片拍摄模糊")
	BankPhoto_ReuploadReason2("银行卡图片拍摄模糊"),
	@Description(text = "银行卡图片有效信息未拍摄完整或者被遮挡")
	BankPhoto_ReuploadReason3("银行卡图片有效信息未拍摄完整或者被遮挡"),
	@Description(text = "银行卡图片卡号不清晰")
	BankPhoto_ReuploadReason4("银行卡图片卡号不清晰"),
	@Description(text = "银行卡图片开户行不清晰")
	BankPhoto_ReuploadReason5("银行卡图片开户行不清晰"),
	@Description(text = "银行卡图片卡号与录入卡号不一致")
	BankPhoto_ReuploadReason6("银行卡图片卡号与录入卡号不一致"),
	@Description(text = "银行卡图片开户行与录入开户行不一致")
	BankPhoto_ReuploadReason7("银行卡：开户行与录入开户行不一致"),
	@Description(text = "银行卡图片姓名/性别与填写不一致")
	BankPhoto_ReuploadReason8("银行卡图片姓名/性别与填写不一致"),
	
	//现场照
	@Description(text = "现场图片拍摄模糊")
	HeadPhoto_ReuploadReason1("现场图片拍摄模糊"),
	@Description(text = "现场图片五官未拍摄完整或者被遮挡")
	HeadPhoto_ReuploadReason2("现场图片五官未拍摄完整或者被遮挡"),
    
    //与工作人员合影
	@Description(text = "与工作人员合影图片不是合影")
	D1GroupPhoto_ReuploadReason1("与工作人员合影图片不是合影"),
	@Description(text = "与工作人员合影图片拍摄模糊")
	D1GroupPhoto_ReuploadReason2("与工作人员合影图片拍摄模糊"),
	@Description(text = "与工作人员合影图片五官未拍摄完整或者被遮挡")
	D1GroupPhoto_ReuploadReason3("与工作人员合影图片五官未拍摄完整或者被遮挡"),
    
	//电子借条
	@Description(text = "电子借条图片中无电子借条和人像")
	IOU_ReuploadReason1("电子借条图片中无电子借条和人像"),
	@Description(text = "电子借条图片中无人像")
	IOU_ReuploadReason2("电子借条图片中无人像"),
	@Description(text = "电子借条图片中无手机电子借条")
	IOU_ReuploadReason3("电子借条图片中无手机电子借条"),
	@Description(text = "电子借条图片拍摄模糊")
	IOU_ReuploadReason4("电子借条图片拍摄模糊"),
	@Description(text = "电子借条图片中五官未拍摄完整或者被遮挡")
	IOU_ReuploadReason5("电子借条图片中五官未拍摄完整或者被遮挡"),
	@Description(text = "电子借条图片中电子借条被双手遮挡")
	IOU_ReuploadReason6("电子借条图片中电子借条被双手遮挡"),
	@Description(text = "电子借条图片中人像或者电子借条未拍摄完整")
	IOU_ReuploadReason7("电子借条图片中人像或者电子借条未拍摄完整"),
	@Description(text = "电子借条图片中人像不是本人")
	IOU_ReuploadReason8("电子借条图片中人像不是本人"),
	@Description(text = "电子借条图片不是现在拍摄的")
	IOU_ReuploadReason9("电子借条图片不是现在拍摄的"),
	@Description(text = "电子借条图片姓名不清晰")
	IOU_ReuploadReason10("电子借条图片姓名不清晰"),
	@Description(text = "电子借条图片借款金额/月还款金额不清晰")
	IOU_ReuploadReason11("电子借条图片借款金额/月还款金额不清晰"),
	@Description(text = "电子借条图片验证码不清晰")
	IOU_ReuploadReason12("电子借条图片验证码不清晰"),
	@Description(text = "电子借条图片姓名不一致")
	IOU_ReuploadReason13("电子借条图片姓名不一致"),
	@Description(text = "电子借条图片借款金额/月还款金额不一致")
	IOU_ReuploadReason14("电子借条图片借款金额/月还款金额不一致"),
	@Description(text = "电子借条图片验证码不一致")
	IOU_ReuploadReason15("电子借条图片验证码不一致"),
	@Description(text = "电子借条图片还款期数不清晰")
	IOU_ReuploadReason16("电子借条图片还款期数不清晰"),
	@Description(text = "电子借条图片还款期数不一致")
	IOU_ReuploadReason17("电子借条图片还款期数不一致");
		
	PicReuploadReason(String value){
		this.value = value;
	}
	
	public String getValue()
    {
        return this.value;
    }
	
	private String value;

	

}
