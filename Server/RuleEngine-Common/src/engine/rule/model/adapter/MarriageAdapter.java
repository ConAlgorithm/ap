package engine.rule.model.adapter;

import catfish.base.business.dao.PersonalInfoDao;

public class MarriageAdapter implements DBFieldAdapter {

	@Override
	public String execute(Object... args) {
		String appId = (String) args[0];
		Integer marriageStatus = PersonalInfoDao.getMarriageStatusById(appId);
		return (marriageStatus == null ? null : String.valueOf(marriageStatus));
	}
}
