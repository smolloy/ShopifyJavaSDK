//ProductSearchEngine ProductSearchEngines productSearchEngine productSearchEngines product_search_engines

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
import com.shopify.api.resources.ProductSearchEngine;


@Path("/admin/product_search_engines")
@Consumes("application/json") @Produces("application/json")
public interface ProductSearchEnginesService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=ProductSearchEngine.class)
	@JsonRootName("product_search_engines")
	class ProductSearchEngineList extends ArrayList<ProductSearchEngine> {}
	
    @GET @Path(".json")
    ProductSearchEngineList getProductSearchEngines();

    @GET @Path(".json?{query}")
    ProductSearchEngineList getProductSearchEngines(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    ProductSearchEngine getProductSearchEngine(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    ProductSearchEngine getProductSearchEngine(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    ProductSearchEngine createProductSearchEngine(ProductSearchEngine productSearchEngine);

    @PUT @Path("{id}.json")
    ProductSearchEngine updateProductSearchEngine(@PathParam("id") long id, ProductSearchEngine productSearchEngine);

    @DELETE @Path("{id}.json")
    void deleteProductSearchEngine(@PathParam("id") long id);
}
