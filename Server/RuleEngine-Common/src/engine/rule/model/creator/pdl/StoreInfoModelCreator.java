package engine.rule.model.creator.pdl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.LocalDate;

import catfish.base.CollectionUtils;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.InstalmentType;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.MerchantStoreRiskControlDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.object.context.MerchantStoreData;
import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.model.in.pdl.IStoreInfoInForm;
import engine.rule.model.in.pdl.StoreInfoInForm;
import engine.rule.model.in.pdl.StoreInfoInFormProxy;

public class StoreInfoModelCreator extends AbstractModelCreator {

	private static ConcurrentMap<String, MerchantStoreData> storeDataMap = new ConcurrentHashMap<String, MerchantStoreData>();
	private String storeId;

	public StoreInfoModelCreator(String storeId) {
		this.storeId = storeId;
	}

	@Override
	public Map<String, Object> createModelForm() {
		LocalDate date = LocalDate.now();

		StoreInfoInFormProxy storeForm = new StoreInfoInFormProxy(); 
		form = new StoreInfoInForm(storeForm);

		MerchantStoreObject storeObj = new MerchantStoreDao(this.storeId)
				.getSingle();

		// String storeId = storeObj.Id;
		MerchantStoreData storeData = storeDataMap.get(this.storeId);

		if (storeData == null
				|| storeData.analyzeTime.compareTo(date.toDate()) < 0) {
			storeData = new MerchantStoreData();
			storeData.analyzeTime = date.toDate();
			storeData.storeObj = storeObj;
			// 设置总完成单数
			storeData.completedApplicationCount = MerchantStoreDao
					.getLoanCompletedAppCountByTime(storeId, null);

			LocalDate lastDate = date.plusDays(-30);
			// 设置一个月内完成单数
			storeData.completedApplicationCountByMonth = MerchantStoreDao
					.getLoanCompletedAppCountByTime(storeId, lastDate.toDate());
			// 设置拒绝单数
			storeData.rejectedApplicationCount = MerchantStoreDao
					.getAppCountByStatusAndTime(storeId,
							ApplicationStatus.Rejected, null);
			// 设置一个月内拒绝单数
			storeData.rejectedApplicationCountByMonth = MerchantStoreDao
					.getAppCountByStatusAndTime(storeId,
							ApplicationStatus.Rejected, lastDate.toDate());
			// 设置总单数
			storeData.applicationCount = MerchantStoreDao.getAppCountByTime(
					storeObj.Id, null);
			// 设置一个月总单数
			storeData.applicationCountByMonth = MerchantStoreDao
					.getAppCountByTime(storeId, lastDate.toDate());
			// 设置逾期单数
			storeData.overDueCount = MerchantStoreDao.getOverdueCount(storeId,
					5);

			// 设置首逾>=7天用户数
			storeData.overDueNdaysUserCount = MerchantStoreDao
					.getOverDueUserCount(storeId, 7, 1, InstalmentType.PENALT);

			// 设置首逾未超过7天用户数量
			storeData.notOverDueNdaysUserCount = MerchantStoreDao
					.getNotOverDueUserCount(storeId, 7, 1,
							InstalmentType.PENALT);

			// 设置门店所在城市，省份
			CNAreaObject area = new CNAreaDao(storeObj.CNAreaId).getSingle();
			storeData.locatedCity = MerchantStoreDao.getLocatedCityByAreaCode(area.Code);
			storeData.locatedProvince = MerchantStoreDao.getLocatedProvinceByAreaCode(area.Code);

			storeDataMap.put(storeId, storeData);
		}
		// 设置门店上下文信息
		storeForm.setStoreData(storeData);

		// 设置门店所有已经放款申请信息
		storeForm.setAppList(MerchantStoreDao.getAllAppsOfMoneyTransferred(
				storeId, ApplicationStatus.Completed));

		// 设置门店风险信息
		storeForm.setRiskObj(new MerchantStoreRiskControlDao(storeId)
				.getSingle());

		// //设置当前申请的门店S1身份证号
		// storeForm.setS1Obj(new MerchantUserDao(appId).getSingle());

		return CollectionUtils.mapOf("in_Store", (Object) form);
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ConcurrentMap<String, MerchantStoreData> getStoreDataMap() {
		return storeDataMap;
	}
}
