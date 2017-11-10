package engine.rule.test.data;

import java.util.LinkedList;
import java.util.List;

public class ValidateResult {

private List<IMessage> msg = new LinkedList<>(); 
       
    public synchronized void addMsg(IMessage msgItem)
    {
    	if(msg != null)
    		msg.add(msgItem);
    }
    
    public synchronized void removeMsgs()
    {
    	if(msg != null)
    	{
    		msg.clear();
    	}
    }
    
	public List<IMessage> getMsg() {
		return msg;
	}
	
}
