package jma.thirdpartyservices.reg007;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import thirdparty.config.REG007Configuration;
import catfish.base.Logger;

public class REG007ThreadService implements ThreadFactory{

	private static ExecutorService executor;
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {		
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				Logger.get().error(String.format("REG007Crawling %s crushed! ", t.getName()),e);
			}
		});
		return t;
	}
	
	public static void initialize() {
		executor = Executors.newFixedThreadPool(REG007Configuration.getMaxThread(), new REG007ThreadService());
	}
	
	public static void startCrawling(String appId) {
		executor.execute(new REG007CrawlingService(appId));
	}
}