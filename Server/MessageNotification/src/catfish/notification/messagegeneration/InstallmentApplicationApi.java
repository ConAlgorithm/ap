package catfish.notification.messagegeneration;

import java.math.BigDecimal;

import grasscarp.application.model.AppProduct;

import org.apache.http.client.utils.URIBuilder;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Configuration;
import catfish.notification.MessageConfig.NotificationKeys;

import com.google.gson.Gson;

public class InstallmentApplicationApi {

	public static AppProduct getAppModel(String appId){
		
		try {
			URIBuilder builder = new URIBuilder()
		        .setPath(Configuration.getGrasscarpUrlPrefix() + String.format("/application/%s/product", appId));
		    builder.setParameter(NotificationKeys.APP_ID, appId);
		
		    String literal = HttpClientApi.get(builder.toString());
		    Logger.get().info("Got grasscarp response: " + literal);
		
		    AppProduct response = new Gson().fromJson(literal, AppProduct.class);
		    return response;
		} catch (Exception e) {
			Logger.get().warn("Error happens: " + appId, e);
			AppProduct appProduct = new AppProduct();
			appProduct.setPenaltyFee(new BigDecimal(5.0));
			return appProduct;
		}
	}
}
