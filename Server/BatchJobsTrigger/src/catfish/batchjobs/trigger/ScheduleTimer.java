package catfish.batchjobs.trigger;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import catfish.base.Logger;


public class ScheduleTimer{
	
	private Date startTime;
	private TimerTask timerTask;
	private long period;
	private SimpleDateFormat sdf;
	
	
	public ScheduleTimer(){
		
	}
	

	public void run(){
		Timer timer=new Timer();
		
		timer.schedule(timerTask, startTime, period);
	}
	
	
	public void setTimerTask(TimerTask timerTask){
		this.timerTask=timerTask;
	}
	
	public void setPeriod(long period){
		this.period=period;
	}
	
	public String getTime(){
		Date date=new Date();
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
	}
	
	private String getTimeClear(){
		Date date=new Date();
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
		
	}
	
	public void setStartTime(String setDateTime){
		
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startTime=sdf.parse(setDateTime);
			
			//设置时间已过则定为明天执行
			if(sdf.parse(getTimeClear()).getTime() >startTime.getTime()){
				startTime=new Date(startTime.getTime()+86400000);
				Logger.get().info(setDateTime+" is late,task will run at "+sdf.format(startTime));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	


}
