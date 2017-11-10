package catfish.flowcontroller.activities;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.flowcontroller.ActivityCreator;
import catfish.flowcontroller.activities.app.AppPhotoEvidenceCollectCheckActivityFactory;
import catfish.flowcontroller.activities.app.AppReCheckActivityFactory;
import catfish.flowcontroller.activities.app.LoanCheckActivityFactory;
import catfish.flowcontroller.activities.cashloan.CLSecurityCheckActivityFactory;
import catfish.flowcontroller.activities.paydayloan.PDLReCheckActivityFactory;
import catfish.flowcontroller.activities.paydayloan.PDLWorkEvidenceCollollectCheckActivityFactory;
import catfish.flowcontroller.activities.weixin.CheckAdditionalContactActivityFactory;
import catfish.flowcontroller.activities.weixin.ClassifyActivityFactory;
import catfish.flowcontroller.activities.weixin.WeixinReCheckActivityFactory;

public class ActivityManager implements ActivityCreator{
    public static ActivityManager instance = new ActivityManager();

    private Map<String, ActivityFactory> activityFactoryMap = new HashMap<String, ActivityFactory>();
    private ActivityManager(){

    	/*******************common*********************************/
    	initCommonFactory(activityFactoryMap);
        
        activityFactoryMap.put("checkD1FeedbackPass", new CheckD1FeedbackPassActivityFactory());
        activityFactoryMap.put("checkD1Feedback", new CheckD1FeedbackActivityFactory());
        /***************only for weixin************************/
        activityFactoryMap.put("weixinReCheck", new WeixinReCheckActivityFactory());
        activityFactoryMap.put("checkAdditionalContact", new CheckAdditionalContactActivityFactory());
        activityFactoryMap.put("classify", new ClassifyActivityFactory());

        /***************only for app**************************/
        activityFactoryMap.put("appReCheck", new AppReCheckActivityFactory());
        activityFactoryMap.put("loanCheck", new LoanCheckActivityFactory());
        activityFactoryMap.put("appCollectCheck", new AppPhotoEvidenceCollectCheckActivityFactory());
        
        /***************only for paydayloan******************/
        activityFactoryMap.put("pdlReCheck",
                new PDLReCheckActivityFactory());
        activityFactoryMap.put("collectWorkEvidence",
                new PDLWorkEvidenceCollollectCheckActivityFactory());

        // cash loan
        activityFactoryMap.put("CLSecurityCheck", new CLSecurityCheckActivityFactory());
    }
    
    public static void initCommonFactory(Map<String, ActivityFactory> activityFactoryMap){
    	/*******************common*********************************/
        activityFactoryMap.put("queue", new QueueActivityFactory());
        activityFactoryMap.put("loop", new LoopActivityFactory());
        activityFactoryMap.put("conditional", new ConditionalActivityFactory());
        activityFactoryMap.put("ubtDBWriting", new UbtDBWritingActivityFactory());
        activityFactoryMap.put("dummy", new DummyActivityFactory());
        activityFactoryMap.put("block", new BlockActivityFactory());
        activityFactoryMap.put("terminate", new TerminateActivityFactory());
        activityFactoryMap.put("onsNotify", new OnsNotificationActivityFactory());
        activityFactoryMap.put("split", new SplitActivityFactory());
        activityFactoryMap.put("changeStatus", new ChangeStatusServiceActivityFactory());
        activityFactoryMap.put("split_dyna", new SplitDynaActivityFactory());
    }
    
    @Override
    public Activity create(Map<String, Object> activityConfig){
        Object typeObj = activityConfig.get("type");
        String type = "queue";
        if(typeObj != null){
            type = typeObj.toString();
        }
        ActivityFactory af = activityFactoryMap.get(type);
        if(af == null){
            Logger.get().error("fail to get activity factory for " + type);
            return null;
        }
        return af.create(activityConfig);
    }
}
