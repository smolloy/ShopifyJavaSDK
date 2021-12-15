//CustomCollection CustomCollections customCollection customCollections custom_collections

package com.shopify.api.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shopify.api.client.ShopifyClient;
import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.resources.CustomCollection;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jboss.resteasy.annotations.*;


@Path("/admin/custom_collections")
@Consumes("application/json") @Produces("application/json")
public interface CustomCollectionsService extends BaseShopifyService {

    @ResponseObject
    public interface PaginatedResponse {
        @Body
        CustomCollectionList list();

        @Status
        int status();

        @LinkHeaderParam(rel = "next")
        URI nextPage();

        @LinkHeaderParam(rel = "previous")
        URI previousPage();

        @GET
        @LinkHeaderParam(rel = "next")
        PaginatedResponse getNextPage();

        @GET
        @LinkHeaderParam(rel = "previous")
        PaginatedResponse getPreviousPage();
    }


	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=CustomCollection.class)
	@JsonRootName("custom_collections")
	public static class CustomCollectionList extends ArrayList<CustomCollection> {}
	
    @GET @Path(".json")
    PaginatedResponse getCustomCollections();

    @Data
    @Accessors(chain = true)
    public static class Args {
        @QueryParam("title") String title;
        @QueryParam("product_id") String productId;
        @QueryParam("handle") String handle;
        @QueryParam("published_status") String publishedStatus;
        @QueryParam("limit") int limit = 100;
    }

    @GET @Path(".json")
    PaginatedResponse getCustomCollections(@Form Args arguments);
    
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
