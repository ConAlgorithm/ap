package catfish.monitor;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public String _id;
    public String appId;
    public String workflow;
    public long startTime = -1;
    public long endTime = -1;
    
    public Map<String, String> states = new HashMap<String, String>();
    public Map<String, Long> waitingTime = new HashMap<String, Long>();
}
