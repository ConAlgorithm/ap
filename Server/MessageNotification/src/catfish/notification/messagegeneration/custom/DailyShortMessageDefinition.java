package catfish.notification.messagegeneration.custom;

import grasscarp.account.model.MobileHistory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;

import catfish.base.Description;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.business.util.EnumUtils;
import catfish.base.httpclient.HttpClientApi;
import catfish.msglauncher.model.ShortMessageProvider;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.message.ShortMessage;
import catfish.notification.message.WeChatText;
import catfish.notification.messagegeneration.AccountServiceApi;
import catfish.notification.messagegeneration.InstallmentApplicationApi;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.SettlementApi;
import catfish.notification.messagegeneration.SettlementApi.PayInfoResponse;
import catfish.notification.messagegeneration.SettlementApi.ProductResponse;
import catfish.notification.messagegeneration.UserApi;
import catfish.notification.messagegeneration.UserApi.UserData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omniprimeinc.superapp.wechatbase.api.models.WechatUserBaseModel;

interface MessageSender {
    public Boolean execute(String message);
    public Boolean execute(String message, int provider);
    public Boolean executePush(String message);
    
    public Boolean executeWechat(String message, String openId);
}

interface MessageSaver {
    public void execute(String message);
}

public class DailyShortMessageDefinition implements MessageDefinition {
//    private static final String REPAYMENT_BY_DATE_URL = "/Repayment/RepaymentByDate";
//    private static final String PENALTY_URL           = "/Repayment/PenaltyPerDay";
//    private static final String REPAYMENT_ALL_URL     = "/Repayment/GetTodayRepaymentInfo"; // 查询今日还款信息，应还（本金，利息，罚金，服务费，逾期天数）
    private static final String USER_URL              = "/user";                            // 根据id获取用户信息
    private static String QUEUENAME = StartupConfig.get("catfish.batch.priority.msg.queue.name", "BatchMessageLauncher");

    @Override
    public List<Message> apply(final Map<String, ?> receivedMessage) {

        String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);

        final Map<String, String> query = MessageDatabaseApi.getApplicationInfo(receivedMessage);

        Map<String, Object> params = new HashMap<>();
        params.put("appId", appId);
        params.put("date", System.currentTimeMillis());

        BigDecimal amount;
        BigDecimal penalty;
        BigDecimal amountAll;
        String bankCardAccount;

//        Data data = RepaymentApi.invoke(REPAYMENT_BY_DATE_URL, params);
        PayInfoResponse response1 = SettlementApi.getPayInfo(params);
        if (response1 == null) {
            Logger.get().warn("data is null, APP_ID = " + appId);
            return Collections.emptyList();
        } else if (response1.getCurRepaymentPayAmount() == null || response1.getOverDueAmount() == null) {
            Logger.get().warn("CurrentAmount is null, APP_ID = " + appId);
            return Collections.emptyList();
        }
        amount = response1.getCurRepaymentPayAmount().setScale(2, RoundingMode.HALF_UP);
        amountAll = response1.getOverDueAmount().setScale(2, RoundingMode.HALF_UP);

//        Data data = RepaymentApi.invoke(PENALTY_URL, params);
        ProductResponse response2 = SettlementApi.getProductInfo(params);
        if (response2 == null) {
            Logger.get().warn("data is null, APP_ID = " + appId);
            return Collections.emptyList();
        } else if (response2.getFee() == null || response2.getFee().getPenaltyFee() == null) {
            Logger.get().warn("PenaltyPerDay is null, APP_ID = " + appId);
            return Collections.emptyList();
        }
        penalty = response2.getFee().getPenaltyFee().setScale(2, RoundingMode.HALF_UP);

//        data = RepaymentApi.invoke(REPAYMENT_ALL_URL, appId);
//        if (data == null) {
//            Logger.get().warn("data is null, APP_ID = " + appId);
//            return Collections.emptyList();
//        } else if (data.getTotal() == null) {
//            Logger.get().warn("Total is null, APP_ID = " + appId);
//            return Collections.emptyList();
//        }
//        amountAll = data.getTotal().setScale(2, RoundingMode.HALF_UP);

