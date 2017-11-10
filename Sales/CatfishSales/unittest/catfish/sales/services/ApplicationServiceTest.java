package catfish.sales.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.database.DataSourceFactory;
import catfish.base.database.Database;
import catfish.sales.models.InstallmentApplicationModel;

public class ApplicationServiceTest {

    private static Database getMysqlDatabase(){
        String mysqlConn = StartupConfig.get("catfish.mysql.URL");
        int minConnections = StartupConfig.getAsInt("catfish.mysql.min.connections");
        int maxConnections =  StartupConfig.getAsInt("catfish.mysql.max.connections");
        testDatasource = DataSourceFactory.mysql(mysqlConn, minConnections, maxConnections);

        return new Database(testDatasource);
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
    
    private ApplicationService applicationService;
    private DataSourceTransactionManager dataSourceTransactionManager;
    private TransactionStatus transactionStatus;
    @Before
    public void setUp(){
    	applicationService = new ApplicationService(testDatabase);
        
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
    	
        List<InstallmentApplicationModel> list= applicationService.getAppInfoByPOSList(null, null);
        Assert.assertNull("InstallmentApplicationModel is not null", list);
        
        
    }
}
