package catfish.sales.services;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.database.DataSourceFactory;
import catfish.base.database.Database;
import catfish.base.database.TransactionTemplateHelper;
import catfish.sales.objects.MerchantStoreObject;

public class MerchantStoreServiceTest {
    
    private static Database getMysqlDatabase(){
        String mysqlConn = StartupConfig.get("catfish.mysql.URL");
        int minConnections = StartupConfig.getAsInt("catfish.mysql.min.connections");
        int maxConnections =  StartupConfig.getAsInt("catfish.mysql.max.connections");
        testDatasource = DataSourceFactory.mysql(mysqlConn, minConnections, maxConnections);

        return new Database(testDatasource);
    }
    
    private static MerchantStoreObject create(){
        MerchantStoreObject merchantStoreObject = new MerchantStoreObject();
        merchantStoreObject.Id = UUID.randomUUID().toString();
        merchantStoreObject.Code = "310108550345001";
        merchantStoreObject.EstimatedSalesPerDay = 100;
        merchantStoreObject.EstimatedPersonTraficPerDay = 500;
        merchantStoreObject.QrCodeExpireSeconds = 0;
        merchantStoreObject.QrCodeSceneId = 172;
        
        merchantStoreObject.Type = 3;
        merchantStoreObject.StoreCategory = 1;
        merchantStoreObject.CommodityCategories = 1;
        merchantStoreObject.CommodityBrands = 3;
        merchantStoreObject.ExtraCode = 1;
        merchantStoreObject.DateAdded = new Date();
        merchantStoreObject.LastModified = merchantStoreObject.DateAdded; 
        
        return merchantStoreObject;
    }
    
    private static Database testDatabase;
    private static DataSource testDatasource;
    
    @BeforeClass
    public static void beforeClass(){
        StartupConfig.initialize();
        Logger.initialize();
        
        testDatabase = getMysqlDatabase();
    }
    
    @AfterClass
    public static void afterClass(){
        
    }
    
    private POSService merchantStoreService;
    private DataSourceTransactionManager dataSourceTransactionManager;
    private TransactionStatus transactionStatus;
    @Before
    public void setUp(){
        merchantStoreService = new POSService(testDatabase);
        
        dataSourceTransactionManager = new DataSourceTransactionManager(testDatasource);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionStatus = dataSourceTransactionManager.getTransaction(def);
    }
    
    @After
    public void tearDown(){
        dataSourceTransactionManager.rollback(transactionStatus);
    }
    
    @Test
    public void test1(){
        MerchantStoreObject mso = merchantStoreService.get(null);
        Assert.assertNull("MerchantStoreObject is not null", mso);
        
        mso = merchantStoreService.get("123");
        Assert.assertNull("MerchantStoreObject is not null", mso);
        
        MerchantStoreObject newPos = create();
        String id = newPos.Id;
        mso = merchantStoreService.get(id);
        Assert.assertNull("MerchantStoreObject is not null", mso);
        
        boolean result = merchantStoreService.add(newPos);
        Assert.assertTrue(result);
        
        mso = merchantStoreService.get(id);
        Assert.assertNotNull("MerchantStoreObject is null", mso);
        
    }
}
