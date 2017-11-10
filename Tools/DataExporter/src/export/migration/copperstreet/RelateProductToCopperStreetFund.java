package export.migration.copperstreet;

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

public class RelateProductToCopperStreetFund implements IMigratable{

	private static final String Folder = "copperstreet/";
	
    private CSVWriter errorWriter = new CSVWriter(Folder + "fundsError.csv");
	private CSVWriter migWriter = new CSVWriter(Folder + "migrate.csv");
	
	private static final String sql = "select * from ProductFundObjects where ProductId = :Id order by Priority asc";
	@Override
	public void migrate() {

		String axdlFundId = getFundId("AXDL");
		String copperFundId = getFundId("COPPERSTREET");
		
		Dao<ProductFundObject> dao = Dao.create(ProductFundObject.class, DatabaseApi.database);
		List<ProductObject> prods = getAllProducts();
		for(ProductObject prod : prods)
		{
			try{
				
				deleteOldSetting(dao, prod.Id, copperFundId);
				List<ProductFundObject> prodFunds = getProdFunds(dao, prod.Id);
				
				Integer priority = createPriority(prodFunds, axdlFundId, prod.name);
								
				if(priority == null) continue;
								
				ProductFundObject item = new ProductFundObject();
				item.setProductId(prod.Id);
				item.setDateAdded(new Date());
				item.setLastModified(new Date());
				item.setId(null);
				item.setHistory(false);
				item.setFundId(copperFundId);
				item.setPriority(priority);
				dao.add(item);
				
			}catch(Exception e){
				Logger.get().error(e);
				errorWriter.write(new String[]{e.getMessage()});
			}
			
		}		
	}
	
	public void deleteOldSetting(Dao<ProductFundObject> dao, String prodId, String fundId)
	{
		String sql = "delete from [ProductFundObjects] where ProductId = :ProductId and FundId = :FundId";
		dao.update(sql, CollectionUtils.mapOf("ProductId", prodId, "FundId", fundId));
	}
	
	public Integer createPriority(List<ProductFundObject> pfs, String axdlId, String prodName)
	{
		ProductFundObject axdl = null;
		int afterAxdlPriority = 0;
		for(ProductFundObject item : pfs)
		{
			if(axdl != null)
			{
				afterAxdlPriority = item.priority;
				break;
			}
			else if(item.fundId.equals(axdlId))
			{
				axdl = item;
			}
		}
		if(axdl == null){
			return null;
		}else if(afterAxdlPriority == 0){
			return axdl.priority + 10;
		}else if(afterAxdlPriority == axdl.priority+1){
			migWriter.write(new String[]{prodName});
			return null;
		}else{			
			return (axdl.priority + afterAxdlPriority)/2;
		}
	}
	
	/*public void changeAllPriority()
	{
		Dao<ProductFundObject> dao = Dao.create(ProductFundObject.class, DatabaseApi.database);
		String sql = "update ProductFundObjects set Priority";
	}*/
	
	public List<ProductFundObject> getProdFunds(Dao<ProductFundObject> dao, String prodId)
	{
		List<ProductFundObject> prodFunds = dao.getMultiple(sql, CollectionUtils.mapOf("Id", prodId));
		return prodFunds;
	}
	
	public List<ProductObject> getAllProducts()
	{
		Dao<ProductObject> dao = Dao.create(ProductObject.class, DatabaseApi.database);
		String sql = "select * from ProductObjects";
		return dao.getMultiple(sql, null);
	}
	
	private String getFundId(String fundTag)
	{
		String sql = "select top 1 * from FundObjects where FundTag = :FundTag order by DateAdded desc";
		
		Dao<FundObject> dao = Dao.create(FundObject.class, DatabaseApi.database);
		return dao.getSingle(sql, CollectionUtils.mapOf("FundTag", fundTag)).Id;
	}
}
