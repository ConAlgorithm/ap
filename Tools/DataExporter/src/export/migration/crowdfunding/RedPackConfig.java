package export.migration.crowdfunding;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import catfish.base.business.object.ActivityFactorObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import export.migration.IMigratable;

public class RedPackConfig implements IMigratable{

	@Override
	public void migrate() {
		// TODO Auto-generated method stub
		Dao<ActivityFactorObject> dao = Dao.create(ActivityFactorObject.class, DatabaseApi.database);
		List<String> prodIds = Consts.getProdIds(Consts.products, false);
		String sellerId = Consts.getSellerId(Consts.sellerName);
		for(String prodId : prodIds)
		{
			ActivityFactorObject obj = new ActivityFactorObject();
			obj.FactorType = 1;
			obj.FactorId = prodId;
			obj.MaxReward = 0;
			obj.MaxReward = 0;
			obj.AvgReward = 0;
			obj.Offset = 0;
			obj.StartTime = new Date();
			obj.EndTime = DateTime.now().plusYears(5).toDate();
			dao.add(obj);
		}
	}

}
