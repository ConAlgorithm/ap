package tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import core.DataExtractor;

public final class TestDataExtractor 
{
	private TestDataExtractor()
	{
		
	}
	
	private static List<String> appIds = new ArrayList<>();
	private static DataExtractor de; 
//	private static List<String> res = new ArrayList<>();

	public static void main(String[] args)
	{
		StartupConfig.initialize();
		Logger.initialize();
		de = new DataExtractor();
		// All AppIds extracted already
//		for(int year=2014; year<2016; year++)
//			for(int month=1; month<13;month++)
//				de.extractAppIdToFile(year, month);
		
		// Extract history data from 201501-201503
//		for(int month=1; month<2;month++){
//		for(int month=5; month<12;month++){
//			appIds.clear();
//			appIds = de.extractAppIdToList(2015, month);
//			de.clear();
//			try
//			{
//				de.extractHistoryToFile(appIds, 2015, month);
//			}
//			catch (Exception ex)
//			{
//				ex.printStackTrace();
//				return;
////				continue;
//			}
//		}
//		de.extractManyBlackListToFile(100);
		de.extractAllBlackListToFile();

//		int year = Calendar.YEAR;
		for (int year = 2015; year < Calendar.YEAR; year++)
		{
			for (int month = 12; month > 0; month--)
			{ //11 10 9 8 7 6 5 4 3 2 1
	//		for(int month=9; month<10; month++){ //11 10 9 8 7 6 5 4 3 2 1
				appIds.clear();
				appIds = de.extractAppIdToList(year, month);
	//			Collections.shuffle(appIds);
				de.extractMassiveHistoryToFile(appIds, year, month);
	
	//			int numOfLoops=0;
	//			while(numOfLoops<5){
	//				numOfLoops++;
	//				for(int i=100; i<101; i++)
	//				{
	//					de.clear();
	//					res.clear();
	//					try
	//					{
	////						de.extractMassiveHistoryToFile(appIds, year, month);
	//						res = de.extractMassiveHistoryToList(appIds, year, month, i);
	//		//				res.forEach(record->{
	//		//					System.out.println(record);
	//		//				});
	//						System.out.println("########################################");
	//						System.out.println("##########One set is completed!!########");
	//						System.out.println("########################################");
	//						System.out.println();
	//					}
	//					catch (Exception ex)
	//					{
	//						ex.printStackTrace();
	//		//				return;
	//						continue;
	//					}
	//				}
	//			}
			}
		}
	}
}
