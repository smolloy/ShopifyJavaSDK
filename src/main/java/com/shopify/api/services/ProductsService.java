//Product Products product products products

package com.shopify.api.services;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.resources.Product;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jboss.resteasy.annotations.*;


@Path("/admin/products")
@Consumes("application/json") @Produces("application/json")
public interface ProductsService extends BaseShopifyService {

    @ResponseObject
    public interface PaginatedResponse {
        @Body
        ProductList list();

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
	@JsonDeserialize(contentAs=Product.class)
	@JsonRootName("products")
	public static class ProductList extends ArrayList<Product> {}

    @Data
    @Accessors(chain = true)
    public static class Args {
        @QueryParam("title") String title;
        @QueryParam("vendor") String vendor;
        @QueryParam("handle") String handle;
        @QueryParam("status") Product.ProductStatus status;
        @QueryParam("published_status") Product.PublishedStatus publishedStatus;
        @QueryParam("limit") int limit = 100;
    }

    @GET @Path(".json")
    PaginatedResponse getProducts(@Form Args arguments);
    
    @GET @Path("{id}.json")
    Product getProduct(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Product getProduct(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Product createProduct(Product product);

    @PUT @Path("{id}.json")
    Product updateProduct(@PathParam("id") long id, Product product);

    @DELETE @Path("{id}.json")
    void deleteProduct(@PathParam("id") long id);
}
