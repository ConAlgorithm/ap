package jma;

import java.util.concurrent.ConcurrentHashMap;

import catfish.base.StartupConfig;

public class Configuration {
	
	  private static String hostname;
	  private static int port;

  public static final String JOB_FAILURE_QUEUE_NAME = "JobFailureQueue";
  public static final String JOB_CL_STATUS_QUEUE_NAME = "CLStatusQueue";  // cash loan

  public static final int JOB_RETRY_INTERVAL = 5;                // seconds
  private static final int DEFAULT_JOB_EXPIRATION = 30;          // seconds

  private static ConcurrentHashMap<String, Class<? extends JobHandler>> jobHandlerMapping =
      new ConcurrentHashMap<>();

  private static String jobRequestQueue;
  private static String jobResponseQueue;

  private static int jobMaxRetries;
  private static int jobWorkerThreads;
  private static String aliyunOssEndpoint;
  private static String aliyunOssAccessId;
  private static String aliyunOssAccessKey;
  private static String aliyunOssBucket;
  private static String identifierUsername;
  private static String identifierPassword;
  private static String zcxyUsername;
  private static String zcxyPassword;
  private static String zcxyEncryptKey;
  private static String zcxyUrl;
  private static String dataSourceUrl;
  private static String dataSourcePort;
  private static String dataSourceAccountName;
  private static String dataSourceAccountPwd;
  private static String dataSourceSeed;
  private static String pengyuanUrl;
  private static String pengyuanUsername;
  private static String pengyuanPassword;
  //骏聿电信URL
  private static String junyuDXUrl;
  //骏聿联通URL
  private static String junyuLTUrl;
  private static String junyuKey;
  private static int junyuMaxRetries;
  //身份证和公安部照片相似度阀值
  private static int junyuIdPhotoSimilarityPass;
  //身份证和现场照相似度阀值
  private static int junyuHeadPhotoSimilarityPass;
  private static String idCardCheckers;

  private static String financeServiceUrl;
  private static String gpsServiceUrl;
  private static String applicationServiceUrl;
  private static int applicationServiceMaxRetries;
  
  private static String cowfishUrl;
  private static String phoebusUrl;
  
