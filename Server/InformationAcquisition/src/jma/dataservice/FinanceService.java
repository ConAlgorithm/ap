package jma.dataservice;

import java.util.Date;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import catfish.base.CollectionUtils;
import jma.models.FinanceServiceResponse;
import jma.util.HttpUtils;

public class FinanceService {

    public static FinanceServiceResponse<Date> getLastPayBackDateByUserId(String userId) {
        Map<String, String> params = CollectionUtils.mapOf("UserId", userId);
        StringBuilder sb = new StringBuilder(HttpUtils.financeServiceUrl);
        sb.append(HttpUtils.SYMBOL_PATH_SEPARATOR).append("Repayment")
        .append(HttpUtils.SYMBOL_PATH_SEPARATOR).append("LastPaybackDate")
        .append(HttpUtils.SYMBOL_QUESTION_MARK)
        .append(HttpUtils.buildGetParamString(params));
        
        return HttpUtils.invokeGet(sb.toString(), new TypeToken<FinanceServiceResponse<Date>>(){}.getType());
    }
}