        UserData userData = UserApi.invoke(USER_URL, query.get("UserId"));
        if (userData == null) {
            Logger.get().warn("userData is null, APP_ID = " + appId);
            return Collections.emptyList();
        } else if (userData.getBankCardAccount() == null) {
            Logger.get().warn("BankCardAccount is null, APP_ID = " + appId);
            return Collections.emptyList();
        }
        bankCardAccount = userData.getBankCardAccount();

//        final String userId = MessageDatabaseApi.getUserIdByAppId(appId);
        List<MobileHistory> mobiles = AccountServiceApi
            .getUserMobiles(MessageDatabaseApi.getUserIdByAppId(appId));
        String phone = query.get("Phone");
        if (!org.springframework.util.CollectionUtils.isEmpty(mobiles) && mobiles.size() > 1) {
            phone = mobiles.get(mobiles.size() - 1).getMobile();
        }
        Logger.get().info("Send short message to :" + phone);
        query.put("Phone", phone);

        query.put("Amount", amount.toString());
        query.put("Penalty", penalty.toString());
        query.put("AmountAll", amountAll.toString());
        query.put("BankCardAccount", bankCardAccount);
        query.put(NotificationKeys.APP_ID, appId);
        
        
        MessageSender sender = new MessageSender() {
            @Override
            public Boolean execute(String message) {
                return catfish.notification.sender.MessagesSender.send(Arrays.<Message> asList(
                    new ShortMessage(receivedMessage, query.get("Phone"), message)), 1, QUEUENAME, 10);
            }
            
            @Override
            public Boolean execute(String message, int provider) {
                return catfish.notification.sender.MessagesSender.send(Arrays.<Message> asList(
                    new ShortMessage(receivedMessage, query.get("Phone"), message, provider)), 1, QUEUENAME, 10);
            }
            
            
            @Override
            public Boolean executePush(String message) {
            	Map<String, Object> toSupperAppMessage = new HashMap<String, Object>();
            	toSupperAppMessage.putAll(receivedMessage);
            	toSupperAppMessage.put("toSupperApp", "Y");
                return catfish.notification.sender.MessagesSender.send(Arrays.<Message> asList(
                    new LeanCloudMessage(toSupperAppMessage, query.get("UserId"), message)), 1, QUEUENAME, 10);
            }
            
            @Override
            public Boolean executeWechat(String message, String openId) {
                return catfish.notification.sender.MessagesSender.send(Arrays.<Message> asList(
                     new WeChatText(receivedMessage, Configuration.getWeChatCustomerTokeManager(), openId, message)), 1, QUEUENAME, 10);
            }
            
        };

        MessageSaver saver = new MessageSaver() {
            @Override
            public void execute(String message) {
                MessageDatabaseApi.updateDailyShortMsgRecord(message,
                    receivedMessage.get("RecordId").toString());
            }
        };

        AbstractMessageHandler handler = Integer.parseInt(
            query.get("Type")) == (DailyShortMessageType.LegacyUserThreeDaysNotification.getValue())
                ? new LegacyMessageHandler(query) : new WithholdingMessageHandler(query);
        //handler.MessageHandler(sender, saver);
        if (penalty.compareTo(BigDecimal.ZERO) == 1)
        {
           handler.MessageHandler(sender, saver);
        }
        return Arrays.<Message> asList();
    }
    
}

enum DailyShortMessageType implements CatfishEnum<Integer> {

                                                            @Description(text = "代扣用户提前3天通知短信") WithholdingUserThreeDaysNotification(1),

                                                            @Description(text = "遗留用户提前3天通知短信") LegacyUserThreeDaysNotification(2),

                                                            @Description(text = "代扣用户提前1天通知短信") WithholdingUserOneDayNotification(3),

