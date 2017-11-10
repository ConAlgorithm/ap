package network.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import network.relationship.api.TextStorage;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.database.DatabaseApi;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;

public class NetworkRebuildingPlugin implements IPlugin, IQueueListener {

	@Override
	public void onMessage(String arg0, String arg1) {
		
		List<String> list = getAllSortedApps(); 
		for (int i = 0; i < list.size(); i++) {
			Logger.get().info("Rebuild graph by AppId: " + list.get(i));
			boolean succeeded = true;
			do {
				succeeded = true;
				try {
					NewApplicationHandler.handleAppId(list.get(i), new TextStorage());				
				} catch (Exception e) {
					succeeded = false;
					Logger.get().error("exception happens: ", e);
					try {
						Thread.sleep(5 * 60 * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			} while (!succeeded);
		}
	}

	@Override
	public void init(IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue queue = queueService.getQueue("GraphRebuildingQueue");
		queue.register(this);
	}
	
	private static List<String> getAllSortedApps(){
		String sql = "SELECT i.Id "
                + "FROM InstallmentApplicationObjects as i "
                + "join EndUserExtensionObjects as u "
                + "on u.Id=i.UserId "
                + "where u.UserType=:userType "
                + "order by i.DateAdded";
		
		List<String> apps = DatabaseApi.database.queryMultipleResults(sql, 
				CollectionUtils.mapOf("userType", 0), new ResultSetExtractor<List<String>>(){
			@Override
			public List<String> extractData(ResultSet resultSet) throws SQLException,
					DataAccessException {
				List<String> list = new ArrayList<String>();
				while(resultSet.next())
				{
					list.add(resultSet.getString(1));
				}
				return list;
			}
		});
		
		return apps;
	}
}
