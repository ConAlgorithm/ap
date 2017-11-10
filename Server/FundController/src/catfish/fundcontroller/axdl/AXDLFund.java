package catfish.fundcontroller.axdl;

/**
 * Created by hary on 15/9/9.
 */

import catfish.base.StartupConfig;
import catfish.funcontroller.data.DataService;
import catfish.funcontroller.objects.FundObject;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


public class AXDLFund {

    private static String fundtag= StartupConfig.get("catfish.axdl.fundtag", "AXDL");

    private FundObject fund;

    public AXDLFund(){
        this.fund=new DataService().getFundByFundTag(fundtag);
    }

    public String getFundTag(){
        return fundtag;
    }

    public FundObject getFund(){
        return fund;
    }

    public BigDecimal getLoanableAmount(){

        Date startdate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE,1);

        Date enddate=calendar.getTime();
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
