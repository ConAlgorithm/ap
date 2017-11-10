package jma.thirdpartyservices.jxl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;

class JXLPoolingThread extends Thread{
	private Runnable crawler;
	
	public JXLPoolingThread(Runnable crawler)
	{
		this.crawler = crawler;
	}

	public Runnable getCrawler() {
		return crawler;
	}
}

public class JXLThreadWatchdog implements ThreadFactory{

	private static ExecutorService executor;
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {		
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				Logger.get().error(String.format("聚信力 InfoCrawlerThread %s crushed! ", t.getName()),e);
			}
		});
		return t;
	}
	
	public static void initialize()
	{
		executor = Executors.newCachedThreadPool(new JXLThreadWatchdog());
	}
	
	public static void startJXLInfoCrawling(String appId)
	{
		executor.execute(new InfoCrawlerThreadNew(appId));
	}
	
	public static void startJXLInfoCrawlingForCL(QueueMessager requestQueueMessager)
	{
		executor.execute(new InfoCrawlerThreadForCL(requestQueueMessager));
	}
}