                                                            @Description(text = "代扣用户逾期n天通知短信") WithholdingUserOverdueNDaysNotification(4),

                                                            @Description(text = "代扣用户逾期20天通知短信") WithholdingUserOverdueTwentyDaysNotification(5),

                                                            @Description(text = "已激活还款缓冲包的用户逾期3天通知短信") WithholdingUserBufferThreeDaysNotification(13);

    private int value;

    private DailyShortMessageType(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}

enum ApplicationStatus implements CatfishEnum<Integer> {

                                                        @Description(text = "还款中") Completed(100),

                                                        @Description(text = "已逾期") Delayed(200);

    private int value;

    private ApplicationStatus(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}

class BankManager {
    @SuppressWarnings("serial")
    private static final Map<String, Map<String, String>> bankData = new HashMap<String, Map<String, String>>() {
        {
            put("市辖区", new HashMap<String, String>() {
                {
                    put("中国银行", "6217900800001485323");
                    put("建设银行", "6217001210028653044");
                    put("招商银行", "6214830215792226");
                }
            });
            put("苏州市", new HashMap<String, String>() {
                {
                    put("中国银行", "6217856101009897799");
                    put("建设银行", "6217002000016189609");
                    put("招商银行", "6214835120887722");
                }
            });
            put("杭州市", new HashMap<String, String>() {
                {
                    put("中国银行", "6217856200011578387");
                    put("建设银行", "6217001540010854154");
                    put("招商银行", "6214835711050532");
                }
            });
            put("南京市", new HashMap<String, String>() {
                {
                    put("中国银行", "6217856100026476835");
                    put("建设银行", "6217001370016358632");
                    put("招商银行", "6214830252659122");
                }
            });
            put("长沙市", new HashMap<String, String>() {
                {
                    put("中国银行", "6217587500005853423");
                    put("建设银行", "6217002920120233896");
                    put("招商银行", "6214837210408005");
                }
            });
            put("宁波市", new HashMap<String, String>() {
                {
                    put("招商银行", "6214835740460116");
                }
            });
        }
    };

    public static List<String> GenerateCorrectBankData(String area, String bankName) {
        Map<String, String> bank = bankData.get(area);
        if (bank != null) {
            return bank.containsKey(bankName) ? Arrays.asList(bankName, bank.get(bankName))
                : (bank.containsKey("建设银行") ? Arrays.asList("建设银行", bank.get("建设银行"))
                    : Arrays.asList("建设银行", "6217001210028653044"));
        } else {
            return Arrays.asList("建设银行", "6217001210028653044");
        }
    }

}

class Consts {
    private static final String CLASSNAME                                       = "DailyShortMessageDefinition";
    public static final String  WithholdingNDaysNormalUserFormat                = ResourceConfig
        .getResourceByName(CLASSNAME + "_NDays", Receiver.CUSTOMER, Channel.SHORTMESSAGE);

    public static final String  WithHoldingTodayNormalUserFormat                = ResourceConfig
        .getResourceByName(CLASSNAME + "_Today", Receiver.CUSTOMER, Channel.SHORTMESSAGE);

    public static final String  PosWithholdingNDaysNormalUserFormat             = ResourceConfig
        .getResourceByName(CLASSNAME + "_NDays", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.POS);
    
    // Hanter 2080
    public static final String  PosWithholdingNDaysNormalUserFormat_SupperApp             = ResourceConfig
            .getResourceByName(CLASSNAME + "_NDays", Receiver.CUSTOMER, Channel.APP,
                Product.POS);
    
    public static final String  PosWithHoldingNDaysNormalOverdueUserFormat      = ResourceConfig
        .getResourceByName(CLASSNAME + "_OverdueNDays", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.POS);

    public static final String  PosWithHoldingTwentyDaysNormalOverdueUserFormat = ResourceConfig
        .getResourceByName(CLASSNAME + "_OverdueTwentyDays", Receiver.CUSTOMER,
            Channel.SHORTMESSAGE, Product.POS);
    
