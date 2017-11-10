package engine.rule.model.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.MerchantUserDao;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.StoreInfoInForm;
import engine.rule.model.in.pdl.StoreInfoInFormProxy;


public class AppStoreInfoModelCreator extends AbstractApplicationModelCreator {

	public AppStoreInfoModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {

		StoreInfoModelCreator creator = new StoreInfoModelCreator(
				MerchantStoreDao.getMerchantStoreId(appId));
		Map<String, Object> modelForm = creator.createModelForm();
		form = (StoreInfoInForm) modelForm.get("in_Store");
		StoreInfoInFormProxy storeForm = (StoreInfoInFormProxy)((StoreInfoInForm)form).getProxyForm();

		// 设置当前申请的门店S1身份证号
		storeForm.setS1Obj(new MerchantUserDao(appId).getSingle());

		return CollectionUtils.mapOf("in_Store", (Object) form);
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}
}
