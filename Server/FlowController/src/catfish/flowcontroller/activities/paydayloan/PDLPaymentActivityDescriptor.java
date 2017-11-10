package catfish.flowcontroller.activities.paydayloan;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.ActivityDescriptor;

public class PDLPaymentActivityDescriptor extends ActivityDescriptor{

	private static final String FinalCheck = "FinalCheck";
	private static final String MoneyTransferSubmitted = "MoneyTransferSubmitted";
	
	private static final int PreSelected = 1;
	private static final int DelaySelected = 2;
	
	public PDLPaymentActivityDescriptor() {
		super("CheckInformation");
	}

	@Override
	public String getDescription(String activity, Map<String, Object> state, Properties resources, List<QueueMessager> messages){
	 
		boolean preSelected = false;
		
		boolean needSelectProduct = true;
		
		for(QueueMessager msg : messages)
		{
			String jobName = msg.getJobName();
			if(jobName != null)
			{
				if(jobName.equalsIgnoreCase(FinalCheck))
				{
					Integer dataInt = msg.getJobDataInt();
					if(dataInt != null)
					{
						if(PreSelected == dataInt)
						{
							preSelected = true;
						}
					}
				}
				if(!preSelected && jobName.equalsIgnoreCase(MoneyTransferSubmitted))
				{
					needSelectProduct = false;
				}
			}		
		}
		if(preSelected || !needSelectProduct)
		{
			return resources.getProperty("DoPayment");
		}
		else if(!preSelected && needSelectProduct){
			return resources.getProperty("NeedSelectProduct");
		}else{
			return null;
		}
	}
}
