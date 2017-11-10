package catfish.plugins.redpack;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.ActivityWeixinRedPackDao;
import catfish.base.business.dao.ActivityWeixinRedPackRecordDao;
import catfish.base.business.object.ActivityWeixinRedPackObject;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.MerchantUserDao;
import catfish.base.business.object.MerchantUserObject;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.queue.QueueMapMessager;
import catfish.base.queue.QueueMessages;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.messageservice.Message;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.plugins.redpack.module.SmsaResponseData;
import catfish.services.message.HttpMessageConfig;

public class ActivityWeixinRedPackPlugin implements IPlugin, IQueueListener {
    private static final String MCH_ID = StartupConfig.get("catfish.redpack.MchId");
    private static final String D_MCH_ID = StartupConfig.get("catfish.dealer.mchid");
    private static final String WXD_APPID = StartupConfig.get("catfish.wxd.appid");

    private IWeixinRedpackCalculator rpCalculator;

    private List<String> S1MessageList = new ArrayList<String>();
    private String S1MessageReminder;
    private IMessageService messageService;
    private ActivityService activityService;
    private Integer[] value;
    private double[] probability;
    private double posBonus = 0;
    private double dayBonus = 0;
    private double hourBonus = 0;
    private double weekBonus = 0;
    private int bonusStartDay = 0;
    private int bonusEndDay = 0;
    private int bonusStartHour = 0;
    private int bonusEndHour = 0;
    private int posSize = 0;
    private int isHoliday = 0;
    private double originMiu = 0;
    private double originSigma = 0;
    private double extraMiuPos = 0;
    private double extraMiuDay = 0;
    private double extraMiuHour = 0;
    private double extraMiuWeek = 0;
    private double miuLimit = 0;
    private double pLimit = 0;
    private double rpLow = 0;
    private double rpHigh = 0;
    private final long msOneDay = 1000 * 60 * 60 * 24;
    private static double adaptiveRedpack = 0;
    private double standardMean = 0;
    private int rpcnt = 0;

    private static String sql_getD1OpenIdForApp =
            "select u.weixinuserid from dealeruserapplicationrelationobjects da " +
            "join userobjects u on u.id = da.userid " +
            "where da.appid = :appId";
    
    private static String sql_getD1UserIdForApp =
            "select u.id from dealeruserapplicationrelationobjects da " +
            "join userobjects u on u.id = da.userid " +
            "where da.appid = :appId";
    
    private static final String SMSA_OLDUSER_EXTINFO_URL = "http://%s:%s/public/common/olduserextinfo/queryOldUserExtInfo";
	
    public ActivityWeixinRedPackPlugin() {
        this(new ActivityRedPackService());
    }

    public ActivityWeixinRedPackPlugin(ActivityService activityService) {
        this.activityService = activityService;

        Properties messageProperty = new Properties();
        try {
            messageProperty.load(new InputStreamReader(new FileInputStream("message.properties"), "UTF8"));
            Set<String> keySet = messageProperty.stringPropertyNames();
            for (String key : keySet) {
                if (key.contains("catfish.services.message.s1")) {
                    S1MessageList.add(messageProperty.getProperty(key));
                }
            }
            S1MessageReminder = messageProperty.getProperty("catfish.services.message.reminder");
        } catch (IOException e) {
        }

        //import redpack reward parameters
        ArrayList<Integer> valueList = new ArrayList<Integer>();
        Properties systemProperties = StartupConfig.getSystemProperty();
        Set<String> keySet = systemProperties.stringPropertyNames();
        for (String key : keySet) {
            if (key.contains("catfish.redpack.value")) {
                valueList.add(Integer.parseInt(systemProperties.getProperty(key)));
            }
        }
        Collections.sort(valueList);
        value = valueList.toArray(new Integer[valueList.size()]);

        probability = new double[value.length];
        for (String key : keySet) {
            if (key.contains("catfish.redpack.p.")) {
                int index = Integer.parseInt(key.substring(18));
                probability[index - 1] = Double.parseDouble(systemProperties.getProperty(key));
            }
        }

        // 红包基本配置
        originMiu = Double.valueOf(StartupConfig.get("catfish.redpack.miu")).doubleValue();
        originSigma = Double.valueOf(StartupConfig.get("catfish.redpack.sigma")).doubleValue();
        rpLow = Double.valueOf(StartupConfig.get("catfish.redpack.rpLow")).doubleValue();
        rpHigh = Double.valueOf(StartupConfig.get("catfish.redpack.rpHigh")).doubleValue();

        // 红包加成配置
        posBonus = Double.valueOf(StartupConfig.get("catfish.redpack.posBonus")).doubleValue();
        dayBonus = Double.valueOf(StartupConfig.get("catfish.redpack.dayBonus")).doubleValue();
        hourBonus = Double.valueOf(StartupConfig.get("catfish.redpack.hourBonus")).doubleValue();
        weekBonus = Double.valueOf(StartupConfig.get("catfish.redpack.weekBonus")).doubleValue();
        bonusStartDay = Integer.valueOf(StartupConfig.get("catfish.redpack.bonusStartDay")).intValue();
        bonusEndDay = Integer.valueOf(StartupConfig.get("catfish.redpack.bonusEndDay")).intValue();
        bonusStartHour = Integer.valueOf(StartupConfig.get("catfish.redpack.bonusStartHour")).intValue();
        bonusEndHour = Integer.valueOf(StartupConfig.get("catfish.redpack.bonusEndHour")).intValue();
        posSize = Integer.valueOf(StartupConfig.get("catfish.redpack.posSize")).intValue();
        isHoliday = Integer.valueOf(StartupConfig.get("catfish.redpack.isHoliday")).intValue();
        extraMiuPos = Double.valueOf(StartupConfig.get("catfish.redpack.extraMiuPos")).doubleValue();
        extraMiuDay = Double.valueOf(StartupConfig.get("catfish.redpack.extraMiuDay")).doubleValue();
        extraMiuHour = Double.valueOf(StartupConfig.get("catfish.redpack.extraMiuHour")).doubleValue();
        extraMiuWeek = Double.valueOf(StartupConfig.get("catfish.redpack.extraMiuWeek")).doubleValue();
        miuLimit = Double.valueOf(StartupConfig.get("catfish.redpack.miuLimit")).doubleValue();
        pLimit = Double.valueOf(StartupConfig.get("catfish.redpack.pLimit")).doubleValue();
        standardMean = Double.valueOf(StartupConfig.get("catfish.redpack.standardMean")).doubleValue();
    }

