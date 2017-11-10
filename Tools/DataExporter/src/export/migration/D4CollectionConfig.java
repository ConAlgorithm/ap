package export.migration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;
import com.google.gson.Gson;

class IdRole{
	public String userId;
	public String role;
}
public class D4CollectionConfig implements IMigratable{

	@Override
	public void migrate() {
		// TODO Auto-generated method stub
		
	}

	/*private static final int StartInterval = 11;
	private static final String ServiceUrl = StartupConfig.get("catfish.collection.url");
	private CSVWriter alreadyWriter = new CSVWriter("alreadyConfig.csv");
	private CSVWriter errorWriter = new CSVWriter("configError.csv");
	//private static final String Sql = "select UserId, Role from DealerUserObjects where IdName = :IdName";
	private CSVReader reader;
	public D4CollectionConfig(CSVReader reader)
	{
		this.reader = reader;
	}
	
	private CollectionProperty createProperty(BigDecimal ratio, int startInterval)
	{
		CollectionProperty prop = new CollectionProperty();
		prop.setCollectionRatio(ratio);
		prop.setStartCollectionInterval(startInterval);
		return prop;
	}
	private BigDecimal getCollectionRatio(String ratio)
	{
		return new BigDecimal(Double.parseDouble(ratio.replace("%", ""))/100);
	}
	
	@Override
	public void migrate() {
		
		Set<String> alreadUsers = getAllUsers();
		Set<String> data = new HashSet<>();
		while(reader.next())
		{
			String[] record = reader.getRecord();
			String userId = record[0];
			if(userId != null)
			    data.add(userId);
		}
		for(String userId : data)
		{
			try{				
				if(alreadUsers == null || !alreadUsers.contains(userId)){
					POSCollectionRole role = new POSCollectionRole();
					role.setUserId(userId);
					role.setM1firstOverDue(createProperty(getCollectionRatio("100%"), StartInterval));
					role.setM1secOverDue(createProperty(getCollectionRatio("10%"), StartInterval));
					role.setM1thirdOverDue(createProperty(getCollectionRatio("10%"), StartInterval));
					
					String result = this.insertCollectionRole(role);
					if(result == null)
					{
						throw new Exception(userId);
					}else{
						Logger.get().info(String.format("Succcess! userName:%s, userId:%s, result:%s", userId, userId, result));
					}
					Thread.sleep(500);
				}else{
					alreadyWriter.write(Arrays.asList(new String[]{userId}));
				}			
			}catch(Exception e){
				errorWriter.write(Arrays.asList(new String[]{userId}));
				Logger.get().error("Insert CollectionRole Error!", e);
			}
		}				
	}
	
	private Set<String> getAllUsers()
	{
		return HttpClientApi.CallService(3, new ServiceHandler<String, Set<String>>(){

			@Override
			public String createUrl() {
				return ServiceUrl + "/collectionrole/userids";
			}

			@Override
			public String OnRequest(String url) {
				return HttpClientApi.get(url);
			}

			@Override
			public Set<String> OnSuccess(String result) {
				Set<String> userIds = new HashSet<>();
				try{
					JSONArray array = new JSONArray(result);			
					for(int i =0; i < array.length(); i ++)
					{
						userIds.add(array.getJSONObject(i).getString("id"));
					}
					return userIds;
				}catch(Exception e){
					Logger.get().error("Get All UserIds Error!");
				}
				return userIds;
			}

			@Override
			public Set<String> OnError(String result) {
				// TODO Auto-generated method stub
				return null;
			}		
		});
	}
	
	private String insertCollectionRole(final POSCollectionRole role)
	{
		return  HttpClientApi.CallService(3, new ServiceHandler<String, String>(){

			@Override
			public String createUrl() {
				return ServiceUrl + "/collectionrole";
			}

			@Override
			public String OnRequest(String url) {
				Map<String, ?> map = new Gson().fromJson(new Gson().toJson(role), Map.class);
				return HttpClientApi.postJson(url, map).get("id").toString();
			}

			@Override
			public String OnSuccess(String result) {
				return result;
			}

			@Override
			public String OnError(String result) {
				// TODO Auto-generated method stub
				return null;
			}		
		});
	}*/
}
