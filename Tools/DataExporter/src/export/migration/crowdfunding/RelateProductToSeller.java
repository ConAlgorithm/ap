package export.migration.crowdfunding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.dao.ProductDao;
import catfish.base.business.object.MerchantCompanyObject;
import catfish.base.business.object.ProductObject;
import catfish.base.business.object.SellerCommissionObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.database.TransactionTemplateHelper;
import catfish.sales.objects.CommissionDetailObject;
import catfish.sales.objects.CommissionListObject;
import catfish.sales.objects.ProductCommissionObject;
import export.migration.IMigratable;

public class RelateProductToSeller implements IMigratable{

	private List<String> prodIds;
	private String sellerId;
	@Override
	public void migrate() {
		// TODO Auto-generated method stub
		prodIds = Consts.getProdIds(Consts.products, false);
		sellerId = Consts.getSellerId(Consts.sellerName);
		TransactionTemplateHelper.newTransactionTemplate().execute(new TransactionCallbackWithoutResult(){

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try{
					String commissionId = createCommission(Consts.commissionName);
					createCommissionDetail(commissionId);
					createProdCommission(commissionId);
					createSellerCommission(commissionId);
				}catch(Exception e){
					Logger.get().error(e);
					status.setRollbackOnly();
				}
			}});
	}

	private String createCommission(String name)
	{		
		String selectSql = "select * from CommissionListObjects where Description = :Desc";
		Dao<CommissionListObject> commissionDao = Dao.create(CommissionListObject.class, DatabaseApi.database);
		CommissionListObject oldObject = commissionDao.getSingle(selectSql, CollectionUtils.mapOf("Desc", name));	
		if(oldObject != null)
		{
			return null;
		}
		CommissionListObject obj = new CommissionListObject();
		obj.Description = name;
		obj.valid = true;
		obj.DateAdded = new Date();
		obj.LastModified = new Date();
		System.out.println(commissionDao.add(obj));
		return commissionDao.getSingle(selectSql, CollectionUtils.mapOf("Desc", name)).Id;
	}
	
	private void createCommissionDetail(String commissionId)
	{
		CommissionDetailObject obj = new CommissionDetailObject();
		obj.CommissionId = commissionId;
		obj.Max = 0;
		obj.Min = 0;
		obj.Rate = new BigDecimal(0);
		obj.DateAdded = new Date();
		obj.LastModified = new Date();
		Dao<CommissionDetailObject> dao = Dao.create(CommissionDetailObject.class, DatabaseApi.database);
		dao.add(obj);
	}
	private void createProdCommission(String commissionId)
	{
		Dao<ProductCommissionObject> dao = Dao.create(ProductCommissionObject.class, DatabaseApi.database);
		ProductCommissionObject obj = new ProductCommissionObject();
		for(String id : prodIds)
		{					
			obj.ProductId = id;
			obj.CommissionId = commissionId;
			dao.add(obj);
		}
	}
	

	private void createSellerCommission(String commissionId)
	{
		Dao<SellerCommissionObject> sellerDao = Dao.create(SellerCommissionObject.class, DatabaseApi.database);
		String sql = "select * from ProductCommissionObjects where CommissionId = :CommissionId";
		Dao<ProductCommissionObject> dao = Dao.create(ProductCommissionObject.class, DatabaseApi.database);
		List<ProductCommissionObject> pcs = dao.getMultiple(sql, CollectionUtils.mapOf("CommissionId", commissionId));
		for(ProductCommissionObject item : pcs){
			SellerCommissionObject obj = new SellerCommissionObject();
			obj.SellerId = sellerId;
			obj.ProductCommissionId = item.Id;
			obj.StartTime = new Date();
			sellerDao.add(obj);
		}
	}
}
