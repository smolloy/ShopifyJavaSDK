//Page Pages page pages pages

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
import com.shopify.api.resources.Page;


@Path("/admin/pages")
@Consumes("application/json") @Produces("application/json")
public interface PagesService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Page.class)
	@JsonRootName("pages")
	class PageList extends ArrayList<Page> {}
	
    @GET @Path(".json")
    PageList getPages();

    @GET @Path(".json?{query}")
    PageList getPages(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Page getPage(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Page getPage(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Page createPage(Page page);

    @PUT @Path("{id}.json")
    Page updatePage(@PathParam("id") long id, Page page);

    @DELETE @Path("{id}.json")
    void deletePage(@PathParam("id") long id);
}