    @Override
    public void init(IServiceProvider sp) {
        messageService = sp.getService(IMessageService.class);
        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue("CatfishServerQueue");
        queue.register(QueueMessages.APPMONEY_TRANSFERRED, this);
        queue.register(QueueMessages.REDPACK_SENT, this);
    }

    @Override
    public void onMessage(String message, String data) {
        Logger.get().info(String.format("%s:%s:%s", this.getClass().getName(), message, data));
        QueueMapMessager dataMap = QueueMapMessager.fromString(data);
        if (dataMap == null) {
            return;
        }

        if (QueueMessages.APPMONEY_TRANSFERRED.equals(message)) {
            String appId = dataMap.getAsString("appId");
            InstallmentApplicationDao appDao = new InstallmentApplicationDao(appId);
            InstallmentApplicationObject app = appDao.getSingle();
            if (app.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue()) {
                Logger.get().info("PaydayLoan app, redpack not created for appId : " + appId);
                return;
            }
            onAppMoneyTransferred(appId);
        } else if (QueueMessages.REDPACK_SENT.equals(message)) {
            onRedPackSent(dataMap.getAsString("recordId"), dataMap.getAsInt("recordId"));
        }
    }

    private void onAppMoneyTransferred(String appId) {
//        ActivityWeixinRedPackObject activity = this.activityService.getActivity(appId);
//        if (activity == null) {
//            Logger.get().info(String.format("appId:%s, not in activity.", appId));
//            return;
//        }

        String mchBillNo = generateMchBillno();
//        Double reward = generateReward();
//        Double reward = generateReword(appId, activity);
        // todo
        rpCalculator = new SalesRedpackCalculator();
        BigDecimal redpack = rpCalculator.calculate(appId);
        if (redpack == null) {
            Logger.get().info("redpack is null");
        } else {
            double reward = redpack.doubleValue();
            String recordId = this.activityService.createRedPackRecord(appId, rpCalculator.getActivityId(), mchBillNo, reward);

//        ActivityWeixinRedPackObject  activity = rpCalculator.getActivity();
//        String recordId = this.activityService.createRedPackRecord(appId, activity, mchBillNo, reward);
            if (recordId != null) {
                sendMessageToS1(appId, recordId);
            }
        }


        // D1红包
        if ("ON".equals(StartupConfig.get("catfish.dealer.redpack.switch", "OFF"))) {
            IWeixinRedpackCalculator d1rpCalculator = new DealerUserRedpackCalculator();
            BigDecimal d1Redpack = d1rpCalculator.calculate(appId);
            if (d1Redpack == null) {
                Logger.get().info("d1 redpack is null for appId " + appId);
                return;
            }
            double d1Reward = d1Redpack.doubleValue();
            String d1MchBillNo = generateMchBillno(D_MCH_ID);
            String d1openId = null;
            
            String salesAdminServiceSwitchOn = StartupConfig.get("salesAdminServiceSwitchOn", "OFF");
            if ("on".equalsIgnoreCase(salesAdminServiceSwitchOn)) {
            	//拆分：1查出d1的userId；2，调用smsa的接口查询d人员的微信openId
            	d1openId = getD1WeChatOpenIdByAppId(appId);
			}else {
				// 根据appid查询D1的微信openId  -- 旧有逻辑-从UserObjects表定位到d人员的openId
				d1openId = DatabaseApi.querySingleString(sql_getD1OpenIdForApp, CollectionUtils.mapOf("appId", appId));
			}
            //创建红包发送记录
            String d1rpRecordId = this.activityService.createRedPackRecord(appId, d1rpCalculator.getActivityId(),
                    d1MchBillNo, d1Reward, WXD_APPID, d1openId);
            if (d1rpRecordId != null) {
                sendMessageToD1(appId, d1rpRecordId);
            }

        }
    }
    

