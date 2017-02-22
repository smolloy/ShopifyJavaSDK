//Collect Collects collect collects collects

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
import com.shopify.api.resources.Collect;


@Path("/admin/collects")
@Consumes("application/json") @Produces("application/json")
public interface CollectsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Collect.class)
	@JsonRootName("collects")
	class CollectList extends ArrayList<Collect> {}
	
    @GET @Path(".json")
    CollectList getCollects();

    @GET @Path(".json?{query}")
    CollectList getCollects(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Collect getCollect(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Collect getCollect(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Collect createCollect(Collect collect);

    @PUT @Path("{id}.json")
    Collect updateCollect(@PathParam("id") long id, Collect collect);

    @DELETE @Path("{id}.json")
    void deleteCollect(@PathParam("id") long id);
}
