//Shop Shops shop shops shops

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

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.resources.Shop;


@Path("/admin/shops")
@Consumes("application/json") @Produces("application/json")
public interface ShopsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Shop.class)
	@JsonRootName("shops")
	class ShopList extends ArrayList<Shop> {}
	
    @GET @Path(".json")
    ShopList getShops();

    @GET @Path(".json?{query}")
    ShopList getShops(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Shop getShop(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Shop getShop(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Shop createShop(Shop shop);

    @PUT @Path("{id}.json")
    Shop updateShop(@PathParam("id") long id, Shop shop);

    @DELETE @Path("{id}.json")
    void deleteShop(@PathParam("id") long id);
}
