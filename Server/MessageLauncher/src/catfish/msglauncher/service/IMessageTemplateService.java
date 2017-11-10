package catfish.msglauncher.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import catfish.msglauncher.model.MessageTemplate;
import catfish.msglauncher.model.RestResult;

@Path("message-template")
public interface IMessageTemplateService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<MessageTemplate> list(@QueryParam("templateCode") String templateCode);
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	RestResult add(MessageTemplate messageTemplate);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	RestResult update(MessageTemplate messageTemplate);
}
