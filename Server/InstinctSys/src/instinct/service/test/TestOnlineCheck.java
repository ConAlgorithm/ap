package instinct.service.test;

import catfish.base.StartupConfig;
import instinct.service.client.OnlineFraudCheck;
import instinct.service.client.OnlineFraudCheckSoap;


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
		String testapp = "OMN|CN|Null|NGVRSDQICNM98RNN0HEKXPM4|16/02/2015|15/02/2015|PSL|2800.0|||||MobileCredit|苹果 iPhone 5S|||||Started||D9D29A6D-F2B4-E411-98E3-AC853DA49BEC|||A|320683199310242558||oiUFft7xWuK1usOgn3pkv8Ulc1aU|周晨星|周晨星||||||15806282543||||||TechnicalSecondarySchool|||Yes||||HanZu|||U|||||||||||||||||||O|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|None|Error||EmptyData|EmptyData|EmptyData|||||||||||||W||||||||||||||||B|||徐林林|孙月霞|赵宝航||||13773883312||0.7799999713897705078125||||||3287D7E6-BB7F-E411-98E3-AC853DA49BEC|F8A53ED4-54A2-E411-98E3-AC853DA49BEC|9FC14069-98A6-E411-98E3-AC853DA49BEC|通州区隆欣手机连锁通州苹果店|K||false|false|false|false|||||||||||||None|None|||2|1|http://wx.qlogo.cn/mmopen/Q3auHgzwzM7muiaFXb4QEXV5ozf3MGnkLG1PFoALvChHVryhiceZPy0c1hALStZePGoz75bmbpoJib0PT6WbTJhibg/0";
		String response = portType.instinctFraudCheckString(testapp);
		System.out.println(response);
//		System.out.println(new OnlineFraudCheck().toString());
	}
}
