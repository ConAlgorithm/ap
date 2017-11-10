package jma.models;

import java.util.Date;

public class AuthDataSourceResponse {
//    "accountId": "-2",
//    "accountName": "OmniPrime_DSP",
//    "tokenId": "JeWzisvseN5RXqOxxCsHE1zAYogViWi9",
//    "serverTimeStamp": 1467163248
	private String accountId;
	private String accountName;
	private String tokenId;
	private Long serverTimeStamp;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public Long getServerTimeStamp() {
		return serverTimeStamp;
	}
	public void setServerTimeStamp(Long serverTimeStamp) {
		this.serverTimeStamp = serverTimeStamp;
	}
	

}
