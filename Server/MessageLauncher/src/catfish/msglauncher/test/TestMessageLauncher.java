package catfish.msglauncher.test;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.queue.QueueApi;
import catfish.base.queue.QueueConfig;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.message.MessageLauncherMessage;
import catfish.msglauncher.message.ShortMessage;
import catfish.msglauncher.util.MessageLauncherUtil;
import catfish.msglauncher.util.MessageType;

public class TestMessageLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartupConfig.initialize();
	    Logger.initialize();

	    QueueConfig.initialize();
	    Configuration.initialize();
	    
	    ShortMessage smg = new ShortMessage(null, "13764648731", null);
	    smg.setTemplateCode("ALL_SHORTMESSAGE_CUSTOMER_SendValidationCodeMessageDefinition");
//	    smg.setContentJson("{\"sum\":\"3.0\",\"amount\":\"4.0\",\"var\":\"5.0\"}");
	    smg.setContentJson("{\"code\":\"33\"}");
	    
	    Gson gson = new Gson();
	    MessageLauncherMessage mlMessage = new MessageLauncherMessage();
		mlMessage.setMessageType(MessageType.SHORTMESSAGE);
		mlMessage.setMessage(smg);
		String mlMessageString = gson.toJson(mlMessage);
		QueueApi.writeMessage(MessageLauncherUtil.MESSAGE_LAUNCHER_QUEUE_NAME, mlMessageString, 1, 0);
	}

}
