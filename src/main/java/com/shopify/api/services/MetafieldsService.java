//Metafield Metafields metafield metafields metafields

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
import com.shopify.api.resources.Metafield;


@Path("/admin/metafields")
@Consumes("application/json") @Produces("application/json")
public interface MetafieldsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Metafield.class)
	@JsonRootName("metafields")
	class MetafieldList extends ArrayList<Metafield> {}
	
    @GET @Path(".json")
    MetafieldList getMetafields();

    @GET @Path(".json?{query}")
    MetafieldList getMetafields(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Metafield getMetafield(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Metafield getMetafield(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Metafield createMetafield(Metafield metafield);

    @PUT @Path("{id}.json")
    Metafield updateMetafield(@PathParam("id") long id, Metafield metafield);

    @DELETE @Path("{id}.json")
    void deleteMetafield(@PathParam("id") long id);
}
