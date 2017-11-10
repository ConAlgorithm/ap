package export.migration.crowdfunding;

import java.util.List;

import catfish.base.CollectionUtils;
import catfish.base.business.object.ProductQuotaObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import export.migration.IMigratable;

public class D3ProductQuota implements IMigratable{

	@Override
	public void migrate() {
		String sellerId = Consts.getSellerId(Consts.sellerName);
		String d3Id = this.getD3Id(sellerId);
		List<String> prodIds = Consts.getProdIds(Consts.products, true);
		Dao<ProductQuotaObject> dao = Dao.create(ProductQuotaObject.class, DatabaseApi.database);
		for(String id : prodIds)
		{
			ProductQuotaObject obj = new ProductQuotaObject();
			obj.D3Id = d3Id;
			obj.ProductId = id;
			obj.Quota = 9999;
			dao.add(obj);
		}
	}

	private String getD3Id(String sellerId)
	{
		String sql = "select du.Id from SellerPOSRelationObjects spr "+
                      "join POSDOrgRelationObjects pdr on pdr.POSId = spr.POSId "+
					  "join DOrgRelationObjects d1r on d1r.NodeId = pdr.OrgNodeId "+
					  "join DOrgRelationObjects d2r on d2r.NodeId = d1r.ParentId "+
					  "join DOrgDUserRelationObjects dour on dour.OrgNodeId = d2r.ParentId "+
					  "join DealerUserObjects du on du.Id = dour.AffiliateId "+
					  "where spr.SellerId = :SellerId "+
					  "and pdr.DeletedOn is null and d1r.DeletedOn is null "+
					  "and d2r.DeletedOn is null and dour.DeletedOn is null "+
					  "and du.DeletedOn is null and du.Status = 0";
		return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("SellerId", sellerId));
	}
	
}
