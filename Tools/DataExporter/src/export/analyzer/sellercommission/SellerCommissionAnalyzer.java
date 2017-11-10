package export.analyzer.sellercommission;

import java.util.List;





import catfish.base.CollectionUtils;
import catfish.base.business.object.MerchantCompanyObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVWriter;
import export.analyzer.IAnalyzable;

public class SellerCommissionAnalyzer implements IAnalyzable{

	private static final String selectDuplicate = "select ProductId, Description from (select a.Id, a.ProductId, a.CommissionId, b.Name, c.Description from ProductCommissionObjects a"+
                                                  " join ProductObjects b on a.ProductId = b.Id join CommissionListObjects c on a.CommissionId = c.Id) temp1"+
                                                  " group by temp1.ProductId, Description having count(Description) > 1";
	
	private static final String selectDetail = "select a.Id, a.ProductId, a.CommissionId, b.Name, c.Description, d.max, d.min, d.rate from ProductCommissionObjects a "+
                                               "join ProductObjects b on a.ProductId = b.Id "+
                                               "join CommissionListObjects c on a.CommissionId = c.Id "+
                                               "join CommissionDetailObjects d on d.CommissionId = c.Id "+
                                               "where a.ProductId = :ProductId "+
                                               "and c.Description = :Description";
	
	private static final String selectSeller = "select * from MerchantCompanyObjects where Id in ("+
                                               "select SellerId from SellerCommissionObjects "+
                                               "where ProductCommissionId = :ProductCommissionId)";
	
	private static final String Dir = "sellercommission";
	
	@Override
	public void analyze() {
		// TODO Auto-generated method stub
		CSVWriter writer = new CSVWriter(Dir + "/out.csv");
		CSVWriter diffWriter = new CSVWriter(Dir + "/diff.csv");
		CSVWriter sellerWriter = new CSVWriter(Dir + "/seller.csv");
		
		Dao<PD> dao = Dao.create(PD.class, DatabaseApi.database);
		Dao<Detail> detailDao = Dao.create(Detail.class, DatabaseApi.database);
		Dao<MerchantCompanyObject> sellerDao = Dao.create(MerchantCompanyObject.class, DatabaseApi.database);
		
		List<PD> pdList = dao.getMultiple(selectDuplicate, null);
		writer.write(new String[]{"Id", "ProductId", "Name", "CommissionId", "Description", "Max", "Min", "Rate"});
		sellerWriter.write(new String[]{"商户Id", "商户名称","ProductCommissionId","产品名称","返佣描述"});
		pdList.forEach(item -> {
			List<Detail> details =  detailDao.getMultiple(selectDetail, CollectionUtils.mapOf("ProductId", item.getProductId(), "Description", item.getDescription()));
			details.forEach(detail -> {
				writer.write(new String[]{
						detail.getId(), 
						detail.getProductId(), 
						detail.getName(), 
						detail.getCommissionId(), 
						detail.getDescription(), 
						detail.getMax()+ "",
						detail.getMin()+ "",
						detail.getRate()+ ""});
				if(detail.getDescription().startsWith("固定"))
				{
					List<MerchantCompanyObject> sellers = sellerDao.getMultiple(selectSeller, CollectionUtils.mapOf("ProductCommissionId", detail.getId()));
					sellers.forEach(seller -> {
						sellerWriter.write(new String[]{seller.Id, seller.Name, detail.getId(), detail.getName(), detail.getDescription()});
					});
				}			
			});
		});	
	}
}
