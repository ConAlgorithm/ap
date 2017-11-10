package thirdparty.jxl.response.reportdata;

import com.google.gson.annotations.SerializedName;

public class BindDataSourceItem{

	/*
	        数据源标识	key	string	绑定数据源的变量名
		数据源名称	name	string	绑定数据源的名称
		账号名称	account	string	绑定的账号名称
		数据类型	category.name	string	数据源类型
		数据类型名称	category.value	string	数据源类型名称
		数据有效性	status	string	绑定的账号数据量是否足够支持数据推断
		数据可靠性	reliability	string	绑定的账号是否实名认证
		绑定时间	binding_time	string	最早绑定的时间
	*/
	//eg: jingdong
	@SerializedName("key")
	private String key;
	
	//eg: 京东商城
	@SerializedName("name")
	private String name;
	
	//eg: zhangzhanyu
	@SerializedName("account")
	private String account;
	
	//eg: e_business
	@SerializedName("category_name")
	private String categoryName;
	
	//eg: e_business
	@SerializedName("category_value")
	private String categoryValue;
	
	//eg: valid
	@SerializedName("status")
	private String status;
	
	//eg: 非实名认证
	@SerializedName("reliability")
	private String reliability;
	
	//eg: 2008-12-30 14:30:00
	@SerializedName("binding_time")
	private String bindingTime;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReliability() {
		return reliability;
	}

	public void setReliability(String reliability) {
		this.reliability = reliability;
	}

	public String getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(String bindingTime) {
		this.bindingTime = bindingTime;
	}
}
