package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import instinct.model.Applicant;
import instinct.model.Application;
import omni.database.DatabaseClient;
import omni.database.catfish.dao.CatfishDaoImp;
import omni.database.catfish.object.BlackListObject;
import util.FileUtil;

/**
 * This class provides methods to extract history/blacklist/appId data to list/files.
 *
 * @author guoqing
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class DataExtractor 
{

	private static String logFileprefix;
	private static String outfileBInstinctprefix;
	private static String outfileBInstinct;
	private static String outfileCBlackListprefix;
	private static String outfileCBlackList;
	private static String outfileAppIdprefix;
	private static String outfileAppIds;
	private static String outfileFailedAppIdsprefix;
	private static String outfileFailedAppIds;
	private static String outfileFailedReasonsprefix;
	private static String outfileFailedReasons;
	private static String filepostfix = ".txt";
	private HashSet<String> failedAppIds = new HashSet<>();
	private HashMap<String, String> failedReasons = new HashMap<>();

	private static int blocksize;

	private static CatfishDaoImp catfishDao;

	public DataExtractor() 
	{
		catfishDao = DatabaseClient.catfishDao;
		outfileCBlackListprefix = StartupConfig.get("instinct.data.storepath") + StartupConfig.get("instinct.blacklist.fileprefix");
		outfileBInstinctprefix = StartupConfig.get("instinct.data.storepath") + StartupConfig.get("instinct.history.fileprefix");
		outfileAppIdprefix = StartupConfig.get("instinct.data.storepath") + StartupConfig.get("instinct.appid.fileprefix");
		outfileFailedAppIdsprefix = StartupConfig.get("instinct.data.storepath") + StartupConfig.get("instinct.failedappid.fileprefix");
		outfileFailedReasonsprefix = StartupConfig.get("instinct.data.storepath") + StartupConfig.get("instinct.failedreason.fileprefix");
		blocksize = StartupConfig.get("instinct.massivedata.blocksize") 
				== null ? 10 : StartupConfig.getAsInt("instinct.massivedata.blocksize");
		logFileprefix = StartupConfig.get("instinct.data.storepath");
	}

	/**
	 * This method fetches all appIds of <tt>year</tt>-<tt>month</tt> into file <tt>outfileAppIds</tt>.
	 *
	 * @param year the year of appId data to fetch.
	 * @param month the month of appId data to fetch.
	 *
	 */
	public final void extractAppIdToFile(Integer year, Integer month)
	{
		outfileAppIds = outfileAppIdprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		List<String> appIds = catfishDao.getPOSAppIds(year, month);

		try
		{
			FileUtil.deleteFile(outfileAppIds);
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		appIds.forEach(appId->
		{
			FileUtil.writeFile(appId, outfileAppIds);
		});
	}

	/**
	 * This method fetches all appIds of <tt>year</tt>-<tt>month</tt> into a list.
	 *
	 * @param year the year of appId data to fetch.
	 * @param month the month of appId data to fetch.
	 * @return a list of application IDs of <tt>year</tt>-<tt>month</tt>.
	 *
	 */
	public final List<String> extractAppIdToList(Integer year, Integer month)
	{
		return catfishDao.getAppIds(year, month);
	}

	/**
	 * This method fetches the application data within <tt>appIds</tt> of <tt>year</tt>-<tt>month</tt> into file <tt>outfileBInstinct</tt>
	 * in a massive manner.<p>
	 * Number of <tt>blocksize</tt> application data will be accessed at each time. And it is suggested to fetch less than 500 sets of data at a time
	 * due to possible heavy workload of SQLServer and the intensive memory consumption of {@link omni.database.DataContainer}.<p>
	 * All failed appIds will be stored in file <tt>outfileFailedAppIds</tt> with their reasons stored in file <tt>outfileFailedReasons</tt>.
	 *
	 * @param appIds the list storing historical application IDs to fetch.
	 * @param year the year of application data to fetch.
	 * @param month the month of application data to fetch.
	 *
	 */
	public final void extractMassiveHistoryToFile(List<String> appIds, Integer year, Integer month)
	{
		outfileBInstinct = outfileBInstinctprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		outfileFailedAppIds = outfileFailedAppIdsprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		outfileFailedReasons = outfileFailedReasonsprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		try
		{
			FileUtil.deleteFile(outfileBInstinct);
			FileUtil.deleteFile(outfileFailedAppIds);
			FileUtil.deleteFile(outfileFailedReasons);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		List<String> subset = new ArrayList<>();
		List<String> recordList = new ArrayList<>();
		int size = appIds.size();
		int remainedSize;

		for (remainedSize = size; remainedSize > 0; remainedSize -= blocksize)
		{
			int lastIndex = (remainedSize < blocksize) ? size : (size - remainedSize + blocksize);
			subset = appIds.subList(size - remainedSize, lastIndex);
			try
			{
				recordList = InstinctizeModel.allInOne(subset);

				recordList.forEach(record->
				{
					FileUtil.writeFile(record, outfileBInstinct);
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String err = e.toString();
				subset.forEach(appId->
				{
					failedAppIds.add(appId);
					failedReasons.put(appId, err);
				});
				continue;
			}
			recordList.clear();
		}

		failedAppIds.forEach(failedAppId->
		{
			FileUtil.writeFile(failedAppId, outfileFailedAppIds);
		});

		failedReasons.forEach((failedAppId, reason)->
		{
			String msg = failedAppId + " : " + reason;
			FileUtil.writeFile(msg, outfileFailedReasons);
		});
	}

	/**
	 * This method fetches the application data within <tt>appIds</tt> of <tt>year</tt>-<tt>month</tt> into list <tt>resultList</tt>
	 * in a massive manner.<p>
	 * Number of <tt>blocksize</tt> application data will be accessed at each time. And it is suggested to fetch less than 500 sets of data at a time
	 * due to possible heavy workload of SQLServer and the intensive memory consumption of {@link omni.database.DataContainer}.<p>
	 * All failed appIds will be stored in list <tt>failedAppIds</tt> with their reasons stored in list <tt>failedReasons</tt>.
	 *
	 * @param appIds the list storing historical application IDs to fetch.
	 * @param year the year of application data to fetch.
	 * @param month the month of application data to fetch.
	 * @return list of AppIds
	 *
	 */
	public final List<String> extractMassiveHistoryToList(List<String> appIds, Integer year, Integer month)
	{

		String logFile = logFileprefix + "Log/PFetchData" + year.toString() + (month > 9 ? month : ("0" + month.toString())) + "_" + blocksize + "_" + System.currentTimeMillis() + ".log";
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<String> subset = new ArrayList<>();
		List<String> recordList = new ArrayList<>();
		List<String> resultList = new ArrayList<>();
		long startSample = 0;
		long endSample = 0;
		long sumTime = 0;
		long sumCount = 0;

		int sizeOfAppIds = appIds.size();
		int remainedSize;

		for (remainedSize = sizeOfAppIds; remainedSize > 0; remainedSize -= blocksize)
		{
			int lastIndex = (remainedSize < blocksize) ? sizeOfAppIds : (sizeOfAppIds - remainedSize + blocksize);
			subset = appIds.subList(sizeOfAppIds - remainedSize, lastIndex);
			sumCount++;
			startSample = System.currentTimeMillis();
			try
			{
				recordList = InstinctizeModel.allInOne(subset);

//				recordList.forEach(record->{
//				});
				resultList.addAll(recordList);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String err = e.toString();
				subset.forEach(appId->
				{
					failedAppIds.add(appId);
					failedReasons.put(appId, err);
				});
				continue;
			}
			endSample = System.currentTimeMillis();
			sumTime += (endSample - startSample);
			System.out.println("It takes " + (endSample - startSample) + " milli-seconds for this batch.");
			FileUtil.writeFile(df.format(new java.util.Date()) + "," + (endSample - startSample), logFile);
			// 1000 sets data should be enough...
//			if (sumCount > 1000)
//			{
//				break;
//			}
			recordList.clear();
		}

		System.out.println("########################################");
		System.out.println("##########One set is completed!!########");
		System.out.println("########################################");
		System.out.println();
		System.out.println("Total time: " + sumTime + " ms.");
		System.out.println("Total counts: " + sumCount + ".");
		System.out.println("Average time for each batch: " + sumTime / sumCount + " ms.");
		System.out.println("Average time for each app: " + sumTime / sumCount / blocksize + " ms.");
		System.out.println();

		FileUtil.writeFile("Total time: " + sumTime + " ms.", logFile);
		FileUtil.writeFile("Total counts: " + sumCount + ".", logFile);
		FileUtil.writeFile("Average time for each batch: " + sumTime / sumCount + " ms.", logFile);
		FileUtil.writeFile("Average time for each app: " + sumTime / sumCount / blocksize + " ms.", logFile);
		return resultList;
	}

	/**
	 * This method fetches the application data within <tt>appIds</tt> of <tt>year</tt>-<tt>month</tt> into list <tt>resultList</tt>
	 * in a massive manner.<p>
	 * Number of <tt>size</tt> application data will be accessed at each time. And it is suggested to fetch less than 500 sets of data at a time
	 * due to possible heavy workload of SQLServer and the intensive memory consumption of {@link omni.database.DataContainer}.<p>
	 * All failed appIds will be stored in list <tt>failedAppIds</tt> with their reasons stored in list <tt>failedReasons</tt>.
	 *
	 * @param appIds the list storing historical application IDs to fetch.
	 * @param year the year of application data to fetch.
	 * @param month the month of application data to fetch.
	 * @param setSize the blocksize to fetch at one time.
	 * @return list of history data
	 *
	 */
	public final List<String> extractMassiveHistoryToList(List<String> appIds, Integer year, Integer month, int setSize)
	{

		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM-dd_HH-mm-ss");
		String logFile = logFileprefix + "Log/PFetchData" + year.toString() 
							+ (month > 9 ? month : ("0" + month.toString())) + "_" + setSize + "_" + df1.format(new java.util.Date()) + ".log";

		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<String> subset = new ArrayList<>();
		List<String> recordList = new ArrayList<>();
		List<String> resultList = new ArrayList<>();
		long startSample = 0;
		long endSample = 0;
		long totalTime = 0;
		long totalSets = 0;

		int sizeofAppIds = appIds.size();
		int remainedSize;

		for (remainedSize = sizeofAppIds; remainedSize > 0; remainedSize -= setSize)
		{
			int lastIndex = (remainedSize < setSize) ? sizeofAppIds : (sizeofAppIds - remainedSize + setSize);
			subset = appIds.subList(sizeofAppIds - remainedSize, lastIndex);
			totalSets++;
			startSample = System.currentTimeMillis();
			try
			{
				recordList = InstinctizeModel.allInOne(subset);
				resultList.addAll(recordList);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String err = e.toString();
				subset.forEach(appId->
				{
					failedAppIds.add(appId);
					failedReasons.put(appId, err);
				});
				continue;
			}
			endSample = System.currentTimeMillis();
			totalTime += (endSample - startSample);
			System.out.println("It takes " + (endSample - startSample) + " milli-seconds for this batch.");
			FileUtil.writeFile(df.format(new java.util.Date()) + "," + (endSample - startSample), logFile);
			// 1000 sets data should be enough...
//			if (totalSets > 1000) 
//			{
//				break;
//			}
//			if ((setSize > 10) && (totalSets > 100)) 
//			{
//				break;
//			}
			recordList.clear();
		}

		System.out.println("########################################");
		System.out.println("##########One set is completed!!########");
		System.out.println("########################################");
		System.out.println();
		System.out.println("Total time: " + totalTime + " ms.");
		System.out.println("Total counts: " + totalSets + ".");
		System.out.println("Average time for each batch: " + totalTime / totalSets + " ms.");
		System.out.println("Average time for each app: " + totalTime / totalSets / setSize + " ms.");
		System.out.println();

		FileUtil.writeFile("totalTime(ms), totalSets, setSize, avgTimePerSet(ms), avgTimePerApp(ms), TPS", logFile);
		FileUtil.writeFile(totalTime + ", " + totalSets + ", " + setSize + ", " + totalTime / totalSets + ", " + totalTime / totalSets / setSize + ", " 
		+ new java.text.DecimalFormat("#.00").format((double) setSize * 10 * 10 * 10 / ((double) totalTime / (double) totalSets)), logFile);

		return resultList;
	}

	/**
	 * This method fetches the application data within <tt>appIds</tt> of <tt>year</tt>-<tt>month</tt> into file <tt>outfileBInstinct</tt>
	 * one by one.
	 * All failed appIds will be stored in <tt>outfileFailedAppIds</tt> with their reasons stored in file <tt>outfileFailedReasons</tt>.
	 *
	 * @param appIds the list storing historical application IDs to fetch.
	 * @param year the year of application data to fetch.
	 * @param month the month of application data to fetch.
	 *
	 */
	public final void extractHistoryToFile(List<String> appIds, Integer year, Integer month)
	{
		outfileBInstinct = outfileBInstinctprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		outfileFailedAppIds = outfileFailedAppIdsprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		outfileFailedReasons = outfileFailedReasonsprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		try
		{
			FileUtil.deleteFile(outfileBInstinct);
			FileUtil.deleteFile(outfileFailedAppIds);
			FileUtil.deleteFile(outfileFailedReasons);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		Iterator<String> ite = appIds.iterator();

		while (ite.hasNext())
		{
			String appId = ite.next();
			try
			{
				String record = InstinctizeModel.allInOne(appId);
				FileUtil.writeFile(record, outfileBInstinct);
			}
			catch (Exception e)
			{
				Logger.get().info("AppId is " + appId);
				e.printStackTrace();
				String err = e.toString();
				failedAppIds.add(appId);
				failedReasons.put(appId, err);
				continue;
			}
		}

		failedAppIds.forEach(failedAppId->
		{
			FileUtil.writeFile(failedAppId, outfileFailedAppIds);
		});

		failedReasons.forEach((failedAppId, reason)->
		{
			String msg = failedAppId + " : " + reason;
			FileUtil.writeFile(msg, outfileFailedReasons);
		});
	}

	/**
	 * This method fetches the application data within <tt>appIds</tt> into a list one by one.
	 *
	 * @param appIds the list storing historical application IDs to fetch.
	 * @return a list of application IDs of <tt>year</tt>-<tt>month</tt>.
	 *
	 */
	public final List<String> extractHistoryToList(List<String> appIds)
	{
		List<String> res = new ArrayList<>();
		appIds.forEach(appId->
		{
			String record = InstinctizeModel.allInOne(appId);
			res.add(record);
		});
		return res;
	}

	/**
	 * This method fetches all the black list data into file <tt>outfileCBlackList</tt>.
	 */
	public final void extractAllBlackListToFile()
	{
		outfileCBlackList = outfileCBlackListprefix + filepostfix;
		try
		{
			FileUtil.deleteFile(outfileCBlackList);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		ArrayList<BlackListObject> blackListObj = catfishDao.getAllIABlackListObject();
		blackListObj.forEach(black-> {
			Application appblk = new Application();
			Applicant apptblk = new Applicant(new omni.model.ApplicantBlackList(black));
			String record = InstinctizeModel.singleBlackList(appblk, apptblk);
			FileUtil.writeFile(record, outfileCBlackList);
		});
	}

	/**
	 * This method fetches the TOP <tt>numOfBlackList</tt> of black list data into file <tt>outfileCBlackList</tt>.
	 *
	 * @param numOfBlackList number of black list data to fetch.
	 *
	 */
	public final void extractBlackListToFile(int numOfBlackList)
	{
		outfileCBlackList = outfileCBlackListprefix + filepostfix;
		try
		{
			FileUtil.deleteFile(outfileCBlackList);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		ArrayList<BlackListObject> blackListObj = catfishDao.getBlackListObject(numOfBlackList);
		
		blackListObj.forEach(black-> {
			Application appblk = new Application();
			Applicant apptblk = new Applicant(new omni.model.ApplicantBlackList(black));
			String record = InstinctizeModel.singleBlackList(appblk, apptblk);
			FileUtil.writeFile(record, outfileCBlackList);
		});
	}
	
	/**
	 * This method fetches all the black list data into a list. 
	 * 
	 * @return a list of black list application.
	 * 
	 */
	public final List<String> extractAllBlackListToList()
	{
		ArrayList<BlackListObject> blackListObj = catfishDao.getAllIABlackListObject();
		List<String> res = new ArrayList<>(); 
		
		blackListObj.forEach(black-> {
			Application appblk = new Application();
			Applicant apptblk = new Applicant(new omni.model.ApplicantBlackList(black));
			String record = InstinctizeModel.singleBlackList(appblk, apptblk);
			res.add(record);
		});
		return res;
	}
	
	public final HashSet<String> getFailedAppIds()
	{
		return this.failedAppIds;
	}
	
	public final void clearFailedAppIds()
	{
		this.failedAppIds.clear();
	}
	
	public final HashMap<String, String> getFailedReasons()
	{
		return this.failedReasons;
	}
	
	public final void clearFailedReasons()
	{
		this.failedReasons.clear();
	}

	public final void clear() 
	{
		this.clearFailedAppIds();
		this.clearFailedReasons();
	}
	
	/**
	 * This method deletes the existing old history files of <tt>year</tt>-<tt>month</tt>, 
	 * including <tt>outfileBInstinct</tt>, <tt>outfileFailedAppIds</tt>, <tt>outfileFailedReasons</tt> and <tt>outfileAppIds</tt>.
	 * 
	 * @param year the year of application data to fetch.
	 * @param month the month of application data to fetch.
	 * @return true when some of the files is deleted.<p> false when no file is deleted.
	 * 
	 */
	public final Boolean clearHistoryFiles(Integer year, Integer month) 
	{
		boolean del = false;
		boolean del1 = false;
		boolean del2 = false;
		boolean del3 = false;
		boolean del4 = false;
		outfileBInstinct = outfileBInstinctprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		outfileFailedAppIds = outfileFailedAppIdsprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		outfileFailedReasons = outfileFailedReasonsprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		outfileAppIds = outfileAppIdprefix + year.toString() + (month > 9 ? month : ("0" + month.toString())) + filepostfix;
		try
		{
			del1 = FileUtil.deleteFile(outfileBInstinct);
			del2 = FileUtil.deleteFile(outfileFailedAppIds);
			del3 = FileUtil.deleteFile(outfileFailedReasons);
			del4 = FileUtil.deleteFile(outfileAppIds);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		del = del1 | del2 | del3 | del4;
		return del;
	}
	
	/**
	 * This method deletes the existing old black list file <tt>outfileCBlackList</tt>.
	 * 
	 * @return true when some of the files is deleted.<p> false when no file is deleted.
	 * 
	 */
	public final Boolean clearBlacklistFiles() 
	{
		boolean del = false;
		outfileCBlackList = outfileCBlackListprefix + filepostfix;
		try
		{
			del = FileUtil.deleteFile(outfileCBlackList);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return del;
	}
	
}
