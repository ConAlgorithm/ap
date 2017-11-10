package catfish.funcontroller.data;

import java.math.BigDecimal;
import java.util.Date;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;
import catfish.funcontroller.objects.FundObject;

public class DataService {

	public FundObject getFundByFundTag(String fundTag){
		if(fundTag==null){
			Logger.get().error("getFundByFundTag 传入参数为空");
			return null;
		}
        FundDao dao = new FundDao();
        FundObject fundObj = dao.getFundByFundTag(fundTag);
        return fundObj;
    }

	// 获取某个fund在(begin, end]时间段借出去的钱
	// sql:
	// MoneyTransferredOn 贷出去时间不为空的都算
	//
	public BigDecimal getLoanedAmount(String fundId, Date begin, Date end) {

	    String sql = "select sum(Principal) from InstallmentApplicationObjects "
	        + "where FundId = :fundId and DateAdded < CONVERT(datetime, convert(char(10),:end,112)) and "
	        + "((MoneyTransferredOn is not null and DateAdded > CONVERT(datetime, convert(char(10),:begin,112)))) ";

	    BigDecimal result = DatabaseApi.querySingleResultOrDefault(
	        sql,
	        CollectionUtils.mapOf("begin", begin, "end", end, "fundId", fundId),
	        DatabaseExtractors.DECIMAL_EXTRACTOR,
	        BigDecimal.ZERO);

	    return result == null ? BigDecimal.ZERO : result;
	  }

}
