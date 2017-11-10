package catfish.notification.receiver;

import java.io.IOException;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;

import catfish.base.Logger;
import catfish.notification.Configuration;
import catfish.notification.HealthHandler;
import catfish.notification.sender.MessagesSender;

public class HttpReceiver {

  //private static final int THREAD_POOL_SIZE = 5;
  private static final int TIMEOUT = 30 * 1000;      // 30 seconds

  public static void trigger() {
    Server server = new Server();

    ServerConnector connector = new ServerConnector(server);
    connector.setIdleTimeout(TIMEOUT);
    connector.setStopTimeout(TIMEOUT);
    connector.setHost(Configuration.getHttpHost());
    connector.setPort(Configuration.getHttpPort());
    server.setConnectors(new Connector[] { connector });

    // Add a single handler on context "/hello"
    ContextHandler healthcontext = new ContextHandler();
    healthcontext.setContextPath( "/health" );
    healthcontext.setHandler( new HealthHandler());
    
    HandlerCollection handlers = new HandlerCollection();
    
    DefaultHandler def  = new DefaultHandler() {
        @Override
        public void handle(
            String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
          try {
        	  if(baseRequest.isHandled())return;
            Logger.get().info("Got http request from " + request.getRemoteAddr());

            boolean isSucceed = MessagesSender.handle(IOUtils.toString(request.getInputStream(), Configuration.UTF_8), 0);
            byte[] responseBytes = String.format("{result:%b}", isSucceed).getBytes("UTF-8");

            response.setContentType("text/plain");
            response.setContentLength(responseBytes.length);
            IOUtils.write(responseBytes, response.getOutputStream());
          } catch (RuntimeException e) {
            Logger.get().warn("Handle notification exception", e);
            throw e;
          }
        }
      };
    
    handlers.setHandlers(new Handler[] {healthcontext,def});

    server.setHandler(handlers);

    try {
      server.start();
      
    } catch (Exception e) {
      Logger.get().fatal("Cannot start http server", e);
    }
  }
  

}

