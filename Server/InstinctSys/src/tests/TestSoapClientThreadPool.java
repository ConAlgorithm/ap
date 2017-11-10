package tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import core.InstinctOnlineJudge;
import instinct.service.exception.InstinctServiceException;
import instinct.service.exception.MalFormedApplicationException;
import instinct.service.model.InstinctResult;
import util.FileUtil;

/** A thread of Instinct soap client. */

public final class TestSoapClientThreadPool 
{
	private TestSoapClientThreadPool()
	{
		
	}
	
	public static int workerThreadCount;
	public static float start; 
	public static float end; 
	
	
	public static void main(String[] args)
	{
	    StartupConfig.initialize();
	    Logger.initialize();
	    
	    //Test the performance for the number threads from 9->30
	    for (int i = 9; i < 10; i++)
	    {
	    	TestSoapClientThreadPool.createThreadPool(i);
	    	InstinctRequestQueue.reqQ.addAll(InstinctRequestQueue.testA.subList(0, InstinctRequestQueue.jobNum));
	    }
	}
	
	public static void createThreadPool(int threadCount)
	{	
		workerThreadCount = threadCount;
		ExecutorService pool = Executors.newFixedThreadPool(workerThreadCount);
		int jobNum = InstinctRequestQueue.jobNum;
		
		start = System.nanoTime();
		for (int i = 0; i < workerThreadCount; i++)
		{
			Thread t1 = new SoapClientThread(i); 
		
			pool.execute(t1);
		}
		pool.shutdown();
		try 
		{
			pool.awaitTermination(10, TimeUnit.DAYS);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = System.nanoTime();
		System.out.println("Elapsed time is " + (end - start) + " nano-seconds, from " + start + " to " + end + ".");
		float elapsedSec = (end - start) / 1000 / 1000 / 1000;
		System.out.println("Elapsed time is " + elapsedSec + " seconds.");
		System.out.println("Num of worker thread: " + workerThreadCount + ".");
		System.out.println("Num of job finished: " + jobNum + ".");
		System.out.println("Throughput: " + jobNum / elapsedSec + "/s, " + jobNum / elapsedSec * 60 + "/min, " + jobNum / elapsedSec * 60 * 60 + "/hour.");
		
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM-dd_HH-mm-ss");
		String logFile = 
				"C:/Users/guoqing/Desktop/Instinct/Log/Integration" + "/PIntegration" + "_" + workerThreadCount + "_" + df1.format(new java.util.Date()) + ".log";
		
		FileUtil.writeFile("totalTime(s), workerThreadCount, jobNum, Throughput(/s), Throughput(/min), Throughput(/hour)", logFile);
		FileUtil.writeFile(elapsedSec + ", " + workerThreadCount + ", " 
		+ jobNum + ", " + jobNum / elapsedSec + "/s, " + jobNum / elapsedSec * 60 + "/min, " + jobNum / elapsedSec * 60 * 60 + "/hour", logFile);
	}
}

class SoapClientThread extends Thread 
{
	private int threadIndex;

	public SoapClientThread(int index) 
	{
		this.threadIndex = index;
	}

	@Override
	public void run() 
	{
		while (true) 
		{
			String appId = InstinctRequestQueue.reqQ.poll();
			System.out.println("The appId of thread " + threadIndex + " is " + appId + "!");
			if (appId != null)
			{
				try 
				{
					InstinctResult res = InstinctOnlineJudge.judge(appId, "finalCheck");
					System.out.println("The juding result of thread " + threadIndex + " is " + res.toString() + "!");
				} 
				catch (InstinctServiceException | MalFormedApplicationException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Exiting thread " + this.threadIndex + "....");
				break;
			}
		}
	}
}