    public static final String  PosWithHoldingThreeDaysBufferOverdueUserFormat = ResourceConfig
    	.getResourceByName(CLASSNAME + "_BufferThreeDays", Receiver.CUSTOMER,
    			Channel.SHORTMESSAGE, Product.POS);

    public static final String  PosWithholdingOverdueUserFormat                 = ResourceConfig
        .getResourceByName(CLASSNAME + "_Overdue", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.POS);

    public static final String  PosLegacyNormalUserFormat                       = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyNormal", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.POS);

    public static final String  PosLegacyOverdueUserFormat                      = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyOverdue", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.POS);

    public static final String  PosLegacyNormalUserFormatBySMS                  = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyNormalSMS", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.POS);

    public static final String  PosLegacyOverdueUserFormatBySMS                 = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyOverdueSMS", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.POS);

    public static final String  PdlWithholdingOverdueUserFormat                 = ResourceConfig
        .getResourceByName(CLASSNAME + "_Overdue", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.PDL);

    public static final String  PdlLegacyNormalUserFormat                       = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyNormal", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.PDL);

    public static final String  PdlLegacyOverdueUserFormat                      = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyOverdue", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.PDL);

    public static final String  PdlLegacyNormalUserFormatBySMS                  = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyNormalSMS", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.PDL);

    public static final String  PdlLegacyOverdueUserFormatBySMS                 = ResourceConfig
        .getResourceByName(CLASSNAME + "_LegacyOverdueSMS", Receiver.CUSTOMER, Channel.SHORTMESSAGE,
            Product.PDL);
}

abstract class AbstractMessageHandler {
    public String                name;
    public Integer               installment;
    public String                amount;
    public String                penalty;
    public DateTime              InstallmentStartOn;
    public DailyShortMessageType type;
    public ApplicationStatus     status;
    public String                itemPrice;
    public String                phone;
    public String                appId;
    public String                amountAll;
    public String                bankCardAccount;
    
    // Hanter 2080
    public String                userId;

    public AbstractMessageHandler(Map<String, String> messageInfo) {
        this.phone = messageInfo.get("Phone");
        this.name = messageInfo.get("Name");
        this.amount = messageInfo.get("Amount");
        this.penalty = messageInfo.get("Penalty");
        this.status = EnumUtils.parse(ApplicationStatus.class,
            Integer.parseInt(messageInfo.get("Status")));
        this.type = EnumUtils.parse(DailyShortMessageType.class,
            Integer.parseInt(messageInfo.get("Type")));
        this.itemPrice = messageInfo.get("Principal");
        this.appId = messageInfo.get(NotificationKeys.APP_ID);
        this.amountAll = messageInfo.get("AmountAll");
        this.bankCardAccount = messageInfo.get("BankCardAccount");
        
        // Hanter 2080
        this.userId = messageInfo.get("UserId");
        
        setInstallmentStartOn(messageInfo.get("StartDate"));
        setInstallment();
    }

    abstract void MessageHandler(MessageSender sender, MessageSaver saver);

    public DateTime getInstallmentStartOn() {
        return this.InstallmentStartOn;
    }

    public void setInstallmentStartOn(String stringDate) {
        this.InstallmentStartOn = new DateTime(DateTimeUtils.parse(stringDate));
    }

    public Integer getInstallment() {
        return installment;
    }

    public void setInstallment() {
        DateTime now = DateTime.now();
        this.installment = now.getMonthOfYear() - this.InstallmentStartOn.getMonthOfYear();
        if (now.getYear() != this.InstallmentStartOn.getYear()) {
            this.installment += 12;
        }

        // 如果用户的约定还款日是1,2,3 号，则表明短信由上月发送，故期数少一期
        if (this.InstallmentStartOn.getDayOfMonth() == 1
            || this.InstallmentStartOn.getDayOfMonth() == 2
            || this.InstallmentStartOn.getDayOfMonth() == 3) {
            this.installment += 1;
        }
    }
}

class WithholdingMessageHandler extends AbstractMessageHandler {

