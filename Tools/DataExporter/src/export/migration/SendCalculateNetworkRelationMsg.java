package export.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.Logger;
import catfish.base.database.DatabaseApi;
import catfish.base.file.CSVWriter;
import catfish.base.queue.QueueApi;
import catfish.base.queue.QueueMessager;

public class SendCalculateNetworkRelationMsg implements IMigratable{

	private static final String sql = "SELECT Id FROM InstallmentApplicationObjects "
			+ "where InstallmentStartedOn > '2015-09-01 00:00:00.000' and Status = 40 "
			+ "and PreApprovedOn is null "
			+ "order by InstallmentStartedOn asc";
	
	private CSVWriter successWriter = new CSVWriter("SucceessAppIds.csv");
	private CSVWriter failWriter = new CSVWriter("FailAppIds.csv");
	
	@Override
	public void migrate() {

        List<String> successList = new LinkedList<>();
        List<String> failList = new LinkedList<>();
		List<String> ids = DatabaseApi.queryMultipleResults(sql, null, new RowMapper<String>(){      
			@Override
			public String mapRow(ResultSet result, int arg1) throws SQLException {
				return result.getString("Id");
		}});
		
		for(String id : ids)
		{
			try{
				QueueMessager msg = new QueueMessager(id, "UpdateGraphByApplication");
			    QueueApi.writeMessager("GraphServiceRequestQueue", msg);
			    successList.add(id);
			}catch(Exception e)
			{
				Logger.get().error(String.format("Send msg of Appid: %s error", id));
				failList.add(id);
			}		
		}
		successWriter.write(successList);
		failWriter.write(failList);
	}
}
