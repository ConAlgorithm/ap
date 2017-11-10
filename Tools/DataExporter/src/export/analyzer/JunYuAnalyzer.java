package export.analyzer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.file.CSVReader;

public class JunYuAnalyzer implements IAnalyzable{

	private static final String junyuFile = "Junyu/junyu.csv";
	private static int standards = 60;
	private CSVReader reader;
	private Map<Integer, List<JunYuRecord>> junyu3PhotoMap = new HashMap<>();
	private  Map<Integer, List<JunYuRecord>> junyu2PhotoMap = new HashMap<>();
	
	public JunYuAnalyzer()
	{
		initMap(3, 0);
		initMap(3, 1);
		initMap(3, 2);
		initMap(3, 3);
		initMap(3, -2);
		initMap(3, -3);
		initMap(3, -4);
		initMap(3, -5);
		
		initMap(2, 10);
		initMap(2, 11);
		initMap(2, -2);
		initMap(2, -3);
		initMap(2, -4);
		initMap(2, -5);
	}
	
	private void initMap(int type, int returnValue)
	{
		if(type == 2)
		{
			junyu2PhotoMap.put(returnValue, new LinkedList<JunYuRecord>());
		}else{
			junyu3PhotoMap.put(returnValue, new LinkedList<JunYuRecord>());
		}
	}
	private void initRecords()
	{
		reader = new CSVReader(junyuFile);
		while(reader.next())
		{
			String[] result = reader.getRecord();			
			if(result == null)
			{
				break;
			}
			JunYuRecord record = new JunYuRecord(result);
			if(record.type == 3)
			{
				junyu3PhotoMap.get(record.returnValue).add(record);
			}else
			{
				junyu2PhotoMap.get(record.returnValue).add(record);
			}
		}
		Logger.get().info("initRecords Finished:");
		int count_3 = 0, count_2 = 0;
		for(Integer item : junyu3PhotoMap.keySet())
		{
			count_3 += junyu3PhotoMap.get(item).size();
		}
		for(Integer item : junyu2PhotoMap.keySet())
		{
			count_2 += junyu2PhotoMap.get(item).size();
		}
		Logger.get().info("���ŶԱ�����: " + count_3);
		Logger.get().info("���ŶԱ�����: " + count_2);
	}
	
	
	@Override
	public void analyze() {
		initRecords();
		Logger.get().info("���ŶԱȷ��ؽ�������");
		print3_NCount(0);
		analyze3_0Result();
		print3_NCount(1);
		print3_NCount(2);
		print3_NCount(3);
		print3_NCount(-2);
		print3_NCount(-3);
		print3_NCount(-4);
		print3_NCount(-5);
		
		Logger.get().info("���ŶԱȷ��ؽ�������");
		print2_NCount(10);
		analyze2_10Result();
		print2_NCount(11);
		print2_NCount(-2);
		print2_NCount(-3);
		print2_NCount(-4);
		print2_NCount(-5);
	}

	public void analyze3_0Result()
	{
		int idPass = 0, capturePass = 0, bothPass = 0;
		for(JunYuRecord item : junyu3PhotoMap.get(0))
		{
			if(item.idSimilarity >= standards)
			{
				idPass ++;
			}
			if(item.captureSimilarity >= standards)
			{
				capturePass ++;
			}
			if(item.idSimilarity >= standards && item.captureSimilarity >= standards)
			{
				bothPass ++;
			}
		}
		Logger.get().info("֤�����빫������Ƭƥ�䣨>=60�֣�����: " + idPass);
		Logger.get().info("֤�������ֳ���Ƭƥ�䣨>=60�֣�����: " + capturePass);
		Logger.get().info("֤�����빫������Ƭ���ֳ���Ƭ��ƥ�䣨>=60�֣�����: " + bothPass);
	}
	
	public void analyze2_10Result()
	{
		int capturePass = 0;
		for(JunYuRecord item : junyu2PhotoMap.get(10))
		{
			if(item.captureSimilarity >= 60)
			{
				capturePass ++;
			}
		}
		Logger.get().info("֤�������ֳ���Ƭƥ�䣨>=60�֣����� " + capturePass);
	}
	
	public void print3_NCount(int returnValue)
	{
		Logger.get().info("����" + returnValue + "����: " + junyu3PhotoMap.get(returnValue).size());
	}
	
	public void print2_NCount(int returnValue)
	{
		Logger.get().info("����" + returnValue + "����: " + junyu2PhotoMap.get(returnValue).size());
	}
}