    private static final String DATE_Format = "yyyy年MM月dd日"; // 短信模版中日期格式
    private static final String DATE_Form = "MM月dd日"; // 短信模版中日期格式
    private int                 scale       = 4;             // 还款账户尾号位数

    public WithholdingMessageHandler(Map<String, String> messageInfo) {
        super(messageInfo);
    }

    @Override
    public void MessageHandler(MessageSender sender, MessageSaver saver) {
        String message;
        int provider = ShortMessageProvider.DaHanSanTong.getValue();
        // 当前日期calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_Format);
        SimpleDateFormat dfm = new SimpleDateFormat(DATE_Form);

        if (MessageDatabaseApi.isPOSApplication(this.appId)) { // POS用户
            if (ApplicationStatus.Completed.equals(this.status)
                && DailyShortMessageType.WithholdingUserOneDayNotification.equals(this.type)) { // 代扣用户提前1天通知短信
                // 计算还款日期
                calendar.add(Calendar.DATE, 1);
                String datedue = dfm.format(calendar.getTime());
                message = String.format(Consts.PosWithholdingNDaysNormalUserFormat, this.name,
                    this.bankCardAccount.substring(bankCardAccount.length() - scale), this.amount,
                    datedue);
                
                // Hanter 2080
                String supperAppMessage = String.format(Consts.PosWithholdingNDaysNormalUserFormat_SupperApp, this.name,
                        this.bankCardAccount.substring(bankCardAccount.length() - scale), this.amount,
                        datedue);                		
//                if(!sender.executePush(supperAppMessage)){
//                	Logger.get().warn("D1 app push exception! " + supperAppMessage);
//                }
                
                batchpush(this.userId, supperAppMessage, 3, "还款提示");
                
                wechatPush(sender, supperAppMessage, "D1");
                
            } else if (ApplicationStatus.Completed.equals(this.status)
                       && DailyShortMessageType.WithholdingUserThreeDaysNotification
                           .equals(this.type)) { // 代扣用户提前3天通知短信
                // 计算还款日期
                calendar.add(Calendar.DATE, 3);
                String datedue = dfm.format(calendar.getTime());
                message = String.format(Consts.PosWithholdingNDaysNormalUserFormat, this.name,
                    this.bankCardAccount.substring(bankCardAccount.length() - scale), this.amount,
                    datedue);
                
                // Hanter 2080
                String supperAppMessage = String.format(Consts.PosWithholdingNDaysNormalUserFormat_SupperApp, this.name,
                        this.bankCardAccount.substring(bankCardAccount.length() - scale), this.amount,
                        datedue);                		
//                if(!sender.executePush(supperAppMessage)){
//                	Logger.get().warn("D3 app push exception! " + supperAppMessage);
//                }
                
                batchpush(this.userId, supperAppMessage, 3, "还款提示");
                
                wechatPush(sender, supperAppMessage, "D3");
                
            } else if (DailyShortMessageType.WithholdingUserOverdueNDaysNotification
                .equals(this.type)) { // 代扣用户逾期N天通知短信
                message = String.format(Consts.PosWithHoldingNDaysNormalOverdueUserFormat,
                    this.name, this.bankCardAccount.substring(bankCardAccount.length() - scale),
                    this.amountAll, this.penalty);
            } else if (DailyShortMessageType.WithholdingUserOverdueTwentyDaysNotification
                .equals(this.type)) { // 代扣用户逾期20天通知短信
                message = String.format(Consts.PosWithHoldingTwentyDaysNormalOverdueUserFormat,
                    this.name, this.amountAll);
            } else if (DailyShortMessageType.WithholdingUserBufferThreeDaysNotification
                    .equals(this.type)) {// 已激活还款缓冲包的用户逾期3天通知短信
            	message = String.format(Consts.PosWithHoldingThreeDaysBufferOverdueUserFormat,
                        this.name, this.bankCardAccount.substring(bankCardAccount.length() - scale),
                        this.amountAll);
            } else {
                // Note: keep the "this.penalty" as old code
                BigDecimal penaltyFee = InstallmentApplicationApi.getAppModel(appId)
                    .getPenaltyFee();
                message = String.format(Consts.PosWithholdingOverdueUserFormat, this.name,
                    this.installment, this.amount, this.penalty, penaltyFee);
            }
        } else { // 其他用户
            if (ApplicationStatus.Completed.equals(this.status)) {
                if (DailyShortMessageType.WithholdingUserOneDayNotification.equals(this.type)) {
                    message = String.format(Consts.WithholdingNDaysNormalUserFormat, this.name,
                        this.installment, this.amount, 1, this.penalty);
                } else {
                    message = String.format(Consts.WithholdingNDaysNormalUserFormat, this.name,
                        this.installment, this.amount, 3, this.penalty);
                }
            } else {
                message = String.format(Consts.PdlWithholdingOverdueUserFormat, this.name,
                    this.installment, this.amount, this.penalty);
            }
        }
        if (provider > 0 ? sender.execute(message, provider) : sender.execute(message)) {
            saver.execute(message);
        }
    }
    
