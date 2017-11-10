package catfish.flowcontroller.test;

public class MessageInfo{
    public String jobName;
    public String queueName;
    
    public MessageInfo(String queueName, String jobName){
        this.jobName = jobName;
        this.queueName = queueName;
    }
}
