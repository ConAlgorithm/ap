package catfish.flowcontroller.activities.test;

import catfish.base.Logger;
import catfish.flowcontroller.test.Application;
import catfish.flowcontroller.test.MessageData;
import catfish.framework.IServiceProvider;

public class MessageActivity extends Activity{
    public String notificationName;
    public String extraInfo;

    public void run(Application app, IServiceProvider sp){
    }
    
    public boolean complete(Application app, IServiceProvider sp){
        MessageData messageData = app.messageData;
        if(messageData == null){
            return false;
        }
        
        if( notificationName.equals(messageData.notificationName)){
            if(extraInfo != null && extraInfo.equals(messageData.extraInfo)){
                app.messageData = null;
                return true;
            } else {
                String errMsg = "Same notificationName, but different extraInfo";
                Logger.get().error(errMsg);
                throw new RuntimeException(errMsg);
            }
        }
        return false;
    }
}