    /**
	 * 根据appid查询D1的微信openid
	 * 
	 * @param appId
	 * @return
	 */
	public static String getD1WeChatOpenIdByAppId(String appId) {
		Logger.get().info("call getD1WeChatOpenIdByAppId with appId : " + appId);
		String WeiXinUserId = null;
		// 1.先根据appid查询签单表中d1的userId
		String d1UserId = DatabaseApi.querySingleString(sql_getD1UserIdForApp, CollectionUtils.mapOf("appId", appId));
		if (StringUtils.isNullOrWhiteSpaces(d1UserId)) {
			Logger.get().error("no d1UserId from dealeruserapplicationrelationobjects , for appId : " + appId);
		} else {
			// 2.调用smsa接口，根据d人员的id查询d的微信openId
			Logger.get().info("-----begin smsa-service to get weixinUserId for d1 , d1UserId : " + d1UserId);
			Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().build();
			params.put("userObjectsId", d1UserId);
			
			Map<String, Object>[] data = getSmsaUserExtendInfo(params);
			if (data == null || data.length==0 || !data[0].containsKey("weixinUserId")) {
				Logger.get().error("call smsa-service -- no weixinUserId for d1 , d1UserId : " + d1UserId + " , appId : " + appId);
			} else {
				WeiXinUserId = (String) data[0].get("weixinUserId");
				Logger.get().info("call smsa-service -- weixinUserId for d1 , WeiXinUserId : " + WeiXinUserId +" d1UserId : " + d1UserId + " , appId : " + appId);
			}
		}
		return WeiXinUserId;
	}
	
	/**
	 * 调用SMSA接口,根据bd或d的userId查询对应的微信openId
	 * 
	 * @param did
	 * @return
	 */
	public static Map<String, Object>[] getSmsaUserExtendInfo(Map<String, Object> params) {
		SmsaResponseData<Object> responseData = null;
		Map<String, Object>[] userExtInfoDatas = null;
		
		String SMSA_URL =  String.format(SMSA_OLDUSER_EXTINFO_URL, StartupConfig.get("sales.adminService.host"),StartupConfig.get("sales.adminService.port"));
		Logger.get().info("-----begin call smsa-service to getUserExtendInfo -----  \n url : " + SMSA_URL +" \n params : " + new Gson().toJson(params));
		//responseData = HttpClientApi.postJson(SMSA_URL, params, new TypeToken<SmsaResponseData<Object>>() {}.getType());
		String postResult = HttpClientApi.postJson(SMSA_URL, params, "string");
		if (StringUtils.isNullOrWhiteSpaces(postResult)) {
			Logger.get().error("call smsa-service to getUserExtendInfo error  ! postResult is null  ");
			return null;
		}
		else {
			responseData = (SmsaResponseData<Object>)new Gson().fromJson(postResult, SmsaResponseData.class);
			if ( responseData !=null && responseData.getCode() ==0) {//操作成功
				userExtInfoDatas = responseData.getValue();
				if (userExtInfoDatas != null) {
					Logger.get().info("call smsa-service to getUserExtendInfo success , response value :  " + (new Gson()).toJson(userExtInfoDatas));
				} 
			}else {
				Logger.get().error("call smsa-service to getUserExtendInfo error  ! message :  " + responseData.getMessage());
			}
		}
		return userExtInfoDatas;
	}
	
    private void onRedPackSent(String recordId, int status) {
        new ActivityWeixinRedPackRecordDao().updateStatus(recordId, status);
    }

    private boolean sendMessageToS1(String appId, String recordId) {
        messageService.sendMessage(constructRedPackMessage(appId, recordId));
        return false;
    }

    private Message constructRedPackMessage(String appId, String recordId) {
        Collections.shuffle(S1MessageList);
        String url;
        if (StartupConfig.get("catfish.redpack.port") != null &&
                !StartupConfig.get("catfish.redpack.port").isEmpty()) {
            url = StartupConfig.get("catfish.redpack.host") + ':' +
                    StartupConfig.get("catfish.redpack.port");
        } else url = StartupConfig.get("catfish.redpack.host");

        String content = String.format(S1MessageList.get(0), url, recordId) + S1MessageReminder;
        return new Message(Message.ObjectType.MERCHANT,
                Message.MessageType.WECHAT,
                appId,
                content);
    }

