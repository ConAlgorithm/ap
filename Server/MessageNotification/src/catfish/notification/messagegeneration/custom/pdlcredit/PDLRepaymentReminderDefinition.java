package catfish.notification.messagegeneration.custom.pdlcredit;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.message.ShortMessage;
import catfish.notification.messagegeneration.AccountServiceApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.PDLBusinessServiceApi;
import catfish.notification.messagegeneration.PDLBusinessServiceApi.NotificationInfoModel;
import grasscarp.account.model.MobileHistory;

public class PDLRepaymentReminderDefinition implements MessageDefinition {

	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
		String userId = (String) receivedMessage.get(NotificationKeys.USER_ID);
		String phone = null;
		NotificationInfoModel model = PDLBusinessServiceApi.getNotificationInfoModel(userId);
		
		List<MobileHistory> mobiles = AccountServiceApi.getUserMobiles(userId);
	    if(!org.springframework.util.CollectionUtils.isEmpty(mobiles) && mobiles.size() > 0){
	        phone = mobiles.get(mobiles.size() - 1).getMobile();
	    }
	    
	    String bankCardNumber = model.getBankInfo().getBankCardAccount();
	    String subBankCardNumber = bankCardNumber.substring(bankCardNumber.length() -4 , bankCardNumber.length()); 
	    
	    
		@SuppressWarnings("deprecation")
		String shortMessageContent = String.format(ResourceConfig.getResourceByName(
				this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.SHORTMESSAGE, Product.PDL),
				model.getBill().getBillStartDate().getMonth() + 1, model.getBill().getTotalAmount(), model.getPlanAmount().getPlanAmount(),
				subBankCardNumber);
		
		return Arrays.<Message> asList(
				new ShortMessage(receivedMessage, phone, shortMessageContent));
	}
	
}
