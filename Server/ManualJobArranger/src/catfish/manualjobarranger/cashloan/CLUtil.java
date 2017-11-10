package catfish.manualjobarranger.cashloan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.manualjobarranger.MessageEntity;

public class CLUtil {

  public static void implementCashLoanMessages(List<MessageEntity> messages) {
    Set<String> cashLoanAppIds = new HashSet<>();
    List<MessageEntity> cashLoanMessages = new ArrayList<>();
    for (MessageEntity message : messages) {
      if (message.getChannel() == -1) {
        cashLoanAppIds.add(message.getAppId());
        cashLoanMessages.add(message);
      }
    }
    Logger.get().debug("cashloan appids " + new Gson().toJson(cashLoanAppIds));
    if (cashLoanAppIds.isEmpty()) {
      return;
    }

    List<CLApplicationSubmittedModel> models =
        CLApplicationService.getApplicationSubmittedDates(new ArrayList<>(cashLoanAppIds));
    Logger.get().debug("cashloan appids " + new Gson().toJson(cashLoanAppIds));
    if (models == null) {
    	Logger.get().warn("get cashloan model is null!");
      return;
    }

    for (CLApplicationSubmittedModel model : models) {
      for (MessageEntity message : cashLoanMessages) {
        if (message.getAppId().equalsIgnoreCase(model.getAppId())) {
          message.setAppSubmittedDate(model.getSubmitDate());
          message.setChannel(InstalmentChannel.CashLoan.getValue());
        }
      }
    }
  }

}
