package export;

import thirdparty.config.QhzxConfiguration;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.file.CSVWriter;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import export.analyzer.IAnalyzable;
import export.analyzer.JunYuAnalyzer;
import export.analyzer.sellercommission.SellerCommissionAnalyzer;
import export.exporter.DerivativeVariablesExporter;
import export.exporter.IExportable;
import export.exporter.RawDataExporter;
import export.exporter.junyu.JunyuExporter;
import export.exporter.jxl.JXLDataHandler;
import export.exporter.merchantview.MerchantViewInfoExporter;
import export.exporter.timer.CountDownTimerExporter;
import export.exporter.ylzh.YLZHMatchInfoExporter;
import export.exporter.ylzh.YLZHReportInfoExporter;
import export.migration.IMigratable;
import export.migration.IdCardCNAreaMapping;
import export.migration.MobilePhoneCNAreaMapping;
import export.migration.commission.CommissionMigration;
import export.migration.copperstreet.RelateProductToCopperStreetFund;
import export.migration.crowdfunding.CrowdFundingMigration;
import export.migration.machinelearn.MLMigration;
import export.migration.qhzx.QHZXMigration;
import exporter.userAttachment.UserAttachmentHandler;

public class Main {

	static{
		StartupConfig.initialize();
		Logger.initialize();
		PersistenceConfig.initialize();	
		HttpClientConfig.initialize();
		QhzxConfiguration.initialize();
	}
	/************************Export***************************************************
	private static final String appIdFile = "AppId.csv";
	private static final DerivativeVariablesExporter merchantViewExp = new MerchantViewInfoExporter(new CSVWriter("merchantView.csv"));
	private static final DerivativeVariablesExporter ylzhReportExp = new YLZHReportInfoExporter(new CSVWriter("ylzh.csv"));
	private static final DerivativeVariablesExporter ylzhMatchExp = new YLZHMatchInfoExporter(new CSVWriter("ylzhMatch.csv"));
	private static final RawDataExporter junyuExp = new JunyuExporter(new CSVWriter("D:/junyu.csv"));
	private static final IExportable timerExporter = new CountDownTimerExporter();
	
	*************************Migration**********************************************
	private static final IMigratable mobileCNAreaMap = new MobilePhoneCNAreaMapping();
	private static final IMigratable idcardAreaMap = new IdCardCNAreaMapping();
	//private static final IMigratable d4CollectionConfig = new D4CollectionConfig(new CSVReader("collection.csv"));
	private static final IMigratable crowdFunding = new CrowdFundingMigration();
	private static final IMigratable relateProductToCopperStreetFund = new RelateProductToCopperStreetFund();
	******************************************************************************
	
	**********************Analyze************************************************
	private static final IAnalyzable junyuMap = new JunYuAnalyzer();
	*******************************************************************************/
	//private static final IAnalyzable sc = new SellerCommissionAnalyzer();
	//private static final IMigratable qhzx = new QHZXMigration();
	//private static final IMigratable commissionMigration = new CommissionMigration();
	private static final IMigratable mlMigration = new MLMigration();
	
	public static void migration()
	{
		//mobileCNAreaMap.migrate();
		//idcardAreaMap.migrate();
		//d4CollectionConfig.migrate();
		//crowdFunding.migrate();
		//relateProductToCopperStreetFund.migrate();
	    //qhzx.migrate();
		//commissionMigration.migrate();
		mlMigration.migrate();
	}
	
	public static void analyze()
	{
		//junyuMap.analyze();
		//sc.analyze();
	}
	
	public static void main(String[] args)
	{
	  
		migration();
		//analyze();
		
		//new JXLDataHandler().handle(appIdFile);
		//new UserAttachmentHandler().handle();
		//timerExporter.export(null, null);
        System.out.println("end!");
        System.exit(-1);
	}
}
