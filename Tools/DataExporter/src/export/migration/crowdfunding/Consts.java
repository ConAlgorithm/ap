package export.migration.crowdfunding;

import java.util.ArrayList;
import java.util.List;

import catfish.base.CollectionUtils;
import catfish.base.business.dao.ProductDao;
import catfish.base.business.object.MerchantCompanyObject;
import catfish.base.business.object.ProductObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVReader;

public class Consts {

	public static final String Folder = "CrowdFunding/";
	private static CSVReader reader = new CSVReader(Folder + "products.csv");
	private static CSVReader fundReader = new CSVReader(Folder + "fund.csv");
	private static CSVReader commissionReader = new CSVReader(Folder + "commission.csv");
	private static CSVReader sellerReader = new CSVReader(Folder + "seller.csv");
	public static List<String> products;
	public static String fundTag;
	public static String commissionName;
	public static String sellerName;
	static{
		products = getProducts();
		fundTag = getResult(fundReader);
		commissionName = getResult(commissionReader);
	    sellerName = getResult(sellerReader);
	}
	public static String getSellerId(String sellerName)
	{
		String sql = "select top 1 * from MerchantCompanyObjects where Name = :Name";
		Dao<MerchantCompanyObject> dao = Dao.create(MerchantCompanyObject.class, DatabaseApi.database);
		return dao.getSingle(sql, CollectionUtils.mapOf("Name", sellerName)).Id;
	}
	public static List<String> getProdIds(List<String> prodNames, boolean isNotStandard)
	{
		List<String> result = new ArrayList<>();
		for(String name : prodNames)
		{
			ProductObject prod = new ProductDao(name).getSingle();
			if(prod != null){
				if(isNotStandard)
				{
					if(prod.catalog.equals("POS_Standard")) continue;
				}
				result.add(prod.Id);
			}
		}
		return result;
	}
	
	private static List<String> getProducts()
	{
		List<String> names = new ArrayList<>();
		while(reader.next())
		{
			String name = reader.getRecord()[0];
			names.add(name);
		}
		return names;
	}
	
	private static String getResult(CSVReader reader)
	{
		if(reader.next())
		{
			return reader.getRecord()[0];
		}
		return null;
	}
}
