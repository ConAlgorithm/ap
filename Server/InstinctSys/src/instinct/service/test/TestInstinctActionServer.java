package instinct.service.test;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.dectechsolutions.instinct.InstinctActionWebServiceSoapImpl;

import catfish.base.Logger;
import catfish.base.StartupConfig;

public class TestInstinctActionServer 
{

	protected TestInstinctActionServer() throws Exception 
	{
		String serveraddr = StartupConfig.get("instinct.action.serveraddress");
		System.out.println("Starting Server");
	    InstinctActionWebServiceSoapImpl implementor = new InstinctActionWebServiceSoapImpl();
	    JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
	    svrFactory.setServiceClass(InstinctActionWebServiceSoapImpl.class);
	    svrFactory.setAddress(serveraddr);
	    svrFactory.setServiceBean(implementor);
	    svrFactory.getInInterceptors().add(new LoggingInInterceptor());
	    svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
	    svrFactory.create();
	}

	public static void main(String[] args) throws Exception
	{
	    StartupConfig.initialize();
	    Logger.initialize();
		try
		{
			new TestInstinctActionServer();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error Happens during creating instinct action server!");
		}
	    System.out.println("Server ready...");

//	    Thread.sleep(5 * 60 * 1000);
//	    System.out.println("Server exiting");
//	    System.exit(0);
	}

}
