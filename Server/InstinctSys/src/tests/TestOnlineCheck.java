package tests;

import catfish.base.StartupConfig;
import instinct.service.client.OnlineFraudCheck;
import instinct.service.client.OnlineFraudCheckSoap;
import instinct.service.model.InstinctResult;

public final class TestOnlineCheck 
{
	private TestOnlineCheck()
	{
		
	}
	
	public static void main(String[] args)
	{
		StartupConfig.initialize();

		OnlineFraudCheck service = new OnlineFraudCheck();
		OnlineFraudCheckSoap portType = service.getOnlineFraudCheckSoap();
//		String testapp="OMN|CN|Null|bPk(vaWC5RGs2KyFPZ9VCA|16/02/2015|15/02/2015|PSL|2800.0|||||MobileCredit|ƻ�� iPhone 5S|||||Started||6CF93FBD-A582-E511-ACD8-AC853D9F5508|||A|320683199310242558||oiUFft7xWuK1usOgn3pkv8Ulc1aU|�ܳ���|�ܳ���||||||15806282543||||||TechnicalSecondarySchool|||Yes||||HanZu|||U|||||||||||||||||||O|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|Error||EmptyData|EmptyData|EmptyData|||||||||||||W||||||||||||||||B|||������|����ϼ|�Ա���||||13773883312||0.7799999713897705078125||||||3287D7E6-BB7F-E411-98E3-AC853DA49BEC|F8A53ED4-54A2-E411-98E3-AC853DA49BEC|9FC14069-98A6-E411-98E3-AC853DA49BEC|ͨ����¡���ֻ�����ͨ��ƻ����|K||false|false|false|false|||||||||||||None|None|||2|1|http://wx.qlogo.cn/mmopen/Q3auHgzwzM7muiaFXb4QEXV5ozf3MGnkLG1PFoALvChHVryhiceZPy0c1hALStZePGoz75bmbpoJib0PT6WbTJhibg/0";
//		String testapp="OMN|CN|Null|bPk(vaWC5RGs2KyFPZ9VCA|26/05/2016|25/05/2016|PDL|800|||||PaydayLoan||||||Canceled|Update|6CF93FBD-A582-E511-ACD8-AC853D9F5508|||||A|37132219920413751X|1649653731|o8gXSt3I-gUIl4D6WdlnFbKR2vco|֣�پ�|����APP|M|ɽ��ʡ|������|۰����|�˷�|18369905102|��������|||�ƶ�|WithParents|None|None|Single|No||�Ͳ�|0|HanZu|6221887990010211254||R|��Ƭ|Father||�Ͳ�|18369905103||||||||R|���Լ�|Spouse||�Ͳ�|18369905104||||||||U|2||||||||||||||||||O||||||||||||||||||||||||||||||||||||||W||||||||||||||||||||||||B|||�پ�|||���ɹ�|���ͺ�����|��Ͻ��|18369905102||0|||0.0322580645161290313627233672377769835293292999267578125|0.0322580645161290313627233672377769835293292999267578125|0.0322580645161290313627233672377769835293292999267578125|8C5FBC90-9412-E611-B04C-D89D67298EA4||||K|false|true|false|false|false|||||||||0|0|||||||||http://wx.qlogo.cn/mmopen/HWs5iclIb7vxaqYXB6JyBx4UeG1QW6c2XvOJUibqqhYWOtwejIp1CHPxicBZtW3Ujx9fYkrGqicfgfXbatNsibZxpnf8Lr6JTq4p1/0";
		String testapp = "OMN|CN|Null|FClai7TP4xGzSODbVRbVaA|30/04/2014|29/04/2014|PSL|2000|||||MobileCredit|苹果 iPhone 5S|||||Started|Update|14295A8B-B4CF-E311-B348-E0DB5516D568|||||A|接电话大黄|686646|oZD4EjwiLhzog3M3rFAKX5AmblSE|很多很多哈|买单侠客服003|M||||很多很多哈|13482231751||||移动|WithParents|None|||No||上海||HanZu|||U|||||||||||||||||||O||||||||||||||||||||||||||||||||||||||W||||||||||||||||||||||||B|||||||||||0.60000002384185791015625||||||||||K||false|false|false|false|||||||||||||||||||http://wx.qlogo.cn/mmopen/nr8hUicTwIV00UC3VM6NuktmqthrbzC3BPXrHf9ibOBMQnwjnGN41kGYPf5uic7sdwVXmGZAiczyFqUNK3o0qdQKDAxlzMqtiaAYI/0";
		String response = portType.instinctFraudCheckString(testapp);
		InstinctResult insRes = null;
		try
		{
			insRes = new InstinctResult(response);
			System.out.println(insRes);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(response);
		System.out.println(new OnlineFraudCheck().toString());
	}
}
