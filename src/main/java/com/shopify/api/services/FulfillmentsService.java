//Fulfillment Fulfillments fulfillment fulfillments fulfillments

package com.shopify.api.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.resources.Fulfillment;


@Path("/admin/orders/{orderId}/fulfillments")
@Consumes("application/json") @Produces("application/json")
public interface FulfillmentsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Fulfillment.class)
	@JsonRootName("fulfillments")
	public static class FulfillmentList extends ArrayList<Fulfillment> {}
	
    @GET @Path(".json")
    FulfillmentList getFulfillments(@PathParam("orderId") Long orderId);

    @GET @Path(".json?{query}")
    FulfillmentList getFulfillments(@PathParam("orderId") Long orderId, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Fulfillment getFulfillment(@PathParam("orderId") Long orderId, @PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Fulfillment getFulfillment(@PathParam("orderId") Long orderId, @PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount(@PathParam("orderId") Long orderId);
    
    @GET @Path("count.json?{query}")
    int getCount(@PathParam("orderId") Long orderId, @Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Fulfillment createFulfillment(@PathParam("orderId") Long orderId, Fulfillment fulfillment);

    @PUT @Path("{id}.json")
    Fulfillment updateFulfillment(@PathParam("orderId") Long orderId, @PathParam("id") long id, Fulfillment fulfillment);

    @DELETE @Path("{id}.json")
    void deleteFulfillment(@PathParam("orderId") Long orderId, @PathParam("id") long id);
}
