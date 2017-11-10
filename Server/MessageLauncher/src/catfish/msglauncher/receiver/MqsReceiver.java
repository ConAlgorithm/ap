package catfish.msglauncher.receiver;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.queue.QueueApi;
import catfish.msglauncher.sender.MessagesSender;
import catfish.msglauncher.util.MessageLauncherUtil;

import com.google.gson.Gson;

public class MqsReceiver implements Runnable {
	private static ExecutorService mqsReceiverThreadPool;
	private static ExecutorService highPriorityMqsReceiverThreadPool;

	public static void trigger() {
		String normalPriorityQueueName = StartupConfig.get("catfish.normal.priority.msg.queue.name",
				MessageLauncherUtil.MESSAGE_LAUNCHER_QUEUE_NAME);
		String highPriorityQueueName = StartupConfig.get("catfish.high.priority.msg.queue.name",
				"HighPriorityMessageLauncher");
		int mqsReceiverThreadPoolSize = Integer
				.parseInt(StartupConfig.get("catfish.normal.priority.msg.queue.thread.pool.size", "2"));
		int highPriorityMqsReceiverThreadPoolSize = Integer
				.parseInt(StartupConfig.get("catfish.high.priority.msg.queue.thread.pool.size", "2"));
		mqsReceiverThreadPool = Executors.newFixedThreadPool(mqsReceiverThreadPoolSize);
		highPriorityMqsReceiverThreadPool = Executors.newFixedThreadPool(highPriorityMqsReceiverThreadPoolSize);
		for (int i = 0; i < mqsReceiverThreadPoolSize; i++) {
			MqsReceiver mqsReceiver = new MqsReceiver(normalPriorityQueueName);
			mqsReceiverThreadPool.execute(mqsReceiver);
		}
		for (int i = 0; i < highPriorityMqsReceiverThreadPoolSize; i++) {
			MqsReceiver highPriorityMqsReceiver = new MqsReceiver(highPriorityQueueName);
			highPriorityMqsReceiverThreadPool.execute(highPriorityMqsReceiver);
		}
	}

	private String queueName;

	private MqsReceiver(String queueName) {
		this.queueName = queueName;
	}

	public void run() {
		/*
		 * try{ QueueApi.ensureQueueExist(this.queueName, 30L); }catch(Exception
		 * ex){ Logger.get().error("MqsReceiver: ensureQueueExist error", ex); }
		 */

		while (true) {
			try {
				@SuppressWarnings("unchecked")
				Map<String, Object> notification = QueueApi.consumeMessage(Map.class, this.queueName);
				if (notification == null) {
					Logger.get().warn("MqsReceiver: messageData is null from " + this.queueName);
					continue;
				}

				String jsonStr = new Gson().toJson(notification);
				Logger.get().info("Got MQS message: " + jsonStr);
				MessagesSender.handle(jsonStr, 0);
			} catch (Exception ex) {
				Logger.get().error("MqsReceiver: handle mqs notification error", ex);
			}
		}
	}

}
