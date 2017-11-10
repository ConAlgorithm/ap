package jma.handlers;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.httpclient.HttpClientApi;
import grasscarp.application.model.POSApplication;
import grasscarp.product.model.CellPhone;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.util.GrasscarpClient;

// this handler is for app product only
public class CalculateApplicationMaxCreditHandler extends NonBlockingJobHandler{
	public static String productUrl=StartupConfig.get("product.url");
    @Override
    public void execute(String appId) throws RetryRequiredException {
        
        POSApplication app = GrasscarpClient.getPosApplicationById(appId);
        
        if (app == null) {
          return;
        }
        BigDecimal defineInterestValue = getDefineInterestValue(app.getProductId());
        BigDecimal max = null;
        if (app.getProductId() != null && !StringUtils.isNullOrWhiteSpaces(app.getProductName())) {                        
        	CellPhone cellPhone = GrasscarpClient.getCellPhone( app.getProductId(), _encodeString(app.getProductName()), app.getMerchantStoreId());        
        	if(cellPhone != null) {
        		max = cellPhone.getMax();
        	}
        }else {
        	Logger.get().warn("get application info from service with bad params: productId--" + 
        	          app.getProductId() + " cellPhone name--" + app.getProductName()); 
        }
        
        AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        		.isNotNullAdd(AppDerivativeVariableNames.ApplicationMaxCredit, max)
        		.isNotNullAdd(jma.models.DerivativeVariableNames.INITPROD_RATE, defineInterestValue)
        		.build());
    }
    /**
     * 名义利率
     * @param productId
     */
    protected BigDecimal getDefineInterestValue(String productId) {
    	if(productId == null || productId.isEmpty()) {
    		Logger.get().info("appId = "+appId+" ,productId is empty");
    		return null;
    	}
    	
    	String url = String.format("%s/product/%s", productUrl, productId);
    	String result = HttpClientApi.get(url);
    	if(result == null || result.isEmpty()) {
    		return null;
    	}
    		
    	
    	Gson gson = new Gson();
    	ProductResult obj =  gson.fromJson(result, new TypeToken<ProductResult>() {
         }.getType());
    	if(obj.data.extend.DefineInterestValue == null || obj.data.extend.DefineInterestValue.isEmpty()) {
    		Logger.get().info("appId = "+appId+" ,DefineInterestValue is empty");
    		return null;
    	}
    	return new BigDecimal(obj.data.extend.DefineInterestValue);
    
    }
    private String _encodeString(String str) {
        try {
            String result = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
            return result;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            Logger.get().warn(
                    "url encode error : " + e);
            return null;
        }
    }
    class ProductResult{
    	Integer resCode;
    	String errorMsg;
    	Data data;
    }
    class Data{
    	Integer id;
    	String productId;
    	Extend extend;
    }
    class Extend{
    	String DefineInterestValue;
    }
}
