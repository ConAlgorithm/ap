package engine.rule.analyzer.restful.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

public class CORSResponseFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext arg0,
			ContainerResponseContext responseContext) throws IOException {
		MultivaluedMap headers = responseContext.getHeaders();
        //"*"为通配，指所有域名都可跨域访问，也可指定域名
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
	}
}
