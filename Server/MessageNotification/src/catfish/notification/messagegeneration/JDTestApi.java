package catfish.notification.messagegeneration;

import grasscarp.application.model.POSApplication;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.InstalmentChannel;
import catfish.notification.common.Receiver;

public class JDTestApi {

	  /****************京东试点项目过渡********************/
	  public static boolean isJDTestProduct(String productId)
	  {	  
		  String productName = MessageDataRequestApi.getProdNameById(productId);
		  return productName != null && productName.equals(StartupConfig.get("jdTest.product", "京东试点产品"));
	  }
	  
	  public static Receiver getReceiver(String appId)
	  {
		//根据是否京东试点产品调整接收者
		    Receiver receiver = null;
		    POSApplication app = (POSApplication)MessageDataRequestApi.getApp(appId, InstalmentChannel.App.getValue());
		    if(app == null)
		    {
		    	Logger.get().warn(String.format("Judge whether is jd test product of appId: %s error", appId));
		    	return null;
		    }
		    receiver = isJDTestProduct(app.getProductId())? Receiver.D1 : Receiver.S1;
		    return receiver;
	  }
	  /**************************************************/
}