    private String getOpenId(String userId){  
    	WechatUserBaseModel result = HttpClientApi.getGson(Configuration.getSuperAppServiceUrl() + "/openId?userId=" + userId, WechatUserBaseModel.class);
    	String openId = result.getOpenId();
    	Logger.get().info(String.format("getOpenId() openId: %s, userId: %s", openId == null?"":openId, userId));
    	return openId;
    }
    
    private void wechatPush(MessageSender sender, String message, String type){
    	String openId = StringUtils.EMPTY_STRING;
    	
    	boolean getOpenId = true;
    	try{
    		openId = getOpenId(this.userId);
	        
    	}catch(Exception e){
    		getOpenId = false;
    		e.printStackTrace();
    		Logger.get().warn("get openId exception! userId: " + this.userId);
    	}
    	
    	if(getOpenId && !StringUtils.isNullOrWhiteSpaces(openId)){
    		if(!sender.executeWechat(message, openId)){
            	if("D1".equals(type)){
            		Logger.get().warn("D1 wechat push exception! " + message);
            	}else if("D3".equals(type)){
            		Logger.get().warn("D3 wechat push exception! " + message);
            	}
            	
            }
    	}
    }
    
    private void batchpush(String userId, String supperAppMessage, int msgType, String title){
    	try{
	    	List<Map<String, Object>> requestParamList = new ArrayList<Map<String, Object>>();
	    	Map<String, Object> requestParamMap = new HashMap<String, Object>();
	    	requestParamMap.put("_path", "pushCenter");
	    	Map<String, Object> messageMap = new HashMap<String, Object>();
	    	messageMap.put("userIds", Arrays.asList(userId));
	    	messageMap.put("msgTpye", msgType);  // 1、系统通知；2、订单信息；3、还款信息；4、优惠信息；5、借款信息
	    	messageMap.put("appType", "mdxfenqi");
	    	messageMap.put("alert", supperAppMessage);
	    	messageMap.put("action", "MDXIM_STATUS");
	    	messageMap.put("badge", "Increment");
	    	messageMap.put("title", title);
	    	messageMap.put("scheme", "mdx://me/");
	    	messageMap.put("requestId", UUID.randomUUID().toString());
	    	
	    	requestParamMap.put("message", messageMap);
	    	
	    	requestParamList.add(requestParamMap);
	    	
	    	Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
	    	    	
	    	String responseStr = HttpClientApi.postString(Configuration.getUserbaseServiceUrl() + "/iceberg/message/batchpush", GSON.toJson(requestParamList), new HashMap<String, String>());
	    	Map<String, Object> responseMap = GSON.fromJson(responseStr, Map.class);
	    	if((int)responseMap.get("code")==0){
	    		Logger.get().info(String.format("batchpush() succeeded for userId: %s", userId));
	    	}else{
	    		Logger.get().info(String.format("batchpush() failed for userId: %s", userId));
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		Logger.get().warn(String.format("batchpush() exception for userId: %s", userId));
    	}
    }
    
    
    
}



class LegacyMessageHandler extends AbstractMessageHandler {
    private int     payMonth;
    private Integer payDay;
    private String  bankName;
    private String  bankAccount;
    private String  area;

