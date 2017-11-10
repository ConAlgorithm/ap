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
import catfish.base.business.dao.MobilePhoneCNAreaDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVWriter;

public class MobilePhoneCNAreaMapping implements IMigratable{

	private static final String queryMobileSql = "select * from MobilePhoneAreaObjects order by Id";
	
	private CSVWriter notSingleWriter = new CSVWriter("MatchNotSingle.csv");
	private CSVWriter areaMultipleMatchWriter = new CSVWriter("AreaMultipleMatch.csv");
	private CSVWriter notMatchWriter = new CSVWriter("NotMatch.csv");
	@Override
	public void migrate() {
		DatabaseApi.queryMultipleResults(queryMobileSql, null, new ResultSetExtractor<List<String>>(){

			@Override
			public List<String> extractData(ResultSet resultSet)
					throws SQLException, DataAccessException {
				List<String> needManualAreas = CNAreaDao.getNotSingleNameCNArea();
				String currentCity = null;
				String city = null;
				String mobilePhoneAreaId = null;
				String CNAreaId = null;
				boolean flag1 = true, flag2 = true;
				while(resultSet.next())
				{
					mobilePhoneAreaId = resultSet.getString("Id");
					if(currentCity == null)
					{
						currentCity = resultSet.getString("City");					
					}else
					{
						city = resultSet.getString("City");
					}
					if(city != null)
					{
						if(city.equals(currentCity))
						{
							if(flag1 && flag2)
							{
								MobilePhoneCNAreaDao.insert(mobilePhoneAreaId, CNAreaId, "Migration");
							}
							continue;
						}
						else
						{
							currentCity = city;
						}
					}
					//����ڶ��ͬ��������У��򽻸��˹�����
					if(contains(needManualAreas, currentCity))
					{
						flag1 = false;
						continue;
					}else{
						flag1 = true;
						//���ƥ�䵽��������У��򽻸��˹�����
						List<CNAreaObject> areaList = CNAreaDao.getCNAreaObjByFuzzyName(currentCity);
						if(areaList.size() > 1)
						{
							flag2 = false;
							List<String> record = new ArrayList<>();
							record.add(currentCity);
							for(CNAreaObject item : areaList)
							{
								record.add(item.Name);
							}
							areaMultipleMatchWriter.write(record);
						}else if(areaList.size() == 1){
							flag2 = true;							
							CNAreaId = areaList.get(0).Id;
							MobilePhoneCNAreaDao.insert(mobilePhoneAreaId, CNAreaId, "Migration");
						}else
						{
							flag2 = false;
							notMatchWriter.write(Arrays.asList(new String[]{currentCity}));
						}
					}
				}
				return null;
			}});
	}

	public boolean contains(List<String> names, String src)
	{
		for(String item : names)
		{
			if(item.contains(src) || src.contains(item))
			{
				Logger.get().info(String.format("Match! item: %s, src: %s", item, src));
				notSingleWriter.write(Arrays.asList(new String[]{src, item}));
				return true;
			}
		}
		return false;
	}
}
