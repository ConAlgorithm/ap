package export.migration.qhzx;

import java.util.ArrayList;
import java.util.List;

import jma.handlers.CheckUserCreditOn3rdPartyHandler;

import org.joda.time.DateTime;

import thirdparty.qhzx.DomainResult;
import thirdparty.qhzx.QhzxApi;
import catfish.base.business.common.InstalmentChannel;

public class CheckUserOnQHHandler extends CheckUserCreditOn3rdPartyHandler{

	private static final String NULL = " ";
	
	public CheckUserOnQHHandler()
	{
		this.channel = InstalmentChannel.App;
	}
	
	public void setId(String appId)
	{
		this.appId = appId;
	}
	
	public boolean checkValid()
	{
		return this.getPreModel() != null && this.getPreModel().userName != null && this.getPreModel().userIdNumber != null;
	}
	
	public String[] getResult()
	{
		DomainResult result = QhzxApi.query(appId, this.getPreModel().userIdNumber, this.getPreModel().userName, isNewQhzxQuery());

		List<String> line = new ArrayList<>();
		line.add(appId);
		
	    if (result.isHasBlacklist()) {
	      line.add("true");
	    }else{
	    	line.add(NULL);
	    }
	    if (result.isHasBeenExecuted()) {
	      line.add("true");
	    }else{
	    	line.add(NULL);
	    }
	    if (result.isHasBeenBrokenPromise()) {
	      line.add("true");
	    }else{
	    	line.add(NULL);
	    }
	    if (result.isHasBeenBroeknPromiseAndExecuted()) {
	      line.add("true");
	    }else{
	    	line.add(NULL);
	    }
	    if (result.getLatestBuildTime() != null) {
	      line.add(new DateTime(result.getLatestBuildTime()).toString("yyyy-MM-dd HH:mm:ss.SSS"));
	    }else{
	    	line.add(NULL);
	    }
	    if (result.getMaxOverdueGrade() > 0) {
	      line.add(""+result.getMaxOverdueGrade());
	    }else{
	    	line.add(NULL);
	    }
	    if (result.getMaxMoneyBound() > 0) {
	      line.add(""+result.getMaxMoneyBound());
	    }else
	    {
	    	line.add(NULL);
	    }
	    return line.toArray(new String[]{});
	}
}