  private static String merchantAccountUrl;
  private static int joMaxRetries;
    private static String                                                 baiduBlackListUrl;
    private static String                                                 grasscarpUrl;
    //百度评分url
    private static String baiduScoreUrl;
    private static String                                                 phoebusInstallmentSlaveUrl;
   //聚信立V4.2url
    private static String jxlNewUrl;
    //聚信立版本号
    private static String jxlNewVersion;
    //上海资信url
    private static String shzxUrl;
  public static void readConfiguration() {
	    hostname = StartupConfig.get("catfish.ia.host");
	    port = StartupConfig.getAsInt("catfish.ia.port");
	  
	  
    jobRequestQueue = StartupConfig.get("ia.queue.default.request");
    jobResponseQueue = StartupConfig.get("ia.queue.default.response");
    jobMaxRetries = StartupConfig.getAsInt("catfish.job.MaxRetries");
    jobWorkerThreads = StartupConfig.getAsInt("catfish.job.WorkerThreads");
    aliyunOssEndpoint = StartupConfig.get("catfish.aliyun.oss.Endpoint");
    aliyunOssAccessId = StartupConfig.get("catfish.aliyun.oss.AccessId");
    aliyunOssAccessKey = StartupConfig.get("catfish.aliyun.oss.AccessKey");
    aliyunOssBucket = StartupConfig.get("catfish.aliyun.oss.Bucket");
    identifierUsername = StartupConfig.get("catfish.identifier.Username");
    identifierPassword = StartupConfig.get("catfish.identifier.Password");
    zcxyUsername = StartupConfig.get("catfish.zcxy.username");
    zcxyPassword = StartupConfig.get("catfish.zcxy.password");
    zcxyEncryptKey = StartupConfig.get("catfish.zcxy.encrypt.key");
    zcxyUrl = StartupConfig.get("catfish.zcxy.url");
    dataSourceUrl= StartupConfig.get("catfish.datasource.url");
    dataSourcePort= StartupConfig.get("catfish.datasource.port");
    dataSourceAccountName = StartupConfig.get("catfish.datasource.AccountName");
    dataSourceAccountPwd = StartupConfig.get("catfish.datasource.AccountPwd");
    dataSourceSeed = (StartupConfig.get("catfish.datasource.seed"));
    pengyuanUrl = StartupConfig.get("catfish.pengyuan.url");
    pengyuanUsername = StartupConfig.get("catfish.pengyuan.username");
    pengyuanPassword = StartupConfig.get("catfish.pengyuan.password");
    junyuDXUrl = StartupConfig.get("catfish.junyu.dxurl");
    junyuLTUrl = StartupConfig.get("catfish.junyu.lturl");
    junyuKey = StartupConfig.get("catfish.junyu.key");
    junyuMaxRetries = StartupConfig.getAsInt("catfish.junyu.maxRetries");
    junyuIdPhotoSimilarityPass = StartupConfig.getAsInt("catfish.junyu.idphotoSimilarityPass");
    junyuHeadPhotoSimilarityPass = StartupConfig.getAsInt("catfish.junyu.headphotoSimilarityPass");
    idCardCheckers = StartupConfig.get("catfish.idcard.checkers");
    financeServiceUrl = StartupConfig.get("catfish.rest.finance.URL");
    gpsServiceUrl = StartupConfig.get("catfish.rest.gps.URL");
    applicationServiceUrl = StartupConfig.get("catfish.rest.URL");
    applicationServiceMaxRetries = StartupConfig.getAsInt("catfish.rest.maxRetries");
    cowfishUrl = StartupConfig.get("catfish.cowfish.host");
    phoebusUrl = StartupConfig.get("phoebus.rest.host");
    joMaxRetries = (StartupConfig.getAsInt("catfish.jo.maxRetries"));
    merchantAccountUrl=StartupConfig.get("merchant.account.url");
    baiduBlackListUrl=StartupConfig.get("dsp.api.resource.baiduBlackLsit");
        grasscarpUrl = StartupConfig.get("catfish.grasscarp.host");
    baiduScoreUrl= StartupConfig.get("dsp.api.resource.baiduScore");  
        phoebusInstallmentSlaveUrl = StartupConfig.get("phoebus.installment.slave.host");
    jxlNewUrl = StartupConfig.get("dsp.api.resource.jxlurl");
    jxlNewVersion = StartupConfig.get("dsp.jxl.version");
    shzxUrl = StartupConfig.get("dsp.api.resource.shzxUrl");
  }

    public static String getGrasscarpUrl() {
        return grasscarpUrl;
    }

    public static String getFinanceServiceUrl() {
    return financeServiceUrl;
  }

  public static String getGpsServiceUrl() {
    return gpsServiceUrl;
  }

  public static int getJobMaxRetries() {
    return jobMaxRetries;
  }

  public static int getJobWorkerThreads() {
    return jobWorkerThreads;
  }

  public static String getAliyunOssEndPoint() {
    return aliyunOssEndpoint;
  }

  public static String getAliyunOssAccessId() {
    return aliyunOssAccessId;
  }

  public static String getAliyunOssAccessKey() {
    return aliyunOssAccessKey;
  }

  public static String getAliyunOssBucket() {
    return aliyunOssBucket;
  }

  public static String getIdentifierUsername() {
    return identifierUsername;
  }

  public static String getIdentifierPassword() {
    return identifierPassword;
  }

  public static String getZcxyUsername() {
    return zcxyUsername;
  }

  public static String getZcxyPassword() {
    return zcxyPassword;
  }

