package export.migration.crowdfunding;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import catfish.base.Logger;
import catfish.base.business.dao.ProductDao;
import catfish.base.business.object.ProductObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVWriter;
import export.migration.IMigratable;

public class CreateCrowdFundingProduct implements IMigratable{

	private CSVWriter errorWriter = new CSVWriter(Consts.Folder + "productsError.csv");
	private CSVWriter alreadyWriter = new CSVWriter(Consts.Folder + "productsAlready.csv");
	@Override
	public void migrate() {
		List<String> productNames = Consts.products;
		ProductObject product = null;
		Dao<ProductObject> dao = Dao.create(ProductObject.class, DatabaseApi.database);
		for(String name : productNames)
		{
			try{
				String oldName = name.substring(name.lastIndexOf("-")+1, name.length());
				product = new ProductDao(oldName).getSingle();
				if(product == null)
				{
					throw new Exception(oldName +" not exits, insert " + name + "error!");
				}
				product.setId(null);
				product.setName(name);
				product.setDateAdded(new Date());
				product.setLastModified(new Date());
				if(new ProductDao(name).getSingle() != null)
				{
					Logger.get().warn(name + " already exists！");
					alreadyWriter.write(Arrays.asList(new String[]{name}));
				}else if(dao.add(product) == null){
					throw new Exception("Insert " + name + " error!");
				}				
			}catch(Exception e){
				Logger.get().error(e);
				errorWriter.write(Arrays.asList(new String[]{e.getMessage()}));
			}			
		}
	}
}
