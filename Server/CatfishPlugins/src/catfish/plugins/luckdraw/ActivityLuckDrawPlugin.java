package catfish.plugins.luckdraw;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.ActivityLuckDrawConfigDao;
import catfish.base.business.dao.ActivityLuckDrawPrizeDao;
import catfish.base.business.dao.ActivityLuckDrawRecordDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.execution.ExecutionType;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.ActivityLuckDrawConfigObject;
import catfish.base.queue.QueueMapMessager;
import catfish.base.queue.QueueMessages;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.messageservice.Message;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.plugins.redpack.ActivityService;

public class ActivityLuckDrawPlugin implements IPlugin, IQueueListener {
	private IMessageService messageService;

	public ActivityLuckDrawPlugin() {
	}

	public ActivityLuckDrawPlugin(ActivityService activityService) {

	}

	@Override
	public void init(IServiceProvider sp) {
		messageService = sp.getService(IMessageService.class);
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue queue = queueService.getQueue("CatfishServerQueue");
		queue.register(QueueMessages.APPMONEY_TRANSFERRED, this);
	}

	@Override
	public void onMessage(String message, String data) {
		Logger.get().info(
				String.format("%s:%s:%s", this.getClass().getName(), message,
						data));
		QueueMapMessager dataMap = QueueMapMessager.fromString(data);
		if (dataMap == null) {
			return;
		}
		if (QueueMessages.APPMONEY_TRANSFERRED.equals(message)) {
			onAppMoneyTransferred(dataMap.getAsString("appId"));
		}
	}

	// 处理POS的多个申请做单时的并发情况
	private void onAppMoneyTransferred(String appId) {
		Logger.get().info("Get message AppMoneyTransferred for luckdraw. AppId: " + appId);
		Integer instalmentChannel = InstallmentApplicationDao.getApplicationTypeById(appId);
        if(instalmentChannel == null ||  instalmentChannel == ExecutionType.PayDayLoan.getValue())
            return;
		String recordId = doLuckDraw(appId);
		if (recordId != null) {
			Logger.get().info("Luckdraw database update successfully. AppId: " + appId);
			sendMessageToCustomer(appId);
			Logger.get().info("Message of luckdraw are sent out. AppId: " + appId);
		}
	}

	private String doLuckDraw(String appId) {
		List<ActivityLuckDrawConfigObject> relatedPOSConfigs = ActivityLuckDrawConfigDao
				.getAvaiableActivityLuckDrawConfigsByAppId(appId);
		String defaultPrizeId = ActivityLuckDrawPrizeDao.getDefaultPrizeId();
		if ((relatedPOSConfigs == null || relatedPOSConfigs.size() == 0) &&
			(defaultPrizeId == null || defaultPrizeId.isEmpty())){
			Logger.get().info(String.format("appId:%s, not available in activity.",appId));
			return null;
		}
		int prizeCategoryCount = relatedPOSConfigs.size();
		ActivityLuckDrawConfigObject luckDrawConfig = null;
		String POSId = MerchantStoreDao.getMerchantStoreId(appId);
		if(prizeCategoryCount == 0) {
			return ActivityLuckDrawRecordDao.insertLuckDrawRecord(appId, POSId, defaultPrizeId);
		} else {
			Boolean isRandom = StartupConfig.getAsBoolean("catfish.luckdraw.isRandom");
			Random ran = new Random();
			luckDrawConfig = relatedPOSConfigs.get(ran.nextInt(relatedPOSConfigs.size()));
			if(isRandom && ran.nextBoolean()) {
					return ActivityLuckDrawRecordDao.insertLuckDrawRecord(appId, POSId, defaultPrizeId);
			} else {
				if (ActivityLuckDrawConfigDao.updatePOSConfig(luckDrawConfig.Id)) {
					return ActivityLuckDrawRecordDao.insertLuckDrawRecord(appId, luckDrawConfig.POSId, luckDrawConfig.LuckDrawPrizeId);
				} else {
					return null;
				}
			}
		}
	}

	private void sendMessageToCustomer(String appId) {
		try {
			Logger.get().info("Ready to sendLuckDraw message appId: " + appId);
			messageService.sendMessage("SendLuckDrawMessage", appId, "");
		} catch (Exception e) {
			Logger.get().error("SendLuckDraw message failed: " + e);
		}
	}

}
