//Comment Comments comment comments comments

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
import com.shopify.api.resources.Comment;


@Path("/admin/comments")
@Consumes("application/json") @Produces("application/json")
public interface CommentsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Comment.class)
	@JsonRootName("comments")
	class CommentList extends ArrayList<Comment> {}
	
    @GET @Path(".json")
    CommentList getComments();

    @GET @Path(".json?{query}")
    CommentList getComments(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Comment getComment(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Comment getComment(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Comment createComment(Comment comment);

    @PUT @Path("{id}.json")
    Comment updateComment(@PathParam("id") long id, Comment comment);

    @DELETE @Path("{id}.json")
    void deleteComment(@PathParam("id") long id);
}
