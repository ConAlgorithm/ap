package jma.thirdpartyservices.reg007;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jma.DatabaseUtils;
import thirdparty.config.REG007Configuration;
import thirdparty.reg007.ResponseData.RegisterWebInfo;
import catfish.base.Logger;
import catfish.base.business.dao.UserDao;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;

import com.google.gson.Gson;

public class REG007CrawlingService implements Runnable {

	private String appId;
	
	private String mobile;
	
	private String qqMail;
	
	private Map<String, List<RegisterWebInfo>> result = new HashMap<String, List<RegisterWebInfo>>();
	
	public REG007CrawlingService(String appId) {
		this.appId = appId;
	}
	
	@Override
	public void run() {
		try {
//			System.out.println(String.format("Start one thread and key is %s and start on %s", this.appId, java.util.Calendar.getInstance().getTime()));
			this.mobile = UserDao.getUserMobileById(this.appId);
			this.qqMail = DatabaseUtils.getUserQQ(this.appId) + "@qq.com";
			
			try {
				result.put(this.mobile, (getKeywordRegisterWebsite(this.mobile)));
			} catch (Exception e) {
				Logger.get().warn(String.format("Get RegisterWebSite With %s error, %s", this.mobile, e.toString()), e);
			}
			
			try {
				result.put(this.qqMail, (getKeywordRegisterWebsite(this.qqMail)));
			} catch (Exception e) {
				Logger.get().warn(String.format("Get RegisterWebSite With %s error, %s", this.qqMail, e.toString()), e);
			}

			AppDerivativeVariableManager.addVariables(
					new AppDerivativeVariable(appId, AppDerivativeVariableNames.CheckRegisterWebSiteScore, getScore()));
			AppDerivativeVariableManager.addVariables(
					new AppDerivativeVariable(appId, AppDerivativeVariableNames.IsRegisterBlackListWebSite, inBlacklist()));
			RawDataStorageManager.addRawDatas(
			        new RawData(appId, RawDataVariableNames.REG007_REGISTERWEBSITE_RAW_DATA, new Gson().toJson(result)));
//			System.out.println(String.format("Finish this job %s  %s", this.appId, java.util.Calendar.getInstance().getTime()));
		} catch (Exception ex) {
			Logger.get().error(String.format("REG007 error! %s", ex.toString()), ex);
		}
	}
	
	public List<RegisterWebInfo> getKeywordRegisterWebsite(String key) {
		return new REG007InfoCrawling(key).run();
	}
	
	public BigDecimal getScore() {
		double score = 0.0;
		Map<String, Object> matchWebsite = new HashMap<String, Object>();
		for(String key : result.keySet()){
			try {
				for(RegisterWebInfo info : result.get(key)) {
					
						if(matchWebsite.containsKey(info.getName())){
							continue;
						}
						if(REG007Configuration.getScore(info.getName()) == null) {
							continue;
						}
						score += Double.parseDouble(REG007Configuration.getScore(info.getName()));
						matchWebsite.put(info.getName(), "true");
//						System.out.println(String.format("%s site and score is %s  %s  %s ", info.getName(), REG007Configuration.getScore(info.getName()), this.mobile, this.qqMail)); // trace score					
				}
			} catch (Exception ex) {
				Logger.get().warn(String.format("REG007 parse website score error : %s", ex.toString()), ex);
				continue;
			}
		}		
		return new BigDecimal(score);
	}
	
	public Boolean inBlacklist() {
		for(String key : result.keySet()){
			try {
				for(RegisterWebInfo info : result.get(key)) {
					if(REG007Configuration.getBlacklist(info.getName()) == null) {
						continue;
					}
					if(REG007Configuration.getBlacklist(info.getName()).equals("ON")) {
//						System.out.println(String.format("%s site is Blacklist website %s", info.getName(), REG007Configuration.getBlacklist(info.getName()))); // trace blacklist
						return true;
					}
				}
			} catch(Exception ex) {
				Logger.get().warn(String.format("REG007 parse blacklist website error : %s", ex.toString()), ex);
				continue;
			}
		}		
		return false;
	}
}