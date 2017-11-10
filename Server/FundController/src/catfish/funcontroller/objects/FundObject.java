package catfish.funcontroller.objects;

import java.math.BigDecimal;
import java.util.Date;

public class FundObject {
	public String Id;
	
	public String FundTag ;

    public String FundName ;

    public BigDecimal CostOfCapital ;

    public BigDecimal FundSize ;

    public BigDecimal Balance ;

    public Date InvestStartTime ;

    public Date InvestEndTime ;

    public Boolean IsActive ;
}
