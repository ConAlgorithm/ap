package catfish.bonuspoints;

import java.util.*;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.queue.QueueMapMessager;
import catfish.base.queue.QueueMessages;
import catfish.bonuspoints.rank.RankListener;
import catfish.bonuspoints.rank.RankService;
import catfish.bonuspoints.rules.IRuleProvider;
import catfish.bonuspoints.rules.RuleHandler;
import catfish.bonuspoints.rules.RuleProvider;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.framework.http.IHTTPService;
import catfish.base.business.common.BonusPointSourceType;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.*;

public class BonusPointsPlugin implements IPlugin,  IQueueListener{
	private static final String CATFISH_SERVER_QUEUE = "CatfishServerQueue";
	private static final Map<String, Integer> RuleKeyToPointSourceTypeMap = CollectionUtils.mapOf(
	    "ApplicationSubmit", BonusPointSourceType.APPLICATION_SUBMIT,
	    "ApplicationComplete", BonusPointSourceType.APPLICATION_COMPLETE);

	private IRuleProvider ruleProvider;
	private DataService dataService;
	private RankService rankService;

	public BonusPointsPlugin(){
		this(new RuleProvider(), new DataService(), new RankService());
	}

	public BonusPointsPlugin(IRuleProvider ruleProvider, DataService dataService, RankService rankService){
		this.ruleProvider = ruleProvider;
		this.dataService = dataService;
		this.rankService = rankService;
	}

	@Override
	public void init(IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue queue = queueService.getQueue(CATFISH_SERVER_QUEUE);
	    queue.register(QueueMessages.APPLICATION_COMPLETED, this);
	    queue.register(QueueMessages.APPLICATION_SUBMITTED, this);
	    IHTTPService restService = sp.getService(IHTTPService.class);
	    restService.register("rank", new RankListener(rankService));
	}

	@Override
	public void onMessage(String message, String data) {
		QueueMapMessager dataMap = QueueMapMessager.fromString(data);
		String appId = dataMap.getAsString("appId");
	    if (dataMap == null || appId == null) {
	        return;
	    }

    	InstallmentApplicationDao appDao = new InstallmentApplicationDao(appId);
    	InstallmentApplicationObject app = appDao.getSingle();
    	if(app.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue()) {
    		Logger.get().info("PaydayLoan app, bonuspoints not created for appId : "+appId);
    		return;
    	}

	    List<BonusPointsObject> pointList = new ArrayList<BonusPointsObject>();
	    List<BonusPointsRuleObject> rules = ruleProvider.getRules(message);
	    if (rules == null || rules.isEmpty()) {
	      Logger.get().info(String.format("appId:%s, no bonus point rule matches.", appId));
	      return;
	    }
	    rules.forEach(rule -> {
	      int point = new RuleHandler().getPoint(rule, dataMap);
	      if (point == 0) {
	        return;
	      }

	      BonusPointsObject pointObject = new BonusPointsObject();
          pointObject.SourceId = appId;
          pointObject.SourceType = RuleKeyToPointSourceTypeMap.get(rule.Key);
          pointObject.Point = point;
          pointObject.RuleId = rule.Id;
          pointObject.UserId = dataService.getS1Id(appId);
          pointObject.SelfRefId = null;
          pointList.add(pointObject);
	    });
	    dataService.savePoint(pointList);
	}
}
