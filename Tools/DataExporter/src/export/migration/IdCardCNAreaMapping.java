package export.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import catfish.base.Logger;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.IdCardAreaDao;
import catfish.base.business.dao.IdCardCNAreaDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVWriter;

public class IdCardCNAreaMapping implements IMigratable{

	private CSVWriter notSingleDistrictWriter = new CSVWriter("NotSingleDistrictName.csv");
	private CSVWriter notSingleCNAreaWriter = new CSVWriter("NotSingleCNAreaName.csv");
	private CSVWriter notSingleCNAreaMatchWriter = new CSVWriter("NotSingleCNAreaMatch.csv");
	private CSVWriter areaMultipleMatchWriter = new CSVWriter("AreaMultipleMatch.csv");
	private CSVWriter notMatchWriter = new CSVWriter("NotMatch.csv");
	private static String queryIdSql = "select * from (select Id, District, count(District) Num from IdCardAreaObjects group by Id, District) temp where temp.Num = 1";
	
	public void recordNotSingleDistrctAreas()
	{
		List<String> notSingleDistrictNames = IdCardAreaDao.getNotSingleDistricAreas();
		notSingleDistrictWriter.write(notSingleDistrictNames);
	}
	@Override
	public void migrate() {
		this.recordNotSingleDistrctAreas();
		
		DatabaseApi.queryMultipleResults(queryIdSql, null, new ResultSetExtractor<List<String>>(){

			@Override
			public List<String> extractData(ResultSet resultSet)
					throws SQLException, DataAccessException {
				List<String> notSingleCNAreaNames = CNAreaDao.getNotSingleNameCNArea();
				notSingleCNAreaWriter.write(notSingleCNAreaNames);
				String district = null;
				String idCardAreaId = null;
				while(resultSet.next())
				{
					district = resultSet.getString("District");
					if(contains(notSingleCNAreaNames, district))
					{
						continue;
					}else
					{
						List<CNAreaObject> areaList = CNAreaDao.getCNAreaObjByFuzzyName(district);
						if(areaList.size() > 1)
						{
							List<String> areaNameList = new ArrayList<>();
							areaNameList.add(district);
							for(CNAreaObject item: areaList)
							{
								areaNameList.add(item.Name);
							}
							areaMultipleMatchWriter.write(areaNameList);
						}else if(areaList.size() == 1)
						{
							idCardAreaId = resultSet.getString("Id");
							IdCardCNAreaDao.insert(idCardAreaId, areaList.get(0).Id, "Migration");
						}else{
							notMatchWriter.write(Arrays.asList(new String[]{district}));
						}
					}
				}
				return null;
			}
			
		});
		
	}
	public boolean contains(List<String> names, String src)
	{
		for(String item : names)
		{
			if(item.contains(src) || src.contains(item))
			{
				Logger.get().info(String.format("Match! item: %s, src: %s", item, src));
				notSingleCNAreaMatchWriter.write(Arrays.asList(new String[]{src, item}));
				return true;
			}
		}
		return false;
	}

}
