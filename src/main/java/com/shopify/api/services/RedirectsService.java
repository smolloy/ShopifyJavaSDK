//Redirect Redirects redirect redirects redirects

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
import com.shopify.api.resources.Redirect;


@Path("/admin/redirects")
@Consumes("application/json") @Produces("application/json")
public interface RedirectsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Redirect.class)
	@JsonRootName("redirects")
	public static class RedirectList extends ArrayList<Redirect> {}
	
    @GET @Path(".json")
    RedirectList getRedirects();

    @GET @Path(".json?{query}")
    RedirectList getRedirects(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Redirect getRedirect(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Redirect getRedirect(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Redirect createRedirect(Redirect redirect);

    @PUT @Path("{id}.json")
    Redirect updateRedirect(@PathParam("id") long id, Redirect redirect);

    @DELETE @Path("{id}.json")
    void deleteRedirect(@PathParam("id") long id);
}
