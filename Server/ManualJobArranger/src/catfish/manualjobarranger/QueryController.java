package catfish.manualjobarranger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.manualjobarranger.cashloan.CLUtil;

public class QueryController {
  private static final int JOB_BUFFER = 60;    // seconds

  private static final Comparator<MessageEntity> OFFLINE_COMPARATOR =
      new Comparator<MessageEntity>() {
        @Override
        public int compare(MessageEntity x, MessageEntity y) {
          return - Integer.compare(
              Configuration.getArrangerConfig().getOfflineJobOrders().indexOf(x.getQueueName()),
              Configuration.getArrangerConfig().getOfflineJobOrders().indexOf(y.getQueueName()));
        }
      };

  public static MessageEntity selectMessage(List<String> products, String userId) {
    MessageEntity message = selectMessage(products, userId, true, new OnlineJobComparator(
        Math.max(1, ManualJobDatabaseApi.queryExamingAppCount()),
        Math.max(1, ManualJobDatabaseApi.queryOnlineExaminerCount(
            Configuration.getArrangerConfig().getExaminerPrefixes()))));
    if (message == null) {
      message = selectMessage(products, userId, false, OFFLINE_COMPARATOR);
    }
    return message;
  }

  public static MessageEntity queryMessageCount(List<String> products, String userId) {
    MessageEntity message = new MessageEntity();
    message.setJobCount(ManualJobDatabaseApi.retrieveMessageCount(products, userId));
    message.setSuccess(true);
    return message;
  }

  private static MessageEntity selectMessage(
      List<String> products,
      String userId,
      boolean isRealtime,
      Comparator<MessageEntity> comparator) {
    List<MessageEntity> messages =
        ManualJobDatabaseApi.retrieveMessages(products, userId, isRealtime);
    Logger.get().debug("retrieve message is : " + new Gson().toJson(messages));
    CLUtil.implementCashLoanMessages(messages);
    Collections.sort(messages, comparator);

    for (MessageEntity message : messages) {
      if (!ManualJobDatabaseApi.deleteMessage(message.getId())) {
        Logger.get().debug("Job has been taken: " + message.getMessageHandle());
        continue;
      }
      try {
//        message.setMessageHandle(GeneralQueueApi.ChangeTimeout(
//            message.getQueueName(),
//            message.getMessageHandle(),
//            message.getJobDuration() + JOB_BUFFER));
    	 boolean useNewStrategy = Configuration.isUseNewStrategy();
    	 int jobReserveSeconds = Configuration.getJobReserveSeconds();
    	 if(useNewStrategy)
    	 {
    		 //使用新的调度策略
    		 //这里将message的timeout设置为一个较短的时间
    		 //在这段时间内，catfish.admin的审核页面展示该job后，调用后台接口发送确认信息
    		 //catfish.admin的后台服务将相应message的timeout时间再设置为JobDuration
    		 //这样，一旦catfish.admin未收到该job，或者前台页面未能正常展示该job的话，后台就无法收到前台页面的确认信息
    		 //该message很快就会超时，重新回到队列，从而防止了该job丢失
    		 message.setMessageHandle(GeneralQueueApi.ChangeTimeout(
		            message.getQueueName(),
		            message.getMessageHandle(),
		            jobReserveSeconds));
    	 }
    	 else
    	 {
    		 message.setMessageHandle(GeneralQueueApi.ChangeTimeout(
				 message.getQueueName(),
				 message.getMessageHandle(),
				 message.getJobDuration() + JOB_BUFFER));
    	 }
    	  
      } catch (Exception e) {
        Logger.get().warn("Job probably has already expired: " + message.getMessageHandle(), e);
        continue;
      }
      return message;
    }
    return null;
  }

}