    private void sendMessageToD1(String appId, String recordId) {
        String content = String.format(S1MessageList.get(0), StartupConfig.get("catfish.dealer.wxhost"), recordId);
        Message message = new Message(Message.ObjectType.MERCHANT, Message.MessageType.WECHAT, appId, content);
        sendMessageSync(message, "PosD1Redpack");
    }

    private void sendMessageSync(Message message, String notificationName) {
        HttpClientApi.postJson(
                String.format("http://%s:%s/notification.action",
                        HttpMessageConfig.getHttpHost(), HttpMessageConfig.getHttpPort()),
                CollectionUtils.mapOf(
                        MessageNotificationUtil.NotificationKeys.NOTIFICATION_NAME, notificationName,
                        "ObjectType", message.getObjectType().toString(),
                        "MessageType", message.getMessageType().toString(),
                        "Content", message.getContent(),
                        "AppId", message.getAppId()));
    }


    private double generateReword(String appId, ActivityWeixinRedPackObject activity) {
        Random random = new Random();
        double pExtra = 0;
        double redpack = 0;
        double miu = activity.AvgReward - 2;
        double sigma = originSigma + activity.AvgReward / 25;
        double baseReward;
        double extraReward;


        // 判断商户大小
        int n = MerchantStoreDao.getPosCountByAppId(appId);
        if (n >= posSize) {
            miu += extraMiuPos;
            pExtra += posBonus;
        }

        // 开业时间
        MerchantUserObject mu = MerchantUserDao.getMerchantUserbyAppId(appId);
        int t = 0;
        Date current = new Date();
        if (mu != null) {
            Date muAddDate = mu.DateAdded;
            t = (int) ((current.getTime() - muAddDate.getTime()) / msOneDay);
        }

        if (t > bonusStartDay && t < bonusEndDay) {
            miu += extraMiuDay;
            pExtra += dayBonus;
        }

        // 发红包时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= bonusStartHour && hour < bonusEndHour) {
            miu += extraMiuHour;
            pExtra += hourBonus;
        }

        // 是否周末
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY || isHoliday == 1) {
            miu += extraMiuWeek;
            pExtra += weekBonus;
        }

        // 基本钳制
//	  if(miu>miuLimit) {
//		  miu = miuLimit;
//	  }

        // 加成概率钳制
        if (pExtra > pLimit) {
            pExtra = pLimit;
        }

        baseReward = generateBaseReward(miu, sigma);
        extraReward = generateExtraReward(pExtra);

        redpack = baseReward + extraReward;

        // 红包金额自修正
        rpcnt++;
        if (redpack > 120) {
            Logger.get().info("Big redpack ready to sent.");
        } else if (activity.Offset > 0) {
            if (redpack > activity.AvgReward) {
                if ((redpack - standardMean) > 25) {
                    redpack -= (activity.Offset / 10);
                } else {
                    redpack -= (activity.Offset / 20);
                }
            }
        }

        // 红包不得超过设定范围
        if (redpack < activity.MinReward) {
            redpack = activity.MinReward + random.nextDouble();
        } else if (redpack > activity.MaxReward) {
            redpack = activity.MaxReward - random.nextDouble();
        }

        // 2位小数，精确到分
        BigDecimal bd = new BigDecimal(redpack);
        redpack = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        ActivityWeixinRedPackDao.updateOffset(activity.Id, (redpack - activity.AvgReward));
        Logger.get().info(String.format("redpack %f sent, offset before : %f, activity Id : %s",
                redpack, activity.Offset, activity.Id));

        return redpack;
    }

    // 生成基础红包金额
    private double generateBaseReward(double miu, double sigma) {
        Random random = new Random();
        return (random.nextGaussian() * sigma + miu);
    }

    // 生成额外红包金额
    private double generateExtraReward(double pExtra) {
        Random random = new Random();
        double extraMoney = 0;
        double pBonus = random.nextDouble() + pExtra;

        for (int i = 0; i < probability.length; i++) {
            if (pBonus <= probability[i]) {
                extraMoney = random.nextDouble() * value[i];
                break;
            } else {
                continue;
            }

        }
        return extraMoney;
    }

    //mch_id + yyyymmdd + 10位一天内不能重复的数字
    private static String generateMchBillno() {
        Format formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return String.format("%s%s%s",
                MCH_ID, formatter.format(new Date()), new Random().nextInt(10));
    }

    private static String generateMchBillno(String mchId) {
        Format formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return String.format("%s%s%s",
                mchId, formatter.format(new Date()), new Random().nextInt(10));
    }
}
