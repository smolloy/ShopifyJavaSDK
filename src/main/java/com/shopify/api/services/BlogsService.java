//Blog Blogs blog blogs blogs

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
import com.shopify.api.resources.Blog;


@Path("/admin/blogs")
@Consumes("application/json") @Produces("application/json")
public interface BlogsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Blog.class)
	@JsonRootName("blogs")
	public static class BlogList extends ArrayList<Blog> {}
	
    @GET @Path(".json")
    BlogList getBlogs();

    @GET @Path(".json?{query}")
    BlogList getBlogs(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Blog getBlog(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Blog getBlog(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Blog createBlog(Blog blog);

    @PUT @Path("{id}.json")
    Blog updateBlog(@PathParam("id") long id, Blog blog);

    @DELETE @Path("{id}.json")
	void deleteBlog(@PathParam("id") long id);
}
