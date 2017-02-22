//CustomCollection CustomCollections customCollection customCollections custom_collections

package com.shopify.api.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shopify.api.client.ShopifyClient;
import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.resources.CustomCollection;


@Path("/admin/custom_collections")
@Consumes("application/json") @Produces("application/json")
public interface CustomCollectionsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=CustomCollection.class)
	@JsonRootName("custom_collections")
	class CustomCollectionList extends ArrayList<CustomCollection> {}
	
    @GET @Path(".json")
    CustomCollectionList getCustomCollections();

    @GET @Path(".json?{query}")
    CustomCollectionList getCustomCollections(@PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    CustomCollection getCustomCollection(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    CustomCollection getCustomCollection(@PathParam("id") long id, @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@PathParam("query") String queryParams);

    @POST @Path(".json")
    CustomCollection createCustomCollection(CustomCollection customCollection);

    @PUT @Path("{id}.json")
    CustomCollection updateCustomCollection(@PathParam("id") long id, CustomCollection customCollection);

    @DELETE @Path("{id}.json")
    void deleteCustomCollection(@PathParam("id") long id);
}