    public LegacyMessageHandler(Map<String, String> messageInfo) {
        super(messageInfo);
        this.area = messageInfo.get("Area");
        this.bankName = messageInfo.get("BankName");
        this.payDay = Integer.parseInt(messageInfo.get("PayDay"));
        setPayMonth();
    }

    @SuppressWarnings("deprecation")
    public void setPayMonth() {
        // 过滤如果用户的约定还款日是1号，表示发送日期为上月，故月份+1
        if (this.payDay == 1 || this.payDay == 2 || this.payDay == 3) {
            this.payMonth = new java.util.Date().getMonth() + 2;
        } else {
            this.payMonth = new java.util.Date().getMonth() + 1;
        }
    }

    @Override
    public void MessageHandler(MessageSender sender, MessageSaver saver) {
        List<String> bankInfo = BankManager.GenerateCorrectBankData(this.area, this.bankName);
        this.bankName = bankInfo.get(0);
        this.bankAccount = bankInfo.get(1);
        BigDecimal penaltyFee = InstallmentApplicationApi.getAppModel(appId).getPenaltyFee();

        if (this.status == ApplicationStatus.Completed) {
            String messageA = "";
            if (MessageDatabaseApi.isPDLApplication(this.appId)) {
                messageA = String.format(Consts.PdlLegacyNormalUserFormat, this.name, this.payMonth,
                    this.payDay, this.itemPrice, this.installment, this.amount, this.bankName,
                    this.bankAccount);
            } else {
                messageA = String.format(Consts.PosLegacyNormalUserFormat, this.name, this.payMonth,
                    this.payDay, this.itemPrice, this.installment, this.amount, penaltyFee,
                    this.bankName, this.bankAccount);
            }
            if (sender.execute(messageA)) {
                saver.execute(messageA);
            } else {
                String messageB = "";
                if (MessageDatabaseApi.isPDLApplication(this.appId)) {
                    messageB = String.format(Consts.PdlLegacyNormalUserFormatBySMS, this.name,
                        this.payMonth, this.payDay, this.itemPrice, this.installment, this.amount);
                } else {
                    messageB = String.format(Consts.PosLegacyNormalUserFormatBySMS, this.name,
                        this.payMonth, this.payDay, this.itemPrice, this.installment, this.amount,
                        penaltyFee);
                }
                if (sender.execute(messageB)) {
                    saver.execute(messageB);
                }
            }
        } else {
            String messageA = "";
            if (MessageDatabaseApi.isPDLApplication(this.appId)) {
                messageA = String.format(Consts.PdlLegacyOverdueUserFormat, this.name,
                    this.bankName, this.bankAccount);
            } else {
                messageA = String.format(Consts.PosLegacyOverdueUserFormat, this.name, penaltyFee,
                    this.bankName, this.bankAccount);
            }

            if (sender.execute(messageA)) {
                saver.execute(messageA);
            } else {
                String messageB = "";
                if (MessageDatabaseApi.isPDLApplication(this.appId)) {
                    messageB = String.format(Consts.PdlLegacyOverdueUserFormatBySMS, this.name);
                } else {
                    messageB = String.format(Consts.PosLegacyOverdueUserFormatBySMS, this.name,
                        penaltyFee);
                }
                if (sender.execute(messageB)) {
                    saver.execute(messageB);
                }
            }
        }
    }
}
