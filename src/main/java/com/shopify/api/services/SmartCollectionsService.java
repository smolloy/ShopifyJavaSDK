//SmartCollection SmartCollections smartCollection smartCollections smart_collections

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
import com.shopify.api.resources.SmartCollection;


@Path("/admin/smart_collections")
@Consumes("application/json") @Produces("application/json")
public interface SmartCollectionsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=SmartCollection.class)
	@JsonRootName("smart_collections")
	public static class SmartCollectionList extends ArrayList<SmartCollection> {}
	
    @GET @Path(".json")
    SmartCollectionList getSmartCollections();

    @GET @Path(".json?{query}")
    SmartCollectionList getSmartCollections(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    SmartCollection getSmartCollection(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    SmartCollection getSmartCollection(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    SmartCollection createSmartCollection(SmartCollection smartCollection);

    @PUT @Path("{id}.json")
    SmartCollection updateSmartCollection(@PathParam("id") long id, SmartCollection smartCollection);

    @DELETE @Path("{id}.json")
    void deleteSmartCollection(@PathParam("id") long id);
}
