package export.migration.crowdfunding;

import java.util.Date;
import java.util.List;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.dao.ProductDao;
import catfish.base.business.object.FundObject;
import catfish.base.business.object.ProductFundObject;
import catfish.base.business.object.ProductObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVWriter;
import export.migration.IMigratable;

public class RelateProductToFund implements IMigratable{
	
	private CSVWriter errorWriter = new CSVWriter(Consts.Folder + "fundsError.csv");
	
	private static final String sql = "select * from ProductFundObjects where ProductId = :Id";
	@Override
	public void migrate() {
		List<String> prodNames = Consts.products;
		String fundTag = Consts.fundTag;
		Dao<ProductFundObject> dao = Dao.create(ProductFundObject.class, DatabaseApi.database);
		for(String name : prodNames)
		{
			try{
				String oldName = name.substring(name.lastIndexOf("-")+1, name.length());
				List<ProductFundObject> oldProdFunds = getProdFunds(dao, oldName);
				String newProdId = new ProductDao(name).getSingle().Id;
				delDuplicateProdFunds(newProdId);
				for(ProductFundObject item : oldProdFunds)
				{
					item.setProductId(newProdId);
					item.setDateAdded(new Date());
					item.setLastModified(new Date());
					item.setId(null);
					item.setHistory(false);
					item.setPriority(item.getPriority()+1);
					dao.add(item);
				}
				dao.add(getNewProdFund(newProdId, getCrowdFundId(fundTag)));
				
			}catch(Exception e){
				Logger.get().error(e);
				errorWriter.write(new String[]{e.getMessage()});
			}
			
		}		
	}
	public List<ProductFundObject> getProdFunds(Dao<ProductFundObject> dao, String prodName)
	{
		ProductObject prod = new ProductDao(prodName).getSingle();
		List<ProductFundObject> prodFunds = dao.getMultiple(sql, CollectionUtils.mapOf("Id", prod.Id));
		return prodFunds;
	}
	
	private void delDuplicateProdFunds(String productId)
	{
		String sql = "delete from ProductFundObjects where ProductId = :ProductId";
		DatabaseApi.updateTable(sql, CollectionUtils.mapOf("ProductId", productId));
	}
	
	private String getCrowdFundId(String fundTag)
	{
		String sql = "select top 1 * from FundObjects where FundTag = :FundTag order by DateAdded desc";
		
		Dao<FundObject> dao = Dao.create(FundObject.class, DatabaseApi.database);
		return dao.getSingle(sql, CollectionUtils.mapOf("FundTag", fundTag)).Id;
	}
	
	private ProductFundObject getNewProdFund(String prodId, String fundId)
	{
		ProductFundObject obj = new ProductFundObject();
		obj.setDateAdded(new Date());
		obj.setLastModified(new Date());
		obj.setFundId(fundId);
		obj.setProductId(prodId);
		obj.setPriority(1);
		obj.setHistory(false);
		return obj;
	}
}
