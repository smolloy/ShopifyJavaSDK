//Image Images image images images

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
import com.shopify.api.resources.Image;


@Path("/admin/products/{productId}/images")
@Consumes("application/json") @Produces("application/json")
public interface ImagesService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Image.class)
	@JsonRootName("images")
	public static class ImageList extends ArrayList<Image> {}
	
    @GET @Path(".json")
    ImageList getImages(@PathParam("productId") long productId);

    @GET @Path(".json?{query}")
    ImageList getImages(@PathParam("productId") long productId, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Image getImage(@PathParam("productId") long productId, @PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Image getImage(@PathParam("productId") long productId, @PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount(@PathParam("productId") long productId);
    
    @GET @Path("count.json?{query}")
    int getCount(@PathParam("productId") long productId, @Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Image createImage(@PathParam("productId") long productId, Image image);

    @PUT @Path("{id}.json")
    Image updateImage(@PathParam("productId") long productId, @PathParam("id") long id, Image image);

    @DELETE @Path("{id}.json")
    void deleteImage(@PathParam("productId") long productId, @PathParam("id") long id);
}
