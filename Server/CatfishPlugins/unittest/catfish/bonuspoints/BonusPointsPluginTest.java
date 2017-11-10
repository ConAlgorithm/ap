//package catfish.bonuspoints;
//
//import static org.junit.Assert.*;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import catfish.base.business.object.BonusPointsObject;
//import catfish.base.business.object.BonusPointsRuleObject;
//import catfish.base.queue.QueueMessager;
//import catfish.bonuspoints.rank.RankService;
//import catfish.bonuspoints.rules.IRuleProvider;
//import catfish.framework.IMessageService;
//import catfish.framework.queue.IQueueService;
//import catfish.services.ServiceProvider;
//import catfish.test.mocks.MockQueueService;
//
//public class BonusPointsPluginTest {
//	static final String TestMessage = "testMessage";
//	static final String TestAppId = "436CF5DB-55C1-E411-B762-005056C00008";
//
//	private ServiceProvider serviceProvider;
//	private IQueueService queueServcie;
//
//	@Before
//	public void setUp() throws Exception {
//		serviceProvider = new ServiceProvider();
//		queueServcie = new MockQueueService();
//		serviceProvider.register(IQueueService.class, queueServcie);
//	}
//
//	@Test
//	public void testRule() {
//		MockDataService mds = new MockDataService();
//		MockRuleProvider ruleProvider = new MockRuleProvider();
//		MockRankService mrs = new MockRankService();
//		BonusPointsPlugin plugin = new BonusPointsPlugin(ruleProvider, mds, mrs);
//		plugin.init(serviceProvider);
//
//		QueueMessager qm = new QueueMessager(TestAppId, TestMessage);
//		queueServcie.getQueue("test").postMessage(TestMessage, qm.toString());
//
//		assertEquals(2, mds.pointList.size());
//		BonusPointsObject point1 = mds.pointList.get(0);
//		BonusPointsObject point2 = mds.pointList.get(1);
//		assertEquals(point1.Point, 1500);
//		assertEquals(point2.Point, 1500);
//	}
//
//}
//
//class MockRule extends BasicRule{
//	public MockRule(){
//		this.rule = new BonusPointsRuleObject();
//		this.rule.Id = "mockId";
//	}
//	@Override
//	protected int calculatePoint(String appId){
//		return 1500;
//	}
//}
//
//class MockBonusRule extends BonusRule{
//	public MockBonusRule(){
//		this.rule = new BonusPointsRuleObject();
//		this.rule.Id = "mockBonusRuleId";
//	}
//	@Override
//	protected int calculateAddedPoint(int point, String appId){
//		return point;
//	}
//}
//
//class MockRuleProvider implements IRuleProvider{
//	public BasicRule getBasicRule(String key){
//		if(BonusPointsPluginTest.TestMessage.equals(key)){
//			return new MockRule();
//		} else {
//			return null;
//		}
//	}
//
//	public BonusRule[] getBonusRules(){
//		return new BonusRule[]{
//				new MockBonusRule()
//		} ;
//	}
//}
//
//class MockDataService extends DataService{
//	public List<BonusPointsObject> pointList;
//
//	@Override
//	public boolean savePoint(List<BonusPointsObject> pointList){
//		this.pointList = pointList;
//		return true;
//	}
//
//	@Override
//	public String getS1Id(String appId){
//		return "s1Id";
//	}
//}
//
//class MockRankService extends RankService {
//	//TODO: write mock service
//}
