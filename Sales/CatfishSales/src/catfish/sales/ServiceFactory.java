package catfish.sales;

import javax.sql.DataSource;

import catfish.base.StartupConfig;
import catfish.base.database.DataSourceFactory;
import catfish.base.database.Database;
import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.database.IDatabaseService;
import catfish.framework.restful.IRESTfulService;
import catfish.services.restful.RESTfulServiceFactory;

public class ServiceFactory implements IServiceFactory{
	public void createServices(IServiceRegister serviceRegister){
		serviceRegister.register(IRESTfulService.class, RESTfulServiceFactory.create(StartupConfig.getSystemProperty()));
	}
	public void initServices(IServiceProvider serviceProvider){
		IDatabaseService dbService = serviceProvider.getService(IDatabaseService.class);
		dbService.register("mysql", getMysqlDatabase());
		dbService.register("sqlserver", getSqlServerDatabase());
	}
	
	private Database getMysqlDatabase(){
		String mysqlConn = "jdbc:mysql://localhost:3306/catfish?user=sa&password=qingchun123&useUnicode=yes&characterEncoding=UTF-8";
		DataSource mysqlDS = DataSourceFactory.mysql(mysqlConn, 2, 4);
		
		return new Database(mysqlDS);
	}
	
	private Database getSqlServerDatabase(){
		String mssqlConn = "jdbc:sqlserver://localhost;Database=catfish;user=sa;password=qingchun123";
		DataSource mssqlDS = DataSourceFactory.sqlserver(mssqlConn, 2, 4);
		return new Database(mssqlDS);
	}
}
