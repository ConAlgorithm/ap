package export.migration.commission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVReader;
import catfish.base.file.CSVWriter;
import export.migration.IMigratable;

class Commission{
	public String commissionId;
	public String description;
	public int max;
	public int min;
	public float rate;
}
public class CommissionMigration implements IMigratable{

	private static final String updateSql = "update [CommissionListObjects] set Description = :Desc where Id = :Id";
	
	private static final String DIR = "commission/";
	private Map<String, Map<String, List<Commission>>> prodMap = new HashMap<>();
	CSVWriter errorWriter = new CSVWriter(DIR + "error.csv");
	@Override
	public void migrate() {
		CSVReader reader = new CSVReader(DIR + "input.csv");
		CSVWriter writer = new CSVWriter(DIR + "out.csv");

		String[] record = null;
		while(reader.next()){
			record = reader.getRecord();
			String prodId = record[1];
			String comName = record[4];
			Commission com = getCommission(record);
			if(!prodMap.containsKey(prodId))
			{
				prodMap.put(prodId, new HashMap<>());
			}if(!prodMap.get(prodId).containsKey(comName)){
				prodMap.get(prodId).put(comName, new ArrayList<>());
			}
			prodMap.get(prodId).get(comName).add(com);
		}
		prodMap.forEach((key, value) ->{
			value.forEach((commissionName, commission) ->{
				List<Commission> result = getModifiedCommission(commission);
				if(result != null)
				{
					result.forEach(item -> {
						writeLine(writer, item);
						//updateDB(item);
					});
				}				
			});
		});
	}
	
	private List<Commission> getModifiedCommission(List<Commission> input)
	{
		boolean isStatic = false;
		List<Integer> splits = new ArrayList<>();
		int notEqualCount = 0;
		for(int i = 1; i < input.size(); i ++)
		{
			if(input.get(i).description.contains("固定")) isStatic = true;
			if(isStatic)
			{
				if(!input.get(i).commissionId.equals(input.get(i-1).commissionId))
				{
					notEqualCount ++;
					if(i == 1)
					{
						input.get(i-1).description += "(原始)";
					}				
				    input.get(i).description += "(重复" + i + ")";
				}
			}else{
				if(!input.get(i).commissionId.equals(input.get(i-1).commissionId))
				{
					notEqualCount ++;
					splits.add(i);
				}
			}
		}
		if(notEqualCount == 1 && isStatic)
		{
			input.get(1).description = input.get(1).description.replace("1", "");
		}
		if(splits.size() > 1){
			writeLine(errorWriter, input.get(0));
			return null;
		}else if(!isStatic && splits.size() != 0){
			int bound = splits.get(0);
			for(int i = 0; i < bound; i ++)
			{
				if(!compare(input.get(i), input.get(i + bound)))
				{
					writeLine(errorWriter, input.get(0));
					return null;
				}
			}
			List<Commission> result = new ArrayList<>();
			input.get(bound-1).description += "(原始)";
			result.add(input.get(bound-1));
			input.get(bound).description += "(重复)";
			result.add(input.get(bound));
			return result;
		}
		if(notEqualCount == 0) return null;
		return input;
	}
	private boolean compare(Commission x, Commission y)
	{
		if(x.max != y.max || x.min != y.min || Float.compare(x.rate, y.rate) != 0)
			return false;
		return true;
	}
	private Commission getCommission(String[] record)
	{
		Commission com = new Commission();
		com.commissionId = record[3];
		com.description = record[4];
		com.max = Integer.parseInt(record[5]);
	    com.min = Integer.parseInt(record[6]);
		com.rate = Float.parseFloat(record[7]);
		return com;
	}

	private void writeLine(CSVWriter writer, Commission com)
	{
		writer.write(new String[]{
			com.commissionId,
			com.description,
			com.max + "",
			com.min + "",
			com.rate + ""
		});
	}
	
	private void updateDB(Commission com)
	{
		DatabaseApi.updateTable(updateSql, CollectionUtils.mapOf("Desc", com.description, "Id", com.commissionId));
	}
}
