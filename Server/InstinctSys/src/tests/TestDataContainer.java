package tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import catfish.base.StartupConfig;
import omni.database.DataContainer;
import omni.database.DatabaseClient;
import omni.database.catfish.dao.CatfishDaoImp;

public final class TestDataContainer 
{
	
	private TestDataContainer()
	{
		
	}
	
	public static void main(String[] args)
	{
		
		StartupConfig.initialize();
		
		CatfishDaoImp dao = DatabaseClient.catfishDao;
		
		List<String> appIds = new LinkedList<>(); 

//		appIds = dao.getAppIds(2014, 6); 	//422 9s
//		appIds = dao.getAppIds(2015, 2);	//854 75s	

//		appIds = dao.getAppIds(2015, 3); 	//1790 ?s
		
		appIds = dao.getAppIds(2015, 5); //26520
		
		List<String> subset = new ArrayList<>();
		int size = appIds.size();
		int remainedSize;
		int blocksize = 10; // 200 15s ->854 73s // 100 10s -> 854 76s  //250 21s ->854 74s //300 25s-> 854 73s
							//400 30s ->854 72s // 800 69s->854 72s 	//900 74s
		
		// 200 75s ->1970 ?s // 100 40s -> 1970 s // 50 20s -> 1970 s // 10 4s -> 1970 s
		// 5 2s -> 1970 s
		//250 s ->1970 s //300 s-> 1970 s
		//400 s ->1970 s // 800 s->1970 s 	//900 s
		
		// 200 40s ->26520 ?s // 100 20s -> 26520 s // 50 s -> 26520 s // 10 2s -> 26520 s
		// 5 s -> 26520 s
		//250 s ->26520 74s //300 s-> 26520 73s
		//400 s ->26520 72s // 800 s->26520 72s 	//900 s
		int count = 1;
		
		for (remainedSize = size; remainedSize > 0; remainedSize -= blocksize)
		{
			int lastIndex = (remainedSize < blocksize) ? size : (size - remainedSize + blocksize);
			subset = appIds.subList(size - remainedSize, lastIndex);
			DataContainer.setMassiveMongoDv(subset);
			System.out.println("Number " + count++);
			System.out.println("One block completed!!");
//			break;
		}
	}
}
