package catfish.notification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HealthHandler extends AbstractHandler  
{  
    public void handle(String target,Request baseRequest,HttpServletRequest request,HttpServletResponse response)   
        throws IOException, ServletException  
    {  
    	baseRequest.setHandled(true);
        response.setContentType("text/plain;charset=utf-8");  
        response.setStatus(HttpServletResponse.SC_OK);  
        byte[] responseBytes = "{\"status\": \"UP\"}".getBytes("UTF-8");
        response.setContentLength(responseBytes.length);
        IOUtils.write(responseBytes, response.getOutputStream());
    }  
}
