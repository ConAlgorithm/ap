package engine.rest.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import catfish.framework.restful.IRESTfulService;
import engine.rest.model.CreditModel;


@Path("/credit")
public interface CLCreditCheckRest {
	
	/**
	 * <p>
	 * 送入字段到规则引擎，判断是否需要增信
	 * </p>
	 *
	 * @param userId
	 *            用户ID
	 * @param appId
	 *            CL借款申请ID
	 * @return
	 * 
	 * @Author neo on 2017年2月13日
	 */
	@GET
	@Produces(IRESTfulService.JSON_UTF8)
	@Path("/{userId}/{appId}/need")
	public CreditModel<Boolean> isNeedCreditCheck(@PathParam("userId") String userId, @PathParam("appId") String appId);

	/**
	 * <p>
	 * 用户主动增信提额，获取提额比例
	 * </p>
	 *
	 * @param userId
	 *            用户ID
	 * @param appId
	 *            增信申请ID
	 * @return
	 * 
	 * @Author neo on 2017年2月13日
	 */
	@GET
	@Produces(IRESTfulService.JSON_UTF8)
	@Path("/{userId}/{appId}/percent")
	public CreditModel<Double> getCreditCheckPercent(@PathParam("userId") String userId, @PathParam("appId") String appId);
}
