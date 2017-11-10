package catfish.notification.messagegeneration;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URIBuilder;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Configuration;

import com.google.gson.Gson;

public class UserApi {
	
	public static class UserData {
		private String bankCardAccount;

		public String getBankCardAccount() {
			return bankCardAccount;
		}

		public void setBankCardAccount(String bankCardAccount) {
			this.bankCardAccount = bankCardAccount;
		}

	}

	public static UserData invoke(String relativePath, String params) {
		URIBuilder builder = new URIBuilder().setPath(Configuration
				.getUserUrlPrefix() + relativePath + "/" + params);

		String literal = HttpClientApi.get(builder.toString());
		Logger.get().info("Got repayment response: " + literal);

		UserData data = new Gson().fromJson(literal,
				UserData.class);

		return data;
	}
}