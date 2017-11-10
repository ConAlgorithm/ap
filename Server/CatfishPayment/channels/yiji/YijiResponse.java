package yiji;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import catfish.finance.payment.api.PaymentController;
import catfish.finance.payment.api.PaymentResponse;

@Path("yiji")
public class YijiResponse {
    protected PaymentController controller;
    
    public YijiResponse(PaymentController controller){
        this.controller = controller;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean getNames(@PathParam("id")String appId){
        PaymentResponse response = new PaymentResponse();
        response.setAppId(appId);
        this.controller.payDone("yiji", response);
        return true;
    }
}