  public static String getZcxyEncryptKey() {
    return zcxyEncryptKey;
  }

  public static String getZcxyUrl() {
    return zcxyUrl;
  }
  
  public static String getDataSourceUrl() {
	 return dataSourceUrl;
  }

  public static String getDataSourcePort() {
     return dataSourcePort;
  }

  public static String getDataSourceAccountName() {
	return dataSourceAccountName;
  }
  
  public static String getDataSourceAccountPwd() {
	return dataSourceAccountPwd;
  }

  public static String getPengyuanUrl() {
    return pengyuanUrl;
  }

  public static String getPengyuanUsername() {
    return pengyuanUsername;
  }

  public static String getPengyuanPassword() {
    return pengyuanPassword;
  }

  public static String getJunyuDXUrl() {
	return junyuDXUrl;
  }

  public static String getJunyuLTUrl() {
	return junyuLTUrl;
  }

  public static String getJunyuKey() {
    return junyuKey;
  }

  public static String getIdCardCheckers() {
    return idCardCheckers;
  }

  public static int getJunyuMaxRetries() {
	return junyuMaxRetries;
  }


  public static int getJunyuIdPhotoSimilarityPass() {
	return junyuIdPhotoSimilarityPass;
  }

  public static int getJunyuHeadPhotoSimilarityPass() {
	return junyuHeadPhotoSimilarityPass;
  }

  public static String getApplicationServiceUrl() {
	return applicationServiceUrl;
  }

  public static int getApplicationServiceMaxRetries() {
	return applicationServiceMaxRetries;
  }
  
  public static String getCowfishUrl() {
      return cowfishUrl;
  }
  
  public static String getPhoebusUrl() {
      return phoebusUrl;
  }

@SuppressWarnings("unchecked")
  public static JobHandler createJobHandler(String jobType) {
    String canonicalClassName = String.format("jma.handlers.%sHandler", jobType);

    if (!jobHandlerMapping.containsKey(canonicalClassName)) {
      try {
        jobHandlerMapping.putIfAbsent(
            canonicalClassName,
            (Class<? extends JobHandler>) Class.forName(canonicalClassName));
      } catch (ClassNotFoundException e) {
        throw new RuntimeException("No Job Handler can handle job of type " + jobType);
      }
    }

    try {
      return jobHandlerMapping.get(canonicalClassName).newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Cannot create job handler for job " + jobType);
    }
  }

  public static int getExpirationSeconds(String jobType) {
    return DEFAULT_JOB_EXPIRATION;
  }

  public static String getJobRequestQueue() {
    return jobRequestQueue;
  }

  public static void setJobRequestQueue(String jobRequestQueue) {
    Configuration.jobRequestQueue = jobRequestQueue;
  }

  public static String getJobResponseQueue() {
    return jobResponseQueue;
  }

  public static void setJobResponseQueue(String jobResponseQueue) {
    Configuration.jobResponseQueue = jobResponseQueue;
  }

	public static String getDataSourceSeed() {
		return dataSourceSeed;
	}
	
	public static int getJoMaxRetries() {
		return joMaxRetries;
	}
	
	public static String getMerchantAccountUrl() {
		return merchantAccountUrl;
	}

	public static String getBaiduBLUrl(){
	    return baiduBlackListUrl;
	}
	public static String getBaiduScoreUrl(){
        return baiduScoreUrl;
    }

    public static String getPhoebusInstallmentSlaveUrl() {
        return phoebusInstallmentSlaveUrl;
    }
   
    public static String getJxlNewUrl() {
        return jxlNewUrl;
    }
    
    public static String getJxlNewVersion() {
        return jxlNewVersion;
    }

    public static String getShzxUrl() {
        return shzxUrl;
    }

	public static String getHostname() {
		return hostname;
	}

	public static void setHostname(String hostname) {
		Configuration.hostname = hostname;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		Configuration.port = port;
	}
    
    
}
