package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum RejectedType implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(-99),
    
	@Description(text = "A_正常拒绝_正常拒绝")
    Normal(1),

    @Description(text = "B_集中度控制_同单位客户集中")
    CustomerCompanyConcentration(200),

    @Description(text = "B_集中度控制_同住址客户集中")
    CustomerAddressConcentration(201),

    @Description(text = "C_不良用户_用户涉嫌欺诈")
    PossibleFraud(300),

    @Description(text = "C_不良用户_征信过度")
    ExcessiveCredit(301),

    @Description(text = "C_不良用户_征信不良")
    BadCredit(302),

    @Description(text = "D_进件不合格_年龄<=18")
    AgeLessThanEighteen(400),

    @Description(text = "D_进件不合格_无法提供身份证号")
    NoIdNumberProvided(401),

    @Description(text = "D_进件不合格_无行为能力")
    Incapacitated(402),

    @Description(text = "D_进件不合格_非本人身份证")
    OtherPeopleIdCard(403),

    @Description(text = "D_进件不合格_无法提供身份证")
    IdCardUnavailable(404),

    @Description(text = "D_进件不合格_身份证无法辨认")
    IdCardUnidentified(405),

    @Description(text = "D_进件不合格_姓名有误")
    NameError(406),

    @Description(text = "D_进件不合格_不能提供有效银行卡")
    BankCardUnavailable(407),

    @Description(text = "D_进件不合格_证件号有误")
    WrongCertificatingNumber(408),

    @Description(text = "D_进件不合格_学生")
    Student(409),

    @Description(text = "D_进件不合格_年龄>=35")
    AgeOlderThanThirtyFive(410),

    @Description(text = "D_进件不合格_无职业")
    NoProfession(411),

    @Description(text = "D_进件不合格_特殊人群")
    SpecialCrowd(412),

    @Description(text = "D_进件不合格_离职/临时工")
    DimissionOrCasual(413),

    @Description(text = "D_进件不合格_特殊客户")
    SpecialCustomer(414),

    @Description(text = "D_进件不合格_非认可商品")
    InvalidMerchandise(415),

    @Description(text = "D_进件不合格_商品超量")
    OverWeightedMerchandise(416),

    @Description(text = "D_进件不合格_拒绝后申请")
    ReappliedAfterRejection(417),

    @Description(text = "D_进件不合格_现场照片无效")
    LivePhotoInvalid(418),

    @Description(text = "D_进件不合格_不配合处理")
    Mismatched(419),

    @Description(text = "D_进件不合格_无法联系")
    ContactsUnreachable(420),

    @Description(text = "D_进件不合格_联系人无效")
    ContactsInvalid(421),

    @Description(text = "D_进件不合格_无法提供有效联系人")
    NoValidContactsProvided(422),

    @Description(text = "D_进件不合格_重复申请")
    RepeatedApplication(423),

    @Description(text = "D_进件不合格_语音与宣誓文字意思相反或相悖")
    VoiceAgainstWord(424),

    @Description(text = "用户信息未通过规则引擎审核")
    RuleEngineCheckFailed(500),

    @Description(text = "暗访用户未通过规则引擎审核")
    SecretVisitorReject(501),
    
    @Description(text = "其他")
    Other(900);
    
    private int value;
    
    private static final Map<Integer, RejectedType> typesByValue = new HashMap<Integer, RejectedType>();

    static {
        for (RejectedType type : RejectedType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static RejectedType forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    RejectedType(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
