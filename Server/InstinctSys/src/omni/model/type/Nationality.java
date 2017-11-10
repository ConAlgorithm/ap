package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum Nationality implements CatfishEnum<String>
{
    
	@Description(text = "汉族")
	HanZu("11"),
    
    @Description(text = "蒙古族")
	MengGuZu("12"),
	
    @Description(text = "回族")
	HuiZu("13"),

    @Description(text = "藏族")
	ZangZu("14"),

    @Description(text = "维吾尔族")
	WeiWuErZu("15"),

    @Description(text = "苗族")
	MiaoZu("16"),

    @Description(text = "彝族")
	YiZu("17"),

    @Description(text = "壮族（僮族）")
	ZhuangZu("18"),

    @Description(text = "满族")
	ManZu("19"),

    @Description(text = "朝鲜族")
	ChaoxianZu("21"),
	
    @Description(text = "达斡尔族")
	DaWoErZu("22"),

    @Description(text = "鄂温克族")
	EWenKeZu("23"),

	@Description(text = "鄂伦春族")
	ELunChunZu("24"),

	@Description(text = "赫哲族")
	HeZheZu("25"),

	@Description(text = "土族")
	TuZu("31"),

	@Description(text = "撒拉族")
	SaLaZu("32"),

	@Description(text = "东乡族")
	DongXiangZu("33"),

	@Description(text = "保安族")
	BaoAnZu("34"),

	@Description(text = "裕固族")
	YuGuZu("35"),

	@Description(text = "哈萨克族")
	HaSaKeZu("36"),

	@Description(text = "柯尔克孜族")
	KeErKeZiZu("37"),

	@Description(text = "乌孜别克族")
	WuZiBieKeZu("38"),

	@Description(text = "塔吉克族")
	TaJiKeZu("41"),

	@Description(text = "塔塔尔族")
	TaTaErZu("42"),

	@Description(text = "锡伯族")
	XiBoZu("43"),

	@Description(text = "俄罗斯族")
	ELuoSiZu("44"),

	@Description(text = "瑶族")
	YaoZu("51"),

	@Description(text = "白族（民家）")
	BaiZu_MinJia("52"),

	@Description(text = "傣族")
	DaiZu("53"),

	@Description(text = "哈尼族")
	HaNiZu("54"),

	@Description(text = "佤族")
	WaZu("55"),

	@Description(text = "傈傈族")
	LiLiZu("56"),

	@Description(text = "纳西族（么些）")
	NaXiZu_MeXie("57"),

	@Description(text = "拉祜族（倮黑）")
	LaHuZu_LuoHei("58"),

	@Description(text = "景颇族")
	JingPoZu("59"),

	@Description(text = "布朗族（濮曼）")
	BuLangZu_PuMan("61"),

	@Description(text = "阿昌族")
	AChangZu("62"),

	@Description(text = "怒族")
	NuZu("63"),

	@Description(text = "德昂族（崩龙）")
	DeAngZu_BengLong("64"),

	@Description(text = "独龙族")
	DuLongZu("65"),

	@Description(text = "普米族")
	PuMiZu("66"),

	@Description(text = "门巴族")
	MenBaZu("67"),

	@Description(text = "布依族（仲家）")
	BuYiZu_ZhongJia("68"),

	@Description(text = "水族（水家）")
	ShuiZu_ShuiJia("69"),

	@Description(text = "仡佬族")
	GeLaoZu("71"),

	@Description(text = "侗族")
	DongZu("72"),

	@Description(text = "土家族")
	TuJiaZu("73"),

	@Description(text = "羌族")
	QiangZu("74"),

	@Description(text = "仫佬族")
	MuLaoZu("75"),

	@Description(text = "毛南族（毛难）")
	MaoNanZu("76"),

	@Description(text = "珞巴族")
	LuoBaZu("77"),

	@Description(text = "基诺族")
	JiNuoZu("78"),

	@Description(text = "黎族")
	LiZu("81"),

	@Description(text = "京族")
	JingZu("82"),

	@Description(text = "畲族")
	SheZu("83"),

	@Description(text = "高山族")
	GaoShanZu("84"),

	@Description(text = "苦聪人")
	KuCongRen("85"),

	@Description(text = "登人")
	DengRen("86"),

	@Description(text = "穿青人")
	ChuanQingRen("87");
	
    private final String value;
    
    private static final Map<String, Nationality> typesByValue = new HashMap<String, Nationality>();

    static {
        for (Nationality type : Nationality.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static Nationality forValue(String value) 
    {
        return typesByValue.get(value);
    }
    
    public String getValue()
    {
    	return this.value;
    }
    
    Nationality(String val)
    {
    	this.value = val;
    }
}
