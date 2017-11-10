package engine.rule.analyzer.restful.resource;

import java.io.FileInputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import catfish.base.Logger;

@Path("api/sales")
public class SalesResource {

	//1
	@GET
	@Path("/bd/{bdId}/dealer-list")
	@Produces({MediaType.APPLICATION_JSON})
	public String getBD_Dealer_List(@PathParam("bdId")String id)
	{
		try(FileInputStream stream = new FileInputStream("bd-dealer-list.json"))
		{
			byte[] result = new byte[stream.available()];
			stream.read(result);
			return new String(result);
		}catch(Exception e){
		    Logger.get().error(e);
		    return null;
		}
	}
	
	//2
	@GET
	@Path("/dealer/{dealerId}/info")
	@Produces({MediaType.APPLICATION_JSON})
	public String getDealer_Dealer_Info(@PathParam("dealerId")String id)
	{
		try(FileInputStream stream = new FileInputStream("dealer-info.json"))
		{
			byte[] result = new byte[stream.available()];
			stream.read(result);
			return new String(result);
		}catch(Exception e){
		    Logger.get().error(e);
		    return null;
		}
	}
	
	//3
	@GET
	@Path("/dealer/{dealerId}/detail")
	@Produces({MediaType.APPLICATION_JSON})
	public String getDealerDetail(@PathParam("dealerId")String id)
	{
		try(FileInputStream stream = new FileInputStream("dealer-detail.json"))
		{
			byte[] result = new byte[stream.available()];
			stream.read(result);
			return new String(result);
		}catch(Exception e){
		    Logger.get().error(e);
		    return null;
		}
	}
	
	//4
	@GET
	@Path("/bd/{bdId}/dealer-status-list")
	@Produces({MediaType.APPLICATION_JSON})
	public String getDealerStatusList(@PathParam("bdId")String id)
	{
		try(FileInputStream stream = new FileInputStream("dealer-status-list.json"))
		{
			byte[] result = new byte[stream.available()];
			stream.read(result);
			return new String(result);
		}catch(Exception e){
		    Logger.get().error(e);
		    return null;
		}
	}
}
