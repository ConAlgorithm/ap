package catfish.notification;

import java.util.HashMap;
import java.util.Map;

import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.GenericTextMsgDefinition;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.SimpleMessageDefinition;
import catfish.notification.messagegeneration.custom.*;
import catfish.notification.messagegeneration.custom.pdlcredit.PDLAccountantReminderDefinition;
import catfish.notification.messagegeneration.custom.pdlcredit.PDLRepaymentFailedDefinition;
import catfish.notification.messagegeneration.custom.pdlcredit.PDLRepaymentReminderDefinition;
import catfish.notification.messagegeneration.custom.pdlcredit.PDLRepaymentSuccessDefinition;
import catfish.notification.messagegeneration.custom.zerofee.AfterCreatApplication;

public class MessageConfig {

  public static class NotificationKeys {
    public static final String NOTIFICATION_NAME = "NotificationName";
    public static final String APP_ID = "AppId";
    public static final String USER_ID = "UserId";
    //客服短信参数
    public static final String MOBILE = "mobile";
    public static final String CONTENT = "content";
  }

  private static Map<String, MessageDefinition> definitions = new HashMap<>();

//  private static Map<String, String> notificationAppMessageMap = new HashMap<>();

  public static void initialize() {
    addCustomerMsgDefinition("Cancel", null);
    addCustomerMsgDefinition("CancelAfterLoanCheck", null);
    addCustomerMsgDefinition("IOUCheckPassed", null);
    addCustomerMsgDefinition("IOUCheckDone", null);
    addCustomerMsgDefinition("ReUploadHeadPhoto", null);
    addCustomerMsgDefinition("ReUploadIdPhoto", null);
    addCustomerMsgDefinition("ReUploadBankPhoto", null);
    addCustomerMsgDefinition("Reject", null);
    addCustomerMsgDefinition("UploadValidIOU", null);
    addCustomerMsgDefinition("IOUCheckRejected", null);
    addCustomerMsgDefinition("NoFileNeedUpload", null);
    addCustomerMsgDefinition("LoanVoucherApproved", null);
    addCustomerMsgDefinition("SendLoanMoneyShortMessage", Channel.SHORTMESSAGE);
    definitions.put("SendValidationCode", new SendValidationCodeMessageDefinition());
    definitions.put("SendValidationCodeFromApp", new SendValidationCodeMessageFromAppDefinition());
    definitions.put("SendAuthValidationCodeFromApp", new SendAuthValidationCodeMessageFromAppDefinition());
    definitions.put("SendAuthValidationVoiceCodeFromApp", new SendAuthValidationVoiceCodeMessageFromAppDefinition());
    definitions.put("SendS1AuthValidationCodeFromMCApp", new SendS1AuthValidationCodeMessageFromMCAppDefinition());
    definitions.put("SendUserAuthValidationCodeFromMCApp", new SendUserAuthValidationCodeMessageFromMCAppDefinition());
    definitions.put("SendUserAuthValidationCodeFromMCWX", new SendUserAuthValidationCodeMessageFromMCWXDefinition());
    definitions.put("StartApplyFromApp", new StartApplyMessageFromAppDefinition());
    definitions.put("ReuploadFiles", new ReuploadFileMessageDefinition());
    definitions.put("ApproveAndUploadIou", new ApproveAndUploadIouMessageDefinition());
    definitions.put("ReuploadIou", new ReuploadIouMessageDefinition());
    definitions.put("PreApprovedAndUploadFiles", new PreApproveAndUploadFileMessageDefinition());
    definitions.put("RejectApplication", new RejectApplicationMessageDefinition());
    definitions.put("ReuploadFile", new ReuploadFileMessageDefinition());
    definitions.put("ReuploadFileUserOnly", new ReuploadFileUserOnlyMessageDefinition());
    definitions.put("PreApprovedApplication", new PreApproveAndUploadFileMessageDefinition());
    definitions.put("MoneyTransferred", new MoneyTransferredMessageDefinition());
    definitions.put("IOUIdentifierInvalid", new IOUIdentifierInvalidMessageDefinition());
    definitions.put("IOUIdentifierNotGenerated", new IOUIdentifierNotGeneratedMessageDefinition());
    definitions.put("Raw", new RawMessageDefinition());
    definitions.put("RawToApp", new RawToAppMessageDefinition());
    definitions.put("RawToAppOperation", new RawToAppOperationMessageDefinition());
    definitions.put("RawShortMessage", new RawShortMessageDefinition());
    definitions.put("RawOverdueMessage", new RawOverdueMessageDefinition());
    definitions.put("StartApply", new StartApplyMessageDefinition());
    definitions.put("ApplicationClosedInAdvanced", new ApplicationClosedInAdvancedMessageDefinition());
    definitions.put("ApplicationClosed", new ApplicationClosedMessageDefinition());
    definitions.put("UploadBuckle", new BuckleUploadMessageDefinition());
    definitions.put("SendDailyShortMessage", new DailyShortMessageDefinition());
    definitions.put("DsfWithholdingFailOnce", new DsfWithholdingMsgDefinition("FailOnce"));
    definitions.put("DsfWithholdingFailTwice", new DsfWithholdingMsgDefinition("FailTwice"));
    definitions.put("DsfWithholdingOverdueSuccess", new DsfWithholdingMsgDefinition("OverdueSuccess"));
    definitions.put("DsfWithholdingSuccess", new DsfWithholdingMsgDefinition("Success"));
    definitions.put("GenericTextMessage", new GenericTextMsgDefinition());
    definitions.put("WeiXinRedPack", new RedPackMessageDefinition());
    definitions.put("UploadFiles", new UploadFilesMessageDefinition());
    definitions.put("WeiXinQrCode", new WeiXinQrCodeMessageDefinition());
    definitions.put("PaybackEntry", new PaybackEntryMsgDefinition());
    definitions.put("LoanMoneySuccess", new LoanMoneySuccessMsgDefinition());
    definitions.put("PDLApprove", new PDLApproveMessageDefinition());
    definitions.put("PDLReject", new PDLRejectMessageDefinition());
    definitions.put("PDLLoanSuccess", new PDLLoanSuccessMessageDefinition());
    definitions.put("PDLLoanFail", new PDLLoanFailMessageDefinition());
    definitions.put("PdlRepaymentReminder", new PdlRepaymentReminderMessageDefinition());
    definitions.put("SendLuckDrawMessage", new LuckDrawMsgDefinition());
    definitions.put("ZeroFeeAfterCreateApplication", new AfterCreatApplication());
    definitions.put("PDLFrozen", new PDLFrozenMessageDefinition());
    
    //PDL credit message
    definitions.put("PDLCreditAccountantReminder", new PDLAccountantReminderDefinition());
    definitions.put("PDLCreditRepaymentReminder", new PDLRepaymentReminderDefinition());
    definitions.put("PDLCreditRepaymentSuccess", new PDLRepaymentSuccessDefinition());
    definitions.put("PDLCreditRepaymentFailed", new PDLRepaymentFailedDefinition());

    // D1 redpack
    definitions.put("PosD1Redpack", new D1RedpackMsgDefinition());
    
    definitions.put("WebchatUserMessage", new WebchatUserMessageDefinition());
    definitions.put("FreeContentMessage", new FreeContentMessageDefinition());

  }

  public static MessageDefinition getDefinitionBy(String messageName) {
    return definitions.get(messageName);
  }

//  public static String getMessageNameBy(String notificationName)
//  {
//	  if (notificationAppMessageMap.containsKey(notificationName))
//	  {
//		  return notificationAppMessageMap.get(notificationName);
//	  }
//	  return null;
//  }

  private static void addCustomerMsgDefinition(String name, Channel channel) {
    definitions.put(name, new SimpleMessageDefinition(Receiver.CUSTOMER, channel,
      ResourceConfig.getResourceByName(name, Receiver.CUSTOMER)));
  }
//  private static void addCustomerShortMessage(String name, String content) {
//    definitions.put(name, new AppMessageDefinition(Receiver.CUSTOMER_SHORT_MESSAGE, content));
//  }
}
