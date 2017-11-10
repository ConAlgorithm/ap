/**
 * 
 */
package jma.handlers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.httpclient.HttpClientApi;
import jma.Configuration;
import jma.JobHandler;
import jma.RetryRequiredException;
import net.sf.json.JSONObject;

/**
 * @author dengw
 *
 */
public class CheckCLCreditInfoHandler extends JobHandler {

	@Override
	public void execute(String appId) throws RetryRequiredException {
		Logger.get().info("CheckCLCreditInfoHandler execute appId:" + appId);
		try {
			Map<String, Object> param = getCreditInfo(appId);
			if (param == null) {
                Logger.get().info("get LTV CreditInfo param is null");
                return;
            }
			
			String principal = String.valueOf(param.get("principal"));
			BigDecimal result = new BigDecimal(principal);
			
			Logger.get().info("get cashloan applicationCredit:" + result);

            AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId,
                AppDerivativeVariableNames.CL_ApplicationCredit, result));
		} catch (Exception e) {
			Logger.get().error(String.format("exception occurred!appId=%s", appId), e);
		}
		
	}
	
	/**
     * <p>〈获取请求的LTV借款金额〉</p>
     * 
     * @param appId
     * @return
     */
    protected Map<String, Object> getCreditInfo(String appId) {
        Map<String, Object> param = new HashMap<String, Object>();       
        String cowfishHost = Configuration.getCowfishUrl(); 
        String appInfoUrl = cowfishHost + "/application/" + appId; 
        
        String appInfoResponse = HttpClientApi.get(appInfoUrl);
        JSONObject appJson = JSONObject.fromObject(appInfoResponse);
        
        String principal = appJson.getString("principal");
        
        param.put("principal", principal);
        
        return param;
    }

}
