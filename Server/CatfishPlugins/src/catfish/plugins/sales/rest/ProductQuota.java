package catfish.plugins.sales.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import catfish.framework.restful.IRESTfulService;
import catfish.plugins.sales.model.ProductQuotaAddHandler;
import catfish.plugins.sales.model.ProductQuotaDetailModel;
import catfish.plugins.sales.rest.handler.ProductQuotaAndProductIdHandler;
import catfish.plugins.sales.rest.handler.ProductQuotaGetAllDetailHandler;
import catfish.plugins.sales.rest.handler.ProductQuotaGetAllHandler;
import catfish.plugins.sales.rest.handler.ProductQuotaHandler;
import catfish.plugins.sales.rest.handler.ProductQuotaSoftDeleteHandler;
import catfish.plugins.sales.rest.handler.ProductQuotaZeroFeeProductAvailabilityHandler;

@Path("productQuota")
public class ProductQuota {

  @GET
  @Path("/zeroFeeProductAvailability")
  @Produces(IRESTfulService.JSON_UTF8)
  public String getZeroFeeProductAvailability(
      @QueryParam("productId") String productId, @QueryParam("d1Id") String d1Id)
  {
      ProductQuotaZeroFeeProductAvailabilityHandler handler =
          new ProductQuotaZeroFeeProductAvailabilityHandler(productId, d1Id);
      handler.handle();
      return handler.getResponse();
  }

  @GET
  @Path("/quota")
  @Produces(IRESTfulService.JSON_UTF8)
  public String getQuotaByProductIdAndD3Id(
      @QueryParam("productId") String productId, @QueryParam("d3Id") String d3Id)
  {
      ProductQuotaHandler handler = new ProductQuotaHandler(productId, d3Id);
      handler.handle();
      return handler.getResponse();
  }

  @GET
  @Path("/productAndQuota")
  @Produces(IRESTfulService.JSON_UTF8)
  public String getProductIdAndQuotaByD3Id(@QueryParam("d3Id") String d3Id)
  {
      ProductQuotaAndProductIdHandler handler = new ProductQuotaAndProductIdHandler(d3Id);
      handler.handle();
      return handler.getResponse();
  }

  @GET
  @Path("/all")
  @Produces(IRESTfulService.JSON_UTF8)
  public String getAll()
  {
      ProductQuotaGetAllHandler handler = new ProductQuotaGetAllHandler();
      handler.handle();
      return handler.getResponse();
  }

  @GET
  @Path("/allDetail")
  @Produces(IRESTfulService.JSON_UTF8)
  public String getAllDetail()
  {
      ProductQuotaGetAllDetailHandler handler = new ProductQuotaGetAllDetailHandler();
      handler.handle();
      return handler.getResponse();
  }

  @POST
  @Path("/")
  @Produces(IRESTfulService.JSON_UTF8)
  @Consumes(MediaType.APPLICATION_JSON)
  public String addProductQuota(ProductQuotaDetailModel model)
  {
      ProductQuotaAddHandler handler = new ProductQuotaAddHandler(model);
      handler.handle();
      return handler.getResponse();
  }

  @DELETE
  @Path("/{id}")
  @Produces(IRESTfulService.JSON_UTF8)
  @Consumes(MediaType.TEXT_PLAIN)
  public String SoftDeleteProductQuota(@PathParam("id") String id)
  {
      ProductQuotaSoftDeleteHandler handler = new ProductQuotaSoftDeleteHandler(id);
      handler.handle();
      return handler.getResponse();
  }

}
