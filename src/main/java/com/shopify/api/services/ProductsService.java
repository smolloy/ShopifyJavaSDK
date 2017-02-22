//Product Products product products products

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
import com.shopify.api.resources.Product;


@Path("/admin/products")
@Consumes("application/json") @Produces("application/json")
public interface ProductsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Product.class)
	@JsonRootName("products")
	class ProductList extends ArrayList<Product> {}
	
    @GET @Path(".json")
    ProductList getProducts();

    @GET @Path(".json?{query}")
    ProductList getProducts(@Encoded @PathParam("query") String queryParams);
    
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
