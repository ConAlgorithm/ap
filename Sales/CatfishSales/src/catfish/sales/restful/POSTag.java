package catfish.sales.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import catfish.framework.restful.IRESTfulService;
import catfish.sales.models.POSTagModel;

public class POSTag {

	private static final String JSON_UTF8=IRESTfulService.JSON_UTF8;;
	/**
	 * 新增门店tag
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(JSON_UTF8)
	public boolean addTag(POSTagModel model){
		return false;
	}
}
