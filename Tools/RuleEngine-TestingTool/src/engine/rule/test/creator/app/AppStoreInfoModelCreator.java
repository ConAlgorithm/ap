package engine.rule.test.creator.app;

import java.math.BigDecimal;
import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.app.StoreInfoInForm;
import engine.rule.test.creator.TestModelCreator;
import engine.rule.test.model.in.TestStoreInfoInForm;


public class AppStoreInfoModelCreator extends TestModelCreator {

	public AppStoreInfoModelCreator(Map<String, String> columnNameValueMappings) {
		super(StoreInfoInForm.class, "in_Store", columnNameValueMappings);
	}
	
	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		boolean result = false;
		
		StoreInfoInForm inform = (StoreInfoInForm)form;
		TestStoreInfoInForm proxyForm = new TestStoreInfoInForm();
		inform.setProxyForm(proxyForm);		
	
		//(this)的门店所在省份
		String key = "getLocatedProvince";
		if(this.columnNameValueMappings.containsKey(key)){
			String province = this.columnNameValueMappings.get(key);
			proxyForm.setLocatedProvince(province.equalsIgnoreCase("NONE") ? "" : province);
			result = true;
		}
		
		key = "getLocatedCity";
		if(this.columnNameValueMappings.containsKey(key)){
			String city = this.columnNameValueMappings.get(key);
			proxyForm.setLocatedCity(city.equalsIgnoreCase("NONE") ? "" : city);
			result = true;
		}
		key = "getLocatedDistrict";
		if(this.columnNameValueMappings.containsKey(key)){
			String district = this.columnNameValueMappings.get(key);
			proxyForm.setLocatedDistrict(district.equalsIgnoreCase("NONE") ? "" : district);
			result = true;
		}
		key = "getNDaysFactualQuantityLimitNum";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			proxyForm.setNDaysFactualQuantityLimitNum(new Integer(value));
			result = true;
		}
		
		key = "getFactualRiskExposure";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			proxyForm.setFactualRiskExposure(new BigDecimal(value));
			result = true;
		}
		
		key = "getNDaysStatedQuantityLimitNum";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			proxyForm.setNDaysStatedQuantityLimitNum(new Integer(value));
			result = true;
		}
		
		key = "getStatedRiskExposure";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			proxyForm.setStatedRiskExposure(new BigDecimal(value));
			result = true;
		}
		
		key = "getCompletedAppCountByMonth";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			proxyForm.setCompletedAppCountByMonth(Integer.parseInt(value));
			result = true;
		}
		
		key = "storecategory";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setStoreCategory(Integer.valueOf(value));
		    result = true;
		}
		
		key = "getHasRepairedGoods";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setHasRepairedGoods(Integer.parseInt(value));
		    result = true;
		}
		
		key = "smuggledGoods";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setHasSmuggledGoods(Integer.parseInt(value));
		    result = true;
		}
		
		key = "getNumOfOpenDay";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setNumOfOpenDay(Integer.parseInt(value));
		    result = true;
		}
		
		key = "getFirstOverDueLessThan7DayCount";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setFirstOverDueLessThan7DayCount(Integer.parseInt(value));
		    result = true;
		}
		
		key = "getHasSecondHandGoods";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setHasSecondHandGoods(Integer.parseInt(value));
		    result = true;
		}
		
		key = "getHasSmuggledGoods";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setHasSmuggledGoods(Integer.parseInt(value));
		    result = true;
		}
		
		key = "getCompletedAppCount";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setCompletedAppCount(Integer.parseInt(value));
		    result = true;
		}
		
		key = "getOverDueCount";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setOverDueCount(Integer.parseInt(value));
		    result = true;
		}
		
		key = "getFirstOverDue7DayCount";
		if(this.columnNameValueMappings.containsKey(key)){
		    String value = this.columnNameValueMappings.get(key);
		    proxyForm.setFirstOverDue7DayCount(Integer.parseInt(value));
		    result = true;
		}
		
		return result;
	}
}
