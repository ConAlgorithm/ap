package catfish.batchjobs.trigger;

import java.util.TimerTask;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.queue.QueueMessager;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.services.coordination.CoordinationService;
import catfish.services.coordination.DataCoordinator;

public class Trigger implements  Runnable{

	private final static String DATA="BatchJobs";
    private IQueue fcQueue;
    private DataCoordinator dataCoordinator;
    private String uid;
    private ScheduleTimer scheduleTimer;
	
    
	public Trigger(IQueueService queueService,CoordinationService cs) {
		// TODO Auto-generated constructor stub
		fcQueue = queueService.getQueue("JobStatusQueue","BatchJobsTrigger");

        
        
        dataCoordinator = cs.registerData(DATA);
        
        new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		scheduleTimer=new ScheduleTimer();
		Logger.get().info("start to setup startTime.......");
		String startTime=scheduleTimer.getTime()+" "+StartupConfig.get("catfish.trigger.timer.startTime");
		Logger.get().info("startTime: "+startTime+" setup");
		scheduleTimer.setStartTime(startTime);
		Logger.get().info("start to setup period.......");
		long period=StartupConfig.getAsLong("catfish.trigger.timer.period");
		Logger.get().info("period: "+period+"ms setup");
		scheduleTimer.setPeriod(period);
		scheduleTimer.setTimerTask(getTask());
		scheduleTimer.run();
	}
	

	
	
	public TimerTask getTask(){
    	return new TimerTask() {
			
			@Override
			public void run() {
				uid="BatchJobs:"+scheduleTimer.getTime();
				try
		        {
		            dataCoordinator.lock(uid.toUpperCase());
		            try{
		                if(dataCoordinator.existChildNode(uid)){
		                    Logger.get().info("already triggered batch jobs:" + uid);
		                    return;
		                }
		            }catch(Exception e){
		                Logger.get().error("failed to check batch jobs:" + uid);
		                //TODO: improve exception handling: retry?
		                return;
		            }
		            QueueMessager messager = new QueueMessager(uid, "triggerBatchJobs","batchjobs");
		            fcQueue.sendMessage(messager.getJobName(), messager);
		            //TODO: maybe need retry
		            boolean result = dataCoordinator.createChildNode(uid, true);
		            if(result){
		                Logger.get().info("triggered batch jobs:" + uid);
		            } else {
		                Logger.get().error("failed to save triggered batch jobs:" + uid);
		            }
		        }finally{
		            dataCoordinator.releaseLock(uid.toUpperCase());
		        }
				
			}
		};
    }


}


