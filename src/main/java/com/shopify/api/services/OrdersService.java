//Order Orders order orders orders

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
import com.shopify.api.resources.Order;


@Path("/admin/orders")
@Consumes("application/json") @Produces("application/json")
public interface OrdersService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Order.class)
	@JsonRootName("orders")
	class OrderList extends ArrayList<Order> {}
	
    @GET @Path(".json")
    OrderList getOrders();

    @GET @Path(".json?{query}")
    OrderList getOrders(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Order getOrder(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Order getOrder(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Order createOrder(Order order);

    @PUT @Path("{id}.json")
    Order updateOrder(@PathParam("id") long id, Order order);

    @DELETE @Path("{id}.json")
    void deleteOrder(@PathParam("id") long id);
}
