package catfish.fundcontroller.zrb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import catfish.base.StartupConfig;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.funcontroller.data.DataService;
import catfish.funcontroller.objects.FundObject;

public class ZRBFund {

	private static String fundtag=StartupConfig.get("catfish.zrb.fundtag","ZhenRongBao");
	private FundObject fund;
	public ZRBFund(){
		this.fund=new DataService().getFundByFundTag(fundtag);
	}
	public String getFundTag(){
		return fundtag;
	}
	
	public FundObject getFund(){
		return fund;
	}
	
	public BigDecimal getLoanableAmount(){
		Date startdate=new Date();
		Date enddate=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE,1);
        enddate=calendar.getTime();
		BigDecimal loanedAmount=new DataService().getLoanedAmount(this.fund.Id, startdate, enddate);
		return this.fund.FundSize.subtract(loanedAmount);
	}
	
	public boolean isAvailable(BigDecimal principal){
		if(principal.compareTo(getLoanableAmount())<=0){
			return true;	
		}
		return false;
	}
	
}
