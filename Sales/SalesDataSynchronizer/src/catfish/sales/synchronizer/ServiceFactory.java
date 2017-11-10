package catfish.sales.synchronizer;

import javax.sql.DataSource;

import catfish.base.StartupConfig;
import catfish.base.database.DataSourceFactory;
import catfish.base.database.Database;
import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.database.IDatabaseService;
import catfish.framework.queue.IQueueService;
import catfish.services.queue.QueueServiceFactory;

public class ServiceFactory implements IServiceFactory{
    public void createServices(IServiceRegister serviceRegister){
        serviceRegister.register(IQueueService.class, QueueServiceFactory.create());
    }
    
    public void initServices(IServiceProvider serviceProvider){
        IDatabaseService dbService = serviceProvider.getService(IDatabaseService.class);
        dbService.register("mysql", getMysqlDatabase());
        dbService.register("sqlserver", getSqlServerDatabase());
    }
    
    private Database getMysqlDatabase(){
        //String mysqlConn = "jdbc:mysql://localhost:3306/catfish?user=sa&password=qingchun123&useUnicode=yes&characterEncoding=UTF-8";
        String mysqlConn = StartupConfig.get("catfish.mysql.URL");
        int minConnections = StartupConfig.getAsInt("catfish.mysql.min.connections");
        int maxConnections =  StartupConfig.getAsInt("catfish.mysql.max.connections");
        DataSource mysqlDS = DataSourceFactory.mysql(mysqlConn, minConnections, maxConnections);
        
        return new Database(mysqlDS);
    }
    
    private Database getSqlServerDatabase(){
        String mssqlConn = StartupConfig.get("catfish.database.URL");
        int minConnections = StartupConfig.getAsInt("catfish.database.min.connections");
        int maxConnections =  StartupConfig.getAsInt("catfish.database.max.connections");
        //String mssqlConn = "jdbc:sqlserver://localhost;Database=catfish;user=sa;password=qingchun123";
        DataSource mssqlDS = DataSourceFactory.sqlserver(mssqlConn, minConnections, maxConnections);
        return new Database(mssqlDS);
    }
}
