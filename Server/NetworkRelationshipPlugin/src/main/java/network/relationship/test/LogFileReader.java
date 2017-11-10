package network.relationship.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import network.relationship.businessdomain.GroupInfo;
import network.relationship.domain.User;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class LogFileReader {

	public static Map<String, List<String>> parse(String fileName) throws IOException{

		List<String> lines = FileManipulator.Read(fileName);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		int lastAppIdIndex = 0;
		String lastAppId = null;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if(line.contains(" ERROR ") || line.contains(" FATAL ") || !line.startsWith("2015")){
				continue;
			}
			
			String key = "Start updating graph by AppId : ";
			if(line.contains(key)){
				lastAppId = line.substring(line.indexOf(key) + key.length());
				map.put(lastAppId, null);
				lastAppIdIndex = i;
				continue;
			}
			
			key = "Connected users : ";
			if(line.contains(key) && i - lastAppIdIndex < 30){
				String json = line.substring(line.indexOf(key) + key.length());
				List<String> list = new ArrayList<String>();
				list.add(json);
				map.put(lastAppId, list);
				continue;
			}
			
			key = "GroupInfo : ";
			if(line.contains(key) && i - lastAppIdIndex < 30){
				String json = line.substring(line.indexOf(key) + key.length());
				if(!map.containsKey(lastAppId) || map.get(lastAppId) == null){
					continue;
				}
				map.get(lastAppId).add(json);
				continue;
			}
		}
		
		return map;
	}
	
	private static String getSortedIdNumbers(List<User> users){
		List<String> line = new ArrayList<String>();
		for (int i = 0; i < users.size(); i++) {
			line.add(users.get(i).getIdNumber());
		}
		Collections.sort(line);
		
		StringBuffer sb = new StringBuffer();
		line.forEach(item -> sb.append(item + "|"));
		
		return sb.toString();
	}
	
	private static String getRiskTags(GroupInfo groupInfo){
		StringBuffer sb = new StringBuffer();
//		sb.append(groupInfo.appTotalCount);
//		sb.append(",");
//		sb.append(groupInfo.appApprovedCount);
//		sb.append(",");
//		sb.append(groupInfo.appRejectedCount);
//		sb.append(",");
//		sb.append(groupInfo.appCancelledCount);
//		sb.append(",");
//		sb.append(groupInfo.appFPD1Count);
//		sb.append(",");
//		sb.append(groupInfo.appFPD1AllCount);
//		sb.append(",");
//		sb.append(groupInfo.appFPD30Count);
//		sb.append(",");
//		sb.append(groupInfo.appFPD30AllCount);
//		sb.append(",");
//		sb.append(groupInfo.userTotalCount);
//		sb.append(",");
//		sb.append(groupInfo.userApprovedCount);
//		sb.append(",");
//		sb.append(groupInfo.userRejectedCount);
//		sb.append(",");
//		sb.append(groupInfo.userCancelledCount);
//		sb.append(",");
//		sb.append(groupInfo.userFPD1Count);
//		sb.append(",");
//		sb.append(groupInfo.userFPD1AllCount);
//		sb.append(",");
//		sb.append(groupInfo.userFPD30Count);
//		sb.append(",");
//		sb.append(groupInfo.userFPD30AllCount);
		
		return sb.toString();
	}
	
	private static String getHeader(){
		StringBuffer sb = new StringBuffer();
		sb.append("appId");
		sb.append(",");
		sb.append("IdNumbers");
		sb.append(",");
		sb.append("appTotalCount");
		sb.append(",");
		sb.append("appApprovedCount");
		sb.append(",");
		sb.append("appRejectedCount");
		sb.append(",");
		sb.append("appCancelledCount");
		sb.append(",");
		sb.append("appFPD1Count");
		sb.append(",");
		sb.append("appFPD1AllCount");
		sb.append(",");
		sb.append("appFPD30Count");
		sb.append(",");
		sb.append("appFPD30AllCount");
		sb.append(",");
		sb.append("userTotalCount");
		sb.append(",");
		sb.append("userApprovedCount");
		sb.append(",");
		sb.append("userRejectedCount");
		sb.append(",");
		sb.append("userCancelledCount");
		sb.append(",");
		sb.append("userFPD1Count");
		sb.append(",");
		sb.append("userFPD1AllCount");
		sb.append(",");
		sb.append("userFPD30Count");
		sb.append(",");
		sb.append("userFPD30AllCount");
		
		return sb.toString();
	}
	
	public static void main(String[] args){
		Map<String, List<String>> map = null;
		try {
			FileManipulator.MergeFile("E:\\logs\\", "E:\\result.txt");
			
			map = parse("E:\\result.txt");
			
			FileManipulator.Write("E:\\result.csv", getHeader());
		
			for (Entry<String, List<String>> entry : map.entrySet()) {
				if(entry.getValue() == null)
					continue;
				
				StringBuffer line = new StringBuffer();
				line.append(entry.getKey());
				line.append(",");
				
				List<User> list = new Gson().fromJson(entry.getValue().get(0), new TypeToken<ArrayList<User>>(){}.getType());
				line.append(getSortedIdNumbers(list));
				line.append(",");
				
				if(entry.getValue().size() < 2)
					continue;
				
				GroupInfo groupInfo = new Gson().fromJson(entry.getValue().get(1), new TypeToken<GroupInfo>(){}.getType());
				line.append(getRiskTags(groupInfo));
				
				FileManipulator.Write("E:\\result.csv", line.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
