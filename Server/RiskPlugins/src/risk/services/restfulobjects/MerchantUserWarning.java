package risk.services.restfulobjects;

import catfish.base.business.model.BaseBusinessModel;

public class MerchantUserWarning extends BaseBusinessModel{

	private String appId;
	private String merchantUserId;
	private Integer actionType;
	private String content;
	private String merchantUserRole;//F用户的角色

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMerchantUserId() {
		return merchantUserId;
	}

	public void setMerchantUserId(String merchantUserId) {
		this.merchantUserId = merchantUserId;
	}

	public Integer getActionType() {
	  return actionType;
	}

	public void setActionType(Integer actionType) {
	  this.actionType = actionType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public String getMerchantUserRole() {
        return merchantUserRole;
    }

    public void setMerchantUserRole(String merchantUserRole) {
        this.merchantUserRole = merchantUserRole;
    }
	
}
